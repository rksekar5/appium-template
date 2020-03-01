package common;

import static common.ConfigReader.getValueFromJsonConfigFile;

import io.qameta.allure.Step;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.AlgorithmParameters;
import java.security.GeneralSecurityException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Base64;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import javax.validation.constraints.NotNull;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Cipher {

  private static final String CIPHER_TYPE = "AES/CBC/PKCS5Padding";

  /**
   * Provides {@link SecretKeySpec}.
   *
   * @param password Password {@link String}
   * @param salt Salt {@link String}
   * @return {@link SecretKeySpec}
   * @throws NoSuchAlgorithmException when requested encryption algorithm is not supported
   * @throws InvalidKeySpecException when secret key could not be generated with given {@link
   *     KeySpec}
   */
  private static SecretKeySpec createSecretKey(char[] password, byte[] salt)
      throws NoSuchAlgorithmException, InvalidKeySpecException {
    final SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA512");
    final KeySpec keySpec = new PBEKeySpec(password, salt, 40000, 128);
    final SecretKey tmpKey = keyFactory.generateSecret(keySpec);
    return new SecretKeySpec(tmpKey.getEncoded(), "AES");
  }

  /** private constructor to hide the implicit public one. */
  private Cipher() {}

  /**
   * Initializes required encryption properties like salt and secret from test data config.
   *
   * @param propName name of the property to read from config
   * @return property value
   * @throws IOException thrown when configuration file could not be read
   */
  @Step("Retrieve Cipher essentials from Test data file")
  private static String initialize(String propName) {
    return getValueFromJsonConfigFile("cipher_config.json", propName);
  }

  /**
   * Encodes a byte array to {@link Base64} encoded {@link String}.
   *
   * @param bytes byte array to encode
   * @return {@link Base64} encoded {@link String} of byte array
   */
  private static String encodeBase64(byte[] bytes) {
    return Base64.getEncoder().encodeToString(bytes);
  }

  /**
   * Decodes a {@link Base64} encoded {@link String} to byte array.
   *
   * @param encoded {@link Base64} encoded {@link String} to decode
   * @return decoded byte array of {@link Base64} encoded {@link String}
   */
  private static byte[] decodeBase64(final String encoded) {
    return Base64.getDecoder().decode(encoded);
  }

  /**
   * Encrypts a given sensitive {@link String}.
   *
   * @param secret sensitive {@link String} that shall be encrypted
   * @return Encrypted payload for given sensitive {@link String}, never null.
   * @throws GeneralSecurityException when {@link javax.crypto.Cipher} instance or {@link
   *     IvParameterSpec} could not be retrieved
   */
  @SuppressWarnings("unused")
  public static String encrypt(@NotNull final String secret) throws GeneralSecurityException {
    final SecretKeySpec secretKeySpec =
        createSecretKey(initialize("secret").toCharArray(), initialize("salt").getBytes());
    final javax.crypto.Cipher ciph = javax.crypto.Cipher.getInstance(CIPHER_TYPE);
    ciph.init(javax.crypto.Cipher.ENCRYPT_MODE, secretKeySpec);
    final AlgorithmParameters encParams = ciph.getParameters();
    final IvParameterSpec paramSpec = encParams.getParameterSpec(IvParameterSpec.class);
    final byte[] cryptoText = ciph.doFinal(secret.getBytes(StandardCharsets.UTF_8));
    final byte[] iv = paramSpec.getIV();
    return encodeBase64(iv) + ":" + encodeBase64(cryptoText);
  }

  /**
   * Decrypts sensitive {@link String} from given encrypted payload.
   *
   * @param cryptoText encrypted payload
   * @return decrypted sensitive {@link String}
   * @throws GeneralSecurityException when {@link javax.crypto.Cipher} instance or {@link
   *     IvParameterSpec} could not be retrieved
   * @throws GeneralSecurityException when secret (key) or salt could not be retrieved from config
   */
  public static String decrypt(@NotNull final String cryptoText) throws GeneralSecurityException {
    final SecretKeySpec secretKeySpec =
        createSecretKey(initialize("secret").toCharArray(), initialize("salt").getBytes());
    final String[] parts = cryptoText.split(":");
    if (parts.length != 2) {
      log.warn("Unexpected number of parts in encoded text");
      throw new GeneralSecurityException(
          "Unable to decrypt because format of encrypted text is incorrect");
    }
    final String iv = parts[0];
    final String secret = parts[1];
    final javax.crypto.Cipher ciph = javax.crypto.Cipher.getInstance(CIPHER_TYPE);
    ciph.init(
        javax.crypto.Cipher.DECRYPT_MODE, secretKeySpec, new IvParameterSpec(decodeBase64(iv)));
    return new String(ciph.doFinal(decodeBase64(secret)));
  }
}

package common;

import static common.Logger.logInfo;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import io.qameta.allure.Step;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.Objects;
import java.util.Properties;
import lombok.extern.slf4j.Slf4j;
import mobile.androidapp.common.AndroidFactory;

@Slf4j
public class ConfigReader {

  private static final Constants constants = new Constants();

  /**
   * Find a json file at {@code fileName} and look for a property named {@code propName}.
   *
   * @param fileName Name of a json configuration file which will be loaded from the configured
   *     config directory.
   * @param propName The name (key) of a property to find.
   * @return The value of the property, never null.
   */
  @Step("Get value from JSON config file")
  public static String getValueFromJsonConfigFile(String fileName, String propName) {

    final File configFile = configFile(fileName);
    final JsonObject jsonObject = parseConfig(configFile);

    if (null == jsonObject.get(propName)) {
      throw new NullPointerException(
          configFile.getAbsolutePath() + " does not contain the property " + propName);
    }
    return jsonObject.get(propName).getAsString();
  }

  /**
   * Try to read the {@code configFile} as a json object.
   *
   * @param configFile a not null file, which must exist.
   * @return The file converted to json, otherwise an exception will be thrown
   */
  private static JsonObject parseConfig(final File configFile) {
    JsonObject jsonObject = null;
    try {
      final JsonElement element = new JsonParser().parse(new FileReader(configFile));
      if (element.isJsonNull()) {
        throw new NullPointerException(
            configFile.getAbsolutePath()
                + " could not be read (parsed as JsonNull), this normally means"
                + " that it is not valid json");
      }
      jsonObject = (JsonObject) element;
    } catch (FileNotFoundException e) {
      log.error("Unable to read {} ", configFile, e);
    }
    if (null == jsonObject) {
      throw new NullPointerException(
          configFile.getAbsolutePath()
              + " could not be read , this normally means it is not valid json");
    }
    return jsonObject;
  }

  private static File configFile(String fileName) {
    final File configFile = new File(constants.getConfigPath() + File.separatorChar + fileName);
    log.debug("Config file: " + configFile);
    if (!configFile.canRead()) {
      throw new NullPointerException(
          configFile.getAbsolutePath()
              + " cannot be read. It probably either doesnt exist "
              + "or has incorrect file permissions");
    }
    return configFile;
  }

  /**
   * Find a property file at {@code locationAndFileName} and look for a property named {@code
   * propName}.
   *
   * @param locationAndFileName Fully qualified path of file to load.
   * @param propName Name of property to read
   * @return The property value (may be null), note that if the field is a password it will be
   *     provided as is (encrypted) and must be decrypted by the client.
   */
  @Step("Get value from a Property file")
  public static String getValueFromPropertyConfigFile(String locationAndFileName, String propName) {
    final Properties prop = new Properties();
    try (FileInputStream fis = new FileInputStream(locationAndFileName)) {
      prop.load(fis);
      final String propValue = prop.getProperty(propName);
      logInfo("Param - " + propName + " is : " + propValue);
      return propValue;
    } catch (final IOException e) {
      log.error("Problem reading property {}", propName);
      return null;
    }
  }

  public static String getGlobalConfigPath() {
    try {
      final String path =
          Objects.requireNonNull(AndroidFactory.class.getClassLoader().getResource("config"))
              .getPath();
      return URLDecoder.decode(path, String.valueOf(StandardCharsets.UTF_8));
    } catch (UnsupportedEncodingException e) {
      throw new RuntimeException("Invalid configuration, encoding not valid");
    }
  }
}

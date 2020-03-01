package common;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

public class Constants implements Serializable {

  private static final long serialVersionUID = -7763032112653322722L;

  public static final String CONFIG_FILE_DIRECTORY;

  public static final String DATA_FILE_DIRECTORY;

  protected static final URL findResource(final String name) {
    if ("".equals(name)) {
      throw new NullPointerException("Must provide a valid folder name");
    }
    final URL url = Constants.class.getClassLoader().getResource(name);
    return Objects.requireNonNull(url);
  }

  static {
    CONFIG_FILE_DIRECTORY = findResource("config").getPath();
    DATA_FILE_DIRECTORY = findResource("data").getPath();
  }

  /**
   * The configured path to configuration files.
   *
   * @return The location of the config folder.
   */
  public String getConfigPath() {
    try {
      return URLDecoder.decode(CONFIG_FILE_DIRECTORY, String.valueOf(StandardCharsets.UTF_8));
    } catch (final UnsupportedEncodingException e) {
      throw new RuntimeException("Invalid configuration, encoding not valid");
    }
  }

  /**
   * The configured path to data files.
   *
   * @return The location of the data folder.
   */
  public String getDataPath() {
    try {
      return URLDecoder.decode(DATA_FILE_DIRECTORY, String.valueOf(StandardCharsets.UTF_8));
    } catch (final UnsupportedEncodingException e) {
      throw new RuntimeException("Invalid configuration, encoding not valid");
    }
  }
}

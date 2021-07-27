package mobile.driverhandler;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import javax.validation.constraints.NotNull;
import java.io.IOException;
import java.net.ServerSocket;

import static mobile.common.ConfigReader.getValueFromJsonConfigFile;

@Slf4j
public class AppFactory {

  public static AppiumDriverLocalService service;
  public static AppiumDriver<MobileElement> appiumDriver;

  public AppiumDriverLocalService startServer() {
    AppiumServiceBuilder serviceBuilder = new AppiumServiceBuilder();
    service = AppiumDriverLocalService.buildService(serviceBuilder);
    boolean serverRunning = !stopServerOnPort(4723);
    if (null != service && serverRunning) {
      service.stop();
      log.debug("Killing Appium service before starting a new session");
    }

    if (service != null) {
      service.start();
    }
    return service;
  }

  /**
   * Try to close server on socket with provided port number.
   *
   * @param portNumber
   * @return true if successful
   */
  private static boolean stopServerOnPort(int portNumber) {

    try {
      ServerSocket serverSocket = new ServerSocket(portNumber);
      serverSocket.close();
      return true;

    } catch (IOException e) {
      return false;
    }
  }

  @SneakyThrows
  public static String readValueFromMobileConfigFile(@NotNull String propName) {
    return getValueFromJsonConfigFile("mobile_config.json", propName);
  }
}

package mobile.driverhandler;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.service.local.AppiumDriverLocalService;
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

  public static AppiumDriver<MobileElement> getAppiumDriver() {
    return appiumDriver;
  }

  public AppiumDriverLocalService startServer() {
    boolean flag = checkIfServerIsRunning(4723);
    if (!flag) {
      service = AppiumDriverLocalService.buildDefaultService();
      service.start();
    }
    return service;
  }

  public static boolean checkIfServerIsRunning(int port) {

    boolean isServerRunning = false;
    ServerSocket serverSocket;
    try {
      serverSocket = new ServerSocket(port);

      serverSocket.close();
    } catch (IOException e) {
      // If control comes here, then it means that the port is in use
      isServerRunning = true;
      service.stop();
    } finally {
      serverSocket = null;
    }
    return isServerRunning;
  }

  @SneakyThrows
  public static String readValueFromMobileConfigFile(@NotNull String propName) {
    return getValueFromJsonConfigFile("mobile_config.json", propName);
  }
}

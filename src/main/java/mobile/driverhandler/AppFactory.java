package mobile.driverhandler;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import lombok.extern.slf4j.Slf4j;

import javax.validation.constraints.NotNull;
import java.io.IOException;
import java.net.ServerSocket;

import static com.diconium.qa.testautomationframework.common.ConfigReader.getValueFromJsonConfigFile;

@Slf4j
public class AppFactory {

  public static AppiumDriverLocalService service;

  public static AppiumDriver<MobileElement> appiumDriver;

  public AppiumDriverLocalService startServer() {
    boolean flag = checkIfServerIsRunningAfterClosingSocket(4723);
    if (flag) {
      service.stop();
      log.debug("Killing Appium service before starting a new session");
    }
    AppiumServiceBuilder serviceBuilder = new AppiumServiceBuilder();
    service = AppiumDriverLocalService.buildService(serviceBuilder);
    service.start();
    return service;
  }

  public static boolean checkIfServerIsRunningAfterClosingSocket(int port) {

    boolean isServerRunning = false;
    ServerSocket serverSocket;
    try {
      serverSocket = new ServerSocket(port);
      serverSocket.close();
    } catch (IOException e) {
      isServerRunning = true;
    }
    return isServerRunning;
  }

  public static String readValueFromMobileConfigFile(@NotNull String propName) {
    return getValueFromJsonConfigFile("mobile_config.json", propName);
  }
}

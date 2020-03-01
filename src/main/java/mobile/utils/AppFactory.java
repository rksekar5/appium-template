package mobile.utils;

import static common.ConfigReader.getValueFromJsonConfigFile;

import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import java.io.IOException;
import java.net.ServerSocket;
import javax.validation.constraints.NotNull;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AppFactory {

  public static AppiumDriverLocalService service;

  public AppiumDriverLocalService startServer() {
    boolean flag = checkIfServerIsRunnningAfterClosingSocker(4723);
    if (flag) {
      service.stop();
      log.debug("Killing Appium service before starting a new session");
    }
    AppiumServiceBuilder serviceBuilder = new AppiumServiceBuilder();
    service = AppiumDriverLocalService.buildService(serviceBuilder);
    service.start();
    return service;
  }

  public static boolean checkIfServerIsRunnningAfterClosingSocker(int port) {

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

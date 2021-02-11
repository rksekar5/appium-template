package mobile.utils;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;

public class CustomFunctions {
    public static File getApkPath(String apkName) {
        Path root = FileSystems.getDefault().getPath("").toAbsolutePath();
        Path filePath = Paths.get(root.toString(), "src", "main", "resources", "apk");
        File apkPathName = new File(filePath.toString() + "/" + apkName + ".apk");
        return apkPathName;
    }

    public static void inputText(String deviceId, String text) throws IOException {
        try {
            Runtime.getRuntime().exec("adb -s " + deviceId + " shell input text " + text + "");
        } catch (IOException e) {
            e.getCause();
        }
    }

    public void clearAppsData(String deviceId, String packageID) {
        try {
            Runtime.getRuntime().exec("adb -s " + deviceId + " shell pm clear " + packageID + "");
        } catch (IOException e) {
            e.getCause();
        }
    }

    public void forceStopApp(String deviceId, String packageID) {
        try {
            Runtime.getRuntime().exec("adb -s " + deviceId + " shell am force-stop " + packageID + "");
        } catch (IOException e) {
            e.getCause();
        }
    }

    public void clearLogBuffer(String deviceId) {
        try {
            Runtime.getRuntime().exec("adb -s " + deviceId + " shell -c");
        } catch (IOException e) {
            e.getCause();
        }
    }

    public void pushFile(String deviceId, String source, String target) {
        try {
            Runtime.getRuntime().exec("adb -s " + deviceId + " push " + source + " " + target + "");
        } catch (IOException e) {
            e.getCause();
        }
    }

    public void pullFile(String deviceId, String source, String target) {
        try {
            Runtime.getRuntime().exec("adb -s " + deviceId + " pull " + source + " " + target + "");
        } catch (IOException e) {
            e.getCause();
        }
    }

    public void deleteFile(String deviceId, String target) {
        try {
            Runtime.getRuntime().exec("adb -s " + deviceId + " shell rm " + target + "");
        } catch (IOException e) {
            e.getCause();
        }
    }
}

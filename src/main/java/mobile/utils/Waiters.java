package mobile.utils;

import com.diconium.qa.testautomationframework.web.WebConstants;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.AppiumFluentWait;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import ru.yandex.qatools.allure.annotations.Step;

import java.time.Duration;

import static java.util.concurrent.TimeUnit.SECONDS;
import static mobile.driverhandler.AndroidFactory.appiumDriver;
import static org.awaitility.Awaitility.await;

@Slf4j
public class Waiters {

    public static AppiumDriver<MobileElement> driver;

    static Wait<AndroidDriver> getFluentWait() {
        return getFluentWait(WebConstants.FLUENT_WAIT_TIMEOUT_SECONDS);
    }


    private static Wait<AndroidDriver> getFluentWait(long timeoutSeconds) {
        return new AppiumFluentWait(appiumDriver)
                .withTimeout(Duration.ofSeconds(timeoutSeconds))
                .pollingEvery(Duration.ofSeconds(2))
                .ignoring(NoSuchElementException.class)
                .ignoring(TimeoutException.class);
    }

    /**
     * Wait until the mobile element is visible
     * @param mobileElement
     * static way -> await().atMost(10, SECONDS).atLeast(1,SECONDS).until(mobileElement::isDisplayed);
     */
    public static void waitUntilMobileElementVisible(MobileElement mobileElement) {
        log.trace("Waiting for {} to be visible", mobileElement);
        getFluentWait().until(ExpectedConditions.visibilityOf(mobileElement));
    }

    /**
     * Wait until the mobile element is not visible
     * @param mobileElement
     */
    public static void waitUntilMobileElementInvisible(MobileElement mobileElement) {
        log.trace("Waiting for {} to be invisible", mobileElement);
        getFluentWait().until(ExpectedConditions.visibilityOf(mobileElement));
        await().atMost(10, SECONDS).atLeast(1,SECONDS)
                .until(() -> !mobileElement.isDisplayed());
    }

    /**
     * Wait until text is present on the mobile element
     * @param mobileElement
     */
    public static void waitUntilTextIsPresent(MobileElement mobileElement, String text) {
        log.trace("Waiting for text \"{}\" to be present", mobileElement);
        getFluentWait().until(ExpectedConditions.textToBePresentInElement(mobileElement, text));
    }

    /**
     * Wait until the mobile element is clickable
     * @param mobileElement
     */
    public static void waitUntilElementClickable(MobileElement mobileElement) {
        log.trace("Waiting for element \"{}\" to be clickable", mobileElement);
        getFluentWait().until(ExpectedConditions.elementToBeClickable(mobileElement));
    }

    /**
     * Wait until expected value is present on the mobile element
     * @param mobileElement
     */
    @Step
    public static void waitUntilValueWillBePresentInElement(MobileElement mobileElement, String text) {
        log.trace("Waiting for element \"{}\" to be clickable", mobileElement);
        getFluentWait().until(ExpectedConditions.textToBePresentInElementValue(mobileElement, text));
    }


}

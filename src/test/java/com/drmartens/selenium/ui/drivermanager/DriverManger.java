package com.drmartens.selenium.ui.drivermanager;

import com.browserstack.BrowserStackSdk;
import dataProvider.ConfigFileReader;
import io.github.bonigarcia.wdm.WebDriverManager;
import java.util.Random;
import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.URL;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

public class DriverManger {
    ConfigFileReader configFileReader = new ConfigFileReader();
    private static final ThreadLocal<WebDriver> driver = ThreadLocal.withInitial(() -> null);
    JavascriptExecutor js;
    private static final ThreadLocal<String> locale = ThreadLocal.withInitial(() -> null);

    public Duration time = Duration.ofSeconds(30);

    public WebDriver getDriver() {
        return driver.get();
    }

    public void quitWebDriver() {
        if (getDriver() != null)
        {
            getDriver().quit();
            driver.remove();
        }
    }

    public void setLocale(String country) {
        locale.set(country);
    }

    public String getLocale() {
        return locale.get();
    }

    public DriverManger getWebdriver() {
        if (isBrowserstackPlatform())
        {
            MutableCapabilities capabilities = new MutableCapabilities();
            HashMap<String, String> bstackOptions = new HashMap<>();
            bstackOptions.putIfAbsent("source", "cucumber-java:sample-cucumber-junit5:v1.0");
            capabilities.setCapability("bstack:options", bstackOptions);
            try {
                driver.set(new RemoteWebDriver(new URL(String.format("https://%s:%s@hub.browserstack.com/wd/hub",
                        configFileReader.bsusername(), configFileReader.bsAccessKey())), capabilities));
            } catch (Exception e) {
                System.out.println("Exception in remotedriver : " + e);
            }
        } else {
            switch (configFileReader.getBrowser()) {
                case "edge":
                    WebDriverManager.edgedriver().setup();
                    driver.set(new EdgeDriver(getEdgeOptions()));
                    break;
                case "chrome":
                    WebDriverManager.chromedriver().setup();
                    driver.set(new ChromeDriver(getChromeOptions()));
                    break;
                default:
                    WebDriverManager.firefoxdriver().setup();
                    driver.set(new FirefoxDriver(getFirefoxOptions()));
                    break;
            }
        }
        maxBrowser();
        return this;
    }

    private ChromeOptions getChromeOptions() {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--remote-allow-origins=*", "--disable-popup-blocking", "--disable-notifications", "--ignore-certificate-errors",
                "--incognito", "--disable-in-process-stack-traces", "--disable-logging", "--log-level=3");
        return chromeOptions;
    }

    private EdgeOptions getEdgeOptions() {
        EdgeOptions edgeOptions = new EdgeOptions();
        edgeOptions.addArguments("--inprivate", "--remote-allow-origins=*");
        return edgeOptions;
    }

    private FirefoxOptions getFirefoxOptions() {
        FirefoxOptions firefoxOptions = new FirefoxOptions();
        firefoxOptions.addArguments("--inprivate", "--remote-allow-origins=*");
        return firefoxOptions;
    }

    public void navigateToURL() {
        driver.get().navigate().to(configFileReader.getApplicationUrl());
    }



    public void maxBrowser() {
        if (!isMobile())
            driver.get().manage().window().maximize();
        // getWebDriver().manage().window().setSize(new Dimension(360, 800));  //Enable when try to run script on mobile resolution locally
    }

    public void implicitlyWait() {
        driver.get().manage().timeouts().implicitlyWait(Duration.ofSeconds(configFileReader.getImplicitlyWait()));
        driver.get().manage().timeouts().pageLoadTimeout(Duration.ofSeconds(configFileReader.getImplicitlyWait()));

    }



    public void handelWindow() {
        sleep(2000);
        try {
            Alert alert = driver.get().switchTo().alert();
            alert.accept();
        } catch (NoAlertPresentException ne) {
            ne.printStackTrace();
        }
    }

    public void cookiesHandel() {
        Set<Cookie> cookies = driver.get().manage().getCookies();
        cookies.size();
        for (Cookie cookie : cookies) {
            System.out.println(cookie.getName() + ":" + cookie.getValue());

        }
        driver.get().manage().deleteAllCookies();
    }

    public void scrollByJavaScriptExecutor(int x, int y) {
        js = (JavascriptExecutor) driver.get();
        js.executeScript("window.scrollBy(" + x + "," + y + ")");
    }

    public void scrollUpToViewByJavaScriptExecutor(WebElement element) {
        js = (JavascriptExecutor) getDriver();
        js.executeScript("arguments[0].scrollIntoView(true);", element);
        sleep(2000);
    }

    public void clickHiddenElementByJavaScriptExecutor(WebElement element) {
        js = (JavascriptExecutor) driver.get();
        js.executeScript("arguments[0].click();", element);
    }

    public void scrollToBottomByJavaScriptExecutor() {
        js = (JavascriptExecutor) driver.get();
        js.executeScript("window.scrollBy(0,document.body.scrollHeight)");
    }

    public void sleep(int ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public DriverManger close() {
        driver.get().close();
        return this;
    }

    // when some product displaying you need to say selenium to wait until some product loaded
    public WebElement waitUntilNumOfElementsGrater(By element, int number) {
        new WebDriverWait(driver.get(), time).until(ExpectedConditions.numberOfElementsToBeMoreThan(element, number));
        return (WebElement) element;
    }

    public WebElement waitUntilElementsVisible(WebElement element) {
        new WebDriverWait(driver.get(), time).until(ExpectedConditions.visibilityOf(element));
        return element;
    }

    public WebElement waitUntilElementsVisibleWithTime(WebElement element, long sec) {
        new WebDriverWait(driver.get(), Duration.ofSeconds(sec)).until(ExpectedConditions.visibilityOf(element));
        return element;
    }

    public WebElement waitUntilElementIsInvisible(WebElement element) {
        new WebDriverWait(driver.get(), time).until(ExpectedConditions.invisibilityOf(element));
        return element;
    }

    public WebElement waitUntilElementIsInvisibleWithTime(WebElement element, long sec) {
        new WebDriverWait(driver.get(), Duration.ofSeconds(sec)).until(ExpectedConditions.invisibilityOf(element));
        return element;
    }

    //whenever clickable action you can use this is to check button clickable or not?
    public WebElement waitForBtnClickable(WebElement element) {
        try {
            return new WebDriverWait(driver.get(), time).until(ExpectedConditions.elementToBeClickable(element));
        } catch (Exception e) {
            System.out.println("Exception: waitForBtnClickable" + e.getMessage());
            return null;
        }
    }

    public WebElement waitForBtnClickableWithTime(WebElement element, long sec) {
        try {
            return new WebDriverWait(driver.get(), Duration.ofSeconds(sec)).until(ExpectedConditions.elementToBeClickable(element));
        } catch (Exception e) {
            System.out.println("Exception: waitForBtnClickable" + e.getMessage());
            return null;
        }
    }

    //Created for Generating random string for Unique email
    public static String randomString() {
        String generatedString = RandomStringUtils.randomAlphabetic(5);
        return generatedString;

    }

    //switch to anotherFrame by element
    public void switchToFrameByElement(String element) {
        WebDriverWait wait = new WebDriverWait(driver.get(), Duration.ofSeconds(30));
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath(element)));
        driver.get().switchTo().frame(driver.get().findElement(By.xpath(element)));
    }

    public static boolean isDisplayed(WebElement element) {
        try {
            if (element.isDisplayed())
                return element.isDisplayed();
        } catch (NoSuchElementException ex) {
            System.out.println("Exception in isDisplayed() = " + ex);
        }
        return false;
    }

    public List<WebElement> waitUntilAllElementsVisible(List<WebElement> element) {
        new WebDriverWait(driver.get(), time).until(ExpectedConditions.visibilityOfAllElements(element));
        return element;
    }

    public void waitUntilPageLoad() {
        try {
            driver.get().manage().timeouts().pageLoadTimeout(time);
        } catch (final Exception ignored) {
        }
    }

    public void pressBrowserBackButton() {
        driver.get().navigate().back();
        waitUntilPageLoad();
    }

    public void staticWait(int seconds) {
        try {
            Thread.sleep(seconds * 1000L);
        } catch (Exception ignored) {
        }
    }

    public void scrollDownUsingLoop(int counter) {
        try {
            while (counter >= 0) {
                scrollByJavaScriptExecutor(0, 250);
                counter--;
            }
            staticWait(2);
        } catch (Exception ignored) {
        }
    }

    public void scrollUpUsingLoop(int counter) {
        try {
            while (counter >= 0) {
                scrollByJavaScriptExecutor(0, -250);
                counter--;
            }
            staticWait(2);
        } catch (Exception ignored) {
        }
    }

    public void scrollToTop() {
        new Actions(getDriver()).sendKeys(Keys.HOME).perform();
        staticWait(2);
    }

    public void moveToElement(WebElement element) {
        new Actions(getDriver())
                .moveToElement(element)
                .perform();
    }

    public void selectDropdownItemByVisibleText(WebElement element, String name) {
        Select dropdown = new Select(element);
        dropdown.selectByVisibleText(name);
    }

    public void click(final WebElement element) {
        try {
            try {
                waitForBtnClickable(element).click();
            } catch (Exception e) {
                clickHiddenElementByJavaScriptExecutor(element);
            }
        } catch (StaleElementReferenceException e) {
            System.out.println("StaleElementReferenceException encountered, refreshing the page and trying again");
            driver.get().navigate().refresh();
            waitUntilPageLoad();
            sleep(2000);
            clickHiddenElementByJavaScriptExecutor(element);
        }
    }

    public boolean isMobile() {
        try {
            Object deviceNameObject = BrowserStackSdk.getCurrentPlatform().get("deviceName");
            if (deviceNameObject != null)
                return true;
            else
                return false;

        } catch (Exception e) {
            return false;
        }
    }

    public boolean isBrowserstackPlatform() {
        try {
            return !(BrowserStackSdk.getCurrentPlatform().isEmpty());
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isAndroidPhone() {
        try {
            String deviceName = BrowserStackSdk.getCurrentPlatform().get("deviceName").toString().toUpperCase().trim();
            if (deviceName.contains("SAMSUNG"))
                return true;
            else
                return false;

        } catch (Exception e) {
            return false;
        }
    }

    public boolean isIphone() {
        try {
            String deviceName = BrowserStackSdk.getCurrentPlatform().get("deviceName").toString().toUpperCase().trim();
            if (deviceName.contains("IPHONE"))
                return true;
            else
                return false;

        } catch (Exception e) {
            return false;
        }
    }

    public WebElement waitUntilElementIsVisibleCustomWait(WebElement element, int timeout) {
        new WebDriverWait(driver.get(), Duration.ofSeconds(timeout)).until(ExpectedConditions.visibilityOf(element));
        return element;
    }

    public WebElement waitForPresenceOfElementWithStalenessOf(final WebElement webElement) {
        try {
            new WebDriverWait(driver.get(), time).until(ExpectedConditions.refreshed(ExpectedConditions.stalenessOf(webElement)));
        } catch (TimeoutException | ClassCastException e) {
            System.out.println("waitForPresenceOfElementWithStalenessOf: Exception");
        }
        return webElement;
    }

    public void refreshBrowser() {
        driver.get().navigate().refresh();
        waitUntilPageLoad();
    }

    public String getCurrentURL() {
        String url;
        try {
            url = driver.get().getCurrentUrl();
        } catch (Exception e) {
            url = (String) ((JavascriptExecutor) driver.get()).executeScript("return window.location.href;");
        }
        return url;
    }

    public boolean isUnitedStateLocale() {
        return locale.get().equalsIgnoreCase("United States");
    }

    public boolean isUnitedKingdomLocale() {
        return locale.get().equalsIgnoreCase("United Kingdom");
    }

    public boolean isFranceLocale() {
        return locale.get().equalsIgnoreCase("France");
    }

    public boolean isItalyLocale() {
        return locale.get().equalsIgnoreCase("Italy");
    }

    public boolean isGermanyLocale() {
        return locale.get().equalsIgnoreCase("Germany");
    }

    public String getRandomPostCode(){
        String randomSelectedPostCode = "";
        String currentUrl = getDriver().getCurrentUrl();
        Random rand = new Random();
        if(isUnitedKingdomLocale()){
            String []postCodesArray_uk = {"TR21 0PL","TR22 0PL","TR23 0PL","TR24 0PL","TR25 0PL"};
            randomSelectedPostCode = postCodesArray_uk[rand.nextInt(5)];
        }else if(isUnitedStateLocale()){
            String []postCodesArray_us = {"10413-2830","10413-2831","10413-2832","10413-2833","10413-2834"};
            randomSelectedPostCode = postCodesArray_us[rand.nextInt(5)];
        }else if(isFranceLocale()){
            String []postCodesArray_fr = {"03380","03381","03382","03383","03384"};
            randomSelectedPostCode = postCodesArray_fr[rand.nextInt(5)];
        }else if(isItalyLocale()){
            String []postCodesArray_it = {"61011","61012","61013","61014","61015"};
            randomSelectedPostCode = postCodesArray_it[rand.nextInt(5)];
        }
        return randomSelectedPostCode;
    }

    public void clearFieldAndEnterText(WebElement element, String text) {
        element.clear();
        element.sendKeys(text);
    }

    public List<WebElement> getAllDropDownOptions(WebElement element){
        Select dropdown = new Select(element);
        return dropdown.getOptions();
    }

    public String enterPhoneNumber() {
        if (isUnitedKingdomLocale())
            return configFileReader.UK_ContactNumber();
        else if (isUnitedStateLocale())
            return configFileReader.US_ContactNumber();
        else if (isFranceLocale())
            return configFileReader.FR_ContactNumber();
        else if (isItalyLocale())
            return configFileReader.IT_ContactNumber();
        else {
            System.out.println("Contact number not available for current locale...!!!");
            return "NA";
        }
    }

    public void switchToNewWindowHandle(String originalWindow)
    {
        Set<String> allWindowHandles = Set.of();
        int counter = 0;
        while (counter < 5 && allWindowHandles.size() != 2)
        {
            sleep(2000);
            counter++;
            allWindowHandles = getDriver().getWindowHandles();
        }

        for (String windowHandle : allWindowHandles) {
            if (!windowHandle.equals(originalWindow)) {
                getDriver().switchTo().window(windowHandle);
                break;
            }
        }
    }
}
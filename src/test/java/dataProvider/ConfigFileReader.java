package dataProvider;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class ConfigFileReader {
    private Properties properties;
    private final String propertyFilePath = "src/test/resources/config/config.properties";

    public ConfigFileReader() {
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(propertyFilePath));
            properties = new Properties();
            try {
                properties.load(reader);
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException("Configuration.properties not found at " + propertyFilePath);
        }
    }

    public String getDriverPath() {
        String driverPath = properties.getProperty("driverPath");
        if (driverPath != null) return driverPath;
        else throw new RuntimeException("driverPath not specified in the config.properties file.");
    }

    public long getImplicitlyWait() {
        String implicitlyWait = properties.getProperty("implicitlyWait");
        if (implicitlyWait != null) return Long.parseLong(implicitlyWait);
        else throw new RuntimeException("implicitlyWait not specified in the config.properties file.");
    }

    public String addForgetPWDEmail() {
        String forgetPWDEmail = properties.getProperty("forgetPWDEmail");
        if (forgetPWDEmail != null) return forgetPWDEmail;
        else throw new RuntimeException("forgetPWDEmail not specified in the config.properties file.");
    }

    public String getApplicationUrl() {
        String url = properties.getProperty("url");
        if (url != null) return url;
        else throw new RuntimeException("url not specified in the config.properties file.");
    }

    public String addRecipientEmail() {
        String recipientEmail = properties.getProperty("recipientEmail");
        if (recipientEmail != null) return recipientEmail;
        else throw new RuntimeException("recipientEmail not specified in the config.properties file.");
    }

    public String confirmRecipientEmail() {
        String confirmRecipientEmail = properties.getProperty("confirmRecipientEmail");
        if (confirmRecipientEmail != null) return confirmRecipientEmail;
        else throw new RuntimeException("url not specified in the config.properties file.");
    }

    public String getBrowser() {
        String browser = properties.getProperty("browser");
        if (browser != null) return browser;
        else throw new RuntimeException("browser not specified in the config.properties file.");
    }

    public String addUKClearPayEmail() {
        String UK_ClearPayEmail = properties.getProperty("UK_ClearPayEmail");
        if (UK_ClearPayEmail != null) return UK_ClearPayEmail;
        else throw new RuntimeException("UK_ClearPayEmail not specified in the config.properties file.");
    }

    public String addUKClearPayPassword() {
        String UK_ClearPayPassword = properties.getProperty("UK_ClearPayPassword");
        if (UK_ClearPayPassword != null) return UK_ClearPayPassword;
        else throw new RuntimeException("UK_ClearPayPassword not specified in the config.properties file.");
    }

    public String addFRClearPayEmail() {
        String FR_ClearPayEmail = properties.getProperty("FR_ClearPayEmail");
        if (FR_ClearPayEmail != null) return FR_ClearPayEmail;
        else throw new RuntimeException("FR_ClearPayEmail not specified in the config.properties file.");
    }

    public String addFRClearPayPassword() {
        String FR_ClearPayPassword = properties.getProperty("FR_ClearPayPassword");
        if (FR_ClearPayPassword != null) return FR_ClearPayPassword;
        else throw new RuntimeException("FR_ClearPayPassword not specified in the config.properties file.");
    }

    public String addITClearPayEmail() {
        String IT_ClearPayEmail = properties.getProperty("IT_ClearPayEmail");
        if (IT_ClearPayEmail != null) return IT_ClearPayEmail;
        else throw new RuntimeException("IT_ClearPayEmail not specified in the config.properties file.");
    }

    public String addITClearPayPassword() {
        String IT_ClearPayPassword = properties.getProperty("IT_ClearPayPassword");
        if (IT_ClearPayPassword != null) return IT_ClearPayPassword;
        else throw new RuntimeException("IT_ClearPayPassword not specified in the config.properties file.");
    }

    public String addUSAfterPayEmail() {
        String US_AfterPayEmail = properties.getProperty("US_AfterPayEmail");
        if (US_AfterPayEmail != null) return US_AfterPayEmail;
        else throw new RuntimeException("US_AfterPayEmail not specified in the config.properties file.");
    }

    public String addUSAfterPayPassword() {
        String US_AfterPayPassword = properties.getProperty("US_AfterPayPassword");
        if (US_AfterPayPassword != null) return US_AfterPayPassword;
        else throw new RuntimeException("US_AfterPayPassword not specified in the config.properties file.");
    }

    public String checkOutLoginUserName() {
        String checkOutLoginUserName = properties.getProperty("checkOutLoginUserName");
        if (checkOutLoginUserName != null) return checkOutLoginUserName;
        else throw new RuntimeException("checkOutLoginUserName not specified in the config.properties file.");
    }

    public String checkOutLoginFormPassword() {
        String checkOutLoginFormPassword = properties.getProperty("checkOutLoginFormPassword");
        if (checkOutLoginFormPassword != null) return checkOutLoginFormPassword;
        else throw new RuntimeException("checkOutLoginFormPassword not specified in the config.properties file.");
    }

    public String CrDrCardNumber() {
        String CrDrCardNumber = properties.getProperty("CrDrCardNumber");
        if (CrDrCardNumber != null) return String.valueOf(Long.parseLong(CrDrCardNumber));
        else throw new RuntimeException("amexCardNumber not specified in the config.properties file.");
    }

    public String CrDrCardExpiryDate() {
        String CrDrCardExpiryDate = properties.getProperty("CrDrCardExpiryDate");
        if (CrDrCardExpiryDate != null) return CrDrCardExpiryDate;
        else throw new RuntimeException("amexCardExpiryDate not specified in the config.properties file.");
    }

    public String CrDrCardSecurityCode() {
        String CrDrCardSecurityCode = properties.getProperty("CrDrCardSecurityCode");
        if (CrDrCardSecurityCode != null) return String.valueOf(Long.parseLong(CrDrCardSecurityCode));
        else throw new RuntimeException("amexCardSecurityCode not specified in the config.properties file.");
    }

    public String amexCardNumber() {
        String amexCardNumber = properties.getProperty("amexCardNumber");
        if (amexCardNumber != null) return String.valueOf(Long.parseLong(amexCardNumber));
        else throw new RuntimeException("amexCardNumber not specified in the config.properties file.");
    }

    public String amexCardExpiryDate() {
        String amexCardExpiryDate = properties.getProperty("amexCardExpiryDate");
        if (amexCardExpiryDate != null) return amexCardExpiryDate;
        else throw new RuntimeException("amexCardExpiryDate not specified in the config.properties file.");
    }

    public String amexCardSecurityCode() {
        String amexCardSecurityCode = properties.getProperty("amexCardSecurityCode");
        if (amexCardSecurityCode != null) return String.valueOf(Long.parseLong(amexCardSecurityCode));
        else throw new RuntimeException("amexCardSecurityCode not specified in the config.properties file.");
    }

    public String cardHolderName() {
        String cardHolderName = properties.getProperty("cardHolderName");
        if (cardHolderName != null) return cardHolderName;
        else throw new RuntimeException("cardHolderName not specified in the config.properties file.");
    }

    public String UK_GiftCardNumber() {
        String UK_GiftCardNumber = properties.getProperty("UK_GiftCardNumber");
        if (UK_GiftCardNumber != null) return UK_GiftCardNumber;
        else throw new RuntimeException("UK_GiftCardNumber not specified in the config.properties file.");
    }

    public String UK_GiftCardSecNumber() {
        String UK_GiftCardSecNumber = properties.getProperty("UK_GiftCardSecNumber");
        if (UK_GiftCardSecNumber != null) return UK_GiftCardSecNumber;
        else throw new RuntimeException("UK_GiftCardSecNumber not specified in the config.properties file.");
    }

    public String US_GiftCardNumber() {
        String US_GiftCardNumber = properties.getProperty("US_GiftCardNumber");
        if (US_GiftCardNumber != null) return US_GiftCardNumber;
        else throw new RuntimeException("US_GiftCardNumber not specified in the config.properties file.");
    }

    public String US_GiftCardSecNumber() {
        String US_GiftCardSecNumber = properties.getProperty("US_GiftCardSecNumber");
        if (US_GiftCardSecNumber != null) return US_GiftCardSecNumber;
        else throw new RuntimeException("US_GiftCardSecNumber not specified in the config.properties file.");
    }

    public String FR_GiftCardNumber() {
        String FR_GiftCardNumber = properties.getProperty("FR_GiftCardNumber");
        if (FR_GiftCardNumber != null) return FR_GiftCardNumber;
        else throw new RuntimeException("FR_GiftCardNumber not specified in the config.properties file.");
    }

    public String FR_GiftCardSecNumber() {
        String FR_GiftCardSecNumber = properties.getProperty("FR_GiftCardSecNumber");
        if (FR_GiftCardSecNumber != null) return FR_GiftCardSecNumber;
        else throw new RuntimeException("FR_GiftCardSecNumber not specified in the config.properties file.");
    }

    public String IT_GiftCardNumber() {
        String IT_GiftCardNumber = properties.getProperty("IT_GiftCardNumber");
        if (IT_GiftCardNumber != null) return IT_GiftCardNumber;
        else throw new RuntimeException("IT_GiftCardNumber not specified in the config.properties file.");
    }

    public String IT_GiftCardSecNumber() {
        String IT_GiftCardSecNumber = properties.getProperty("IT_GiftCardSecNumber");
        if (IT_GiftCardSecNumber != null) return IT_GiftCardSecNumber;
        else throw new RuntimeException("IT_GiftCardSecNumber not specified in the config.properties file.");
    }

    public String addPayPalEmail() {
        String PayPalEmail = properties.getProperty("PayPalEmail");
        if (PayPalEmail != null) return PayPalEmail;
        else throw new RuntimeException("PayPalEmail not specified in the config.properties file.");
    }

    public String addPayPalPassword() {
        String PayPalPassword = properties.getProperty("PayPalPassword");
        if (PayPalPassword != null) return PayPalPassword;
        else throw new RuntimeException("twitterPassword not specified in the config.properties file.");
    }

    public String KlarnaPayCode() {
        String KlarnaPayCode = properties.getProperty("KlarnaPayCode");
        if (KlarnaPayCode != null) return KlarnaPayCode;
        else throw new RuntimeException("KlarnaPayCode not specified in the config.properties file.");
    }

    public String UK_ContactNumber() {
        String UK_ContactNumber = properties.getProperty("UK_ContactNumber");
        if (UK_ContactNumber != null) return UK_ContactNumber;
        else throw new RuntimeException("UK_ContactNumber not specified in the config.properties file.");
    }

    public String US_ContactNumber() {
        String US_ContactNumber = properties.getProperty("US_ContactNumber");
        if (US_ContactNumber != null) return US_ContactNumber;
        else throw new RuntimeException("US_ContactNumber not specified in the config.properties file.");
    }

    public String FR_ContactNumber() {
        String FR_ContactNumber = properties.getProperty("FR_ContactNumber");
        if (FR_ContactNumber != null) return FR_ContactNumber;
        else throw new RuntimeException("FR_ContactNumber not specified in the config.properties file.");
    }

    public String IT_ContactNumber() {
        String IT_ContactNumber = properties.getProperty("IT_ContactNumber");
        if (IT_ContactNumber != null) return IT_ContactNumber;
        else throw new RuntimeException("IT_ContactNumber not specified in the config.properties file.");
    }

    public String DE_ContactNumber() {
        String DE_ContactNumber = properties.getProperty("DE_ContactNumber");
        if (DE_ContactNumber != null) return DE_ContactNumber;
        else throw new RuntimeException("DE_ContactNumber not specified in the config.properties file.");
    }

    public String FI_ContactNumber() {
        String FI_ContactNumber = properties.getProperty("FI_ContactNumber");
        if (FI_ContactNumber != null) return FI_ContactNumber;
        else throw new RuntimeException("FI_ContactNumber not specified in the config.properties file.");
    }

    public String NL_ContactNumber() {
        String NL_ContactNumber = properties.getProperty("NL_ContactNumber");
        if (NL_ContactNumber != null) return NL_ContactNumber;
        else throw new RuntimeException("NL_ContactNumber not specified in the config.properties file.");
    }

    public String loginUserName() {
        String loginUserName = properties.getProperty("loginUserName");
        if (loginUserName != null) return loginUserName;
        else throw new RuntimeException("loginUserName not specified in the config.properties file.");
    }

    public String loginPassword() {
        String loginPassword = properties.getProperty("loginPassword");
        if (loginPassword != null) return loginPassword;
        else throw new RuntimeException("loginPassword not specified in the config.properties file.");
    }

    public String invalidEmailId() {
        String emailId = properties.getProperty("invalidEmailId");
        if (emailId != null) return emailId;
        else throw new RuntimeException("invalidEmailId not specified in the config.properties file.");
    }

    public String UK_PostCode() {
        String UK_PostCode = properties.getProperty("UK_PostCode");
        if (UK_PostCode != null) return UK_PostCode;
        else throw new RuntimeException("UK_PostCode not specified in the config.properties file.");
    }

    public String getYellowHeaderLinkURLPageTitle(String locale) {
        String pageTitle_yellowHeaderLink = null;

        if(locale.contains("/uk/")) {
            pageTitle_yellowHeaderLink = properties.getProperty("pageTitle_yellowHeaderLink_UK");
        }else if(locale.contains("/us/")) {
            pageTitle_yellowHeaderLink = properties.getProperty("pageTitle_yellowHeaderLink_US");
        }else if(locale.contains("/fr/")) {
            pageTitle_yellowHeaderLink = properties.getProperty("pageTitle_yellowHeaderLink_FR");
        }else if(locale.contains("/it/")) {
            pageTitle_yellowHeaderLink = properties.getProperty("pageTitle_yellowHeaderLink_IT");
        }

        if (pageTitle_yellowHeaderLink != null)
            return pageTitle_yellowHeaderLink;
        else
            throw new RuntimeException("pageTitle_yellowHeaderLink not specified in the config.properties file.");
    }

    public String bsusername() {
        String bsusername = properties.getProperty("BROWSERSTACK_USERNAME");
        if (bsusername != null) return bsusername;
        else throw new RuntimeException("BROWSERSTACK_USERNAME not specified in the config.properties file.");
    }

    public String bsAccessKey() {
        String bsAccessKey = properties.getProperty("BROWSERSTACK_ACCESS_KEY");
        if (bsAccessKey != null) return bsAccessKey;
        else throw new RuntimeException("BROWSERSTACK_ACCESS_KEY not specified in the config.properties file.");
    }

    public String getFooterLinkURLPageTitle(String url) {
        String pageTitle_footerLinks = null;

        if(url.contains("/uk/")) {
            pageTitle_footerLinks = properties.getProperty("pageTitle_footerLinks_UK");
        }else if(url.contains("/us/")) {
            pageTitle_footerLinks = properties.getProperty("pageTitle_footerLinks_US");
        }else if(url.contains("/fr/")) {
            pageTitle_footerLinks = properties.getProperty("pageTitle_footerLinks_FR");
        }else if(url.contains("/it/")) {
            pageTitle_footerLinks = properties.getProperty("pageTitle_footerLinks_IT");
        }

        if (pageTitle_footerLinks != null)
            return pageTitle_footerLinks;
        else
            throw new RuntimeException("pageTitle_footerLinks not specified in the config.properties file.");
    }

    public String getModernSlaveryActURL() {
        String modernSlaveryActURL = properties.getProperty("modernSlaveryActURL");

        if (modernSlaveryActURL != null)
            return modernSlaveryActURL;
        else
            throw new RuntimeException("modernSlaveryActURL not specified in the config.properties file.");
    }

    public String getTaxStrategyURL() {
        String taxStrategyURL = taxStrategyURL = properties.getProperty("taxStrategyURL");

        if (taxStrategyURL != null)
            return taxStrategyURL;
        else
            throw new RuntimeException("taxStrategyURL not specified in the config.properties file.");
    }

    public String getStateListForUSA() {
        String stateList_USA = properties.getProperty("stateList_USA");

        if (stateList_USA != null)
            return stateList_USA;
        else
            throw new RuntimeException("stateList_USA is not specified in the config.properties file.");
    }

    public String getExpectedDeliveryOptions(String country) {
        String expectedDeliveryOptions = properties.getProperty("deliveryOptions_" + country.replaceAll(" ",""));

        if (expectedDeliveryOptions != null)
            return expectedDeliveryOptions;
        else
            throw new RuntimeException("expectedDeliveryOptions for the " +  country + " is not specified in the config.properties file.");
    }

    public String gPayUsername() {
        String gPayUsername = properties.getProperty("gPayUsername");
        if (gPayUsername != null) return gPayUsername;
        else throw new RuntimeException("gPayUsername not specified in the config.properties file.");
    }

    public String gPayPassword() {
        String gPayPassword = properties.getProperty("gPayPassword");
        if (gPayPassword != null) return gPayPassword;
        else throw new RuntimeException("gPayPassword not specified in the config.properties file.");
    }
}

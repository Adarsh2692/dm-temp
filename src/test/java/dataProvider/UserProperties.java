package dataProvider;

import com.drmartens.selenium.ui.drivermanager.DriverManger;
import com.drmartens.selenium.ui.step_def.Hooks;
import org.apache.commons.lang3.StringUtils;
import java.util.Date;
import java.util.ResourceBundle;

public class UserProperties extends DriverManger {

    private static final String PREFIX = "user.";
    private static final String ARRAY_DELIMITER = "\\|";
    private static ResourceBundle messages = ResourceBundle.getBundle("config/user");

    public String getUserProperty(final String key)
    {
        return getMessage(PREFIX + key);
    }

    public String getMessage(final String key)
    {
        if (StringUtils.isBlank(key))
        {
            return "";
        }
        else
        {
            return messages.getString(key);
        }
    }

    public String[] getLocalisedArray(final String key)
    {
        final String text = getMessage(key);
        if (text.isEmpty())
        {
            return new String[0];
        }
        else
        {
            return text.split(ARRAY_DELIMITER);
        }
    }

    public String userNewEmail() {
        return String.format(getUserProperty("new.email"), new Date().getTime());
    }

    public String userNewPassword() {
        return getUserProperty("new.password");
    }

    public String userFirstname() {
        return getUserProperty("new.firstname");
    }

    public String userLastname() {
        return getUserProperty("new.lastname");
    }

    public String oosNotifyEmail() {
        return getUserProperty("oosNotifyEmail");
    }

    public String getShippingAddress() {
        return getMessage(PREFIX + "shipping_address." + getLocale().replace(" ", ""));
    }

    public String getExistingUserUsername() {
        return getUserProperty("existingAccountUsername." + getLocale().replace(" ", ""));
    }

    public String getExistingUserPassword() {
        return getUserProperty("existingAccountPassword");
    }

    public String getApplePayFirstname() {
        return getUserProperty("applePay.firstName");
    }

    public String getApplePayLastname() {
        return getUserProperty("applePay.lastName");
    }

    public String getApplePayCountry() {
        return getUserProperty("applePay.country." + getLocale().replace(" ", ""));
    }

    public String getApplePayPostCode() {
        return getUserProperty("applePay.postcode." + getLocale().replace(" ", ""));
    }

    public String getApplePayStreet() {
        return getUserProperty("applePay.street." + getLocale().replace(" ", ""));
    }

    public String getApplePayCity() {
        return getUserProperty("applePay.city." + getLocale().replace(" ", ""));
    }
}

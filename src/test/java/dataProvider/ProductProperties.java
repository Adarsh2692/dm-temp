package dataProvider;

import com.drmartens.selenium.ui.drivermanager.DriverManger;
import com.drmartens.selenium.ui.step_def.Hooks;
import org.apache.commons.lang3.StringUtils;
import java.util.ResourceBundle;

public class ProductProperties extends DriverManger {

    private static final String PREFIX = "product.";
    private static final String ARRAY_DELIMITER = "\\|";
    private static ResourceBundle messages = ResourceBundle.getBundle("config/product");

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

    public String[] getPageTitlesOnClickingShopNowTiles()
    {
        return getLocalisedArray(PREFIX + "ShopNowTiles.NavigatingPageTitles."
                + getLocale().replaceAll("\\s", ""));
    }

    public String getProductWithReviews()
    {
        return getMessage(PREFIX + "reviewOnPDP."
                + getLocale().replaceAll("\\s", ""));
    }

    public String getProductForWishlist()
    {
        return getMessage(PREFIX + "wishlist."
                + getLocale().replaceAll("\\s", ""));
    }

    public double getDeliveryCharges(String deliveryOption)
    {
        return Double.parseDouble(getMessage(PREFIX + "deliveryCharges." + deliveryOption.toLowerCase().replace(" ", "_") + "."
                + getLocale().replace(" ", "")));
    }

    public String getTestProduct()
    {
        return getUserProperty("testProduct." + getLocale().replace(" ", ""));
    }
}

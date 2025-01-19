package dataProvider;

import com.drmartens.selenium.ui.drivermanager.DriverManger;
import org.apache.commons.lang3.StringUtils;
import java.util.HashMap;
import java.util.ResourceBundle;

public class SiteTextProperties extends DriverManger {

    private static final String PREFIX = "text.";
    private static final String ARRAY_DELIMITER = "\\|";
    private static ResourceBundle messages = ResourceBundle.getBundle("config/siteText");

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

    public String getProductReviewFiltersTitle(String title)
    {
        return getMessage(PREFIX + "reviewOnPDP."
                + title.toUpperCase().replaceAll("\\s", "")
                + "."
                + getLocale().replace(" ", ""));
    }

    public String getTextReviewWithImageOnPDP()
    {
        return getMessage(PREFIX + "reviewOnPDP.reviewWithImages."
                + getLocale().replace(" ", ""));
    }

    public String getTextTrueToSizeOnPDP()
    {
        return getMessage(PREFIX + "reviewOnPDP.trueToSize."
                + getLocale().replace(" ", ""));
    }

    public String getSortReviewTextOnPDP()
    {
        return getMessage(PREFIX + "reviewOnPDP.SORT."
                + getLocale().replace(" ", ""));
    }

    public String getLowestRatingSortTextOnPDP()
    {
        return getMessage(PREFIX + "reviewOnPDP.SORT.LOWESTRATING."
                + getLocale().replace(" ", ""));
    }

    public String[] getShareReviewSocialMediaOptions()
    {
        return getLocalisedArray(PREFIX + "reviewOnPDP.shareList");
    }

    public String getTopHeaderText(String text)
    {
        return getMessage(PREFIX + "header." + text.toLowerCase().replace(" ", "_") + "."
                + getLocale().replace(" ", ""));
    }

    public String getTopHeaderViewAllText(String text)
    {
        return getMessage(PREFIX + "header." + text.toLowerCase().replace(" ", "_") + "."
                + getLocale().replace(" ", ""));
    }

    public HashMap<String, String> getCountriesAndCurrenciesList()
    {
        HashMap<String, String> countryAndCurrency = new HashMap<>();
        String[] list =  getLocalisedArray(PREFIX + "countries_currencies." + getLocale().replace(" ", ""));
        for (String pair : list) {
            countryAndCurrency.put(pair.split("=")[0], pair.split("=")[1]);
        }
        return countryAndCurrency;
    }
}
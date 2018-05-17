package Utils;

import java.text.SimpleDateFormat;
import java.util.Locale;

/**
 * Created by idanciu on 9/12/2017.
 */

public interface Constant {
    public static String DATE_FORMAT = "dd MMM yyyy";
    public static SimpleDateFormat SIMPLE_DATE_FORMAT = new SimpleDateFormat(DATE_FORMAT);
    public String FACTURA_KEY = "factura";
    public static final int PREFERENCE_MODE_PRIVATE = 0;
    public static final String PREFERENCE_FILE = "UserPreferenceFile";
    public static final String USERNAME_PREFERENCE_KEY = "username";
    public static final String PASSWORD_PREFERENCE_KEY = "password";
    public static final String CHECKBOX_PREFERENCE_KEY = "checkbox";
    public static final String ANGAJAT_PREFERENCE_KEY = "angajat";
}

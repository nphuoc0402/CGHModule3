package SQLUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CheckTools {
    public static boolean isNumeric(String strNum) {
        if (strNum == null) {
            return false;
        }
        try {
            int d = Integer.parseInt(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }

        return true;
    }


}

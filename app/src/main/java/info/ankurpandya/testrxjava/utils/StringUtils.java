package info.ankurpandya.testrxjava.utils;

import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.widget.EditText;

import com.google.android.material.textfield.TextInputLayout;

import java.text.DecimalFormat;
import java.util.regex.Pattern;

public class StringUtils {

    private static final String TIMER_MIN = "Minutes";
    private static final DecimalFormat amountFormat = new DecimalFormat("0.00");
    private static final String phonePattern = "[0-9\\s+-]{6,15}+";
    private static final String pinCodePattern = "[0-9]{3,10}+";
    private static final String usernamePattern = "[a-zA-Z0-9]{7}+";
    private static final String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=!_*~?`:;])(?=\\S+$).{8,32}$";
    private static String CURRENCY = "PHP";

    public static void setCurrencyForCountry(String counryCode) {
        CURRENCY = getCurrency(counryCode);
    }

    public static String getFormattedContactNumber(String userCountry, String inputPhone) {
        String countryPhoneCode = getCountryPhoneCode(userCountry);
        if (inputPhone.startsWith("+")) {
            return "(+" + countryPhoneCode + ") " + inputPhone.substring(countryPhoneCode.length() + 1);
        } else {
            return "(+" + countryPhoneCode + ") " + inputPhone;
        }
    }

    public static String getOnlyPhoneNumberPart(String userCountry, String inputPhone) {
        String countryPhoneCode = getCountryPhoneCode(userCountry);
        if (inputPhone.startsWith("+")) {
            return inputPhone.substring(countryPhoneCode.length() + 1);
        } else if (inputPhone.startsWith(countryPhoneCode)) {
            return inputPhone.substring(countryPhoneCode.length());
        } else {
            return inputPhone;
        }
    }

    public static boolean checkTextLength(EditText editText, int lengthMin, int lengthMax) {
        String text = editText.getText().toString().trim();
        return text.length() >= lengthMin && text.length() <= lengthMax;
    }

    public static boolean isValidText(EditText editText) {
        return editText.getText().toString().trim().length() > 0;
    }

    public static boolean isValidText(String string) {
        return string != null && string.trim().length() > 0;
    }

    public static boolean isValidPhoneNumber(String text) {
        return text != null && text.matches(phonePattern);
    }

    public static boolean isValidPinCode(String text) {
        return text != null && text.matches(pinCodePattern);
    }

    public static String formatNumberCount(int count) {
        return String.format("%02d", count);
    }

    public static String getFlagUrl(String countryCode) {
        return isValidText(countryCode) ?
                "https://www.countryflags.io/" + countryCode.toLowerCase() + "/flat/64.png" :
                "";
    }

    public static boolean isValidPinCodeLength(String text, String country) {
        switch (country) {
            case "SG":
                return text.length() == 6;
            case "PH":
                return text.length() == 4;
            default:
                return text.length() == 6;
        }
    }

    public static boolean isValidEmail(EditText etEmail) {
        return isValidEmail(etEmail.getText().toString());
    }

    public static boolean isValidEmail(String text) {
        String emailPattern = "^([_a-zA-Z0-9-]{3,}+(\\.[_a-zA-Z0-9-]+)*@[a-zA-Z0-9-]+(\\.[a-zA-Z0-9-]+)*(\\.[a-zA-Z]{1,6}))?$";
        return text.matches(emailPattern);

    }

    public static boolean isAlphaNumeric(String text) {
        String alphaNumericPattern = "^(?=.*[a-zA-Z])(?=.*[0-9])[A-Za-z0-9]+$";
        return text.matches(alphaNumericPattern);

    }

    public static boolean validatePassword(final String password) {
        return Pattern.compile(PASSWORD_PATTERN).matcher(password).matches();
    }

    public static boolean isValidUsername(String text) {
        return text.matches(usernamePattern);
    }

    public static void showError(TextInputLayout textInputLayout, String text) {
        textInputLayout.setErrorEnabled(true);
        textInputLayout.setError(text);
    }

    public static void hideError(TextInputLayout textInputLayout) {
        textInputLayout.setErrorEnabled(false);
        textInputLayout.setError(null);
    }

    public static String getMobileNumberForUse(String number) {
        if (!isValidText(number)) {
            return "";
        }
        number = number.trim();
        String code = "";
        String mobile = "";
        if (number.length() > 4) {
            code = number.substring(0, 4);
            mobile = number.substring(4, number.length());
        } else {
            mobile = number;
        }

        int index = 0;
        for (int i = 0; i < code.length(); i++) {
            if (code.charAt(i) != '0') {
                index = i;
                break;
            }
        }
        code = code.substring(index, code.length());

        //return "(+" + code + ")" + mobile;
        return "+" + code + mobile;
    }

    public static String formatDiscount(String value) {
        return formatDiscount(safeDouble(value));
    }

    public static String formatDiscount(double value) {
        if (Math.floor(value) < value) {
            return amountFormat.format(value);
        } else {
            return ((int) value) + "";
        }
    }

    public static String getAmount(float value) {
        return getAmount(amountFormat.format(value));
    }

    public static String getAmount(String currency, float value) {
        return getAmount(currency, amountFormat.format(value));
    }

    public static String getAmount(double value) {
        return getAmount(amountFormat.format(value));
    }

    public static String getAmount(String currency, double value) {
        return getAmount(currency, amountFormat.format(value));
    }

    public static String getAmountWithoutCurrencySign(double value) {
        return amountFormat.format(value);
    }

    public static String getAmount(String value) {
        return getAmount(CURRENCY, value);
    }

    public static String getAmount(String currency, String value) {
        return currency + " " + value;
    }

    public static String getTimerInMins(double value) {
        //return "" + value / 60 + " " + TIMER_MIN;
        return getTimer(Double.parseDouble(amountFormat.format(value)));
    }

    public static String getTimer(double value) {
        String timeMinutes;
        /*if (value >= 60) {
            double time = value / 60;
            timeMinutes = amountFormat.format(time);
            return timeMinutes + " " + TIMER_MIN;
        } else {
            timeMinutes = amountFormat.format(value);
            return timeMinutes + " " + TIMER_MIN;
        }*/
        double time = value / 60;
        timeMinutes = amountFormat.format(time);
        return timeMinutes + " " + TIMER_MIN;
    }

    public static String getProperStringAmount(String amount) {
        try {
            if (StringUtils.isValidText(amount)) {
                return amountFormat.format(Double.parseDouble(amount));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "0";
    }

    public static String valueOf(float value) {
        return amountFormat.format(value);
    }

    public static String shortDouble(double value) {
        return amountFormat.format(value);
    }

    public static String getDateWithoutTime(String dateWithTime) {
        if (dateWithTime == null) {
            return "";
        }
        String[] arrOfStr = dateWithTime.split("T", 0);
        if (arrOfStr.length > 0) {
            return arrOfStr[0];
        } else {
            return "";
        }
    }

    public static Spannable getHighlightedWord(String mainString, String world, int color) {
        Spannable spannable = new SpannableString(mainString);
        int ofe = mainString.indexOf(world, 0);
        for (int ofs = 0; ofs < mainString.length() && ofe != -1; ofs = ofe + 1) {
            ofe = mainString.indexOf(world, ofs);
            if (ofe == -1)
                break;
            else {
                spannable.setSpan(
                        new ForegroundColorSpan(color),
                        ofe,
                        ofe + world.length(),
                        Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
                );
            }
        }
        //new BackgroundColorSpan(0xFFFFFF00)
        //tv.setText(wordToSpan, TextView.BufferType.SPANNABLE);
        return spannable;
    }

    private static String getCurrency(String countryIsoCode) {
        if (countryIsoCode != null) {
            switch (countryIsoCode) {
                case "PH":
                    return "PHP";
                case "SG":
                    return "SGD";
                case "IN":
                    return "INR";
            }
        }
        return "PHP";
    }

    public static String getCountryPhoneCode(String countryIsoCode) {
        if (countryIsoCode != null) {
            switch (countryIsoCode) {
                case "PH":
                    return "63";
                case "SG":
                    return "65";
                case "IN":
                    return "91";
            }
        }
        return "63";
    }

    public static int getDiscount(double price, double sellingPrice) {
        double discountDouble = ((price - sellingPrice) * 100 / price);
        if (discountDouble > 99) {
            return (int) discountDouble; //99.99 -> 99
        } else {
            return (int) Math.ceil(discountDouble); //0.001 -> 1, 0.002 -> 2, 98.88 -> 99%
        }
    }

    public static double safeDouble(String amount) {
        try {
            return Double.parseDouble(amount);
        } catch (Exception e) {
            return 0;
        }
    }
}

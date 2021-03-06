package controller;

import model.exceptions.*;
import model.user.User;

import java.util.ArrayList;
import java.util.regex.Pattern;

public class Validator {

    public Validator() {

    }

    private static boolean isNumeric(String strNum) {
        if (strNum == null) {
            return false;
        }
        return Pattern.compile("-?\\d+(\\.\\d+)?").matcher(strNum).matches();
    }

    private static boolean hasDigitsOrSpecialCharacters(String str) {
        if (str == null) {
            return true;
        }
        String specialCharacters="!#$%&()*+,-./:;<=>?@[]^_`{|}";
        for (int i = 0; i < str.length(); i++) {
            if (specialCharacters.contains(Character.toString(str.charAt(i))))
                return true;
        }
        return Pattern.compile("[0-9]+", Pattern.CASE_INSENSITIVE).matcher(str).matches();
    }

    public static void isDataFormat(String data) throws InvalidDataException {
        if (!Pattern.compile("^\\d{1,2}\\.\\d{1,2}\\.\\d{4}$",
                Pattern.CASE_INSENSITIVE).matcher(data).matches()) {
            throw new InvalidDataException("Невірний формат дати. Будь ласка," +
                    "введіть дату в наступному форматі: dd.mm.yyyy");
        }
        String[] dataParts = data.split("\\.");
        if (!isNumeric(dataParts[0]) || !isNumeric(dataParts[1])
                || !isNumeric(dataParts[0])) {
            throw new InvalidDataException("Невірний формат дати. Будь ласка," +
                    "перевірте правильність вводу числа, місяця та року.");
        }
        if (!(dataParts[0].matches("^0[1-9]$|^[1-2][0-9]$|^30$")
                && dataParts[1].matches("^4$|^6$|^9$|^10$|^12$") ||
                dataParts[0].matches("^0[1-9]$|^[1-2][0-9]$|^31$")
                && dataParts[1].matches("^1$|^3$|^5$|^7$|^8$|^10$|^12$") ||
                dataParts[0].matches("^0[1-9]$|^[1-2][0-8]$")
                        && dataParts[1].matches("^2$") &&
                        Integer.parseInt(dataParts[2]) % 4 != 0 ||
                dataParts[0].matches("^0[1-9]$|^[1-2][0-8]$|^29$")
                        && dataParts[1].matches("^2$") &&
                        Integer.parseInt(dataParts[2]) % 4 == 0)
        ) {
            throw new InvalidDataException("Невірний формат дати. Будь ласка," +
                    "перевірте чи входить дана дата в діапазон.");
        }
    }

    static void isPositive(int number) throws InvalidLoginInfo {
        if (number < 0) {
            throw new InvalidLoginInfo("");
        }
    }


    public static void checkOption(String option, ArrayList<Integer> options) throws InvalidOptionException {
        if (!isNumeric(option) || !options.contains(Integer.parseInt(option))) {
            throw new InvalidOptionException("Опція має бути одним із запропонованих чисел."
            + "Буль ласка, спробуйте ще");
        }
    }

    static void checkYesOrNoOptions(String option) throws InvalidOptionException {
        if (!option.equals("так") & !option.equals("ні")) {
            throw new InvalidOptionException("Будь ласка, оберіть так або ні.");
        }
    }


    public static void validateLoginAndPassword(User user, String action) {
        switch (action) {
            case "login":
                if (user == null ) {
                    throw new InvalidLoginInfo("Помилка!Невірно введено логін або пароль." +
                            "Будь ласка, спробуйте ще.");
                }
                if (user.getBlocked()) {
                    throw new BlockedUserException("Помилка!Користувача заблоковано.");
                }
                break;
            case "signup":
                if (user != null ) {
                throw new InvalidLoginInfo("Помилка!Користувач вже існує." +
                        "Бажаєте увійти?.");
            }
        }

    }

}

package br.com.ada.georg.twitter;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Input {

    private static Object get(String expectedInputType) {
        Scanner scanner = new Scanner(System.in);
        Object input;
        try {
            switch (expectedInputType) {
                case "int":
                    return scanner.nextInt();
                case "String":
                    return scanner.nextLine();
                default:
                    return null;
            }
        }
        catch (InputMismatchException e){
            return null;
        }
    }

    private static Object get(String expectedInputType, String prompt) {
        Object input;
        System.out.println(prompt);
        input = get(expectedInputType);
        if (input != null) {
            return input;
        }
        else {
            return get(expectedInputType, prompt);
        }
    }

    public static String getString(String prompt) {
        try {
            return ((String) get("String", prompt)).trim();
        }
        catch (ClassCastException e) {
            return getString(prompt);
        }
    }

    public static int getInt(String prompt, int minValue, int maxValue) {
        int input;
        try {
            input = (int) get("int", prompt);
        }
        catch (ClassCastException e) {
            System.out.println("Invalid input!");
            return getInt(prompt, minValue, maxValue);
        }
        boolean inputInRange = (input >= minValue && input <= maxValue);
        if (inputInRange) {
            return input;
        }
        else {
            return getInt(prompt, minValue, maxValue);
        }
    }

    public static void getUsername() {
        var username = (String) getString("Insert your desired username");
//        TODO add integration with AccountCheck to see
//              if a account with such username exists
//        AccountCheck usernameFree = Account.AccountExists(username, Account.accountList)

    }

    public static String formatFullName(String fullName) {
        var formattedFullName = new String();
        var nameArray = fullName.split(" ");
        for (String name : nameArray) {
            formattedFullName += formatName(name) + " ";
        }
        return formattedFullName.trim();
    }

    public static String formatName(String name) {
        String formattedName = name.substring(0,1).toUpperCase();
        return formattedName + name.substring(1).toLowerCase();
    }
    public static String getFullName() {
        return formatFullName(getString("Insert your full name"));
    }

//    TODO add registerAccount method to receive all input
//        necessary and run Account.registerAccount method


}

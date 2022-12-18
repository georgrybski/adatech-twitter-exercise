package br.com.ada.georg.twitter;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Input {

    private static final String EMAIL_REGEX = "^[\\w-\\+]+(\\.[\\w]+)*@[\\w-]+(\\.[\\w]+)*(\\.[a-z]{2,})$";
    private static final Pattern EMAIL_PATTERN = Pattern.compile(EMAIL_REGEX, Pattern.CASE_INSENSITIVE);

//    private static final String USERNAME_REGEX = Not added for the sake of simplicity during testing;
//    private static final Pattern USERNAME_PATTERN = Pattern.compile(USERNAME_REGEX, Pattern.CASE_INSENSITIVE);

    private Input() {
    }

    public static Account logIn(Account loggedAccount) {
        if (loggedAccount == null) {

            var username = getString("Insert your username");
            var password = getString("Insert your password");
            AccountChecker logInCheck = Account.logIn(username, password);
            if (logInCheck.exists()) {
                Twitter.printFramedMessage("You have successfully logged in as " + logInCheck.getAccount().getUsername());
                return logInCheck.getAccount();
            } else {
                switch (getInt(new String[]{"1 Try Again", "2 Go Back To Menu"}, 1, 2)) {
                    default:
                    case 2:
                        return null;
                    case 1:
                        return logIn(loggedAccount);
                }
            }
        } else {
            Twitter.printFramedMessage("You are already logged in!");
            switch (getInt(new String[]{"1 Go Back To Menu", "2 Log Out"}, 1, 2)) {
                case 1:
                    return loggedAccount;
                case 2:
                    return null;
            }
        }
        return null;
    }

    public static void follow(Account loggedAccount) {
//        if (loggedAccount == null) {
//            Twitter.printFramedMessage("You have to be logged in to follow someone.");
//            return;
//        }
        var usernameToFollow = getString("Insert the username you would like to follow");
        if (usernameToFollow.equalsIgnoreCase(loggedAccount.getUsername())) {
            Twitter.printFramedMessage("You can't follow yourself");
            return;
        }
        if (Account.follow(loggedAccount, usernameToFollow)){
            Twitter.printFramedMessage("You are now following \"" + "@" + usernameToFollow + "\"");
        }
    }

    public static void searchProfile(Account loggedAccount) {
        int found = 0;
        String username = getString("Insert the name/username you would like to search");
        if (username.equalsIgnoreCase("admin")) {
            Twitter.printFramedMessage("No profiles match your search :(");
            return;
        }
        for (int searched = 0; searched < Account.getAccountList().length; searched++) {
            if (Account.getAccountList()[searched] == null) {
                if (found == 0) {
                    Twitter.printFramedMessage("No profiles match your search :(");
                    return;
                } } else {
                    boolean searchStringInUsername = ((Account) Account.getAccountList()[searched]).getHandle().toLowerCase().contains(username);
                    boolean searchStringInName = ((Account) Account.getAccountList()[searched]).getUser().getName().toLowerCase().contains(username);
                    if (searchStringInUsername || searchStringInName) {
                        Twitter.printFormattedProfile((Account) Account.getAccountList()[searched], loggedAccount);
                        found++;
                    }
                }
                if (found > 0 && searched == Account.getAccountList().length -1) {
                    String results = found > 1? " results." : " result";
                    Twitter.printFramedMessage("^ \"" + username + "\" search yielded " + found + results + " ^");
                }
            }
        }



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
        } catch (InputMismatchException e) {
            return null;
        }
    }

    private static Object get(String expectedInputType, String prompt) {
        Object input;
        if (!prompt.isBlank()) {
            Twitter.printFramedMessage(prompt);
        }
        input = get(expectedInputType);
        if (input != null) {
            return input;
        } else {
            return get(expectedInputType, prompt);
        }
    }

    public static String getString(String prompt) {
        try {
            return ((String) get("String", prompt)).trim();
        } catch (ClassCastException e) {
            return getString(prompt);
        }
    }

    public static int getInt(String[] options, int minValue, int maxValue) {
        int input;
        Twitter.printMenuOptions(options);
        String prompt = ("Insert a value between " + minValue + " and " + maxValue);

        try {
            input = (int) get("int", prompt);
        } catch (ClassCastException e) {
            Twitter.printFramedMessage("Invalid input! Insert a value between " + minValue + " and " + maxValue);
            return getInt(options, minValue, maxValue);
        }
        boolean inputInRange = (input >= minValue && input <= maxValue);
        if (inputInRange) {
            return input;
        } else {
            return getInt(options, minValue, maxValue);
        }
    }

    public static String getUsername() {
        var username = (String) getString("Insert your desired username");
        if (!isUsernameValid(username)) {
            Twitter.printFramedMessage("Usernames must have between 4 and 15 characters and " +
                    "contain only letters A-Z, numbers 0-9 or underscores");
            return getUsername();
        }
        AccountChecker usernameFree = AccountChecker.accountExists(username, Account.getAccountList());
        if (usernameFree.exists()) {
            Twitter.printFramedMessage("That username is already taken");
            return getUsername();
        } else {
            return username;
        }
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
        String formattedName = name.substring(0, 1).toUpperCase();
        return formattedName + name.substring(1).toLowerCase();
    }

    public static String getFullName() {
        return formatFullName(getString("Insert your name"));
    }

    public static boolean isUsernameValid(String username) {
        // Check if username is within Twitter's accepted size range.
        boolean usernameLengthValid = (username.length() >= 4 && username.length() <= 15);

        // Regex to check if username contains only Letters A-Z, numbers 9-0 and underscores.
        boolean usernameCharactersValid = (username.matches("^[a-zA-Z0-9_]+$"));
        if (usernameLengthValid && usernameCharactersValid) {
            return true;
        } else {
            return false;
        }
    }

    public static void registerAccount(Account loggedAccount) {
        if (loggedAccount == null || loggedAccount.getUsername().equalsIgnoreCase("admin")) {
            String username = getUsername();
            String password = getString("Insert your desired password");
            String name = getFullName();
            String email = getEmail();
            String birthDate = getBirthDate();


            Account.registerAccount(username, password, name, email, birthDate);
        } else {
            Twitter.printFramedMessage("You have to log out before attempting to create an account");
        }
    }

    private static String getBirthDate() {
        String birthDateInput = getString("Insert a valid birthday in the format dd/mm/yyyy");
        if (Time.isValidDate(birthDateInput)) {
            String formattedBirthDate = Time.convertDateFormat(birthDateInput);
            if (Time.getAge(formattedBirthDate) > 13) {
                return formattedBirthDate;
            } else {
                Twitter.printFramedMessage("You must be at least 13 years old to create an account :(");
                return getBirthDate();
            }
        } else {
            return getBirthDate();
        }
    }

    public static void postTweet(Account loggedAccount) {
        if (loggedAccount == null) {
            System.out.println("You need to be logged in to tweet");
            return;
        }
        var tweet = getValidatedTweetString();
        // TODO format date properly
        Tweet.postTweet(tweet, Time.getCurrentTime(), loggedAccount);
    }

    private static String getValidatedTweetString() {
        String tweetString = getString("Write your tweet in one line (280 character limit)");
        if (isTweetStringValid(tweetString)) {
            return tweetString;
        } else {
            return getValidatedTweetString();
        }
    }

    private static boolean isTweetStringValid(String tweetString) {
        if (tweetString.trim().length() <= 280) {
            return true;
        }
        return false;
    }

    private static boolean isEmailValid(String email) {
        Matcher matcher = EMAIL_PATTERN.matcher(email);
        return matcher.matches();
    }

    private static String getEmail() {
        String email = getString("Insert your email");
        if (isEmailValid(email)) {
            return email;
        }
        Twitter.printFramedMessage("Invalid input! Emails should be inserted in a format such: username@domain.com");
        return getEmail();
    }
}

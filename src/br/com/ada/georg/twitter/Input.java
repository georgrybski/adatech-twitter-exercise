package br.com.ada.georg.twitter;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Input {

    private static final String EMAIL_REGEX = "^[\\w-\\+]+(\\.[\\w]+)*@[\\w-]+(\\.[\\w]+)*(\\.[a-z]{2,})$";
    private static final Pattern EMAIL_PATTERN = Pattern.compile(EMAIL_REGEX, Pattern.CASE_INSENSITIVE);
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
                switch (getInt("Username and/or password incorrect, press 0 to go back to the menu or 1 to try again", 0, 1)) {
                    default:
                    case 0:
                        return null;
                    case 1:
                        return logIn(loggedAccount);
                }
            }
        }
        else {
            Twitter.printFramedMessage("You are already logged in!");
            switch (getInt("Press 1 to log or 0 to go back to the menu", 0,1)) {
                case 0:
                    return loggedAccount;
                case 1:
                    return null;
            }
        }
        return null;
    }

    public static void follow (Account loggedAccount) {
        if (loggedAccount == null) {
            Twitter.printFramedMessage("You have to be logged in to follow someone.");
            return;
        }
        var usernameToFollow = getString("Insert the username you would like to follow");
        if (usernameToFollow.equalsIgnoreCase(loggedAccount.getUsername())){
            Twitter.printFramedMessage("You can't follow yourself");
            return;
        }
        Account.follow(loggedAccount, usernameToFollow);
        Twitter.printFramedMessage("You are now following \"" + usernameToFollow + "\"");
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
        }
        catch (InputMismatchException e){
            return null;
        }
    }

    private static Object get(String expectedInputType, String prompt) {
        Object input;
        if(!prompt.isBlank()){
            Twitter.printFramedMessage(prompt);
        }
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
            Twitter.printFramedMessage("Invalid input!");
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

    public static String getUsername() {
        var username = (String) getString("Insert your desired username");
        if (!isUsernameValid(username)){
            Twitter.printFramedMessage("Usernames must have between 4 and 15 characters and " +
                               "contain only letters A-Z, numbers 0-9 or underscores");
            return getUsername();
        }
        AccountChecker usernameFree = AccountChecker.accountExists(username, Account.getAccountList());
        if (usernameFree.exists()) {
            Twitter.printFramedMessage("That username is already taken");
            return getUsername();
        }
        else {
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
        String formattedName = name.substring(0,1).toUpperCase();
        return formattedName + name.substring(1).toLowerCase();
    }

    public static String getFullName() {
        return formatFullName(getString("Insert the name your name"));
    }

    public static boolean isUsernameValid(String username) {
        // Check if username is within Twitter's accepted size range.
        boolean usernameLengthValid = (username.length() >= 4 && username.length() <= 15);

        // Regex to check if username contains only Letters A-Z, numbers 9-0 and underscores.
        boolean usernameCharactersValid = (username.matches("^[a-zA-Z0-9_]+$"));
        if(usernameLengthValid && usernameCharactersValid) {
            return true;
        }
        else {
            return false;
        }
    }

    public static void registerAccount(Account loggedAccount) {
        if (loggedAccount == null || loggedAccount.getUsername().equalsIgnoreCase("admin")) {
            String username = getUsername();
            String password = getString("Insert your desired password");
            String name = getFullName();
//            String email = getString("Insert your email");
            String email = getEmail();
            String birthDate = getString("Insert your birthdate");

            Account.registerAccount(username, password, name, email, birthDate, "15/12/2022");
        }
        else {
            System.out.println("You have to log out before attempting to create an account");
        }
    }

    public static void postTweet(Account loggedAccount) {
        if (loggedAccount == null) {
            System.out.println("You need to be logged in to tweet");
            return;
        }
        var tweet = getValidatedTweetString();
        // TODO format date properly
        var postDate = getString("Insert your local time");
        Tweet.postTweet(tweet, postDate, loggedAccount);
    }

    private static String getValidatedTweetString() {
        String tweetString = getString("Write your tweet in one line (280 character limit)");
        if (isTweetStringValid(tweetString)) {
            return tweetString;
        }
        else {
            return getValidatedTweetString();
        }
    }

    private static boolean isTweetStringValid(String tweetString) {
        if (tweetString.trim().length() <= 280){
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
        return getEmail();
    }

    // TODO
    public static void printTweetsByAccount(Account account) {

    }

}

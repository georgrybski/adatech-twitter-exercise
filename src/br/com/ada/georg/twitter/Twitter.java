package br.com.ada.georg.twitter;
public class Twitter {

    private static final String[] GUEST_MENU_OPTIONS = {"1 Log In", "2 Create Account", "3 Exit"};

    private static final String[] ADMIN_MENU_OPTIONS = {"1 Create Account", "2 View All Accounts"," 3 View All Tweets", "4 Log out", "5 Exit"};

    private static final String[] USER_MENU_OPTIONS = {"1 Tweet", "2 View Timeline", "3 View My Tweets", "4 View My Profile", "5 Follow", "6 View My Followers", "7 View Who I Follow", "8 Log Out", "9 Exit"};



    private Twitter() {
    }

    private static void printFormattedTweet(Tweet tweet) {
        int lineLength = 70;
        printFrameLine(lineLength, "+", "-");
        printMultipurposeLine(returnTweetInfo(tweet), lineLength);
        printFrameLine(lineLength, "|"," ");
        printFormattedTweetString(tweet, lineLength);
        printFrameLine(lineLength, "|"," ");
        printMultipurposeLine(returnTweetMetrics(tweet), lineLength);
        printFrameLine(lineLength, "+", "-");
    }



    private static void printFormattedProfile(Account account, Account loggedAccount) {
        int lineLength = 70;
        printFrameLine(lineLength, "+", "-");
        printMultipurposeLine(account.getUser().getName(), lineLength);
        printMultipurposeLine(account.getHandle(), lineLength);
        printFrameLine(lineLength, "|"," ");
        printMultipurposeLine("Joined " + account.getCreationDate(), lineLength);
        printFrameLine(lineLength, "|"," ");
        printMultipurposeLine(returnFollowerInfo(account), lineLength);
        if (loggedAccount.getUsername().equalsIgnoreCase("admin")) {
            printFrameLine(lineLength, "|"," ");
            printMultipurposeLine(returnEmailInfo(account),lineLength);
            printMultipurposeLine(returnBirthDateInfo(account),lineLength);
        }
        printFrameLine(lineLength, "+", "-");
    }

    private static String returnEmailInfo(Account account) {
        return "Email: " + account.getUser().getEmail();
    }

    private static String returnBirthDateInfo(Account account) {
        return "Date of birth: " + account.getUser().getBirthDate();
    }

    private static String returnFollowerInfo(Account account) {
        return "Following " + account.getFollowCount() +
                " ".repeat(3) + account.getFollowerCount() + " Followers";
    }

    public static void printAllProfiles(Account loggedAccount) {
        for (Object account:Account.getAccountList()) {
            if (account == null) {
                return;
            }
            if (!((Account)account).getUsername().equalsIgnoreCase("admin")) {
                printFormattedProfile((Account) account, loggedAccount);
            }
        }
    }

    public static void printFramedMessage(String message) {
        int lineLength = 82;
        printFrameLine(lineLength, "+", "-");
        printIndentedFramedLine(message, lineLength);
        printFrameLine(lineLength, "+", "-");
    }

    //TODO
    public static void printWelcomeMessage() {
        printFramedMessage("Welcome to Ada Tech's Twitter by Georg Rybski!");

    }

    public static void printGoodbyeMessage() {
        printFramedMessage("Thanks for using Ada Tech's Twitter by Georg Rybski!");
    }

    public static void printLoggedInStatus(Account loggedAccount) {
        if (loggedAccount == null) {
            printFramedMessage("You are not logged in yet!");
        }
        else {
            printFramedMessage("You are logged in as: \"" + loggedAccount.getHandle() + "\"");
        }
    }

    private static void printIndentedFramedLine(String string, int lineLength) {
        int repeat = (lineLength - string.length())/2;
        String line = "|" + " ".repeat(repeat)+ string + " ".repeat(repeat) + "|";
        System.out.println(line);
    }

    public static void printMenuOptions(String[] options) {
        int lineLength = 82;
        var optionsMatrix = convertOptionsArrayToMatrix(options);
        printFrameLine(lineLength, "+", "-");
        String innerButtonsTopLine = "", innerButtonsMiddleLine = "";
        for (int i = 0, optionsAdded = 0; i < optionsMatrix.length ; i++) {
            boolean optionFitsInLine = (optionsMatrix[i][1].length() + innerButtonsTopLine.length() < lineLength - 4);
            if (optionFitsInLine) {
                innerButtonsTopLine += optionsMatrix[i][1] + " ".repeat(2);
                innerButtonsMiddleLine += optionsMatrix[i][0] + " ".repeat(2);
            }
            if (!optionFitsInLine || i == optionsMatrix.length -1){
                String odEvenOffset = " ".repeat((lineLength - innerButtonsMiddleLine.trim().length()) % 2);
                String spaces = " ".repeat((lineLength - innerButtonsMiddleLine.trim().length()) /2);
                System.out.println("|" + spaces + innerButtonsTopLine.trim() + spaces + odEvenOffset + "|");
                System.out.println("|" + spaces + innerButtonsMiddleLine.trim() + spaces + odEvenOffset + "|");
                System.out.println("|" + spaces + innerButtonsTopLine.trim() + spaces + odEvenOffset + "|");
                innerButtonsTopLine = "" + optionsMatrix[i][1] + " ".repeat(2);
                innerButtonsMiddleLine = "" + optionsMatrix[i][0] + " ".repeat(2);
                if (i == optionsMatrix.length -1) {
                    printFrameLine(lineLength, "+", "-");
                    return;
                }
            }
        }
        printFrameLine(lineLength, "+", "-");
    }

    public static void printGuestOptions() {
        printMenuOptions(GUEST_MENU_OPTIONS);
    }

    public static void printAdminOptions() {
        printMenuOptions(ADMIN_MENU_OPTIONS);
    }

    public static void printUserOptions() {
        printMenuOptions(USER_MENU_OPTIONS);
    }

    private static String[][] convertOptionsArrayToMatrix(String[] options) {
        String[][] optionsMatrix = new String[options.length][2];
        for (int i = 0; i < options.length ; i++) {
            optionsMatrix[i][0] = "| " + options[i] + " |";
            optionsMatrix[i][1] = "+" + "-".repeat(options[i].length()+2) + "+";
        }
        return optionsMatrix;
    }

    //TODO
    public static void printFormattedLogInCreateAccountMenu() {

    }

    //TODO
    public static void printFormattedAdminMenu() {

    }

    private static String returnTweetInfo(Tweet tweet) {
       return tweet.getAuthor().getUser().getName() + "  " +
                tweet.getAuthor().getHandle() + " \u2022 " +
                tweet.getPostDate();
    }

    private static String returnTweetMetrics (Tweet tweet) {
        return "Comments: " + tweet.getCommentCount() +
                " ".repeat(3) + "Likes: " + tweet.getLikeCount();
    }

    private static void printMultipurposeLine(String line, int lineLength) {
        var tweetLine = "|  ";

        tweetLine += line;

        tweetLine += " ".repeat(lineLength - tweetLine.length() -1 ) + "  |";

        System.out.println(tweetLine);
    }

    private static void printFormattedTweetString(Tweet tweet, int lineLength) {
        String tweetStringArray[] = tweet.getTweet().trim().split(" "), word;
        var tweetLine ="|  ";
        for (int i = 0; i < tweetStringArray.length; i++) {
            word = tweetStringArray[i];
            boolean wordFitsOnLine = (tweetLine.length() + word.length() +3) < lineLength;
            if (wordFitsOnLine) {
                tweetLine = (i!=0)? tweetLine + " " + word : tweetLine + word;
            }
            else {
                tweetLine += " ".repeat(lineLength - tweetLine.length()+1) + "|";
                System.out.println(tweetLine);
                tweetLine = "|  " + word;
            }
            if(i == tweetStringArray.length -1){
                tweetLine += " ".repeat(lineLength- tweetLine.length()+1) + "|";
                System.out.println(tweetLine);
                return;
            }
        }
        tweetLine += " ".repeat(lineLength - tweetLine.length()+1);
        System.out.println(tweetLine);
    }

    // Experimental
    private static void printTweetFrame(int length, int height) {
        printFrameLine(length, "+", "-");
        for (int i = 0; i < height-2 ; i++) {
            printFrameLine(length, "|", " ");
        }
        printFrameLine(length, "+", "-");
    }

    private static void printFrameLine(int length, String delimiter, String filler) {
        System.out.println(delimiter + filler.repeat(length) + delimiter);
    }

    private static void printTweetsInList(Object[] tweetList) {
        for (Object tweet : tweetList) {
            if (tweet == null) {
                return;
            }
            else {
                printFormattedTweet((Tweet) tweet);
            }
        }
    }

    public static void printTweetsByAccount(Account loggedAccount) {
        if(loggedAccount == null) {
            System.out.println("You need to be logged in to check tweets");
        }

        for (Object tweet: loggedAccount.getTweets()) {
            if(tweet == null) {
                return;
            }
            Twitter.printFormattedTweet((Tweet) tweet);
        }
    }

    public static void printAllTweets() {
        for (Object tweet: Tweet.getTweetList()) {
            if(tweet == null) {
                return;
            }
            Twitter.printFormattedTweet((Tweet) tweet);
        }
    }
//    private static void pr
    private static void viewProfile(Account account) {

    }

    private static void viewTimelines() {

    }

    private static void viewFollowers() {

    }

    private static void viewFollowed() {

    }


}

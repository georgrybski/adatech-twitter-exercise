package br.com.ada.georg.twitter;

public class Twitter {

    private static final String[] GUEST_MENU_OPTIONS = {"1 Log In", "2 Create Account", "3 Exit"};
    private static final int GUEST_MENU_MIN_INPUT = 1;
    private static final int GUEST_MENU_MAX_INPUT = 3;

    private static final String[] ADMIN_MENU_OPTIONS = {"1 Create Account", "2 View All Accounts", " 3 View All Tweets", "4 Log out", "5 Exit"};
    private static final int ADMIN_MENU_MIN_INPUT = 1;
    private static final int ADMIN_MENU_MAX_INPUT = 5;

    private static final String[] USER_MENU_OPTIONS = {"1 Tweet", "2 View Timeline", "3 View My Profile", "4 Search Profile", "5 Follow", "6 View My Followers", "7 View Accounts I Follow", "8 Log Out", "9 Exit"};
    private static final int USER_MENU_MIN_INPUT = 1;
    private static final int USER_MENU_MAX_INPUT = 9;


    private Twitter() {
    }

    private static int getMenuInput(String[] menuOptions, int minInput, int maxInput) {
        return Input.getInt(menuOptions, minInput, maxInput);
    }

    public static int getGuestMenuInput() {
        return getMenuInput(GUEST_MENU_OPTIONS, GUEST_MENU_MIN_INPUT, GUEST_MENU_MAX_INPUT);
    }

    public static int getUserMenuInput() {
        return getMenuInput(USER_MENU_OPTIONS, USER_MENU_MIN_INPUT, USER_MENU_MAX_INPUT);
    }

    public static int getAdminMenuInput() {
        return getMenuInput(ADMIN_MENU_OPTIONS, ADMIN_MENU_MIN_INPUT, ADMIN_MENU_MAX_INPUT);
    }

    //TODO: refactor method
    private static void printFormattedTweet(Tweet tweet) {
        int lineLength = 70;
        System.out.print(" ".repeat(6));
        printFrameLine(lineLength, "+", "-");
        System.out.print(" ".repeat(6));
        printMultipurposeLine(returnTweetInfo(tweet), lineLength);
        System.out.print(" ".repeat(6));
        printFrameLine(lineLength, "|", " ");
        printFormattedTweetString(tweet, lineLength);
        System.out.print(" ".repeat(6));
        printFrameLine(lineLength, "|", " ");
        System.out.print(" ".repeat(6));
        printMultipurposeLine(returnTweetMetrics(tweet), lineLength);
        System.out.print(" ".repeat(6));
        printFrameLine(lineLength, "+", "-");
    }

    //TODO: refactor method
    public static void printFormattedProfile(Account account, Account loggedAccount) {

        // Check if account being printed is admin
        if (account.getUsername().equalsIgnoreCase("admin")) {
            return;
        }

        // Print info available to all users
        int lineLength = 70;
        System.out.print(" ".repeat(6));
        printFrameLine(lineLength, "+", "-");
        System.out.print(" ".repeat(6));
        printMultipurposeLine(account.getUser().getName(), lineLength);
        System.out.print(" ".repeat(6));
        printMultipurposeLine(account.getHandle(), lineLength);
        System.out.print(" ".repeat(6));
        printFrameLine(lineLength, "|", " ");
        System.out.print(" ".repeat(6));
        printMultipurposeLine("Joined " + account.getCreationDate(), lineLength);
        System.out.print(" ".repeat(6));
        printFrameLine(lineLength, "|", " ");
        System.out.print(" ".repeat(6));
        printMultipurposeLine(returnFollowerInfo(account), lineLength);

        // Add restricted info if user is logged as admin:
        if (loggedAccount.getUsername().equalsIgnoreCase("admin")) {
            System.out.print(" ".repeat(6));
            printFrameLine(lineLength, "|", " ");
            System.out.print(" ".repeat(6));
            printMultipurposeLine(returnEmailInfo(account), lineLength);
            System.out.print(" ".repeat(6));
            printMultipurposeLine(returnAgeInfo(account), lineLength);
            System.out.print(" ".repeat(6));
            printMultipurposeLine(returnBirthDateInfo(account), lineLength);
        }

        // Print the bottom of the box
        System.out.print(" ".repeat(6));
        printFrameLine(lineLength, "+", "-");
    }

    private static String returnAgeInfo(Account account) {
        return "Age: " + Time.getAge(account.getUser().getBirthDate()) + " years old";
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
        for (Object account : Account.getAccountList()) {

            if (Account.getAccountList()[1] == null) {
                printFramedMessage("No accounts registered yet :(");
                return;
            }
            if (account == null) {
                printFramedMessage("^ Scroll up to view profiles ^");
                return;
            }
            if (!((Account) account).getUsername().equalsIgnoreCase("admin")) {
                printFormattedProfile((Account) account, loggedAccount);
            }
            System.out.println();
        }
    }

    public static void printFramedMessage(String message) {
        int lineLength = 82;
        printFrameLine(lineLength, "+", "-");
        if (message.length() > lineLength - 6) {
            printFormattedMultiLineString(message, lineLength);
        } else {
            printIndentedFramedLine(message, lineLength);
        }
        printFrameLine(lineLength, "+", "-");
    }

    //TODO
    public static void printWelcomeMessage() {
        printFramedMessage("Welcome to Ada Tech's Twitter by Georg Rybski!");
        //TODO: add twitter logo printer
    }

    public static void printGoodbyeMessage() {
        printFramedMessage("Thanks for using Ada Tech's Twitter by Georg Rybski!");
        //TODO: add twitter logo printer
    }

    public static void printLoggedInStatus(Account loggedAccount) {
        if (loggedAccount == null) {
            printFramedMessage("You are not logged in yet.");
        } else {
            printFramedMessage("You are logged in as: \"" + loggedAccount.getHandle() + "\"");
        }
    }

    private static void printIndentedFramedLine(String string, int lineLength) {
        int repeat = (lineLength - string.length()) / 2;
        int oddOrEvenOffset = (lineLength - string.length()) % 2;
        String line = "|" + " ".repeat(repeat) + string + " ".repeat(repeat + oddOrEvenOffset) + "|";
        System.out.println(line);
    }


    //TODO: refactor method
    public static void printMenuOptions(String[] options) {
        int lineLength = 82;
        var optionsMatrix = convertOptionsArrayToMatrix(options);
        printFrameLine(lineLength, "+", "-");
        String innerButtonsTopLine = "", innerButtonsMiddleLine = "";
        for (int i = 0, optionsAdded = 0; i < optionsMatrix.length; i++) {
            boolean optionFitsInLine = (optionsMatrix[i][1].length() + innerButtonsTopLine.length() < lineLength - 4);
            if (optionFitsInLine) {
                innerButtonsTopLine += optionsMatrix[i][1] + " ".repeat(2);
                innerButtonsMiddleLine += optionsMatrix[i][0] + " ".repeat(2);
                optionsAdded++;
            }
            if (!optionFitsInLine || i == optionsMatrix.length - 1) {
                String oddEvenOffset = " ".repeat((lineLength - innerButtonsMiddleLine.trim().length()) % 2);
                String spaces = " ".repeat((lineLength - innerButtonsMiddleLine.trim().length()) / 2);
                System.out.println("|" + spaces + innerButtonsTopLine.trim() + spaces + oddEvenOffset + "|");
                System.out.println("|" + spaces + innerButtonsMiddleLine.trim() + spaces + oddEvenOffset + "|");
                System.out.println("|" + spaces + innerButtonsTopLine.trim() + spaces + oddEvenOffset + "|");
                innerButtonsTopLine = "" + optionsMatrix[i][1] + " ".repeat(2);
                innerButtonsMiddleLine = "" + optionsMatrix[i][0] + " ".repeat(2);

                if (i == optionsMatrix.length - 1 && optionsAdded != i && i % 2 != 0 && optionsMatrix.length > 2) {
                    oddEvenOffset = " ".repeat((lineLength - innerButtonsMiddleLine.trim().length()) % 2);
                    spaces = " ".repeat((lineLength - innerButtonsMiddleLine.trim().length()) / 2);
                    System.out.println("|" + spaces + innerButtonsTopLine.trim() + spaces + oddEvenOffset + "|");
                    System.out.println("|" + spaces + innerButtonsMiddleLine.trim() + spaces + oddEvenOffset + "|");
                    System.out.println("|" + spaces + innerButtonsTopLine.trim() + spaces + oddEvenOffset + "|");
                }
            }
        }
        printFrameLine(lineLength, "+", "-");
    }

    private static String[][] convertOptionsArrayToMatrix(String[] options) {
        String[][] optionsMatrix = new String[options.length][2];
        for (int i = 0; i < options.length; i++) {
            optionsMatrix[i][0] = "| " + options[i] + " |";
            optionsMatrix[i][1] = "+" + "-".repeat(options[i].length() + 2) + "+";
        }
        return optionsMatrix;
    }

    private static String returnTweetInfo(Tweet tweet) {
        return tweet.getAuthor().getUser().getName() + "  " +
                tweet.getAuthor().getHandle() + " \u2022 " +
                tweet.getPostDate();
    }

    private static String returnTweetMetrics(Tweet tweet) {
        return "Comments: " + tweet.getCommentCount() +
                " ".repeat(3) + "Likes: " + tweet.getLikeCount();
    }

    private static void printMultipurposeLine(String line, int lineLength) {
        var tweetLine = "|  ";

        tweetLine += line;

        tweetLine += " ".repeat(lineLength - tweetLine.length() - 1) + "  |";

        System.out.println(tweetLine);
    }

    private static void printFormattedTweetString(Tweet tweet, int lineLength) {
        String tweetStringArray[] = tweet.getTweet().trim().split(" "), word;
        var tweetLine = "|  ";
        for (int i = 0; i < tweetStringArray.length; i++) {
            word = tweetStringArray[i];
            boolean wordFitsOnLine = (tweetLine.length() + word.length() + 3) < lineLength;
            if (wordFitsOnLine) {
                tweetLine = (i != 0) ? tweetLine + " " + word : tweetLine + word;
            } else {
                tweetLine += " ".repeat(lineLength - tweetLine.length() + 1) + "|";
                System.out.print(" ".repeat(6));
                System.out.println(tweetLine);
                tweetLine = "|  " + word;
            }
            if (i == tweetStringArray.length - 1) {
                tweetLine += " ".repeat(lineLength - tweetLine.length() + 1) + "|";
                System.out.print(" ".repeat(6));
                System.out.println(tweetLine);
                return;
            }
        }
        System.out.print(" ".repeat(6));
        tweetLine += " ".repeat(lineLength - tweetLine.length() + 1);
        System.out.println(tweetLine);
    }

    private static void printFormattedMultiLineString(String string, int lineLength) {
        String stringArray[] = string.trim().split(" "), currentLine = "";
        for (int i = 0; i < stringArray.length; i++) {
            if (stringArray[i].length() + currentLine.length() + 1 < lineLength - 6) {
                currentLine += stringArray[i] + " ";
            } else {
                printIndentedFramedLine(currentLine, lineLength);
                currentLine = stringArray[i];
                if (i == stringArray.length - 1) {
                    printIndentedFramedLine(currentLine, lineLength);
                    return;
                }
            }
            if (i == stringArray.length - 1) {
                printIndentedFramedLine(currentLine, lineLength);
                return;
            }
        }

    }

    private static void printFrameLine(int length, String delimiter, String filler) {
        System.out.println(delimiter + filler.repeat(length) + delimiter);
    }

    public static void printAllTweets() {
        printAllTweetsInList(Tweet.getTweetList());
    }

    public static void printAllTweetsInList(Object[] tweets) {
        for (Object tweet : tweets) {
            if (tweet == null) {
                return;
            }
            Twitter.printFormattedTweet((Tweet) tweet);
            System.out.println();
        }
    }

    public static void viewTimelines(Account loggedAccount) {
        switch (Input.getInt(new String[]{"1 View All Tweets", "2 View My Tweets", "3 View Tweets From Followers", "4 View Tweets From Followed", "5 Go Back To Menu"}, 1, 5)) {

            case 1:
                if (Tweet.getTweetList()[0] == null){
                    printFramedMessage("No Tweets have been posted yet :(");
                    break;
                }
                Twitter.printAllTweets();
                break;

            case 2:
                if (loggedAccount.getTweetCount() == 0) {
                    printFramedMessage("You haven't posted any Tweet yet");
                    break;
                }
                Twitter.printAllTweetsInList(loggedAccount.getTweets());
                break;

            case 3:
                if (loggedAccount.getFollowerCount() == 0) {
                    printFramedMessage("Sorry, you have no followers yet :(");
                    break;
                }
                for (Object follower : loggedAccount.getFollowerList()) {
                    if (follower != null) {
                        Twitter.printAllTweetsInList(((Account) follower).getTweets());
                    } else {
                        return;
                    }
                }
                break;

            case 4:
                if (loggedAccount.getFollowCount() == 0) {
                    printFramedMessage("You haven't followed anyone yet");
                    break;
                }
                for (Object followed : loggedAccount.getFollowedList()) {
                    if (followed != null) {
                        Twitter.printAllTweetsInList(((Account) followed).getTweets());
                    } else {
                        return;
                    }
                }
                break;
            case 5:
                break;
        }
    }


    public static void viewFollowed(Account loggedAccount) {
        if(loggedAccount.getFollowCount() == 0) {
            printFramedMessage("You are not following anyone at the moment :(");
            return;
        }
        Account.printAllAccounts(loggedAccount, loggedAccount.getFollowedList());
    }

    public static void viewFollowers(Account loggedAccount) {
        if (loggedAccount.getFollowerCount() == 0) {
            printFramedMessage("Sorry, You have no followers at the moment :(");
        }
        Account.printAllAccounts(loggedAccount, loggedAccount.getFollowerList());
    }


}

package br.com.ada.georg.twitter;
public class Twitter {

    private static void printFormattedTweet(Tweet tweet) {
        int lineLength = 70;
        printFrameLine(lineLength, "+", "-");
        printMultipurposeTweetLine(returnTweetInfo(tweet), lineLength);
        printFrameLine(lineLength, "|"," ");
        printFormattedTweetString(tweet, lineLength);
        printFrameLine(lineLength, "|"," ");
        printMultipurposeTweetLine(returnTweetMetrics(tweet), lineLength);
        printFrameLine(lineLength, "+", "-");
    }

    //TODO
    private static void printFormattedProfile(Account account, Account loggedAccount) {
        int lineLength = 70;
        printFrameLine(lineLength, "+", "-");
        printMultipurposeTweetLine(account.getUser().getName(), lineLength);
        printMultipurposeTweetLine(account.getHandle(), lineLength);
        printFrameLine(lineLength, "|"," ");
        printMultipurposeTweetLine("Joined " + account.getCreationDate(), lineLength);
        printFrameLine(lineLength, "|"," ");
        printMultipurposeTweetLine(returnFollowerInfo(account), lineLength);
        if (loggedAccount.getUsername().equalsIgnoreCase("admin")) {

        }
        printFrameLine(lineLength, "+", "-");
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


    //TODO
    public static void printWelcomeMenu() {

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

    private static void printMultipurposeTweetLine(String line, int lineLength) {
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

package br.com.ada.georg.twitter;
public class Twitter {

    //TODO
    private static void printFormattedTweet(Tweet tweet, int lineLength) {
        printFrameLine(lineLength, "+", "-");
        printFormattedTweetInfo(tweet, lineLength);
        printFrameLine(lineLength, "|"," ");
        printFormattedTweetString(tweet, lineLength);
        printFrameLine(lineLength, "+", "-");

    }

    private static void printFormattedTweetInfo(Tweet tweet, int lineLength) {
        var tweetInfoLine = "|  ";

        tweetInfoLine += tweet.getAuthor().getUser().getName() + "  " +
                         tweet.getAuthor().getHandle() + " \u2022 " +
                         tweet.getPostDate();

        tweetInfoLine += " ".repeat(lineLength - tweetInfoLine.length() -1 ) + "  |";

        System.out.println(tweetInfoLine);
    }

    private static void printFormattedTweetString(Tweet tweet, int lineLength) {
        String[] tweetStringArray = tweet.getTweet().trim().split(" ");
        var tweetLine ="|  ";
        for (String word: tweetStringArray) {
            boolean wordFitsOnLine = (tweetLine.length() + word.length()) < lineLength;
            if (wordFitsOnLine) {
                tweetLine += " " + word;
            }
            else {
                if(tweetLine.length() != lineLength) {
                    tweetLine += " ".repeat(lineLength - tweetLine.length() -1);
                }
                tweetLine += "  |";
                System.out.println(tweetLine);
                tweetLine ="|  ";
            }
        }
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
                printFormattedTweet((Tweet) tweet,70);
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
//            System.out.println(((Tweet) tweet).toString());
            Twitter.printFormattedTweet((Tweet) tweet, 70);
        }
    }

    public static void printAllTweets() {
        for (Object tweet: Tweet.getTweetList()) {
            if(tweet == null) {
                return;
            }
            System.out.println(((Tweet)tweet).toString());
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

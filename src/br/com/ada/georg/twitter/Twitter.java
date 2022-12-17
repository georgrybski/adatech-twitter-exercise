package br.com.ada.georg.twitter;

public class Twitter {

    //TODO
    private static void printFormattedTweet(Tweet tweet) {

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
        System.out.println(delimiter + filler.repeat(length-2) + delimiter);
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
            System.out.println(((Tweet) tweet).toString());
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

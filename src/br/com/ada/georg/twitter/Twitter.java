package br.com.ada.georg.twitter;

public class Twitter {

    private static void printFormattedTweet(Tweet tweet) {

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

//    private static void pr
    private static void viewProfile() {

    }

    private static void viewTimelines() {

    }


}

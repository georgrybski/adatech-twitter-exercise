package br.com.ada.georg.twitter;
public class Twitter {
    
    private static void printFormattedTweet(Tweet tweet, int lineLength) {
        printFrameLine(lineLength, "+", "-");
        printFormattedTweetInfo(tweet, lineLength);
        printFrameLine(lineLength, "|"," ");
        printFormattedTweetString(tweet, lineLength);
        printFrameLine(lineLength, "|"," ");
        printFormattedTweetMetrics(tweet, lineLength);
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

    private static void printFormattedTweetMetrics(Tweet tweet, int lineLength) {
        var tweetMetricsLine = "|  ";

        tweetMetricsLine += "Comments: " + tweet.getCommentCount() + " ".repeat(3) + "Likes: " + tweet.getLikeCount();

        tweetMetricsLine += " ".repeat(lineLength - tweetMetricsLine.length() -1 ) + "  |";

        System.out.println(tweetMetricsLine);

    }

    private static void printFormattedTweetString(Tweet tweet, int lineLength) {
        String tweetStringArray[] = tweet.getTweet().trim().split(" "), word;
        var tweetLine ="|  ";
        for (int i = 0; i < tweetStringArray.length; i++) {
            word = tweetStringArray[i];
            boolean wordFitsOnLine = (tweetLine.length() + word.length() +3) < lineLength;
            if (wordFitsOnLine) {
                tweetLine += " " + word;
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
                printFormattedTweet((Tweet) tweet,80);
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

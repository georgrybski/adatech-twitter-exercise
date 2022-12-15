package br.com.ada.georg.twitter;

public class Tweet {
    private static Object[] tweetList = new Object[1];
    private static int tweetCount = 0;

    private String tweet;
//    TODO add proper date structure
    private String postDate;
    private Account author;

    private Object[] comments = new Object[1];
    private int commentCount = 0;

    private Object[] likes = new Object[1];
    private int likeCount;

    public static Object[] getTweetList() {
        return tweetList;
    }

    //    TODO
    public static void displayTweets(Object[] tweets) {


    }

    //    TODO
    public static void displayTweet(Tweet tweet) {

    }

    public static int getTweetCount() {
        return tweetCount;
    }

    public String getTweet() {
        return tweet;
    }

    public String getPostDate() {
        return postDate;
    }

    public Account getAuthor() {
        return author;
    }

    public Object[] getComments() {
        return comments;
    }

    public int getCommentCount() {
        return commentCount;
    }

    public Object[] getLikes() {
        return likes;
    }

    public int getLikeCount() {
        return likeCount;
    }
}

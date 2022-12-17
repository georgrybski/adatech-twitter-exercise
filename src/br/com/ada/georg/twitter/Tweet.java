package br.com.ada.georg.twitter;

import java.util.Arrays;

public class Tweet {
    private static Object[] tweetList = new Object[1];
    private static int tweetCount = 0;

    private String tweet;

    private Tweet(String tweet, String postDate, Account author) {
        this.tweet = tweet;
        this.postDate = postDate;
        this.author = author;
    }

    public static void postTweet(String tweet, String postDate, Account author) {
//        var isTweetListFull = tweetCount == tweetList.length-1;
//        if(isTweetListFull) {
//            tweetList = ArrayTools.returnExpandedArray(tweetList);
//        }

        Tweet postedTweet = new Tweet(tweet, postDate, author);

        tweetList = ArrayTools.expandArrayIfNecessary(tweetList, tweetCount);
        tweetList[tweetCount++] = postedTweet;

        Account.postTweetInAccountTweetList(author, postedTweet);
    }

    @Override
    public String toString() {
        return "Tweet{" +
                "tweet='" + tweet + '\'' +
                ", postDate='" + postDate + '\'' +
                ", name=" + author.getHandle() + "\'" +
                ", author=" + author.getUsername() + "\'" +
                ", comments=" + Arrays.toString(comments) +
                ", commentCount=" + commentCount +
                ", likes=" + Arrays.toString(likes) +
                ", likeCount=" + likeCount +
                '}';
    }

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

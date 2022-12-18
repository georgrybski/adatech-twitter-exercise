package br.com.ada.georg.twitter;

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
        Tweet postedTweet = new Tweet(tweet, postDate, author);

        tweetList = ArrayTools.expandArrayIfNecessary(tweetList, tweetCount);
        tweetList[tweetCount++] = postedTweet;

        Account.postTweetInAccountTweetList(author, postedTweet);
    }

    private String postDate;
    private Account author;

    private Object[] comments = new Object[1];
    private int commentCount = 0;


    //    TODO: Implement likes

    private Object[] likes = new Object[1];
    private int likeCount;



    public static Object[] getTweetList() {
        return tweetList;
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

    public int getCommentCount() {
        return commentCount;
    }

    public int getLikeCount() {
        return likeCount;
    }
}

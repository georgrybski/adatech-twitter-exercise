package br.com.ada.georg.twitter;

public class Account {
    private static Object[] accountList = new Object[1];
    private static int accountCount = 0;

    private String username;
    private String password;
    private User user;
    private String creationDate;

    private Object[] tweets = new Object[1];
    private int tweetCount = 0;

    private Object[] followedList = new Object[1];
    private int followCount = 0;

    private Object[] followerList = new Object[1];
    private int followerCount =0;

    private Account(String username, String password, String creationDate ,User user) {
        this.username = username;
        this.password = password;
        this.user = user;
        this.creationDate = creationDate;
    }

    public static void registerAccount(String username, String password, String name, String email, String birthDate) {
        var isListFull = accountCount == accountList.length-1;
        if(isListFull) {
            accountList = ArrayTools.returnExpandedArray(accountList);
        }
        accountList[accountCount++] = new Account(username, password, Time.getCurrentDate(),  new User(name, email, birthDate));
    }

    public static void printAllAccounts(Account loggedAccount, Object[] list) {
        if(loggedAccount == null){
            return;
        }
        for (Object account : list) {
            if (account != null) {
                Twitter.printFormattedProfile(((Account)account), loggedAccount);
            }
        }
    }

    public static boolean follow(Account follower, String  followedUsername) {

        // Check if an account with followedUsername exists
        AccountChecker followedAccountCheck;
        followedAccountCheck = AccountChecker.accountExists(followedUsername, Account.accountList);
        if(!followedAccountCheck.exists()){
            Twitter.printFramedMessage("There is no account with the username \"" + "@" + followedUsername + "\"");
            return false;
        }

        // Check if follower is already following the account that has followedUsername
        AccountChecker followedInFollowList = AccountChecker.accountExists(followedUsername, follower.followedList);
        if (followedInFollowList.exists()){
            Twitter.printFramedMessage("You are already following \"" + "@" + followedUsername + "\"");
            return false;
        }

        //Check if the involved arrays are full, and expand them if necessary
        var isFollowedListFull = followedAccountCheck.getAccount().followerCount == followedAccountCheck.getAccount().followerList.length-1;
        var isFollowerListFull = follower.followCount == follower.followedList.length -1;

        // Expand follower account's followed list
        if (isFollowerListFull) {
            follower.followedList = ArrayTools.returnExpandedArray(follower.followedList);
        }

        // Expand followed  account's follower list
        if (isFollowedListFull) {
            followedAccountCheck.getAccount().followerList = ArrayTools.returnExpandedArray(followedAccountCheck.getAccount().followerList);
        }

        // Add followed to follower's followed list
        follower.followedList[follower.followCount++] = followedAccountCheck.getAccount();

        // Add follower to followed's follower list
        followedAccountCheck.getAccount().followerList[followedAccountCheck.getAccount().followerCount++] = follower;
        return true;
    }

    public static AccountChecker logIn (String username, String password){
        var accountCheck = AccountChecker.accountExists(username, Account.accountList);
        if(accountCheck.exists()) {
            if (accountCheck.getAccount().password.equals(password)){
                return new AccountChecker(accountCheck.getAccount(), true);
            }
        }
        return new AccountChecker(null, false);
    }

    public static Account logOut(Account loggedAccount) {
        if (loggedAccount != null) {
            Twitter.printFramedMessage("You have successfully logged out");
        }
        return null;
    }

    public static void postTweetInAccountTweetList(Account author, Tweet tweet) {
        author.tweets = ArrayTools.expandArrayIfNecessary(author.tweets, author.tweetCount);
        author.tweets[author.tweetCount++] = tweet;
    }

    public static Object[] getAccountList() {
        return accountList;
    }

    public String getUsername() {
        return username;
    }

    public String getHandle() {
        return "@" + username;
    }

    public User getUser() {
        return user;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public Object[] getTweets() {
        return tweets;
    }

    public int getTweetCount() {
        return tweetCount;
    }

    public Object[] getFollowedList() {
        return followedList;
    }

    public int getFollowerCount() {
        return followerCount;
    }

    public Object[] getFollowerList() {
        return followerList;
    }

    public int getFollowCount() {
        return followCount;
    }
}

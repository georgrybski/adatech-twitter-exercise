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

    public static void registerAccount(String username, String password, String name, String email, String birthDate, String currentDate) {
        var isListFull = accountCount == accountList.length-1;
        if(isListFull) {
            accountList = ArrayTools.returnExpandedArray(accountList);
        }
        accountList[accountCount++] = new Account(username, password, currentDate,  new User(name, email, birthDate));
    }

    @Override
    public String toString() {
        return "Account{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                user.toString() +
                ", creationDate='" + creationDate + '\'' +
                ", tweetCount=" + tweetCount +
                ", followerCount=" + followerCount +
                ", followCount=" + followCount +
                '}';
    }

    public static void printAllAccounts(Object[] list) {
        for (Object account : list) {
            if (account != null) {
                System.out.println(((Account) account).toString());
            }
        }
    }

    public static void follow(Account follower, Account followed) {
        // Check if Already following

        //Check if arrays are full, and expand them if necessary
        var isFollowedListFull = followed.followerCount == followed.followerList.length-1;
        var isFollowerListFull = follower.followCount == follower.followedList.length -1;

        if (isFollowerListFull) {
            follower.followedList = ArrayTools.returnExpandedArray(follower.followedList);
        }
        if (isFollowedListFull) {
            followed.followerList = ArrayTools.returnExpandedArray(followed.followerList);
        }

        // Add followed to follower's followed list
        follower.followedList[follower.followCount++] = followed;
        // Add follower to followed's follower list
        followed.followerList[followed.followerCount++] = follower;
    }

    public static Object[] getAccountList() {
        return accountList;
    }

    public static int getAccountCount() {
        return accountCount;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
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

    public static void setAccountList(Object[] accountList) {
        Account.accountList = accountList;
    }

    public static void setAccountCount(int accountCount) {
        Account.accountCount = accountCount;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }

    public void setTweets(Object[] tweets) {
        this.tweets = tweets;
    }

    public void setTweetCount(int tweetCount) {
        this.tweetCount = tweetCount;
    }

    public void setFollowedList(Object[] followedList) {
        this.followedList = followedList;
    }

    public void setFollowerCount(int followerCount) {
        this.followerCount = followerCount;
    }

    public void setFollowerList(Object[] followerList) {
        this.followerList = followerList;
    }

    public void setFollowCount(int followCount) {
        this.followCount = followCount;
    }
}

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
    private int followerCount =0;

    private Object[] followerList = new Object[1];
    private int followCount = 0;

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

}

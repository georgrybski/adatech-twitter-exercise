package br.com.ada.georg.twitter;

public class Main {
    public static void main(String[] args) {
        boolean run = true;
        Account loggedAccount = null;

        while (run) {

            switch (Input.getInt("0-Exit 1-Create account 2-Log In 3-Log out 4- print all account info, 5-follow 6-printFollowed \n7-printFollowing 8-Post Tweet 9-View my tweets 10-View All tweets",
                    0, 10)) {

                // Exit application
                case (0):
                    run = false;
                    break;

                // TODO create a check to avoid being able to create an account while logged in
                // Create account
                case (1):
                    Input.registerAccount(loggedAccount);
                    break;

                // Log in
                case (2):
                    loggedAccount = Input.logIn(loggedAccount);
                    break;

                // Log out
                case (3):
                    loggedAccount = Account.logOut(loggedAccount);
                    break;

                // Print all registered accounts
                case (4):
                    Account.printAllAccounts(Account.getAccountList());
                    break;

                // Follow someone
                case (5):
                    System.out.println(loggedAccount);
                    Input.follow(loggedAccount);
                    break;

                // Print following
                case (6):
                    if (loggedAccount != null) {
                        Account.printAllAccounts(loggedAccount.getFollowedList());
                    }
                    break;

                // Print followed
                case (7):
                    if (loggedAccount != null) {
                        Account.printAllAccounts(loggedAccount.getFollowerList());
                    }
                    break;

                // Post Tweet
                case (8):
                    Input.postTweet(loggedAccount);
                    break;

                // Print tweets by logged user
                case (9):
                    Twitter.printTweetsByAccount(loggedAccount);
                    System.out.println("number of tweets by" + loggedAccount.getHandle() + ": " + loggedAccount.getTweets().length);
                    break;

                // Print all tweets
                case (10):
                    Twitter.printAllTweets();
                    System.out.println("number of tweets currently posted" + Tweet.getTweetList().length);
                    break;
            }
        }
    }
}


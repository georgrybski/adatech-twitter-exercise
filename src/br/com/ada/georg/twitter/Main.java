package br.com.ada.georg.twitter;

public class Main {
    public static void main(String[] args) {

        boolean run = true;
        Account loggedAccount = null;

        //TODO create admin account (with privilege to view all accounts info).
//        Account.registerAccount();

        while (run) {
            if (loggedAccount != null) {
                boolean isAdmin = loggedAccount.getUsername().equalsIgnoreCase("admin");
                if (isAdmin) {
                    switch (Input.getInt("0-Exit 1-Create account 2-View account info 3-View all profiles 4-view all tweets 5-log out",
                            0, 10)) {
                        // Exit
                        case 0:
                            run = false;
                            break;
                        // Create account
                        case 1:
                            Input.registerAccount(loggedAccount);
                            break;
                        case 3:
                            Twitter.printAllProfiles(loggedAccount);
                            break;
                        case 4:
                            break;

                        // Log out
                        case 5:
                            loggedAccount = Account.logOut(loggedAccount);
                            break;
                        // TODO: add admin capability to suspend and/or delete accounts and delete tweets.
                    }
                } else {
                    switch (Input.getInt("0-Exit 3-Log out 4- print all account info, 5-follow 6-printFollowed \n7-printFollowing 8-Post Tweet 9-View my tweets 10-View All tweets",
                            0, 10)) {

                        // TODO: add a "goodbye" method
                        // Exit application
                        case (0):
                            run = false;
                            break;

                        // Log out
                        case (3):
                            loggedAccount = Account.logOut(loggedAccount);
                            break;

                        // Print all registered accounts
                        case (4):
                            Account.printAllAccounts(loggedAccount, Account.getAccountList());
                            break;

                        // Follow someone
                        case (5):
                            System.out.println(loggedAccount);
                            Input.follow(loggedAccount);
                            break;

                        // Print following
                        case (6):
                            Account.printAllAccounts(loggedAccount, loggedAccount.getFollowedList());
                            break;

                        // Print followed
                        case (7):
                            Account.printAllAccounts(loggedAccount, loggedAccount.getFollowerList());
                            break;

                        // Post Tweet
                        case (8):
                            Input.postTweet(loggedAccount);
                            break;

                        // Print tweets by logged user
                        case (9):
                            Twitter.printTweetsByAccount(loggedAccount);
                            break;

                        // Print all tweets
                        case (10):
                            Twitter.printAllTweets();
                            break;
                    }
                }
            }

            if (loggedAccount == null) {
                switch (Input.getInt("0-Exit 1-Create account 2-Log In",
                        0, 10)) {

                    // TODO: add a "goodbye" method
                    // Exit application
                    case (0):
                        run = false;
                        break;

                    // Create account
                    case (1):
                        Input.registerAccount(loggedAccount);
                        break;

                    // Log in
                    case (2):
                        loggedAccount = Input.logIn(loggedAccount);
                        break;
                }
            }
        }
    }
}



package br.com.ada.georg.twitter;

public class Main {
    public static void main(String[] args) {

        boolean run = true;
        Account loggedAccount = null;

        while (run) {

            Twitter.printLoggedInStatus(loggedAccount);

            if (loggedAccount != null) {
                boolean isAdmin = loggedAccount.getUsername().equalsIgnoreCase("admin");
                if (isAdmin) {
                    Twitter.printAdminOptions();
                    switch (Input.getInt("0-Exit 1-Create account 2-View account info 3-View all profiles 4-view all tweets 5-log out",
                            0, 10)) {

                        // Create account
                        case 1:
                            Input.registerAccount(loggedAccount);
                            break;

                            // View all accounts
                        case 2:
                            Twitter.printAllProfiles(loggedAccount);
                            break;

                        // View all tweets
                        case 3:
                            Twitter.printAllTweets();
                            break;

                        // Log out
                        case 4:
                            loggedAccount = Account.logOut(loggedAccount);
                            break;

                        // Exit
                        case 5:
                            run = false;
                            Twitter.printGoodbyeMessage();
                            break;
                        // TODO: add admin capability to suspend and/or delete accounts and delete tweets.
                    }
                }
                else {
                    Twitter.printUserOptions();
                    switch (Input.getInt("Insert a ",
                            0, 10)) {

                        // Exit application
                        case (9):
                            run = false;
                            Twitter.printGoodbyeMessage();
                            break;

                        // Log out
                        case (8):
                            loggedAccount = Account.logOut(loggedAccount);
                            break;

                        // Print all registered accounts
                        case (4):
                            Account.printAllAccounts(loggedAccount, Account.getAccountList());
                            break;

                        // Follow someone
                        case (5):
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
                        case (1):
                            Input.postTweet(loggedAccount);
                            break;

                        // Print tweets by logged user
                        case (3):
                            Twitter.printTweetsByAccount(loggedAccount);
                            break;

                        // Print all tweets
                        case (2):
                            Twitter.printAllTweets();
                            break;
                    }
                }
            }

            if (loggedAccount == null) {
                Twitter.printGuestOptions();
                switch (Input.getInt("",
                        0, 10)) {

                    // TODO: add a "goodbye" method
                    // Exit application
                    case (3):
                        run = false;
                        Twitter.printGoodbyeMessage();
                        break;

                    // Create account
                    case (2):
                        Input.registerAccount(loggedAccount);
                        break;

                    // Log in
                    case (1):
                        loggedAccount = Input.logIn(loggedAccount);
                        break;
                }
            }
        }
    }
}



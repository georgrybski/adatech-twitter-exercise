package br.com.ada.georg.twitter;

public class Main {
    public static void main(String[] args) {
        Account.registerAccount("admin", "admin",
                "Admin", "admin@twitter.com", Time.getCurrentDate());
        boolean run = true;
        Account loggedAccount = null;

        while (run) {

            Twitter.printLoggedInStatus(loggedAccount);

            if (loggedAccount != null) {
                boolean isAdmin = loggedAccount.getUsername().equalsIgnoreCase("admin");
                if (isAdmin) {
                    switch (Twitter.getAdminMenuInput()) {

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
                    switch (Twitter.getUserMenuInput()) {

                        // Post Tweet
                        case (1):
                            Input.postTweet(loggedAccount);
                            break;

                        // Print all tweets
                        case (2):
                            Twitter.printAllTweets();
                            break;

                        // Print tweets by logged user
                        case (3):
                            Twitter.printTweetsByAccount(loggedAccount);
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

                        // Exit application
                        case (8):
                            loggedAccount = Account.logOut(loggedAccount);
                            break;

                        // Log out
                        case (9):
                            run = false;
                            Twitter.printGoodbyeMessage();
                            break;

                    }
                }
            }

            if (loggedAccount == null) {
                switch (Twitter.getGuestMenuInput()) {

                    // Log in
                    case (1):
                        loggedAccount = Input.logIn(loggedAccount);
                        break;

                    // Create account
                    case (2):
                        Input.registerAccount(loggedAccount);
                        break;

                    // Exit application
                    case (3):
                        run = false;
                        Twitter.printGoodbyeMessage();
                        break;
                }
            }
        }
    }
}



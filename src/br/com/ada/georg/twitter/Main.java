package br.com.ada.georg.twitter;

public class Main {
    public static void main(String[] args) {
        Account.registerAccount("admin", "admin",
                "Admin", "admin@twitter.com", Time.getCurrentDate());
        Twitter.printFramedMessage("Hello there! To access administrator privileges, log in with username admin  and password admin.");
        Twitter.printWelcomeMessage();


        boolean run = true;
        Account loggedAccount = null;

        while (run) {

            if (loggedAccount != null) {

                boolean isAdmin = loggedAccount.getUsername().equalsIgnoreCase("admin");
                if (isAdmin) {
                    Twitter.printLoggedInStatus(loggedAccount);
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
                    Twitter.printLoggedInStatus(loggedAccount);
                    switch (Twitter.getUserMenuInput()) {

                        // Post Tweet
                        case (1):
                            Input.postTweet(loggedAccount);
                            break;

                        // Print all tweets
                        case (2):
                            Twitter.viewTimelines(loggedAccount);
                            break;

                        // View My Profile
                        case (3):
                            Twitter.printFormattedProfile(loggedAccount, loggedAccount);
                            break;

                        case (4):
                            Input.searchProfile(loggedAccount);
                            break;

                        // Follow someone
                        case (5):
                            Input.follow(loggedAccount);
                            break;

                        // View My Followers
                        case (6):
                            Twitter.viewFollowers(loggedAccount);
                            break;

                        // View Accounts I Follow
                        case (7):
                            Twitter.viewFollowed(loggedAccount);
                            break;

                        // Log Out
                        case (8):
                            loggedAccount = Account.logOut(loggedAccount);
                            break;

                        // Exit
                        case (9):
                            run = false;
                            Twitter.printGoodbyeMessage();
                            break;
                        // TODO: add functionality to delete comments and user's account.
                    }
                }
            }
            if (loggedAccount == null) {
                Twitter.printLoggedInStatus(loggedAccount);
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



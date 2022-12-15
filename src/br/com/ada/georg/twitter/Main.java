package br.com.ada.georg.twitter;

public class Main {
    public static void main(String[] args) {
        boolean run = true;
        Account loggedAccount = null;

        while(run){

            switch (Input.getInt("0-Exit 1-Create account 2-Log In 3-Log out 4- print all account info, 5-follow 6-printFollowed",
                    0,4)) {
                case(0):
                    run = false;
                    break;
                case(1):
                    Input.registerAccount();
                    break;
                case(2):

                    break;
                case(3):
                    Account.printAllAccounts(Account.getAccountList());
                    break;
                case(4):
                    Account.printAllAccounts(Account.getAccountList());
                    break;
                case(5):
                    if(loggedAccount!=null){

                    }
                    break;
                case(6):
                    if(loggedAccount!=null){
                        Account.printAllAccounts(loggedAccount.getFollowedList());
                    }
                    break;

            }


        }

        Input.registerAccount();


    }
}

package br.com.ada.georg.twitter;

public class AccountChecker {
    private Account account;
    private boolean exists;

    public AccountChecker(Account account, boolean exists) {
        this.account = account;
        this.exists = exists;
    }

    public static AccountChecker accountExists(String username, Object[] list) {
        boolean usernamesAreEqual;
        boolean accountIsNull;

        for (int i = 0; i <list.length ; i++) {
            accountIsNull = (Account) list[i] == null;
            if (accountIsNull) {
                return new AccountChecker(null, false);
            }
            usernamesAreEqual = ((Account) list[i]).getUsername().equals(username);
            if (usernamesAreEqual){
                return new AccountChecker((Account) list[i], true);
            }
        }
        return null;
    }

    public Account getAccount() {
        return account;
    }

    public boolean exists() {
        return exists;
    }
}

package br.com.ada.georg.twitter;

public class AccountChecker {
    private Account account;
    private boolean exists;

    private AccountChecker(Account account, boolean exists) {
        this.account = account;
        this.exists = exists;
    }

    

    public Account getAccount() {
        return account;
    }

    public boolean accountExists() {
        return exists;
    }
}

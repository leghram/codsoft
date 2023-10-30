package src.repository;

import java.util.LinkedList;

public class ATM {
    
    private static LinkedList<Account> accountsList = new LinkedList<>();

    public ATM(){

    }

    public static void addAccount(Account account){
        accountsList.add(account);
    }

    public static Account getAccountById(int accountID){
        Account accountSearched=null;
        for (Account account : accountsList) {
            if (account.getAccount() == accountID) {
                accountSearched = account;
            }
        }
        return accountSearched;
    }

}

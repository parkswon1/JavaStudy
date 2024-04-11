package thisisjava.src.bank;

import thisisjava.src.bank.Exception.NotEnoughMoneyException;

public class Account {
    private String accountNumber;
    private String userName;
    private int balance;

    public Account(String accountNumber, String userName){
        this.accountNumber = accountNumber;
        this.userName = userName;
        this.balance = 0;
    }

    public void deposit(int depositCash){
        this.balance += depositCash;
    }

    public void withdraw(int withdrawCash) throws NotEnoughMoneyException {
        if (withdrawCash > balance){
            throw new NotEnoughMoneyException("잔액이 부족합니다.");
        }
        this.balance -= withdrawCash;
    }

    public int getBalance(){
        return balance;
    }
}

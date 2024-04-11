package thisisjava.src.bank;

import thisisjava.src.bank.Exception.AccountNotFoundException;
import thisisjava.src.bank.Exception.NotEnoughMoneyException;

public class Banker {
    private String name;
    private String bankerID;

    public Banker(String name, String bankerID){
        this.name = name;
        this.bankerID = bankerID;
    }

    //통장 개설 승인
    public void approvalAccount(Bank bank, String accountNumber, String accountHolder){
        bank. makeAccount(accountNumber, accountHolder);
        System.out.println("통장 개설 완료되었습니다.");
    }

    //출금 승인
    public void authoriztionWithdrawal(Bank bank, String accountNumber, int withdrawCash) throws AccountNotFoundException, NotEnoughMoneyException{
        Account account = bank.getAccountList(accountNumber);
        account.withdraw(withdrawCash);
        System.out.println("출금이 승인되었습니다.");
    }

    // 입금 승인
    public void approveDeposit(Bank bank, String accountNumber, int amount) throws AccountNotFoundException {
        Account account = bank.getAccountList(accountNumber);
        account.deposit(amount);
        System.out.println("입금이 승인되었습니다.");
    }
}

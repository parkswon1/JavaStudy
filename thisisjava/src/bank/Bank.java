package thisisjava.src.bank;

import thisisjava.src.bank.Exception.AccountNotFoundException;

import java.util.HashMap;
import java.util.Map;

public class Bank {
    private String bankName;
    private Map<String, Account> accountMap;

    public Bank(String bankName){
        this.bankName = bankName;
        this.accountMap = new HashMap<>();
    }

    //통장 개설
    public void makeAccount(String accountNumber, String accountHolder){
        Account account = new Account(accountNumber, accountHolder);
        accountMap.put(accountNumber, account);
    }

    //특정 통장 정보 조회
    public Account getAccountList(String accountNumber) throws AccountNotFoundException {
        Account account = accountMap.get(accountNumber);
        if (account == null){
            throw new AccountNotFoundException("통장을 찾을 수 없습니다.");
        }
        return account;
    }
}

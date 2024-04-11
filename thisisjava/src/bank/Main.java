package thisisjava.src.bank;

import thisisjava.src.bank.Exception.AccountNotFoundException;
import thisisjava.src.bank.Exception.NotEnoughMoneyException;

import java.util.Scanner;

public class Main {
    static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) throws AccountNotFoundException{
        Bank bank = new Bank("LionCash");
        Banker banker = new Banker("Park", "1999");

        // 통장 개설
        banker.approvalAccount(bank, "0209", "Seok");

        // 남은 잔액 확인
        Account account = bank.getAccountList("0209");

        while(true) {
            System.out.println("현재 잔액: " + account.getBalance());
            System.out.println("1. 입금 2. 출금 그외 입력시 종료");
            int flag = scanner.nextInt();
            if (flag == 1) {
                // 입금
                System.out.print("입금할 금액을 입력하세요: ");
                int depositCash = scanner.nextInt();
                try {
                    banker.approveDeposit(bank, "0209", depositCash);
                } catch (AccountNotFoundException e) {
                    System.out.println("통장 미개설: " + e.getMessage());
                }
            } else if (flag == 2) {
                // 출금
                System.out.print("출금할 금액을 입력하세요: ");
                int withdrawCash = scanner.nextInt();
                try {
                    banker.authoriztionWithdrawal(bank, "0209", withdrawCash);
                    System.out.println(withdrawCash + "원 출금되었습니다.");
                } catch (NotEnoughMoneyException e) {
                    System.out.println("출금 실패: " + e.getMessage());
                }
            } else{
                break;
            }
        }
    }
}
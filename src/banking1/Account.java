package banking1;

import java.util.Scanner;

public class Account {
	private String accNum; // 계좌번호
	private String name; // 이름
	private int balance; // 잔액

	// 계좌번호를 개설할 목적으로 만들어질 객체의 생성자
	public Account(String accNum, String name, int balance) {
		this.accNum = accNum;
		this.name = name;
		this.balance = balance;
	}
	
	public void showAccountInfo() {
		System.out.println("-------------");
		System.out.println("계좌번호: " + accNum);
		System.out.println("이름: " + name);
		System.out.println("잔고: " + balance);
		System.out.println("-------------");
	}
	
	public String getAccNum() {
		return accNum;
	}

	public void setAccNum(String accNum) {
		this.accNum = accNum;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getBalance() {
		return balance;
	}

	public void setBalance(int balance) {
		this.balance = balance;
	}

	// 입금처리
	public boolean plusAccMoney(int money) {
		// ver01에서는 별도의 검사를 하지 않고 입금처리함
		balance += money;
		return true;
	}
	
	// 출금처리
	public boolean minusAccMoney(int money) {
		// ver01에서는 잔고가 마이너스 처리되더라도 입금처리함 조건 검사 없이 출금처리함
		balance -= money;
		return true;
	}
}

package banking4;

import java.util.Scanner;

public abstract class Account {
	private String accNum; // 계좌번호
	private String name; // 이름
	protected int balance; // 잔액

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
	public abstract boolean plusAccMoney(int money);
	
	// 출금처리
	public boolean minusAccMoney(int money) {
		// ver01에서는 잔고가 마이너스 처리되더라도 입금처리함 조건 검사 없이 출금처리함
		balance -= money;
		return true;
	}
	
	// accNum의 해쉬코드를 재정의
	@Override
	public int hashCode() {
		return accNum.hashCode();
	}
	
	// accNum의 해쉬코드를 이용하여 비교
	@Override
	public boolean equals(Object obj) {
		Account acc = (Account)obj;
		Scanner scan = new Scanner(System.in);
				
		if (this.accNum.equals(acc.getAccNum())) {
			System.out.println("중복계좌가 발견되었습니다. 덮어쓸까요?");
			System.out.print("Y or N: ");
			String str = scan.nextLine().toUpperCase();
			
			switch (str) {
			case "Y":
				System.out.println("기존 정보를 삭제하고 덮어쓰기합니다.");
				// 이렇게 하면 덮어쓰기가 안되고 중복 저장이 됨
				return false;
			default:
				System.out.println("새로운 정보가 무시되었습니다. 기존 정보를 유지합니다.");
				return true;
			}
		} else {
			return false;
		}
	}
}

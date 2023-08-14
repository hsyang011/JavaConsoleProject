package banking6;

import java.io.Serializable;

@SuppressWarnings("serial")
public abstract class Account implements Serializable {
	protected String accNum; // 계좌번호
	protected String name; // 이름
	protected int balance; // 잔액

	// 계좌번호를 개설할 목적으로 만들어질 객체의 생성자
	public Account(String accNum, String name, int balance) {
		this.accNum = accNum;
		this.name = name;
		this.balance = balance;
	}
	
	public abstract void showAccountInfo();
	
	public String getAccNum() {
		return accNum;
	}

	public String getName() {
		return name;
	}

	public int getBalance() {
		return balance;
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
				
		if (this.accNum.equals(acc.getAccNum())) {
			return true;
		} else {
			return false;
		}
	}
	
	public abstract String toString();
}

package banking6;

@SuppressWarnings("serial")
public class HighCreditAccount extends Account {
	private double inter;
	private double addInter;
	private String grade;

	public HighCreditAccount(String accNum, String name, int balance, double inter, String grade) {
		super(accNum, name, balance);
		this.inter = inter/100.0;
		this.grade = grade.toUpperCase();
	}
	
	@Override
	public boolean plusAccMoney(int money) {
		switch (grade) {
		case "A":
			addInter = 0.07;
			break;
		case "B":
			addInter = 0.04;
			break;
		case "C":
			addInter = 0.02;
			break;
		}
		balance = (int)(balance + (balance * (inter+addInter)) + money);
		return true;
	}
	
	@Override
	public void showAccountInfo() {
		System.out.println("-------------");
		System.out.println("계좌번호: " + accNum);
		System.out.println("이름: " + name);
		System.out.println("잔고: " + balance);
		System.out.println("기본이자: " + (int)(inter*100) + "%");
		System.out.println("신용등급: " + grade);
		System.out.println("-------------");
	}
	
	@Override
	public String toString() {
		String str = String.format("[신용계좌 %s등급], %s, %s, %d, %d", grade, accNum, name, balance, (int)(inter*100));
		return str;
	}
}

package banking4;

public class HighCreditAccount extends Account {
	private double inter;
	private String grade;

	public HighCreditAccount(String accNum, String name, int balance, double inter, String grade) {
		super(accNum, name, balance);
		this.inter = inter/100.0;
		this.grade = grade;
	}
	
	@Override
	public boolean plusAccMoney(int money) {
		switch (grade) {
		case "A":
			inter += 0.07;
			break;
		case "B":
			inter += 0.04;
			break;
		case "C":
			inter += 0.02;
			break;
		}
		super.balance = (int)(super.balance + (super.balance * inter) + money);
		return true;
	}
}

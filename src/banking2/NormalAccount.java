package banking2;

public class NormalAccount extends Account {
	private double inter;

	public NormalAccount(String accNum, String name, int balance, int inter) {
		super(accNum, name, balance);
		this.inter = inter/100.0;
	}
	
	@Override
	public boolean plusAccMoney(int money) {
		super.balance = (int)(super.balance + (super.balance * inter) + money);
		return true;
	}
}

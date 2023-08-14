package banking6;

@SuppressWarnings("serial")
public class NormalAccount extends Account {
	private double inter;

	public NormalAccount(String accNum, String name, int balance, int inter) {
		super(accNum, name, balance);
		this.inter = inter/100.0;
	}
	
	@Override
	public boolean plusAccMoney(int money) {
		balance = (int)(balance + (balance * inter) + money);
		return true;
	}
	
	@Override
	public void showAccountInfo() {
		System.out.println("-------------");
		System.out.println("계좌번호: " + accNum);
		System.out.println("이름: " + name);
		System.out.println("잔고: " + balance);
		System.out.println("기본이자: " + (int)(inter*100) + "%");
		System.out.println("-------------");
	}
	
	@Override
	public String toString() {
		String str = String.format("[일반계좌], %s, %s, %d, %d", accNum, name, balance, (int)(inter*100));
		return str;
	}
}

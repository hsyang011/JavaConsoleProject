package banking7.jdbc;

import java.sql.SQLException;

public class UpdateSQL extends IConnectImpl {
	private String accNum;
	private int balance;
	private int money;
	
	public UpdateSQL(String accNum, int balance, int money) {
		super("education", "1234");
		this.accNum = accNum;
		this.balance = balance;
		this.money = money;
	}

	@Override
	public void execute() 
	{
		//update 쿼리문 생성
		String sql = "UPDATE banking_tb "
				+ " SET balance=? "
				+ " WHERE accNum=?";
		try {
			//prepared객체 생성
			psmt = con.prepareStatement(sql);
			psmt.setString(2, accNum);
			psmt.setInt(1, balance + money);
			
			//쿼리문 실행 및 결과확인
			psmt.executeUpdate();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			close();
		}
	}
}
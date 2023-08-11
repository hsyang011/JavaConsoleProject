package banking7.jdbc;

import java.sql.SQLException;

public class SelectSQL extends IConnectImpl {
	private String accNum;
	private int balance;

	public SelectSQL(String accNum) {
		super("education", "1234");
		this.accNum = accNum;
	}
	
	public String getAccNum() {
		return accNum;
	}

	public int getBalance() {
		return balance;
	}
	
	@Override
	public void execute() {
		try {
			while (true) {
				/* prepared인터페이스를 통해 인파라미터를 설정하면 문자인 경우
				자동으로 싱글쿼테이션을 추가하게 되므로 || 연산자를 추가해서 쿼리문을
				작성해야 한다. */
				String sql = "SELECT balance FROM banking_tb "
						+ " WHERE accNum=?";
				
				psmt = con.prepareStatement(sql);
				psmt.setString(1, accNum);
				rs = psmt.executeQuery();
				
				while (rs.next()) {
					balance = rs.getInt("balance");
				}
				
				break;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
	}
}

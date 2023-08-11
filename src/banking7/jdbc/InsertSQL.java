package banking7.jdbc;

import java.sql.SQLException;
import java.util.Scanner;

// DB연결을 위한 클래스를 상속한다.
public class InsertSQL extends IConnectImpl {
	private String accNum;
	private String name;
	private int balance;
	
	// 생성자 : 부모클래스의 생성자를 호출하여 연결한다.
	public InsertSQL(String accNum, String name, int balance) {
		super("education", "1234");
		this.accNum = accNum;
		this.name = name;
		this.balance = balance;
	}
	
	// 쿼리 실행을 위한 멤버메소드
	@Override
	public void execute() {
		try {
			// 1. 쿼리문 준비 : 값의 셋팅을 위한 부분을 ?(인파라미터)로 기술한다.
			String query = "INSERT INTO banking_tb VALUES (?, ?, ?)";
			
			// 2. prepared객체 생성 : 준비한 쿼리문을 인수로 전달한다.
			psmt = con.prepareStatement(query);
			psmt.setString(1, accNum);
			psmt.setString(2, name);
			psmt.setInt(3, balance);
			
			// 쿼리실행
			psmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
	}
}

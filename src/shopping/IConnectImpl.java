package shopping;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

/* 인터페이스를 구현한 클래스로 extends 대신 implements를 사용한다.
또한 용어도 '상속'이 아닌 '구현'이라 표현한다. */
public class IConnectImpl implements IConnect {
	
	// 멤버변수
	public Connection con; // DB연결
	public PreparedStatement psmt; // 동적쿼리문 실행
	public CallableStatement csmt; // 프로시저 실행
	public ResultSet rs; // select 실행결과 반환

	public IConnectImpl(String user, String pass) {
		try {
			// 인터페이스에 선언된 멤버상수를 그대로 사용
			Class.forName(ORACLE_DRIVER);
			// 매개변수를 통해 받은 계정정보를 사용
			connect(user, pass);
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패");
			e.printStackTrace();
		}
	}
	
	// DB연결
	@Override
	public void connect(String user, String pass) {
		try {
			con = DriverManager.getConnection(ORACLE_URL, user, pass);
		} catch (SQLException e) {
			System.out.println("데이터베이스 연결 오류");
			e.printStackTrace();
		}
	}
	
	// 오버라이딩의 목적으로 정의한 메소드로 각 크랠스에서 목적에 맞게 재정의하면 된다.
	@Override
	public void execute() {
		// 실행부 없음
	}
	
	// 자원반납
	@Override
	public void close() {
		try {
			if (con != null) con.close();
			if (psmt != null) psmt.close();
			if (csmt != null) csmt.close();
			if (rs != null) rs.close();
		} catch (Exception e) {
			System.out.println("자원 반납시 오류발생");
			e.printStackTrace();
		}
	}
	
	// 사용자로부터 입력값을 받기 위한 메소드
	@Override
	public String scanValue(String title) {
		Scanner scan = new Scanner(System.in);
		System.out.print(title + "을(를) 입력(exit->종료):");
		String inputStr = scan.nextLine();
		
		/* equalsIgnoreCase() : equals()와 동일하게 문자열이 동일한지 비교하는
		메소드로 대소문자를 구분하지 않고 비교한다. 즉, EXIT와 exit를 같은 문자열로
		판단한다. */
		if ("EXIT".equalsIgnoreCase(inputStr)) {
			System.out.println("프로그램을 종료합니다.");
			// 자원반납
			close();
			// 프로그램 자체가 즉시 종료된다.
		}
		
		// 종료가 아니라면 사용자가 입력한 값을 반환한다.
		return inputStr;
	}
}

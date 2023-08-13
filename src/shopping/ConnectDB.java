package shopping;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/* 상속을 목적으로 생성하는 추상클래스. 오라클 DB연결과 자원반납을 담당한다. 추상클래스는
객체를 생성할 수는 없지만 참조변수 선언, 상속 등의 기능은 사용할 수 있다. */
public abstract class ConnectDB {
	
	// 멤버변수 : 상속관계에서 접근가능한 접근지정자로 지정함.
	protected Connection con; // DB연결
	protected Statement stmt; // 정적쿼리문 실행
	protected ResultSet rs; // select실행 후 결과를 반환받음
	
	/* 생성자2 : 앞에서 선언한 생성자를 복사하여 선언된 생성자로 고정된 값으로 DB연결을
	진행한다. 오버로딩으로 정의되었다. */
	public ConnectDB() {
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			con = DriverManager.getConnection(
					"jdbc:oracle:thin:@localhost:1521:xe",
					"education", "1234");
		} catch (ClassNotFoundException e) {
			System.out.println("오라클 드라이버 로딩 실패");
		} catch (SQLException e) {
			System.out.println("DB 연결 실패");
		} catch (Exception e) {
			System.out.println("알 수 없는 예외 발생");
			e.printStackTrace();
		}
	}
	
	// 자원반납
	public void close() {
		try {
			/* Statement, Connection, ResultSet객체에 대한 자원반납 처리 */
			if (stmt != null) stmt.close();
			if (con != null) con.close();
			if (rs != null) rs.close();
		} catch (SQLException e) {
			System.out.println("자원반납시 오류가 발생하였습니다.");
		}
	}
	
	/* 상속관계에서 오버라이딩을 목적으로 생성한 추상메소드로 하위 클래스에 각 업무(update,
	select 등)에 따라 재정의하게 될 것이다.
	
	추상메소드를 포함한 클래스는 반드시 추상클래스로 선언해야 에러가 발생하지 않는다. 따라서
	해당클래스는 abstract로 선언해야 한다. */
	abstract void execute();
}
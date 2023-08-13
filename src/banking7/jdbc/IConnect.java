package banking7.jdbc;

/* 인터페이스에 선언되는 모든 변수는 public static final이 추가되어 '정적상수'가 된다.
모든 메소드는 public abstract가 추가되어 '추상메소드'가 된다. */
public interface IConnect {
	// 멤버상수 : 오라클 드라이버명과 URL을 선언
	String ORACLE_DRIVER = "oracle.jdbc.OracleDriver";
	String ORACLE_URL = "jdbc:oracle:thin:@localhost:1521:xe";
	
	// 멤버추상메소드
	void connect(String user, String pass); // DB연결
	void execute(); // 쿼리문 실행
	void close(); // 자원 반납
}

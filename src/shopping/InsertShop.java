package shopping;

// DB연결을 위한 클래스를 상속한다.
public class InsertShop extends IConnectImpl {
	
	// 생성자 : 부모클래스의 생성자를 호출하여 연결한다.
	public InsertShop() {
		super("education", "1234");
	}
	
	// 쿼리 실행을 위한 멤버메소드
	@Override
	public void execute() {
		try {
			// 1. 쿼리문 준비 : 값의 셋팅을 위한 부분을 ?(인파라미터)로 기술한다.
			String query = "INSERT INTO sh_goods VALUES (seq_total_idx.nextval, ?, ?, sysdate, ?)";
			
			// 2. prepared객체 생성 : 준비한 쿼리문을 인수로 전달한다.
			psmt = con.prepareStatement(query);
			
			// 사용자로부터 insert할 내용을 입력받는다.
			String goods_name = scanValue("상품명");
			String goods_price = scanValue("상품가격");
			String p_code = scanValue("상품코드");
			
			psmt.setString(1, goods_name);
			psmt.setString(2, goods_price);
			psmt.setString(3, p_code);
			
			// 쿼리실행 및 결과값 반환
			int affected = psmt.executeUpdate();
			System.out.println(affected + "행이 입력되었습니다.");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close();
		}
	}

	public static void main(String[] args) {
		new InsertShop().execute();
	}

}

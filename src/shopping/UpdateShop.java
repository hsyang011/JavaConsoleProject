package shopping;

import java.sql.Types;

public class UpdateShop extends IConnectImpl {
	
	// 생성자 : 부모클래스의 생성자를 호출하여 연결한다.
	public UpdateShop() {
		super("education", "1234");
	}
	
	// 쿼리 실행을 위한 멤버메소드
	@Override
	public void execute() {
		try {
			csmt = con.prepareCall("{ call ShopUpdateGoods(?, ?, ?, ?, ?) }");
			csmt.setString(1, scanValue("상품명"));
			csmt.setString(2, scanValue("가격"));
			csmt.setString(3, scanValue("제품코드"));
			csmt.setString(4, scanValue("수정할 상품의 일련번호"));
			csmt.registerOutParameter(5, Types.INTEGER);
			csmt.execute();
			
			switch (csmt.getInt(5)) {
			case 0:
				System.out.println("수정에 실패하였습니다.");
				break;
			case 1:
				System.out.println("수정에 성공하였습니다.");
				break;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close();
		}
	}
	
	public static void main(String[] args) {
		new UpdateShop().execute();
	}

}

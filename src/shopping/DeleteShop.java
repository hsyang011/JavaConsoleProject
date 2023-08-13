package shopping;

import java.sql.Types;

public class DeleteShop extends IConnectImpl {
	
	// 생성자 : 부모클래스의 생성자를 호출하여 연결한다.
	public DeleteShop() {
		super("education", "1234");
	}
	
	// 쿼리 실행을 위한 멤버메소드
	@Override
	public void execute() {
		try {
			csmt = con.prepareCall("{ call ShopDeleteGoods(?, ?) }");
			csmt.setString(1, scanValue("삭제할 상품의 일련번호"));
			csmt.registerOutParameter(2, Types.INTEGER);
			csmt.execute();
			
			switch (csmt.getInt(2)) {
			case 0:
				System.out.println("삭제에 실패하였습니다.");
				break;
			case 1:
				System.out.println("삭제에 성공하였습니다.");
				break;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close();
		}
	}
	
	public static void main(String[] args) {
		new DeleteShop().execute();
	}

}

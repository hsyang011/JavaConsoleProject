package shopping;

import java.sql.SQLException;
import java.util.Scanner;

public class SelectShop extends ConnectDB {
	
	@Override
	public void execute() {
		Scanner scan = new Scanner(System.in);
		
		try {
			stmt = con.createStatement();
			
			System.out.println("검색할 상품명을 입력하세요: ");
			String search = scan.nextLine();
			
			String query = "SELECT g_idx, goods_name, to_char(goods_price, '999,999,000'), "
					+ " to_char(regidate, 'yyyy-mm-dd hh:mi'), p_code "
					+ " FROM sh_goods "
					+ " WHERE goods_name LIKE '%" + search + "%'";
			
			rs = stmt.executeQuery(query);

			while (rs.next()) {
				String g_idx = rs.getString(1);
				String goods_name = rs.getString(2);
				String goods_price = rs.getString(3);
				String regidate = rs.getString(4);
				String p_code = rs.getString(5);
				
				System.out.printf("%s %s %s %s %s\n", g_idx, goods_name, goods_price, regidate, p_code);
			} 
		} catch (SQLException e) {
			System.out.println("쿼리오류발생");
			e.printStackTrace();
		} finally {
			scan.close();
			close(); // DB자원반납
		}
	}

	public static void main(String[] args) {
		new SelectShop().execute();
	}

}

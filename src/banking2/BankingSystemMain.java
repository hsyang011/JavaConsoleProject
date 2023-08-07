package banking2;

import java.util.Scanner;

public class BankingSystemMain implements ICustomDefine {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		AccountManager accMgr = new AccountManager();
		
		while (true) {
			accMgr.showMenu();
			
			int choice = scan.nextInt();
			scan.nextLine(); // 버퍼날림
			
			switch (choice) {
			case MAKE:
				System.out.println("***신규계좌개설***");
				accMgr.makeAccount();
				break;
			case DEPOSIT:
				System.out.println("***입 금***");
				accMgr.depositMoney();
				break;
			case WITHDRAW:
				System.out.println("***출 금***");
				accMgr.withdrawMoney();
				break;
			case INQUIRE:
				System.out.println("***계좌정보출력***");
				accMgr.showAccInfo();
				break;
			case EXIT:
				System.out.println("프로그램을 종료합니다. 이용해주셔서 감사합니다.");
				return;
			}
		}
	}

}

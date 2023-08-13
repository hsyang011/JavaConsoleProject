package banking6_2;

import java.util.InputMismatchException;
import java.util.Scanner;

public class BankingSystemMain implements ICustomDefine {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		AccountManager accMgr = AccountManager.getManager();
		
		// 저장되어있는 객체 파일을 불러오기
		accMgr.objectInput();
		
		while (true) {
			accMgr.showMenu();
			
			int choice;
			
			// 문자열을 입력했을 때 예외처리
			try {
				choice = scan.nextInt();
				scan.nextLine(); // 버퍼날림
			} catch (InputMismatchException e) {
				System.out.println("잘못 입력하였습니다. 숫자가 아닙니다.");
				scan.nextLine(); // 버퍼날림
				continue;
			}
			
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
			case REMOVE:
				System.out.println("***계좌정보삭제***");
				accMgr.removeAccount();
				break;
			case SAVE:
				System.out.println("***저장옵션***");
				accMgr.saveOption();
				break;
			case EXIT:
				// 종료되기 직전 객체를 obj파일로 저장하기
				accMgr.objectOutput();
				System.out.println("프로그램을 종료합니다. 이용해주셔서 감사합니다.");
				return;
			// 범위를 벗어났을 때 예외처리
			default:
				try {
					String message = "범위가 잘못되었습니다.\n1~5사이의 숫자를 입력해주세요.";
					MenuSelectException ex = new MenuSelectException(message);
					throw ex;
				} catch (MenuSelectException e) {
					System.out.println(e.getMessage());
				}
			}
			
			System.out.println("계속하려면 엔터키를 누르세요.");
			scan.nextLine();
		}
	}

}

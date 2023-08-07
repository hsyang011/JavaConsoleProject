package banking3;

import java.util.InputMismatchException;
import java.util.Scanner;

public class BankingSystemMain implements ICustomDefine {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		AccountManager accMgr = new AccountManager();

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
			case EXIT:
				System.out.println("프로그램을 종료합니다. 이용해주셔서 감사합니다.");
				return;
			// 범위를 벗어났을 때 예외처리
			default:
				System.out.println("범위가 잘못되었습니다.");
				System.out.println("1~5사이의 숫자를 입력해주세요.");
			}
		}
	}

}

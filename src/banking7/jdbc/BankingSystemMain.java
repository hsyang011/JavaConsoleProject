package banking7.jdbc;

import java.util.Scanner;

public class BankingSystemMain implements ICustomDefine {
	static Scanner scan = new Scanner(System.in);

	// 메뉴출력
	public static void showMenu() {
		System.out.println("-----Menu-----");
		System.out.println("1. 계좌개설");
		System.out.println("2. 입금");
		System.out.println("3. 출금");
		System.out.println("4. 계좌정보출력");
		System.out.println("5. 프로그램종료");
		System.out.print("선택: ");
	}
	
	// 계좌개설
	public static void makeAccount() {
		System.out.print("계좌번호: " );
		String accNum = scan.nextLine();
		System.out.print("고객이름: ");
		String name = scan.nextLine();
		System.out.print("잔고: ");
		int balance = scan.nextInt();
		scan.nextLine();
		
		// 계좌개설에 필요한 정보들을 입력받아 인자로 보낸다.
		new InsertSQL(accNum, name, balance).execute();
		
		System.out.println("계좌개설이 완료되었습니다.");
	}
	
	// 입금
	public static void depositMoney() {
		System.out.println("계좌번호와 입금할 금액을 입력하세요.");
		System.out.print("계좌번호: ");
		String searchAccNum = scan.nextLine();

		// 셀렉트를 먼저 실행하여 입력한 계좌번호의 잔고를 가져온다.
		SelectSQL sel = new SelectSQL(searchAccNum);
		sel.execute();

		// 계좌번호와 잔고를 가져온다.
		String accNum = sel.getAccNum();
		int balance = sel.getBalance();
		System.out.println("현재 잔고는 " + balance + "원 입니다.");
		System.out.print("입금액: ");
		int money = scan.nextInt();
		scan.nextLine();
		// 업데이트를 실행하여 잔고를 수정한다.
		UpdateSQL upd = new UpdateSQL(accNum, balance, money);
		upd.execute();
		
		System.out.println(money + "원 입금이 완료되었습니다.");
		System.out.println("입금 후 잔고는 " + (balance + money) + "원 입니다.");
	}
	
	// 출금
	public static void withdrawMoney() {
		System.out.println("계좌번호와 출금할 금액을 입력하세요.");
		System.out.print("계좌번호: ");
		String searchAccNum = scan.nextLine();
		System.out.print("출금액: ");
		int money = scan.nextInt();
		scan.nextLine();
		
		// 셀렉트를 먼저 실행하여 입력한 계좌번호의 잔고를 가져온다.
		SelectSQL sel = new SelectSQL(searchAccNum);
		sel.execute();
	
		// 계좌번호와 잔고를 가져온다.
		String accNum = sel.getAccNum();
		int balance = sel.getBalance();
		System.out.println("현재 잔고는 " + balance + "원 입니다.");
		// 업데이트를 실행하여 잔고를 수정한다.
		UpdateSQL upd = new UpdateSQL(accNum, balance, -money);
		upd.execute();
		
		System.out.println(money + "원 출금이 완료되었습니다.");
		System.out.println("출금 후 잔고는 " + (balance - money) + "원 입니다.");
	}
	
	public static void showAccInfo() {
		new SelectAllSQL().execute();
		System.out.println("전제계좌정보 출력이 완료되었습니다.");
	}

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		
		while (true) {
			showMenu();
			
			int choice = scan.nextInt();
			scan.nextLine(); // 버퍼날림
			
			switch (choice) {
			case MAKE:
				System.out.println("***신규계좌개설***");
				makeAccount();
				break;
			case DEPOSIT:
				System.out.println("***입 금***");
				depositMoney();
				break;
			case WITHDRAW:
				System.out.println("***출 금***");
				withdrawMoney();
				break;
			case INQUIRE:
				System.out.println("***계좌정보출력***");
				showAccInfo();
				break;
			case EXIT:
				System.out.println("프로그램을 종료합니다. 이용해주셔서 감사합니다.");
				scan.close();
				return;
			}
		}
	}

}

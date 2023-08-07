package banking1;

import java.util.Scanner;

public class BankingSystemMain implements ICustomDefine {
	public static Scanner scan = new Scanner(System.in);
	static Account[] accArr = new Account[50]; // Account객체 배열 생성
	static int accCnt = 0; // 객체배열의 길이를 저장할 변수

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
		
		accArr[accCnt++] = new Account(accNum, name, balance);
		System.out.println("계좌개설이 완료되었습니다.");
		System.out.println();
	}
	
	public static void depositMoney() {
		System.out.println("계좌번호와 입금할 금액을 입력하세요.");
		System.out.print("계좌번호: ");
		String searchAccount = scan.nextLine();
		System.out.print("입금액: ");
		int money = scan.nextInt();
		scan.nextLine();
		
		for (int i=0; i<accCnt; i++) {
			if (searchAccount.equals(accArr[i].getAccNum())) {
				accArr[i].plusAccMoney(money);
				System.out.println("입금이 완료되었습니다.");
				break;
			}
		}
	}
	
	public static void withdrawMoney() {
		System.out.println("계좌번호와 출금할 금액을 입력하세요.");
		System.out.print("계좌번호: ");
		String searchAccount = scan.nextLine();
		System.out.print("출금액: ");
		int money = scan.nextInt();
		scan.nextLine();
		
		for (int i=0; i<accCnt; i++) {
			if (searchAccount.equals(accArr[i].getAccNum())) {
				accArr[i].minusAccMoney(money);
				System.out.println("출금이 완료되었습니다.");
				break;
			}
		}
	}
	
	public static void showAccInfo() {
		for (int i=0; i<accCnt; i++) {
			accArr[i].showAccountInfo();
		}
		System.out.println("전제계좌정보 출력이 완료되었습니다.");
	}

	public static void main(String[] args) {
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
				return;
			}
		}
	}

}

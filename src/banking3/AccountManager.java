package banking3;

import java.util.InputMismatchException;
import java.util.Scanner;

public class AccountManager {
	private Scanner scan = new Scanner(System.in);
	private Account[] accArr = new Account[50]; // Account객체 배열 생성
	private int accCnt = 0; // 객체배열의 길이를 저장할 변수

	// 메뉴출력
	public void showMenu() {
		System.out.println("-----Menu-----");
		System.out.println("1. 계좌개설");
		System.out.println("2. 입금");
		System.out.println("3. 출금");
		System.out.println("4. 계좌정보출력");
		System.out.println("5. 프로그램종료");
		System.out.print("선택: ");
	}
	
	// 계좌개설
	public void makeAccount() {
		// 계좌선택
		System.out.println("-----계좌선택-----");
		System.out.println("1. 보통계좌");
		System.out.println("2. 신용신뢰계좌");
		System.out.print("선택: ");
		int choice = scan.nextInt();
		scan.nextLine();
		
		// 기본정보 입력
		System.out.print("계좌번호: " );
		String accNum = scan.nextLine();
		System.out.print("고객이름: ");
		String name = scan.nextLine();
		System.out.print("잔고: ");
		int balance = scan.nextInt();
		System.out.print("기본이자 % (정수형태로 입력): ");
		int inter = scan.nextInt();
		scan.nextLine();
		
		// 선택한 계좌에 따라 정보 입력
		switch (choice) {
		case 1:
			accArr[accCnt++] = new NormalAccount(accNum, name, balance, inter);
			break;
		case 2:
			System.out.print("신용등급(A,B,C등급): ");
			String grade = scan.nextLine();
			accArr[accCnt++] = new HighCreditAccount(accNum, name, balance, inter, grade);
			break;
		}
		
		System.out.println("계좌개설이 완료되었습니다.");
		System.out.println();
	}
	
	// 입금처리
	public void depositMoney() {
		System.out.println("계좌번호와 입금할 금액을 입력하세요.");
		System.out.print("계좌번호: ");
		String searchAccount = scan.nextLine();
		System.out.print("입금액: ");
		
		int money;
		
		// 예외처리
		while (true) {
			// 문자열 입력할 때의 예외처리
			try {
				money = scan.nextInt();
				scan.nextLine();
			} catch (InputMismatchException e) {
				System.out.println("문자는 입력할 수 없습니다. 다시 입력해주세요.");
				scan.nextLine();
				continue;
			}
			
			// 음수일 때, 500원 단위가 아닐 때 예외처리
			if (money < 0) {
				System.out.println("음수는 입력할 수 없습니다. 다시 입력해주세요.");
			} else if (money%500 != 0) {
				System.out.println("입금은 500원 단위로 가능합니다. 다시 입력해주세요.");
			} else {
				break;
			}
		}
		
		for (int i=0; i<accCnt; i++) {
			if (searchAccount.equals(accArr[i].getAccNum())) {
				accArr[i].plusAccMoney(money);
				System.out.println(money + "원 입금이 완료되었습니다.");
				break;
			}
		}
	}
	
	// 출금처리
	public void withdrawMoney() {
		System.out.println("계좌번호와 출금할 금액을 입력하세요.");
		System.out.print("계좌번호: ");
		String searchAccount = scan.nextLine();
		System.out.print("출금액: ");
		
		int money;
		
		// 예외처리
		while (true) {
			// 문자열을 입력했을 때 예외처리
			try {
				money = scan.nextInt();
				scan.nextLine();
			} catch (InputMismatchException e) {
				System.out.println("문자는 입력할 수 없습니다. 다시 입력해주세요.");
				scan.nextLine();
				continue;
			}
			
			// 음수, 1000원 단위가 아닐 때 예외처리
			if (money < 0) {
				System.out.println("음수는 입력할 수 없습니다. 다시 입력해주세요.");
			} else if (money%1000 != 0) {
				System.out.println("출금은 1000원 단위로 가능합니다. 다시 입력해주세요.");
			} else {
				break;
			}
		}
		
		for (int i=0; i<accCnt; i++) {
			if (searchAccount.equals(accArr[i].getAccNum())) {
				// 잔고보다 출금액이 더 작을 때
				if (accArr[i].getBalance() > money) {
					accArr[i].minusAccMoney(money);
					System.out.println("출금이 완료되었습니다.");
				// 출금액이 더 클 때
				} else {
					System.out.println("잔고가 부족합니다. 금액 전체를 출금할까요?");
					String str = scan.nextLine();
					
					// yes 혹은 YES를 입력했을 때는 금액 전체 출금
					if (str.toUpperCase().equals("YES")) {
						accArr[i].minusAccMoney(accArr[i].getBalance());
						System.out.println("금액 전체를 출금하였습니다.");
					// 그 외의 값을 입력했을 때는 출금 취소
					} else {
						System.out.println("출금이 취소되었습니다.");
					}
				}
				break;
			}
		}
	}
	
	// 전체계좌정보 출력
	public void showAccInfo() {
		for (int i=0; i<accCnt; i++) {
			accArr[i].showAccountInfo();
		}
		System.out.println("전제계좌정보 출력이 완료되었습니다.");
	}
}

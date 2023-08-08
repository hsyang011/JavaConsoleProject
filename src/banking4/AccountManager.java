package banking4;

import java.util.HashSet;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.Set;

public class AccountManager {
	private Scanner scan = new Scanner(System.in);
	private Set<Account> accSet = new HashSet<Account>();

	// 메뉴출력
	public void showMenu() {
		System.out.println("-----Menu-----");
		System.out.print("1. 계좌개설 ");
		System.out.print("2. 입금 ");
		System.out.print("3. 출금 ");
		System.out.print("4. 계좌정보출력 ");
		System.out.print("5. 계좌정보삭제 ");
		System.out.print("6. 프로그램종료\n");
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
		Account acc = null;
		boolean flag = true;
		switch (choice) {
		case 1:
			acc = new NormalAccount(accNum, name, balance, inter);
			flag = accSet.add(acc);
			break;
		case 2:
			System.out.print("신용등급(A,B,C등급): ");
			String grade = scan.nextLine();
			acc = new HighCreditAccount(accNum, name, balance, inter, grade);
			flag = accSet.add(acc);
			break;
		}
		
		// 값을 넣기에 실패했다면 중복된 것이므로 덮어쓰기 여부를 물어본다.
		if (!flag) {
			System.out.println("중복계좌가 발견되었습니다. 덮어쓸까요?");
			System.out.println("Y or N");
			String str = scan.nextLine();
			
			switch (str) {
			case "Y":
				accSet.remove(acc);
				accSet.add(acc);
				System.out.println("새로운 계좌정보가 저장되었습니다. 기존 계좌정보는 삭제합니다.");
				break;
			default:
				System.out.println("기존 계좌정보를 유지합니다.");
				break;
			}
		}
		
		System.out.println("계좌개설이 완료되었습니다.");
		System.out.println();
	}
	
	// 입금처리
	public void depositMoney() {
		System.out.println("계좌번호와 입금할 금액을 입력하세요.");
		System.out.print("계좌번호: ");
		String searchAcc = scan.nextLine();
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
			try {
				String message;
				if (money < 0) {
					message = "음수는 입력할 수 없습니다. 다시 입력해주세요.";
				} else if (money%500 != 0) {
					message = "입금은 500원 단위로 가능합니다. 다시 입력해주세요.";
				} else {
					break;
				}
				
				MenuSelectException ex = new MenuSelectException(message);
				throw ex;
				
			} catch (MenuSelectException e) {
				System.out.println(e.getMessage());
			}
		}
		
		for (Account acc : accSet) {
			if (searchAcc.equals(acc.getAccNum())) {
				acc.plusAccMoney(money);
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

			// 음수일 때, 1000원 단위가 아닐 때 예외처리
			try {
				String message;
				if (money < 0) {
					message = "음수는 입력할 수 없습니다. 다시 입력해주세요.";
				} else if (money%1000 != 0) {
					message = "출금은 1000원 단위로 가능합니다. 다시 입력해주세요.";
				} else {
					break;
				}
				
				MenuSelectException ex = new MenuSelectException(message);
				throw ex;
				
			} catch (MenuSelectException e) {
				System.out.println(e.getMessage());
			}
		}
		
		for (Account acc : accSet) {
			if (searchAccount.equals(acc.getAccNum())) {
				// 잔고보다 출금액이 더 작을 때
				if (acc.getBalance() > money) {
					acc.minusAccMoney(money);
					System.out.println("출금이 완료되었습니다.");
				// 출금액이 더 클 때
				} else {
					System.out.println("잔고가 부족합니다. 금액 전체를 출금할까요?");
					System.out.print("YES or No: ");
					String str = scan.nextLine();
					
					// yes 혹은 YES를 입력했을 때는 금액 전체 출금
					if (str.toUpperCase().equals("YES")) {
						acc.minusAccMoney(acc.getBalance());
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
		for (Account acc : accSet) {
			acc.showAccountInfo();
		}
		System.out.println("전제계좌정보 출력이 완료되었습니다.");
	}
	
	// 계좌정보 삭제
	public void removeAccount() {
		System.out.print("삭제할 계좌정보의 계좌번호를 입력해주세요: ");
		String removeAcc = scan.nextLine();
		
		for (Account acc : accSet) {
			if (removeAcc.equals(acc.getAccNum())) {
				accSet.remove(acc);
				System.out.println("계좌번호가 " + removeAcc + "인 계좌정보를 삭제했습니다.");
				return;
			}
		}
		
		System.out.println("삭제할 계좌정보를 찾을 수 없습니다.");
	}
}

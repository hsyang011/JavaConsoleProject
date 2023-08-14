package banking7.threeby3;

import java.util.Scanner;

import banking1.Account;

public class BankingSystemMain implements ICustomDefine {
	static Scanner scan = new Scanner(System.in);
	static Account[] accArr = new Account[50]; // Account객체 배열 생성
	static int accCnt = 0; // 객체배열의 길이를 저장할 변수

	// 메뉴출력
	public static void showMenu() {
		System.out.println("-----Menu-----");
		System.out.println("1. 계좌개설");
		System.out.println("2. 입금");
		System.out.println("3. 출금");
		System.out.println("4. 계좌정보출력");
		System.out.println("5. 게임시작");
		System.out.println("6. 프로그램종료");
		System.out.print("선택: ");
	}
	
	// 계좌개설
	public static void makeAccount() {
		// 사용자로부터 입력받는다.
		System.out.print("계좌번호: " );
		String accNum = scan.nextLine();
		System.out.print("고객이름: ");
		String name = scan.nextLine();
		System.out.print("잔고: ");
		int balance = scan.nextInt();
		scan.nextLine();
		
		// 사용자로부터 입력받은 변수들을 생성자의 인자로 보낸다.
		new InsertSQL(accNum, name, balance).execute();
		
		System.out.println("계좌개설이 완료되었습니다.");
	}
	
	// 입금
	public static void depositMoney() {
		// 사용자로부터 계좌번호를 입력받는다.
		System.out.println("계좌번호와 입금할 금액을 입력하세요.");
		System.out.print("계좌번호: ");
		String searchAccNum = scan.nextLine();

		// 사용자로부터 계좌번호를 입력받은 후 생성자의 인자로 넘겨준다.
		SelectSQL sel = new SelectSQL(searchAccNum);
		sel.execute();
	
		// 계좌번호와 일치하는 레코드를 찾은 후 계좌번호와 잔고를 가져온다.
		String accNum = sel.getAccNum();
		int balance = sel.getBalance();
		System.out.println("현재 잔고는 " + balance + "원 입니다.");
		// 사용자로부터 입금액을 입력받는다.
		System.out.print("입금액: ");
		int money = scan.nextInt();
		scan.nextLine();
		// 생성자의 인자로 계좌번호, 잔고, 입금액을 넘겨준다.
		UpdateSQL upd = new UpdateSQL(accNum, balance, money);
		// 잔고를 수정한다.
		upd.execute();
		
		System.out.println(money + "원 입금이 완료되었습니다.");
		System.out.println("입금 후 잔고는 " + (balance + money) + "원 입니다.");
	}
	
	public static void withdrawMoney() {
		System.out.println("계좌번호와 출금할 금액을 입력하세요.");
		System.out.print("계좌번호: ");
		String searchAccNum = scan.nextLine();
		System.out.print("출금액: ");
		int money = scan.nextInt();
		scan.nextLine();
		
		SelectSQL sel = new SelectSQL(searchAccNum);
		sel.execute();
	
		String accNum = sel.getAccNum();
		int balance = sel.getBalance();
		System.out.println("현재 잔고는 " + balance + "원 입니다.");
		UpdateSQL upd = new UpdateSQL(accNum, balance, -money);
		upd.execute();
		
		System.out.println(money + "원 출금이 완료되었습니다.");
		System.out.println("출금 후 잔고는 " + (balance - money) + "원 입니다.");
	}
	
	public static void showAccInfo() {
		// 모든 계좌정보를 출력한다.
		new SelectAllSQL().execute();
		System.out.println("전제계좌정보 출력이 완료되었습니다.");
	}
	
	public static void startGame() {
		while (true) {
			// 게임 객체 생성 및 실행
			Game game = new Game();
			boolean flag = game.run();
			
			// 게임을 성공적으로 끝냈을 때
			if (flag) {
				System.out.print("재시작하시겠습니까? (Y 누르면 재시작, 나머지는 종료)");
				String code = scan.nextLine();
				
				// y 혹은 Y를 입력했을 때 게임 재시작
				if (code.equalsIgnoreCase("Y")) {
					System.out.println("게임을 재시작합니다.");
				} else {
					System.out.println("게임을 종료합니다.");
					return;
				}
			// 게임 도중 X를 눌렀을 때
			} else {
				System.out.println("게임을 종료합니다.");
				return;
			}
		}
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
			case GAME:
				System.out.println("***게임시작***");
				startGame();
				break;
			case EXIT:
				System.out.println("프로그램을 종료합니다. 이용해주셔서 감사합니다.");
				scan.close();
				return;
			}
		}
	}

}
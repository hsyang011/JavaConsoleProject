package banking7.threeby3;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Game {
	private Scanner scan = new Scanner(System.in);
	
	// add(), remove()를 자주 호출할 예정이므로 LinkedList를 사용하는 것이 성능에 유리하다.
	private List<String> list = new LinkedList<String>();
	
	public boolean showBoard() {
		String answer = "=======\n1 2 3\n4 5 6\n7 8 X\n=======\n";
		
		// 출력할 보드를 String.format()을 이용하여 작성한다.
		String board = String.format("=======\n%s %s %s\n%s %s %s\n%s %s %s\n=======\n",
				list.get(0), list.get(1), list.get(2), list.get(3), list.get(4),
				list.get(5), list.get(6), list.get(7), list.get(8));
		
		if (board.equals(answer)) {
			System.out.println("==^^정답입니다^^==");
			System.out.println(answer);
			return true;
		}
		
		// 처음 보드 출력
		System.out.println(board);
		return false;
	}
	
	public void initGame() {
		// 리스트의 값 초기화
		for (int i=1; i<=8; i++) {
			list.add(Integer.toString(i));
		}
		list.add("X");
		
		// 셔플
		String[] codeArr = { "W", "A", "S", "D" };
		// 총 3번 셔플을 돌린다.
		for (int i=0; i<1; i++) {
			// 0~3까지의 랜덤값을 부여하고, 그 값을 배열의 인덱스로 접근하여 코드를 꺼내온다.
			String code = codeArr[(int)(Math.random()*4)];
			String temp1, temp2;
			int ranIdx;
			
			switch (code) {
			case "W":
				// 위쪽 이동은 3~8로만 나오게 한다.
				ranIdx = (int)(Math.random()*6)+3;
				temp1 = list.get(ranIdx-3);
				temp2 = list.get(ranIdx);
				list.remove(ranIdx-3);
				list.add(ranIdx-3, temp2);
				list.remove(ranIdx);
				list.add(ranIdx, temp1);
				break;
			case "A":
				// 왼쪽 이동은 (1,2) + (0,3,6)으로만 나오게 한다. 즉, 1,2,4,5,7,8만 나온다.
				ranIdx = (int)(Math.random()*2)+1+(((int)(Math.random()*3))*3);
				temp1 = list.get(ranIdx-1);
				temp2 = list.get(ranIdx);
				list.remove(ranIdx-1);
				list.add(ranIdx-1, temp2);
				list.remove(ranIdx);
				list.add(ranIdx, temp1);
				break;
			case "S":
				// 아래쪽 이동은 0~5로만 나오게 한다.
				ranIdx = (int)(Math.random()*6);
				temp1 = list.get(ranIdx+3);
				temp2 = list.get(ranIdx);
				list.remove(ranIdx+3);
				list.add(ranIdx+3, temp2);
				list.remove(ranIdx);
				list.add(ranIdx, temp1);
				break;
			case "D":
				// 오른쪽 이동은 (0,1) + (0,3,6)으로만 나오게 한다. 즉, 0,1,3,4,6,7만 나온다.
				ranIdx = (int)(Math.random()*2)+(((int)(Math.random()*3))*3);
				temp1 = list.get(ranIdx+1);
				temp2 = list.get(ranIdx);
				list.remove(ranIdx+1);
				list.add(ranIdx+1, temp2);
				list.remove(ranIdx);
				list.add(ranIdx, temp1);
				break;
			}
		}
	}

	public boolean run() {
		System.out.println("3 by 3 게임을 시작합니다.");
		
		// 게임 셋팅 (리스트 생성 및 셔플)
		initGame();
		
		// 생성된 보드 출력
		showBoard();

		// 정답을 맞출때까지 혹은 X를 누를때까지 반복
		while (true) {
			System.out.println("[사용법] W:위쪽, A:왼쪽, S:아래쪽, D:오른쪽, X:종료");
			System.out.print("키를 입력해주세요:");
			String code = scan.nextLine();
			int idx = list.indexOf("X");
			
			switch (code.toUpperCase()) {
			case "W":
				if (0<=idx && idx<=2) {
					System.out.println("xxxxxxxxxxxxxxxxxxxx");
					System.out.println("xxxxxxx이동불가xxxxxxx");
					System.out.println("xxxxxxxxxxxxxxxxxxxx");
				} else {
					String temp = list.get(idx-3);
					list.remove(idx-3);
					list.add(idx-3, "X");
					list.remove(idx);
					list.add(idx, temp);
					System.out.println("X를 위로 이동하였습니다.");
				}
				break;
			case "A":
				if (idx%3 == 0) {
					System.out.println("xxxxxxxxxxxxxxxxxxxx");
					System.out.println("xxxxxxx이동불가xxxxxxx");
					System.out.println("xxxxxxxxxxxxxxxxxxxx");
				} else {
					String temp = list.get(idx-1);
					list.remove(idx-1);
					list.add(idx-1, "X");
					list.remove(idx);
					list.add(idx, temp);
					System.out.println("X를 왼쪽으로 이동하였습니다.");
				}
				break;
			case "S":
				if (6<=idx && idx<=8) {
					System.out.println("xxxxxxxxxxxxxxxxxxxx");
					System.out.println("xxxxxxx이동불가xxxxxxx");
					System.out.println("xxxxxxxxxxxxxxxxxxxx");
				} else {
					String temp = list.get(idx+3);
					list.remove(idx+3);
					list.add(idx+3, "X");
					list.remove(idx);
					list.add(idx, temp);
					System.out.println("X를 아래쪽으로 이동하였습니다.");
				}
				break;
			case "D":
				if (idx%3 == 2) {
					System.out.println("xxxxxxxxxxxxxxxxxxxx");
					System.out.println("xxxxxxx이동불가xxxxxxx");
					System.out.println("xxxxxxxxxxxxxxxxxxxx");
				} else {
					String temp = list.get(idx+1);
					list.remove(idx+1);
					list.add(idx+1, "X");
					list.remove(idx);
					list.add(idx, temp);
					System.out.println("X를 오른쪽으로 이동하였습니다.");
				}
				break;
			case "X":
				return false;
			default:
				System.out.println("W, A, S, D 중 하나로만 입력해주세요.");
				break;
			}
			
			if (showBoard()) {
				return true;
			}
		}
	}

}

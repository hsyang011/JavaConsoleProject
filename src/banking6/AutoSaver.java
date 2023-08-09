package banking6;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Set;

public class AutoSaver extends Thread {
	private Set<Account> accSet;
	
	public AutoSaver(AccountManager accMgr) {
		accSet = accMgr.getAccSet();
	}
	
	@Override
	public void run() {
		while (true) {
			try {
				// 5초동안 쉬기
				sleep(5000);
				
				// 문자열을 저장하기 위한 출력스트림 생성
				PrintWriter out = new PrintWriter(
						new FileWriter("src/banking6/AutoSaveAccount.txt")
				);
				
				for (Account acc : accSet) {
					out.printf("%s\n", acc.toString());
				}
				
				out.close();
				System.out.println("자동저장이 완료되었습니다.");
				
			} catch (InterruptedException e) {
				System.out.println("자동저장을 종료합니다.");
				// 무한루프를 탈출하여 쓰레드의 반복실행을 멈춘다.
				break;
			} catch (FileNotFoundException e) {
				System.out.println("파일이 존재하지 않습니다.");
			} catch (IOException e) {
				System.out.println("IO오류");
			}
		}
	}
}

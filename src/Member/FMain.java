package Member;

import java.beans.beancontext.BeanContextMembershipListener;
import java.util.Scanner;

public class FMain {

	public static void main(String[] args) {
		
		FSQL sql = new FSQL();
		FMember member = new FMember();
		Scanner sc = new Scanner(System.in);
		
		boolean run = true;
		int menu = 0;
		
		while(run) {
			System.out.println("========================================");
			System.out.println("1.DB접속   2.DB해제   3.회원가입   4.회원목록");
			System.out.println("5.회원수정  6.회원삭제  7.회원조회   8.종료");
			System.out.println("========================================");
			System.out.print("선택 >> ");
			menu = sc.nextInt();
			
			switch(menu) {
			case 1:
				sql.connect();
				break;
			case 2:
				sql.conClose();
				break;
			case 3:
				System.out.println("회원정보를 입력해주세요.");
				
				// 회원정보(필드) 입력받기
				System.out.print("아이디 >> ");
				String fId = sc.next();
				
				System.out.print("비밀번호 >> ");
				String fPw = sc.next();
				
				System.out.print("이름 >> ");
				String fName = sc.next();
				
				System.out.print("생년월일 ex)2022.03.15 >> ");
				String fBirth = sc.next();
				
				System.out.print("성별 >> ");
				String fGender = sc.next();
				
				System.out.print("연락처 >> ");
				String fPhone = sc.next();
				
				// FMember클래스의 member객체에 입력한 정보 setter로 저장하기
				member.setfId(fId);
				member.setfPw(fPw);
				member.setfName(fName);
				member.setfBirth(fBirth);
				member.setfGender(fGender);
				member.setfPhone(fPhone);
				
				sql.insert(member);
				break;
			case 4:
				sql.select();
				break;
			case 5:
				System.out.println("회원정보 수정하기");
				System.out.println("아이디와 비밀번호를 입력해주세요!");
				
				System.out.print("아이디 >> ");
				fId = sc.next();
				
				System.out.print("비밀번호 >> ");
				fPw = sc.next();
				
				boolean check = sql.idCheck(fId, fPw);
				// System.out.println("check : " + check);
				
				if(check) {
					System.out.println("아이디와 비밀번호가 일치합니다.");
					System.out.print("변경할 이름 >> ");
					fName = sc.next();
					
					sql.modify(fId, fName);
					
				} else {
					System.out.println("아이디와 비밀번호를 확인해주세요!");
				}
				
				break;
			case 6:
				System.out.println("회원정보 삭제하기");
				System.out.println("아이디와 비밀번호를 입력해주세요!");
				
				System.out.print("아이디 >> ");
				fId = sc.next();
				
				System.out.print("비밀번호 >> ");
				fPw = sc.next();
				
				check = sql.idCheck(fId, fPw);
				// System.out.println("check : " + check);
				
				if(check) {
					System.out.println("아이디와 비밀번호가 일치합니다.");
					
					sql.delete(fId);
					
				} else {
					System.out.println("아이디와 비밀번호를 확인해주세요!");
				}
				break;
			case 7:
				System.out.println("회원정보 조회");
				System.out.print("조회할 정보 ( 아이디 / 이름 ) >> ");
				String choice = sc.next();
				
				if(choice.equals("아이디")) {
					
					System.out.print("아이디 >> ");
					fId = sc.next();
					sql.searchId(fId);
					
				} else if(choice.equals("이름")) {
					
					System.out.print("이름 >> ");
					fName = sc.next();
					sql.searchName(fName);
					
				} else {
					System.out.println("'아이디' / '이름' 중에 선택해주세요!");
				}
				
				
				break;
			case 8:
				run = false;
				System.out.println("프로그램을 종료합니다.");
				break;
			default:
				System.out.println("다시 입력해주세요!");
				break;
			}
			
			
			
			
			
			
			
			
			
			
			
		}

	}

}

package Member;

import java.sql.*;

public class FSQL {

	// FSQL클래스 ==> (어제 배운)CRUD클래스 역할

	// DB접속을 위한 Connection타입의 객체 con 선언
	Connection con = null;

	// SQL쿼리문 전송을 위한 Statement타입의 객체 stmt선언
	Statement stmt = null;

	// stmt의 확장형 PreparedStatement타입의 객체 pstmt선언
	// sql문 안에 ?(입력할 데이터)가 있을 경우 사용
	// ?가 없으면 stmt사용
	PreparedStatement pstmt = null;

	// 조회(SELECT) 결과를 저장하기 위한 ResultSet타입의 객체 rs선언
	ResultSet rs = null;

	// 1. DB연결 메소드 connect()
	public void connect() {
		con = DBC.DBConnect();
	}

	// 2. DB해제 메소드 conClose()
	public void conClose() {
		try {
			con.close();
			System.out.println("DB접속 해제!");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// 3. 회원가입 메소드 insert()
	public void insert(FMember member) {

		// 1) sql문 작성
		String sql = "INSERT INTO FMEMBER VALUES(?, ?, ?, ?, ?, ?)";

		// INSERT INTO FMEMBER VALUES('inchoriya', '1122', '황인철','2022.03.15', '남',
		// '01041008924');
		// pstmt = con.prepareStatement(sql); 를 적고 try-catch문 만들기

		try {
			// 2) sql문에 데이터 넣기
			pstmt = con.prepareStatement(sql);

			// ?에 데이터 넣기
			pstmt.setString(1, member.getfId());
			pstmt.setString(2, member.getfPw());
			pstmt.setString(3, member.getfName());
			pstmt.setString(4, member.getfBirth());
			pstmt.setString(5, member.getfGender());
			pstmt.setString(6, member.getfPhone());

			// 3) sql문 실행
			int result = pstmt.executeUpdate();

			// 4) 결과확인
			if (result > 0) {
				System.out.println("회원가입 성공!");
			} else {
				System.out.println("회원가입 실패!");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	// 4. 회원목록 메소드 select()
	public void select() {
		// 1) sql문 작성
		// String sql = "SELECT * FROM FMEMBER";
		String sql = "SELECT FID, FPW, FNAME, TO_CHAR(FBIRTH, 'YYYY-MM-DD'), FGENDER, FPHONE FROM FMEMBER";

		// stmt = con.createStatement(); 문 작성해서 try-catch문 만들기
		try {
			// 2) 데이터 넣기 x
			stmt = con.createStatement();

			// 3) sql문 실행
			rs = stmt.executeQuery(sql);

			// 4) 결과확인
			while (rs.next()) {
				System.out.print (rs.getString(3) + "님의 정보 [ ");
				System.out.print("아이디 : " + rs.getString(1));
				System.out.print(", 비밀번호 : " + rs.getString(2));
				System.out.print(", 생년월일 : " + rs.getString(4));
				System.out.print(", 성별 : " + rs.getString(5));
				System.out.println(", 연락처 : " + rs.getString(6) + " ]");

			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				stmt.close();
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	// 5. 회원정보 수정메소드(1) 아이디체크 메소드 idCheck()
	public boolean idCheck(String fId, String fPw) {

		// 1. 메소드를 만들면 메소드 타입의 변수 선언
		// 2. 변수에는 (데이터타입에 맞는)기본값 설정
		boolean check = false;

		// 4. 내용작성
		// 1) sql문 작성하기
		String sql = "SELECT FID FROM FMEMBER WHERE FID=? AND FPW=?";

		try {
			// 2) sql문에 데이터 넣기
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, fId);
			pstmt.setString(2, fPw);

			// 3) sql문 실행
			rs = pstmt.executeQuery();

			// 4) 결과확인 : 조회결과는 0개 이거나 1개 이거나
//			if(rs.next()) {
//				check = true;
//			} else {
//				check = false;
//			}

			check = rs.next();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// 3. 만들어 준 변수를 return값으로
		return check;
	}

	public void modify(String fId, String fName) {
		// void타입은 return값이 없다.

		// 1) sql문 작성
		String sql = "UPDATE FMEMBER SET FNAME=? WHERE FID=?";

		try {
			// 2) sql문에 데이터 넣기
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, fName);
			pstmt.setString(2, fId);

			// 3) sql문 실행
			int result = pstmt.executeUpdate();

			// 4) sql문 결과확인
			if (result > 0) {
				System.out.println("회원정보 수정 성공!");
			} else {
				System.out.println("회원정보 수정 실패!");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	// 6. 회원정보 삭제 메소드 delete()
	public void delete(String fId) {
		// 1) sql문 작성
		String sql = "DELETE FROM FMEMBER WHERE FID=?";

		try {
			// 2) sql문에 데이터 넣기
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, fId);

			// 3) sql문 실행
			int result = pstmt.executeUpdate();

			// 4) sql문 결과확인
			if (result > 0) {
				System.out.println("회원정보 삭제 성공!");
			} else {
				System.out.println("회원정보 삭제 실패!");
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void searchId(String fId) {
		// 1) sql문 작성
		String sql = "SELECT * FROM FMEMBER WHERE FID=?";

		try {
			// 2) sql문 데이터 넣기
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, fId);

			// 3) sql문 실행
			rs = pstmt.executeQuery();

			// 4) sql문 결과확인
			if (rs.next()) {
				System.out.print (rs.getString(3) + "님의 정보 [ ");
				System.out.print("아이디 : " + rs.getString(1));
				System.out.print(", 비밀번호 : " + rs.getString(2));
				System.out.print(", 생년월일 : " + rs.getString(4));
				System.out.print(", 성별 : " + rs.getString(5));
				System.out.println(", 연락처 : " + rs.getString(6) + " ]");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public void searchName(String fName) {
		// 1) sql문 작성
		String sql = "SELECT * FROM FMEMBER WHERE FNAME=?";

		try {
			// 2) sql문 데이터 넣기
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, fName);

			// 3) sql문 실행
			rs = pstmt.executeQuery();

			// 4) sql문 결과확인
			while (rs.next()) {
				System.out.print (rs.getString(3) + "님의 정보 [ ");
				System.out.print("아이디 : " + rs.getString(1));
				System.out.print(", 비밀번호 : " + rs.getString(2));
				System.out.print(", 생년월일 : " + rs.getString(4));
				System.out.print(", 성별 : " + rs.getString(5));
				System.out.println(", 연락처 : " + rs.getString(6) + " ]");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	// 1) sql문 작성
	// 2) sql문 데이터 넣기
	// 3) sql문 실행
	// 4) sql문 결과확인
}

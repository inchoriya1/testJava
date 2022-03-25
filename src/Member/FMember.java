package Member;

public class FMember {
	// 클래스 3가지 요소
	// (1) 필드
	private String fId;
	private String fPw;
	private String fName;
	private String fBirth;
	private String fGender;
	private String fPhone;
	
	// (2) 메소드 : setter, getter, toString
	public String getfId() {
		return fId;
	}
	public void setfId(String fId) {
		this.fId = fId;
	}
	public String getfPw() {
		return fPw;
	}
	public void setfPw(String fPw) {
		this.fPw = fPw;
	}
	public String getfName() {
		return fName;
	}
	public void setfName(String fName) {
		this.fName = fName;
	}
	public String getfBirth() {
		return fBirth;
	}
	public void setfBirth(String fBirth) {
		this.fBirth = fBirth;
	}
	public String getfGender() {
		return fGender;
	}
	public void setfGender(String fGender) {
		this.fGender = fGender;
	}
	public String getfPhone() {
		return fPhone;
	}
	public void setfPhone(String fPhone) {
		this.fPhone = fPhone;
	}
	
	@Override
	public String toString() {
		return "FMember [fId=" + fId + ", fPw=" + fPw + ", fName=" + fName + ", fBirth=" + fBirth + ", fGender="
				+ fGender + ", fPhone=" + fPhone + "]";
	}
	
	// (3) 생성자 : 기본생성자(생략가능)
	
}

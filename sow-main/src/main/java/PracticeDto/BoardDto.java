package PracticeDto;

import java.io.Serializable;

public class BoardDto implements Serializable {
	
	private String name;
	private String age;
	private String tel;
	
	public BoardDto() {
		super();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

}

package dto;

import java.util.Date;

public class Member {
	private final Integer id;
	private String password;
	private String name;
	private String gender;
	private String zipcode;
	private String address1;
	private String address2;
	private String building;
	private String phone;
	private String mail;
	private Date date;
	
	public Member(Integer id, String password, String name, String gender, String zipcode, String address1, String address2,
			String building, String phone, String mail, Date date) {
		this.id = id;
		this.password = password;
		this.name = name;
		this.gender = gender;
		this.zipcode = zipcode;
		this.address1 = address1;
		this.address2 = address2;
		this.building = building;
		this.phone = phone;
		this.mail = mail;
		this.date = date;
	}

	public String getName() {
		return name;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getZipcode() {
		return zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

	public String getAddress1() {
		return address1;
	}

	public void setAddress1(String address1) {
		this.address1 = address1;
	}

	public String getAddress2() {
		return address2;
	}

	public void setAddress2(String address2) {
		this.address2 = address2;
	}

	public String getBuilding() {
		return building;
	}

	public void setBuilding(String building) {
		this.building = building;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public java.util.Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Integer getId() {
		return id;
	}
	
	

}

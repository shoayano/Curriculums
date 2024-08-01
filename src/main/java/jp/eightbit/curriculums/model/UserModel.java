package jp.eightbit.curriculums.model;

import jp.eightbit.curriculums.entity.Property;
import jp.eightbit.curriculums.entity.User;

public class UserModel {
	
	public UserModel(User user) {
		this.userId = user.getUserId();
		this.lastName = user.getLastName();
		this.firstName = user.getFirstName();
		this.property = user.getProperty();
		String lup = user.getProperty().getName();
		this.teacherFlg = lup.equals("管理者") || lup.equals("先生");
	}
	
	public UserModel() {}
	
	private int userId;
	private String lastName;
	private String firstName;
	private Property property;
	private boolean teacherFlg;
	
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public Property getProperty() {
		return property;
	}
	public void setProperty(Property property) {
		this.property = property;
	}
	public boolean getTeacherFlg() {
		return teacherFlg;
	}
	public void setTeacherFlg(boolean teacherFlg) {
		this.teacherFlg = teacherFlg;
	}
	
	@Override
	public String toString() {
		return "UserModel [userId=" + userId + ", lastName=" + lastName + ", firstName=" + firstName + ", property="
				+ property + ", teacherFlg=" + teacherFlg + "]";
	}

}

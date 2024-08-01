package jp.eightbit.curriculums.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "user")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int userId;
	@Column(name = "last_name")
	@NotNull
	private String lastName;
	@Column(name = "first_name")
	@NotNull
	private String firstName;
	@Column(name = "email", unique = true)
	@NotNull
	private String email;
	@Column(name = "password")
	@NotNull
	private String password;
	@OneToOne
	@JoinColumn(name = "property")
	private Property property;

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
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Property getProperty() {
		return property;
	}
	public void setProperty(Property property) {
		this.property = property;
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", lastName=" + lastName + ", firstName=" + firstName + ", email=" + email
				+ ", password=" + password + ", property=" + property + "]";
	}
	
}

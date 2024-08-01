package jp.eightbit.curriculums.entity;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jp.eightbit.curriculums.model.UserModel;

/*
 * getUserの返り値はUserModel型（email,passwordを含まない）
*/

@Entity
@Table(name = "course")
public class Course {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int courseId;
	@Column(name = "name")
	private String name;
	@OneToOne
	@JoinColumn(name = "subject")
	private Subject subject;
	@OneToOne
	@JoinColumn(name = "user")
	private User user;
	@Column(name = "start_month")
	@NotNull
	private String startMonth;
	@Column(name = "editor")
	private String editor;
	@Column(name = "edit_date")
	private LocalDate editDate;
	
	public int getCourseId() {
		return courseId;
	}
	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Subject getSubject() {
		return subject;
	}
	public void setSubject(Subject subject) {
		this.subject = subject;
	}
	/*
	 * public User getUser() { return user; }
	 */
	public UserModel getUser() {
		if(user == null) return null;
		else return new UserModel(user);
	}
	public void setUser(User user) {
		this.user = user;
	}
	public String getStartMonth() {
		return startMonth;
	}
	public void setStartMonth(String startMonth) {
		this.startMonth = startMonth;
	}
	public String getEditor() {
		return editor;
	}
	public void setEditor(String editor) {
		this.editor = editor;
	}
	public LocalDate getEditDate() {
		return editDate;
	}
	public void setEditDate(LocalDate editDate) {
		this.editDate = editDate;
	}
	
	@Override
	public String toString() {
		return "Course [courseId=" + courseId + ", name=" + name + ", subject=" + subject + ", user=" + getUser()
				+ ", startMonth=" + startMonth + ", editor=" + editor + ", editDate=" + editDate + "]";
	}
	
}

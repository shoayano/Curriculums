package jp.eightbit.curriculums.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jp.eightbit.curriculums.model.CurriculumEditModel;

@Entity
@Table(name = "curriculum")
public class Curriculum {
	
	public Curriculum() {}
	
	public Curriculum(int courseId) {
		this.courseId = courseId;
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int curriculumId;
	@Column(name = "course")
	private int courseId;
	@Column(name = "order_num")
	private int orderNum;
	@Column(name = "name")
	private String name;
	@Column(name = "textbook")
	private Integer textbookId;
	@Column(name = "textbook_name")
	private String textbookName;
	@Column(name = "page")
	private Integer page;
	@OneToOne
	@JoinColumn(name = "status")
	private Status status;
	@Column(name = "score")
	private Integer score;
	
	public int getCurriculumId() {
		return curriculumId;
	}
	public void setCurriculumId(int curriculumId) {
		this.curriculumId = curriculumId;
	}
	public int getCourseId() {
		return courseId;
	}
	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}
	public int getOrderNum() {
		return orderNum;
	}
	public void setOrderNum(int orderNum) {
		this.orderNum = orderNum;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getTextbookId() {
		return textbookId;
	}
	public void setTextbookId(Integer textbookId) {
		this.textbookId = textbookId;
	}
	public String getTextbookName() {
		return textbookName;
	}
	public void setTextbookName(String textbookName) {
		this.textbookName = textbookName;
	}
	public Integer getPage() {
		return page;
	}
	public void setPage(Integer page) {
		this.page = page;
	}
	public Status getStatus() {
		return status;
	}
	public void setStatus(Status status) {
		this.status = status;
	}
	public Integer getScore() {
		return score;
	}
	public void setScore(Integer score) {
		this.score = score;
	}
	
	
	public void setEdit(CurriculumEditModel cEditModel) {
		orderNum = cEditModel.getOrderNum();
		name = cEditModel.getName();
		textbookId = cEditModel.getTextbookId();
		textbookName = cEditModel.getTextbookName();
		page = cEditModel.getPage();
	}
	
	@Override
	public String toString() {
		return "Curriculum [curriculumId=" + curriculumId + ", courseId=" + courseId + ", orderNum=" + orderNum + ", name=" + name + ", textbookId="
				+ textbookId + ", textbookName=" + textbookName + ", page=" + page + ", status=" + status + ", score=" + score + "]";
	}
	
}

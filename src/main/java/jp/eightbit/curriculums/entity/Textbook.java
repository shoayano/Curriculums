package jp.eightbit.curriculums.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "textbook")
public class Textbook {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int textbookId;
	@Column(name = "name")
	private String name;
	@OneToOne
	@JoinColumn(name = "subject")
	private Subject subject;
	
	public int getTextbookId() {
		return textbookId;
	}
	public void setTextbookId(int textbookId) {
		this.textbookId = textbookId;
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
	
}

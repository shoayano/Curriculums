package jp.eightbit.curriculums.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "unit")
public class Unit {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int unitId;
	@Column(name = "name")
	private String name;
	@Column(name = "textbook")
	private int textbookId;
	@Column(name = "page")
	private int page;
	
	public int getUnitId() {
		return unitId;
	}
	public void setUnitId(int unitId) {
		this.unitId = unitId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getTextbookId() {
		return textbookId;
	}
	public void setTextbookId(int textbookId) {
		this.textbookId = textbookId;
	}
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	
	@Override
	public String toString() {
		return "Unit [unitId=" + unitId + ", name=" + name + ", textbookId=" + textbookId + ", page=" + page + "]";
	}
	
}

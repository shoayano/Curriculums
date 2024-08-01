package jp.eightbit.curriculums.model;

public class CurriculumEditModel {

	private boolean deleteFlg = false;
	private int curriculumId;
	private int orderNum;
	private String name;
	private Integer textbookId;
	private String textbookName;
	private Integer page;
	
	public boolean getDeleteFlg() {
		return deleteFlg;
	}
	
	public void setDeleteFlg(boolean deleteFlg) {
		this.deleteFlg = deleteFlg;
	}
	
	public int getCurriculumId() {
		return curriculumId;
	}

	public void setCurriculumId(int curriculumId) {
		this.curriculumId = curriculumId;
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

	@Override
	public String toString() {
		return "CurriculumEditModel [deleteFlg=" + deleteFlg + ", curriculumId=" + curriculumId + ", orderNum=" + orderNum + ", name=" + name
				+ ", textbookId=" + textbookId + ", textbookName=" + textbookName + ", page=" + page + "]";
	}
	
}

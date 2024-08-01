package jp.eightbit.curriculums.model;

import jp.eightbit.curriculums.entity.Status;

public class CurriculumCheckModel {

	private int curriculumId;
	private Status status;
	private Integer score;
	
	public int getCurriculumId() {
		return curriculumId;
	}
	public void setCurriculumId(int curriculumId) {
		this.curriculumId = curriculumId;
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
	
	@Override
	public String toString() {
		return "CurriculumCheckModel [curriculumId=" + curriculumId + ", status=" + status + ", score=" + score + "]";
	}
	
}

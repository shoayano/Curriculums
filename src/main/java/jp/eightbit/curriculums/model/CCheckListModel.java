package jp.eightbit.curriculums.model;

import java.util.List;

public class CCheckListModel {
	public CCheckListModel() {}
	
	public CCheckListModel(List<CurriculumCheckModel> cCheckList) {
		this.cCheckList = cCheckList;
	}
	
	private List<CurriculumCheckModel> cCheckList;

	public List<CurriculumCheckModel> getcCheckList() {
		return cCheckList;
	}

	public void setcCheckList(List<CurriculumCheckModel> cCheckList) {
		this.cCheckList = cCheckList;
	}
	
}

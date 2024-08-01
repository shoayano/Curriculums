package jp.eightbit.curriculums.model;

import java.util.List;

public class CurriculumListModel {
	public CurriculumListModel() {}
	
	public CurriculumListModel(List<CurriculumEditModel> cEditList, List<Integer> deleteIdList) {
		this.cEditList = cEditList;
		this.deleteIdList = deleteIdList;
	}

	private List<CurriculumEditModel> cEditList;
	private List<Integer> deleteIdList;

	public List<CurriculumEditModel> getcEditList() {
		return cEditList;
	}

	public void setcEditList(List<CurriculumEditModel> cEditList) {
		this.cEditList = cEditList;
	}
	
	public List<Integer> getDeleteIdList() {
		return deleteIdList;
	}
	
	public void setDeleteIdList(List<Integer> deleteIdList) {
		this.deleteIdList = deleteIdList;
	}

}

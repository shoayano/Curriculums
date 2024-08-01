package jp.eightbit.curriculums.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.eightbit.curriculums.entity.Textbook;
import jp.eightbit.curriculums.entity.Unit;
import jp.eightbit.curriculums.repository.TextbookRepository;
import jp.eightbit.curriculums.repository.UnitRepository;

@Service
public class TextbookService {

	@Autowired
	private TextbookRepository textbookRepo;
	@Autowired
	private UnitRepository unitRepo;
	
	public List<Textbook> findTextbookBySubjectId(int subjectId){
		return textbookRepo.findBySubjectSubjectId(subjectId);
	}
	
	public List<Unit> findUnitByTextbookId(int textbookId){
		return unitRepo.findByTextbookIdOrderByPageAsc(textbookId);
	}
}

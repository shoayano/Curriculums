package jp.eightbit.curriculums.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import jp.eightbit.curriculums.entity.Textbook;

@Repository
public interface TextbookRepository extends JpaRepository<Textbook, Integer> {

	List<Textbook> findBySubjectSubjectId(int subjectId);
}

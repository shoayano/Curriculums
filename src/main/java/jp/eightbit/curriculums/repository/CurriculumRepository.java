package jp.eightbit.curriculums.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import jp.eightbit.curriculums.entity.Curriculum;

@Repository
public interface CurriculumRepository extends JpaRepository<Curriculum, Integer> {

	List<Curriculum> findByCourseIdOrderByOrderNumAsc(int courseId);
}

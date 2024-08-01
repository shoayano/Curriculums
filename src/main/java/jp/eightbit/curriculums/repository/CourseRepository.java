package jp.eightbit.curriculums.repository;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import jakarta.transaction.Transactional;
import jp.eightbit.curriculums.entity.Course;

@Repository
public interface CourseRepository extends JpaRepository<Course, Integer> {

	List<Course> findByUserUserIdOrderByStartMonthAsc(int userId);
	
	@Modifying
	@Transactional
	@Query(value = "update course set editor = :editor, edit_date = :editDate where id = :courseId", nativeQuery = true)
	void updateCourse(@Param("courseId") int courseId, @Param("editor") String editor, @Param("editDate") Date editDate);
}

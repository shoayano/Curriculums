package jp.eightbit.curriculums.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import jp.eightbit.curriculums.entity.Subject;

@Repository
public interface SubjectRepository extends JpaRepository<Subject, Integer> {

}

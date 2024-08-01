package jp.eightbit.curriculums.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import jp.eightbit.curriculums.entity.Status;

@Repository
public interface StatusRepository extends JpaRepository<Status, Integer> {

}

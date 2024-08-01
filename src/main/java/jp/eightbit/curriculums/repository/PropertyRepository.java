package jp.eightbit.curriculums.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import jp.eightbit.curriculums.entity.Property;

@Repository
public interface PropertyRepository extends JpaRepository<Property, Integer> {
	List<Property> findByPropertyIdGreaterThanEqual(int Id);
}

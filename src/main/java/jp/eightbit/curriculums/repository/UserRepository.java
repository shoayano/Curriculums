package jp.eightbit.curriculums.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import jp.eightbit.curriculums.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
	User findByEmail(String email);
	
	List<User> findByPropertyPropertyIdGreaterThanEqual(int propertyId);
}

package jp.eightbit.curriculums.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import jp.eightbit.curriculums.entity.Property;
import jp.eightbit.curriculums.entity.User;
import jp.eightbit.curriculums.model.UserModel;
import jp.eightbit.curriculums.repository.PropertyRepository;
import jp.eightbit.curriculums.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	PasswordEncoder passwordEncoder;
	@Autowired
	private PropertyRepository propertyRepo;
	@Autowired
	private UserRepository userRepo;
	
	public List<Property> getAllProperty() {
		return propertyRepo.findAll();
	}
	
	
	public List<Property> getAllStudentsProperty(){
		return propertyRepo.findByPropertyIdGreaterThanEqual(3);
	}
	
	
	public void registerUser(User user, int propertyId) {
		String hashPass = passwordEncoder.encode(user.getPassword());
		user.setPassword(hashPass);
		user.setProperty(new Property(propertyId));
		userRepo.save(user);
	}
	
	
	public User findById(int id) {
		return userRepo.findById(id).get();
	}
	
	
	public List<List<UserModel>> findAllStudentsGroup() {
		List<User> studentList = userRepo.findByPropertyPropertyIdGreaterThanEqual(3);
		return getStudentsGroup(studentList);
	}
	
	
	private List<List<UserModel>> getStudentsGroup(List<User> studentList) {
		List<List<UserModel>> studentsGroup = new ArrayList<>();
		for(int i=1; i<=6; i++) {
			studentsGroup.add(new ArrayList<UserModel>());
		}

		for(User student : studentList) {
			UserModel studentModel = new UserModel(student);
			switch (student.getProperty().getName()) {
			case "中１" :
				studentsGroup.get(0).add(studentModel);
				break;
			case "中２" :
				studentsGroup.get(1).add(studentModel);
				break;
			case "中３" :
				studentsGroup.get(2).add(studentModel);
				break;
			case "高１" :
				studentsGroup.get(3).add(studentModel);
				break;
			case "高２" :
				studentsGroup.get(4).add(studentModel);
				break;
			case "高３" :
				studentsGroup.get(5).add(studentModel);
				break;
			}
		}
		
		return studentsGroup;
	}

}

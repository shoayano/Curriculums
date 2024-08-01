package jp.eightbit.curriculums.service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.eightbit.curriculums.entity.Course;
import jp.eightbit.curriculums.entity.Curriculum;
import jp.eightbit.curriculums.entity.Status;
import jp.eightbit.curriculums.entity.Subject;
import jp.eightbit.curriculums.model.CCheckListModel;
import jp.eightbit.curriculums.model.CurriculumCheckModel;
import jp.eightbit.curriculums.model.CurriculumEditModel;
import jp.eightbit.curriculums.model.CurriculumListModel;
import jp.eightbit.curriculums.repository.CourseRepository;
import jp.eightbit.curriculums.repository.CurriculumRepository;
import jp.eightbit.curriculums.repository.StatusRepository;
import jp.eightbit.curriculums.repository.SubjectRepository;
import jp.eightbit.curriculums.repository.UserRepository;

@Service
public class CurriculumService {
	
	@Autowired
	private SubjectRepository subjectRepo;
	@Autowired
	private CourseRepository courseRepo;
	@Autowired
	private UserRepository userRepo;
	@Autowired
	private CurriculumRepository curriculumRepo;
	@Autowired
	private StatusRepository statusRepo;
	
	public List<Subject> getAllSubject() {
		return subjectRepo.findAll();
	}
	
	
	public List<Status> getAllStatus() {
		return statusRepo.findAll();
	}
	
	
	public List<Course> findCourseByUserId(int userId) {
		return courseRepo.findByUserUserIdOrderByStartMonthAsc(userId);
	}
	
	
	public Course findCourseById(int id) {
		return courseRepo.findById(id).get();
	}
	
	
	public int makeCourse(Course course, int subjectId, int studentId) {
		course.setSubject(subjectRepo.findById(subjectId).get());
		course.setUser(userRepo.findById(studentId).get());
		course.setEditDate(LocalDate.now());
		int courseId = courseRepo.save(course).getCourseId();
		return courseId;
	}
	
	
	public List<Curriculum> findCurriculumByCourseId(int courseId) {
		return curriculumRepo.findByCourseIdOrderByOrderNumAsc(courseId);
	}
	
	
	public void checkCurriculumList(CCheckListModel cCheckListModel) {
		
		if(cCheckListModel.getcCheckList() == null) return;
				
		Map<Integer, CurriculumCheckModel> cCheckMap = cCheckListModel.getcCheckList().stream()
																.collect(Collectors.toMap(c -> c.getCurriculumId(), c -> c));
		List<Integer> idList = new ArrayList<>(cCheckMap.keySet());
		List<Curriculum> cList = curriculumRepo.findAllById(idList);
		
		cList.forEach(c -> {
			CurriculumCheckModel cCheckModel = cCheckMap.get(c.getCurriculumId());
			c.setStatus(cCheckModel.getStatus());
			c.setScore(cCheckModel.getScore());
		});
		
		if(cList != null) curriculumRepo.saveAll(cList);
	}

	
	public void updateCurriculumList(CurriculumListModel cListModel, int courseId, String loginUserName) {
		
		List<Integer> deleteIdList = cListModel.getDeleteIdList();
		if(deleteIdList != null) curriculumRepo.deleteAllById(deleteIdList);
		
		if(cListModel.getcEditList() == null) {
			courseRepo.updateCourse(courseId, loginUserName, Date.valueOf(LocalDate.now()));
			return;
		}
		
		Status newStatus = statusRepo.findById(1).get();
		
		Map<Integer, CurriculumEditModel> cEditMap = new HashMap<>();
		List<Curriculum> cList = new ArrayList<>();
		List<Integer> idList = new ArrayList<>();
		
		cListModel.getcEditList().forEach(e -> {
			if(e.getDeleteFlg()) return;
			int id = e.getCurriculumId();
			if(id == 0) {
				Curriculum c = new Curriculum(courseId);
				c.setEdit(e);
				c.setStatus(newStatus);
				cList.add(c);
			}else {
				idList.add(id);
				cEditMap.put(id, e);
			}
		});
		
		List<Curriculum> updateList = curriculumRepo.findAllById(idList);
		
		updateList.forEach(c -> {
			c.setEdit(cEditMap.get(c.getCurriculumId()));
			cList.add(c);
		});
		
		if(cList != null) curriculumRepo.saveAll(cList);
		
		courseRepo.updateCourse(courseId, loginUserName, Date.valueOf(LocalDate.now()));
	}
}

package jp.eightbit.curriculums.controller;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import jp.eightbit.curriculums.entity.Course;
import jp.eightbit.curriculums.model.UserModel;
import jp.eightbit.curriculums.service.CurriculumService;
import jp.eightbit.curriculums.service.UserDetailsImpl;
import jp.eightbit.curriculums.service.UserService;

@Controller
public class StudentController {
	private final UserService userService;
	private final CurriculumService curriculumService;
	
	@Autowired
	public StudentController(UserService userService, CurriculumService curriculumService) {
		this.userService = userService;
		this.curriculumService = curriculumService;
	}
	
	
	@GetMapping("/studentIndex")
	public String routeToStudentIndex (@AuthenticationPrincipal UserDetailsImpl loginUser, Model model) {
		
		System.out.println("test Index");
		System.out.println("login user: " + loginUser.getUser());
		
		List<List<UserModel>> studentsGroup = userService.findAllStudentsGroup();
		
		model.addAttribute("studentsProperty", userService.getAllStudentsProperty());
		model.addAttribute("studentsGroup", studentsGroup);


		/*
		 * model.addAttribute("j1List", studentsGroup.get(0));
		 * model.addAttribute("j2List", studentsGroup.get(1));
		 * model.addAttribute("j3List", studentsGroup.get(2));
		 * model.addAttribute("h1List", studentsGroup.get(3));
		 * model.addAttribute("h2List", studentsGroup.get(4));
		 * model.addAttribute("h3List", studentsGroup.get(5));
		 */


		model.addAttribute("loginUser", new UserModel(loginUser.getUser()));

		System.out.println(model + "\n");

		return "studentIndex";
	}
	
	
	@GetMapping("/student/{id}")
	public String routeToStudent (@AuthenticationPrincipal UserDetailsImpl loginUser, @PathVariable("id") int id, Model model) {
		
		System.out.println("test student");
		System.out.println("login user: " + loginUser.getUser());
		
		List<Course> courseList = curriculumService.findCourseByUserId(id);
		List<String> startList = new ArrayList<>();
		courseList.forEach((course) -> {
			if(!startList.contains(course.getStartMonth())) startList.add(course.getStartMonth());
		});
		
		model.addAttribute("loginUser", new UserModel(loginUser.getUser()));
		model.addAttribute("student", new UserModel(userService.findById(id)));
		model.addAttribute("courseList", courseList);
		model.addAttribute("startList", startList);
		model.addAttribute("subjectList", curriculumService.getAllSubject());
		
		System.out.println(model + "\n");
		
		return "student";
	}
	
	
	@GetMapping("/mypage")
	public String routeToMypage (@AuthenticationPrincipal UserDetailsImpl loginUser, Model model) {
		
		System.out.println("test mypage");
		System.out.println("login user: " + loginUser.getUser());
		
		UserModel loginUserModel = new UserModel(loginUser.getUser());
		List<Course> courseList = curriculumService.findCourseByUserId(loginUser.getUserId());
		List<String> startList = new ArrayList<>();
		courseList.forEach((course) -> {
			if(!startList.contains(course.getStartMonth())) startList.add(course.getStartMonth());
		});
		
		model.addAttribute("loginUser", loginUserModel);
		model.addAttribute("student", loginUserModel);
		model.addAttribute("courseList", courseList);
		model.addAttribute("startList", startList);
		
		System.out.println(model + "\n");
		
		return "student";
	}
}

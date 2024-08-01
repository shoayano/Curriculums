package jp.eightbit.curriculums.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import jp.eightbit.curriculums.entity.Course;
import jp.eightbit.curriculums.entity.Curriculum;
import jp.eightbit.curriculums.entity.Status;
import jp.eightbit.curriculums.entity.Textbook;
import jp.eightbit.curriculums.entity.Unit;
import jp.eightbit.curriculums.model.CCheckListModel;
import jp.eightbit.curriculums.model.CurriculumCheckModel;
import jp.eightbit.curriculums.model.CurriculumEditModel;
import jp.eightbit.curriculums.model.CurriculumListModel;
import jp.eightbit.curriculums.model.UserModel;
import jp.eightbit.curriculums.service.CurriculumService;
import jp.eightbit.curriculums.service.TextbookService;
import jp.eightbit.curriculums.service.UserDetailsImpl;

@Controller
public class CurriculumController {
	private final CurriculumService curriculumService;
	private final TextbookService textbookService;
	
	@Autowired
	public CurriculumController(CurriculumService curriculumService, TextbookService textbookService) {
		this.curriculumService = curriculumService;
		this.textbookService = textbookService;
	}

	
	@PostMapping("/makeCourse")
	public String makeCourse(@ModelAttribute Course course, @RequestParam("subjectId") int subjectId,
								@RequestParam("studentId") int studentId) {
		
		System.out.println("test make course");
		System.out.println(course);
		System.out.println(subjectId);
		System.out.println(studentId);
		System.out.println();
		
		int courseId = curriculumService.makeCourse(course, subjectId, studentId);
		
		return "redirect:/" + courseId + "/curriculum/edit";
	}
	
	
	@GetMapping("/{courseId}/curriculum")
	public String routeToCurriculumList(@AuthenticationPrincipal UserDetailsImpl loginUser,
											@PathVariable("courseId") int courseId, Model model) {
		
		System.out.println("test curriculum list");
		
		Course course = curriculumService.findCourseById(courseId);
		
		if( isIncorrectUser(loginUser, course) ) return "redirect:/mypage";
		
		List<Curriculum> cList = curriculumService.findCurriculumByCourseId(courseId);
		model.addAttribute("course", course);
		model.addAttribute("cList", cList);
		CCheckListModel cCheckListModel = new CCheckListModel(new ArrayList<CurriculumCheckModel>());
		model.addAttribute("cCheckListModel", cCheckListModel);
		
		List<Status> statusList = curriculumService.getAllStatus();
		model.addAttribute("statusList", statusList);
		model.addAttribute("loginUser", new UserModel(loginUser.getUser()));
		
		System.out.println(cList);
		System.out.println();
		
		return "curriculum";
	}
	
	
	@PostMapping("/{courseId}/curriculum/check")
	public String checkCurriculum(@PathVariable("courseId") int courseId,
									@ModelAttribute("cCheckListModel") CCheckListModel cCheckListModel) {
		
		System.out.println("test curriculum check");
		System.out.println(cCheckListModel.getcCheckList());
				
		curriculumService.checkCurriculumList(cCheckListModel);
		
		System.out.println();
		
		return "redirect:/" + courseId + "/curriculum";
	}
	
	
	@GetMapping("/{courseId}/curriculum/edit")
	public String routeToCurriculumEdit(@AuthenticationPrincipal UserDetailsImpl loginUser,
											@PathVariable("courseId") int courseId, Model model) {
		
		System.out.println("test curriculum edit");
		
		Course course = curriculumService.findCourseById(courseId);
		
//		if( isIncorrectUser(loginUser, course) ) return "redirect:/mypage";
		
		List<Curriculum> cList = curriculumService.findCurriculumByCourseId(courseId);
		model.addAttribute("course", course);
		model.addAttribute("cList", cList);
		CurriculumListModel cListModel = new CurriculumListModel(new ArrayList<CurriculumEditModel>(), new ArrayList<Integer>());
		model.addAttribute("cListModel", cListModel);
		
		List<Textbook> textbookList = textbookService.findTextbookBySubjectId(course.getSubject().getSubjectId());
		model.addAttribute("textbookList", textbookList);
		model.addAttribute("loginUser", new UserModel(loginUser.getUser()));
		
		System.out.println(model);
		System.out.println();
		
		return "curriculumEdit";
	}
	
	
	@PostMapping("/{courseId}/curriculum/update")
	public String updateCurriculum(@AuthenticationPrincipal UserDetailsImpl loginUser, @PathVariable("courseId") int courseId,
									@ModelAttribute("cListModel") CurriculumListModel cListModel) {
		
		System.out.println("test curriculum update");
		System.out.println(cListModel.getcEditList());
		System.out.println(cListModel.getDeleteIdList());
		
		curriculumService.updateCurriculumList(cListModel, courseId, loginUser.getUser().getLastName());
		
		System.out.println();
		
		return "redirect:/" + courseId + "/curriculum";
	}
	
	
	@GetMapping("/unit/json")
	@ResponseBody
	public List<Unit> getUnitList(@RequestParam("textbookId") int textbookId) {
		
		System.out.println("test unit json");
		
		List<Unit> unitList = textbookService.findUnitByTextbookId(textbookId);
		
		System.out.println(unitList + "\n");
		return unitList;
	}
	
	
	
	private boolean isIncorrectUser(UserDetailsImpl loginUser, Course course) {
		String lup = loginUser.getPropertyName();
		int lui = loginUser.getUserId();
		int courseUserId = course.getUser().getUserId();
		if( !(lup.equals("管理者")) && !(lup.equals("先生")) && lui != courseUserId ) {
			return true;
		}else {
			return false;
		}
	}
}

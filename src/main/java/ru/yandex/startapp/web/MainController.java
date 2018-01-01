package ru.yandex.startapp.web;

import ru.yandex.startapp.service.*;

import ru.yandex.startapp.domain.Task;
import ru.yandex.startapp.domain.Admin;
import ru.yandex.startapp.domain.Master;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.IOException;
import java.util.List;

import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ModelAttribute;


//@CrossOrigin
@Controller
@RequestMapping("/")
public class MainController {
	
	
	@Autowired
	private MasterService masterServise; 
	@Autowired
	private TaskService taskServise;
	@Autowired
	private AdminService adminServise;
	
	@RequestMapping("/")
	public String main(Model ui){
		return "redirect:/index";
	}
	
	@RequestMapping(value="/index", method= RequestMethod.GET)
	public String taskListGet(Model ui){
		ui.addAttribute("newTask", new Task());
		
		return "/index";
	}
		
	@RequestMapping(value="index/form", method= RequestMethod.POST)
	public @ResponseBody Task taskListPost(Task task, BindingResult br,  Model ui){
		//System.out.println(br.toString() + "BR ");
		//System.out.println(task.toString()  + "task ");
		taskServise.addTask(task);
		ui.addAttribute("newTask", task);	
		
		return task;
	}
	
	
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value="/test",method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody Task getUserInfo() {
    	List<Task> taskList = taskServise.listTask();
		Task task = taskList.get(0);
		return task;
    }
	
	@RequestMapping(value="/service", method= RequestMethod.GET)
	public String masterListGet(Model ui){
		ui.addAttribute("newMaster", new Master());

		return "/service";
	}
		
	@RequestMapping(value="service/form", method= RequestMethod.POST)
	public String masterListPost(Master master, BindingResult br,  Model ui){
		System.out.println(br.toString() + "BR ");
		System.out.println(master.toString()  + "task ");
		
		
		masterServise.addMaster(master);
		
		
		ui.addAttribute("newMaster", master);
			
		return "/admin";
	}
	
	@RequestMapping(value="/admin", method= RequestMethod.GET)
	public String getAdmin(Model ui){
		ui.addAttribute("newAdmin", new Admin());

		return "/admin";
	}
		
	@RequestMapping(value="admin/form", method= RequestMethod.POST)
	public String postAdmin(Admin admin, BindingResult br,  Model ui){
		System.out.println(br.toString() + "BR ");
		//System.out.println(task.toString()  + "task ");
		boolean verifyAdmin = adminServise.verifyAdmin(admin);
		ui.addAttribute("newAdmin", admin);	
		if(verifyAdmin) {
			return "/success";
		}
		else {
			return "/wrong";
		}
		
	}
	
	@RequestMapping(value="/success", method= RequestMethod.GET)
	public String getSuccess(Model ui){
		ui.addAttribute("existingMasters", new Master());

		return "/success";
	}
	
	@RequestMapping(value="success/form", method= RequestMethod.POST)
	public String successListPost(Master master, BindingResult br,  Model ui){	
		
		
		List masterList =  masterServise.listMaster();
		
		
		ui.addAttribute("newMasterList", masterList);
			
		return "/success";
	}
	
}





package ru.yandex.startapp.web;

import ru.yandex.startapp.service.*;
import ru.yandex.startapp.domain.*;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;

import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;


@Controller
public class WebController {

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
	
}

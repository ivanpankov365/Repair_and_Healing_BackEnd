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
import org.springframework.web.bind.annotation.RequestBody;
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

@Controller
@RequestMapping("/")
public class MainController {

	@Autowired
	private MasterService masterServise;
	@Autowired
	private TaskService taskServise;
	@Autowired
	private AdminService adminServise;

	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "/tasklist", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody List<Task> getTaskList() {
		return taskServise.listTask();
	}

	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "/masterlist", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody List<Master> getmasterList() {
		return masterServise.listMaster();
	}

	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "/addnewtask", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public Task addNewTask(@RequestBody Task task) {
		taskServise.addTask(task);
		return task;
	}

	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "/addnewmaster", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public Master addNewTask(@RequestBody Master master) {
		masterServise.addMaster(master);
		return master;
	}

	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "/deletetask", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public Task deleteTask(@RequestBody Task task) {
		taskServise.removeTask(task.getTaskId());
		return task;
	}

	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "/checkadmin", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public boolean checkAdmin(@RequestBody Admin admin) {
		return adminServise.verifyAdmin(admin);
	}

	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "/checkmaster", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public Master checkMaster(@RequestBody Master master) {
		return masterServise.verifyMaster(master);
	}

	@RequestMapping("/")
	public String main(Model ui) {
		return "redirect:/index";
	}

}

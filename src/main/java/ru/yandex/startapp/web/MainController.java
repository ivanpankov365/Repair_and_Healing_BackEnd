package ru.yandex.startapp.web;

import ru.yandex.startapp.*;
import ru.yandex.startapp.service.*;
import ru.yandex.startapp.domain.*;

import java.sql.SQLException;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller
@RequestMapping("/")
public class MainController {
	
	
	@RequestMapping("/")
	public String main(Model ui){
		return "redirect:/index";
	}
	

	
	@RequestMapping(value="/service", method= RequestMethod.GET)
	public String taskListGetService(Model ui){
		ui.addAttribute("newMaster", new Master());

		return "/service";
	}
		
	@RequestMapping(value="service/form", method= RequestMethod.POST)
	public String taskListPostService(Master master, BindingResult br,  Model ui) throws SQLException{
		System.out.println(br.toString() + "BR ");
		System.out.println(master.toString()  + "task ");
		
		MasterService masterServise = new MasterServiceImpl();
		masterServise.addMaster(master);
		
		
		ui.addAttribute("newMaster", master);
			
		return "/service";
	}
	
	
}





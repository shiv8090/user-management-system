package in.ashokit.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import ch.qos.logback.core.model.Model;
import in.ashokit.entity.user;
import in.ashokit.repo.userrepo;
import in.ashokit.service.userservice;
import jakarta.validation.Valid;

@Controller
public class usercontroller {
	@Autowired
	private userrepo repo;
	
@Autowired	
private userservice uservice;

 public usercontroller(userservice uservice)
 {
		 this.uservice=uservice;
 }
 @GetMapping("/")
 public ModelAndView register()
 {
	 ModelAndView mav=new ModelAndView();
	 mav.addObject("user",new user());
	
	 mav.setViewName("registration");
	 return mav;
 }

 @GetMapping("/login")
 public ModelAndView showlogin()
 {
	 ModelAndView mav=new ModelAndView();
	 mav.addObject("user",new user());
	 mav.setViewName("login");
	 return mav;
 }
 
 @PostMapping("/login")
 public ModelAndView handleregistration( @ModelAttribute("user")  @Valid user u ,BindingResult result)
 {
	 ModelAndView mav=new ModelAndView();
	 if(result.hasErrors())
	 {
		 mav.setViewName("registartion");
		 mav.addObject("user",new user());
		 return mav;
	 }else
	 {
		 boolean saveduser= uservice.saveuser(u);
			mav.setViewName("login");
			 mav.addObject("user",new user());

			if(saveduser)
			{
				mav.addObject("msg","registration sucessfull");
				return mav;
			}
			else {
				mav.addObject("emsg","registration unsucessfull");
				return  mav;
			}
	 }
	

	
	
 }
 @PostMapping("/dashboard")
public ModelAndView handlelogin(user u)
{
	ModelAndView mav=new ModelAndView();
	mav.addObject("user", new user());
	user existinguser = repo.findByUemail(u.getUemail());
	
	
	if(existinguser==null|| !((user) existinguser).getUpass().equals(u.getUpass()))
	{
	  mav.addObject("emsg", "Invalid email or password");
	  mav.setViewName("login");
	}
	else
	{
		mav.setViewName("dashboard");
	}
	return mav;
}
 @GetMapping("/dashboard")
 public  ModelAndView dashbaord()
{
	ModelAndView mav=new ModelAndView();
	mav.addObject("msg", "dashboard");
	mav.setViewName("dashboard");
	  return new ModelAndView("dashboard");
}
}
 
 
 




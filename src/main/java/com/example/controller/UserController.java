package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.entity.User;
import com.example.service.UserService;

@Controller
public class UserController {
	@Autowired
	  UserService userService;
	  /**
	   * ユーザー情報一覧画面を表示
	   * @param model Model
	   * @return ユーザー情報一覧画面のHTML
	   */
	  @GetMapping("/list")

	  public String displayList(Model model) {
	    List<User> userlist = userService.searchAll();

	    	System.out.println(userlist.get(0).getId());
	    	System.out.println(userlist.get(0).getName());
	    	System.out.println(userlist.get(0).getEmail());
	    	System.out.println(userlist.get(0).getPassword());


	    model.addAttribute("userlist", userlist);
	    return "list";
	  }
	  @GetMapping("/loginForm")
	    public String getLogin() {
	        return "loginForm";
	    }
	  @GetMapping("/create")
	    public String createUser(@ModelAttribute("user") User user) {
	        return "createUser";
	    }
	  @PostMapping("/create")
	    public String addUser(@RequestParam("name") String name
	                              , @RequestParam("email") String email, @RequestParam("password") String password) {
	        // データを挿入します
	        this.userService.insert(name, email,password);
	        // 一覧ページにリダイレクト(後述)します
	        return "redirect:/list";
	    }
}

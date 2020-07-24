package com.shopping.bbs.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.shopping.bbs.dto.userDTO;
import com.shopping.bbs.dto.userDTO;
import com.shopping.bbs.form.userForm;
import com.shopping.bbs.form.userForm;
import com.shopping.bbs.service.userService;
 
@Controller
@RequestMapping(value = "/user")
public class userController {
 
    @Autowired
    private userService userService;
   
    //�α��� page
    @RequestMapping( value = "/login")
    public String login(HttpServletRequest request, HttpServletResponse response) throws Exception{
        
        return "user/login";
    }
    
    //�α���
    @RequestMapping(value = "/Login")
    @ResponseBody
    public userDTO Login(HttpServletRequest request, HttpServletResponse response, userForm userForm) throws Exception {
        
    	userDTO userDTO = userService.Login(userForm);
        
    	return userDTO;
    }
    
    //�α׾ƿ�
    @RequestMapping(value = "/Logout")
    @ResponseBody
    public userDTO Logout(HttpServletRequest request, HttpServletResponse response, userForm userForm) throws Exception {
        
    	userDTO userDTO = userService.Login(userForm);
        
    	return userDTO;
    }
    
    //ȸ������ page
    @RequestMapping( value = "/join")
    public String join(HttpServletRequest request, HttpServletResponse response) throws Exception{
        
        return "user/join";
    }
    
    //ȸ�� ����// ȸ���ߺ�Ȯ��
    @RequestMapping(value = "/ExistUser")
    @ResponseBody
    public int ExistUser(HttpServletRequest request, HttpServletResponse response, userForm userForm) throws Exception {
    	int result = userService.ExistUser(userForm);
    	
    	return result;
    }
    
    //ȸ������
    @RequestMapping(value = "/Join")
    @ResponseBody
    public userDTO Join(HttpServletRequest request, HttpServletResponse response, userForm userForm) throws Exception {
        
    	userDTO userDTO = userService.Join(userForm);
        
    	return userDTO;
    }
    
    //ȸ�� ��� page
    @RequestMapping( value = "/userList")
    public String userList(HttpServletRequest request, HttpServletResponse response) throws Exception{
        
        return "user/userList";
    }
 
    //ȸ�� ���
    @RequestMapping(value = "/UserList")
    @ResponseBody
    public List<userDTO> UserList(HttpServletRequest request, HttpServletResponse response, userForm userForm) throws Exception {
        
    	List<userDTO> userDTO = userService.UserList(userForm);
        
    	return userDTO;
    }

    
    //ȸ�� ���� //ȸ��Ż��
    @RequestMapping(value = "/UserDelete")
    @ResponseBody
    public userDTO UserDelete(HttpServletRequest request, HttpServletResponse response, userForm userForm) throws Exception {
        
    	userDTO userDTO = userService.UserDelete(userForm);
        
    	return userDTO;
    }
    
    //ȸ�� ���� page
    @RequestMapping( value = "/userUpdate")
    public String userUpdate(HttpServletRequest request, HttpServletResponse response) throws Exception{
        
        return "user/userUpdate";
    }
 
    //ȸ�� ����
    @RequestMapping(value = "/UserUpdate")
    @ResponseBody
    public userDTO UserUpdate(HttpServletRequest request, HttpServletResponse response, userForm userForm) throws Exception {
        
    	userDTO userDTO = userService.UserUpdate(userForm);
        
    	return userDTO;
    }
    
    
}
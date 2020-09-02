package com.shopping.bbs.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.shopping.bbs.dto.pagingDTO;
import com.shopping.bbs.dto.userDTO;
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
        HttpSession session = request.getSession();
    	userDTO userDTO = userService.Login(userForm);
    	if(userDTO == null) {
    		session.setAttribute("userForm", null);
    	}
    	else {
    		session.setAttribute("userForm", userDTO);
    	}
    	return userDTO;
    }
    
    //�α׾ƿ�
    @RequestMapping(value = "/Logout")
    public String Logout(HttpServletRequest request, HttpServletResponse response, userForm userForm) throws Exception {
        
    	HttpSession session = request.getSession();
        session.invalidate();
    	
        String referer = request.getHeader("Referer");
        
    	return "redirect:"+referer;
    }
    
    //ȸ������ page
    @RequestMapping( value = "/join")
    public String join(HttpServletRequest request, HttpServletResponse response) throws Exception{
        
        return "user/join";
    }
    
    // ȸ���ߺ�Ȯ��
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
    @RequestMapping( value = "/userView")
    public String userView(HttpServletRequest request, HttpServletResponse response) throws Exception{
        
        return "user/userView";
    }
    
    //ȸ�� ��
    @RequestMapping(value = "/UserView")
    @ResponseBody
    public userDTO UserView(HttpServletRequest request, HttpServletResponse response, userForm userForm) throws Exception {
    	userDTO userDTO = userService.UserView(userForm);
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
    	//���������� ���� ���ù� ��
    	int postNum = 10;
    	//��ϴ� ù������(bbsID)
    	int displayPost = (userForm.getUserNum()-1)*postNum;
    	userForm.setDisplayPost(displayPost);
    	userForm.setPostNum(postNum);
    	
    	List<userDTO> userDTO = userService.UserList(userForm);
        
    	return userDTO;
    }

    //����list ����¡
    @RequestMapping(value = "/UserPaging")
    @ResponseBody
    public pagingDTO BbsPaging(HttpServletRequest request, HttpServletResponse response,userForm userForm) throws Exception {    	
    	//���������� ���� ���ù� ��
    	int postNum = 10;    	
    	//ī�װ��� �Խù� ����
    	int UserTotal = userService.UserTotal(userForm);
    	//�ѹ��� ǥ���� ����¡ ��ȣ ����
    	int pageNum_cnt = 10;
    	//ǥ�õǴ� ������ ��ȣ �� ������ ��ȣ
    	int endPageNum = (int)(Math.ceil((double)userForm.getUserNum()/(double)pageNum_cnt)*pageNum_cnt);
    	//ǥ�õǴ� ������ ��ȣ �� ù��° ��ȣ
    	int startPageNum = endPageNum - (pageNum_cnt -1);
    	//������ ��ȣ ����
    	int endPageNum_tmp = (int)(Math.ceil((double)UserTotal/(double)postNum));
    	
    	if(endPageNum > endPageNum_tmp) {
    		endPageNum=endPageNum_tmp;
    	}
    	
    	boolean prev = startPageNum == 1 ? false : true;
    	boolean next = endPageNum * pageNum_cnt >= UserTotal ? false : true;
    	
    	pagingDTO pagingDTO = new pagingDTO();
    	pagingDTO.setStartPageNum(startPageNum);
    	pagingDTO.setEndPageNum(endPageNum);
    	pagingDTO.setPrev(prev);
    	pagingDTO.setNext(next);
    	
    	return pagingDTO;
    }  
    
    //ȸ�� ���� //ȸ��Ż��
    @RequestMapping(value = "/UserDelete")
    @ResponseBody
    public userDTO UserDelete(HttpServletRequest request, HttpServletResponse response, userForm userForm) throws Exception {
        
    	userDTO userDTO = userService.UserDelete(userForm);
    	HttpSession session = request.getSession();
        session.invalidate();
        
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
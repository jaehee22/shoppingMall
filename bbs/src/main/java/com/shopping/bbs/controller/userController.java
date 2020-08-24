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
   
    //로그인 page
    @RequestMapping( value = "/login")
    public String login(HttpServletRequest request, HttpServletResponse response) throws Exception{
        
        return "user/login";
    }
    
    //로그인
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
    
    //로그아웃
    @RequestMapping(value = "/Logout")
    public String Logout(HttpServletRequest request, HttpServletResponse response, userForm userForm) throws Exception {
        
    	HttpSession session = request.getSession();
        session.invalidate();
    	
        String referer = request.getHeader("Referer");
        
    	return "redirect:"+referer;
    }
    
    //회원가입 page
    @RequestMapping( value = "/join")
    public String join(HttpServletRequest request, HttpServletResponse response) throws Exception{
        
        return "user/join";
    }
    
    // 회원중복확인
    @RequestMapping(value = "/ExistUser")
    @ResponseBody
    public int ExistUser(HttpServletRequest request, HttpServletResponse response, userForm userForm) throws Exception {
    	int result = userService.ExistUser(userForm);
    	
    	return result;
    }
    
        
    //회원가입
    @RequestMapping(value = "/Join")
    @ResponseBody
    public userDTO Join(HttpServletRequest request, HttpServletResponse response, userForm userForm) throws Exception {
        
    	userDTO userDTO = userService.Join(userForm);
        
    	return userDTO;
    }

    //회원 목록 page
    @RequestMapping( value = "/userView")
    public String userView(HttpServletRequest request, HttpServletResponse response) throws Exception{
        
        return "user/userView";
    }
    
    //회원 뷰
    @RequestMapping(value = "/UserView")
    @ResponseBody
    public userDTO UserView(HttpServletRequest request, HttpServletResponse response, userForm userForm) throws Exception {
    	userDTO userDTO = userService.UserView(userForm);
    	return userDTO;
    }
    
    //회원 목록 page
    @RequestMapping( value = "/userList")
    public String userList(HttpServletRequest request, HttpServletResponse response) throws Exception{
        
        return "user/userList";
    }
    
    int pageNum = 0;
    
    //회원 목록
    @RequestMapping(value = "/UserList")
    @ResponseBody
    public List<userDTO> UserList(HttpServletRequest request, HttpServletResponse response, userForm userForm) throws Exception {
        
    	//카테고리별 게시물 갯수
    	int UserTotal = userService.UserTotal(userForm);
    	//한페이지당 나올 개시물 수
    	int postNum = 10;
    	//총 페이징 번호 수
    	pageNum = (int)Math.ceil((double)UserTotal/postNum);
    	//블록당 첫페이지(bbsID)
    	int displayPost = (userForm.getNum()-1)*postNum;
    	userForm.setDisplayPost(displayPost);
    	userForm.setPostNum(postNum);
    	
    	List<userDTO> userDTO = userService.UserList(userForm);
        
    	return userDTO;
    }

    //유저list 페이징
    @RequestMapping(value = "/UserPaging")
    @ResponseBody
    public pagingDTO BbsPaging(HttpServletRequest request, HttpServletResponse response,userForm userForm) throws Exception {    	
    	    	
    	//카테고리별 게시물 갯수
    	int UserTotal = userService.UserTotal(userForm);
    	//한번에 표시할 페이징 번호 개수
    	int pageNum_cnt = 10;
    	//표시되는 페이지 번호 중 마지막 번호
    	int endPageNum = (int)(Math.ceil((double)userForm.getNum()/(double)pageNum_cnt)*pageNum_cnt);
    	//표시되는 페이지 번호 중 첫번째 번호
    	int startPageNum = endPageNum - (pageNum_cnt -1);
    	//마지막 번호 재계산
    	int endPageNum_tmp = (int)(Math.ceil((double)UserTotal/(double)pageNum_cnt));
    	
    	if(endPageNum > endPageNum_tmp) {
    		endPageNum=endPageNum_tmp;
    	}
    	
    	boolean prev = startPageNum == 1 ? false : true;
    	boolean next = endPageNum * pageNum_cnt >= UserTotal ? false : true;
    	
    	pagingDTO pagingDTO = new pagingDTO();
    	pagingDTO.setPageNum(pageNum);
    	pagingDTO.setStartPageNum(startPageNum);
    	pagingDTO.setEndPageNum(endPageNum);
    	pagingDTO.setPrev(prev);
    	pagingDTO.setNext(next);
    	
    	return pagingDTO;
    }  
    
    //회원 삭제 //회원탈퇴
    @RequestMapping(value = "/UserDelete")
    @ResponseBody
    public userDTO UserDelete(HttpServletRequest request, HttpServletResponse response, userForm userForm) throws Exception {
        
    	userDTO userDTO = userService.UserDelete(userForm);
    	HttpSession session = request.getSession();
        session.invalidate();
        
    	return userDTO;
    }
    
    //회원 수정 page
    @RequestMapping( value = "/userUpdate")
    public String userUpdate(HttpServletRequest request, HttpServletResponse response) throws Exception{
        
        return "user/userUpdate";
    }
 
    //회원 수정
    @RequestMapping(value = "/UserUpdate")
    @ResponseBody
    public userDTO UserUpdate(HttpServletRequest request, HttpServletResponse response, userForm userForm) throws Exception {
        
    	userDTO userDTO = userService.UserUpdate(userForm);
        
    	return userDTO;
    }
    
    
}
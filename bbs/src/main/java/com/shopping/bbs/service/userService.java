package com.shopping.bbs.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
 
import com.shopping.bbs.dao.userDAO;
import com.shopping.bbs.dto.userDTO;
import com.shopping.bbs.form.userForm;

@Service
public class userService {
 
    @Autowired
    private userDAO userDAO;
 
    //로그인
    public userDTO Login(userForm userForm) throws Exception {
    	
		return userDAO.Login(userForm);
	}
    
    //회원가입
    public userDTO Join(userForm userForm) throws Exception{
    	
    	userDTO userDTO = new userDTO();
    	    	
    	int cnt = userDAO.Join(userForm);
    	
    	if(cnt>0) {
    		userDTO.setResult("SUCCESS");
    	}
    	
    	return userDTO;
    }
    
    //회원중복확인
    public int ExistUser(userForm userForm) throws Exception {
    	
    	int result = userDAO.ExistUser(userForm);
    	return result;
    }
    
    //회원뷰
	public userDTO UserView(userForm userForm) throws Exception {
	    	
		userDTO userDTO = new userDTO();
	    	
	    userDTO = userDAO.UserView(userForm);
	    	
	    return userDTO;
	}
    
    
    //회원 리스트
    public List<userDTO> UserList(userForm userForm) throws Exception {
    	 
        return userDAO.UserList(userForm);
    }
    //총 유저 
    public int UserTotal(userForm userForm) throws Exception{
    	return userDAO.UserTotal(userForm);
    }
    //회원 삭제//회원 탈퇴
    public userDTO UserDelete(userForm userForm) throws Exception{
    	
    	userDTO userDTO = new userDTO();
    	
    	int cnt = userDAO.UserDelete(userForm);
    	
    	if(cnt>0) {
    		userDTO.setResult("SUCCESS");
    	}
    	
    	return userDTO;
    }
    //회원수정
    public userDTO UserUpdate(userForm userForm) throws Exception{
    	
    	userDTO userDTO = new userDTO();
    	
    	int cnt = userDAO.UserUpdate(userForm);
    	
    	if(cnt>0) {
    		userDTO.setResult("SUCCESS");
    	}
    	
    	return userDTO;
    }
}

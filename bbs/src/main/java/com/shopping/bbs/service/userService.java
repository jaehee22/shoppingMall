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
 
    //�α���
    public userDTO Login(userForm userForm) throws Exception {
    	
		return userDAO.Login(userForm);
	}
    
    //ȸ������
    public userDTO Join(userForm userForm) throws Exception{
    	
    	userDTO userDTO = new userDTO();
    	    	
    	int cnt = userDAO.Join(userForm);
    	
    	if(cnt>0) {
    		userDTO.setResult("SUCCESS");
    	}
    	
    	return userDTO;
    }
    
    //ȸ���ߺ�Ȯ��
    public int ExistUser(userForm userForm) throws Exception {
    	
    	int result = userDAO.ExistUser(userForm);
    	return result;
    }
    
    //ȸ����
	public userDTO UserView(userForm userForm) throws Exception {
	    	
		userDTO userDTO = new userDTO();
	    	
	    userDTO = userDAO.UserView(userForm);
	    	
	    return userDTO;
	}
    
    
    //ȸ�� ����Ʈ
    public List<userDTO> UserList(userForm userForm) throws Exception {
    	 
        return userDAO.UserList(userForm);
    }
    //�� ���� 
    public int UserTotal(userForm userForm) throws Exception{
    	return userDAO.UserTotal(userForm);
    }
    //ȸ�� ����//ȸ�� Ż��
    public userDTO UserDelete(userForm userForm) throws Exception{
    	
    	userDTO userDTO = new userDTO();
    	
    	int cnt = userDAO.UserDelete(userForm);
    	
    	if(cnt>0) {
    		userDTO.setResult("SUCCESS");
    	}
    	
    	return userDTO;
    }
    //ȸ������
    public userDTO UserUpdate(userForm userForm) throws Exception{
    	
    	userDTO userDTO = new userDTO();
    	
    	int cnt = userDAO.UserUpdate(userForm);
    	
    	if(cnt>0) {
    		userDTO.setResult("SUCCESS");
    	}
    	
    	return userDTO;
    }
}

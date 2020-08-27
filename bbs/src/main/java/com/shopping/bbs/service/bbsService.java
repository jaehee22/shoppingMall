package com.shopping.bbs.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
 
import com.shopping.bbs.dao.bbsDAO;
import com.shopping.bbs.dto.bbsDTO;
import com.shopping.bbs.form.bbsForm;
 
@Service
public class bbsService {
 
    @Autowired
    private bbsDAO bbsDAO;
 
    //�Խ��� ��� (main)
    public List<bbsDTO> BbsList() throws Exception {
 
        return bbsDAO.BbsList();
    }

    //�Խ��� ��� (bbs)
    public List<bbsDTO> BbsbbsList(bbsForm bbsForm) throws Exception {       
       return bbsDAO.BbsbbsList(bbsForm);
    }
    
    //�Խù� �� ����
    public int BbsTotal(bbsForm bbsForm) throws Exception{
    	return bbsDAO.BbsTotal(bbsForm);
    }
    
    //�Խ��� ��
    public bbsDTO BbsView(bbsForm bbsForm) throws Exception {
    	
    	bbsDTO bbsDTO = new bbsDTO();
    	
    	bbsDTO = bbsDAO.BbsView(bbsForm);
    	
    	return bbsDTO;
    }
    
    //�Խñ� ���
    public bbsDTO BbsWrite(bbsForm bbsForm) throws Exception{
    	
    	bbsDTO bbsDTO = new bbsDTO();
    	
    	bbsForm.setBbsID(bbsDAO.GetNext());
    	
    	int cnt = bbsDAO.BbsWrite(bbsForm);
    	
    	if(cnt>0) {
    		bbsDTO.setResult("SUCCESS");
    	}
    	
    	return bbsDTO;
    }
    
    //�Խñ� ����
    public bbsDTO BbsDelete(bbsForm bbsForm) throws Exception{
    	
    	bbsDTO bbsDTO = new bbsDTO();
    	
    	int cnt = bbsDAO.BbsDelete(bbsForm);
    	
    	if(cnt>0) {
    		bbsDTO.setResult("SUCCESS");
    	}
    	
    	return bbsDTO;
    }
    
    //�Խñ� ����
    public bbsDTO BbsUpdate(bbsForm bbsForm) throws Exception{
    	
    	bbsDTO bbsDTO = new bbsDTO();
    	
    	int cnt = bbsDAO.BbsUpdate(bbsForm);
    	
    	if(cnt>0) {
    		bbsDTO.setResult("SUCCESS");
    	}
    	
    	return bbsDTO;
    }
    
}
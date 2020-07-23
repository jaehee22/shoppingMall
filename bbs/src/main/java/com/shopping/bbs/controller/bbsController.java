package com.shopping.bbs.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
 
import com.shopping.bbs.dto.bbsDTO;
import com.shopping.bbs.form.bbsForm;
import com.shopping.bbs.service.bbsService;
 
@Controller
@RequestMapping(value = "/bbs")
public class bbsController {
 
    @Autowired
    private bbsService bbsService;
   
    //���� page
    @RequestMapping( value = "/home")
    public String home(HttpServletRequest request, HttpServletResponse response) throws Exception{
        
        return "bbs/home";
    }
    
    //�Խ��� ��� page
    @RequestMapping( value = "/bbs")
    public String bbs(HttpServletRequest request, HttpServletResponse response) throws Exception{
        
        return "bbs/bbs";
    }
 
    //�Խ��� ��� (main)
    @RequestMapping(value = "/BbsList")
    @ResponseBody
    public List<bbsDTO> BbsList(HttpServletRequest request, HttpServletResponse response, bbsForm bbsForm) throws Exception {
        
    	List<bbsDTO> bbsDTO = bbsService.BbsList(bbsForm);
        
    	return bbsDTO;
    }
    
    //�Խ��� ��� (bbs)
    @RequestMapping(value = "/BbsbbsList")
    @ResponseBody
    public List<bbsDTO> BbsbbsList(HttpServletRequest request, HttpServletResponse response, bbsForm bbsForm) throws Exception {
        
    	List<bbsDTO> bbsDTO = bbsService.BbsbbsList(bbsForm);
        
    	return bbsDTO;
    }    
    
    //�Խ��� �� page
    @RequestMapping( value = "/bbsView")
    public String bbsView(HttpServletRequest request, HttpServletResponse response) throws Exception{
        
        return "bbs/bbsView";
    }
 
    //�Խ��� ��
    @RequestMapping(value = "/BbsView")
    @ResponseBody
    public bbsDTO BbsView(HttpServletRequest request, HttpServletResponse response, bbsForm bbsForm) throws Exception {
        
    	bbsDTO bbsDTO = bbsService.BbsView(bbsForm);

    	return bbsDTO;
    }
    
    //�Խ��� �۹�ȣ
    @RequestMapping(value = "/GetNext")
    @ResponseBody
    public bbsDTO GetNext(HttpServletRequest request, HttpServletResponse response, bbsForm bbsForm) throws Exception {
        
    	bbsDTO bbsDTO = bbsService.BbsUpdate(bbsForm);
        
    	return bbsDTO;
    }
    
    //�Խñ� �ۼ� page
    @RequestMapping( value = "/bbsWrite")
    public String bbsWrite(HttpServletRequest request, HttpServletResponse response) throws Exception{
        
        return "bbs/bbsWrite";
    }
 
    //�Խ��� ���
    @RequestMapping(value = "/BbsWrite")
    @ResponseBody
    public bbsDTO BbsWrite(HttpServletRequest request, HttpServletResponse response, bbsForm bbsForm) throws Exception {
        
    	bbsDTO bbsDTO = bbsService.BbsWrite(bbsForm);
        
    	return bbsDTO;
    }
    
    //�Խ��� ����
    @RequestMapping(value = "/BbsDelete")
    @ResponseBody
    public bbsDTO BbsDelete(HttpServletRequest request, HttpServletResponse response, bbsForm bbsForm) throws Exception {
        
    	bbsDTO bbsDTO = bbsService.BbsDelete(bbsForm);
        
    	return bbsDTO;
    }
    
    //�Խñ� ���� page
    @RequestMapping( value = "/bbsUpdate")
    public String bbsUpdate(HttpServletRequest request, HttpServletResponse response) throws Exception{
        
        return "bbs/bbsUpdate";
    }
 
    //�Խ��� ���
    @RequestMapping(value = "/BbsUpdate")
    @ResponseBody
    public bbsDTO BbsUpdate(HttpServletRequest request, HttpServletResponse response, bbsForm bbsForm) throws Exception {
        
    	bbsDTO bbsDTO = bbsService.BbsUpdate(bbsForm);
        
    	return bbsDTO;
    }
}

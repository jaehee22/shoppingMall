package com.shopping.bbs.controller;

import java.io.File;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.MultipartRequest;

import com.shopping.bbs.dto.bbsDTO;
import com.shopping.bbs.dto.pagingDTO;
import com.shopping.bbs.form.bbsForm;
import com.shopping.bbs.service.bbsService;
 
@Controller
@RequestMapping(value = "/bbs")
public class bbsController {
 
    @Autowired
    private bbsService bbsService;
   
    //메인 page
    @RequestMapping( value = "/home")
    public String home(HttpServletRequest request, HttpServletResponse response) throws Exception{
        
        return "bbs/home";
    }
    
    //게시판 목록 page
    @RequestMapping( value = "/bbs")
    public String bbs(HttpServletRequest request, HttpServletResponse response) throws Exception{
        
        return "bbs/bbs";
    }
 
    //게시판 목록 (main)
    @RequestMapping(value = "/BbsList")
    @ResponseBody
    public List<bbsDTO> BbsList(HttpServletRequest request, HttpServletResponse response, bbsForm bbsForm) throws Exception {
        
    	List<bbsDTO> bbsDTO = bbsService.BbsList(bbsForm);
        
    	return bbsDTO;
    }
        
    int pageNum = 0;
    
    //게시판 목록 (bbs)
    @RequestMapping(value = "/BbsbbsList")
    @ResponseBody
    public List<bbsDTO> BbsbbsList(HttpServletRequest request, HttpServletResponse response, bbsForm bbsForm) throws Exception {
    	
    	//카테고리별 게시물 갯수
    	int BbsTotal = bbsService.BbsTotal(bbsForm);
    	//한페이지당 나올 개시물 수
    	int postNum = 9;
    	//총 페이징 번호 수
    	pageNum = (int)Math.ceil((double)BbsTotal/postNum);
    	//블록당 첫페이지(bbsID)
    	int displayPost = (bbsForm.getNum()-1)*postNum;
    	bbsForm.setDisplayPost(displayPost);
    	bbsForm.setPostNum(postNum);
    	
    	List<bbsDTO> bbsDTO = bbsService.BbsbbsList(bbsForm);
    	
    	return bbsDTO;
    }    
    
    @RequestMapping(value = "/BbsPaging")
    @ResponseBody
    public pagingDTO BbsPaging(HttpServletRequest request, HttpServletResponse response,bbsForm bbsForm) throws Exception {    	
    	    	
    	//카테고리별 게시물 갯수
    	int BbsTotal = bbsService.BbsTotal(bbsForm);
    	//한번에 표시할 페이징 번호 개수
    	int pageNum_cnt = 10;
    	//표시되는 페이지 번호 중 마지막 번호
    	int endPageNum = (int)(Math.ceil((double)bbsForm.getNum()/(double)pageNum_cnt)*pageNum_cnt);
    	//표시되는 페이지 번호 중 첫번째 번호
    	int startPageNum = endPageNum - (pageNum_cnt -1);
    	//마지막 번호 재계산
    	int endPageNum_tmp = (int)(Math.ceil((double)BbsTotal/(double)pageNum_cnt));
    	
    	if(endPageNum > endPageNum_tmp) {
    		endPageNum=endPageNum_tmp;
    	}
    	
    	boolean prev = startPageNum == 1 ? false : true;
    	boolean next = endPageNum * pageNum_cnt >= BbsTotal ? false : true;
    	
    	pagingDTO pagingDTO = new pagingDTO();
    	pagingDTO.setPageNum(pageNum);
    	pagingDTO.setStartPageNum(startPageNum);
    	pagingDTO.setEndPageNum(endPageNum);
    	pagingDTO.setPrev(prev);
    	pagingDTO.setNext(next);
    	
    	return pagingDTO;
    }  
    
    //게시판 뷰 page
    @RequestMapping( value = "/bbsView")
    public String bbsView(HttpServletRequest request, HttpServletResponse response) throws Exception{
        
        return "bbs/bbsView";
    }
 
    //게시판 뷰
    @RequestMapping(value = "/BbsView")
    @ResponseBody
    public bbsDTO BbsView(HttpServletRequest request, HttpServletResponse response, bbsForm bbsForm) throws Exception {
        
    	bbsDTO bbsDTO = bbsService.BbsView(bbsForm);

    	return bbsDTO;
    }
    
    //게시판 글번호
    @RequestMapping(value = "/GetNext")
    @ResponseBody
    public bbsDTO GetNext(HttpServletRequest request, HttpServletResponse response, bbsForm bbsForm) throws Exception {
        
    	bbsDTO bbsDTO = bbsService.BbsUpdate(bbsForm);
        
    	return bbsDTO;
    }
    
    //게시글 작성 page
    @RequestMapping( value = "/bbsWrite")
    public String bbsWrite(HttpServletRequest request, HttpServletResponse response) throws Exception{
        
        return "bbs/bbsWrite";
    }
 
    //게시판 작성
    @RequestMapping(value = "/BbsWrite")
    @ResponseBody
    public bbsDTO BbsWrite(MultipartHttpServletRequest request, HttpServletResponse response, bbsForm bbsForm) throws Exception {
    	   	
    	bbsDTO bbsDTO = bbsService.BbsWrite(bbsForm);   
    	
    	//파일 업로드
    	String root = request.getSession().getServletContext().getRealPath("/");
    	String path = root + "resources/bbsImg/"+bbsForm.getBbsID();

    	File dir = new File(path);    	
    	if (!dir.isDirectory()) {
    		dir.mkdir();
    	}
    	
    	Iterator<String> files = request.getFileNames();
    	
    	while(files.hasNext()) {
    		String uploadFile = files.next();
    		MultipartFile mFile = request.getFile(uploadFile);
    		String fileName = bbsForm.getBbsID()+".jpg";
    		String file_save_path = path;
    		try {
    			mFile.transferTo(new File(file_save_path+"\\"+fileName));
    		}catch(Exception e) {
    			e.printStackTrace();
    		}
    		
    	}
    	
    	return bbsDTO;
    }
    
    //게시판 삭제
    @RequestMapping(value = "/BbsDelete")
    @ResponseBody
    public bbsDTO BbsDelete(HttpServletRequest request, HttpServletResponse response, bbsForm bbsForm) throws Exception {
        
    	bbsDTO bbsDTO = bbsService.BbsDelete(bbsForm);
        
    	return bbsDTO;
    }
    
    //게시글 수정 page
    @RequestMapping( value = "/bbsUpdate")
    public String bbsUpdate(HttpServletRequest request, HttpServletResponse response) throws Exception{
        
        return "bbs/bbsUpdate";
    }
 
    //게시글 수정
    @RequestMapping(value = "/BbsUpdate")
    @ResponseBody
    public bbsDTO BbsUpdate(HttpServletRequest request, HttpServletResponse response, bbsForm bbsForm) throws Exception {
        
    	bbsDTO bbsDTO = bbsService.BbsUpdate(bbsForm);
        
    	return bbsDTO;
    }
    
}

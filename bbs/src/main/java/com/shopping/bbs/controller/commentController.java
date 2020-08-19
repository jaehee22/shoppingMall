package com.shopping.bbs.controller;

import java.io.File;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.shopping.bbs.dto.commentDTO;
import com.shopping.bbs.dto.pagingDTO;
import com.shopping.bbs.form.commentForm;
import com.shopping.bbs.service.commentService;
 
@Controller
@RequestMapping(value = "/comment")
public class commentController {
 
    @Autowired
    private commentService commentService;
    int pageNum = 0;
    
    //후기 목록
    @RequestMapping(value = "/CommentList")
    @ResponseBody
    public List<commentDTO> CommentList(HttpServletRequest request, HttpServletResponse response, commentForm commentForm) throws Exception {
    	//카테고리별 게시물 갯수
    	int commentTotal = commentService.CommentTotal(commentForm);
    	//한페이지당 나올 개시물 수
    	int postNum = 5;
    	//총 페이징 번호 수
    	pageNum = (int)Math.ceil((double)commentTotal/postNum);
    	//블록당 첫페이지
    	int displayPost = (commentForm.getNum()-1)*postNum;
    	commentForm.setDisplayPost(displayPost);
    	commentForm.setPostNum(postNum);
        	
    	List<commentDTO> commentDTO = commentService.CommentList(commentForm);

    	return commentDTO;
    }               
    
    @RequestMapping(value = "/CommentPaging")
    @ResponseBody
    public pagingDTO Paging(HttpServletRequest request, HttpServletResponse response,commentForm commentForm) throws Exception {    	
    	    	
    	//카테고리별 게시물 갯수
    	int commentTotal = commentService.CommentTotal(commentForm);
    	//한번에 표시할 페이징 번호 개수
    	int pageNum_cnt = 6;
    	//표시되는 페이지 번호 중 마지막 번호
    	int endPageNum = (int)(Math.ceil((double)commentForm.getNum()/(double)pageNum_cnt)*pageNum_cnt);
    	//표시되는 페이지 번호 중 첫번째 번호
    	int startPageNum = endPageNum - (pageNum_cnt -1);
    	//마지막 번호 재계산
    	int endPageNum_tmp = (int)(Math.ceil((double)commentTotal/(double)pageNum_cnt));

    	if(endPageNum > endPageNum_tmp) {
    		endPageNum=endPageNum_tmp;
    	}
    	
    	boolean prev = startPageNum == 1 ? false : true;
    	boolean next = endPageNum * pageNum_cnt >= commentTotal ? false : true;
    	
    	pagingDTO pagingDTO = new pagingDTO();
    	pagingDTO.setPageNum(pageNum);
    	pagingDTO.setStartPageNum(startPageNum);
    	pagingDTO.setEndPageNum(endPageNum);
    	pagingDTO.setPrev(prev);
    	pagingDTO.setNext(next);
    	
    	return pagingDTO;
    }  
    
    //댓글 번호
    @RequestMapping(value = "/GetNext")
    @ResponseBody
    public commentDTO GetNext(HttpServletRequest request, HttpServletResponse response, commentForm commentForm) throws Exception {
        
    	commentDTO commentDTO = commentService.CommentUpdate(commentForm);
        
    	return commentDTO;
    }
     
    //댓글 작성
    @RequestMapping(value = "/CommentWrite")
    @ResponseBody
    public commentDTO CommentWrite(MultipartHttpServletRequest request, HttpServletResponse response, commentForm commentForm) throws Exception {
    	    	
    	commentDTO commentDTO = commentService.CommentWrite(commentForm);   

    	//파일 업로드
    	String root = request.getSession().getServletContext().getRealPath("/");
    	String path = root + "resources/bbsImg/"+commentForm.getBbsID();

    	File dir = new File(path);    	
    	if (!dir.isDirectory()) {
    		dir.mkdir();
    	}
    	
    	path += "/comment";
    	
    	File dir2 = new File(path);    	
    	if (!dir2.isDirectory()) {
    		dir2.mkdir();
    	}
    	
    	Iterator<String> files = request.getFileNames();
    	
    	if(files.hasNext()) {
    		String uploadFile = files.next();
    		MultipartFile mFile = request.getFile(uploadFile);
    		String fileName = commentForm.getCommentID()+".jpg";
    		String file_save_path = path;
    		try {
    			mFile.transferTo(new File(file_save_path+"\\"+fileName));
    		}catch(Exception e) {
    			e.printStackTrace();
    		}
    		
    	}
    	
    	return commentDTO;
    }
    
    //댓글 뷰
    @RequestMapping(value = "/CommentView")
    @ResponseBody
    public commentDTO CommentView(HttpServletRequest request, HttpServletResponse response, commentForm commentForm) throws Exception {
        
    	commentDTO commentDTO = commentService.CommentView(commentForm);

    	return commentDTO;
    }
    
    //댓글 삭제
    @RequestMapping(value = "/CommentDelete")
    @ResponseBody
    public commentDTO CommentDelete(HttpServletRequest request, HttpServletResponse response, commentForm commentForm) throws Exception {
        
    	//파일 삭제
    	String root = request.getSession().getServletContext().getRealPath("/");
    	String path = root + "resources/bbsImg/"+commentForm.getBbsID()+"/comment/"+commentForm.getCommentID()+".jpg";
    	File file = new File(path);
    	if(file.exists()) {
    		file.delete();
    	}
    	
    	commentDTO commentDTO = commentService.CommentDelete(commentForm);
        
    	return commentDTO;
    }
 
    
    //댓글 수정 page
    @RequestMapping( value = "/commentUpdate")
    public String commentUpdate(HttpServletRequest request, HttpServletResponse response) throws Exception{
        
        return "comment/commentUpdate";
    }
    
    //댓글 수정
    @RequestMapping(value = "/CommentUpdate")
    @ResponseBody
    public commentDTO CommentUpdate(MultipartHttpServletRequest request, HttpServletResponse response, commentForm commentForm) throws Exception {
            	
    	Iterator<String> files = request.getFileNames();
    	
    	if(files.hasNext()) {
    		//파일 경로
        	String root = request.getSession().getServletContext().getRealPath("/");
        	String path = root + "resources/bbsImg/"+commentForm.getBbsID()+"/comment/"+commentForm.getCommentID();

        	File file = new File(path);
        	if(file.exists()) {
        		file.delete();
        	}
        	
        	//파일 업로드
        	String newPath = root + "resources/bbsImg/"+commentForm.getBbsID()+"/comment";

        	File dir = new File(newPath);    	
        	if (!dir.isDirectory()) {
        		dir.mkdir();
        	}
        	
    		String uploadFile = files.next();
    		MultipartFile mFile = request.getFile(uploadFile);
    		String fileName = commentForm.getCommentID()+".jpg";
    		String file_save_path = newPath;
    		try {
    			mFile.transferTo(new File(file_save_path+"\\"+fileName));
    		}catch(Exception e) {
    			e.printStackTrace();
    		}
    		
    	}
    	
    	commentDTO commentDTO = commentService.CommentUpdate(commentForm);
        
    	return commentDTO;
    }
    
    //답글 작성 page
    @RequestMapping( value = "/subCommentWrite")
    public String subCommentWrite(HttpServletRequest request, HttpServletResponse response) throws Exception{
        
        return "comment/subCommentWrite";
    }
    
    
  //답글작성
  @RequestMapping(value = "/SubCommentWrite")
  @ResponseBody
  public commentDTO SubCommentWrite(HttpServletRequest request, HttpServletResponse response, commentForm commentForm) throws Exception {
  	
  	commentDTO commentDTO = commentService.CommentWrite(commentForm);   
  	
  	return commentDTO;
  }

}

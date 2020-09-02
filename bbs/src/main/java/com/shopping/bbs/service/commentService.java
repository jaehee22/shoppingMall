package com.shopping.bbs.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
 
import com.shopping.bbs.dao.commentDAO;
import com.shopping.bbs.dto.commentDTO;
import com.shopping.bbs.form.commentForm;
 
@Service
public class commentService {
 
    @Autowired
    private commentDAO commentDAO;
 
    //��� ���
    public List<commentDTO> CommentList(commentForm commentForm) throws Exception {    	
        return commentDAO.CommentList(commentForm);
    }

    //�Խù� �� ����
    public int CommentTotal(commentForm commentForm) throws Exception{
    	return commentDAO.CommentTotal(commentForm);
    }
        
    //��� ���
    public commentDTO CommentWrite(commentForm commentForm) throws Exception{
    	
    	commentDTO commentDTO = new commentDTO();
    	
    	commentForm.setSubCommentID(commentDAO.SubGetNext(commentForm));

    	//����� �ƴ� ���
    	if(commentForm.getSubCommentID()==0) {
        	commentForm.setCommentID(commentDAO.GetNext());
    	}
    	
    	int cnt = commentDAO.CommentWrite(commentForm);
    	
    	if(cnt>0) {
    		commentDTO.setResult("SUCCESS");
    	}
    	
    	return commentDTO;
    }
    //��� ��
    public commentDTO CommentView(commentForm commentForm) throws Exception {
    	
    	commentDTO commentDTO = new commentDTO();
    	
    	commentDTO = commentDAO.CommentView(commentForm);
    	
    	return commentDTO;
    }
    
    public int UserReview(commentForm commentForm) throws Exception {
    	return commentDAO.UserReview(commentForm);
    }
    
    //��� ����
    public commentDTO CommentDelete(commentForm commentForm) throws Exception{
    	
    	commentDTO commentDTO = new commentDTO();
    	
    	int cnt = commentDAO.CommentDelete(commentForm);
    	
    	if(cnt>0) {
    		commentDTO.setResult("SUCCESS");
    	}
    	
    	return commentDTO;
    }
    
    //��� ����
    public commentDTO CommentUpdate(commentForm commentForm) throws Exception{
    	
    	commentDTO commentDTO = new commentDTO();
    	
    	int cnt = commentDAO.CommentUpdate(commentForm);
    	
    	if(cnt>0) {
    		commentDTO.setResult("SUCCESS");
    	}
    	
    	return commentDTO;
    }
    
}
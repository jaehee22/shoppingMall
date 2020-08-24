package com.shopping.bbs.dao;

import java.util.List;
 
import javax.annotation.Resource;
 
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.shopping.bbs.dto.commentDTO;
import com.shopping.bbs.form.commentForm;
 
@Repository
public class commentDAO {
 
    @Resource(name = "sqlSession")
    private SqlSession sqlSession;
 
    private static final String NAMESPACE = "com.shopping.bbs.commentMapper";
 
    //��� ��� 
    public List<commentDTO> CommentList(commentForm commentForm) throws Exception {
        return sqlSession.selectList(NAMESPACE + ".CommentList", commentForm);
    }

    //��� �� ����
    public int CommentTotal(commentForm commentForm) throws Exception{
    	return sqlSession.selectOne(NAMESPACE + ".CommentTotal", commentForm);
    }
    
    //commentID ����
    public int GetNext() throws Exception{
    	
    	List<commentDTO> commentDTO = sqlSession.selectList(NAMESPACE + ".GetNext");
    	int i;
    	
    	if(commentDTO.isEmpty()) {
    		i=1;
    	}else {
    		i = commentDTO.get(0).getCommentID()+1;
    	}    		
    	return i;
    }
    
    //subCommentID ����
    public int SubGetNext(commentForm commentForm) throws Exception{
    	
    	List<commentDTO> commentDTO = sqlSession.selectList(NAMESPACE + ".SubGetNext",commentForm);
    	int i;
    	
    	if(commentDTO.isEmpty()) {
    		i=0;
    	}else{
    		System.out.println(commentDTO.get(0).getSubCommentID());
    		i = commentDTO.get(0).getSubCommentID()+1;
    	}    		
    	return i;
    }
    
    //��� ���
    public int CommentWrite(commentForm commentForm) throws Exception {
    	
        return sqlSession.insert(NAMESPACE + ".CommentWrite", commentForm);
    }
    
    //��� ��
    public commentDTO CommentView(commentForm commentForm) throws Exception {
        return sqlSession.selectOne(NAMESPACE + ".CommentView", commentForm);
    }
    
    //�ı��ۼ��� ���� ��������
    public int UserReview(commentForm commentForm) throws Exception{
    	return sqlSession.selectOne(NAMESPACE+".UserReview",commentForm);
    }
    
    //��� ����
    public int CommentDelete(commentForm commentForm) throws Exception {
 
        return sqlSession.delete(NAMESPACE + ".CommentDelete", commentForm);
    }
    
    //��� ����
    public int CommentUpdate(commentForm commentForm) throws Exception {
 
        return sqlSession.update(NAMESPACE + ".CommentUpdate", commentForm);
    }
}

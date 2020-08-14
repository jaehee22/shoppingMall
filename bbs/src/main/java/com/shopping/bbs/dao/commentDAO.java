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
 
    //´ñ±Û ¸ñ·Ï 
    public List<commentDTO> CommentList(commentForm commentForm) throws Exception {
    	
        return sqlSession.selectList(NAMESPACE + ".CommentList", commentForm);
    }

    //´ñ±Û ÃÑ °¹¼ö
    public int CommentTotal(commentForm commentForm) throws Exception{
    	return sqlSession.selectOne(NAMESPACE + ".CommentTotal", commentForm);
    }
    
    //commentID »ý¼º
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
    
    //´ñ±Û µî·Ï
    public int CommentWrite(commentForm commentForm) throws Exception {
    	
        return sqlSession.insert(NAMESPACE + ".CommentWrite", commentForm);
    }
    
    //´ñ±Û »èÁ¦
    public int CommentDelete(commentForm commentForm) throws Exception {
 
        return sqlSession.delete(NAMESPACE + ".CommentDelete", commentForm);
    }
    
    //´ñ±Û ¼öÁ¤
    public int CommentUpdate(commentForm commentForm) throws Exception {
 
        return sqlSession.update(NAMESPACE + ".CommentUpdate", commentForm);
    }
}

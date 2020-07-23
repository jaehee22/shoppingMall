package com.shopping.bbs.dao;

import java.util.List;
 
import javax.annotation.Resource;
 
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;
 
import com.shopping.bbs.dto.bbsDTO;
import com.shopping.bbs.form.bbsForm;
 
@Repository
public class bbsDAO {
 
    @Resource(name = "sqlSession")
    private SqlSession sqlSession;
 
    private static final String NAMESPACE = "com.shopping.bbs.bbsMapper";
 
    //게시판 목록 (main)
    public List<bbsDTO> BbsList(bbsForm bbsForm) throws Exception {
 
        return sqlSession.selectList(NAMESPACE + ".BbsList", bbsForm);
    }

    //게시판 목록 (bbs)
    public List<bbsDTO> BbsbbsList(bbsForm bbsForm) throws Exception {
 
        return sqlSession.selectList(NAMESPACE + ".BbsbbsList", bbsForm);
    }
    
    //게시판 뷰
    public bbsDTO BbsView(bbsForm bbsForm) throws Exception {
        return sqlSession.selectOne(NAMESPACE + ".BbsView", bbsForm);
    }
    
    public int GetNext() throws Exception{
    	
    	List<bbsDTO> bbsDTO = sqlSession.selectList(NAMESPACE + ".GetNext");
    	int i;
    	
    	if(bbsDTO.isEmpty()) {
    		i=1;
    	}else {
    		i = bbsDTO.get(0).getBbsID()+1;
    	}    		
    	return i;
    }
    
    //게시글 등록
    public int BbsWrite(bbsForm bbsForm) throws Exception {
    	
        return sqlSession.insert(NAMESPACE + ".BbsWrite", bbsForm);
    }
    
    //게시글 삭제
    public int BbsDelete(bbsForm bbsForm) throws Exception {
 
        return sqlSession.delete(NAMESPACE + ".BbsDelete", bbsForm);
    }
    
    //게시글 수정
    public int BbsUpdate(bbsForm bbsForm) throws Exception {
 
        return sqlSession.update(NAMESPACE + ".BbsUpdate", bbsForm);
    }
}

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
 
    //게시판 목록
    public List<bbsDTO> BbsList(bbsForm bbsForm) throws Exception {
 
        return sqlSession.selectList(NAMESPACE + ".BbsList", bbsForm);
    }

    //게시판 뷰
    public int BbsView(bbsForm bbsForm) throws Exception {
 
        return sqlSession.selectOne(NAMESPACE + ".BbsView", bbsForm);
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

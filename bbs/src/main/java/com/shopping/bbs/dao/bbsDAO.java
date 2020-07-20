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
 
    public List<bbsDTO> BbsList(bbsForm bbsForm) throws Exception {
 
        return sqlSession.selectList(NAMESPACE + ".BbsList");
    }
}

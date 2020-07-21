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
 
    //�Խ��� ���
    public List<bbsDTO> BbsList(bbsForm bbsForm) throws Exception {
 
        return sqlSession.selectList(NAMESPACE + ".BbsList", bbsForm);
    }

    //�Խ��� ��
    public int BbsView(bbsForm bbsForm) throws Exception {
 
        return sqlSession.selectOne(NAMESPACE + ".BbsView", bbsForm);
    }
    
    //�Խñ� ���
    public int BbsWrite(bbsForm bbsForm) throws Exception {
 
        return sqlSession.insert(NAMESPACE + ".BbsWrite", bbsForm);
    }
    
    //�Խñ� ����
    public int BbsDelete(bbsForm bbsForm) throws Exception {
 
        return sqlSession.delete(NAMESPACE + ".BbsDelete", bbsForm);
    }
    
    //�Խñ� ����
    public int BbsUpdate(bbsForm bbsForm) throws Exception {
 
        return sqlSession.update(NAMESPACE + ".BbsUpdate", bbsForm);
    }
}

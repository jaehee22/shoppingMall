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
 
    //�Խ��� ��� (main)
    public List<bbsDTO> BbsList() throws Exception {
 
        return sqlSession.selectList(NAMESPACE + ".BbsList");
    }

    //�Խ��� ��� (bbs)
    public List<bbsDTO> BbsbbsList(bbsForm bbsForm) throws Exception {
    	
        return sqlSession.selectList(NAMESPACE + ".BbsbbsList", bbsForm);
    }
    
    //�Խ��� ��
    public bbsDTO BbsView(bbsForm bbsForm) throws Exception {
        return sqlSession.selectOne(NAMESPACE + ".BbsView", bbsForm);
    }
    //�Խù� �� ����
    public int BbsTotal(bbsForm bbsForm) throws Exception{
    	return sqlSession.selectOne(NAMESPACE + ".BbsTotal", bbsForm);
    }
    
    //bbsID����
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

package com.shopping.bbs.dao;

import java.util.List;
 
import javax.annotation.Resource;
 
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.shopping.bbs.dto.cartDTO;
import com.shopping.bbs.form.cartForm;
 
@Repository
public class cartDAO {
 
    @Resource(name = "sqlSession")
    private SqlSession sqlSession;
 
    private static final String NAMESPACE = "com.shopping.bbs.cartMapper";
 
    //��ٱ��� ���
    public List<cartDTO> CartList(cartForm cartForm) throws Exception {
        return sqlSession.selectList(NAMESPACE + ".CartList", cartForm);
    }
    
    //��ٱ��Ͽ� �ش�Ǵ� BBS����
    public cartDTO CartInfo(cartForm cartForm) throws Exception {
        return sqlSession.selectOne(NAMESPACE + ".CartInfo", cartForm);
    }
    
    //��ٱ��� �� ����
    public int CartTotal(cartForm cartForm) throws Exception{
    	return sqlSession.selectOne(NAMESPACE + ".CartTotal", cartForm);
    }
    //��ٱ��� ��
    public cartDTO CartView(cartForm cartForm) throws Exception {
        return sqlSession.selectOne(NAMESPACE + ".CartView", cartForm);
    }
    //cartID ����
    public int GetNext() throws Exception{
    	
    	List<cartDTO> cartDTO = sqlSession.selectList(NAMESPACE + ".GetNext");
    	
    	int i;
    	
    	if(cartDTO.isEmpty()) {
    		i=1;
    	}else {
    		i = cartDTO.get(0).getCartID()+1;
    	}    		
    	return i;
    }
    
    //��ٱ��� ���
    public int CartWrite(cartForm cartForm) throws Exception {
    	
        return sqlSession.insert(NAMESPACE + ".CartWrite", cartForm);
    }
    
    //��ٱ��� ����
    public int CartUpdate(cartForm cartForm) throws Exception {
 
        return sqlSession.update(NAMESPACE + ".CartUpdate", cartForm);
    }
    
    //��ٱ��� ����
    public int CartDelete(cartForm cartForm) throws Exception {
 
        return sqlSession.delete(NAMESPACE + ".CartDelete", cartForm);
    }
    
    //��ٱ��Ͽ��� ������ ���� �ֹ����� update
    public int CartOrder(cartForm cartForm) throws Exception {
 
        return sqlSession.update(NAMESPACE + ".CartOrder", cartForm);
    }
    
    //view���� ��������
    public int ThisOrder(cartForm cartForm) throws Exception {
    	
        return sqlSession.insert(NAMESPACE + ".ThisOrder", cartForm);
    }
    
    //�ֹ� ����(��ٱ���)
    public int CartOrderReset(cartForm cartForm) throws Exception {
 
        return sqlSession.delete(NAMESPACE + ".CartOrderReset", cartForm);
    }
    
    //�ֹ� ����(bbs)
    public int BbsOrderReset(cartForm cartForm) throws Exception {
 
        return sqlSession.delete(NAMESPACE + ".BbsOrderReset", cartForm);
    }
}

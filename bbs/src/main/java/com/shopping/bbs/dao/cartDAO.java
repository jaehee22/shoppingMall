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
 
    //장바구니 목록
    public List<cartDTO> CartList(cartForm cartForm) throws Exception {
        return sqlSession.selectList(NAMESPACE + ".CartList", cartForm);
    }
    
    //장바구니에 해당되는 BBS정보
    public cartDTO CartInfo(cartForm cartForm) throws Exception {
        return sqlSession.selectOne(NAMESPACE + ".CartInfo", cartForm);
    }
    
    //장바구니 총 갯수
    public int CartTotal(cartForm cartForm) throws Exception{
    	return sqlSession.selectOne(NAMESPACE + ".CartTotal", cartForm);
    }
    //장바구니 뷰
    public cartDTO CartView(cartForm cartForm) throws Exception {
        return sqlSession.selectOne(NAMESPACE + ".CartView", cartForm);
    }
    //cartID 생성
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
    
    //장바구니 등록
    public int CartWrite(cartForm cartForm) throws Exception {
    	
        return sqlSession.insert(NAMESPACE + ".CartWrite", cartForm);
    }
    
    //장바구니 수정
    public int CartUpdate(cartForm cartForm) throws Exception {
 
        return sqlSession.update(NAMESPACE + ".CartUpdate", cartForm);
    }
    
    //장바구니 삭제
    public int CartDelete(cartForm cartForm) throws Exception {
 
        return sqlSession.delete(NAMESPACE + ".CartDelete", cartForm);
    }
    

}

package com.shopping.bbs.dao;

import java.util.List;
 
import javax.annotation.Resource;
 
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.shopping.bbs.dto.orderDTO;
import com.shopping.bbs.form.orderForm;
 
@Repository
public class orderDAO {
 
    @Resource(name = "sqlSession")
    private SqlSession sqlSession;
 
    private static final String NAMESPACE = "com.shopping.bbs.orderMapper";
 
    //게시판 목록 (main)
    public List<orderDTO> OrderList(orderForm orderForm) throws Exception {
 
        return sqlSession.selectList(NAMESPACE + ".OrderList", orderForm);
    }
    
    //주문 뷰
    public orderDTO OrderView(orderForm orderForm) throws Exception {
       
    	orderDTO orderDTO = new orderDTO();
    	
    	orderDTO = sqlSession.selectOne(NAMESPACE + ".OrderView", orderForm);
    	
    	return orderDTO;
    }
    
    //orderID생성
    public int GetNext() throws Exception{
    	
    	List<orderDTO> orderDTO = sqlSession.selectList(NAMESPACE + ".GetNext");
    	int i;
    	
    	if(orderDTO.isEmpty()) {
    		i=1;
    	}else {
    		i = orderDTO.get(0).getOrderID()+1;
    	}    		
    	return i;
    }
    
    //주문 등록
    public int OrderWrite(orderForm orderForm) throws Exception {
    	
        return sqlSession.insert(NAMESPACE + ".OrderWrite", orderForm);
    }
    
    //주문 삭제
    public int OrderDelete(orderForm orderForm) throws Exception {
 
        return sqlSession.delete(NAMESPACE + ".OrderDelete", orderForm);
    }
    
    //주문 수정
    public int OrderUpdate(orderForm orderForm) throws Exception {
 
        return sqlSession.update(NAMESPACE + ".OrderUpdate", orderForm);
    }
}

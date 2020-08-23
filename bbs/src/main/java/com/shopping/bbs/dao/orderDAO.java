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
 
    //�ֹ� ��� (�ֹ�write�Ҷ� ������ ���)
    public List<orderDTO> OrderList(orderForm orderForm) throws Exception {
 
        return sqlSession.selectList(NAMESPACE + ".OrderList", orderForm);
    }
    
    //�ֹ���� (�ֹ��Ϸ�)
    public List<orderDTO> OrderBbs(orderForm orderForm) throws Exception {
    	 
        return sqlSession.selectList(NAMESPACE + ".OrderBbs", orderForm);
    }
    
    //�ֹ� ��
    public List<orderDTO> OrderView(orderForm orderForm) throws Exception {
          	
    	return sqlSession.selectList(NAMESPACE + ".OrderView", orderForm);
    }
    
    //orderID����
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
    
    public int Items(orderForm orderForm) throws Exception{
    	return sqlSession.selectOne(NAMESPACE + ".Items" , orderForm);
    }
    //�ֹ� �� ����
    public int OrderTotal(orderForm orderForm) throws Exception{
    	return sqlSession.selectOne(NAMESPACE + ".OrderTotal", orderForm);
    }
    //�ֹ� ���
    public int OrderWrite(orderForm orderForm) throws Exception {
    	
        return sqlSession.insert(NAMESPACE + ".OrderWrite", orderForm);
    }
    //sub�� �Է��� �ֹ� ���
    public List<orderForm> SubOrderList(orderForm orderForm) throws Exception {
 
        return sqlSession.selectList(NAMESPACE + ".SubOrderList", orderForm);
    }
    //sub�ֹ� ���
    public int SubOrderWrite(orderForm orderForm) throws Exception {
    	
        return sqlSession.insert(NAMESPACE + ".SubOrderWrite", orderForm);
    }
    //�ֹ� ���� �� cart������ ����
    public int SubOrderSuccess(orderForm orderForm) throws Exception {
 
        return sqlSession.delete(NAMESPACE + ".SubOrderSuccess", orderForm);
    }
    //�ֹ� ����
    public int OrderDelete(orderForm orderForm) throws Exception {
 
        return sqlSession.delete(NAMESPACE + ".OrderDelete", orderForm);
    }
    
    //�ֹ� ����
    public int OrderUpdate(orderForm orderForm) throws Exception {
 
        return sqlSession.update(NAMESPACE + ".OrderUpdate", orderForm);
    }
}

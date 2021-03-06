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
 
    //주문 목록 (주문write할때 구매할 목록)
    public List<orderDTO> OrderList(orderForm orderForm) throws Exception {
 
        return sqlSession.selectList(NAMESPACE + ".OrderList", orderForm);
    }
    
    //주문목록 (주문완료)
    public List<orderDTO> OrderBbs(orderForm orderForm) throws Exception {
    	 
        return sqlSession.selectList(NAMESPACE + ".OrderBbs", orderForm);
    }
    
    //주문 뷰
    public List<orderDTO> OrderView(orderForm orderForm) throws Exception {
          	
    	return sqlSession.selectList(NAMESPACE + ".OrderView", orderForm);
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
    
    public int Items(orderForm orderForm) throws Exception{
    	return sqlSession.selectOne(NAMESPACE + ".Items" , orderForm);
    }
    //주문 총 갯수
    public int OrderTotal(orderForm orderForm) throws Exception{
    	return sqlSession.selectOne(NAMESPACE + ".OrderTotal", orderForm);
    }
    //주문 등록
    public int OrderWrite(orderForm orderForm) throws Exception {
    	
        return sqlSession.insert(NAMESPACE + ".OrderWrite", orderForm);
    }
    //sub에 입력할 주문 목록
    public List<orderForm> SubOrderList(orderForm orderForm) throws Exception {
 
        return sqlSession.selectList(NAMESPACE + ".SubOrderList", orderForm);
    }
    //sub주문 등록
    public int SubOrderWrite(orderForm orderForm) throws Exception {
    	
        return sqlSession.insert(NAMESPACE + ".SubOrderWrite", orderForm);
    }
    //주문 성공 시 cart데이터 삭제
    public int SubOrderSuccess(orderForm orderForm) throws Exception {
 
        return sqlSession.delete(NAMESPACE + ".SubOrderSuccess", orderForm);
    }
    //주문성공 시 해당 제품 판매량 추가
    public int SellUpdate(orderForm orderForm) throws Exception {
 
        return sqlSession.update(NAMESPACE + ".SellUpdate", orderForm);
    }
    
    //주문 삭제
    public int OrderDelete(orderForm orderForm) throws Exception {
 
        return sqlSession.delete(NAMESPACE + ".OrderDelete", orderForm);
    }
    //주문삭제 시 해당 제품 판매량 감소
    public List<orderForm> SellRefund1(orderForm orderForm) throws Exception {
 
        return sqlSession.selectList(NAMESPACE + ".SellRefund1", orderForm);
    }
    public int SellRefund2(orderForm orderForm) throws Exception {
    	return sqlSession.update(NAMESPACE + ".SellRefund2",orderForm);
    }
    
    //주문 수정
    public int OrderUpdate(orderForm orderForm) throws Exception {
 
        return sqlSession.update(NAMESPACE + ".OrderUpdate", orderForm);
    }
    //배송상태 수정
    public int DeliveryUpdate(orderForm orderForm) throws Exception {
 
        return sqlSession.update(NAMESPACE + ".DeliveryUpdate", orderForm);
    }
    
    //회원들 주문목록
    public List<orderDTO> UserOrder(orderForm orderForm) throws Exception {
    	 
        return sqlSession.selectList(NAMESPACE + ".UserOrder", orderForm);
    }
    //회원 주문 총 갯수
    public int UserOrderTotal(orderForm orderForm) throws Exception{
    	return sqlSession.selectOne(NAMESPACE + ".UserOrderTotal", orderForm);
    }
}

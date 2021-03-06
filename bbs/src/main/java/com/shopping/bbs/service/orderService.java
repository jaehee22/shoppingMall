package com.shopping.bbs.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
 
import com.shopping.bbs.dao.orderDAO;
import com.shopping.bbs.dto.orderDTO;
import com.shopping.bbs.form.orderForm;
 
@Service
public class orderService {
 
    @Autowired
    private orderDAO orderDAO;
 
    //주문 목록 (주문write할때 구매할 목록)
    public List<orderDTO> OrderList(orderForm orderForm) throws Exception {
 
        return orderDAO.OrderList(orderForm);
    }
    
    //주문 목록 (주문완료)
    public List<orderDTO> OrderBbs(orderForm orderForm) throws Exception {
    	
    	List<orderDTO> orderDTO = orderDAO.OrderBbs(orderForm);
    	
    	orderForm count = new orderForm(); 
    	
    	for(int i=0; i<orderDTO.size(); i++) {
    		count.setOrderID(orderDTO.get(i).getOrderID());
    		orderDTO.get(i).setItems(orderDAO.Items(count));
    	}
    	
    	return orderDTO;
    }
    //주문 총 갯수
    public int OrderTotal(orderForm orderForm) throws Exception{
    	return orderDAO.OrderTotal(orderForm);
    }
    
    //주문 뷰
    public List<orderDTO> OrderView(orderForm orderForm) throws Exception {
    	
    	return orderDAO.OrderView(orderForm);
    }
    
    //주문 등록
    public orderDTO OrderWrite(orderForm orderForm) throws Exception{
    	
    	orderDTO orderDTO = new orderDTO();
    	
    	orderForm.setOrderID(orderDAO.GetNext());
    	
    	int cnt = orderDAO.OrderWrite(orderForm);
    	
    	if(cnt>0) {
    		orderDTO.setResult("SUCCESS");
    		orderDTO.setOrderID(orderForm.getOrderID());
    	}
    	
    	return orderDTO;
    }
   
    //주문 등록(sub) && 판매량 추가
    public orderDTO SubOrderWrite(orderForm orderForm) throws Exception{
    	
    	//등록 list
    	List<orderForm> SubOrderForm = orderDAO.SubOrderList(orderForm);
    	
    	//판매량 추가 Form 
    	orderForm sell = new orderForm();
    	
    	//완성됐다는걸 알려주는 DTO
    	orderDTO orderDTO = new orderDTO();
	
    	int cnt = 0;

    	for(int i=0; i<SubOrderForm.size(); i++) {
    		SubOrderForm.get(i).setOrderID(orderForm.getOrderID());
    		sell.setBbsID(SubOrderForm.get(i).getBbsID());
    		sell.setAmount(SubOrderForm.get(i).getAmount());
    		orderDAO.SellUpdate(sell);
        	orderDAO.SubOrderWrite(SubOrderForm.get(i));
        	cnt++;
    	}
    	
    	if(cnt>0) {
    		orderDTO.setResult("SUCCESS");
    	}
    	
    	return orderDTO;
    }
    
    //주문완료시 cart데이터 삭제 
    public orderDTO SubOrderSuccess(orderForm orderForm) throws Exception{
    	    	
    	orderDTO orderDTO = new orderDTO();
    	
    	int cnt = orderDAO.SubOrderSuccess(orderForm);
    	
    	if(cnt>0) {
    		orderDTO.setResult("SUCCESS");
    	}
    	
    	return orderDTO;
    }
    
    
    //주문 삭제
    public orderDTO OrderDelete(orderForm orderForm) throws Exception{
    	
    	//판매량 줄일 리스트
    	List<orderForm> sellRefund = orderDAO.SellRefund1(orderForm);
    	
    	//주문서1삭제 dto
    	orderDTO orderDTO = new orderDTO();

    	for(int i=0; i<sellRefund.size(); i++) {
    		orderDAO.SellRefund2(sellRefund.get(i));
    	}
    	
    	int cnt = orderDAO.OrderDelete(orderForm);
    	
    	if(cnt>0) {
    		orderDTO.setResult("SUCCESS");
    	}
    	
    	return orderDTO;
    }
    
    //주문 수정
    public orderDTO OrderUpdate(orderForm orderForm) throws Exception{
    	
    	orderDTO orderDTO = new orderDTO();
    	
    	int cnt = orderDAO.OrderUpdate(orderForm);
    	
    	if(cnt>0) {
    		orderDTO.setResult("SUCCESS");
    	}
    	
    	return orderDTO;
    }
    //배송상태 수정
    public orderDTO DeliveryUpdate(orderForm orderForm) throws Exception{
    	
    	orderDTO orderDTO = new orderDTO();
    	
    	int cnt = orderDAO.DeliveryUpdate(orderForm);
    	
    	if(cnt>0) {
    		orderDTO.setResult("SUCCESS");
    	}
    	
    	return orderDTO;
    }
    
    //회원 주문 목록
    public List<orderDTO> UserOrder(orderForm orderForm) throws Exception {
    	
    	List<orderDTO> orderDTO = orderDAO.UserOrder(orderForm);
    	
    	orderForm count = new orderForm(); 
    	
    	for(int i=0; i<orderDTO.size(); i++) {
    		count.setOrderID(orderDTO.get(i).getOrderID());
    		orderDTO.get(i).setItems(orderDAO.Items(count));
    	}
    	
    	return orderDTO;
    }
    //주문 총 갯수
    public int UserOrderTotal(orderForm orderForm) throws Exception{
    	return orderDAO.UserOrderTotal(orderForm);
    }
}
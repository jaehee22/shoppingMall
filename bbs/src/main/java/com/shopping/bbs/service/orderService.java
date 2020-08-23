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
    
    //주문 뷰
    public orderDTO OrderView(orderForm orderForm) throws Exception {
    	
    	orderDTO orderDTO = new orderDTO();
    	
    	orderDTO = orderDAO.OrderView(orderForm);
    	
    	return orderDTO;
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
   
    //주문 등록(sub)
    public orderDTO SubOrderWrite(orderForm orderForm) throws Exception{
    	
    	//등록 list
    	List<orderForm> SubOrderForm = orderDAO.SubOrderList(orderForm);
    	
    	//완성됐다는걸 알려주는 DTO
    	orderDTO orderDTO = new orderDTO();
    	int cnt = 0;

    	for(int i=0; i<SubOrderForm.size(); i++) {
    		SubOrderForm.get(i).setOrderID(orderForm.getOrderID());
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
    	
    	orderDTO orderDTO = new orderDTO();
    	
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
    
}
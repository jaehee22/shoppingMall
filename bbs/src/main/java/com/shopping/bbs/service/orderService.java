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
 
    //게시판 목록 (main)
    public List<orderDTO> OrderList(orderForm orderForm) throws Exception {
 
        return orderDAO.OrderList(orderForm);
    }
    
    //게시판 뷰
    public orderDTO OrderView(orderForm orderForm) throws Exception {
    	
    	orderDTO orderDTO = new orderDTO();
    	
    	orderDTO = orderDAO.OrderView(orderForm);
    	
    	return orderDTO;
    }
    
    //게시글 등록
    public orderDTO OrderWrite(orderForm orderForm) throws Exception{
    	
    	orderDTO orderDTO = new orderDTO();
    	
    	orderForm.setBbsID(orderDAO.GetNext());
    	
    	int cnt = orderDAO.OrderWrite(orderForm);
    	
    	if(cnt>0) {
    		orderDTO.setResult("SUCCESS");
    	}
    	
    	return orderDTO;
    }
    
    //게시글 삭제
    public orderDTO OrderDelete(orderForm orderForm) throws Exception{
    	
    	orderDTO orderDTO = new orderDTO();
    	
    	int cnt = orderDAO.OrderDelete(orderForm);
    	
    	if(cnt>0) {
    		orderDTO.setResult("SUCCESS");
    	}
    	
    	return orderDTO;
    }
    
    //게시글 수정
    public orderDTO OrderUpdate(orderForm orderForm) throws Exception{
    	
    	orderDTO orderDTO = new orderDTO();
    	
    	int cnt = orderDAO.OrderUpdate(orderForm);
    	
    	if(cnt>0) {
    		orderDTO.setResult("SUCCESS");
    	}
    	
    	return orderDTO;
    }
    
}
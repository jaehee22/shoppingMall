package com.shopping.bbs.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.shopping.bbs.dto.orderDTO;
import com.shopping.bbs.form.orderForm;
import com.shopping.bbs.service.orderService;
 
@Controller
@RequestMapping(value = "/order")
public class orderComtroller {
 
    @Autowired
    private orderService orderService;
   
    //�ֹ� ��� page
    @RequestMapping( value = "/orderList")
    public String orderList(HttpServletRequest request, HttpServletResponse response) throws Exception{
        
        return "order/orderList";
    }
 
    //�ֹ� ���
    @RequestMapping(value = "/OrderList")
    @ResponseBody
    public List<orderDTO> OrderList(HttpServletRequest request, HttpServletResponse response, orderForm orderForm) throws Exception {
        
    	List<orderDTO> orderDTO = orderService.OrderList(orderForm);
        
    	return orderDTO;
    }
        
    //�ֹ� �� page
    @RequestMapping( value = "/orderView")
    public String orderView(HttpServletRequest request, HttpServletResponse response) throws Exception{
        
        return "order/orderView";
    }
 
    //�ֹ� ��
    @RequestMapping(value = "/OrderView")
    @ResponseBody
    public  orderDTO OrderView(HttpServletRequest request, HttpServletResponse response, orderForm orderForm) throws Exception {
        
    	orderDTO orderDTO = orderService.OrderView(orderForm);

    	return orderDTO;
    }
    
    //�ֹ� �۹�ȣ
    @RequestMapping(value = "/GetNext")
    @ResponseBody
    public orderDTO GetNext(HttpServletRequest request, HttpServletResponse response, orderForm orderForm) throws Exception {
        
    	orderDTO orderDTO = orderService.OrderUpdate(orderForm);
        
    	return orderDTO;
    }
    
    //�Խñ� �ۼ� page
    @RequestMapping( value = "/orderWrite")
    public String orderWrite(HttpServletRequest request, HttpServletResponse response) throws Exception{
        
        return "order/orderWrite";
    }
 
    //�ֹ� �ۼ�
    @RequestMapping(value = "/OrderWrite")
    @ResponseBody
    public orderDTO OrderWrite(HttpServletRequest request, HttpServletResponse response, orderForm orderForm) throws Exception {
    	   	
    	orderDTO orderDTO = orderService.OrderWrite(orderForm);   
    	
    	return orderDTO;
    }
    
    //�ֹ� ����
    @RequestMapping(value = "/OrderDelete")
    @ResponseBody
    public orderDTO OrderDelete(HttpServletRequest request, HttpServletResponse response, orderForm orderForm) throws Exception {
    	
    	orderDTO orderDTO = orderService.OrderDelete(orderForm);
        
    	return orderDTO;
    }
    
    //�Խñ� ���� page
    @RequestMapping( value = "/orderUpdate")
    public String orderUpdate(HttpServletRequest request, HttpServletResponse response) throws Exception{
        
        return "order/orderUpdate";
    }
 
    //�Խñ� ����
    @RequestMapping(value = "/OrderUpdate")
    @ResponseBody
    public orderDTO OrderUpdate(HttpServletRequest request, HttpServletResponse response, orderForm orderForm) throws Exception {
            	
    	
    	orderDTO orderDTO = orderService.OrderUpdate(orderForm);
        
    	return orderDTO;
    }

    //�ּҰ˻� page
    @RequestMapping( value = "/jusoPopup")
    public String jusoPopup(HttpServletRequest request, HttpServletResponse response) throws Exception{
        
        return "order/jusoPopup";
    }

}

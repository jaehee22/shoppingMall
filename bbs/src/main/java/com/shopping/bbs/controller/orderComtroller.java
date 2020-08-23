package com.shopping.bbs.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.shopping.bbs.dto.orderDTO;
import com.shopping.bbs.dto.pagingDTO;
import com.shopping.bbs.form.orderForm;
import com.shopping.bbs.service.orderService;
 
@Controller
@RequestMapping(value = "/order")
public class orderComtroller {
 
    @Autowired
    private orderService orderService;
    
    //�ֹ� ��� (�ֹ�write�Ҷ� ������ ���)
    @RequestMapping(value = "/OrderList")
    @ResponseBody
    public List<orderDTO> OrderList(HttpServletRequest request, HttpServletResponse response, orderForm orderForm) throws Exception {
        
    	List<orderDTO> orderDTO = orderService.OrderList(orderForm);
        
    	return orderDTO;
    }
    
    //�ֹ� ��� page (�ֹ� �Ϸ�)
    @RequestMapping( value = "/orderBbs")
    public String orderBbs(HttpServletRequest request, HttpServletResponse response) throws Exception{
        
        return "order/orderBbs";
    }
    
    
    int pageNum = 0;

    //�ֹ���� (�ֹ��Ϸ�)
    @RequestMapping(value = "/OrderBbs")
    @ResponseBody
    public List<orderDTO> OrderBbs(HttpServletRequest request, HttpServletResponse response, orderForm orderForm) throws Exception {
        
    	//ī�װ��� �Խù� ����
    	int orderTotal = orderService.OrderTotal(orderForm);
    	//���������� ���� ���ù� ��
    	int postNum = 10;
    	//�� ����¡ ��ȣ ��
    	pageNum = (int)Math.ceil((double)orderTotal/postNum);
    	//��ϴ� ù������(bbsID)
    	int displayPost = (orderForm.getNum()-1)*postNum;
    	orderForm.setDisplayPost(displayPost);
    	orderForm.setPostNum(postNum);
    	List<orderDTO> orderDTO = orderService.OrderBbs(orderForm);
        
    	return orderDTO;
    }
    
    //����¡
    @RequestMapping(value = "/OrderPaging")
    @ResponseBody
    public pagingDTO OrderPaging(HttpServletRequest request, HttpServletResponse response,orderForm orderForm) throws Exception {    	
    	    	
    	//ī�װ��� �Խù� ����
    	int orderTotal = orderService.OrderTotal(orderForm);
    	//�ѹ��� ǥ���� ����¡ ��ȣ ����
    	int pageNum_cnt = 10;
    	//ǥ�õǴ� ������ ��ȣ �� ������ ��ȣ
    	int endPageNum = (int)(Math.ceil((double)orderForm.getNum()/(double)pageNum_cnt)*pageNum_cnt);
    	//ǥ�õǴ� ������ ��ȣ �� ù��° ��ȣ
    	int startPageNum = endPageNum - (pageNum_cnt -1);
    	//������ ��ȣ ����
    	int endPageNum_tmp = (int)(Math.ceil((double)orderTotal/(double)pageNum_cnt));
    	
    	if(endPageNum > endPageNum_tmp) {
    		endPageNum=endPageNum_tmp;
    	}
    	
    	boolean prev = startPageNum == 1 ? false : true;
    	boolean next = endPageNum * pageNum_cnt >= orderTotal ? false : true;
    	
    	pagingDTO pagingDTO = new pagingDTO();
    	pagingDTO.setPageNum(pageNum);
    	pagingDTO.setStartPageNum(startPageNum);
    	pagingDTO.setEndPageNum(endPageNum);
    	pagingDTO.setPrev(prev);
    	pagingDTO.setNext(next);
    	
    	return pagingDTO;
    }  
    
    //�ֹ� �� page
    @RequestMapping( value = "/orderView")
    public String orderView(HttpServletRequest request, HttpServletResponse response) throws Exception{
        
        return "order/orderView";
    }
 
    //�ֹ� ��
    @RequestMapping(value = "/OrderView")
    @ResponseBody
    public  List<orderDTO> OrderView(HttpServletRequest request, HttpServletResponse response, orderForm orderForm) throws Exception {
        
    	List<orderDTO> orderDTO = orderService.OrderView(orderForm);

    	return orderDTO;
    }
    
    //�ֹ� �۹�ȣ
    @RequestMapping(value = "/GetNext")
    @ResponseBody
    public orderDTO GetNext(HttpServletRequest request, HttpServletResponse response, orderForm orderForm) throws Exception {
        
    	orderDTO orderDTO = orderService.OrderUpdate(orderForm);
        
    	return orderDTO;
    }
    
    //�ֹ� �ۼ� page
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
    
    //�ֹ� �ۼ�(sub)
    @RequestMapping(value = "/SubOrderWrite")
    @ResponseBody
    public orderDTO SubOrderWrite(HttpServletRequest request, HttpServletResponse response, orderForm orderForm) throws Exception {
    	   	
    	orderDTO orderDTO = orderService.SubOrderWrite(orderForm);   
    	
    	return orderDTO;
    }
    
    //�ֹ��ۼ��Ϸ�(cart������ ����)
    @RequestMapping(value = "/SubOrderSuccess")
    @ResponseBody
    public orderDTO SubOrderSuccess(HttpServletRequest request, HttpServletResponse response, orderForm orderForm) throws Exception {
    	   	
    	orderDTO orderDTO = orderService.SubOrderSuccess(orderForm);   
    	
    	return orderDTO;
    }
    
    //�ֹ� ���� (�������)
    @RequestMapping(value = "/OrderDelete")
    @ResponseBody
    public orderDTO OrderDelete(HttpServletRequest request, HttpServletResponse response, orderForm orderForm) throws Exception {
    	
    	orderDTO orderDTO = orderService.OrderDelete(orderForm);
        
    	return orderDTO;
    }
    
    //���� ���� page
    @RequestMapping( value = "/orderUpdate")
    public String orderUpdate(HttpServletRequest request, HttpServletResponse response) throws Exception{
        
        return "order/orderUpdate";
    }
 
    //���� ����
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

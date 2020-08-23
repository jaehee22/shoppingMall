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
    
    //주문 목록 (주문write할때 구매할 목록)
    @RequestMapping(value = "/OrderList")
    @ResponseBody
    public List<orderDTO> OrderList(HttpServletRequest request, HttpServletResponse response, orderForm orderForm) throws Exception {
        
    	List<orderDTO> orderDTO = orderService.OrderList(orderForm);
        
    	return orderDTO;
    }
    
    //주문 목록 page (주문 완료)
    @RequestMapping( value = "/orderBbs")
    public String orderBbs(HttpServletRequest request, HttpServletResponse response) throws Exception{
        
        return "order/orderBbs";
    }
    
    
    int pageNum = 0;

    //주문목록 (주문완료)
    @RequestMapping(value = "/OrderBbs")
    @ResponseBody
    public List<orderDTO> OrderBbs(HttpServletRequest request, HttpServletResponse response, orderForm orderForm) throws Exception {
        
    	//카테고리별 게시물 갯수
    	int orderTotal = orderService.OrderTotal(orderForm);
    	//한페이지당 나올 개시물 수
    	int postNum = 10;
    	//총 페이징 번호 수
    	pageNum = (int)Math.ceil((double)orderTotal/postNum);
    	//블록당 첫페이지(bbsID)
    	int displayPost = (orderForm.getNum()-1)*postNum;
    	orderForm.setDisplayPost(displayPost);
    	orderForm.setPostNum(postNum);
    	List<orderDTO> orderDTO = orderService.OrderBbs(orderForm);
        
    	return orderDTO;
    }
    
    //페이징
    @RequestMapping(value = "/OrderPaging")
    @ResponseBody
    public pagingDTO OrderPaging(HttpServletRequest request, HttpServletResponse response,orderForm orderForm) throws Exception {    	
    	    	
    	//카테고리별 게시물 갯수
    	int orderTotal = orderService.OrderTotal(orderForm);
    	//한번에 표시할 페이징 번호 개수
    	int pageNum_cnt = 10;
    	//표시되는 페이지 번호 중 마지막 번호
    	int endPageNum = (int)(Math.ceil((double)orderForm.getNum()/(double)pageNum_cnt)*pageNum_cnt);
    	//표시되는 페이지 번호 중 첫번째 번호
    	int startPageNum = endPageNum - (pageNum_cnt -1);
    	//마지막 번호 재계산
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
    
    //주문 뷰 page
    @RequestMapping( value = "/orderView")
    public String orderView(HttpServletRequest request, HttpServletResponse response) throws Exception{
        
        return "order/orderView";
    }
 
    //주문 뷰
    @RequestMapping(value = "/OrderView")
    @ResponseBody
    public  List<orderDTO> OrderView(HttpServletRequest request, HttpServletResponse response, orderForm orderForm) throws Exception {
        
    	List<orderDTO> orderDTO = orderService.OrderView(orderForm);

    	return orderDTO;
    }
    
    //주문 글번호
    @RequestMapping(value = "/GetNext")
    @ResponseBody
    public orderDTO GetNext(HttpServletRequest request, HttpServletResponse response, orderForm orderForm) throws Exception {
        
    	orderDTO orderDTO = orderService.OrderUpdate(orderForm);
        
    	return orderDTO;
    }
    
    //주문 작성 page
    @RequestMapping( value = "/orderWrite")
    public String orderWrite(HttpServletRequest request, HttpServletResponse response) throws Exception{
        
        return "order/orderWrite";
    }
 
    //주문 작성
    @RequestMapping(value = "/OrderWrite")
    @ResponseBody
    public orderDTO OrderWrite(HttpServletRequest request, HttpServletResponse response, orderForm orderForm) throws Exception {
    	   	
    	orderDTO orderDTO = orderService.OrderWrite(orderForm);   
    	
    	return orderDTO;
    }
    
    //주문 작성(sub)
    @RequestMapping(value = "/SubOrderWrite")
    @ResponseBody
    public orderDTO SubOrderWrite(HttpServletRequest request, HttpServletResponse response, orderForm orderForm) throws Exception {
    	   	
    	orderDTO orderDTO = orderService.SubOrderWrite(orderForm);   
    	
    	return orderDTO;
    }
    
    //주문작성완료(cart데이터 삭제)
    @RequestMapping(value = "/SubOrderSuccess")
    @ResponseBody
    public orderDTO SubOrderSuccess(HttpServletRequest request, HttpServletResponse response, orderForm orderForm) throws Exception {
    	   	
    	orderDTO orderDTO = orderService.SubOrderSuccess(orderForm);   
    	
    	return orderDTO;
    }
    
    //주문 삭제 (구매취소)
    @RequestMapping(value = "/OrderDelete")
    @ResponseBody
    public orderDTO OrderDelete(HttpServletRequest request, HttpServletResponse response, orderForm orderForm) throws Exception {
    	
    	orderDTO orderDTO = orderService.OrderDelete(orderForm);
        
    	return orderDTO;
    }
    
    //구매 수정 page
    @RequestMapping( value = "/orderUpdate")
    public String orderUpdate(HttpServletRequest request, HttpServletResponse response) throws Exception{
        
        return "order/orderUpdate";
    }
 
    //구매 수정
    @RequestMapping(value = "/OrderUpdate")
    @ResponseBody
    public orderDTO OrderUpdate(HttpServletRequest request, HttpServletResponse response, orderForm orderForm) throws Exception {
            	
    	
    	orderDTO orderDTO = orderService.OrderUpdate(orderForm);
        
    	return orderDTO;
    }

    //주소검색 page
    @RequestMapping( value = "/jusoPopup")
    public String jusoPopup(HttpServletRequest request, HttpServletResponse response) throws Exception{
        
        return "order/jusoPopup";
    }

}

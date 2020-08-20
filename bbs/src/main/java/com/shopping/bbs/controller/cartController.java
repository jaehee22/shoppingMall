package com.shopping.bbs.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.shopping.bbs.dto.cartDTO;
import com.shopping.bbs.dto.pagingDTO;
import com.shopping.bbs.form.cartForm;
import com.shopping.bbs.service.cartService;
 
@Controller
@RequestMapping(value = "/cart")
public class cartController {
 
    @Autowired
    private cartService cartService;
    int pageNum = 0;
    
    
    
    //장바구니 bbs page
    @RequestMapping( value = "/cartBbs")
    public String cartBbs(HttpServletRequest request, HttpServletResponse response) throws Exception{
        
        return "cart/cartBbs";
    }
    
    //장바구니 목록
    @RequestMapping(value = "/CartList")
    @ResponseBody
    public List<cartDTO> CartList(HttpServletRequest request, HttpServletResponse response, cartForm cartForm) throws Exception {
    	//카테고리별 게시물 갯수
    	int cartTotal = cartService.CartTotal(cartForm);
    	//한페이지당 나올 개시물 수
    	int postNum = 5;
    	//총 페이징 번호 수
    	pageNum = (int)Math.ceil((double)cartTotal/postNum);
    	//블록당 첫페이지
    	int displayPost = (cartForm.getNum()-1)*postNum;
    	cartForm.setDisplayPost(displayPost);
    	cartForm.setPostNum(postNum);
        	
    	List<cartDTO> cartDTO = cartService.CartList(cartForm);

    	return cartDTO;
    }               
    
    @RequestMapping(value = "/CartPaging")
    @ResponseBody
    public pagingDTO Paging(HttpServletRequest request, HttpServletResponse response,cartForm cartForm) throws Exception {    	
    	    	
    	//카테고리별 게시물 갯수
    	int cartTotal = cartService.CartTotal(cartForm);
    	//한번에 표시할 페이징 번호 개수
    	int pageNum_cnt = 6;
    	//표시되는 페이지 번호 중 마지막 번호
    	int endPageNum = (int)(Math.ceil((double)cartForm.getNum()/(double)pageNum_cnt)*pageNum_cnt);
    	//표시되는 페이지 번호 중 첫번째 번호
    	int startPageNum = endPageNum - (pageNum_cnt -1);
    	//마지막 번호 재계산
    	int endPageNum_tmp = (int)(Math.ceil((double)cartTotal/(double)pageNum_cnt));

    	if(endPageNum > endPageNum_tmp) {
    		endPageNum=endPageNum_tmp;
    	}
    	
    	boolean prev = startPageNum == 1 ? false : true;
    	boolean next = endPageNum * pageNum_cnt >= cartTotal ? false : true;
    	
    	pagingDTO pagingDTO = new pagingDTO();
    	pagingDTO.setPageNum(pageNum);
    	pagingDTO.setStartPageNum(startPageNum);
    	pagingDTO.setEndPageNum(endPageNum);
    	pagingDTO.setPrev(prev);
    	pagingDTO.setNext(next);
    	
    	return pagingDTO;
    }  
    
    //장바구니에 이미 담겨있는 상품인지
    @RequestMapping(value = "/CartView")
    @ResponseBody
    public cartDTO CartView(HttpServletRequest request, HttpServletResponse response, cartForm cartForm) throws Exception {
        
    	cartDTO cartDTO = cartService.CartView(cartForm);

    	return cartDTO;
    }

    //cartID
    @RequestMapping(value = "/GetNext")
    @ResponseBody
    public cartDTO GetNext(HttpServletRequest request, HttpServletResponse response, cartForm cartForm) throws Exception {
        
    	cartDTO cartDTO = cartService.CartUpdate(cartForm);
        
    	return cartDTO;
    }
     
    //장바구니 작성
    @RequestMapping(value = "/CartWrite")
    @ResponseBody
    public cartDTO CartWrite(HttpServletRequest request, HttpServletResponse response, cartForm cartForm) throws Exception {
    	    	
    	cartDTO cartDTO = cartService.CartWrite(cartForm);   

    	return cartDTO;
    }
       
    //장바구니 삭제
    @RequestMapping(value = "/CartDelete")
    @ResponseBody
    public cartDTO CartDelete(HttpServletRequest request, HttpServletResponse response, cartForm cartForm) throws Exception {
    	
    	cartDTO cartDTO = cartService.CartDelete(cartForm);
        
    	return cartDTO;
    }

    //장바구니 수정
    @RequestMapping(value = "/CartUpdate")
    @ResponseBody
    public cartDTO CartUpdate(HttpServletRequest request, HttpServletResponse response, cartForm cartForm) throws Exception {            
    	
    	cartDTO cartDTO = cartService.CartUpdate(cartForm);
        
    	return cartDTO;
    }


}

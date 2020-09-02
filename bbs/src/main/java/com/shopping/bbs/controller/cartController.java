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
    
    //장바구니 bbs page
    @RequestMapping( value = "/cartBbs")
    public String cartBbs(HttpServletRequest request, HttpServletResponse response) throws Exception{
        
        return "cart/cartBbs";
    }
    
    //장바구니 목록
    @RequestMapping(value = "/CartList")
    @ResponseBody
    public List<cartDTO> CartList(HttpServletRequest request, HttpServletResponse response, cartForm cartForm) throws Exception {
    	//한페이지당 나올 개시물 수
    	int postNum = 5;
    	//블록당 첫페이지
    	int displayPost = (cartForm.getCartNum()-1)*postNum;
    	cartForm.setDisplayPost(displayPost);
    	cartForm.setPostNum(postNum);
        	
    	List<cartDTO> cartDTO = cartService.CartList(cartForm);

    	return cartDTO;
    }               
    
    @RequestMapping(value = "/CartPaging")
    @ResponseBody
    public pagingDTO Paging(HttpServletRequest request, HttpServletResponse response,cartForm cartForm) throws Exception {    	
    	//한페이지당 나올 개시물 수
    	int postNum = 5;
    	
    	//카테고리별 게시물 갯수
    	int cartTotal = cartService.CartTotal(cartForm);
    	//한번에 표시할 페이징 번호 개수
    	int pageNum_cnt = 6;
    	//표시되는 페이지 번호 중 마지막 번호
    	int endPageNum = (int)(Math.ceil((double)cartForm.getCartNum()/(double)pageNum_cnt)*pageNum_cnt);
    	//표시되는 페이지 번호 중 첫번째 번호
    	int startPageNum = endPageNum - (pageNum_cnt -1);
    	//마지막 번호 재계산
    	int endPageNum_tmp = (int)(Math.ceil((double)cartTotal/(double)postNum));

    	if(endPageNum > endPageNum_tmp) {
    		endPageNum=endPageNum_tmp;
    	}
    	
    	boolean prev = startPageNum == 1 ? false : true;
    	boolean next = endPageNum * pageNum_cnt >= cartTotal ? false : true;
    	
    	pagingDTO pagingDTO = new pagingDTO();
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

    //장바구니에서 선택한 물품 주문으로update
    @RequestMapping(value = "/CartOrder")
    @ResponseBody
    public  cartDTO CheckOrder(HttpServletRequest request, HttpServletResponse response, cartForm cartForm) throws Exception {
        
    	cartDTO cartDTO = cartService.CartOrder(cartForm);

    	return cartDTO;
    }

    //view에서 직접구매
    @RequestMapping(value = "/ThisOrder")
    @ResponseBody
    public  cartDTO ThisOrder(HttpServletRequest request, HttpServletResponse response, cartForm cartForm) throws Exception {
        
    	cartDTO cartDTO = cartService.ThisOrder(cartForm);

    	return cartDTO;
    }
    
 	//구매창에서 나갔을 때(장바구니)
    @RequestMapping(value = "/CartOrderReset")
    @ResponseBody
    public  cartDTO CartOrderReset(HttpServletRequest request, HttpServletResponse response, cartForm cartForm) throws Exception {
        
    	cartDTO cartDTO = cartService.CartOrderReset(cartForm);

    	return cartDTO;
    }
 	
    //구매창에서 나갔을 때(bbs)
    @RequestMapping(value = "/BbsOrderReset")
    @ResponseBody
    public  cartDTO BbsOrderReset(HttpServletRequest request, HttpServletResponse response, cartForm cartForm) throws Exception {
        
    	cartDTO cartDTO = cartService.BbsOrderReset(cartForm);

    	return cartDTO;
    }
}

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
    
    //��ٱ��� bbs page
    @RequestMapping( value = "/cartBbs")
    public String cartBbs(HttpServletRequest request, HttpServletResponse response) throws Exception{
        
        return "cart/cartBbs";
    }
    
    //��ٱ��� ���
    @RequestMapping(value = "/CartList")
    @ResponseBody
    public List<cartDTO> CartList(HttpServletRequest request, HttpServletResponse response, cartForm cartForm) throws Exception {
    	//���������� ���� ���ù� ��
    	int postNum = 5;
    	//��ϴ� ù������
    	int displayPost = (cartForm.getCartNum()-1)*postNum;
    	cartForm.setDisplayPost(displayPost);
    	cartForm.setPostNum(postNum);
        	
    	List<cartDTO> cartDTO = cartService.CartList(cartForm);

    	return cartDTO;
    }               
    
    @RequestMapping(value = "/CartPaging")
    @ResponseBody
    public pagingDTO Paging(HttpServletRequest request, HttpServletResponse response,cartForm cartForm) throws Exception {    	
    	//���������� ���� ���ù� ��
    	int postNum = 5;
    	
    	//ī�װ��� �Խù� ����
    	int cartTotal = cartService.CartTotal(cartForm);
    	//�ѹ��� ǥ���� ����¡ ��ȣ ����
    	int pageNum_cnt = 6;
    	//ǥ�õǴ� ������ ��ȣ �� ������ ��ȣ
    	int endPageNum = (int)(Math.ceil((double)cartForm.getCartNum()/(double)pageNum_cnt)*pageNum_cnt);
    	//ǥ�õǴ� ������ ��ȣ �� ù��° ��ȣ
    	int startPageNum = endPageNum - (pageNum_cnt -1);
    	//������ ��ȣ ����
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
    
    //��ٱ��Ͽ� �̹� ����ִ� ��ǰ����
    @RequestMapping(value = "/CartView")
    @ResponseBody
    public cartDTO CartView(HttpServletRequest request, HttpServletResponse response, cartForm cartForm) throws Exception {
        
    	cartDTO cartDTO = cartService.CartView(cartForm);

    	return cartDTO;
    }
     
    //��ٱ��� �ۼ�
    @RequestMapping(value = "/CartWrite")
    @ResponseBody
    public cartDTO CartWrite(HttpServletRequest request, HttpServletResponse response, cartForm cartForm) throws Exception {
    	    	
    	cartDTO cartDTO = cartService.CartWrite(cartForm);   

    	return cartDTO;
    }
       
    //��ٱ��� ����
    @RequestMapping(value = "/CartDelete")
    @ResponseBody
    public cartDTO CartDelete(HttpServletRequest request, HttpServletResponse response, cartForm cartForm) throws Exception {
    	
    	cartDTO cartDTO = cartService.CartDelete(cartForm);
        
    	return cartDTO;
    }

    //��ٱ��� ����
    @RequestMapping(value = "/CartUpdate")
    @ResponseBody
    public cartDTO CartUpdate(HttpServletRequest request, HttpServletResponse response, cartForm cartForm) throws Exception {            
    	
    	cartDTO cartDTO = cartService.CartUpdate(cartForm);
        
    	return cartDTO;
    }

    //��ٱ��Ͽ��� ������ ��ǰ �ֹ�����update
    @RequestMapping(value = "/CartOrder")
    @ResponseBody
    public  cartDTO CheckOrder(HttpServletRequest request, HttpServletResponse response, cartForm cartForm) throws Exception {
        
    	cartDTO cartDTO = cartService.CartOrder(cartForm);

    	return cartDTO;
    }

    //view���� ��������
    @RequestMapping(value = "/ThisOrder")
    @ResponseBody
    public  cartDTO ThisOrder(HttpServletRequest request, HttpServletResponse response, cartForm cartForm) throws Exception {
        
    	cartDTO cartDTO = cartService.ThisOrder(cartForm);

    	return cartDTO;
    }
    
 	//����â���� ������ ��(��ٱ���)
    @RequestMapping(value = "/CartOrderReset")
    @ResponseBody
    public  cartDTO CartOrderReset(HttpServletRequest request, HttpServletResponse response, cartForm cartForm) throws Exception {
        
    	cartDTO cartDTO = cartService.CartOrderReset(cartForm);

    	return cartDTO;
    }
 	
    //����â���� ������ ��(bbs)
    @RequestMapping(value = "/BbsOrderReset")
    @ResponseBody
    public  cartDTO BbsOrderReset(HttpServletRequest request, HttpServletResponse response, cartForm cartForm) throws Exception {
        
    	cartDTO cartDTO = cartService.BbsOrderReset(cartForm);

    	return cartDTO;
    }
}

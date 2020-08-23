package com.shopping.bbs.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
 
import com.shopping.bbs.dao.cartDAO;
import com.shopping.bbs.dto.cartDTO;
import com.shopping.bbs.form.cartForm;
 
@Service
public class cartService {
 
    @Autowired
    private cartDAO cartDAO;
 
    //카트 목록
    public List<cartDTO> CartList(cartForm cartForm) throws Exception {
    	    	
        return cartDAO.CartList(cartForm);
    }

    //카트 총 갯수
    public int CartTotal(cartForm cartForm) throws Exception{
    	return cartDAO.CartTotal(cartForm);
    }
    
    //카트 뷰
    public cartDTO CartView(cartForm cartForm) throws Exception {
    	
    	cartDTO cartDTO = new cartDTO();
    	
    	cartDTO = cartDAO.CartView(cartForm);
    	
    	return cartDTO;
    }
    
    //카트 등록
    public cartDTO CartWrite(cartForm cartForm) throws Exception{
    	
    	cartDTO cartDTO = new cartDTO();
    
        cartForm.setCartID(cartDAO.GetNext());
    	
    	int cnt = cartDAO.CartWrite(cartForm);
    	
    	if(cnt>0) {
    		cartDTO.setResult("SUCCESS");
    	}
    	
    	return cartDTO;
    }
    
    //카트 삭제
    public cartDTO CartDelete(cartForm cartForm) throws Exception{
    	
    	cartDTO cartDTO = new cartDTO();
    	
    	int cnt = cartDAO.CartDelete(cartForm);
    	
    	if(cnt>0) {
    		cartDTO.setResult("SUCCESS");
    	}
    	
    	return cartDTO;
    }
    
    //카트 수정
    public cartDTO CartUpdate(cartForm cartForm) throws Exception{
    	
    	cartDTO cartDTO = new cartDTO();
    	
    	int cnt = cartDAO.CartUpdate(cartForm);
    	
    	if(cnt>0) {
    		cartDTO.setResult("SUCCESS");
    	}
    	
    	return cartDTO;
    }
    
    //장바구니 선택한 물품 order로 update
    public cartDTO CartOrder(cartForm cartForm) throws Exception{
    	
    	cartDTO cartDTO = new cartDTO();
    	
    	int cnt = cartDAO.CartOrder(cartForm);
    	
    	if(cnt>0) {
    		cartDTO.setResult("SUCCESS");
    	}
    	
    	return cartDTO;
    }
    
    //view에서 직접구매
    public cartDTO ThisOrder(cartForm cartForm) throws Exception{
    	
    	cartDTO cartDTO = new cartDTO();
    
        cartForm.setCartID(cartDAO.GetNext());
    	
    	int cnt = cartDAO.ThisOrder(cartForm);
    	
    	if(cnt>0) {
    		cartDTO.setResult("SUCCESS");
    	}
    	
    	return cartDTO;
    }
    
    //구매창에서 나갔을 때(장바구니)
    public cartDTO CartOrderReset(cartForm cartForm) throws Exception{
    	
    	cartDTO cartDTO = new cartDTO();
    	
    	int cnt = cartDAO.CartOrderReset(cartForm);
    	
    	if(cnt>0) {
    		cartDTO.setResult("SUCCESS");
    	}
    	
    	return cartDTO;
    }
    
    //구매창에서 나갔을 때(bbs)
    public cartDTO BbsOrderReset(cartForm cartForm) throws Exception{
    	
    	cartDTO cartDTO = new cartDTO();
    	
    	int cnt = cartDAO.BbsOrderReset(cartForm);
    	
    	if(cnt>0) {
    		cartDTO.setResult("SUCCESS");
    	}
    	
    	return cartDTO;
    }
}
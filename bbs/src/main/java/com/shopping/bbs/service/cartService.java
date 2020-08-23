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
 
    //īƮ ���
    public List<cartDTO> CartList(cartForm cartForm) throws Exception {
    	    	
        return cartDAO.CartList(cartForm);
    }

    //īƮ �� ����
    public int CartTotal(cartForm cartForm) throws Exception{
    	return cartDAO.CartTotal(cartForm);
    }
    
    //īƮ ��
    public cartDTO CartView(cartForm cartForm) throws Exception {
    	
    	cartDTO cartDTO = new cartDTO();
    	
    	cartDTO = cartDAO.CartView(cartForm);
    	
    	return cartDTO;
    }
    
    //īƮ ���
    public cartDTO CartWrite(cartForm cartForm) throws Exception{
    	
    	cartDTO cartDTO = new cartDTO();
    
        cartForm.setCartID(cartDAO.GetNext());
    	
    	int cnt = cartDAO.CartWrite(cartForm);
    	
    	if(cnt>0) {
    		cartDTO.setResult("SUCCESS");
    	}
    	
    	return cartDTO;
    }
    
    //īƮ ����
    public cartDTO CartDelete(cartForm cartForm) throws Exception{
    	
    	cartDTO cartDTO = new cartDTO();
    	
    	int cnt = cartDAO.CartDelete(cartForm);
    	
    	if(cnt>0) {
    		cartDTO.setResult("SUCCESS");
    	}
    	
    	return cartDTO;
    }
    
    //īƮ ����
    public cartDTO CartUpdate(cartForm cartForm) throws Exception{
    	
    	cartDTO cartDTO = new cartDTO();
    	
    	int cnt = cartDAO.CartUpdate(cartForm);
    	
    	if(cnt>0) {
    		cartDTO.setResult("SUCCESS");
    	}
    	
    	return cartDTO;
    }
    
    //��ٱ��� ������ ��ǰ order�� update
    public cartDTO CartOrder(cartForm cartForm) throws Exception{
    	
    	cartDTO cartDTO = new cartDTO();
    	
    	int cnt = cartDAO.CartOrder(cartForm);
    	
    	if(cnt>0) {
    		cartDTO.setResult("SUCCESS");
    	}
    	
    	return cartDTO;
    }
    
    //view���� ��������
    public cartDTO ThisOrder(cartForm cartForm) throws Exception{
    	
    	cartDTO cartDTO = new cartDTO();
    
        cartForm.setCartID(cartDAO.GetNext());
    	
    	int cnt = cartDAO.ThisOrder(cartForm);
    	
    	if(cnt>0) {
    		cartDTO.setResult("SUCCESS");
    	}
    	
    	return cartDTO;
    }
    
    //����â���� ������ ��(��ٱ���)
    public cartDTO CartOrderReset(cartForm cartForm) throws Exception{
    	
    	cartDTO cartDTO = new cartDTO();
    	
    	int cnt = cartDAO.CartOrderReset(cartForm);
    	
    	if(cnt>0) {
    		cartDTO.setResult("SUCCESS");
    	}
    	
    	return cartDTO;
    }
    
    //����â���� ������ ��(bbs)
    public cartDTO BbsOrderReset(cartForm cartForm) throws Exception{
    	
    	cartDTO cartDTO = new cartDTO();
    	
    	int cnt = cartDAO.BbsOrderReset(cartForm);
    	
    	if(cnt>0) {
    		cartDTO.setResult("SUCCESS");
    	}
    	
    	return cartDTO;
    }
}
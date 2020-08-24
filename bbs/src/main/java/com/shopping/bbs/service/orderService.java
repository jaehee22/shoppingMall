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
 
    //�ֹ� ��� (�ֹ�write�Ҷ� ������ ���)
    public List<orderDTO> OrderList(orderForm orderForm) throws Exception {
 
        return orderDAO.OrderList(orderForm);
    }
    
    //�ֹ� ��� (�ֹ��Ϸ�)
    public List<orderDTO> OrderBbs(orderForm orderForm) throws Exception {
    	
    	List<orderDTO> orderDTO = orderDAO.OrderBbs(orderForm);
    	
    	orderForm count = new orderForm(); 
    	
    	for(int i=0; i<orderDTO.size(); i++) {
    		count.setOrderID(orderDTO.get(i).getOrderID());
    		orderDTO.get(i).setItems(orderDAO.Items(count));
    	}
    	
    	return orderDTO;
    }
    //�ֹ� �� ����
    public int OrderTotal(orderForm orderForm) throws Exception{
    	return orderDAO.OrderTotal(orderForm);
    }
    
    //�ֹ� ��
    public List<orderDTO> OrderView(orderForm orderForm) throws Exception {
    	
    	return orderDAO.OrderView(orderForm);
    }
    
    //�ֹ� ���
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
   
    //�ֹ� ���(sub) && �Ǹŷ� �߰�
    public orderDTO SubOrderWrite(orderForm orderForm) throws Exception{
    	
    	//��� list
    	List<orderForm> SubOrderForm = orderDAO.SubOrderList(orderForm);
    	
    	//�Ǹŷ� �߰� Form 
    	orderForm sell = new orderForm();
    	
    	//�ϼ��ƴٴ°� �˷��ִ� DTO
    	orderDTO orderDTO = new orderDTO();
	
    	int cnt = 0;

    	for(int i=0; i<SubOrderForm.size(); i++) {
    		SubOrderForm.get(i).setOrderID(orderForm.getOrderID());
    		sell.setBbsID(SubOrderForm.get(i).getBbsID());
    		sell.setAmount(SubOrderForm.get(i).getAmount());
    		orderDAO.SellUpdate(sell);
        	orderDAO.SubOrderWrite(SubOrderForm.get(i));
        	cnt++;
    	}
    	
    	if(cnt>0) {
    		orderDTO.setResult("SUCCESS");
    	}
    	
    	return orderDTO;
    }
    
    //�ֹ��Ϸ�� cart������ ���� 
    public orderDTO SubOrderSuccess(orderForm orderForm) throws Exception{
    	    	
    	orderDTO orderDTO = new orderDTO();
    	
    	int cnt = orderDAO.SubOrderSuccess(orderForm);
    	
    	if(cnt>0) {
    		orderDTO.setResult("SUCCESS");
    	}
    	
    	return orderDTO;
    }
    
    
    //�ֹ� ����
    public orderDTO OrderDelete(orderForm orderForm) throws Exception{
    	
    	//�Ǹŷ� ���� ����Ʈ
    	List<orderForm> sellRefund = orderDAO.SellRefund1(orderForm);
    	
    	//�ֹ���1���� dto
    	orderDTO orderDTO = new orderDTO();

    	for(int i=0; i<sellRefund.size(); i++) {
    		orderDAO.SellRefund2(sellRefund.get(i));
    	}
    	
    	int cnt = orderDAO.OrderDelete(orderForm);
    	
    	if(cnt>0) {
    		orderDTO.setResult("SUCCESS");
    	}
    	
    	return orderDTO;
    }
    
    //�ֹ� ����
    public orderDTO OrderUpdate(orderForm orderForm) throws Exception{
    	
    	orderDTO orderDTO = new orderDTO();
    	
    	int cnt = orderDAO.OrderUpdate(orderForm);
    	
    	if(cnt>0) {
    		orderDTO.setResult("SUCCESS");
    	}
    	
    	return orderDTO;
    }
    //��ۻ��� ����
    public orderDTO DeliveryUpdate(orderForm orderForm) throws Exception{
    	
    	orderDTO orderDTO = new orderDTO();
    	
    	int cnt = orderDAO.DeliveryUpdate(orderForm);
    	
    	if(cnt>0) {
    		orderDTO.setResult("SUCCESS");
    	}
    	
    	return orderDTO;
    }
    
    //ȸ�� �ֹ� ���
    public List<orderDTO> UserOrder(orderForm orderForm) throws Exception {
    	
    	List<orderDTO> orderDTO = orderDAO.UserOrder(orderForm);
    	
    	orderForm count = new orderForm(); 
    	
    	for(int i=0; i<orderDTO.size(); i++) {
    		count.setOrderID(orderDTO.get(i).getOrderID());
    		orderDTO.get(i).setItems(orderDAO.Items(count));
    	}
    	
    	return orderDTO;
    }
    //�ֹ� �� ����
    public int UserOrderTotal(orderForm orderForm) throws Exception{
    	return orderDAO.UserOrderTotal(orderForm);
    }
}
package com.shopping.bbs.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
 
import com.shopping.bbs.dao.bbsDAO;
import com.shopping.bbs.dto.bbsDTO;
import com.shopping.bbs.form.bbsForm;
 
@Service
public class bbsService {
 
    @Autowired
    private bbsDAO bbsDAO;
 
    public List<bbsDTO> BbsList(bbsForm bbsForm) throws Exception {
 
        return bbsDAO.BbsList(bbsForm);
    }
}

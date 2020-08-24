package com.shopping.bbs.dao;

import java.util.List;
 
import javax.annotation.Resource;
 
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.shopping.bbs.dto.userDTO;
import com.shopping.bbs.form.userForm;
 
@Repository
public class userDAO {
 
    @Resource(name = "sqlSession")
    private SqlSession sqlSession;
 
    private static final String NAMESPACE = "com.shopping.bbs.userMapper";
 
    //�α���
    public userDTO Login(userForm userForm) throws Exception {
        return sqlSession.selectOne(NAMESPACE + ".Login", userForm);
    }
    
    //ȸ������
    public int Join(userForm userForm) throws Exception {
        return sqlSession.insert(NAMESPACE + ".Join", userForm);
    }
    
    //ȸ���ߺ�Ȯ��
    public int ExistUser(userForm userForm) throws Exception {
        int result = sqlSession.selectOne(NAMESPACE + ".ExistUser", userForm);
        
        return result;
    }
    
    //ȸ�� ��
    public userDTO UserView(userForm userForm) throws Exception {
        return sqlSession.selectOne(NAMESPACE + ".UserView", userForm);
    }
    //�� ����
    public int UserTotal(userForm userForm) throws Exception{
    	return sqlSession.selectOne(NAMESPACE + ".UserTotal", userForm);
    }
    //ȸ������Ʈ
    public List<userDTO> UserList(userForm userForm) throws Exception {
        return sqlSession.selectList(NAMESPACE + ".UserList", userForm);
    }
    
    //ȸ������//ȸ��Ż��
    public int UserDelete(userForm userForm) throws Exception {
        return sqlSession.delete(NAMESPACE + ".UserDelete", userForm);
    }
    
    //ȸ�� ����
    public int UserUpdate(userForm userForm) throws Exception {
        return sqlSession.update(NAMESPACE + ".UserUpdate", userForm);
    }
}

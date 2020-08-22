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
 
    //로그인
    public userDTO Login(userForm userForm) throws Exception {
        return sqlSession.selectOne(NAMESPACE + ".Login", userForm);
    }
    
    //회원가입
    public int Join(userForm userForm) throws Exception {
        return sqlSession.insert(NAMESPACE + ".Join", userForm);
    }
    
    //회원중복확인
    public int ExistUser(userForm userForm) throws Exception {
        int result = sqlSession.selectOne(NAMESPACE + ".ExistUser", userForm);
        
        return result;
    }
    
    //회원 뷰
    public userDTO UserView(userForm userForm) throws Exception {
        return sqlSession.selectOne(NAMESPACE + ".UserView", userForm);
    }
    
    //회원리스트
    public List<userDTO> UserList(userForm userForm) throws Exception {
        return sqlSession.selectList(NAMESPACE + ".UserList", userForm);
    }
    
    //회원삭제//회원탈퇴
    public int UserDelete(userForm userForm) throws Exception {
        return sqlSession.delete(NAMESPACE + ".UserDelete", userForm);
    }
    
    //회원 수정
    public int UserUpdate(userForm userForm) throws Exception {
        return sqlSession.update(NAMESPACE + ".UserUpdate", userForm);
    }
}

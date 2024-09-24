package com.javaex.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.UserVo;


@Repository
public class UserDao {
	
	//필드
	@Autowired
	private SqlSession sqlSession;
	
	//생성자
	//메서드 gs
	//메서드 일반
	
	public int insertUser( UserVo userVo  ) {
		
		System.out.println("UserDao.insertUser()");
		
		System.out.println(userVo);
		
		int count = sqlSession.insert("user.insert", userVo);
		
		return count;
		
	}
	
	
	public UserVo selectUser ( UserVo userVo ) {
		
		System.out.println("UserDao.selectUser()");
		
		System.out.println(userVo);
		
		UserVo authUser = sqlSession.selectOne("user.selectByIdPw", userVo);
		
		return authUser;
		
	}
	
	
	public UserVo getUserOne ( UserVo userVo ) {
		
		System.out.println("UserDao.getUserOne()");
		
		UserVo authUser = sqlSession.selectOne("user.selectByNoName", userVo);
		
		System.out.println("getUserOne.User : " + authUser);
		
		return authUser;
	}
	
	public UserVo updateUser ( UserVo userVo ) {
		
		System.out.println("UserDao.updateUser()");
		
		int count = sqlSession.update("user.update", userVo);
		
		if (count == 1) { return userVo;  }
		else { return null; }
		
		
	}
	
	
	
	//id로 데이터 가져오기 - id 사용여부 체크할떄 사용
	public int selectUserById ( String id ) {
		
		System.out.println("UserDao.selectUserById()");
		//System.out.println(id);
		
		int count = sqlSession.selectOne("user.selectById", id);
		//System.out.println(count);
		
		
		return count;
	}
	
	
	
	
	
	

}

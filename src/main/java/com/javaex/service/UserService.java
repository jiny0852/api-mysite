package com.javaex.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.UserDao;
import com.javaex.vo.UserVo;

@Service
public class UserService {
	
	//필드
	@Autowired
	private UserDao userDao;
	
	//생성자
	//메서드 gs
	//메서드 일반
	
	public int exeJoinUser( UserVo userVo ) {
		
		System.out.println("UserService.exeJoinUser()");
		
		System.out.println("UserService.exeJoinUser(userVo) : " + userVo);
		
		int count = userDao.insertUser(userVo);
		
		return count;
		
	}
	
	public UserVo exeLogin ( UserVo userVo ) {
		
		System.out.println("UserService.exeLogin()");
		
		UserVo authUser = userDao.selectUser(userVo);
		
		return authUser;
		
	}
	
	public UserVo exeGetUserOne ( UserVo userVo ) {
		
		System.out.println("UserService.exeGetUserOne()");
		
		UserVo authUser = userDao.getUserOne(userVo);
		
		
		return authUser;
	}
	
	public UserVo exeUpdateUser ( UserVo userVo ) {
		
		System.out.println("UserService.exeUpdateUser()");
		
		UserVo authUser = userDao.updateUser(userVo);
		
		return authUser;
		
	}
	
	/* 아이디 체크 */
	public boolean exeIdCheck ( String id ) {
		
		System.out.println("UserService.exeIdCheck()");
		
		int count = userDao.selectUserById(id);
		
		
		if ( count >= 1 ) {
			return false;
		} else {
			return true;
		}
		
		/*
		boolean result;
		
		if ( count >= 1 ) {
			result = false;
		} else { //if ( count == 0 ) {
			result = true;
		}
		
		return result;
		*/
	}
	
	

}

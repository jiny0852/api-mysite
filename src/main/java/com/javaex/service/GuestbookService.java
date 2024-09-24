package com.javaex.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.GuestbookDao;
import com.javaex.vo.GuestbookVo;

@Service
public class GuestbookService {
	
	@Autowired
	private GuestbookDao guestbookDao;
	
	
	public List<GuestbookVo> exeGetList () {
		
		System.out.println("GuestbookService.exeGetList()");
		
		List<GuestbookVo> guestList = guestbookDao.getGuestbookList();
		
		return guestList;
		
	}
	
	public GuestbookVo exeInsert( GuestbookVo guestbookVo ) {
		
		System.out.println("GuestbokService.exeInsret()");
		
		GuestbookVo addGuestbook = guestbookDao.insertGuestbook(guestbookVo);
		
		return addGuestbook;
	}
	
	
	public int exeDelete( GuestbookVo guestbookVo ) {
		
		System.out.println("GuestbokService.exeDelete()");
		
		int count = guestbookDao.deleteGuestbook(guestbookVo);
		
		return count;
	}
	
	
	public GuestbookVo exeAdd( GuestbookVo guestbookVo ) {
		
		System.out.println("GuestbokService.exeAdd()");
		
		//저장
		//int num = guestbookDao.insertSelectKey(guestbookVo);
		guestbookDao.insertSelectKey(guestbookVo);
		
		//1명 데이터 가져오기
		//GuestbookVo addGuestbook = guestbookDao.getGuestOne(num);
		GuestbookVo addGuestbook = guestbookDao.getGuestOne(guestbookVo.getNo());
		
		return addGuestbook;
	}
	
	
	public int exeRemove( GuestbookVo guestbookVo ) {
		
		System.out.println("GuestbokService.exeRemove()");
		
		int count = guestbookDao.deleteGuestbook(guestbookVo);
		
		
		return count;
		
	}
	
	

}

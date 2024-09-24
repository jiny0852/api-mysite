package com.javaex.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.GuestbookVo;

@Repository
public class GuestbookDao {
	
	@Autowired
	private SqlSession sqlSession;
	
	
	public List<GuestbookVo> getGuestbookList() {
		
		System.out.println("guestbookDao.getGuestbookList()");
		
		List<GuestbookVo> guestbookList = sqlSession.selectList("guestbook.selectList");
		
		return guestbookList;
		
	}
	
	public GuestbookVo insertGuestbook ( GuestbookVo guestbookVo ) {
		
		System.out.println("guestbookDao.insertGuestbook()");
		
		int count = sqlSession.insert("guestbook.insert", guestbookVo);
		
		if (count == 1) {
			return guestbookVo;
		} else {
			return null;
		}
	}
	
	public int deleteGuestbook ( GuestbookVo guestbookVo ) {
		
		System.out.println("guestbookDao.deleteGuestbook()");
		
		int count = sqlSession.delete("guestbook.delete", guestbookVo);
		
		return count;
		
	}
	
	public int insertSelectKey ( GuestbookVo guestbookVo ) {
		
		System.out.println("guestbookDao.insertSelectKey()");
		
		//System.out.println("guestbookVo before: " + guestbookVo);
		
		int count = sqlSession.insert("guestbook.insertSelectKey", guestbookVo);
		
		//System.out.println("guestbookVo after: " + guestbookVo);
		
		
		//GuestbookVo addGuestVo = sqlSession.selectOne("guestbook.selectGuestOne", guestbookVo.getNo());
				
		return count;
		
/*		if (count == 1) {
			return guestbookVo.getNo();//addGuestVo;
		} else {
			return 0;
		}
	}*/
	}	
	
	public GuestbookVo getGuestOne(int no) {
		
		System.out.println("guestbookDao.getGuestOne()");
		
		GuestbookVo guestbookVo = sqlSession.selectOne("guestbook.selectGuestOne", no);
		
		return guestbookVo;
		
	}
	
	

}

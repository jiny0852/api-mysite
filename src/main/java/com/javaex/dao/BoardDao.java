package com.javaex.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.BoardVo;

@Repository
public class BoardDao {
	
	@Autowired
	private SqlSession sqlSession;
	
	public List<BoardVo> getList () {
		
		System.out.println("boardDao.getList()");
		
		List<BoardVo> boardList = sqlSession.selectList("board.selectList");
		
		
		return boardList;
		
	}
	
	public BoardVo getRead (int no) {
		
		System.out.println("boardDao.getRead()");
		
		//BoardVo boardVo = sqlSession.selectOne("board.selectRead", no);
		BoardVo boardVo = sqlSession.selectOne("board.selectRead", no);
		
		return boardVo;
	}

	public BoardVo getInsert ( BoardVo boardVo ) {
		
		System.out.println("boardDao.getInsert()");
		
		int count = sqlSession.insert("board.insert", boardVo);		
		
		if (count == 1) {
			return boardVo;
		} else {
			return null;
		}
	}
	
	public BoardVo getUpdate ( BoardVo boardVo ) {
		
		System.out.println("boardDao.getUpdate()");
		
		int count = sqlSession.update("board.update", boardVo);	
		
		
		if (count == 1) {
			return boardVo;
		} else {
			return null;
		}
		
	}
	
	public int increaseHitCount ( int no ) {
		
		System.out.println("boardDao.increaseHitCount()");
		
		int result = sqlSession.update("board.increaseHitCount", no);	
		
		if (result == 1) {
			return result;
		} else {
			return 0;
		}
		
	}
	
	
	
	
	
	
	
	
	
	
	
}

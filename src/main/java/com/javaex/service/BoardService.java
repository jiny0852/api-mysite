package com.javaex.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.BoardDao;
import com.javaex.vo.BoardVo;

@Service
public class BoardService {
	
	@Autowired
	private BoardDao boardDao;
	
	
	
	public List<BoardVo> exeGetBoardList () {
		
		System.out.println("boardService.exeGetBoardList()");
		
		List<BoardVo> boardList = boardDao.getList();
		
		
		return boardList;
	}
	
	
	public BoardVo exeGetReadOne (int no) {
		
		System.out.println("boardService.exeGetReadOne()");
		
		BoardVo boardVo;
		
		int result = boardDao.increaseHitCount(no);
		
		if (result == 1) {
			boardVo = boardDao.getRead(no);
		} else {
			boardVo = null;
		}
		
		
		return boardVo;
	}
	
	public BoardVo exeGetInsert ( BoardVo boardVo ) {
		
		System.out.println("boardService.exeGetInsert()");
		
		BoardVo insertVo = boardDao.getInsert(boardVo);
		
		return insertVo;
	}
	
	public BoardVo exeUpdate ( BoardVo boardVo ) {
		
		System.out.println("boardService.exeUpdate()");
		
		BoardVo updateVo = boardDao.getUpdate(boardVo);
		
		return updateVo;
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}

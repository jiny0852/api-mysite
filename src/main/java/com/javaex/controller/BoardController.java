package com.javaex.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.javaex.service.BoardService;
import com.javaex.util.JsonResult;
import com.javaex.vo.BoardVo;

@RestController
public class BoardController {

	
	@Autowired
	private BoardService boardService;
	
	
	/* board List Main */
	@GetMapping ( "/api/boards" )
	public JsonResult list (  ) {
		
		System.out.println("boardController.list()");
		
		List<BoardVo> boardList = boardService.exeGetBoardList();
		
		if ( boardList == null ) { 
			return JsonResult.fail("보드리스트 오류");
			
		} else { 
			return JsonResult.success(boardList);
		}
				                                    
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}

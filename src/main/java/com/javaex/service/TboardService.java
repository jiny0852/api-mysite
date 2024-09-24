package com.javaex.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.TboardDao;
import com.javaex.vo.TboardVo;

@Service
public class TboardService {
	
	
	
	@Autowired
	private TboardDao tboardDao;
	
	
	
	public List<TboardVo> exeList() {
		
		System.out.println("TboardService.exeList()");
		
		List<TboardVo> tboardList = tboardDao.selectList();
		
		
		return tboardList;
		
	}
	
	
	/*
	public List<TboardVo> exeList2( int crtPage ) {
		
		System.out.println("TboardService.exeList2()");
		//System.out.println(crtPage);
		
		
		
		/////////////////////////
		//리스트 가져오기
		/////////////////////////
		int listCnt = 10; //한페이지의 출력 갯수
		
		
		// startRowNo 구하기
		// 1 -> (1 10) 2 -> (11 10) 3 -> (21 10) 사람
		// 1 -> (0 10) 2 -> (10 10) 3 -> (20 10) mySql
		//startRowNo = ( crtpage-1 ) * listCnt;
		int startRowNo = ( crtPage-1 ) * listCnt;
		
		//두개의 데이터를 한개로 묶는다
		Map<String, Integer> limitMap = new HashMap<String, Integer>();
		limitMap.put("listCnt", listCnt);
		limitMap.put("startRowNo", startRowNo);
		
		//System.out.println(limitMap);
		
		
		List<TboardVo> tboardList = tboardDao.selectList2(limitMap);
		
		
		return tboardList;
		
	}
	*/
	
	
	
	
	public Map<String, Object> exeList2( int crtPage ) {
		
		System.out.println("TboardService.exeList2()");
		//System.out.println(crtPage);
		
		
		
		/////////////////////////
		//리스트 가져오기
		/////////////////////////
		int listCnt = 10; //한페이지의 출력 갯수
		
		
		
		
		//현재페이지 음수일떄 계싼
		
		// (조건식) ? 값 : 값    3항연산자
		crtPage = ( crtPage > 0 ) ? crtPage : (crtPage=1);
		
		//우에가 믿테 
		if ( crtPage < 0 ) {
			crtPage = 1;
		} /*else {
			crtPage = crtPage;
		}*/
		
		
		
		
		
		
		// startRowNo 구하기
		// 1 -> (1 10) 2 -> (11 10) 3 -> (21 10) 사람
		// 1 -> (0 10) 2 -> (10 10) 3 -> (20 10) mySql
		//startRowNo = ( crtpage-1 ) * listCnt;
		int startRowNo = ( crtPage-1 ) * listCnt;
		
		//두개의 데이터를 한개로 묶는다
		Map<String, Integer> limitMap = new HashMap<String, Integer>();
		limitMap.put("listCnt", listCnt);
		limitMap.put("startRowNo", startRowNo);
		
		//System.out.println(limitMap);
		
		
		List<TboardVo> tboardList = tboardDao.selectList2(limitMap);
		
		
		
		/////////////////
		///////////////
		//페이징 계산(하단 버튼)
		////////////////
		
		//페이지당 버튼 갯수
		int pageBtnCount = 5;
		
		int totalCnt = tboardDao.selectTotalCnt(); //187;    //db에서 조회할 예정
		
		
		//endPageBtnNo 마지막 버튼 번호
		// < 1 2 3 4 5 >
		// 1 --> (1, 5)
		// 2 --> (1, 5)
		// 3 --> (1, 5)
		// 4 --> (1, 5)
		// 5 --> (1, 5)
		// 6 --> (6, 10)
		//...
		// 10 --> (6, 10)
		//...
		// 11 --> (11, 15)
		
		// (1 5) => 올림(1/5)5         0.2(1)*5 --> 5
		// (2 5) => 올림(2/5)5         0.4(1)*5 --> 5
		// (5 5) => 올림(2/5)5         1(1)*5 --> 5
		//...
		// (6 10) => 올림(6/5)5        1.2(2)*5 --> 10
		//(올림(crtPage/pageBtnCount))*pageBtnCount
		
		//마지막 버튼 번호
		int endPageBtnNo = (int)(   ( Math.ceil( crtPage / (double)pageBtnCount )) * pageBtnCount  );		
		//System.out.println(endPageBtnNo);
		
		
		//시작 버튼 번호
		int startPageBtnNo = ( endPageBtnNo - pageBtnCount ) + 1 ;
		//System.out.println(startPageBtnNo);
		
		//다음 화살표 유무
		boolean next = false;
		
		if ( listCnt * endPageBtnNo < totalCnt ) {    //한페이지당 글갯수(10) * 마지막버튼번호(19) < 전체글갯수 (187)
			next = true;
		} else {  ///20번 안보이게 하는 부분
			//다음 화살표가 false 일때 마지막 숫자 버튼이 갯수를 정확히 계산
			// 187 -- 19 page   187/10 --> 18.7  올림처리 19로 사용
			endPageBtnNo = (int)( Math.ceil( totalCnt / (double)listCnt ) );
		}
		
		//이전 화살표 유무
		boolean prev = false;
		
		if ( startPageBtnNo != 1 ) {
			prev = true;
		}
		
		
		/* 화면에 표시할 모든 데이터를 묶는다 */
		Map<String, Object> pMap = new HashMap<String, Object>();
		pMap.put("tboardList" ,tboardList);
		pMap.put("prev" ,prev);
		pMap.put("next" ,next);
		pMap.put("startPageBtnNo" ,startPageBtnNo);
		pMap.put("endPageBtnNo" ,endPageBtnNo);
			
		
		
		
		return pMap;
		
	}
	
	
	
	
	
	public Map<String, Object> exeList3( int crtPage, String keyword) {
		
		System.out.println("TboardService.exeList3()");
		int listCnt = 10; //한페이지의 출력 갯수
		
		//현재페이지 음수일떄 계싼
		
		// (조건식) ? 값 : 값    3항연산자
		crtPage = ( crtPage > 0 ) ? crtPage : (crtPage=1);
		
		//우에가 믿테 
		if ( crtPage < 0 ) {
			crtPage = 1;
		} /*else {
			crtPage = crtPage;
		}*/
		
		
		int startRowNo = ( crtPage-1 ) * listCnt;
		
		//두개의 데이터를 한개로 묶는다
		Map<String, Object> limitMap = new HashMap<String, Object>();
		limitMap.put("listCnt", listCnt);
		limitMap.put("startRowNo", startRowNo);
		limitMap.put("keyword", keyword);
		
		//System.out.println(limitMap);
		
		
		List<TboardVo> tboardList = tboardDao.selectList3(limitMap);
		
		
		//페이지당 버튼 갯수
		int pageBtnCount = 5;
		
		int totalCnt = tboardDao.selectTotalCntKeyword(keyword); //187;    //db에서 조회할 예정
		System.out .println("totalCnt: " + totalCnt);
		
		
		
		//마지막 버튼 번호
		int endPageBtnNo = (int)(   ( Math.ceil( crtPage / (double)pageBtnCount )) * pageBtnCount  );		
		//System.out.println(endPageBtnNo);
		
		
		//시작 버튼 번호
		int startPageBtnNo = ( endPageBtnNo - pageBtnCount ) + 1 ;
		//System.out.println(startPageBtnNo);
		
		//다음 화살표 유무
		boolean next = false;
		
		if ( listCnt * endPageBtnNo < totalCnt ) {    //한페이지당 글갯수(10) * 마지막버튼번호(19) < 전체글갯수 (187)
			next = true;
		} else {  ///20번 안보이게 하는 부분
			//다음 화살표가 false 일때 마지막 숫자 버튼이 갯수를 정확히 계산
			// 187 -- 19 page   187/10 --> 18.7  올림처리 19로 사용
			endPageBtnNo = (int)( Math.ceil( totalCnt / (double)listCnt ) );
		}
		
		//이전 화살표 유무
		boolean prev = false;
		
		if ( startPageBtnNo != 1 ) {
			prev = true;
		}
		
		
		/* 화면에 표시할 모든 데이터를 묶는다 */
		Map<String, Object> pMap = new HashMap<String, Object>();
		pMap.put("tboardList" ,tboardList);
		pMap.put("prev" ,prev);
		pMap.put("next" ,next);
		pMap.put("startPageBtnNo" ,startPageBtnNo);
		pMap.put("endPageBtnNo" ,endPageBtnNo);
			
		
		
		
		return pMap;
		
	}
	
	
	
	
	
	

}

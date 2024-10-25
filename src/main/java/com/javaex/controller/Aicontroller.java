package com.javaex.controller;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.javaex.service.AttachService;
import com.javaex.util.JsonResult;
import com.javaex.vo.AttachVo2;

@RestController
public class Aicontroller {
	
	
	@Autowired
	private AttachService attachService;
	
	
	@PostMapping ( "/api/ai/chats" )
	public JsonResult chat ( @RequestParam(value="question") String question ) {
		
		System.out.println("Aicontroller.chat()");
		System.out.println(question);
		
		String answer = "";

		//메모리의 우리 프로그램 밖의 파이썬을 실행해야한다 [자기파일이름, "영업시간은", -help]
		//ProcessBuilder processBuilder = new ProcessBuilder("가상환경실행", "main.py실행", question);

		try {
			ProcessBuilder processBuilder = new ProcessBuilder(
					"C:\\javaStudy\\workspace-python\\Ex05\\ex05_venv\\Scripts\\python.exe", 
					"C:\\javaStudy\\workspace-python\\Ex05\\main.py", 
					question);
			
			//파이썬에거 전달하는 메세지, 파이썬에서 발생하는 에러메세지 따로 관리된다
			//이것을 1개로 관리할 수 있다
			processBuilder.redirectErrorStream(true);
			
			//파이썬 스크립트 실행
			Process process = processBuilder.start();
			
			///////////////////
			//대답받기
			InputStream is = process.getInputStream();
			InputStreamReader isr = new InputStreamReader(is, "UTF-8");
			BufferedReader br = new BufferedReader(isr);
			
			while(true) {
				
				String line = br.readLine();
				if (line == null) {
					break;
				} else {
					answer += line + "<br />";
				}
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		System.out.println(answer);
		return JsonResult.success(answer);
		
				                                    
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}

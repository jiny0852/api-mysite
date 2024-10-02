package com.javaex.controller;

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
public class AttachController {
	
	
	@Autowired
	private AttachService attachService;
	
	/*
	@PostMapping ( "/api/attachs" )
	public JsonResult form ( @RequestParam("profileImg") MultipartFile profileImg  ) {
		
		System.out.println("AttachController.form()");
		System.out.println(profileImg.getOriginalFilename());
		
		String saveName = attachService.exeUpload(profileImg);
		asd
		
		
		return JsonResult.success(saveName);
		
				                                    
	}
	*/
	
	@PostMapping ( "/api/attachs2" )
	public JsonResult form2 ( @ModelAttribute AttachVo2 attach ) {
							// , @RequestParam("content") String content ) {
		
		System.out.println("AttachController.form2()");
		//System.out.println(attach);
		
		//System.out.println(content);
		
		String savename = attachService.exeUpload2(attach);
		
		
		
		return JsonResult.success(savename);
		
				                                    
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}

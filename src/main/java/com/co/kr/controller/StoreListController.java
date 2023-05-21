package com.co.kr.controller;

import java.io.IOException;
import java.nio.file.DirectoryNotEmptyException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.co.kr.code.Code;

import com.co.kr.domain.StoreFileDomain;
import com.co.kr.domain.StoreListDomain;
import com.co.kr.exception.RequestException;
import com.co.kr.service.UploadService;
import com.co.kr.vo.StoreListVO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class StoreListController {
	@Autowired
	private UploadService uploadService;

	//리스트 하나 가져오기 따로 함수뺌
	/*
	 * public ModelAndView stSelectOneCall(@ModelAttribute("storeListVO")
	 * StoreListVO storeListVO, String stSeq, HttpServletRequest request) {
	 * ModelAndView mav = new ModelAndView(); HashMap<String, Object> map = new
	 * HashMap<String, Object>(); HttpSession session = request.getSession();
	 * 
	 * map.put("stSeq", Integer.parseInt(stSeq)); StoreListDomain storeListDomain
	 * =uploadService.storeSelectOne(map);
	 * System.out.println("storeListDomain"+storeListDomain); List<StoreFileDomain>
	 * storeList = uploadService.storeSelectOneFile(map);
	 * 
	 * for (StoreFileDomain list : storeList) { String path =
	 * list.getUpFilePath().replaceAll("\\\\", "/"); list.setUpFilePath(path); }
	 * mav.addObject("detail", storeListDomain); mav.addObject("files", storeList);
	 * 
	 * //삭제시 사용할 용도 session.setAttribute("files", storeList);
	 * 
	 * return mav; }
	 * 
	 * @GetMapping("detail") public ModelAndView
	 * stDetail(@ModelAttribute("StoreListVO") StoreListVO
	 * storeListVO, @RequestParam("stSeq") String stSeq, HttpServletRequest request)
	 * throws IOException { ModelAndView mav = new ModelAndView(); //하나파일 가져오기 mav =
	 * stSelectOneCall(storeListVO, stSeq,request);
	 * mav.setViewName("store/storeList.html"); return mav; }
	 * 
	 * 
	 * @PostMapping(value = "upload") public ModelAndView
	 * uploadStoreList(StoreListVO storeListVO, MultipartHttpServletRequest request,
	 * HttpServletRequest httpReq) throws IOException, ParseException {
	 * 
	 * ModelAndView mav = new ModelAndView(); int stSeq =
	 * uploadService.storefileProcess(storeListVO, request, httpReq);
	 * storeListVO.setContent(""); //초기화 storeListVO.setTitle(""); //초기화
	 * 
	 * // 화면에서 넘어올때는 bdSeq String이라 string으로 변환해서 넣어즘 mav =
	 * stSelectOneCall(storeListVO, String.valueOf(stSeq),request);
	 * mav.setViewName("store/storeList.html"); return mav;
	 * 
	 * }
	 * 
	 * @GetMapping("stEdit") public ModelAndView edit(StoreListVO
	 * storeListVO, @RequestParam("stSeq") String stSeq, HttpServletRequest request)
	 * throws IOException { ModelAndView mav = new ModelAndView();
	 * 
	 * HashMap<String, Object> map = new HashMap<String, Object>(); HttpSession
	 * session = request.getSession();
	 * 
	 * map.put("stSeq", Integer.parseInt(stSeq)); StoreListDomain storeListDomain =
	 * uploadService.storeSelectOne(map); List<StoreFileDomain> fileList =
	 * uploadService.storeSelectOneFile(map);
	 * 
	 * for (StoreFileDomain list : fileList) { String path =
	 * list.getUpFilePath().replaceAll("\\\\", "/"); list.setUpFilePath(path); }
	 * 
	 * storeListVO.setSeq(storeListDomain.getStSeq());
	 * storeListVO.setContent(storeListDomain.getStContent());
	 * storeListVO.setTitle(storeListDomain.getStTitle());
	 * storeListVO.setIsEdit("edit"); // upload 재활용하기위해서
	 * 
	 * 
	 * mav.addObject("detail", storeListDomain); mav.addObject("files", fileList);
	 * mav.addObject("fileLen",fileList.size());
	 * 
	 * mav.setViewName("store/storeEditList.html"); return mav; }
	 * 
	 * @PostMapping("stEditSave") public ModelAndView
	 * editSave(@ModelAttribute("storeListVO") StoreListVO storeListVO,
	 * MultipartHttpServletRequest request, HttpServletRequest httpReq) throws
	 * IOException { ModelAndView mav = new ModelAndView();
	 * 
	 * uploadService.storefileProcess(storeListVO, request, httpReq);
	 * 
	 * mav = stSelectOneCall(storeListVO, storeListVO.getSeq(), request);
	 * storeListVO.setContent(""); storeListVO.setTitle("");
	 * mav.setViewName("store/storeList.html"); return mav; }
	 * 
	 * @GetMapping("stRemove") public ModelAndView stRemove(@RequestParam("stSeq")
	 * String stSeq, HttpServletRequest request) throws IOException { ModelAndView
	 * mav = new ModelAndView();
	 * 
	 * HttpSession session = request.getSession(); HashMap<String, Object> map = new
	 * HashMap<String, Object>(); List<StoreFileDomain> fileList = null;
	 * if(session.getAttribute("stfiles") != null) { fileList =
	 * (List<StoreFileDomain>) session.getAttribute("stfiles"); }
	 * 
	 * map.put("stSeq", Integer.parseInt(stSeq));
	 * 
	 * //내용삭제 uploadService.stContentRemove(map);
	 * 
	 * for (StoreFileDomain list : fileList) { list.getUpFilePath(); Path filePath =
	 * Paths.get(list.getUpFilePath());
	 * 
	 * try {
	 * 
	 * // 파일 물리삭제 Files.deleteIfExists(filePath); // notfound시 exception 발생안하고 false
	 * 처리 // db 삭제 uploadService.stFileRemove(list);
	 * 
	 * } catch (DirectoryNotEmptyException e) { throw
	 * RequestException.fire(Code.E404, "디렉토리가 존재하지 않습니다", HttpStatus.NOT_FOUND); }
	 * catch (IOException e) { e.printStackTrace(); } }
	 * 
	 * //세션해제 session.removeAttribute("files"); // 삭제 mav = stListCall();
	 * mav.setViewName("store/storeList.html");
	 * 
	 * return mav; }
	 */


	//리스트 가져오기 따로 함수뺌
	public ModelAndView stListCall() {
		ModelAndView mav = new ModelAndView();
		List<StoreListDomain> items = uploadService.storeList();
		mav.addObject("items", items);
		return mav;
	}
	
}

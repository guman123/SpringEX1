package com.co.kr.service;


import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.co.kr.domain.BoardFileDomain;
import com.co.kr.domain.BoardListDomain;
import com.co.kr.domain.StoreFileDomain;
import com.co.kr.domain.StoreListDomain;
import com.co.kr.vo.FileListVO;
import com.co.kr.vo.StoreListVO;

public interface UploadService {
	
	// 전체 리스트 조회
	public List<BoardListDomain> boardList();
	public List<StoreListDomain> storeList();
	
	// 인서트 및 업데이트
	public int fileProcess(FileListVO fileListVO, MultipartHttpServletRequest request, HttpServletRequest httpReq);
	public int storefileProcess(StoreListVO storeListVO, MultipartHttpServletRequest request, HttpServletRequest httpReq);
	
	// 하나 삭제
	public void bdContentRemove(HashMap<String, Object> map);
	public void stContentRemove(HashMap<String, Object> map);
	
	// 하나 삭제
	public void bdFileRemove(BoardFileDomain boardFileDomain);
	public void stFileRemove(StoreFileDomain storeFileDomain);
	
	// 하나 리스트 조회
	public BoardListDomain boardSelectOne(HashMap<String, Object> map);
	public StoreListDomain storeSelectOne(HashMap<String, Object> map);
	
	// 하나 파일 리스트 조회
	public List<BoardFileDomain> boardSelectOneFile(HashMap<String, Object> map);
	public List<StoreFileDomain> storeSelectOneFile(HashMap<String, Object> map);	

}
package com.co.kr.mapper;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.co.kr.domain.BoardContentDomain;
import com.co.kr.domain.BoardFileDomain;
import com.co.kr.domain.BoardListDomain;
import com.co.kr.domain.StoreContentDomain;
import com.co.kr.domain.StoreFileDomain;
import com.co.kr.domain.StoreListDomain;

@Mapper
public interface UploadMapper {

	//list
	public List<BoardListDomain> boardList();
	public List<StoreListDomain> storeList();
	
	//content insert
	public void contentUpload(BoardContentDomain boardContentDomain);
	public void stcontentUpload(StoreContentDomain storeContentDomain);
	//file insert
	public void fileUpload(BoardFileDomain boardFileDomain);
	public void stfileUpload(StoreFileDomain storeFileDomain);

	//content update
	public void bdContentUpdate(BoardContentDomain boardContentDomain);
	public void stContentUpdate(StoreContentDomain storeContentDomain);
	//file updata
	public void bdFileUpdate(BoardFileDomain boardFileDomain);
	public void stFileUpdate(StoreFileDomain storeFileDomain);

  //content delete 
	public void bdContentRemove(HashMap<String, Object> map);
	public void stContentRemove(HashMap<String, Object> map);
	
	//file delete 
	public void bdFileRemove(BoardFileDomain boardFileDomain);
	public void stFileRemove(StoreFileDomain storeFileDomain);
	
	//select one
	public BoardListDomain boardSelectOne(HashMap<String, Object> map);
	public StoreListDomain storeSelectOne(HashMap<String, Object> map);

	//select one file
	public List<BoardFileDomain> boardSelectOneFile(HashMap<String, Object> map);
	public List<StoreFileDomain> storeSelectOneFile(HashMap<String, Object> map);

	
}
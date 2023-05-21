package com.co.kr.domain;

import lombok.Builder;
import lombok.Data;

@Data
@Builder(builderMethodName="builder")

public class StoreFileDomain {
	
	private Integer stSeq;
	private String mbId;
	private String upOriginalFileName;
	private String upNewFileName; 
	private String upFilePath;
	private Integer upFileSize;

}

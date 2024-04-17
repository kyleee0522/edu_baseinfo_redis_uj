/**************************************************************************************
 * ICIS version 1.0
 *
 *  Copyright ⓒ 2022 kt/ktds corp. All rights reserved.
 *
 *  This is a proprietary software of kt corp, and you may not use this file except in
 *  compliance with license agreement with kt corp. Any redistribution or use of this
 *  software, with or without modification shall be strictly prohibited without prior written
 *  approval of kt corp, and the copyright notice above does not evidence any actual or
 *  intended publication of such software.
 *************************************************************************************/

package com.kt.edu.baseinfo.comcd.query.payload.out.dto;

import java.io.Serializable;
import java.math.BigDecimal;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

/**
 * 코드 db table dto
 */
@Data
@Builder
public class BiCsyscdOutQryDto implements Serializable {
	//GRP_ID, CD, NAME, REF1, REF2, REF3, REF4, REF5, DEFINE_NAME, OUTPUT_SEQ, START_DATE, REMARK
	private static final long serialVersionUID = 1670824102915348426L;
	
	@Schema(description = "코드", nullable = false, maxLength = 7)
    private String cd;
    
	@Schema(description = "명", nullable = true, maxLength = 50)
    private String name;
	
	@Schema(description = "그룹아이디", nullable = false, maxLength = 4)
	private String grpId;
	
	@Schema(description = "참조1내용", nullable = true, maxLength = 10)
	private String ref1;
	
	@Schema(description = "참조2내용", nullable = true, maxLength = 20)
	private String ref2;
	
	@Schema(description = "참조3내용", nullable = true, maxLength = 22)
	private String ref3;
	
	@Schema(description = "참조4내용", nullable = true, maxLength = 20)
	private String ref4;
	
	@Schema(description = "참조5내용", nullable = true, maxLength = 20)
	private String ref5;
	
	@Schema(description = "정의명", nullable = true, maxLength = 80)
	private String defineName;
	
	@Schema(description = "출력순서", nullable = true, maxLength = 22)
//	private String outputSeq;
	private BigDecimal outputSeq;
	
	@Schema(description = "시작일자", nullable = true, maxLength = 8)
	private String startDate;
	
	@Schema(description = "종료일자", nullable = true, maxLength = 8)
	private String endDate;
	
	@Schema(description = "비고내용", nullable = true, maxLength = 120)
	private String remark;
	
}
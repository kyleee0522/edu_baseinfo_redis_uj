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

package com.kt.edu.baseinfo.comcd.query.payload.in.dto;

import java.sql.Timestamp;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class BiMdbCsysdddDddInQryDto {
	private String ddd;
	
	@Schema(description = "지역번호", nullable = false, maxLength = 4)
	@JsonProperty("aNo")
    private String aNo;
    
	@Schema(description = "A명", nullable = true, maxLength = 40)
    private String aName;
    
	@Schema(description = "지역서버코드", nullable = true, maxLength = 2)
    private String rsCd;
    
	@Schema(description = "지역전산국코드", nullable = true, maxLength = 6)
    private String tandemOfcCd;
    
	@Schema(description = "등록일시", nullable = true, maxLength = 8)
    private Timestamp regDate;
    
	@Schema(description = "등록국코드", nullable = true, maxLength = 6)
    private String regOfcCd;
    
	@Schema(description = "등록자사번", nullable = true, maxLength = 9)
    private String regerEmpNo;
    
	@Schema(description = "등록자명", nullable = true, maxLength = 10)
    private String regerEmpName;
}
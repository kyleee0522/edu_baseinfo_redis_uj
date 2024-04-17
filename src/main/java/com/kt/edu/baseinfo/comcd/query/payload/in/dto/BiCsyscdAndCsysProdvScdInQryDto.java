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

import org.springframework.data.relational.core.mapping.Column;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BiCsyscdAndCsysProdvScdInQryDto {	
	private String grpId;
	private String cd;
	private String saCd;
	private String soItemId;
	private String fctr1;	
	private String fctr2;	
	private String fctr3;	
	private String fctr4;	
	private String fctr5;
	private String effectiveDateCheck;
	
	@Column("REF1")
	private String ref1;

	@Column("REF2")
	private String ref2;
}
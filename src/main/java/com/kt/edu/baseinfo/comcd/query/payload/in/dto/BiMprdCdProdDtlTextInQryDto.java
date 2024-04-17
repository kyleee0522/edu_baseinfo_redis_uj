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

import lombok.Data;

@Data
public class BiMprdCdProdDtlTextInQryDto {
	/* 사업용 여부  */
	private String ktUserFlag;
	/* 세부이용목적 */
	private String usePurposeCd;
	/* 서비스유형   */
	private String svcType;
	/* 접속단말유형 */
	private String connectTerminalTypeCd;
	/* 기간계약유형  */
	private String termCntrctTypeCd;
	/* 정액형 여부  */
	private String chrgFlag;
	
	private String saCd;
}
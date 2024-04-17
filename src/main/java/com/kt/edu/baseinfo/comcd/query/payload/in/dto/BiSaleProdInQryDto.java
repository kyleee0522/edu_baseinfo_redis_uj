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
public class BiSaleProdInQryDto {
	private String saleProdTypeLvl;
	private String busiTypeCd;
	private String saleProdTypeCd;
	private String procFlag;
	private String rsCd;
	private String saCd; ////상품목록이 많아 화면 죽는 현상 발생하여 추가
}
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

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * MDB BI_SCYSCD In Payload Dto
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BiCsyscdInQryDto {
	private String grpId;	
	private String saCd;
	private String soItemId;
	private String cd;
	private String name;
	private String ref1;
	private String ref2;
	private Integer ref3;
	private String ref4;
	private String ref5;
	private String defineName;
	private Integer outputSeq;
	private String startDate;
	private String remark;
	private String whereTxt;
	private String effectiveDateCheck;
//	
//	public int getCount() {
//		int whereCnt=0;
//		
//		Class<? extends Object> tarCls = this.getClass();
//		
//		try {
//			for (Field field : tarCls.getDeclaredFields()) {
//				field.setAccessible(true);
//				Object value = field.get(this);
//				
//				if (value != null) {
//					whereCnt++;
//				}
//			}
//		}catch(IllegalArgumentException e) {
//			e.printStackTrace();
//		}catch(IllegalAccessException e) {
//			e.printStackTrace();
//		}
//
//		return whereCnt;
//	}

	
}
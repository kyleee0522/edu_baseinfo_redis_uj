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

import java.lang.reflect.Field;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BiCsyscdAndCsysOrdTypeInQryDto {
	private String grpId;
	private String saCd;
	private String cd;
	private String ordCompletedType;
	private String ordMngFlag;
	private Integer userOrdSeq;
	private String ordTypeCd;
	private String effectiveDateCheck;
	
	public Integer getCount() {
		Integer whereCnt=0;
		
		Class<? extends Object> tarCls = this.getClass();
		
		try {
			for (Field field : tarCls.getDeclaredFields()) {
				field.setAccessible(true);
				Object value = field.get(this);
				
				if (value != null) {
					whereCnt++;
				}
			}
		}catch(IllegalArgumentException e) {
			e.printStackTrace();
		}catch(IllegalAccessException e) {
			e.printStackTrace();
		}

		return whereCnt;
	}
}

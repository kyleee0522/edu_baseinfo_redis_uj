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
public class BiMprdcdInQryDto {
	private String saCd;
	private String custChngType;
	private String custDcFlag;
	private String iaType;
	private String menuProdTypeCd;
	private String nic;
	private String prodTypeCd;
	private String tblId;
	private String wrkOrdSndTableId;
	private String whereTxt;
	private String msGb;
	private String kosProdFlag;
	private String kosMenuFlag;
}
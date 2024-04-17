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

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BiCboftpbasicinfoOutQryDto {
	private String namingRule; 
	private String targetWorkName;
	private String sndRcvFlag;
	private String rsCd;
	private String exeFlag;
	private String partnerIpAddress;
	private String partnerPortNo;
	private String partnerZtc;
	private String partnerUserId;
	private String partnerPassWord; 
	private String partnerDir;
	private String partnerPgmDir;
	private String partnerPgmId;
	private String icisIpAddress; 
	private String icisUserId;
	private String icisPassWord;
	private String icisDir;
	private String fwdFlag;
	private String remark;
	private String ecodTargYn;
	private String partnerEcodYn;
	private String passiveYn;
	private String retFlag;
	private String retMsg;
}
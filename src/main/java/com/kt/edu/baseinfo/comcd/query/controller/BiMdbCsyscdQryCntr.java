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

package com.kt.edu.baseinfo.comcd.query.controller;

import com.kt.edu.baseinfo.comcd.query.payload.in.dto.BiCsyscdInQryDto;
import com.kt.edu.baseinfo.comcd.query.payload.out.dto.BiCsyscdOutQryDto;
import com.kt.edu.baseinfo.comcd.query.service.BiCsyscdQrySvc;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * MDB BI_SCYSCD Controller
 */
@Component
@RequiredArgsConstructor
public class BiMdbCsyscdQryCntr {
	private final BiCsyscdQrySvc biCsyscdQrySvc;
	
	public List<BiCsyscdOutQryDto> findCsyscd(BiCsyscdInQryDto inDto) {
		return biCsyscdQrySvc.findCsyscd(inDto);
	}

}
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

package com.kt.edu.baseinfo.comcd.query.api;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RequestMapping("/mdb")
@Tag(name = "[기준정보] BI_CSYSSTATUSCD 조회", description = "BI_CSYSSTATUSCD 조회")
@RequiredArgsConstructor
@RestController
public class BiCsysstatuscdQryApi {
	
	/*private final BiCsysstatuscdQryCntr biCsysstatuscdQryCntr;
	
	@PostMapping("/findBySysId")
	public BiCsysstatuscdOutQryPyld findBySysId(@RequestBody BiCsysstatuscdInQryPyld inDs) {
		
		BiCsysstatuscdInQryDto inDto = inDs.getBiCsysstatuscdInQryDs();
		BiCsysstatuscdOutQryDto result = biCsysstatuscdQryCntr.findBySysId(inDto);
		
		return BiCsysstatuscdOutQryPyld.builder().biCsysstatuscdOutQryDs(result).build();
	}*/
}

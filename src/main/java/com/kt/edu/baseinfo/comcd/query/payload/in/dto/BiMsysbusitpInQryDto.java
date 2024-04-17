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

import java.io.Serializable;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * BI_MSYSBUSITP db table dto
 */
@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
public class BiMsysbusitpInQryDto implements Serializable {
	private static final long serialVersionUID = 1678709629991003826L;
	
	@Schema(description = "CO_TYPE_CD", nullable = false, maxLength = 6)
    private String coTypeCd;
    
}
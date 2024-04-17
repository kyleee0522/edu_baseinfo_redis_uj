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
 * BI_CSYSPGM db table dto
 */
@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
@Schema(description = "BI_CSYSPGM")
public class BiCsyspgmInQryDto implements Serializable {
	private static final long serialVersionUID = 1678709625626957340L;
	
	@Schema(description = "SA_CD", nullable = false, maxLength = 4)
    private String saCd;
    
	@Schema(description = "PGM_CLAS_NAME", nullable = false, maxLength = 30)
    private String pgmClasName;
    
	@Schema(description = "PGM_TYPE", nullable = false, maxLength = 1)
    private String pgmType;
    
	@Schema(description = "PGM_ID", nullable = false, maxLength = 30)
    private String pgmId;
    
	@Schema(description = "TRAN_RSN_DTL_YN", nullable = true, maxLength = 1)
    private String tranRsnDtlYn;
    
}
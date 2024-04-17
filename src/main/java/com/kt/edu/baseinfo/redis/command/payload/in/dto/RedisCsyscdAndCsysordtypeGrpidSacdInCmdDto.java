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

package com.kt.edu.baseinfo.redis.command.payload.in.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class RedisCsyscdAndCsysordtypeGrpidSacdInCmdDto {

    @Schema(description = "그룹아이디", nullable = false, maxLength = 4)
    private String grpId;

    @Schema(description = "코드", nullable = false, maxLength = 7)
    private String cd;

    @Schema(description = "서비스계약코드", nullable = false, maxLength = 4)
    private String saCd;

    @Schema(description = "오더완료유형코드", nullable = true, maxLength = 1)
    private String ordCompletedType;

    @Schema(description = "오더관리여부", nullable = true, maxLength = 1)
    private String ordMngFlag;

    @Schema(description = "사용자오더일련번호", nullable = true, maxLength = 22)
    private Integer userOrdSeq;

    @Schema(description = "오더유형코드", nullable = false, maxLength = 2)
    private String ordTypeCd;

    @Schema(description = "명", nullable = true, maxLength = 50)
    private String name;

    @Schema(description = "DLL명", nullable = true, maxLength = 20)
    private String dllName;

    //	private String outputSeq;
    private BigDecimal outputSeq;
}

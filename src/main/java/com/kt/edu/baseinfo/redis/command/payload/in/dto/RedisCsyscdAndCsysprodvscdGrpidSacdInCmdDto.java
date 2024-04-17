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

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RedisCsyscdAndCsysprodvscdGrpidSacdInCmdDto {

    @Schema(description = "그룹아이디", nullable = false, maxLength = 7)
    private String cd;

    @Schema(description = "그룹아이디", nullable = true, maxLength = 4)
    private String saCd;

    @Schema(description = "그룹아이디", nullable = true, maxLength = 30)
    private String soItemId;

    @Schema(description = "그룹아이디", nullable = true, maxLength = 7)
    private String fctr1;

    @Schema(description = "그룹아이디", nullable = true, maxLength = 7)
    private String fctr2;

    @Schema(description = "그룹아이디", nullable = true, maxLength = 7)
    private String fctr3;

    @Schema(description = "그룹아이디", nullable = true, maxLength = 7)
    private String fctr4;

    @Schema(description = "그룹아이디", nullable = true, maxLength = 7)
    private String fctr5;

    @Schema(description = "그룹아이디", nullable = true, maxLength = 10)
    private String ref1;

    @Schema(description = "그룹아이디", nullable = true, maxLength = 20)
    private String ref2;

    @Schema(description = "ref3", nullable = true, maxLength = 22)
    private Integer ref3;

    @Schema(description = "그룹아이디", nullable = false, maxLength = 4)
    private String grpId;

    @Schema(description = "명", nullable = true, maxLength = 50)
    private String name;

    //	private String outputSeq;
    private BigDecimal outputSeq;
}

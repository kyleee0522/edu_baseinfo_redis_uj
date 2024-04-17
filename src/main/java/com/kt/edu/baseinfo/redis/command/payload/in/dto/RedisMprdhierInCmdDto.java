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

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RedisMprdhierInCmdDto {

    @Schema(description = "서비스계약코드", nullable = false, maxLength = 4)
    private String saCd;

    @Schema(description = "상위서비스계약코드", nullable = false, maxLength = 4)
    private String pSaCd;

    @Schema(description = "관리값1", nullable = true, maxLength = 1)
    private String mngVal1;

    @Schema(description = "관리값2", nullable = true, maxLength = 1)
    private String mngVal2;

    @Schema(description = "관리값3", nullable = true, maxLength = 1)
    private String mngVal3;

    @Schema(description = "관리값4", nullable = true, maxLength = 30)
    private String mngVal4;

    @Schema(description = "관리값5", nullable = true, maxLength = 30)
    private String mngVal5;

    @Schema(description = "관리값6", nullable = true, maxLength = 30)
    private String mngVal6;
}

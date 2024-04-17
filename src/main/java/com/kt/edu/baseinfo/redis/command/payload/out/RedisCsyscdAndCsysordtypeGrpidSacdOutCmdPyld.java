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

package com.kt.edu.baseinfo.redis.command.payload.out;

import com.kt.edu.baseinfo.redis.command.payload.out.dto.RedisCsyscdAndCsysordtypeGrpidSacdOutCmdDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RedisCsyscdAndCsysordtypeGrpidSacdOutCmdPyld {

    private RedisCsyscdAndCsysordtypeGrpidSacdOutCmdDto outDs;
}

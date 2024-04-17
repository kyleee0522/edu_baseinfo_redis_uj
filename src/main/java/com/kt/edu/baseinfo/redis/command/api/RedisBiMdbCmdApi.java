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

package com.kt.edu.baseinfo.redis.command.api;

import com.kt.edu.baseinfo.comcd.exception.OrderException;
import com.kt.edu.baseinfo.redis.command.controller.RedisBiMdbCmdCntr;
import com.kt.edu.baseinfo.redis.command.payload.in.RedisCsyscdInCmdPyld;
import com.kt.edu.baseinfo.redis.command.payload.in.dto.RedisCsyscdInCmdDto;
import com.kt.edu.baseinfo.redis.command.payload.out.RedisCsyscdOutCmdPyld;
import com.kt.edu.baseinfo.redis.command.payload.out.dto.RedisCsyscdOutCmdDto;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "Redis", description = "Redis 기준정보 데이터")
@RequestMapping("/redis")
@RequiredArgsConstructor
@RestController
public class RedisBiMdbCmdApi {

    private final RedisBiMdbCmdCntr redisBiMdbCmdCntr;

    //Redis Key = icis:oder:BI:MDB:CSYSCD:GRP_ID:%s
    @Operation(summary = "Csyscd :: grpId Update", description = "Redis Key = icis:oder:BI:MDB:CSYSCD:GRP_ID:%s")
    @PostMapping("/update/csyscd/grpid")
    public RedisCsyscdOutCmdPyld updateCsyscdGrpid(@RequestBody RedisCsyscdInCmdPyld inPyld) {

        List<RedisCsyscdInCmdDto> list = inPyld.getInDs();
        if (list.size() == 0) {
            throw new OrderException("MCSNE1010", List.of("입력값이 없습니다."));
        }

        Integer result = redisBiMdbCmdCntr.updateCsyscdGrpid(list.get(0).getGrpId(), list);

        return RedisCsyscdOutCmdPyld.builder().outDs(RedisCsyscdOutCmdDto.builder().updateCnt(result).build()).build();
    }


}

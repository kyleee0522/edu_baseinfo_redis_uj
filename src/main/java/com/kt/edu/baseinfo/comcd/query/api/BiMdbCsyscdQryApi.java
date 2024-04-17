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

import com.kt.edu.baseinfo.comcd.query.controller.BiMdbCsyscdQryCntr;
import com.kt.edu.baseinfo.comcd.query.payload.in.BiCsyscdInQryPyld;
import com.kt.edu.baseinfo.comcd.query.payload.out.BiCsyscdOutListQryPyld;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

/**
 * MDB BI_SCYSCD Api
 */
@RefreshScope
@RestController
@RequestMapping("/mdb")
@RequiredArgsConstructor
@Tag(name = "[기준정보] 기준정보 조회", description = "기준정보 조회")
public class BiMdbCsyscdQryApi {

    private final BiMdbCsyscdQryCntr biMdbCsyscdQryCntr;

    @Operation(summary = "List CSysCd", description = "CSysCd 목록 조회 (Redis)")
    @PostMapping(path = "/findcsyscd")
    public BiCsyscdOutListQryPyld findCsyscd(@RequestBody BiCsyscdInQryPyld inPyld) {
        return BiCsyscdOutListQryPyld.builder().baseinfoOutListQryDs(biMdbCsyscdQryCntr.findCsyscd(inPyld.getBaseinfoInQryDs())).build();
    }

}
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

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Project: ICIS TR 오더공통 icis-oder-biquery
 * @FileName: BiCsysnicSetNicGrpInQryDto
 * @Class설명 :
 * @Date: 2023.10.16
 * @작성자: 리그시스템 / 91342980 / 김취홍
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BiCsysnicSetNicGrpInQryDto {
    private String ordTypeGun;
}

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

package com.kt.edu.baseinfo.comcd.query.service;

import com.kt.edu.baseinfo.comcd.exception.OrderException;
import com.kt.edu.baseinfo.comcd.query.payload.in.dto.BiCsyscdInQryDto;
import com.kt.edu.baseinfo.comcd.query.payload.out.dto.BiCsyscdOutQryDto;
import com.kt.edu.baseinfo.comcd.query.repository.BiCsyscdQryMbtsRepo;
import com.kt.edu.baseinfo.comcd.query.repository.BiCsyscdQryRepo;
import com.kt.edu.baseinfo.common.utils.DtoUtil;
import com.kt.edu.baseinfo.constants.BiqueryConstants;
import com.kt.edu.baseinfo.redis.RedisOperator;
import com.kt.edu.baseinfo.redis.constants.RedisConstants;
import com.kt.edu.baseinfo.redis.constants.RedisKeyConstants;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * MDB BI_SCYSCD Service
 */
@RequiredArgsConstructor
@Service
@Slf4j
public class BiCsyscdQrySvc {	
	private final BiCsyscdQryRepo csyscdQryRepo;
	private final BiCsyscdQryMbtsRepo csyscdDynamicQryRepo;
	private final RedisOperator<List<BiCsyscdOutQryDto>> redisOperatorCsyscd;

	public List<BiCsyscdOutQryDto> findCsyscd(BiCsyscdInQryDto inDto) {

		if (DtoUtil.getCount(inDto)<1) {
			log.info("조회 조건을 추가해주세요.");
			throw new OrderException("IOBIE0001");
		}
		List<BiCsyscdOutQryDto> list = new ArrayList<BiCsyscdOutQryDto>();
		
		//검색조건이 grp_id 만 있는 경우만 redis를 이용
		if(StringUtils.isNotEmpty(inDto.getGrpId()) && 
			StringUtils.isAllEmpty(inDto.getSaCd(),
									inDto.getSoItemId(),
									inDto.getCd(),
									inDto.getName(),
									inDto.getRef1(),
									inDto.getRef2(),
									inDto.getRef4(),
									inDto.getRef5(),
									inDto.getDefineName(),
									inDto.getStartDate(),
									inDto.getRemark(),
									inDto.getWhereTxt(),
									inDto.getEffectiveDateCheck()) &&
									inDto.getRef3() == null &&
									inDto.getOutputSeq() == null) 
		{				
			for(String grpId : inDto.getGrpId().split(BiqueryConstants.MDB_DELIM)) {
				String redisKey = RedisKeyConstants.getRedisKey(RedisKeyConstants.REDIS_BI_MDB_CSYSCD_GRPID.key(), grpId);
				findCsyscdRedis(RedisKeyConstants.REDIS_BI_MDB_CSYSCD_GRPID, redisKey, list, BiCsyscdInQryDto.builder().grpId(grpId).build());
			}
		}
		//검색조건이 grp_id 와 cd 만 있는 경우만 redis를 이용
		else if(StringUtils.isNotEmpty(inDto.getGrpId()) && 
				StringUtils.isNotEmpty(inDto.getCd()) &&
				StringUtils.isAllEmpty(	inDto.getSaCd(), 
										inDto.getSoItemId(),
										inDto.getName(),
										inDto.getRef1(),
										inDto.getRef2(),
										inDto.getRef4(),
										inDto.getRef5(),
										inDto.getDefineName(),
										inDto.getStartDate(),
										inDto.getRemark(),
										inDto.getWhereTxt(),
										inDto.getEffectiveDateCheck()) &&
										inDto.getRef3() == null &&
										inDto.getOutputSeq() == null) 
		{
			for(String grpId : inDto.getGrpId().split(BiqueryConstants.MDB_DELIM)) {
				for(String cd : inDto.getCd().split(BiqueryConstants.MDB_DELIM)) {
					String redisKey = RedisKeyConstants.getRedisKey(RedisKeyConstants.REDIS_BI_MDB_CSYSCD_GRPID_CD.key(), grpId, cd);
					findCsyscdRedis(RedisKeyConstants.REDIS_BI_MDB_CSYSCD_GRPID_CD, redisKey, list, BiCsyscdInQryDto.builder().grpId(grpId).cd(cd).build());
				}
			}
		}
		else {
			list = csyscdDynamicQryRepo.findCsyscd(inDto);
		}
		
		return list;
	}

	private void findCsyscdRedis(RedisKeyConstants redisConstants, String key, List<BiCsyscdOutQryDto> list, BiCsyscdInQryDto newDto) {
		List<BiCsyscdOutQryDto> redisList = redisOperatorCsyscd.getValue(key);
		
		if(redisList != null) {
			log.info(RedisConstants.GET_LOG, key, redisList.size());
		}		
		else {
			redisList = csyscdDynamicQryRepo.findCsyscd(newDto);
			if(redisList != null && redisList.size() > 0) {
				redisOperatorCsyscd.set(key, redisList, redisConstants.ttl(), redisConstants.unit());
				log.info(RedisConstants.SET_LOG, key, redisList.size());
			}
		}			
		
		list.addAll(redisList);
	}

}
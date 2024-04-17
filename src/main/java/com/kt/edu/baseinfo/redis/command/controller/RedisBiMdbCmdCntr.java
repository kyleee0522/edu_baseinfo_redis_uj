/**************************************************************************************
 * ICIS version 1.0
 *
 *  Copyright ⓒ 2022 kt/ktds corp. All rights reserved.
 *
 *  This is a proprietary software of kt corp, and you may not use this file except in
 *  compliance with license agreement with kt corp. Any redistribution or use of this
 *  software, with or without modification shall be strictly prohibited without prior written
 *  approval of kt corp, and the copyright notice above does not evidence any actual or
 *  Integerended publication of such software.
 *************************************************************************************/

package com.kt.edu.baseinfo.redis.command.controller;

import static com.kt.edu.baseinfo.redis.constants.RedisKeyConstants.REDIS_BI_MDB_CSYSCD_GRPID;
import static com.kt.edu.baseinfo.redis.constants.RedisKeyConstants.REDIS_BI_MDB_CSYSCD_GRPID_CD;
import static com.kt.edu.baseinfo.redis.constants.RedisKeyConstants.getRedisKey;

import java.util.ArrayList;
import java.util.List;

import com.kt.edu.baseinfo.comcd.query.payload.out.dto.BiCsyscdOutQryDto;
import com.kt.edu.baseinfo.redis.command.payload.in.dto.RedisCsyscdInCmdDto;
import com.kt.edu.baseinfo.redis.RedisOperator;
import com.kt.edu.baseinfo.redis.constants.RedisKeyConstants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
@Slf4j
public class RedisBiMdbCmdCntr {

	private final JdbcTemplate jdbcTemplate;
	private final RedisOperator<List<BiCsyscdOutQryDto>> redisOperatorCsyscd;

	public void createCsyscdGrpid(){
		// 데이터베이스에서 모든 데이터를 조회하는 쿼리
		String sql = """
    			SELECT bc.GRP_ID as grpId,
						bc.CD as cd,
						bc.NAME as name,
						bc.OUTPUT_SEQ as outputSeq,
						bc.REF1 as ref1,
						bc.REF2 as ref2,
						bc.REF3 as ref3,
						bc.REMARK as remark,
						bc.DEFINE_NAME as defineName,
						bc.END_DATE as endDate,
						bc.START_DATE as startDate,
						bc.REF4 as ref4,
						bc.REF5 as ref5
				   FROM baseinfo.BI_CSYSCD bc
				   WHERE bc.GRP_ID IN ("0002")
				   ORDER BY grpId ASC;
				""";
		List<BiCsyscdOutQryDto> dbList = jdbcTemplate.query(sql, (rs, rowNum) -> {
			// 데이터를 BiCsyscdOutQryDto 객체로 변환하는데 빌더 패턴을 사용
			return BiCsyscdOutQryDto.builder()
					.cd(rs.getString("cd"))
					.name(rs.getString("name"))
					.grpId(rs.getString("grpId"))
					.ref1(rs.getString("ref1"))
					.ref2(rs.getString("ref2"))
					.ref3(rs.getString("ref3"))
					.ref4(rs.getString("ref4"))
					.ref5(rs.getString("ref5"))
					.defineName(rs.getString("defineName"))
					.outputSeq(rs.getBigDecimal("outputSeq"))
					.startDate(rs.getString("startDate"))
					.endDate(rs.getString("endDate"))
					.remark(rs.getString("remark"))
					.build();
		});

		dbList.forEach(dbDto -> {
			String grpId = dbDto.getGrpId();
			String cd = dbDto.getCd();
			// grpId 와 cd 기준으로 key 생성
			String redisKey = String.format(REDIS_BI_MDB_CSYSCD_GRPID_CD.key(), grpId, cd);

			List<BiCsyscdOutQryDto> redisList = redisOperatorCsyscd.getValue(redisKey);

			if (redisList == null || redisList.isEmpty()) {
				// Redis 데이터 없는 경우, 새로 저장 (12 시간 이후 만료)
				redisOperatorCsyscd.set(redisKey, List.of(dbDto), REDIS_BI_MDB_CSYSCD_GRPID_CD.ttl(), REDIS_BI_MDB_CSYSCD_GRPID_CD.unit());
			} else {
				// Redis에 이미 데이터가 있는 경우, 업데이트
				if (!redisList.contains(dbDto)) {
					redisList.add(dbDto);
					redisOperatorCsyscd.set(redisKey, redisList, REDIS_BI_MDB_CSYSCD_GRPID_CD.ttl(), REDIS_BI_MDB_CSYSCD_GRPID_CD.unit());
				}
			}
		});

	}

//	@Scheduled(cron = "0 0 10 * * ?") // 매일 오전 10시 실행
//	public void scheduledCreateCsyscdGrpid(){
//		createCsyscdGrpid();
//	}

	//Redis Key = icis:oder:BI:MDB:CSYSCD:GRP_ID:%s
	//RedisConstants.PREFIX 의 값은 edu:order
	public Integer updateCsyscdGrpid(final String grpId, final List<RedisCsyscdInCmdDto> list) {
		String redisKey = getRedisKey(REDIS_BI_MDB_CSYSCD_GRPID.key(), grpId);
		log.debug("Redis Key = {}", redisKey);

		// 주어진 list 의 각 요소의 속성 값들을 복사하여 BiCsyscdOutQryDto 객체로 생성한 후, 이들을 rList 에 추가
		// 기존 객체의 속성 값을 유지하면서 새로운 객체를 생성하고, 해당 객체들을 새로운 리스트에 추가할 수 있음
		List<BiCsyscdOutQryDto> rList = new ArrayList<>(); // BiCsyscdOutQryDto 객체의 리스트인 rList 생성
		// list 요소를 반복하면서 각 요소에 대해 람다식 실행
		list.forEach(s -> {
			BiCsyscdOutQryDto t = BiCsyscdOutQryDto.builder().build(); // 새로운 BiCsyscdOutQryDto 객체 t 생성
			BeanUtils.copyProperties(s, t); // BeanUtils 클래스를 사용하여 s 요소의 속성 값을 t 객체에 복사
			rList.add(t); // 복사된 t 객체를 rList 에 추가
		});

		// redisOperatorCsyscd : redis 와 상호 작용하기 위해 사용되는 RedisOperator 클래스의 인스턴스
		// redisOperatorCsyscd 를 사용하여 redis 에 값 설정
		// redisKey : redis 에서 사용되는 특정 키 ( 저장된 값에 접근하기 위한 고유한 식별자 역할 )
		// rList : redis 에 저장할 값 (rList 객체의 속성 값들이 redis 에 저장 됨)
		// REDIS_BI_MDB_CSYSCD_GRPID.ttl() : redis 에 설정할 값의 만료 시간
		// REDIS_BI_MDB_CSYSCD_GRPID.unit() : 만료 시간의 단위
		redisOperatorCsyscd.set(redisKey, rList, REDIS_BI_MDB_CSYSCD_GRPID.ttl(), REDIS_BI_MDB_CSYSCD_GRPID.unit());
		return list.size();
	}

	//Redis Key = icis:oder:BI:MDB:CSYSCD:GRP_ID:%s:CD:%s
	public Integer updateCsyscdGrpidCd(final String grpId, final String cd, final List<RedisCsyscdInCmdDto> list) {
		String redisKey = getRedisKey(REDIS_BI_MDB_CSYSCD_GRPID_CD.key(), grpId, cd);
		log.debug("Redis Key = {}", redisKey);

		List<BiCsyscdOutQryDto> rList = new ArrayList<>();
		list.forEach(s -> {
			BiCsyscdOutQryDto t = BiCsyscdOutQryDto.builder().build();
			BeanUtils.copyProperties(s, t);
			rList.add(t);
		});

		redisOperatorCsyscd.set(redisKey, rList, REDIS_BI_MDB_CSYSCD_GRPID_CD.ttl(), REDIS_BI_MDB_CSYSCD_GRPID_CD.unit());
		return list.size();
	}

}

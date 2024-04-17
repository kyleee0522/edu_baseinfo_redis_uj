package com.kt.edu.baseinfo.comcd.query.repository.sql;

import com.kt.edu.baseinfo.comcd.query.payload.in.dto.BiCsyscdInQryDto;
import org.apache.ibatis.jdbc.SQL;

public class BiCsyscdQrySql {
	public static String selectCsyscd(BiCsyscdInQryDto inDto) {
		String qry = new SQL() {
		{
			SELECT("GRP_ID AS grpId");
			SELECT("CD AS cd");
			SELECT("NAME AS name");
			SELECT("VAL1 AS val1");
			SELECT("VAL2 AS val2");
			SELECT("VAL3 AS val3");
			SELECT("VAL4 AS val4");
			SELECT("VAL5 AS val5");
			SELECT("REG_DATE AS regDate");
			FROM("BI_CSYSCD");
			if(inDto.getGrpId() != null && !"".equals(inDto.getGrpId().trim())) {
				WHERE("GRP_ID = '"+inDto.getGrpId()+"'");
			}
			if(inDto.getCd() != null && !"".equals(inDto.getCd().trim())) {
				WHERE("CD = '"+inDto.getCd()+"'");
			}
		}}.toString();
		
		return qry;
	}	
}

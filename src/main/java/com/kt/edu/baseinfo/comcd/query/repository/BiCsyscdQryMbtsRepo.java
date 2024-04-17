package com.kt.edu.baseinfo.comcd.query.repository;

import java.util.List;

import com.kt.edu.baseinfo.comcd.query.payload.in.dto.*;
import com.kt.edu.baseinfo.comcd.query.payload.out.dto.BiCsyscdOutQryDto;
import com.kt.edu.baseinfo.comcd.query.repository.dto.BiCsyscdQryDto;
import com.kt.edu.baseinfo.comcd.query.repository.sql.BiCsyscdDynamicQrySql;
import com.kt.edu.baseinfo.comcd.query.repository.sql.BiCsyscdQrySql;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.SelectProvider;


@Mapper
public interface BiCsyscdQryMbtsRepo {
	@SelectProvider(type = BiCsyscdQrySql.class, method = "selectCsyscd")
	public List<BiCsyscdQryDto> selectCsyscd(BiCsyscdInQryDto inDto);


	@SelectProvider(type = BiCsyscdDynamicQrySql.class, method = "findCsyscdSql" )
	List<BiCsyscdOutQryDto> findCsyscd(BiCsyscdInQryDto inDto);


/*
	@SelectProvider(type = BiCsyscdDynamicQrySql.class, method = "findCsyscdInSql" )
	List<BiCsyscdOutQryDto> findCsyscdInSql(List<BiCsyscdGetNameAndCdInQryDto> inDtos, String effectiveDateCheck);

*/
}

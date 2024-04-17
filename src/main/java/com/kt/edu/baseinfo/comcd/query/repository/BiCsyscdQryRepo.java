package com.kt.edu.baseinfo.comcd.query.repository;

import com.kt.edu.baseinfo.comcd.query.repository.entity.BiCsyscdQryEntt;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BiCsyscdQryRepo extends PagingAndSortingRepository<BiCsyscdQryEntt, String>{
	public BiCsyscdQryEntt findByGrpIdAndCd(String grpId, String cd);	
}

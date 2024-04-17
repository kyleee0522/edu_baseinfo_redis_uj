package com.kt.edu;

import com.kt.edu.baseinfo.redis.command.controller.RedisBiMdbCmdCntr;
import com.kt.edu.baseinfo.redis.command.payload.in.dto.RedisCsyscdInCmdDto;
import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.util.ArrayList;
import java.util.List;
import java.util.TimeZone;

@EnableEncryptableProperties
@EnableCaching
@EnableFeignClients
//@EnableScheduling
@SpringBootApplication
@ComponentScan("com.kt")

// CommandLineRunner 를 통해, 어플리케이션 시작 시, 자동으로 특정 로직 실행하도록 설정
public class EduBaseinfoApplication implements CommandLineRunner {

	@Autowired
	private RedisBiMdbCmdCntr redisBiMdbCmdCntr;

	public static void main(String[] args) {
//		// 어플리케이션 전역의 기본 시간대 설정
//		TimeZone.setDefault(TimeZone.getTimeZone("Asia/Seoul"));
		SpringApplication.run(EduBaseinfoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		redisBiMdbCmdCntr.createCsyscdGrpid(); // 애플리케이션 시작 시 createCsyscdGrpid 메소드 실행
	}
}


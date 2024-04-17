package com.kt.edu.baseinfo.redis;

import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Slf4j // lombok 프로젝트의 어노테이션, logging 인스턴스 생성하는 역할
@Repository
public class RedisOperator<T> {

    @Autowired
    @Qualifier("redisObjectTemplate") // 여러 개의 동일한 타입의 Bean 있을 때, 특정 Bean을 선택하기 위해 사용
    private RedisTemplate<String, T> redisTemplate;

    @Resource(name = "redisObjectTemplate") // 이름을 기준으로 Bean을 찾아서 주입받는 어노테이션. redisObjectTemplate 이름의 Bean을 주입 받음
    private ValueOperations<String, T> valueOps; // ValueOperations : redis에서 값을 가져오고 저장하는 메소드를 제공하는 스프링의 인터페이스

    @Resource(name = "redisObjectTemplate")
    private ValueOperations<String, List<T>> valueOpsList; // ValueOperations : redis에서 값을 가져오고 저장하는 메소드를 제공하는 스프링의 인터페이스

    public RedisOperator(){
    }
    // 주어진 키를 사용하여 redis 에서 값을 가져오는 메소드
    // valueOps.get(key)를 사용하여 redis 에서 값을 가져옴
    public T getValue(String key) {
        try {
            return  valueOps.get(key);

        } catch (Exception e) {
            log.error("[CTG:CMMN] RedisOperator getValue error : {} " , e.getMessage());
            return null;
        }
    }
    // 주어진 키를 사용하여 redis 에서 리스트 값을 가져오는 메소드
    public List<T> getListValue(String key) {
        try {
            return valueOpsList.get(key);
        } catch (Exception e) {
            log.error("[CTG:CMMN] RedisOperator getListValue error : {}", e.getMessage());
            return null;
        }
    }
    // 주어진 키-값을 사용하여 redis 에 값을 설정하는 메소드
    // 지정된 시간 이후에 값이 만료됨
    public void set(String key, T value, long timeout, TimeUnit timeUnit) {
        try {
            valueOps.set(key, value, timeout,  timeUnit);
        } catch (Exception e) {
            log.error("[CTG:CMMN] RedisOperator set  error : {}", e.getMessage());
        }
    }
    // 주어진 키-리스트 값을를 사용하여 redis 에 리스트 값을 설정하는 메소드
    public void setList(String key, List<T> list, long timeout, TimeUnit timeUnit){
        try {
            valueOpsList.set(key, list, timeout, timeUnit);
        } catch (Exception e) {
            log.error("[CTG:CMMN] RedisOperator setList  error: {}", e.getMessage());
        }
    }
    // 주어진 키에 해당하는 redis 데이터를 삭제하는 메소드
    // redisTemplate.delete(key) 를 사용하여 redis 에서 키 삭제
    public void delete(String key) {
        try {
            redisTemplate.delete(key);
            log.info("[CTG:CMMN] RedisOperator delete --- key: {}", key);
        } catch (Exception e) {
            log.error("[CTG:CMMN] RedisOperator delete  error: {}",  e.getMessage());
        }
    }
    public void appendToList(String key, T value, long timeout, TimeUnit timeUnit) {
        ListOperations<String, T> listOps = redisTemplate.opsForList();
        listOps.rightPush(key, value);

        // 필요한 경우, 여기서 키의 만료 시간을 설정할 수 있습니다. 예: 24시간 후 만료
        // redisTemplate.expire(key, 24, TimeUnit.HOURS);
    }

    // RedisCommand 를 수행하기 위해 RedisTemplate 을 반환하는 메소드
    public Iterable<byte[]> getRedisTemplate(RedisCallback<Iterable<byte[]>> redisCallback) {
        return (Iterable<byte[]>) redisTemplate.execute((RedisCallback<T>) redisCallback);
    }
}

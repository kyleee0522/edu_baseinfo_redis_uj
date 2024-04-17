package com.kt.edu.baseinfo.redis.constants;

import java.util.concurrent.TimeUnit;

/**
 * Redis Key 목록 상수
 * REIDS_%LOB코드%_%비즈니스명%_%테이블명's%_%필드명%_%값% 으로 명명
 * key, ttl time, ttl timeunit 으로 정의
 */

public enum RedisKeyConstants {

    //MDB BI_CSYSCD : grp_id
    //RedisConstants.PREFIX 의 값은 edu:order
    //RedisConstants.DELIM 의 값은 클론(:)

    REDIS_BI_MDB_CSYSCD_GRPID(RedisConstants.PREFIX+RedisConstants.DELIM+

            "BI"+ RedisConstants.DELIM+

            "MDB"+RedisConstants.DELIM+

            "CSYSCD"+RedisConstants.DELIM+

            "GRP_ID"+RedisConstants.DELIM+

            "%s",

            24,       TimeUnit.HOURS),



    //MDB BI_CSYSCD : grp_id + cd

    REDIS_BI_MDB_CSYSCD_GRPID_CD( RedisConstants.PREFIX+RedisConstants.DELIM+

            "MDB"+RedisConstants.DELIM+
            "CSYSCD"+RedisConstants.DELIM+
            "GRP_ID"+RedisConstants.DELIM+
            "%s"+RedisConstants.DELIM+
            "CD"+RedisConstants.DELIM+
            "%s",
            30,       TimeUnit.MINUTES);

    private String key;
    private int ttl;
    private TimeUnit unit;


    private RedisKeyConstants(String key, int ttl, TimeUnit unit) {
        this.key = key;
        this.ttl = ttl;
        this.unit = unit;
    }

    public String key() {
        return this.key;
    }

    public int ttl() {
        return this.ttl;
    }

    public TimeUnit unit() {
        return this.unit;
    }

    public static String getRedisKey(String key, Object... args) {
        return String.format(key, args);
    }

}
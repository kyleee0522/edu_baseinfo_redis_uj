package com.kt.edu.baseinfo.common.utils;


import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.*;
import com.kt.edu.baseinfo.common.exception.JsonException;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

import static java.lang.String.format;


/**
 * JSON 처리 유틸리티 클래스
 *
 */
@Slf4j
public final class JsonUtil {

    private static ObjectMapper MAPPER = createNewObjectMapper(true);

    private static ObjectMapper MAPPER_NON_EMPTY = createNewObjectMapper(false);

    private JsonUtil() {
    }

    /**
     * 객체 매퍼(NULL 또는 공백 엘리먼트 포함)를 얻는다.
     *
     * @return 객체 매퍼(NULL 또는 공백 엘리먼트 포함)
     */
    public static ObjectMapper getObjectMapper() {
        return MAPPER;
    }

    /**
     * 객체 매퍼(NULL 또는 공백 엘리먼트 포함 안 함)를 얻는다.
     *
     * @return 객체 매퍼(NULL 또는 공백 엘리먼트 포함 안 함)
     */
    public static ObjectMapper getObjectMapperNonEmpty() {
        return MAPPER_NON_EMPTY;
    }

    /**
     * 객체 매퍼를 생성한다.
     *
     * @param includeType NULL 또는 공백 엘리먼트 포함 여부
     * @return 객체 매퍼
     */
    public static ObjectMapper createNewObjectMapper(boolean includeType) {
        final ObjectMapper MAPPER = new ObjectMapper();
        if (includeType) {
            MAPPER.setSerializationInclusion(JsonInclude.Include.ALWAYS);
        } else {
            MAPPER.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
        }
        MAPPER.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        MAPPER.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        MAPPER.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        MAPPER.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);
        MAPPER.configure(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT, true);
        MAPPER.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        MAPPER.setTimeZone(TimeZone.getTimeZone("Asia/Seoul"));
        return MAPPER;
    }

    /**
     * 객체를 주어진 타입의 객체로 변환한다.
     *
     * @param <T> 타입
     * @param object 객체 타입
     * @param clazz 클래스
     * @return 주어진 타입의 객체
     */
    public static <T> T convertValue(Object object, Class<T> clazz) {
        try {
            if (object == null) {
                return null;
            } else {
                return MAPPER.convertValue(object, clazz);
            }
        } catch (IllegalArgumentException e) {
            throw new JsonException(format("Unable to read object '%s' from input '%s'", clazz.getSimpleName(), object),
                    e);
        }
    }

    /**
     * 객체를 주어진 타입의 객체로 읽어들인다.
     *
     * @param <T> 타입
     * @param object 객체
     * @param clazz 클래스
     * @return 주어진 타입의 객체
     */
    public static <T> T readValue(byte[] object, Class<T> clazz) {
        try {
            if (object == null) {
                return null;
            } else {
                return MAPPER.readValue(object, clazz);
            }
        } catch (IllegalArgumentException e) {
            throw new JsonException(format("Unable to read object '%s' from input '%s'", clazz.getSimpleName(), object),
                    e);
        } catch (JsonParseException e) {
            throw new JsonException(format("Unable to read object '%s' from input '%s'", clazz.getSimpleName(), object),
                    e);
        } catch (JsonMappingException e) {
            throw new JsonException(format("Unable to read object '%s' from input '%s'", clazz.getSimpleName(), object),
                    e);
        } catch (IOException e) {
            throw new JsonException(format("Unable to read object '%s' from input '%s'", clazz.getSimpleName(), object),
                    e);
        }
    }

    /**
     * JSON 문자열을 주어진 타입의 객체로 읽어들인다.
     *
     * @param <T> 타입
     * @param jsonString JSON 문자열
     * @param clazz 클래스
     * @return 주어진 타입의 객체
     */
    public static <T> T readJson(final String jsonString, final Class<T> clazz) {
        try {
            return MAPPER.readValue(jsonString, clazz);
        } catch (IOException e) {
            throw new JsonException(
                    format("Unable to read JSON object '%s' from input '%s'", clazz.getSimpleName(), jsonString), e);
        }
    }

    /**
     * JSON 문자열을 주어진 타입 레퍼런스가 가리키는 객체로 읽어들인다.
     *
     * @param <T> 타입
     * @param jsonString JSON 문자열
     * @param typeReference 타입 레퍼런스
     * @return 주어진 타입의 객체
     */
    public static <T> T readJson(final String jsonString, final TypeReference<T> typeReference) {
        try {
            return MAPPER.readValue(jsonString, typeReference);
        } catch (IOException e) {
            throw new JsonException(
                    format("Unable to read JSON object '%s' from input '%s'", typeReference.getType(), jsonString), e);
        }
    }

    /**
     * JSON 바이트를 주어진 타입 레퍼런스가 가리키는 객체로 읽어들인다.
     *
     * @param <T> 타입
     * @param jsonByte JSON 바이트
     * @param typeReference 타입 레퍼런스
     * @return 주어진 타입의 객체
     */
    public static <T> T readJson(final byte[] jsonByte, final TypeReference<T> typeReference) {
        try {
            return MAPPER.readValue(new String(jsonByte, "UTF-8"), typeReference);
        } catch (IOException e) {
            throw new JsonException(
                    format("Unable to read JSON object '%s' from input '%s'", typeReference.getType(), jsonByte), e);
        }
    }

    /**
     * JSON 문자열을 주어진 JSON 노드로 읽어들인다.
     *
     * @param jsonString JSON 문자열
     * @return 주어진 타입의 객체
     */
    public static JsonNode readJson(final String jsonString) {
        try {
            return MAPPER.readTree(jsonString);
        } catch (IOException e) {
            throw new JsonException(format("Unable to read JSON node from input '%s'", jsonString), e);
        }
    }

    /**
     * JSON 문자열을 키가 문자열, 값이 객체인 맵으로 읽어들인다.
     *
     * @param jsonString JSON 문자열
     * @return 주어진 타입의 객체
     */
    public static Map<String, Object> readJsonToMap(final String jsonString) {
        return readJson(jsonString, new TypeReference<HashMap<String, Object>>() {
        });
    }

    /**
     * JSON 문자열을 키가 문자열, 값이 객체인 맵의 리스트로 읽어들인다.
     *
     * @param jsonString JSON 문자열
     * @return 주어진 타입의 객체
     */
    public static List<Map<String, Object>> readJsonToList(final String jsonString) {
        return readJson(jsonString, new TypeReference<List<Map<String, Object>>>() {
        });
    }

    /**
     * 임의 타입(T)의 객체를 JSON 문자열로 변환한다.
     *
     * @param <T> 타입
     * @param object 객체
     * @return JSON 문자열
     */
    public static <T> String toJson(final T object) {
        try {
            return MAPPER.writeValueAsString(object);
        } catch (final JsonProcessingException e) {
            throw new JsonException(format("Unable to write JSON object '%s'", object.getClass().getSimpleName()), e);
        }
    }

    /**
     * 임의 타입(T)의 객체를 JSON 노드로 변환한다.
     *
     * @param <T> 타입
     * @param object 객체
     * @return JSON 노드
     */
    public static <T> JsonNode toJsonNode(final T object) {
        final String jsonString = toJson(object);
        return readJson(jsonString);
    }

    /**
     * 임의 타입(T)의 객체를 JSON 노드로 변환한다.
     *
     * @param <T> 타입
     * @param object 객체
     * @param mapper 객체 매퍼
     * @return JSON 문자열
     */
    public static <T> String toJson(final T object, ObjectMapper mapper) {
        try {
            return mapper.writeValueAsString(object);
        } catch (final JsonProcessingException e) {
            throw new JsonException(format("Unable to write JSON object '%s'", object.getClass().getSimpleName()), e);
        }
    }

    // Json 을 Object로 변환
    public static <T> T jsonToObject(String jsonStr, String keyPath, TypeReference<T> typeReference) {
        T objT = null;
        try {
            String json = MAPPER.readTree(jsonStr)
                    .at(keyPath)
                    .toString();
            if (json != null) {
                objT = MAPPER.readValue(json, typeReference);
            }
        } catch (JsonProcessingException e) {
            log.error("eror:{}", e.getMessage());
        }
        return objT;

    }



    /**
     * 주어진 리소스 경로에 있는 파일을 JSON 노드로 읽어들인다.
     *
     * @param resourcePath 리소스 경로
     * @return JSON 노드
     */
    /*public static JsonNode readJsonNodeFromFile(final String resourcePath) {
        File file = new File(resourcePath);
        //try (BufferedReader bufferedReader = Files.newReader(file, Charsets.UTF_8)) {
        try (BufferedReader bufferedReader = Files.newReader(file, Charsets.UTF_8)) {
            return MAPPER.readTree(bufferedReader);
        } catch (IOException e) {
            throw new JsonException(format("Unable to read JSON node from resource path '%s'", resourcePath), e);
        }
    }*/



    /**
     * JSON 문자열을 JSON 노드로 변환 후 문자열화한다. (예쁘게 출력하는 기능 없음)
     *
     * @param jsonString JSON 문자열
     * @return 있는 그대로 변환된 문자열
     */
    public static String unprettify(final String jsonString) {
        try {
            JsonNode node = MAPPER.readValue(jsonString, JsonNode.class);
            return MAPPER.writeValueAsString(node);
        } catch (IOException e) {
            throw new JsonException(format("Unable to read JSON node from input '%s'", jsonString), e);
        }
    }

    /**
     * JSON 문자열을 JSON 노드로 변환 후 문자열화한다. (예쁘게 출력하는 기능 있음)
     *
     * @param jsonString JSON 문자열
     * @return 예쁘게 변환된 문자열
     */
    public static String prettify(final String jsonString) {
        try {
            JsonNode node = MAPPER.readValue(jsonString, JsonNode.class);
            ObjectWriter writer = MAPPER.writer(new DefaultPrettyPrinter());
            return writer.writeValueAsString(node);
        } catch (IOException e) {
            throw new JsonException(format("Unable to read JSON node from input '%s'", jsonString), e);
        }
    }

    /**
     * 객체를 JSON 노드로 변환 후 문자열화한다. (예쁘게 출력하는 기능 있음)
     *
     * @param object 객체
     * @return 예쁘게 변환된 문자열
     */
    public static String prettify(final Object object) {
        try {
            JsonNode node = MAPPER.convertValue(object, JsonNode.class);
            ObjectWriter writer = MAPPER.writer(new DefaultPrettyPrinter());
            return writer.writeValueAsString(node);
        } catch (IOException e) {
            throw new JsonException(format("Unable to read JSON node from input '%s'", object), e);
        }
    }
}
package org.flab.api.global.cache;

import org.springframework.cache.interceptor.KeyGenerator;

import java.lang.reflect.Method;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CacheKeyGenerator implements KeyGenerator {

    @Override
    public Object generate(Object target, Method method, Object... params) {
        return generateKey(params);
    }

    /**
     * tiketo:{domain}:{id}:{target}
     * @param params
     * @return
     */
    public static Object generateKey(Object... params) {
        return Stream.of(params)
                .map(String::valueOf) // 파라미터를 문자열로 변환
                .collect(Collectors.joining(":"));
    }
}

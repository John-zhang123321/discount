package com.zl.gateway.util;

import cn.hutool.core.collection.CollectionUtil;
import com.alibaba.fastjson.JSON;
import com.google.common.base.Joiner;
import com.google.common.collect.Lists;
import com.google.common.collect.SortedSetMultimap;
import com.google.common.collect.TreeMultimap;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.util.MultiValueMap;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@SuppressWarnings("WeakerAccess")
public final class SignUtils {

    private static final Logger LOGGER = LoggerFactory.getLogger(SignUtils.class);
    private static final String SIGN_KEY = "_sign";

    public static boolean checkSign(String appKey, ServerHttpRequest request, String body ) {
        String sign = request.getQueryParams().getFirst(SIGN_KEY);

        if (StringUtils.isEmpty(sign)) {
            return false;
        }
        SortedSetMultimap<String, String> sortedMap = TreeMultimap.create();
        MultiValueMap<String, String> multiValueMap = request.getQueryParams();

        if (CollectionUtil.isNotEmpty(multiValueMap)) {
            multiValueMap.entrySet().stream()
                    .filter(entry -> !SIGN_KEY.contains(entry.getKey()))
                    .forEach(entry -> sortedMap.putAll(entry.getKey(),entry.getValue()));
        }
        if (StringUtils.isNotBlank(body)) {
            sortedMap.put("_body", body);
        }
        String genSign = genSign(appKey, sortedMap);
        return sign.equalsIgnoreCase(genSign);
    }

    private static String genSign(String appKey, SortedSetMultimap<String, String> sortedMap) {
        StringBuilder sb = new StringBuilder();
        if (sortedMap != null && sortedMap.size() > 0) {
            String params = Joiner.on("")
                    .withKeyValueSeparator("")
                    .join(sortedMap.entries());

            sb.append(params);
        }
        sb.append(appKey);
        String jsonString = sb.toString();
        LOGGER.info("_sign string: " + jsonString);
        return DigestUtils.md5Hex(jsonString);
    }
}

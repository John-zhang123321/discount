package com.zl.user.utils;

import cn.hutool.core.collection.CollectionUtil;
import com.zl.common.constants.ErrorCodeConstants;
import com.zl.user.bo.BaseRegionBo;
import com.zl.user.expection.WxException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by zhangliang on 2019/7/6
 */
public class BaseRegionUtil {

    private static List<BaseRegionBo> getProvinces(List<BaseRegionBo> baseRegionBos) {
        //获取省
        return baseRegionBos.stream().filter(baseReginSmallBo -> baseReginSmallBo.getDepth() == 1).collect(Collectors.toList());
    }

    private static List<BaseRegionBo> getCitys(List<BaseRegionBo> baseRegionBos) {
        //获取市
        return baseRegionBos.stream().filter(baseRegion -> baseRegion.getDepth() == 2).collect(Collectors.toList());
    }

    private static List<BaseRegionBo> getAreas(List<BaseRegionBo> baseRegionBos) {
        //获取区
        return baseRegionBos.stream().filter(baseRegion -> baseRegion.getDepth() == 3).collect(Collectors.toList());
    }

    public static List<BaseRegionBo> getBaseRegion(List<BaseRegionBo> baseRegionBos,boolean needAreas) {
        List<BaseRegionBo> provinces = getProvinces(baseRegionBos);
        if (CollectionUtil.isEmpty(provinces)) {
            return provinces;
        }
        List<BaseRegionBo> citys = getCitys(baseRegionBos);
        if (CollectionUtil.isEmpty(citys)) {
            return provinces;
        }
        Map<String, List<BaseRegionBo>> cityRegionMap = citys.stream().collect(Collectors.groupingBy(BaseRegionBo::getParentRegionId));

        for (BaseRegionBo proince : provinces) {
            proince.setChildren(cityRegionMap.get(proince.getId()));
        }

        if(needAreas){
            List<BaseRegionBo> areas = getAreas(baseRegionBos);
            if (CollectionUtil.isEmpty(citys)) {
                return provinces;
            }
            Map<String, List<BaseRegionBo>> areasRegionMap = areas.stream().collect(Collectors.groupingBy(BaseRegionBo::getParentRegionId));
            for (BaseRegionBo city : citys) {
                city.setChildren(areasRegionMap.get(city.getId()));
            }
        }

        return provinces;
    }
}

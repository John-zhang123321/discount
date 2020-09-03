package com.zl.power.service.impl;

import com.zl.power.mapper.JobMapper;
import com.zl.power.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by zhangliang on 2019/5/24
 */
@Service
public class JobServiceImpl implements JobService {

    @Autowired
    JobMapper jobMapper;

//    @Autowired
//    DeptMapper deptMapper;


//    @Override
//    public List<JobVo>  getByParam(JobQueryDTO jobParamDTO) {
////        List<JobVo> byParam = jobMapper.getByParam(jobParamDTO);
////        byParam = byParam.stream().sorted(Comparator.comparing(JobVo::getSort)).collect(Collectors.toList());
////        for (JobVo jobVo : byParam) {
////            Map<String, String> superDept = deptMapper.getSuperDeptByDeptId(jobVo.getDid());
////            if (superDept != null) {
////                jobVo.setPid(superDept.get("pid"));
////                jobVo.setPname(superDept.get("pname"));
////            }
////
////        }
//
//        return null;
//    }
}

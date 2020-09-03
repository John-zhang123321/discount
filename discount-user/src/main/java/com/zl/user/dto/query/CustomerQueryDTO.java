package com.zl.user.dto.query;

import lombok.Data;
import javax.validation.constraints.*;
import org.hibernate.validator.constraints.Range;


/**
* @author zhangliang
* @date 2019-11-18
*/
@Data
public class CustomerQueryDTO{

    private int pageNum = 1;

    private int pageSize = 10;










}
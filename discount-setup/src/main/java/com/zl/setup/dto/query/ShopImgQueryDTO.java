package com.zl.setup.dto.query;

import lombok.Data;
import javax.validation.constraints.*;
import org.hibernate.validator.constraints.Range;


/**
* @author zhangliang
* @date 2019-11-20
*/
@Data
public class ShopImgQueryDTO{

    private int pageNum = 1;

    private int pageSize = 10;





}
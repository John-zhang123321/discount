package com.zl.user.dto.query;

import lombok.Data;
import javax.validation.constraints.*;
import org.hibernate.validator.constraints.Range;
import java.math.BigDecimal;
import java.sql.Timestamp;

/**
* @author zhangliang
* @date 2020-01-04
*/
@Data
public class SearchHistoryQueryDTO{

    private int pageNum = 1;

    private int pageSize = 10;




}
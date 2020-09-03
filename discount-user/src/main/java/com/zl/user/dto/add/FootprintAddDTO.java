package com.zl.user.dto.add;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
* @author zhangliang
* @date 2019-12-05
*/
@Data
public class FootprintAddDTO {
    // 店铺id
    @NotNull(message = "店铺id不能为空")
    private Long shopId;

    private Long userId;

}
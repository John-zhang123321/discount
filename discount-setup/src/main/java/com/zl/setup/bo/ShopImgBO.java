package com.zl.setup.bo;

import lombok.Data;
import java.io.Serializable;
import java.util.Date;
/**
* @author zhangliang
* @date 2019-11-20
*/
@Data
public class ShopImgBO {

    private Long id;

    private Long shopId;

    private String type;

    private String url;

}
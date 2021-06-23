package com.work.blog.vo;

import lombok.Data;

@Data
public class PageVo {
    Integer pageSize =8;
    Integer pageNo=1;
    String blogName;
}

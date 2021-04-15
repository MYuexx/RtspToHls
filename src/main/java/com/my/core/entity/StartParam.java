package com.my.core.entity;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * 〈一句话功能简述〉<br>
 * 〈〉
 *
 * @author M.Y
 * @date 2021/4/14
 * @since 1.0.0
 */
@Data
public class StartParam {

    @NotBlank(message = "url不能为空")
    private String url;

    private String name;
}
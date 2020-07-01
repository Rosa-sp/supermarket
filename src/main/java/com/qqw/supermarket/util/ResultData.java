package com.qqw.supermarket.util;

import lombok.*;

/**
 * @Classname ResultData
 * @Description TODO()
 * @Date 2020/2/10 19:01
 * @Author by Alex
 */
@Data
@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
public class ResultData {

    /**
     *状态码   1表示成功
     *        0表示失败
     */
    @NonNull
    private int code;

    /**
     * 信息
     */
    @NonNull
    private String message;

    /**
     *数据
     */
    private Object data;
}

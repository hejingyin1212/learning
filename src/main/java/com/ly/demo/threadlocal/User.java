package com.ly.demo.threadlocal;

import lombok.Data;
import lombok.ToString;

import java.time.LocalDate;

/**
 * @author ：LY
 * @date ：2021/2/25 14:53
 * @description ：User
 */

@Data
@ToString
public class User {
    private Integer userId;
    private String name;
    private LocalDate birthday;
}

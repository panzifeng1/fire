package com.jyu.fire.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Log {
    private Integer id;
    private Integer userId;
    private LocalDateTime createTime;
    private String ip;
    private String userAgent;
    private String note;
}

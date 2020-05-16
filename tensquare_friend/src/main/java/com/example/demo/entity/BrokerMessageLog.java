package com.example.demo.entity;

import lombok.Data;

import java.util.Date;
import java.util.List;
@Data
public class BrokerMessageLog {
    private String messageId;
    private String id;
    private String message;

    private Integer tryCount = 0;

    private String status;

    private Date nextRetry;

    private Date createTime;

    private Date updateTime;

    private List<Order> orderList;

}
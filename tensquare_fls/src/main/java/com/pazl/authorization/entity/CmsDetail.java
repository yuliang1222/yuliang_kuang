package com.pazl.authorization.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class CmsDetail {
    private Long id;

    private Long cdno;

    private String cdname;

    private String job;

    private Long mgr;

    private Date hiredata;

    private BigDecimal sal;

    private BigDecimal comm;

    private Long cmsno;


}
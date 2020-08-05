package com.pazl.authorization.mapper;

import com.pazl.authorization.entity.CmsDetail;
import org.springframework.stereotype.Component;

@Component
public interface CmsDetailMapper{
    int deleteByPrimaryKey(Long id);

    int insert(CmsDetail record);

    int insertSelective(CmsDetail record);

    CmsDetail selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(CmsDetail record);

    int updateByPrimaryKey(CmsDetail record);
}
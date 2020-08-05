package com.examp;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Component;

@Component
public interface CmsDetailMapper extends BaseMapper<CmsDetail> {
    int deleteByPrimaryKey(Long id);
    int insertSelective(CmsDetail record);

    CmsDetail selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(CmsDetail record);

    int updateByPrimaryKey(CmsDetail record);
}
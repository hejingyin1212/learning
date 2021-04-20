package com.ly.dao;

import com.ly.entity.TCase;
import com.ly.entity.TCaseExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TCaseMapper {
    long countByExample(TCaseExample example);

    int deleteByExample(TCaseExample example);

    int deleteByPrimaryKey(Long id);

    int insert(TCase record);

    int insertSelective(TCase record);

    List<TCase> selectByExample(TCaseExample example);

    TCase selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") TCase record, @Param("example") TCaseExample example);

    int updateByExample(@Param("record") TCase record, @Param("example") TCaseExample example);

    int updateByPrimaryKeySelective(TCase record);

    int updateByPrimaryKey(TCase record);
}

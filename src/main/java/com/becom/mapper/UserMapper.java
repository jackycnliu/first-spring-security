package com.becom.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.becom.entity.UserInfo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends BaseMapper<UserInfo> {
}

package com.becom.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.becom.entity.UserInfo;
import com.becom.mapper.UserMapper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserServiceImpl implements UserDetailsService {

    @Resource
    UserMapper userMapper;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        QueryWrapper<UserInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_name",username);
        UserInfo user =  userMapper.selectOne(queryWrapper);
        if(user!=null){
            return user;
        }else{
          throw new UsernameNotFoundException("user not found");
        }
    }
}

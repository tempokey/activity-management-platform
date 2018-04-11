package com.edudigital.cloudy.amp.textbook.service.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import com.edudigital.cloudy.amp.textbook.service.entity.po.UserPO;

@Component
public interface UserMapper {

	public UserPO getUser(@Param("user") UserPO u);

	public List<UserPO> getUserByAccount(@Param("user") UserPO userDO);

	public int add(@Param("u") UserPO userDO);
}

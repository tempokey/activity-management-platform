package com.edudigital.cloudy.amp.msg.service.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;
import com.edudigital.cloudy.amp.msg.service.entity.po.MsgTemplatePO;
import com.edudigital.cloudy.amp.msg.service.entity.po.RabbitMqMsgPO;

@Service
public interface MsgMapper {

	MsgTemplatePO getMsgTemplate(@Param("m") MsgTemplatePO msgTemplatePO);

	public List<RabbitMqMsgPO> getRabbitMqConfig();

}

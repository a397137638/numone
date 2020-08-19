package com.numberone.kafka.service.impl;

import java.util.List;

import com.numberone.kafka.domain.Kafkamessage;
import com.numberone.kafka.mapper.KafkamessageMapper;
import com.numberone.kafka.service.IKafkamessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.numberone.common.support.Convert;

/**
 * kafka消息 服务层实现
 * 
 * @author numberone
 * @date 2020-07-30
 */
@Service
public class KafkamessageServiceImpl implements IKafkamessageService
{
	@Autowired
	private KafkamessageMapper kafkamessageMapper;

	/**
     * 查询kafka消息信息
     * 
     * @param messageId kafka消息ID
     * @return kafka消息信息
     */
    @Override
	public Kafkamessage selectKafkamessageById(Integer messageId)
	{
	    return kafkamessageMapper.selectKafkamessageById(messageId);
	}
	
	/**
     * 查询kafka消息列表
     * 
     * @param kafkamessage kafka消息信息
     * @return kafka消息集合
     */
	@Override
	public List<Kafkamessage> selectKafkamessageList(Kafkamessage kafkamessage)
	{
	    return kafkamessageMapper.selectKafkamessageList(kafkamessage);
	}
	
    /**
     * 新增kafka消息
     * 
     * @param kafkamessage kafka消息信息
     * @return 结果
     */
	@Override
	public int insertKafkamessage(Kafkamessage kafkamessage)
	{
	    return kafkamessageMapper.insertKafkamessage(kafkamessage);
	}
	
	/**
     * 修改kafka消息
     * 
     * @param kafkamessage kafka消息信息
     * @return 结果
     */
	@Override
	public int updateKafkamessage(Kafkamessage kafkamessage)
	{
	    return kafkamessageMapper.updateKafkamessage(kafkamessage);
	}

	/**
     * 删除kafka消息对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteKafkamessageByIds(String ids)
	{
		return kafkamessageMapper.deleteKafkamessageByIds(Convert.toStrArray(ids));
	}
	
}

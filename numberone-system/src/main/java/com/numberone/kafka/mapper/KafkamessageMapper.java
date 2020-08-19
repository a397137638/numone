package com.numberone.kafka.mapper;

import com.numberone.kafka.domain.Kafkamessage;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * kafka消息 数据层
 * 
 * @author numberone
 * @date 2020-07-30
 */
@Component
public interface KafkamessageMapper 
{
	/**
     * 查询kafka消息信息
     * 
     * @param messageId kafka消息ID
     * @return kafka消息信息
     */
	public Kafkamessage selectKafkamessageById(Integer messageId);
	
	/**
     * 查询kafka消息列表
     * 
     * @param kafkamessage kafka消息信息
     * @return kafka消息集合
     */
	public List<Kafkamessage> selectKafkamessageList(Kafkamessage kafkamessage);
	
	/**
     * 新增kafka消息
     * 
     * @param kafkamessage kafka消息信息
     * @return 结果
     */
	public int insertKafkamessage(Kafkamessage kafkamessage);
	
	/**
     * 修改kafka消息
     * 
     * @param kafkamessage kafka消息信息
     * @return 结果
     */
	public int updateKafkamessage(Kafkamessage kafkamessage);
	
	/**
     * 删除kafka消息
     * 
     * @param messageId kafka消息ID
     * @return 结果
     */
	public int deleteKafkamessageById(Integer messageId);
	
	/**
     * 批量删除kafka消息
     * 
     * @param messageIds 需要删除的数据ID
     * @return 结果
     */
	public int deleteKafkamessageByIds(String[] messageIds);
	
}
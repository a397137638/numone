package com.numberone.kafka.service;

import com.numberone.kafka.domain.Kafkamessage;

import java.util.List;

/**
 * kafka消息 服务层
 * 
 * @author numberone
 * @date 2020-07-30
 */
public interface IKafkamessageService 
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
     * 删除kafka消息信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteKafkamessageByIds(String ids);
	
}

package com.numberone.activiti.service;

import com.numberone.activiti.domain.ActReModel;

import java.util.List;

/**
 * 流程model 服务层
 * 
 * @author numberone
 * @date 2020-06-17
 */
public interface IActReModelService 
{
	/**
     * 查询流程model信息
     * 
     * @param id 流程modelID
     * @return 流程model信息
     */
	public ActReModel selectActReModelById(String id);
	
	/**
     * 查询流程model列表
     * 
     * @param actReModel 流程model信息
     * @return 流程model集合
     */
	public List<ActReModel> selectActReModelList(ActReModel actReModel);
	
	/**
     * 新增流程model
     * 
     * @param actReModel 流程model信息
     * @return 结果
     */
	public int insertActReModel(ActReModel actReModel);
	
	/**
     * 修改流程model
     * 
     * @param actReModel 流程model信息
     * @return 结果
     */
	public int updateActReModel(ActReModel actReModel);
		
	/**
     * 删除流程model信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteActReModelByIds(String ids);
	
}

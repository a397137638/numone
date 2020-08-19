package com.numberone.activiti.service;

import com.numberone.activiti.domain.NodeRelation;

import java.util.List;

/**
 * 流程配置 服务层
 * 
 * @author numberone
 * @date 2020-06-23
 */
public interface INodeRelationService 
{
	/**
     * 查询流程配置信息
     * 
     * @param id 流程配置ID
     * @return 流程配置信息
     */
	public NodeRelation selectNodeRelationById(Integer id);
	
	/**
     * 查询流程配置列表
     * 
     * @param nodeRelation 流程配置信息
     * @return 流程配置集合
     */
	public List<NodeRelation> selectNodeRelationList(NodeRelation nodeRelation);
	
	/**
     * 新增流程配置
     * 
     * @param nodeRelation 流程配置信息
     * @return 结果
     */
	public int insertNodeRelation(NodeRelation nodeRelation);
	
	/**
     * 修改流程配置
     * 
     * @param nodeRelation 流程配置信息
     * @return 结果
     */
	public int updateNodeRelation(NodeRelation nodeRelation);
		
	/**
     * 删除流程配置信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteNodeRelationByIds(String ids);

	public List<String> getParentIdByDistinct(String modelId);
	
}

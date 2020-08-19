package com.numberone.activiti.mapper;

import com.numberone.activiti.domain.NodeRelation;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 流程配置 数据层
 * 
 * @author numberone
 * @date 2020-06-23
 */
@Component
public interface NodeRelationMapper 
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
     * 删除流程配置
     * 
     * @param id 流程配置ID
     * @return 结果
     */
	public int deleteNodeRelationById(Integer id);
	
	/**
     * 批量删除流程配置
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteNodeRelationByIds(String[] ids);

	public List<String> getParentIdByDistinct(String modelId);
	
}
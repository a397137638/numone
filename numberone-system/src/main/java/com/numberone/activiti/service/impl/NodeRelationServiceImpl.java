package com.numberone.activiti.service.impl;

import java.util.List;

import com.numberone.activiti.domain.NodeRelation;
import com.numberone.activiti.mapper.NodeRelationMapper;
import com.numberone.activiti.service.INodeRelationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.numberone.common.support.Convert;

/**
 * 流程配置 服务层实现
 * 
 * @author numberone
 * @date 2020-06-23
 */
@Service
public class NodeRelationServiceImpl implements INodeRelationService
{
	@Autowired
	private NodeRelationMapper nodeRelationMapper;

	/**
     * 查询流程配置信息
     * 
     * @param id 流程配置ID
     * @return 流程配置信息
     */
    @Override
	public NodeRelation selectNodeRelationById(Integer id)
	{
	    return nodeRelationMapper.selectNodeRelationById(id);
	}
	
	/**
     * 查询流程配置列表
     * 
     * @param nodeRelation 流程配置信息
     * @return 流程配置集合
     */
	@Override
	public List<NodeRelation> selectNodeRelationList(NodeRelation nodeRelation)
	{
	    return nodeRelationMapper.selectNodeRelationList(nodeRelation);
	}
	
    /**
     * 新增流程配置
     * 
     * @param nodeRelation 流程配置信息
     * @return 结果
     */
	@Override
	public int insertNodeRelation(NodeRelation nodeRelation)
	{
	    return nodeRelationMapper.insertNodeRelation(nodeRelation);
	}
	
	/**
     * 修改流程配置
     * 
     * @param nodeRelation 流程配置信息
     * @return 结果
     */
	@Override
	public int updateNodeRelation(NodeRelation nodeRelation)
	{
	    return nodeRelationMapper.updateNodeRelation(nodeRelation);
	}

	/**
     * 删除流程配置对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteNodeRelationByIds(String ids)
	{
		return nodeRelationMapper.deleteNodeRelationByIds(Convert.toStrArray(ids));
	}

	@Override
	public List<String> getParentIdByDistinct(String modelId) {
		return nodeRelationMapper.getParentIdByDistinct(modelId);
	}

}

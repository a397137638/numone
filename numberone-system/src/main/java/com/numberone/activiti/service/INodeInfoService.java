package com.numberone.activiti.service;

import com.numberone.activiti.domain.NodeInfo;

import java.util.List;

/**
 * 流程节点 服务层
 * 
 * @author numberone
 * @date 2020-06-23
 */
public interface INodeInfoService 
{
	/**
     * 查询流程节点信息
     * 
     * @param id 流程节点ID
     * @return 流程节点信息
     */
	public NodeInfo selectNodeInfoById(Integer id);
	
	/**
     * 查询流程节点列表
     * 
     * @param nodeInfo 流程节点信息
     * @return 流程节点集合
     */
	public List<NodeInfo> selectNodeInfoList(NodeInfo nodeInfo);
	
	/**
     * 新增流程节点
     * 
     * @param nodeInfo 流程节点信息
     * @return 结果
     */
	public int insertNodeInfo(NodeInfo nodeInfo);
	
	/**
     * 修改流程节点
     * 
     * @param nodeInfo 流程节点信息
     * @return 结果
     */
	public int updateNodeInfo(NodeInfo nodeInfo);
		
	/**
     * 删除流程节点信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteNodeInfoByIds(String ids);
	
}

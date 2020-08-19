package com.numberone.activiti.service.impl;

import java.util.List;

import com.numberone.activiti.domain.NodeInfo;
import com.numberone.activiti.mapper.NodeInfoMapper;
import com.numberone.activiti.service.INodeInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.numberone.common.support.Convert;

/**
 * 流程节点 服务层实现
 * 
 * @author numberone
 * @date 2020-06-23
 */
@Service
public class NodeInfoServiceImpl implements INodeInfoService
{
	@Autowired
	private NodeInfoMapper nodeInfoMapper;

	/**
     * 查询流程节点信息
     * 
     * @param id 流程节点ID
     * @return 流程节点信息
     */
    @Override
	public NodeInfo selectNodeInfoById(Integer id)
	{
	    return nodeInfoMapper.selectNodeInfoById(id);
	}
	
	/**
     * 查询流程节点列表
     * 
     * @param nodeInfo 流程节点信息
     * @return 流程节点集合
     */
	@Override
	public List<NodeInfo> selectNodeInfoList(NodeInfo nodeInfo)
	{
	    return nodeInfoMapper.selectNodeInfoList(nodeInfo);
	}
	
    /**
     * 新增流程节点
     * 
     * @param nodeInfo 流程节点信息
     * @return 结果
     */
	@Override
	public int insertNodeInfo(NodeInfo nodeInfo)
	{
	    return nodeInfoMapper.insertNodeInfo(nodeInfo);
	}
	
	/**
     * 修改流程节点
     * 
     * @param nodeInfo 流程节点信息
     * @return 结果
     */
	@Override
	public int updateNodeInfo(NodeInfo nodeInfo)
	{
	    return nodeInfoMapper.updateNodeInfo(nodeInfo);
	}

	/**
     * 删除流程节点对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteNodeInfoByIds(String ids)
	{
		return nodeInfoMapper.deleteNodeInfoByIds(Convert.toStrArray(ids));
	}
	
}

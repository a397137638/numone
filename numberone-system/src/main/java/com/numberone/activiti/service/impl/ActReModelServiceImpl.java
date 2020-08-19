package com.numberone.activiti.service.impl;

import java.util.List;

import com.numberone.activiti.domain.ActReModel;
import com.numberone.activiti.mapper.ActReModelMapper;
import com.numberone.activiti.service.IActReModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.numberone.common.support.Convert;

/**
 * 流程model 服务层实现
 * 
 * @author numberone
 * @date 2020-06-17
 */
@Service
public class ActReModelServiceImpl implements IActReModelService
{
	@Autowired
	private ActReModelMapper actReModelMapper;

	/**
     * 查询流程model信息
     * 
     * @param id 流程modelID
     * @return 流程model信息
     */
    @Override
	public ActReModel selectActReModelById(String id)
	{
	    return actReModelMapper.selectActReModelById(id);
	}
	
	/**
     * 查询流程model列表
     * 
     * @param actReModel 流程model信息
     * @return 流程model集合
     */
	@Override
	public List<ActReModel> selectActReModelList(ActReModel actReModel)
	{
	    return actReModelMapper.selectActReModelList(actReModel);
	}
	
    /**
     * 新增流程model
     * 
     * @param actReModel 流程model信息
     * @return 结果
     */
	@Override
	public int insertActReModel(ActReModel actReModel)
	{
	    return actReModelMapper.insertActReModel(actReModel);
	}
	
	/**
     * 修改流程model
     * 
     * @param actReModel 流程model信息
     * @return 结果
     */
	@Override
	public int updateActReModel(ActReModel actReModel)
	{
	    return actReModelMapper.updateActReModel(actReModel);
	}

	/**
     * 删除流程model对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteActReModelByIds(String ids)
	{
		return actReModelMapper.deleteActReModelByIds(Convert.toStrArray(ids));
	}
	
}

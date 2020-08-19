package com.numberone.activiti.domain;


import com.numberone.common.base.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 流程配置表 node_relation
 * 
 * @author numberone
 * @date 2020-06-23
 */
public class NodeRelation extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/**  */
	private String id;
	/**  */
	private String parentId;
	/**  */
	private String terms;
	/**  */
	private String modelId;

	private String nodeName;

	private String taskType;

	private String nodeId;

	private String userType;

	private String userIds;

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public String getUserIds() {
		return userIds;
	}

	public void setUserIds(String userIds) {
		this.userIds = userIds;
	}

	public String getNodeId() {
		return nodeId;
	}

	public void setNodeId(String nodeId) {
		this.nodeId = nodeId;
	}

	public String getTaskType() {
		return taskType;
	}

	public void setTaskType(String taskType) {
		this.taskType = taskType;
	}

	public String getNodeName() {
		return nodeName;
	}

	public void setNodeName(String nodeName) {
		this.nodeName = nodeName;
	}

	public void setId(String id)
	{
		this.id = id;
	}

	public String getId()
	{
		return id;
	}
	public void setParentId(String parentId)
	{
		this.parentId = parentId;
	}

	public String getParentId()
	{
		return parentId;
	}
	public void setTerms(String terms) 
	{
		this.terms = terms;
	}

	public String getTerms() 
	{
		return terms;
	}
	public void setModelId(String modelId) 
	{
		this.modelId = modelId;
	}

	public String getModelId() 
	{
		return modelId;
	}

	@Override
	public String toString() {
		return "NodeRelation{" +
				"id='" + id + '\'' +
				", parentId='" + parentId + '\'' +
				", terms='" + terms + '\'' +
				", modelId='" + modelId + '\'' +
				", nodeName='" + nodeName + '\'' +
				", taskType='" + taskType + '\'' +
				", nodeId='" + nodeId + '\'' +
				", userType='" + userType + '\'' +
				", userIds='" + userIds + '\'' +
				'}';
	}
}

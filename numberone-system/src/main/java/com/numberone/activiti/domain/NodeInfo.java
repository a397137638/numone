package com.numberone.activiti.domain;


import com.numberone.common.base.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 流程节点表 node_info
 * 
 * @author numberone
 * @date 2020-06-23
 */
public class NodeInfo extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/**  */
	private Integer id;
	/**  */
	private String nodeName;

	public void setId(Integer id) 
	{
		this.id = id;
	}

	public Integer getId() 
	{
		return id;
	}
	public void setNodeName(String nodeName) 
	{
		this.nodeName = nodeName;
	}

	public String getNodeName() 
	{
		return nodeName;
	}

    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("nodeName", getNodeName())
            .toString();
    }
}

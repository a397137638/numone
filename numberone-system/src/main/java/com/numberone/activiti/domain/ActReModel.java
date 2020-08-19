package com.numberone.activiti.domain;


import com.numberone.common.base.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;

/**
 * 流程model表 act_re_model
 * 
 * @author numberone
 * @date 2020-06-17
 */
public class ActReModel extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/**  */
	private String id;
	/**  */
	private Integer rev;
	/**  */
	private String name;
	/**  */
	private String key;
	/**  */
	private String category;
	/**  */
	private Date createTime;
	/**  */
	private Date lastUpdateTime;
	/**  */
	private Integer version;
	/**  */
	private String metaInfo;
	/**  */
	private String deploymentId;
	/**  */
	private String editorSourceValueId;
	/**  */
	private String editorSourceExtraValueId;
	/**  */
	private String tenantId;

	public void setId(String id) 
	{
		this.id = id;
	}

	public String getId() 
	{
		return id;
	}
	public void setRev(Integer rev) 
	{
		this.rev = rev;
	}

	public Integer getRev() 
	{
		return rev;
	}
	public void setName(String name) 
	{
		this.name = name;
	}

	public String getName() 
	{
		return name;
	}
	public void setKey(String key) 
	{
		this.key = key;
	}

	public String getKey() 
	{
		return key;
	}
	public void setCategory(String category) 
	{
		this.category = category;
	}

	public String getCategory() 
	{
		return category;
	}
	public void setCreateTime(Date createTime) 
	{
		this.createTime = createTime;
	}

	public Date getCreateTime() 
	{
		return createTime;
	}
	public void setLastUpdateTime(Date lastUpdateTime) 
	{
		this.lastUpdateTime = lastUpdateTime;
	}

	public Date getLastUpdateTime() 
	{
		return lastUpdateTime;
	}
	public void setVersion(Integer version) 
	{
		this.version = version;
	}

	public Integer getVersion() 
	{
		return version;
	}
	public void setMetaInfo(String metaInfo) 
	{
		this.metaInfo = metaInfo;
	}

	public String getMetaInfo() 
	{
		return metaInfo;
	}
	public void setDeploymentId(String deploymentId) 
	{
		this.deploymentId = deploymentId;
	}

	public String getDeploymentId() 
	{
		return deploymentId;
	}
	public void setEditorSourceValueId(String editorSourceValueId) 
	{
		this.editorSourceValueId = editorSourceValueId;
	}

	public String getEditorSourceValueId() 
	{
		return editorSourceValueId;
	}
	public void setEditorSourceExtraValueId(String editorSourceExtraValueId) 
	{
		this.editorSourceExtraValueId = editorSourceExtraValueId;
	}

	public String getEditorSourceExtraValueId() 
	{
		return editorSourceExtraValueId;
	}
	public void setTenantId(String tenantId) 
	{
		this.tenantId = tenantId;
	}

	public String getTenantId() 
	{
		return tenantId;
	}

    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("rev", getRev())
            .append("name", getName())
            .append("key", getKey())
            .append("category", getCategory())
            .append("createTime", getCreateTime())
            .append("lastUpdateTime", getLastUpdateTime())
            .append("version", getVersion())
            .append("metaInfo", getMetaInfo())
            .append("deploymentId", getDeploymentId())
            .append("editorSourceValueId", getEditorSourceValueId())
            .append("editorSourceExtraValueId", getEditorSourceExtraValueId())
            .append("tenantId", getTenantId())
            .toString();
    }
}

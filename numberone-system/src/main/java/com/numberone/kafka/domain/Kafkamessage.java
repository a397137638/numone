package com.numberone.kafka.domain;


import com.numberone.common.base.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * kafka消息表 kafkamessage
 * 
 * @author numberone
 * @date 2020-07-30
 */
public class Kafkamessage extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/**  */
	private Integer messageId;
	/**  */
	private String message;

	public void setMessageId(Integer messageId) 
	{
		this.messageId = messageId;
	}

	public Integer getMessageId() 
	{
		return messageId;
	}
	public void setMessage(String message) 
	{
		this.message = message;
	}

	public String getMessage() 
	{
		return message;
	}

    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
            .append("messageId", getMessageId())
            .append("message", getMessage())
            .toString();
    }
}

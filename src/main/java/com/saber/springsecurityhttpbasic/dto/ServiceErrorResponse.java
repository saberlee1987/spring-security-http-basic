package com.saber.springsecurityhttpbasic.dto;

import com.fasterxml.jackson.annotation.JsonRawValue;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

@Data
public class ServiceErrorResponse {
	private Integer code;
	private String message;
	@JsonRawValue
	private String originalMessage;
	
	@Override
	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.JSON_STYLE)
				.append("code",code)
				.append("message",message)
				.append("originalMessage",originalMessage)
				.toString();
				
	}
}

package com.poly.common;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class QueryParameter {
	private String key;
	private Object value;
	private TemporalType temporalType;
	private int position;
	public QueryParameter(String key, Object value) {
		this.key=key;
		this.value=value;
	}
	
	public QueryParameter(int position, Object value) {
		this.position = position;
		this.value=value;
	}

	public QueryParameter(String key, Object value, TemporalType temporalType) {
		this.key = key;
		this.value = value;
		this.temporalType = temporalType;
	}
}
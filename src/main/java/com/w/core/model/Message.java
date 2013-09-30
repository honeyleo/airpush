package com.w.core.model;

import java.util.HashMap;
import java.util.Map;

public class Message extends HashMap<String,Object> {

	private static final long serialVersionUID = -8476898437116996416L;

	private Message(Builder builder) {
		this.putAll(builder.data);
	}
	public static class Builder {
		
		private Map<String,Object> data = new HashMap<String, Object>();
		
		public Builder() {
		}
		public static Builder newBuilder() {
			return new Builder();
		}
		public Builder put(String key, Object value) {
			data.put(key, value);
			return this;
		}
		public Message build() {
			return new Message(this);
		}
	}
	
}

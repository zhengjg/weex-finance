package com.zjg.enjoy.weexfinance.data.bean;



import com.zjg.enjoy.weexfinance.common.util.JsonUtils;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Created by cloud on 7/24/15.
 */
public class PABaseBean implements Serializable {
	public Map<String, List<String>> session;


	@Override
	public String toString() {
		return JsonUtils.toJSONString(this);
	}
}

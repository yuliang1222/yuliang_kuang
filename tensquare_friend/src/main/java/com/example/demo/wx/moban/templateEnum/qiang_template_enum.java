package com.example.demo.wx.moban.templateEnum;

/**
 * @Author: yuliang
 * @Date: 2020/3/26 0026 11:01
 */

public enum qiang_template_enum {

	HKTZ("1", "{\"touser\":\"OPENID\",\"template_id\":\"gxEXs6QpBiZrzKJVNiMhdl9NuNakMShH5W73btPYiao\",\"url\":\"https://www.pgyer.com/HPv2\",\"topcolor\":\"#FF0000\",\"data\":{\"first\":{\"value\":\"\",\"color\":\"#173177\"},\"keyword1\":{\"value\":\"\",\"color\":\"#173177\"},\"keyword2\":{\"value\":\"\",\"color\":\"#173177\"},\"keyword3\":{\"value\":\"\",\"color\":\"#173177\"},\"keyword4\":{\"value\":\"\",\"color\":\"#173177\"},\"keyword5\":{\"value\":\"\",\"color\":\"#0055ff\"},\"remark\":{\"value\":\"\",\"color\":\"#d63500\"}}}"),
	WEF("2", "{\"touser\":\"OPENID\",\"template_id\":\"5_NRUlsMoxlk8kFNTkuZhZDxSY-cELyrQl91SresO2o\",\"url\":\"https://www.pgyer.com/HPv2\",\"topcolor\":\"#FF0000\",\"data\":{\"first\":{\"value\":\"\",\"color\":\"#173177\"},\"keyword1\":{\"value\":\"\",\"color\":\"#173177\"},\"keyword2\":{\"value\":\"\",\"color\":\"#173177\"},\"keyword3\":{\"value\":\"\",\"color\":\"#173177\"},\"keyword4\":{\"value\":\"\",\"color\":\"#173177\"},\"keyword5\":{\"value\":\"\",\"color\":\"#0055ff\"},\"remark\":{\"value\":\"\",\"color\":\"#d63500\"}}}"),
	;
	private String key;
	private String value;

	qiang_template_enum(String key, String value) {
		this.key = key;
		this.value = value;
	}

	public String key(){
		return this.key;
	}



	public  static String getValue(String key){
		for (qiang_template_enum liang_template_enum : qiang_template_enum.values()) {
			if (liang_template_enum.key.equals(key)) {
				return liang_template_enum.value;
			}
		}
		return null;
	}
	public String value(){
		return this.value;
	}
}

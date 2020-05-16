package com.example.demo.wx.moban.utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.sf.json.JSONArray;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.util.List;
import java.util.Map;


public class JsonUtils {

	/**
	 *jsonArray转String
	 */
    public static List JsonArrayToString(JSONArray jsonArray, Class tClass) {
        List departmentList = (List) JSONArray.toCollection(jsonArray, tClass);
        return departmentList;
    }

	/**
	 *	json转任何格式
	 */
    public static final ObjectMapper mapper = new ObjectMapper();
    private static final Logger logger = LoggerFactory.getLogger(JsonUtils.class);
    public static <T> T nativeRead(String json, TypeReference<T> type) {
        try {
            return mapper.readValue(json, type);
        } catch (IOException e) {
            logger.error("json解析出错：" + json, e);
            return null;
        }
    }

	/**
	 *Json格式文件转List<Map<String, String>>
	 */
	public static List<Map<String, String>> xy() throws IOException {
		ClassPathResource resource = new ClassPathResource("response/nodes.json");
	    String content = org.apache.commons.lang.StringUtils.join(IOUtils.readLines(resource.getInputStream(), "UTF-8"), "\n");
	List<Map<String, String>> mapList = nativeRead(content, new TypeReference<List<Map<String, String>>>() {});
		return mapList;
	}

	/**
	 *Json格式文件转任何格式
	 */
	public static <T> T jshuju(String addr, TypeReference<T> type) throws IOException {
		ClassPathResource resource = new ClassPathResource(addr);
		String content = org.apache.commons.lang.StringUtils.join(IOUtils.readLines(resource.getInputStream(), "UTF-8"), "\n");
		return nativeRead(content, type);
	}



}

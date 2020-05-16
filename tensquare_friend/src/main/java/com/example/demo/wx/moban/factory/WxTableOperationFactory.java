/**
 * ClassName:wxTableOperation
 * Author:Administrator
 * Date:2020/3/27 00270:09
 * Description:TODO
 */
package com.example.demo.wx.moban.factory;

import com.example.demo.utils.Result;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @Author: yuliang
 * @Date: 2020/3/27 0027 0:09
 */
@Component
public class WxTableOperationFactory {

	//1.保存发送成功的消息
	public Result<Object> SaveSentSuccessMsg(String source) {
		return Result.success();
	}
	//2.保存发送失败的消息
	public Result<Object> SaveSentFailMsg(String source) {
		return Result.success();
	}
	//3. 查询错误表里面的信息(重试count<3)
	public List<HashMap<String, Object>> SelectFailMsgList() {
		return new ArrayList();
	}
	//4. 更新失败表失败次数
	public List<HashMap<String, Object>> updateFailMsgCount(HashMap<String, Object> temp) {
		return new ArrayList();
	}
	//5. 更新失败表状态
	public List<HashMap<String, Object>> updateFailMsgStatus(HashMap<String, Object> temp) {
		return new ArrayList();
	}
}

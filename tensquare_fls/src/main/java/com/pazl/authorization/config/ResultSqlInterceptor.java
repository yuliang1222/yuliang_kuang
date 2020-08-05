/**
 * ClassName:InterceptorForQry
 * Author:Administrator
 * Date:2020/5/24 002419:23
 * Description:TODO
 */
package com.pazl.authorization.config;

import com.alibaba.fastjson.JSON;
import lombok.NoArgsConstructor;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;

import java.util.Properties;




/**
 * 打印结果拦截器 〈功能详细描述〉
 *
 * @author gogym
 * @version 2019年4月2日
 * @see
 * @since
 */
@NoArgsConstructor
@Intercepts({@Signature(type = Executor.class, method = "query", args = {MappedStatement.class,
		Object.class, RowBounds.class, ResultHandler.class})})
public class ResultSqlInterceptor implements Interceptor
{

	@Override
	@SuppressWarnings({"rawtypes", "unchecked"})
	public Object intercept(Invocation invocation)
			throws Throwable
	{
		Object result = invocation.proceed(); // 执行请求方法，并将所得结果保存到result中
		String str = JSON.toJSONString(result);
		System.out.println(str);
		return result;
	}
	@Override
	public Object plugin(Object target)
	{
		return Plugin.wrap(target, this);
	}
	@Override
	public void setProperties(Properties arg0)
	{}
}
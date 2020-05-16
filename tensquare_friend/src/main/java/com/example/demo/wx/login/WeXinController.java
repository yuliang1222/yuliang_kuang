package com.example.demo.wx.login;///**
// * ClassName:WeXinController
// * Author:Administrator
// * Date:2020/3/23 002310:49
// * Description:TODO
// */
//package com.example.demo.wx.login;
//
//import com.alibaba.fastjson.JSONException;
//import com.alibaba.fastjson.JSONObject;
//import org.apache.http.HttpEntity;
//import org.apache.http.HttpResponse;
//import org.apache.http.client.ClientProtocolException;
//import org.apache.http.client.methods.HttpGet;
//import org.apache.http.impl.client.DefaultHttpClient;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.ResponseBody;
//
//import javax.annotation.Resource;
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.math.BigDecimal;
//import java.net.URI;
//import java.util.HashMap;
//
///**
// * @Author: yuliang
// * @Date: 2020/3/23 0023 10:49
// */
//@SuppressWarnings("deprecation")
//@Controller
//@RequestMapping("/api")
//public class WeXinController {
//
//
//	//微信公众平台申请
//	//应用唯一标识，在微信开放平台提交应用审核通过后获得 appID
//	//应用密钥AppSecret，在微信开放平台提交应用审核通过后获得 appSecret
//	//TpAccesstoken 用来保存微信返回的用户信息oppid等
//
//
//	@Resource
//	private WeixinLoginProperties weixinLoginProperties;
//	@Autowired
//	TpUsersService tpUsersService;
//	@Autowired
//	TpUsersMapper tpUsersMapper;
//	@Autowired
//	TpAccesstokenService tpAccesstokenService;
//	@Autowired
//	TpAccesstokenMapper tpAccesstokenMapper;
//	@Autowired
//	TpAccumulativeAwardService tpAccumulativeAwardService;
//
//	/**
//	 * 获取accessToken,该步骤返回的accessToken期限为一个月
//	 *
//	 * @param code
//	 * @return
//	 * @throws Exception
//	 */
//	@SuppressWarnings("all")
//	@RequestMapping("weixincallback")
//	@ResponseBody
//	public R getAccessToken(String code) throws Exception {
//
//		String appID = weixinLoginProperties.getWeixinappID();
//		String appSecret = weixinLoginProperties.getWeixinappSecret();
//		String accesstoken;
//		String openid = null;
//		String refreshtoken;
//		int expiresIn;
//		String unionid;//可通过获取用户基本信息中的unionid来区分用户的唯一性，因为只要是同一个微信开放平台帐号下的移动应用、网站应用和公众帐号，
//		//用户的unionid是唯一的。换句话说，同一用户，对同一个微信开放平台下的不同应用，unionid是相同的。
//		if (code != null) {
//			System.out.println(code);
//		}
//		String url = "https://api.weixin.qq.com/sns/oauth2/access_token?appid="+appID+"&secret="+appSecret+"&code="+code+"&grant_type=authorization_code";
//		URI uri = URI.create(url);
//		org.apache.http.client.HttpClient client = new DefaultHttpClient();
//		HttpGet get = new HttpGet(uri);
//		HttpResponse response;
//		try {
//			response = client.execute(get);
//			if (response.getStatusLine().getStatusCode() == 200) {
//				HttpEntity entity = response.getEntity();
//
//				BufferedReader reader = new BufferedReader(new InputStreamReader(entity.getContent(), "UTF-8"));
//				StringBuilder sb = new StringBuilder();
//
//				for (String temp = reader.readLine(); temp != null; temp = reader.readLine()) {
//					sb.append(temp);
//				}
//				JSONObject object = new JSONObject(sb.toString().trim());
//				System.out.println("object:"+object);
//				accesstoken = object.getString("access_token");
//				System.out.println("accesstoken:"+accesstoken);
//				openid = object.getString("openid");
//				System.out.println("openid:"+openid);
//				refreshtoken = object.getString("refresh_token");
//				System.out.println("refreshtoken:"+refreshtoken);
//				expiresIn = (int) object.getLong("expires_in");
//				unionid = object.getString("unionid");
//				// 将用户信息保存到数据库
//				//1.先查询用户是否是第一次第三方登录如果是第一次那么是将用户信息添加到数据库 如果不是那么是更新到数据库
//				TpUsers userInfo = getUserInfo(accesstoken,openid);
//				Integer userId = userInfo.getUserId();
//				HashMap
//				TpAccesstokenExample example = new TpAccesstokenExample();
//				example.createCriteria().andOpenidEqualTo(openid);
//				List<TpAccesstoken> list = tpAccesstokenMapper.selectByExample(example);
//				if(list!=null&&list.size()>0) {
//					//那么该用户不是第一次 执行更新操作
//					TpAccesstoken tpAccesstoken = list.get(0);
//					tpAccesstoken.setAccesstoken(accesstoken);
//					tpAccesstoken.setUserId(userId);
//					tpAccesstoken.setExpiresIn(expiresIn);
//					tpAccesstoken.setOpenid(openid);
//					tpAccesstoken.setRefreshtoken(refreshtoken);
//					tpAccesstokenService.save(tpAccesstoken);
//				}else {
//					TpAccesstoken tpAccesstoken=new TpAccesstoken();
//					tpAccesstoken.setUserId(userId);
//					tpAccesstoken.setAccesstoken(accesstoken);
//					tpAccesstoken.setExpiresIn(expiresIn);
//					tpAccesstoken.setOpenid(openid);
//					tpAccesstoken.setRefreshtoken(refreshtoken);
//					tpAccesstokenService.save(tpAccesstoken);
//					//tpAccesstokenService.insertAccesstoken(userId,openid, accesstoken, expiresIn, refreshtoken);
//				}
//				//refreshAccessToken(openid);
//				System.out.println("Openid"+userInfo.getOpenid());
//				return R.ok().put("userInfo", userInfo).put("openid", openid);
//			}
//		} catch (ClientProtocolException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		} catch (IllegalStateException e) {
//			e.printStackTrace();
//		} catch (JSONException e) {
//			e.printStackTrace();
//		}
//
//		return R.ok().put("openid", openid);
//	}
//	/*
//	 *
//	 * 1 { 2 "access_token":"ACCESS_TOKEN", 3 "expires_in":7200, 4
//	 * "refresh_token":"REFRESH_TOKEN", 5 "openid":"OPENID", 6 "scope":"SCOPE", 7
//	 * "unionid":"o6_bmasdasdsad6_2sgVt7hMZOPfL" 8 } 复制代码 复制代码 参数 说明 access_token
//	 * 接口调用凭证 expires_in access_token 接口调用凭证超时时间，单位（秒） refresh_token
//	 * 用户刷新access_token openid 授权用户唯一标识 scope 用户授权的作用域，使用逗号（,）分隔 unionid
//	 * 只有在用户将公众号绑定到微信开放平台帐号后，才会出现该字段。
//	 *
//	 */
//
//
//	/**
//	 * 刷新token
//	 *
//	 * @param openID
//	 * @return
//	 */
//	@SuppressWarnings({ "unused", "resource" })
//	private void refreshAccessToken(String openid) {
//		String refreshtoken=null;
//		TpAccesstoken tpAccesstoken=new TpAccesstoken();
//		String appID = weixinLoginProperties.getWeixinappID();
//		String appSecret = weixinLoginProperties.getWeixinappSecret();
//		TpAccesstokenExample example = new TpAccesstokenExample();
//		example.createCriteria().andOpenidEqualTo(openid);
//		List<TpAccesstoken> list = tpAccesstokenMapper.selectByExample(example);
//		if(list!=null&&list.size()>0) {
//			tpAccesstoken = list.get(0);
//			refreshtoken = tpAccesstoken.getRefreshtoken();
//		}
//
//		String uri = "https://api.weixin.qq.com/sns/oauth2/refresh_token?appid="+appID+"&grant_type=refresh_token&refresh_token="+refreshtoken;
//		org.apache.http.client.HttpClient client = new DefaultHttpClient();
//		HttpGet get = new HttpGet(URI.create(uri));
//		try {
//			HttpResponse response = client.execute(get);
//			if (response.getStatusLine().getStatusCode() == 200) {
//				BufferedReader reader = new BufferedReader(
//						new InputStreamReader(response.getEntity().getContent(), "UTF-8"));
//				StringBuilder builder = new StringBuilder();
//				for (String temp = reader.readLine(); temp != null; temp = reader.readLine()) {
//					builder.append(temp);
//				}
//				JSONObject object = new JSONObject(builder.toString().trim());
//				String	accessToken = object.getString("access_token");
//				String    refreshToken = object.getString("refresh_token");
//				openid = object.getString("openid");
//				int   expires_in = (int) object.getLong("expires_in");
//				tpAccesstoken.setAccesstoken(accessToken);
//				tpAccesstoken.setExpiresIn(expires_in);
//				tpAccesstoken.setOpenid(openid);
//				tpAccesstoken.setRefreshtoken(refreshToken);
//				tpAccesstokenService.save(tpAccesstoken);
//			}
//		} catch (ClientProtocolException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (JSONException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//
//	}
//
//	/**
//	 * 根据accessToken获取用户信息
//	 *
//	 * @param accessToken
//	 * @param openID
//	 * @return
//	 * @throws Exception
//	 */
//	@SuppressWarnings({ "unused", "resource" })
//	public TpUsers getUserInfo(String accessToken, String openID) throws Exception {
//		String appID = weixinLoginProperties.getWeixinappID();
//		String appSecret = weixinLoginProperties.getWeixinappSecret();
//		String uri = "https://api.weixin.qq.com/sns/userinfo?access_token=" + accessToken + "&openid=" + openID;
//		org.apache.http.client.HttpClient client = new DefaultHttpClient();
//		HttpGet get = new HttpGet(URI.create(uri));
//		try {
//			HttpResponse response = client.execute(get);
//			if (response.getStatusLine().getStatusCode() == 200) {
//				BufferedReader reader = new BufferedReader(
//						new InputStreamReader(response.getEntity().getContent(), "UTF-8"));
//				StringBuilder builder = new StringBuilder();
//				for (String temp = reader.readLine(); temp != null; temp = reader.readLine()) {
//					builder.append(temp);
//				}
//				JSONObject object = new JSONObject(builder.toString().trim());
//
//				String country = object.getString("country");
//				String nikeName = object.getString("nickname");
//				String unionid = object.getString("unionid");
//				String province = object.getString("province");
//				String city = object.getString("city");
//				String openid = object.getString("openid");
//				String sex = object.getString("sex");
//				String headimgurl = object.getString("headimgurl");
//				String language = object.getString("language");
//				BigDecimal bigDecimal=new BigDecimal(0.0);
//				TpUsersExample example=new TpUsersExample();
//				example.createCriteria().andOpenidEqualTo(openid);
//				List<TpUsers> list = tpUsersMapper.selectByExample(example);
//				if(list!=null&&list.size()>0) {
//					TpUsers tpUsers = list.get(0);
//					System.out.println("---------");
//
//					return tpUsers;
//
//				}else {
//					TpUsers tpUsers=new TpUsers();
//					tpUsers.setOauth("wx");
//					tpUsers.setOpenid(openid);
//					tpUsers.setUnionid(unionid);
//					tpUsers.setUserName(nikeName);
//					tpUsers.setUserMoney(bigDecimal);
//					tpUsersService.save(tpUsers);
//					System.out.println("+++++++");
//					return tpUsers;
//				}
//			}
//		} catch (ClientProtocolException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		} catch (JSONException e) {
//			e.printStackTrace();
//		}
//
//		return null;
//	}
//
//	@RequestMapping("/isaccesstoken")
//	@SuppressWarnings({ "resource" })
//	private boolean isAccessTokenIsInvalid(String accessToken,String openID) {
//		String url = "https://api.weixin.qq.com/sns/auth?access_token=" + accessToken + "&openid=" + openID;
//		URI uri = URI.create(url);
//		org.apache.http.client.HttpClient client = new DefaultHttpClient();
//		HttpGet get = new HttpGet(uri);
//		HttpResponse response;
//		try {
//			response = client.execute(get);
//			if (response.getStatusLine().getStatusCode() == 200) {
//				HttpEntity entity = response.getEntity();
//
//				BufferedReader reader = new BufferedReader(new InputStreamReader(entity.getContent(), "UTF-8"));
//				StringBuilder sb = new StringBuilder();
//
//				for (String temp = reader.readLine(); temp != null; temp = reader.readLine()) {
//					sb.append(temp);
//				}
//				JSONObject object = new JSONObject(sb.toString().trim());
//             	 /* {
//                	"errcode":0,"errmsg":"ok"
//                	}
//                	错误的Json返回示例:
//                	{
//                	"errcode":40003,"errmsg":"invalid openid"
//                	}*/
//				int errorCode = object.getInt("errcode");
//				if (errorCode == 0) {
//					return true;
//				}
//			}
//		} catch (ClientProtocolException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		} catch (JSONException e) {
//			e.printStackTrace();
//		}
//		return false;
//
//	}
//

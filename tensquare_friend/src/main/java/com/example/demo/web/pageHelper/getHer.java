/**
 * ClassName:getHer
 * Author:Administrator
 * Date:2019/12/27 002714:10
 * Description:TODO
 */
package com.example.demo.web.pageHelper;
import java.io.*;

/**
 * @Author: yuliang
 * @Date: 2019/12/27 0027 14:10
 */
public class getHer {
	/**
	 * 获得指定文件的byte数组
	 */
	public static byte[] getBytes(InputStream fis){
		byte[] buffer = null;
		byte[] b = new byte[4];
		try {


//			ByteArrayOutputStream bos = new ByteArrayOutputStream(1000);

//			int n;
//			while ((n = fis.read(b)) != -1) {
//				bos.write(b, 0, n);
//			}
			fis.read(b, 0, b.length);
//			fis.close();
//			bos.close();
//			buffer = bos.toByteArray();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return b;
	}

	/**
	 * * 将要读取文件头信息的文件的byte数组转换成string类型表示 * * @param src * 要读取文件头信息的文件的byte数组
	 * * @return 文件头信息
	 */
	private static String bytesToHexString(byte[] src) {
		StringBuilder builder = new StringBuilder();
		if (src == null || src.length <= 0) {
			return null;
		}
		String hv;
		for (int i = 0; i < src.length; i++) {
			// 以十六进制（基数 16）无符号整数形式返回一个整数参数的字符串表示形式，并转换为大写
			hv = Integer.toHexString(src[i] & 0xFF).toUpperCase();
			if (hv.length() < 2) {
				builder.append(0);
			}
			builder.append(hv);
		}

		return builder.toString();
	}

	/**
	 * 判断文件是否为xls 或者 xlsx,是返回true
	 *
	 * @return boolean
	 *
	 *
	 * */
	public static String fileTypes(byte[] bytes){
		//文件的文件头信息
		byte[] b = new byte[4];

		for (int i=0; i<b.length; i++) {
			b[i] = bytes[i];
		}

		//将文件的文件头字节码转换成字符串
		String filetype = bytesToHexString(b);



		return filetype;
	}

	public static void main(String[] args) throws Exception {
//		//File file = new File("C:\\Users\\Administrator\\Desktop\\aaa\\aa.xlsx");
//		byte[] bytes = FileToBytes.getBytes("C:\\*******\\aaa\\a.xls");
//
//		System.out.println(FileValidateType.fileTypes(bytes));

	}
}

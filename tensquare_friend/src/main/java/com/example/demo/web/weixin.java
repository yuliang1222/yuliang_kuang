/**
 * ClassName:微信
 * Author:机械革命
 * Date:2019/10/1222:00
 * Description:TODO
 */
package com.example.demo.web;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.StopWatch;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.PDFRenderer;
import org.slf4j.IMarkerFactory;
import sun.misc.BASE64Encoder;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static com.example.demo.web.pageHelper.getHer.fileTypes;
import static com.example.demo.web.pageHelper.getHer.getBytes;

/**
 * @Author: yuliang
 * @Date: 2019/10/12 22:00
 */
@Slf4j
public class weixin {
	public static void main(String[] args) throws FileNotFoundException {
//		char rfgsfdsfs = findFirstRepeatedChar("rfgsfdsfs");
////		log.info("main:{}",String.valueOf(rfgsfdsfs));

		pdf2png("D:\\Desktop\\txt", "text", "png");




	}
	public static void pdf2png(String fileAddress,String filename,String type) throws FileNotFoundException {
		// 将pdf装图片 并且自定义图片得格式大小
		File file = new File(fileAddress+"\\"+filename+".pdf");
		InputStream fileInputStream = new FileInputStream(file);
		byte[] bytes = getBytes(fileInputStream);
		String s = fileTypes(bytes);
		System.out.println(s);
		try {
			fileInputStream = new FileInputStream(file);
			PDDocument doc = PDDocument.load(fileInputStream);
			PDFRenderer renderer = new PDFRenderer(doc);


			int pageCount = doc.getNumberOfPages();
			for (int i = 0; i < 2; i++) {
				BufferedImage image = renderer.renderImageWithDPI(i, 800); // Windows native DPI
				// BufferedImage srcImage = resize(image, 240, 240);//产生缩略图
				ImageIO.write(image, type, new File(fileAddress+"\\"+filename+"txt"+"_"+(i+1)+"."+type));
			}
			doc.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


	public static char findFirstRepeatedChar(String string) {
		// 检查空字符串
		if (StringUtils.isBlank(string)) {
			throw new RuntimeException();
		}

		// 查找重复字符
		char[] charArray = string.toCharArray();
		Set charSet = new HashSet<>(charArray.length);
		for (char ch : charArray) {
			if (!charSet.add(ch)) {
				return ch;
			}
		}
//		return null;/
		return 0;
	}


	public static String pdf2png1(String fileAddress, String filename, String type, HttpServletResponse response) {
		// 将pdf装图片 并且自定义图片得格式大小
		File file = new File(fileAddress+"\\"+filename+".pdf");
		String png_base64 = null;
		try {
			PDDocument doc = PDDocument.load(file);
			PDFRenderer renderer = new PDFRenderer(doc);
			int pageCount = doc.getNumberOfPages();
			for (int i = 0; i < 1; i++) {
				BufferedImage image = renderer.renderImageWithDPI(i, 822); // Windows native DPI
				// BufferedImage srcImage = resize(image, 240, 240);//产生缩略图
				ByteArrayOutputStream baos = new ByteArrayOutputStream();//io流
				ImageIO.write(image, type, baos);


				byte[] bytes = baos.toByteArray();//转换成字节
				BASE64Encoder encoder = new BASE64Encoder();
				 png_base64 =  encoder.encodeBuffer(bytes).trim();//转换成base64串
				png_base64 = png_base64.replaceAll("\n", "").replaceAll("\r", "");//删除 \r\n
				return png_base64;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return png_base64;
	}

	public static InputStream ste() {
		try {
			URL url = new URL("https://xact01.baidupcs.com/file/b68ad4948ode6f14b88a5a7dffe73227?bkt=en-713b21d6dbc31398c5285f96956b3dcf541da4279191bf0d7bbaa84bb15d05dee112852dda354f4c66c19f0939f757a9106fd1ec7707188989c9045aa2e4c4a3&fid=1905611309-250528-318020240305469&time=1577669480&sign=FDTAXGERLQBHSKfW-DCb740ccc5511e5e8fedcff06b081203-GFywVhUF9pgYXl6ACvwABEQHlYA%3D&to=125&size=765192&sta_dx=765192&sta_cs=1&sta_ft=pdf&sta_ct=1&sta_mt=1&fm2=MH%2Cdefault_region%2CAnywhere%2C%2Cshanghai%2Cct&ctime=1577443436&mtime=1577443436&resv0=cdnback&resv1=0&resv2=rlim&resv3=5&resv4=765192&vuk=1905611309&iv=0&htype=&randtype=&newver=1&newfm=1&secfm=1&flow_ver=3&pkey=en-b8ed9d4ba2c0bde138d1f5dcb37a0318610fbc91c5b628e1ed43a7a269298e01c1caf0a24aa862bba37c29be965d08fe01ffbbb362abbbe3305a5e1275657320&sl=68616270&expires=8h&rt=sh&r=742084264&vbdid=2903686122&fin=text.pdf&fn=text.pdf&rtype=1&dp-logid=8428196755116705813&dp-callid=0.1&hps=1&tsl=200&csl=200&csign=5hLZ6L3bCjREVmfk3lBWOm%2Brz3Q%3D&so=0&ut=6&uter=4&serv=0&uc=569815613&ti=0887d9faa0e99264ca367b45cafbf9b8bd75e1542052418d305a5e1275657320&reqlabel=250528_f_2d0a8481f1af184e1025c3d0f87dddb1_-1_182e597634daaffbd7f96023c0f28fe9&by=themis");

			InputStream inputStream = url.openStream();
			return inputStream;
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}catch (IOException e) {
			e.printStackTrace();
		}

		return null;
	}
	public static void  pdf2png2(String fileAddress, String filename, String type, HttpServletResponse response) {





		// 将pdf装图片 并且自定义图片得格式大小
		List<BufferedImage> listImage = new ArrayList<>();
		String fileName = fileAddress + "\\" + filename + "txt" + "_" + "." + type;
//		File file = new File(fileAddress+"\\"+filename+".pdf");
		InputStream fileInputStream = ste();
//		try {
//			fileInputStream = new FileInputStream(file);
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//		}
		byte[] bytes = getBytes(fileInputStream);
		String s = fileTypes(bytes);
		System.out.println(s);
		String png_base64 = null;
		PDDocument doc = null;
		try {
//			fileInputStream = new FileInputStream(file);
			StopWatch stopWatch = new StopWatch();
			stopWatch.start();
			fileInputStream =ste();
			stopWatch.stop();
			System.out.println("获取url得到流的时间"+stopWatch.getTime());
			stopWatch.reset();

			stopWatch.start();
			 doc = PDDocument.load(fileInputStream);
			PDFRenderer renderer = new PDFRenderer(doc);

			int pageCount = doc.getNumberOfPages();
			stopWatch.stop();
			System.out.println("pdf转png的时间"+stopWatch.getTime());
			stopWatch.reset();
			for (int i = 0; i < 3; i++) {
				BufferedImage image = renderer.renderImageWithDPI(i, 80); // Windows native DPI
				// BufferedImage srcImage = resize(image, 240, 240);//产生缩略图
//				ByteArrayOutputStream baos = new ByteArrayOutputStream();//io流
				listImage.add(image);
			}


			response.setContentType("text/png;charset=UTF-8");
			response.setHeader("filename", fileName);
			response.setHeader("content-disposition", "attachment;filename=" + new String(fileName.getBytes()));
			stopWatch.start();


				int height = 0, // 总高度
						width = 0, // 总宽度
						_height = 0, // 临时的高度 , 或保存偏移高度
						__height = 0;// 临时的高度，主要保存每个高度

				File fileImg = null; // 保存读取出的图片
				int[] heightArray = new int[3]; // 保存每个文件的高度
				BufferedImage buffer = null; // 保存图片流
				List<int[]> imgRGB = new ArrayList<int[]>(); // 保存所有的图片的RGB
				int[] _imgRGB; // 保存一张图片中的RGB数据
				for (int i = 0; i < 3; i++) {
					buffer = listImage.get(i);
					heightArray[i] = _height = buffer.getHeight();// 图片高度
					if (i == 0) {
						width = buffer.getWidth();// 图片宽度
					}
					height += _height; // 获取总高度
					_imgRGB = new int[width * _height];// 从图片中读取RGB
					_imgRGB = buffer.getRGB(0, 0, width, _height, _imgRGB, 0, width);
					imgRGB.add(_imgRGB);
				}
				_height = 0; // 设置偏移高度为0
				// 生成新图片
				BufferedImage imageResult = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
				for (int i = 0; i < 3; i++) {
					__height = heightArray[i];
					if (i != 0){ _height += __height;} // 计算偏移高度
					imageResult.setRGB(0, _height, width, __height, imgRGB.get(i), 0, width); // 写入流中
				}
			ImageIO.write(imageResult, type, response.getOutputStream());
			stopWatch.stop();
			System.out.println("图片拼接的时间"+stopWatch.getTime());
			stopWatch.reset();
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			try {
				doc.close();
				fileInputStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}





}

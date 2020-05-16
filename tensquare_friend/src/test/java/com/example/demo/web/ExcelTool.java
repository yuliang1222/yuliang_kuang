/**
 * ClassName:ExcelTool
 * Author:Administrator
 * Date:2019/12/4 000410:30
 * Description:TODO
 */
package com.example.demo.web;





import java.io.ByteArrayOutputStream;

import java.io.File;

import java.io.FileInputStream;

import java.io.FileOutputStream;

import java.io.IOException;

import java.io.InputStream;

import java.lang.reflect.Field;

import java.math.BigDecimal;

import java.math.RoundingMode;

import java.net.URL;

import java.text.DateFormat;

import java.text.SimpleDateFormat;

import java.util.ArrayList;

import java.util.Collections;

import java.util.Date;

import java.util.HashMap;

import java.util.List;

import java.util.Map;



import net.sf.json.JSONArray;

import net.sf.json.JSONObject;



import org.apache.poi.hssf.usermodel.HSSFCellStyle;

import org.apache.poi.hssf.usermodel.HSSFDateUtil;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import org.apache.poi.poifs.filesystem.POIFSFileSystem;

import org.apache.poi.ss.usermodel.Cell;

import org.apache.poi.ss.usermodel.CellStyle;

import org.apache.poi.ss.usermodel.Drawing;

import org.apache.poi.ss.usermodel.Row;

import org.apache.poi.ss.usermodel.Sheet;

import org.apache.poi.ss.usermodel.Workbook;

import org.apache.poi.xssf.streaming.SXSSFWorkbook;

import org.apache.poi.xssf.usermodel.XSSFClientAnchor;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;





public class ExcelTool {



	private static final String XLSX = ".xlsx";

	private static final String XLS = ".xls";

	public static final short IMG_HEIGTH = 30; // 导出图片高度

	public static final short IMG_WIDTH = 30; // 导出图片宽度

	// 导出指定位置（如：F:\\data\\excel\\，不写即本工程目录下）

	public static final String PATH = "";



	/**

	 * 读取文件数据

	 * @param file .xlsx文件或者.xls文件

	 * @return 文件数据

	 */

	public static JSONArray readExcel(File file) {

		JSONArray array = null;

		try {

			String fileName = file.getName().toLowerCase();

			Workbook book = null;

			if (fileName.endsWith(XLSX)) {

				book = new XSSFWorkbook(new FileInputStream(file));

			} else if (fileName.endsWith(XLS)) {

				POIFSFileSystem poifsFileSystem = new POIFSFileSystem(new FileInputStream(file));

				book = new HSSFWorkbook(poifsFileSystem);

			} else {

				return array;

			}

			array = read(book);

		} catch (Exception e) {

			e.printStackTrace();

		}

		// POI 3.9乃自动关闭，故而无book.close()方法

		return array;

	}



	/**

	 * 将文件的数据解析为JSON

	 */

	private static JSONArray read(Workbook book) throws IOException {

		Sheet sheet = book.getSheetAt(0);

		int rowStart = sheet.getFirstRowNum(); // 首行下标

		int rowEnd = sheet.getLastRowNum(); // 尾行下标

		// 获取第一行JSON对象键

		Row firstRow = sheet.getRow(rowStart);

		int cellStart = firstRow.getFirstCellNum();

		int cellEnd = firstRow.getLastCellNum();

		Map<Integer, String> keyMap = new HashMap<Integer, String>();

		for (int j = cellStart; j < cellEnd; j++) {

			// 表头遇到空格停止解析

			String val = getValue(firstRow.getCell(j));

			if (val == null || val.trim().length() == 0) {

				cellEnd = j;

				break;

			}

			keyMap.put(j,val);

		}

		if (keyMap.isEmpty()) {

			return (JSONArray) Collections.emptyList();

		}

		// 获取每行JSON对象的值

		JSONArray array = new JSONArray();

		// 如果首行与尾行相同，表明只有一行，返回表头数据

		if (rowStart == rowEnd) {

			JSONObject object = new JSONObject();

			for (int i : keyMap.keySet()) {

				object.put(keyMap.get(i), "");

			}

			array.add(object);

			return array;

		}

		for(int i = rowStart+1; i <= rowEnd ; i++) {

			Row eachRow = sheet.getRow(i);

			JSONObject obj = new JSONObject();

			StringBuffer sb = new StringBuffer();

			for (int k = cellStart; k < cellEnd; k++) {

				if (eachRow != null) {

					String val = getValue(eachRow.getCell(k));

					sb.append(val); // 所有数据添加到里面，用于判断该行是否为空

					obj.put(keyMap.get(k),val);

				}

			}

			if (sb.toString().length() > 0) {

				array.add(obj);

			}

		}

		return array;

	}



	/**

	 * 获取表格单元格数据

	 */

	private static String getValue(Cell cell) throws IOException {

		// 空白或空

		if (cell == null || cell.getCellType() == Cell.CELL_TYPE_BLANK ) {

			return "";

		}

		// 0. 数字 类型

		if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {

			if (HSSFDateUtil.isCellDateFormatted(cell)) {

				Date date = cell.getDateCellValue();

				DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

				return df.format(date);

			}

			cell.setCellType(Cell.CELL_TYPE_STRING);

			String val = cell.getStringCellValue()+"";

			val = val.toUpperCase();

			if (val.contains("E")) {

				val = val.split("E")[0].replace(".", "");

			}

			return val;

		}

		// 1. String类型

		if (cell.getCellType() == Cell.CELL_TYPE_STRING) {

			String val = cell.getStringCellValue();

			if (val == null || val.trim().length() == 0) {

				return "";

			}

			return val.trim();

		}

		// 2. 公式 CELL_TYPE_FORMULA

		if (cell.getCellType() == Cell.CELL_TYPE_FORMULA) {

			return cell.getCellFormula();

		}

		// 4. 布尔值 CELL_TYPE_BOOLEAN

		if (cell.getCellType() == Cell.CELL_TYPE_BOOLEAN) {

			return cell.getBooleanCellValue()+"";

		}

		// 5. 错误 CELL_TYPE_ERROR

		return "";

	}



	/**

	 * 获取每个对象的数据

	 */

	private static <T> T getBean(Class<T> c, JSONObject obj) throws Exception{

		T t = c.newInstance();

		Field[] fields = c.getDeclaredFields();

		for (int i = 0; i < fields.length; i++) {

			Field field = fields[i];

			// 获取ExcleDesc注解属性

			ExcelDesc excelDesc = field.getAnnotation(ExcelDesc.class);

			if (excelDesc != null) {

				String cname = excelDesc.value();

				if (cname == null || cname.trim().length() == 0) {

					continue;

				}

				String val = null;

				if (obj.has(cname)) {

					val = obj.getString(cname);

				}

				// 获取具体值

				field.setAccessible(true);

				// 其余情况根据类型赋值

				String fieldClassName = field.getType().getSimpleName();

				try {

					if ("String".equalsIgnoreCase(fieldClassName)) {

						field.set(t, val);

					} else if ("boolean".equalsIgnoreCase(fieldClassName)) {

						field.set(t, obj.getBoolean(cname));

					} else if ("int".equalsIgnoreCase(fieldClassName) || "Integer".equals(fieldClassName)) {

						field.set(t, obj.getInt(cname));

					} else if ("double".equalsIgnoreCase(fieldClassName)) {

						field.set(t, obj.getDouble(cname));

					} else if ("long".equalsIgnoreCase(fieldClassName)) {

						field.set(t, obj.getLong(cname));

					} else if ("BigDecimal".equalsIgnoreCase(fieldClassName)) {

						field.set(t, new BigDecimal(val));

					}

				} catch (Exception e) {

					e.printStackTrace();

				}

			}

		}

		return t;

	}



	/**

	 * 将excel文件解析为指定对象集合

	 */

	public static <T> List<T> getBeanList(Class<T> c, File file) {

		// 解析上传文件为JsonArray

		JSONArray arr = readExcel(file);

		if (arr == null) {

			return Collections.emptyList();

		}

		// 解析List<Bean>

		List<T> list = new ArrayList<T>();

		for (int i = 0; i < arr.size(); i++) {

			try {

				list.add(getBean(c, (JSONObject) arr.get(i)));

			} catch (Exception e) {}

		}

		return list;

	}



	/**

	 * excel导出

	 * @param title 表名称

	 * @param rowList 导出每行数据

	 */

	public static void export(String title, List<List<Object>> rowList) {

		if (rowList == null) {

			rowList = Collections.emptyList();

		}

		SXSSFWorkbook book = new SXSSFWorkbook();

		Sheet sheet = book.createSheet(title);

		Drawing patriarch = sheet.createDrawingPatriarch();

		CellStyle style = book.createCellStyle();

		// 数据居左

		style.setAlignment(HSSFCellStyle.ALIGN_LEFT);

		// 写数据

		for (int i = 0; i < rowList.size(); i++) {

			List<Object> row = rowList.get(i);

			Row sr = sheet.createRow(i);

			for (int j = 0; j < row.size(); j++) {

				if (row.get(j) != null && row.get(j) instanceof URL) {

					URL url = (URL)row.get(j);

					sr.setHeight((short) (IMG_WIDTH * IMG_HEIGTH));

					setExcelImg(book, patriarch, i, j, url);

				} else {

					setExcelValue(sr.createCell(j), row.get(j), style);

				}

			}

		}

		try {

			if (PATH.length() > 0) {

				File dir = new File(PATH);

				if (!dir.exists()) {

					dir.mkdirs();

				}

			}

			File file = new File(PATH + title + XLSX);

			if (!file.exists()) {

				file.createNewFile();

			}

			FileOutputStream fos = new FileOutputStream(file);

			ByteArrayOutputStream ops = new ByteArrayOutputStream();

			book.write(ops);

			fos.write(ops.toByteArray());

			fos.close();

		} catch (Exception e) {

			e.printStackTrace();

		}

	}



	/**

	 * 导出写图片

	 */

	private static void setExcelImg(SXSSFWorkbook wb,

	                                Drawing patriarch, int rowIndex, int cloumIndex, URL url) {

		// （jdk1.7版本try中定义流可自动关闭）

		try (InputStream is = url.openStream();

		     ByteArrayOutputStream outputStream = new ByteArrayOutputStream();) {

			byte[] buff = new byte[1024];

			int rc = 0;

			while ((rc = is.read(buff, 0, 1024)) > 0) {

				outputStream.write(buff, 0, rc);

			}

			// 设置图片位置

			XSSFClientAnchor anchor = new XSSFClientAnchor(0, 0, 0, 0,

					cloumIndex, rowIndex, cloumIndex + 1, rowIndex + 1);

			anchor.setAnchorType(0);

			patriarch.createPicture(anchor, wb.addPicture(

					outputStream.toByteArray(), HSSFWorkbook.PICTURE_TYPE_JPEG));

			outputStream.close();

		} catch (Exception e) {

			e.printStackTrace();

		}

	}



	/**

	 * 导出写数据

	 */

	public static void setExcelValue(Cell cell,  Object value, CellStyle style){

		// 写数据

		if (value == null) {

			cell.setCellValue("");

		}else {

			if (value instanceof Integer || value instanceof Long) {

				cell.setCellType(Cell.CELL_TYPE_NUMERIC);

				cell.setCellValue(Long.valueOf(value.toString()));

			} else if (value instanceof BigDecimal) {

				cell.setCellType(Cell.CELL_TYPE_NUMERIC);

				cell.setCellValue(((BigDecimal)value).setScale(3, RoundingMode.HALF_UP).doubleValue());

			} else {

				cell.setCellValue(value.toString());

			}

			cell.setCellStyle(style);

		}

	}

}

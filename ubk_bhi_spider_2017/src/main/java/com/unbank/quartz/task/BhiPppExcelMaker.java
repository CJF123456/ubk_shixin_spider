//package com.unbank.quartz.task;
//
//import java.io.File;
//import java.io.FileNotFoundException;
//import java.io.FileOutputStream;
//import java.io.IOException;
//import java.util.Date;
//import java.util.Iterator;
//import java.util.LinkedHashMap;
//import java.util.List;
//import java.util.Map;
//import java.util.Set;
//
//import net.sf.ezmorph.bean.MorphDynaBean;
//import net.sf.json.JSONArray;
//import net.sf.json.JSONObject;
//
//import org.apache.poi.ss.usermodel.Cell;
//import org.apache.poi.xssf.usermodel.XSSFRow;
//import org.apache.poi.xssf.usermodel.XSSFSheet;
//import org.apache.poi.xssf.usermodel.XSSFWorkbook;
//
//import com.unbank.dao.BhiPppReader;
//import com.unbank.mybatis.vo.BhiPPPWithBLOBs;
//import com.unbank.tools.WordLorder;
//
//public class BhiPppExcelMaker extends WordLorder {
//	public static int index = 1;
//
//	public static void main(String[] args) {
//
//		String[] areas = new String[] { "湖南", "江苏", "内蒙古", "河北", "陕西", "山东",
//				"安徽", "山西", "江西", "广东", "宁夏", "福建", "四川", "辽宁", "广西", "湖北",
//				"吉林", "河南", "贵州", "新疆", "西藏", "黑龙江", "重庆", "甘肃", "浙江", "上海",
//				"北京", "云南", "天津", "青海", "海南", "" };
//		for (String area : areas) {
//
//			List<BhiPPPWithBLOBs> bhiPPPs = new BhiPppReader()
//					.readBhiPppReader(area);
//			List<String> zonglist = loadWords(BhiPppExcelMaker.class
//					.getResourceAsStream("zong2.txt"));
//
//			List<String> fenglist = loadWords(BhiPppExcelMaker.class
//					.getResourceAsStream("feng2.txt"));
//
//			XSSFWorkbook xwb = new XSSFWorkbook();
//			XSSFSheet sheet = xwb.createSheet("info");
//			String[] a = new String[] { "编号", "项目名称", "地区", "省份", "项目类别", "类型",
//					"合作模式", /* "项目简介", */"总投资（亿元）", "级别", /* "是否有参与单位信息" */
//					"更新时间", "发布时间", /* "是否有进展跟踪", *//* "是否有联系人信息", */"项目详细描述",
//					"公司参与信息", "项目进展信息", "联系人信息", "是否分行相关", "是否总行相关" };
//			XSSFRow row = sheet.createRow(0);
//			for (int i = 0; i < a.length; i++) {
//				Cell cell = row.createCell(i);
//				cell.setCellValue(a[i]);
//			}
//			int rowIndex = 1;
//			boolean isMakeFile = false;
//			for (int j = 0; j < bhiPPPs.size(); j++) {
//				BhiPPPWithBLOBs bhiPPP = bhiPPPs.get(j);
//				String info = bhiPPP.getInfo();
//				String description = bhiPPP.getDescription();
//				JSONObject infoJsonObject = JSONObject.fromObject(info);
//				LinkedHashMap<String, Object> infoMap = (LinkedHashMap<String, Object>) JSONObject
//						.toBean(infoJsonObject, LinkedHashMap.class);
//				description = description.replace("\r\n", "");
//				description = description.replace("\n", "");
//				description = description.replace("	", "");
//				description = description.replace(" ", "");
//				description = description.replace("\\\"", "\"");
//				description = description.replace(": \"\",", "@@@");
//				description = description.replace(":\"\",", "@@@");
//				description = description.replace(":\"\"}", "@@@@");
//				description = description.replace("\"\"", "\"");
//				description = description.replace("@@@@", ":\"\"}");
//				description = description.replace("@@@", ":\"\",");
//				description = description.replace("\\", "");
//				JSONObject jsonObject = JSONObject.fromObject(description);
//				String createTime = (String) infoMap.get("CreateTime");
//				long createDate = Date.parse(createTime);
//				String bijiaoTime = "2016/08/01";
//				long bijiaoDate = Date.parse(bijiaoTime);
//				if (bijiaoDate > createDate) {
//					continue;
//				}
//				isMakeFile = true;
//				row = sheet.createRow(rowIndex++);
//				Cell cell = row.createCell(0);
//				cell.setCellValue(j + 1);
//				makeInfoExcel(infoMap, row);
//				makeDesExcel(jsonObject, row);
//
//				boolean isFeng = false;
//				for (String string : fenglist) {
//					if (description.contains(string.trim())) {
//						isFeng = true;
//						break;
//					}
//					if (info.contains(string.trim())) {
//						isFeng = true;
//						break;
//					}
//				}
//
//				cell = row.createCell(15);
//				if (isFeng) {
//					cell.setCellValue("是");
//				}
//				cell = row.createCell(16);
//				boolean isZong = false;
//				for (String string : zonglist) {
//					if (description.contains(string.trim())) {
//						isZong = true;
//						break;
//					}
//					if (info.contains(string.trim())) {
//						isZong = true;
//						break;
//					}
//				}
//				if (isZong) {
//					cell.setCellValue("是");
//				}
//				index = 1;
//			}
//			if (!isMakeFile) {
//				continue;
//			}
//			String path = null;
//			try {
//				path = BhiPppExcelMaker.class.getClassLoader().getResource("")
//						.toURI().getPath();
//				path = path.substring(1, path.length());
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//			String filePath = path + "temp/";
//			File file = new File(filePath);
//			if (!file.exists()) {
//				System.out.println("执行");
//				file.mkdirs();
//			}
//			StringBuffer fileName = new StringBuffer();
//			fileName.append("bhi_" + area);
//			fileName.append(".xlsx");
//			filePath = filePath + fileName.toString();
//			try {
//				FileOutputStream fileOut = new FileOutputStream(filePath);
//				xwb.write(fileOut);
//				fileOut.close();
//			} catch (FileNotFoundException e) {
//				e.printStackTrace();
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//		}
//	}
//
//	private static void makeDesExcel(JSONObject jsonObject, XSSFRow row) {
//
//		if (jsonObject.containsKey("Description")) {
//
//			JSONArray jsonArray = jsonObject.getJSONArray("Description");
//			JSONObject descriptionjsonObject = JSONObject.fromObject(jsonArray
//					.get(0));
//			if (descriptionjsonObject.containsKey("ProjectDescription")) {
//				String projectDescription = descriptionjsonObject
//						.getString("ProjectDescription");
//				Cell cell = row.createCell(11);
//				cell.setCellValue(projectDescription);
//			}
//			if (descriptionjsonObject.containsKey("Company")) {
//				String company = descriptionjsonObject.getString("Company");
//				Cell cell = row.createCell(12);
//				cell.setCellValue(company);
//			}
//
//		}
//		;
//		if (jsonObject.containsKey("Progress")) {
//			JSONArray jsonArray = jsonObject.getJSONArray("Progress");
//			StringBuffer titleBuffer = new StringBuffer();
//			for (Object object : jsonArray) {
//				JSONObject titleObject = JSONObject.fromObject(object);
//				titleBuffer.append(titleObject.get("title") == null ? ""
//						: titleObject.get("title"));
//				titleBuffer.append("\r\n");
//			}
//			Cell cell = row.createCell(13);
//			cell.setCellValue(titleBuffer.toString());
//
//		}
//		if (jsonObject.containsKey("LinkMan")) {
//			JSONObject linkManJsonObject = jsonObject.getJSONObject("LinkMan");
//			JSONArray linManJsonArray = linkManJsonObject
//					.getJSONArray("content");
//
//			// {"owner":"其他单位","company":"邓州市妇幼保健院","more":"","LabelName":"PPP合作方采购单位","address":"","addDate":"2016/1/7 16:19:59","actors":[{"FullName":"刘书记","OfficePhone":"13949383607"}]},
//			StringBuffer linkMan = new StringBuffer();
//			for (Object object : linManJsonArray) {
//				String company = object.toString();
//				company = company.replace("owner", "单位性质");
//				company = company.replace("company", "单位名称");
//				company = company.replace("more", "其他信息");
//				company = company.replace("LabelName", "参与关系");
//				company = company.replace("addDate", "参加时间");
//				company = company.replace("actors", "联系人");
//				company = company.replace("FullName", "联系人姓名");
//				company = company.replace("OfficePhone", "联系人电话");
//				company = company.replace("address", "单位地址");
//				linkMan.append(company);
//			}
//			Cell cell = row.createCell(14);
//			cell.setCellValue(linkMan.toString());
//
//		}
//	}
//
//	private static void makeInfoExcel(Map<String, Object> infoMap, XSSFRow row) {
//		Set<String> keyset = infoMap.keySet();
//		Iterator<String> iterator = keyset.iterator();
//		while (iterator.hasNext()) {
//			String key = iterator.next();
//			Object object = infoMap.get(key);
//			String value = null;
//			if (object instanceof String) {
//				value = (String) object;
//			} else if (object instanceof Integer) {
//				value = object + "";
//			} else if (object instanceof Double) {
//				value = object + "";
//			} else {
//				value = (String) object;
//			}
//			if (key.equals("ProjectDescription")) {
//				continue;
//			}
//			if (key.equals("Company")) {
//				continue;
//			}
//			if (key.equals("title")) {
//				continue;
//			}
//			if (key.equals("EventsID")) {
//				continue;
//			}
//			if (key.equals("Exportid")) {
//
//			} else {
//				Cell cell = row.createCell(index++);
//				cell.setCellValue(value);
//			}
//		}
//	}
//
//	// {
//	// "Description": [
//	// {
//	// "ProjectDescription":
//	// "占地面积约140亩；一期规模1200吨/日，2×600吨/日生活垃圾焚烧线+2×12MW汽轮发电机组，预留二期工程位置。项目以PPP模式实施，通过公开招标选定中标人，由中标人和政府出资代表（政府以已征土地使用权及2600万元现金参股项目公司，持股比例约为15%，享受分红。）负责组建项目公司与商丘市城市管理局签署《特许经营协议》，由项目公司负责商丘市生活垃圾焚烧发电厂的融资、投资、设计、建设、运营管理和项目特许期结束后的移交。",
//	// "Company": "PPP合作方采购单位：商丘市城市管理局。\r\nPPP合作方招标机构：中化国际招标有限责任公司。"
//	// }
//	// ],
//	// "Progress": [
//	// {
//	// "title": "2016年3月23日，发布项目PPP政府采购招标公告。"
//	// },
//	// {
//	// "title": "2016年3月4日，该项目发布PPP合作方资格预审结果。"
//	// },
//	// {
//	// "title": "2015年9月，入选财政部第二批PPP示范项目名单。"
//	// }
//	// ],
//	// "LinkMan": {
//	// "content": [
//	// {
//	// "owner": "其他单位",
//	// "company": "——",
//	// "more": "",
//	// "LabelName": "",
//	// "address": "",
//	// "addDate": "2015/11/3 13:23:02",
//	// "actors": [
//	// {
//	// "FullName": "刘卫民",
//	// "OfficePhone": "13903709011"
//	// }
//	// ]
//	// }
//	// ]
//	// }
//	// }
//
//	// {"Description":[{"ProjectDescription":"本项目分两期建设：一期工程：南水北调中线工程叶县段生态廊道建设项目本项目涉及生态廊道建设里程31.2公里，干渠两侧绿化带宽度100米(部分地段200米)，其中临渠内侧30米内建设常绿乔木防护林带;中间预留7米宽生产、休闲观光道路;外侧63米构建绿化林带，由社会资本自主确定栽种品种，间作油用牡丹;重点部位加大绿化力度，并设置部分景观园区。本项目总占地面积为10495亩。总投资额为17191万元。(具体数额以政府投资评审数额为准)；二期工程：灰河、沙河治理项目，灰河综合治理长度5公里，沿水岸向两侧各辐射100米，包括河道清淤、绿化、景观小品、园路、路灯等配套工程，灰河治理概算2000万\/公里，总投资1亿元。沙河综合治理长度3公里，沿水岸向两侧各辐射500米，包括河道清淤、绿化、景观小品、园路、路灯等配套工程，沙河概算1亿元\/公里;灰河、沙河治理作为本项目的二期工程，在生态廊道一期工程完工后，根据政府工作安排启动。项目全生命周期：10年(含建设期2个月)","Company":"PPP合作方采购单位：叶县林业局。\r\nPPP合作方招标机构：北京思泰工程咨询有限公司。"}],"Progress":[{"title":"2016年5月，公布PPP合作方中标单位。"},{"title":"2016年3月，该项目进行PPP合作方资格预审。"}],"LinkMan":{"content":[{"owner":"其他单位","company":"北京思泰工程咨询有限公司","more":"","LabelName":"PPP合作方招标机构","address":"","addDate":"2016/1/21
//	// 14:31:21","actors":[{"FullName":"苏先生","OfficePhone":"18903901590"}]}]}}
//	private static void makeDescriptionExcel(Map<String, Object> descMap,
//			XSSFRow row) {
//		Set<String> keyset = descMap.keySet();
//		Iterator<String> iterator = keyset.iterator();
//		while (iterator.hasNext()) {
//			String key = iterator.next();
//			System.out.println(key);
//			Object object = descMap.get(key);
//			String value = null;
//			if (object instanceof List) {
//				JSONArray temp = JSONArray.fromObject(object);
//				for (Object tempObject : temp) {
//					JSONObject tempJsonObject = JSONObject
//							.fromObject(tempObject);
//					LinkedHashMap<String, Object> map = (LinkedHashMap<String, Object>) JSONObject
//							.toBean(tempJsonObject, LinkedHashMap.class);
//					makeDescriptionExcel(map, row);
//				}
//			} else {
//				if (object instanceof LinkedHashMap) {
//					makeDescriptionExcel((LinkedHashMap) object, row);
//
//				} else {
//
//					if (object instanceof MorphDynaBean) {
//						JSONObject tempJsonObject = JSONObject
//								.fromObject(object);
//						LinkedHashMap<String, Object> map = (LinkedHashMap<String, Object>) JSONObject
//								.toBean(tempJsonObject, LinkedHashMap.class);
//						makeDescriptionExcel(tempJsonObject, row);
//
//					} else {
//						if (object instanceof String) {
//							value = (String) object;
//						} else if (object instanceof Integer) {
//							value = object + "";
//						} else if (object instanceof Double) {
//							value = object + "";
//						} else {
//							System.out.println(object);
//						}
//						Cell cell = row.createCell(index++);
//						cell.setCellValue(value);
//					}
//				}
//			}
//
//		}
//
//	}
//}

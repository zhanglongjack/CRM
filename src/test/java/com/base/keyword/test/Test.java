package com.base.keyword.test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.base.common.util.FileReaderUtil;

public class Test {
	private static ExecutorService fixedThreadPool = Executors.newFixedThreadPool(300);
	private static String root = "D:/学习相关/百度推广/我的项目/关键词分析/关键词报表分析/201905/加词21/";
	private static String filterRoot = "D:/学习相关/百度推广/我的项目/关键词分析/关键词报表分析/201905/";
	private static Map<String,String> filterWords = null;
	private static String keywords[] = {"痔疮","外痔","内痔","混合痔","痔核","肉球"};
	private static String cureKeyWorlds[] = {"治疗"};
	public static void main(String[] args) {
		
		
		String data = "data.txt";
		String filterData = "filter_words.txt";
		Map<String,String> dataReult = FileReaderUtil.readFile(root+data);
		filterWords = FileReaderUtil.readFile(filterRoot+""+filterData);
		
		List<String> dataList = new CopyOnWriteArrayList<>();
		List<String> wrongData = new CopyOnWriteArrayList<>();
		List<String> cureKeyWorldsList = new CopyOnWriteArrayList<>();
		
		if(dataReult==null){
			System.out.println("数据文件读取失败");
			return ;
		}
		for(String world : dataReult.keySet()){
			boolean isRightData = isRightData(world);
			boolean isIncludeKeyWorlds = isIncludeKeyWorlds(world);
			boolean isIncludeCureKeyWorlds = isIncludeCureKeyWorlds(world);
			if(isRightData &&isIncludeCureKeyWorlds && isIncludeKeyWorlds){
				cureKeyWorldsList.add(world);
			}else if(isIncludeKeyWorlds && isRightData){
				dataList.add(world);
			}else{
				wrongData.add(world);
			}
		}
		
		
//		for(String world : dataList){
//			for(String keyWorld : keywords){
//				if(check(world,new String[]{keyWorld})){
//					FileReaderUtil.writeInFileBy(root+keyWorld+".txt", world,true);
//					break;
//				}
//			}
//		}
		handle(dataList, "");
//		handle(cureKeyWorldsList, "cure");
		
		FileReaderUtil.writeInFileBy(root+"wrongData.txt", wrongData);
		
		System.out.println(dataList.iterator());
		
		fixedThreadPool.shutdown();
		try {
			while(!fixedThreadPool.isTerminated())
				Thread.sleep(1000);
			System.out.println("结束");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void handle(List<String> dataList,String prefix){
		
		for(String world : dataList){
			fixedThreadPool.execute(new Runnable(){
				@Override
				public void run() {
					for(String keyWorld : keywords){
						if(check(world,new String[]{keyWorld})){
							FileReaderUtil.writeInFileBy(root+prefix+keyWorld+".txt", world,true);
							break;
						}
					}
				}
			});
		}
	}
	
	
	public static boolean isRightData(String world){
		String filter[] = filterWords.keySet().toArray(new String[0]);
		return !check(world,filter);
	}
	public static boolean isIncludeKeyWorlds(String world){
		return check(world,keywords);
	}
	
	public static boolean isIncludeCureKeyWorlds(String world){
		return check(world,cureKeyWorlds);
	}

	public static boolean check(String world, String datas[]){
		for(String data : datas){
			if(isExists(world, data)){
				return true;
			}
		}
		return false;
	}

	private static boolean isExists(String world, String data) {
		if(world!=null&&world.indexOf(data)>=0){
			return true;
		}
		return false;
	}
}

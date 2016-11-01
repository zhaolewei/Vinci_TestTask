package com.zlw.main;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.zlw.bean.Music;
import com.zlw.utils.FileHelper;
import com.zlw.utils.JsonTools;
import com.zlw.utils.ListHelper;

public class Main {

	private static final String path = "C:\\Users\\123\\Desktop\\zlw\\";
	private static final String fileName = "test";

	public static void main(String[] args) {
		StringBuffer buff = new StringBuffer();
		for (int i = 0; i < 10; i++) {
			String str = FileHelper.readFile(path + fileName + i);
			buff.append(str);
		}

		List<Music> data = new ArrayList<Music>();

		String[] arr = buff.toString().split("#zlw#");

		for (int i = 0; i < arr.length; i++) {
			String[] array = arr[i].trim().split(" ", 2);
			if (array.length > 1) {
				data.add(new Music(array[0], array[1].trim()));
			}
		}
		ListHelper.removeDuplicate2(data); // 去重
		ListHelper.<Music> sort(data, "singer", null); // 自然排序
		// showList(data);

		StringBuffer write_buff = new StringBuffer();
		for (int i = 0; i < data.size(); i++) {
			write_buff.append(data.get(i).getSong() + " " + data.get(i).getSinger() + "\n");
		}
		FileHelper.writeFile(new File(path + "newfile"), write_buff.toString());
		System.out.println(FileHelper.readFile(path + "newfile"));
		// 任务2.排序（java程序）； 到此完成 生成文件 newfile
		
		String jsonstr = JsonTools.toJson(data);
		System.out.println(jsonstr);
		FileHelper.writeFile(new File(path + "jsonfile"), jsonstr);
		//任务3.把排序完成后的文件生成一个json数组存入文件结果类似（java程序） ；在此完成，生成jsonfile文件
		

	}

	private static void showList(List<Music> data) {
		for (int i = 0; i < data.size(); i++) {
			System.out.println(data.get(i).getSong() + " : " + data.get(i).getSinger());
		}
	}

}

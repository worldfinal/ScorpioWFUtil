package com.frc.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.junit.Test;

public class ReadCSVUtil {
	final public static char CHARAC_TAB = '\t';
	final public static char CHARAC_COMMA = ',';
	final public static char CHARAC_SPACE = ' ';
	
	private int idx;
	private String rowData;
	private boolean ignore;
	public List<String[]> getResultFromCSVFile(String fileName, char splitCharacter) {
		List<String[]> result = new ArrayList<String[]>();
		File file = new File(fileName);
		if (file.exists()) {
			try {
				// read
				FileInputStream in = new FileInputStream(file);
				//DataInputStream dis = new DataInputStream(in);
				
				Scanner cin=new Scanner(in, "UTF8");
				while (cin.hasNext()) {
					String s = cin.nextLine();
//					System.out.println(s);
					String[] arr = splitString(s, splitCharacter);
					result.add(arr);
				}
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				// close
			}
		}
		return result;
	}
		
	public String[] splitString(String rowData, char splitCharacter) {
		if (rowData == null) {
			return null;
		}
	
		this.idx = 0;
		this.rowData = rowData;
		this.ignore = false;
		
		List<String> arrs = new ArrayList<String>(); 
		boolean flag = parseRow(splitCharacter, arrs);
		if (flag) {
			String[] rs = new String[arrs.size()];
			for (int i = 0; i < arrs.size(); i++) {
				rs[i] = arrs.get(i);
			}
			return rs;
		} else {
			System.out.println("Fail");
			return null;
		}	
	}

	// row  -> item [,item]
	private boolean parseRow(char splitCharacter, List<String> rs) {
		boolean flag = parseItem(splitCharacter, rs);
		while (flag && idx < rowData.length() && rowData.charAt(idx) == splitCharacter) {
			idx++;
			flag |= parseItem(splitCharacter, rs);
		}
		return flag;
	}
	
	// item -> "data" | data
	private boolean parseItem(char splitCharacter, List<String> rs) {
		boolean flag = true;
		if (idx < rowData.length() && rowData.charAt(idx) == '\"') {
			idx++;
			ignore = true;
			flag = parseData(splitCharacter, rs);
			flag &= (idx < rowData.length() && rowData.charAt(idx) == '\"');
			ignore = false;
			idx++;
		} else {
			flag = parseData(splitCharacter, rs);
		}
		return flag;
	}

	// data -> [^,]*
	private boolean parseData(char splitCharacter, List<String> rs) {
		String str = "";
		while (idx < rowData.length()) {
			if (rowData.charAt(idx) == '\"' || !ignore && rowData.charAt(idx) == splitCharacter) {
				break;
			}
			str += rowData.charAt(idx);
			idx++;
		}
		rs.add(str.trim());
		return true;
	}
	
	@Test
	public void testFromCSV() {
		String fileName = "D:\\My Documents\\交易资料\\agtd.txt";
		List<String[]> rs = getResultFromCSVFile(fileName, ReadCSVUtil.CHARAC_TAB);
		System.out.println("totle:" + rs.size());
		for (int i = 0; i < rs.size(); i++) {
			String[] arr = rs.get(i);
			System.out.print(i + " ");
			if (arr == null) {
				System.out.println("[null] ... " + i);
			} else {
				System.out.println("=== " + arr.length + " ======");
			}
			for (int j = 0; j < arr.length; j++) {
				System.out.print(String.format("_%s_", arr[j]));
			}
			System.out.println();
		}
	}
	
	@Test
	public void test() {
		Scanner cin=new Scanner(System.in);
		while (cin.hasNext()) {
			String in = cin.nextLine();
			String[] str = splitString(in, ReadCSVUtil.CHARAC_COMMA);
			System.out.println("length = " + str.length);
			for (int i = 0; i < str.length; i++) {
				System.out.println(i + ":" + str[i]);
			}
			System.out.println("==============");
		}
		
	}
}

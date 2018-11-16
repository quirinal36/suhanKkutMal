package file.to.json;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JOptionPane;

import org.apache.commons.io.FileUtils;

public class GamePlay {

	public static void main(String[] args) {
		Map<String, String> map = new HashMap<>();
		File file = new File("resource", "voca.txt");
		// 1. file 이 실제로 있는지 확인
		if(file.exists()) {
//			System.out.println("파일이 존재함");
		}else {
//			System.out.println("파일이 없음");
		}
		
//		System.out.println(file.getAbsolutePath());
//		System.out.println(file.length());
		if(file.isFile()) {
//			System.out.println("파일 입니다.");
		}
		// FileUtils 사용
		try {
			String readFile = FileUtils.readFileToString(file, StandardCharsets.UTF_8);
//			System.out.println(readFile);
			
			List<String> list = FileUtils.readLines(file, StandardCharsets.UTF_8);
			for(String item : list) {
				int tabIndex = item.indexOf("\t");
//				System.out.print("tabIndex : " + tabIndex);
				if(item.length() == 0 || tabIndex < 0) {
					continue;
				}
				String voca = item.substring(0, tabIndex);
//				System.out.print("\t/voca : " + voca);
				
				String voca_kor = item.substring(tabIndex+1);
//				System.out.println("\t/vocaKor : " + voca_kor);
				
				if(voca.length() > 1) {
					map.put(voca, voca_kor);
				}
			}
			
			// 몇개가 저장 되었나
			System.out.println("size : " + map.size());
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		List<String> gamerList = new ArrayList<>();
		while(true) {
			String input = JOptionPane.showInputDialog("입력하세요");
			if(map.containsKey(input)) {
//				System.out.println("포함된 단어입니다.");
				if(gamerList.contains(input)) {
					System.out.println("이미 사용한 단어입니다.");
				}else {
					if(gamerList.size() > 0) {
						String lastVoca = gamerList.get(gamerList.size()-1);
						char lastAlp = lastVoca.charAt(lastVoca.length()-1);
						char firstAlp = input.charAt(0);
						
						if(lastAlp == firstAlp) {
							System.out.println("정상입력");
							gamerList.add(input);
						} else {
							System.out.println("끝말잇기를 해주세요.");
						}
					}else {
						gamerList.add(input);
					}
				}
			}else {
				System.out.println("알 수 없는 단어입니다.");
			}
		}
	}

}

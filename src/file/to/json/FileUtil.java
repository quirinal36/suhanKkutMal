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

public class FileUtil {
	public static void main(String[] args) {
		Map<String, String> map = new HashMap<>();
		try {
			File file = new File("resource/voca.txt");
			List<String> inputList = FileUtils.readLines(file, StandardCharsets.UTF_8.name());
			for(String line : inputList) {
				
				final int index = line.indexOf("\t");
				if(index > 0) {
					String voca = line.substring(0, index);
					
					if(voca.length() > 1) {
						String voca_kor = line.substring(index, line.length());
						map.put(voca, voca_kor);
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		System.out.println("vocas length : " + map.size());
		List<String> gamerList = new ArrayList<>();
		int turn = 0;
		
		while(true) {
			String input = JOptionPane.showInputDialog("입력:");
			
			if(map.containsKey(input)) {
				boolean rullEndChar = true;
				if(turn > 0) {
					String lastVoca = gamerList.get(turn - 1);
					char lastChar = lastVoca.charAt(lastVoca.length()-1);
					char firstChar = input.charAt(0);
					rullEndChar = (lastChar == firstChar); 
				}
				if(rullEndChar && !(gamerList.contains(input))) {
					System.out.println(input + " => 성공");
					gamerList.add(input);
					turn++;
				}else if(!rullEndChar) {
					System.out.println(input + " => 끝말에 이어서 써주세요");
				}else if(!gamerList.contains(input)) {
					System.out.println("이미 사용된 단어입니다.");
				}
			}else {
				System.out.println("없는 단어입니다.");
			}
		}
	}
}

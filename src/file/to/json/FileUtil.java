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
		// 모든 voca 를 저장할 장소
		Map<String, String> map = new HashMap<>();
		try {
//			File file = new File("resource/voca.txt");
			File file = new File("resource", "voca.txt");
			
			// file 에서 한줄씩 읽어와 List 에 저장시킨다.
			List<String> inputList = FileUtils.readLines(file, StandardCharsets.UTF_8.name());
			
			for(String line : inputList) {			
				// tab 으로 구분되어있는 영어 / 한글 을 분단시킨다.
				final int index = line.indexOf("\t");
				if(index > 0) {
					String voca = line.substring(0, index);
					if(voca.length() > 1) {
						// 단어의 길이가 1개 이상일때만 저장한다.
						String voca_kor = line.substring(index, line.length());
						map.put(voca, voca_kor);
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		System.out.println("vocas length : " + map.size());
		// game 을 주관하는 리스트
		List<String> gamerList = new ArrayList<>();
		// 리스트의 길이가 늘어날수록 turn 길이도 늘어난다.
		int turn = 0;
		
		while(true) {
			// 유저로부터 입력을 받는다.
			String input = JOptionPane.showInputDialog("입력:");
			
			// 마지막 글자와 일치하는지 검사하기 위한 변수
			boolean ruleEndChar = true;
			
			if(turn > 0) {
				// 바로 직전에 입력된 단어
				String lastVoca = gamerList.get(turn - 1);
				// 바로 직전에 입력된 단어의 마지막 알파벳 ------ (1)
				char lastChar = lastVoca.charAt(lastVoca.length()-1);
				// 지금 입력된 글자의 첫번째 알파벳	--------- (2)
				char firstChar = input.charAt(0);
				
				// (1) 과 (2) 가 서로 같다면 true 
				ruleEndChar = (lastChar == firstChar); 
			}
			
			if(ruleEndChar && !(gamerList.contains(input))) {
				// 정상적인 게임룰에 따른다면 진행
				if(map.containsKey(input)) {
					System.out.println(input + " => 성공");
					gamerList.add(input);
					turn++;
				}else {
					System.out.println("없는 단어입니다.");
				}
			}else if(!ruleEndChar) {
				// 끝말에 이어서 쓰지 않았다면,
				System.out.println(input + " => 끝말에 이어서 써주세요");
			}else if(gamerList.contains(input)) {
				// 이미 사용된 단어라면,
				System.out.println("이미 사용된 단어입니다.");
			}
		}
	}
}

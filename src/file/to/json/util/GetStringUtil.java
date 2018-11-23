package file.to.json.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class GetStringUtil {
	public static void main(String[] args) {
			System.out.println(
					getStringFromUrl("http://game.bacoder.kr/existId.jsp?&login=suhan").trim());
	}
	/**
	 * HTML 파일을 열어서 안의 내용물을 조회하는 함수
	 * @param uri
	 * @return
	 * @throws IOException 
	 * @throws MalformedURLException 
	 */
	public static String getStringFromUrl(String uri) {
		String result = new String();
		try (Scanner scanner = new Scanner(new URL(uri).openStream(),
	            StandardCharsets.UTF_8.toString()))
	    {
	        scanner.useDelimiter("\\A");
	        result = scanner.hasNext() ? scanner.next() : "";
	    }catch(MalformedURLException e) {
	    	e.printStackTrace();
	    }catch(IOException e) {
	    	e.printStackTrace();
	    }
		return result;
	}
}
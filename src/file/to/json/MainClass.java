package file.to.json;

import file.to.json.LoginView;

public class MainClass {
	Gameview gameview;
	LoginView loginView;
	
	public static void main(String[] args) {
		//LoginView loginView = LoginView.newInstance("로그인");
		//loginView.setVisible(true);
		new Makelogin();
		
	}
	
}

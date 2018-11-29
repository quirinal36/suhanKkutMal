package file.to.json;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Logger;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.json.JSONArray;
import org.json.JSONObject;

import file.to.bean.GameUser;
import file.to.json.util.GetStringUtil;

public class LoginView extends JFrame implements ActionListener{
	Logger logger = Logger.getLogger(LoginView.class.getSimpleName());

	/**
	 * 
	 */
	private static final long serialVersionUID = -3112867886286423957L;

	JButton loginButton;
	JButton signupButton;
	JTextField idText;
	JTextField pwdText;
	//로그인 뷰 라는객체를만든다.
	/**
	 * 
	 * @param title
	 * @return
	 */
	public static LoginView newInstance(String title) {
		LoginView instance = new LoginView(title);
		return instance;
	}
	/**
	 * 생성자
	 * @param title
	 */
	public LoginView(String title) {
		this.setTitle(title); 	//제목
		this.setBounds(100, 100, 600, 400);	//위치 및 창 크기
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.getContentPane().setLayout(new GridLayout(3,1));	//레이아웃

		init();
	}
	/**
	 * 레이아웃 세팅
	 */
	
	private void init() {
		//1,아이디 입력관련 view
		JPanel idPanel = new JPanel(new GridLayout(1, 2));
		JLabel labelID = new JLabel("id:");
		idText = new JTextField(8);
		labelID.setHorizontalAlignment(JTextField.CENTER);
		idPanel.add(labelID);
		idPanel.add(idText);
		
		//2,비밀번호 입력관련 view
		JPanel pwdPanel = new JPanel(new GridLayout(1, 2));
		JLabel labelPWD = new JLabel("pwd:");
		pwdText = new JTextField(8);
		labelPWD.setHorizontalAlignment(JTextField.CENTER);
		pwdPanel.add(labelPWD);
		pwdPanel.add(pwdText);

		//3,로그인,회원가입 버튼 관련 view
		JPanel buttonPanel = new JPanel(new GridLayout(1, 2));
		loginButton = new JButton("로그인");
		signupButton = new JButton("회원가입");
		buttonPanel.add(loginButton);
		buttonPanel.add(signupButton);

		//패널들을 그리드뷰에 배치하기
		getContentPane().add(idPanel);
		getContentPane().add(pwdPanel);
		getContentPane().add(buttonPanel);
		
		// 버튼 액션 리스너 등록
		loginButton.addActionListener(this);
		signupButton.addActionListener(this);

		//새로 고침
		validate();

	}
	/**
	 * 버튼이 클릭 되었을 때 액션
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		
		//로그인 버튼이 클릭 되었을 때
		if(e.getSource() == loginButton) {
			
			//로그인 URL 만들기(parameter : login,pwd)
			StringBuilder builder = new StringBuilder();
			builder.append("http://game.bacoder.kr/login.jsp")
					.append("?login=").append(idText.getText())
					.append("&pwd=").append(pwdText.getText());
			//로그인 결과 가져오기
			String result = GetStringUtil.getStringFromUrl(builder.toString());
			
			// 결과를 JSON 으로 파싱하기
			JSONObject json = new JSONObject(result);
			//login 과 pwd를 통해 가져온 user가 1명이라면 로그인 성공이다.
			if(json.getJSONArray("list").length() == 1) {
				JSONArray array = json.getJSONArray("list");
				JSONObject userInfo = array.getJSONObject(0);
				
				//JSON => gameuser 객체화
				GameUser user = GameUser.parseTo(userInfo);
				logger.info(user.toString());

			}else {
				//로그인 실패
				logger.info("login failed");
				JOptionPane.showMessageDialog(null, "로그인이 실패하였습니다.");
			}
		}else if(e.getSource() == signupButton) {
			//회원가입 버튼 클릭
			SignupView signup = SignupView.newInstance();
			signup.setVisible(true);
		}
	}
	
	
}

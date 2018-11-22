package file.to.json;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Logger;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
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
	
	public static LoginView newInstance(String title) {
		LoginView instance = new LoginView(title);
		return instance;
	}
	public LoginView(String title) {
		this.setTitle(title);
		this.setBounds(100, 100, 600, 400);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.getContentPane().setLayout(new GridLayout(3,1));
		
		init();
	}
	private void init() {
		JPanel idPanel = new JPanel(new GridLayout(1, 2));
		JLabel labelID = new JLabel("id:");
		idText = new JTextField(8);
		labelID.setHorizontalAlignment(JTextField.CENTER);
		
		idPanel.add(labelID);
		idPanel.add(idText);
		
		JPanel pwdPanel = new JPanel(new GridLayout(1, 2));
		JLabel labelPWD = new JLabel("pwd:");
		pwdText = new JTextField(8);
		labelPWD.setHorizontalAlignment(JTextField.CENTER);
		pwdPanel.add(labelPWD);
		pwdPanel.add(pwdText);
		
		JPanel buttonPanel = new JPanel(new GridLayout(1, 2));
		loginButton = new JButton("로그인");
		signupButton = new JButton("회원가입");
		buttonPanel.add(loginButton);
		buttonPanel.add(signupButton);
		
		getContentPane().add(idPanel);
		getContentPane().add(pwdPanel);
		getContentPane().add(buttonPanel);
		
		loginButton.addActionListener(this);
		signupButton.addActionListener(this);
		
		validate();
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource() == loginButton) {
			StringBuilder builder = new StringBuilder();
			builder.append("http://game.bacoder.kr/login.jsp")
				.append("?login=").append(idText.getText())
				.append("&pwd=").append(pwdText.getText());
			String result = GetStringUtil.getStringFromUrl(builder.toString());
			JSONObject json = new JSONObject(result);
			if(json.getJSONArray("list").length() == 1) {
				JSONArray array = json.getJSONArray("list");
				JSONObject userInfo = array.getJSONObject(0);
				GameUser user = GameUser.parseTo(userInfo);
				logger.info(user.toString());
			}else {
				logger.info("login failed");
			}
		}else if(e.getSource() == signupButton) {
			SignupView signup = SignupView.newInstance();
			signup.setVisible(true);
		}
	}
}

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

import org.json.JSONObject;

import file.to.json.util.GetStringUtil;

public class SignupView extends JFrame implements ActionListener{
	Logger logger = Logger.getLogger(SignupView.class.getSimpleName());
	/**
	 * 
	 */
	private static final long serialVersionUID = -6872372668544559934L;
	
	JTextField loginInput;
	JTextField pwdInput;
	JTextField userNameInput;
	
	public static SignupView newInstance() {
		return new SignupView();
	}
	
	public SignupView() {
		this.setTitle("회원가입");
		this.setBounds(100, 100, 600, 400);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.getContentPane().setLayout(new GridLayout(5,1));
		init();
		validate();
	}
	
	private void init() {
		JPanel loginPanel = new JPanel(new GridLayout(1, 2));
		JLabel login = new JLabel("id:");
		loginInput = new JTextField(8);
		loginPanel.add(login);
		loginPanel.add(loginInput);
		
		JPanel pwdPanel = new JPanel(new GridLayout(1, 2));
		JLabel pwd = new JLabel("pwd:");
		pwdInput = new JTextField(8);
		pwdPanel.add(pwd);
		pwdPanel.add(pwdInput);
		
		JPanel usernamePanel = new JPanel(new GridLayout(1, 2));
		JLabel username = new JLabel("name:");
		userNameInput = new JTextField(8);
		usernamePanel.add(username);
		usernamePanel.add(userNameInput);
		
		JPanel buttonPanel = new JPanel();
		JButton signupButton = new JButton("회원가입");
		signupButton.addActionListener(this);
		buttonPanel.add(signupButton);
		
		this.getContentPane().add(loginPanel,0);
		this.getContentPane().add(pwdPanel,1);
		this.getContentPane().add(usernamePanel,2);
		this.getContentPane().add(buttonPanel,3);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		StringBuilder builder = new StringBuilder();
		builder.append("http://35.189.134.45/DictionaryGameServer/signup.jsp")
		.append("?login=").append(loginInput.getText())
		.append("&pwd=").append(pwdInput.getText())
		.append("&username=").append(userNameInput.getText());
		String result = GetStringUtil.getStringFromUrl(builder.toString());
		JSONObject json = new JSONObject(result);
		if(json.getInt("result") > 0) {
			logger.info("회원가입 성공");
		}else {
			logger.info("회원가입 실패");
		}
	}
}

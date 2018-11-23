package file.to.json;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.logging.Logger;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import org.json.JSONArray;
import org.json.JSONObject;

import file.to.bean.GameUser;
import file.to.json.util.GetStringUtil;

public class Makelogin implements ActionListener {
	Logger logger = Logger.getLogger(getClass().getSimpleName()); 
	JPanel jp = new JPanel();
	JLabel jl = new JLabel("끝말잇기");

	JTextField id = new HintTextField("아이디");
	JTextField pw = new JPasswordField("비밀번호");
	
	
	
	JLabel result;
	JButton btnlogin = new JButton("로그인");
	JButton btnjoin = new JButton("회원가입");
	JButton btnvoca = new JButton("단어장");

	public Makelogin(){
		JFrame frame = new JFrame("MAIN");

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		frame.setSize(500, 400);
		frame.setVisible(true); 
		frame.getContentPane().setLayout(new GridLayout(4,1));
		frame.getContentPane().add(jl);
		//frame.getContentPane().add(id);
		//frame.getContentPane().add(pw);
		
		final int hGap = 10;
		final int vGap = 20;

		JPanel idPanel = new JPanel(new BorderLayout(40,10));
		idPanel.setBorder(
				BorderFactory.createEmptyBorder(hGap, vGap, hGap,vGap));

		idPanel.add(id,BorderLayout.CENTER);
		frame.getContentPane().add(idPanel);

		JPanel pwPanel = new JPanel(new BorderLayout(40,10));
		pwPanel.add(pw,BorderLayout.CENTER);
		pwPanel.setBorder(BorderFactory.createEmptyBorder(hGap, vGap, hGap, vGap));
		frame.getContentPane().add(pwPanel);

		JPanel btnPanel = new JPanel(new BorderLayout(40,10));
		btnPanel.setBorder(BorderFactory.createEmptyBorder(hGap, vGap, hGap, vGap));
		JPanel btnCenterPanel = new JPanel(new GridLayout(1,3,10,0));

		btnCenterPanel.add(btnlogin);
		btnCenterPanel.add(btnjoin);
		btnCenterPanel.add(btnvoca);
		btnPanel.add(btnCenterPanel,BorderLayout.CENTER);
		frame.getContentPane().add(btnPanel);




		jl.setFont(new Font(null, Font.BOLD, 25));
		jl.setHorizontalAlignment(JLabel.CENTER);

		
		//btnlogin.setBounds(140, 270, 100, 40);
		//btnjoin.setBounds(250, 270, 100, 40);
		//btnvoca.setBounds(380, 320, 100, 20);

		//id.setBounds(200, 140, 100, 50);
		//pw.setBounds(200, 210, 100, 50);

		//jp.add(btnlogin);
		//jp.add(id); // jp라는 패널에 tf라는 텍스트필드 추가
		//jp.add(btnjoin);
		//jp.add(pw); 
		//jp.add(btnvoca);
		//jp.add(jl); 

		//frame.add(jp);
		btnlogin.addActionListener(this);
		btnjoin.addActionListener(this);
		pw.addActionListener(this);
		

		frame.validate();
	}
	

	@Override
	public void actionPerformed(ActionEvent evt) {
		if(evt.getSource() == btnlogin) {
			GetStringUtil util = new GetStringUtil();	
			StringBuilder builder = new StringBuilder();
			builder.append("http://game.bacoder.kr/login.jsp")
			.append("?login=").append(id.getText())
			.append("&pwd=").append(pw.getText());


			String result = GetStringUtil.getStringFromUrl(builder.toString());
			JSONObject json = new JSONObject(result);
			if(json.getJSONArray("list").length() == 1) {
				JSONArray array = json.getJSONArray("list");
				JSONObject userInfo = array.getJSONObject(0);
				GameUser user = GameUser.parseTo(userInfo);
				logger.info(user.toString());
				//게임 frame 시작
			}else {
				logger.info("login failed");
			}
		}else if(evt.getSource() == btnjoin) {
			new MemberRegister().setVisible(true);;
		}
		
	}
	class HintTextField extends JTextField implements FocusListener {

		private final String hint;
		private boolean showingHint;

		public HintTextField(final String hint) {
			
			super(hint);
			this.hint = hint;
			this.showingHint = true;
			super.addFocusListener(this);
		}

		@Override
		public void focusGained(FocusEvent e) {
			logger.info("focus gained"+this.getText());
			if(this.getText().isEmpty()) {
				super.setText("");
				showingHint = false;
			}
		}
		@Override
		public void focusLost(FocusEvent e) {
			if(this.getText().isEmpty()) {
				super.setText(hint);
				showingHint = true;
			}
		}
		@Override
		public String getText() {
			return showingHint ? "" : super.getText();
		}

	}
}

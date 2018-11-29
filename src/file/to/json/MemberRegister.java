package file.to.json;


import java.awt.CheckboxGroup;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.logging.Logger;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import org.json.JSONObject;

import file.to.json.util.GetStringUtil;

public class MemberRegister extends JFrame implements ActionListener, DocumentListener{
	Logger logger = Logger.getLogger(getClass().getSimpleName());

	boolean PassWordSame = false;
	
	private JTextField txtId;
	private JTextField txtName;
	private JTextField txtmail;
	private JTextField txtpwCom;
//	private JTextField txtIdcom;
	private JTextField txtAge;
	
	private JPasswordField  txtPwd;
	private JPasswordField txtPwd2;
	
	private JButton btnSubmit;
	private JButton btnCancel;
	private JButton btnrepeat;
	
	public MemberRegister() {
		setTitle("회원가입");
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setLayout(null);
		setSize(470, 479);

		JLabel lblId =new JLabel("아이디");
		JLabel lblPwd=new JLabel("패스워드");
		JLabel lblPwd2 = new JLabel("패스워드 다시입력");
		JLabel lblName=new JLabel("이름");
		JLabel lblAge =new JLabel("나이");
		JLabel IbImail =new JLabel("메일");
		lblId.setBounds(20, 50, 100, 20);
		lblPwd.setBounds(20, 80, 100, 20);
		lblPwd2.setBounds(20, 110, 100, 20);
		lblName.setBounds(20, 140, 100, 20);
		lblAge.setBounds(20, 170, 100, 20);
		IbImail.setBounds(20, 240, 100, 20);
		add(lblId);
		add(lblPwd);
		add(lblName);
		add(lblAge);
		add(lblPwd2);
		add(IbImail);
		
		btnrepeat = new JButton("중복확인");
		add(btnrepeat);
		
//		txtIdcom = new JTextField(10);
		txtpwCom = new JTextField(10); 
		txtId =new JTextField(20);
		txtPwd =new JPasswordField(20);
		txtPwd2 =new JPasswordField(20);
		txtName= new JTextField(20);
		txtmail= new JTextField(20);
		txtPwd2.getDocument().addDocumentListener(this);
		txtAge = new JTextField();
		
		btnCancel = new JButton("취소");
		
		JPanel panGen = new JPanel(new FlowLayout(FlowLayout.LEFT));
		panGen.setBounds(123, 207, 100, 30); 


		btnCancel.addActionListener(this);   
		btnSubmit = new JButton("가입완료");
		btnSubmit.addActionListener(this);
		btnrepeat.addActionListener(this);

		//btnCancel.setSize(150, 40 );
		//btnSubmit.setSize(150, 40);
		btnrepeat.setBounds(290, 50, 90, 20);

		txtId.setBounds(130, 50, 150, 20);
		txtPwd.setBounds(130, 80, 150, 20);
		txtPwd2.setBounds(130, 110, 150, 20);
		txtName.setBounds(130, 140, 150, 20);
		txtAge.setBounds(130, 170, 150, 20);
		txtmail.setBounds(130, 237, 150, 20);
		txtpwCom.setBounds(290,110,90,20);
//		txtIdcom.setBounds(290,80,100,20);
		JPanel paButton = new JPanel();
		paButton.add(btnSubmit);
		paButton.add(btnCancel);
		paButton.setBounds(0, 320, 370, 370);

		add(txtId);
		add(txtPwd);
		add(txtPwd2);
		add(txtName);
		add(panGen);
		add(txtAge);
		add(paButton);
		add(txtmail);
		add(txtpwCom);
//		add(txtIdcom);

	}
	private String getPasswordText(){
		StringBuilder builder = new StringBuilder();
		if(txtPwd != null) {
			char []jpwText=txtPwd.getPassword();
			for(char pwd : jpwText) {
				builder.append(pwd);
			}
		}
		return builder.toString();

	}
	@Override
	public void actionPerformed(ActionEvent evt) {
		if(evt.getSource() == btnSubmit) {
			if( PassWordSame) {
				//회원가입 URL
				StringBuilder builder = new StringBuilder();
				builder.append("http://game.bacoder.kr/signup.jsp?");
				builder.append("&login=").append(txtId.getText());
				builder.append("&pwd=").append(getPasswordText());
				builder.append("&username=").append(txtName.getText());
				builder.append("&age=").append(txtAge.getText());
				builder.append("&email=").append(txtmail.getText());
				logger.info(builder.toString());
				
				//회원가입 결과값
				final String returnStr = GetStringUtil.getStringFromUrl(builder.toString());
				logger.info("returnStr: "+ returnStr);
				
				JSONObject json = new JSONObject(returnStr.trim());
				logger.info("result : ");
				if(json.getInt("result") ==1) {
					//회원가입 성공
				}else {
					//회원가입 실패
				}
			}
			
		}
		else if (evt.getSource() == btnrepeat) {
			//존재하는 아이디인지 판별하는 URL
			StringBuilder builder = new StringBuilder();
			builder.append("http://game.bacoder.kr/existId.jsp?")
			.append("&login=").append(txtId.getText());
			
			//존재하는 아이디인지 아닌지에 대한 결과값
			final String returnStr = GetStringUtil.getStringFromUrl(builder.toString());
			logger.info("returnStr:" + returnStr);
			//결과값을 JSON으로 변환
			JSONObject json = new JSONObject(returnStr.trim());
			int resultId = json.getInt("result");
			if(resultId >0) {
				logger.info("이미 존재하는 아이디입니다");
		//		txtIdcom.setText("중복된 아이디");
				JOptionPane.showMessageDialog(null,"이미 존재하는 아이디입니다");
			}else {
				logger.info("사용하셔도 됩니다.");
		//		txtIdcom.setText("사용 가능");
				
			}
		}
		else if (evt.getSource() == btnCancel) {
			//창을 닫아준다.
			dispose();
		}
	}
	public boolean isValidInfo() {
		boolean result = false;
		if(txtPwd != null && txtPwd2 !=null) {

			char []jpwText=txtPwd.getPassword();//get the char array of password and convert to string represenation 
			char []jpw2Text=txtPwd2.getPassword(); 
			result = Arrays.equals(jpwText, jpw2Text);
		}
		return result;
	}
	@Override
	public void changedUpdate(DocumentEvent e) {
	}
	@Override
	public void insertUpdate(DocumentEvent e) {
		char []jpwText=txtPwd.getPassword();//get the char array of password and convert to string represenation 
		char []jpw2Text=txtPwd2.getPassword(); 
		if (Arrays.equals(jpwText, jpw2Text)) {
			logger.info("same");
			PassWordSame = true;
			txtpwCom.setText("같습니다."); 
		}
		else {
			logger.info("다름니다.");
			PassWordSame = false;
			txtpwCom.setText("다름니다."); 
		}
		 
	}
	@Override
	public void removeUpdate(DocumentEvent e) {
	}
}


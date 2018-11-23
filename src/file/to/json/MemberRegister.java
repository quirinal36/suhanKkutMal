package file.to.json;


import java.awt.CheckboxGroup;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.logging.Logger;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import org.json.JSONObject;


import file.to.json.util.GetStringUtil;
import javafx.scene.control.TextInputDialog;

public class MemberRegister extends JFrame implements ActionListener{
	Logger logger = Logger.getLogger(getClass().getSimpleName());

	JLabel lblId, lblPwd, lblPwd2, lblName,lblAge,IbImail ;
	JTextField txtId, txtName,txtmail,txtpwCom;
	JPasswordField  txtPwd,txtPwd2;
	JSONObject obj = new JSONObject();
	boolean PassWordSame = false;
	
	JTextField txtAge;
	JButton btnSubmit, btnCancel;
	JButton btnrepeat = new JButton("중복확인");
	public MemberRegister() {
		super("회원가입");
		setTitle("회원가입");
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

		setLayout(null);


		lblId =new JLabel("아이디");
		lblPwd=new JLabel("패스워드");
		lblPwd2 = new JLabel("패스워드 다시입력");
		lblName=new JLabel("이름");
		lblAge =new JLabel("나이");
		IbImail =new JLabel("메일");

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
		add(btnrepeat);
		
		txtpwCom = new JTextField(10); 
		txtId =new JTextField(20);
		txtPwd =new JPasswordField(20);
		txtPwd2 =new JPasswordField(20);
		txtPwd2.getDocument().addDocumentListener(new DocumentListener() {
			
			@Override
			public void removeUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void insertUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub
				char []jpwText=txtPwd.getPassword();//get the char array of password and convert to string represenation 
				char []jpw2Text=txtPwd2.getPassword(); 
				if (Arrays.equals(jpwText, jpw2Text)) {
					logger.info("same");
					PassWordSame = true;
					
				}
				else if(Arrays.equals(jpwText, jpw2Text)) {
					JTextField txtpwCom  = new JTextField("중복입니다."); 
				}else {
					logger.info("다름니다.");
					PassWordSame = false;
				}
			}
			
			@Override
			public void changedUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		txtName= new JTextField(20);
		txtmail= new JTextField(20);

		String jpwText=Arrays.toString(txtPwd.getPassword());//get the char array of password and convert to string represenation 
		String jpw2Text=Arrays.toString(txtPwd2.getPassword()); 

		JLabel lblhipen1 = new JLabel("-");
		JLabel lblhipen2 = new JLabel("-");

		JPanel panGen = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JPanel panHobby = new JPanel(new FlowLayout(FlowLayout.LEFT));

		CheckboxGroup group = new CheckboxGroup();
		//          cbMale = new JCheckbox("남자",group,true);
		//          cbFeMale = new JCheckbox("여자",group,false);
		//         
		//          panGen.add(cbMale);
		//          panGen.add(cbFeMale);
		panGen.setBounds(123, 207, 100, 30);         



		txtAge = new JTextField();

		btnCancel = new JButton("취소");   
		btnCancel.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				dispose();
			}
		}
				);
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

		setSize(450, 450);

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
				StringBuilder builder = new StringBuilder();
				builder.append("http://game.bacoder.kr/signup.jsp?");
				builder.append("&login=").append(txtId.getText());
				builder.append("&pwd=").append(getPasswordText());
				builder.append("&username=").append(txtName.getText());
				builder.append("&age=").append(txtAge.getText());
				builder.append("&email=").append(txtmail.getText());

				logger.info(builder.toString());
				final String returnStr = GetStringUtil.getStringFromUrl(builder.toString());

				logger.info("returnStr: "+ returnStr);
			}
			
		}
		else if (evt.getSource() == btnrepeat) {
			StringBuilder builder = new StringBuilder();
			builder.append("http://game.bacoder.kr/existId.jsp?")
			.append("&login=").append(txtId.getText());
			final String returnStr = GetStringUtil.getStringFromUrl(builder.toString());
			logger.info("returnStr:" + returnStr);
			JSONObject json = new JSONObject(returnStr.trim());
			int resultId = json.getInt("result");
			if(resultId >0) {
				logger.info("이미 존재하는 아이디입니다");
			}else {
				logger.info("사용하셔도 됩니다.");
			}
		}
		else if (evt.getSource() == btnCancel) {
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
}


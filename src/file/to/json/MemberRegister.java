package file.to.json;


import java.awt.CheckboxGroup;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class MemberRegister extends JFrame{
    
     JLabel lblId, lblPwd, lblPwd2, lblName, lblGen,lblAge,IbImail ;
     JTextField txtId, txtPwd,txtPwd2, txtName,txtmail;
    
     JCheckBox cbMale,cbFeMale;
     JTextField txtAge;
     JButton btnSubmit, btnCancel;
     JButton repeat = new JButton("중복확인");
     public MemberRegister() {
    	 super("회원가입");
    	 
    	 setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
          
          setLayout(null);
          
          
          lblId =new JLabel("아이디");
          lblPwd=new JLabel("패스워드");
          lblPwd2 = new JLabel("패스워드 다시입력");
          lblName=new JLabel("이름");
          lblGen=new JLabel("성별");
          lblAge =new JLabel("나이");
          IbImail =new JLabel("메일");
         
          lblId.setBounds(20, 50, 100, 20);
          lblPwd.setBounds(20, 80, 100, 20);
          lblPwd2.setBounds(20, 110, 100, 20);
          lblName.setBounds(20, 140, 100, 20);
          lblGen.setBounds(20, 210, 100, 20);
          lblAge.setBounds(20, 170, 100, 20);
          IbImail.setBounds(20, 240, 100, 20);
        
          
          add(lblId);
          add(lblPwd);
          add(lblName);
          add(lblGen);
          add(lblAge);
          add(lblPwd2);
          add(IbImail);
          add(repeat);
         
          txtId =new JTextField(20);
          txtPwd =new JTextField(20);
          txtPwd2 =new JTextField(20);
          txtName= new JTextField(20);
          txtmail= new JTextField(20);
          
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
          btnSubmit = new JButton("가입완료");
          
          //btnCancel.setSize(150, 40 );
          //btnSubmit.setSize(150, 40);
          repeat.setBounds(290, 50, 90, 20);
         
          txtId.setBounds(130, 50, 150, 20);
          txtPwd.setBounds(130, 80, 150, 20);
          txtPwd2.setBounds(130, 110, 150, 20);
          txtName.setBounds(130, 140, 150, 20);
          txtAge.setBounds(130, 170, 150, 20);
          txtmail.setBounds(130, 237, 150, 20);
         
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
          
          setSize(370, 370);
          setResizable(false);
          setVisible(true);
         
     }
    
     public static void main(String[] args) {
          new MemberRegister();
     }   
}

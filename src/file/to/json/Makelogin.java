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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import org.json.JSONArray;
import org.json.JSONObject;

import file.to.bean.GameUser;
import file.to.json.util.GetStringUtil;
/**
 * 로그인 화면 구성
 * @author suhan
 *
 */

public class Makelogin extends JFrame implements ActionListener {
	Logger logger = Logger.getLogger(getClass().getSimpleName()); 
	//상단 제목
	private JLabel jl;
	//아이디 입력
	private JTextField id;
	//비밀번호 입력
	private JTextField pw;
	
	//로그인 버튼
	private JButton btnlogin;
	//회원가입 버튼
	private JButton btnjoin;
	//단어장 버튼
	private JButton btnvoca;

	/**
	 * 생성자
	 */
	public Makelogin(){
		jl = new JLabel("끝말잇기");
		id = new HintTextField("아이디"); 
		pw = new JPasswordField();
		btnlogin = new JButton("로그인");
		btnjoin = new JButton("회원가입");
		btnvoca = new JButton("단어장");
		
		setTitle("로그인");
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		setSize(500, 400);
		setVisible(true); 
		getContentPane().setLayout(new GridLayout(4,1));
		getContentPane().add(jl);
		
		init();
	}
	/**
	 * 레이아웃 초기화
	 */
	public void init() {
		final int hGap = 10;
		final int vGap = 20;

		JPanel idPanel = new JPanel(new BorderLayout(40,10));
		idPanel.setBorder(
				BorderFactory.createEmptyBorder(hGap, vGap, hGap,vGap));

		idPanel.add(id,BorderLayout.CENTER);
		getContentPane().add(idPanel);

		JPanel pwPanel = new JPanel(new BorderLayout(40,10));
		pwPanel.add(pw,BorderLayout.CENTER);
		pwPanel.setBorder(BorderFactory.createEmptyBorder(hGap, vGap, hGap, vGap));
		getContentPane().add(pwPanel);

		JPanel btnPanel = new JPanel(new BorderLayout(40,10));
		btnPanel.setBorder(BorderFactory.createEmptyBorder(hGap, vGap, hGap, vGap));
		JPanel btnCenterPanel = new JPanel(new GridLayout(1,3,10,0));

		btnCenterPanel.add(btnlogin);
		btnCenterPanel.add(btnjoin);
		btnCenterPanel.add(btnvoca);
		btnPanel.add(btnCenterPanel,BorderLayout.CENTER);
		getContentPane().add(btnPanel);

		jl.setFont(new Font(null, Font.BOLD, 25));
		jl.setHorizontalAlignment(JLabel.CENTER);

		btnlogin.addActionListener(this);
		btnjoin.addActionListener(this);
		pw.addActionListener(this);
		
		validate();
	}
	
	@Override
	public void actionPerformed(ActionEvent evt) {
		if(evt.getSource() == btnlogin) {
			//로그인 URL 만들기
			StringBuilder builder = new StringBuilder();
			builder.append("http://35.189.134.45/DictionaryGameServer/login.jsp")
					.append("?login=").append(id.getText())
					.append("&pwd=").append(pw.getText());

			//로그인 결과값 가져오기
			String result = GetStringUtil.getStringFromUrl(builder.toString());
			JSONObject json = new JSONObject(result);
			//login과 pwd를 통해 찾은 사용자가 1명 있을때 
			if(json.getJSONArray("list").length() == 1) {
				JSONArray array = json.getJSONArray("list");
				JSONObject userInfo = array.getJSONObject(0);
				GameUser user = GameUser.parseTo(userInfo);
				logger.info(user.toString());
				
				//현재 창을 숨기기
				dispose();
				//게임 frame 시작
				new Gameview().setVisible(true);
			}else {
				//로그인 실패
				logger.info("login failed");
				JOptionPane.showMessageDialog(null, "로그인이 실패하였습니다.");
			}
		}else if(evt.getSource() == btnjoin) {
			//회원 가입 버튼 클릭 했을때
			new MemberRegister().setVisible(true);;
		}
		
	}
	/**
	 * 클릭 했을 때 힌트가 없어지는 텍스트 뷰
	 * @author suhan
	 *
	 */
	class HintTextField extends JTextField implements FocusListener {

		private final String hint;
		private boolean showingHint;
		/**
		 * 생성자
		 * 
		 * @param hint : 입력된 힌트가 저장된다.
		 */
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

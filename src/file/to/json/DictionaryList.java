package file.to.json;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GraphicsConfiguration;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Logger;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class DictionaryList extends JFrame implements DocumentListener, ActionListener {
	Logger logger = Logger.getLogger(getClass().getSimpleName());
	private List<String> data;
	private JTextField textField;
//	JButton bntSeartchButton;
	JButton oButton;
	JButton nButton;
	JList<String> list;
	boolean VocaLsit = false;

	public DictionaryList(GraphicsConfiguration gc) {
		super(gc);
		// TODO Auto-generated constructor stub
	}

	public DictionaryList(String title, GraphicsConfiguration gc) {
		super(title, gc);
		// TODO Auto-generated constructor stub
	}

	public DictionaryList(String title) throws HeadlessException {
		super(title);
		setTitle(title);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		setSize(500, 400);

		FileUtil fileutil= new FileUtil();

		data = fileutil.getvoca();
		JScrollPane scrollpane = new JScrollPane();
		getContentPane().add(scrollpane, BorderLayout.CENTER);


		JPanel panel = new JPanel();
		scrollpane.setColumnHeaderView(panel);

		textField = new JTextField(10);
		textField.getDocument().addDocumentListener(this);
		panel.add(textField);
		
		textField.setColumns(10);

//		bntSeartchButton = new JButton("검색");
//		bntSeartchButton.addActionListener(this);
//		panel.add(bntSeartchButton);
		
		oButton = new JButton("오름차순");
		oButton.addActionListener(this);
		panel.add(oButton);

		nButton = new JButton("내림차순");
		nButton.addActionListener(this);
		panel.add(nButton);
		
		
		DefaultListModel<String> listmodel = new DefaultListModel<>();
		for(String item : data) {
			listmodel.addElement(item);
		}
		list = new JList<String>();
		list.setFont(new Font(null, Font.BOLD,20));
		scrollpane.setViewportView(list);
		list.setModel(listmodel);

	}
/*
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == bntSeartchButton) {
			System.out.println(textField.getText());
			filterModel((DefaultListModel<String>)list.getModel(), textField.getText());
			
		}
	}
	*/

	public void filterModel(DefaultListModel<String>model, String filiter) {
		Iterator<String> iter = data.iterator();
		while(iter.hasNext()) {
			String item = iter.next();
			if(item .contains(filiter)) {
				if(!model.contains(item)) {
					model.addElement(item);
					
				}
			}else {
				if(model.contains(item)) {
					model.removeElement(item);
				}
			}
		}
	}
	
	@Override
	public void insertUpdate(DocumentEvent e) {
		String txtfiled= textField.getText();
		
			filterModel((DefaultListModel<String>)list.getModel(), textField.getText());
		
		 
	}
	
	
	@Override
	public void changedUpdate(DocumentEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeUpdate(DocumentEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == oButton) {
			Collections.sort(data);
			DefaultListModel<String> listModel = (DefaultListModel<String>)list.getModel();
			listModel.removeAllElements();
			for(String item : data) {
				listModel.addElement(item);
			}
		}
		else if(e.getSource() == nButton){
			Collections.sort(data,new DescendingString());
			DefaultListModel<String> listModel = (DefaultListModel<String>)list.getModel();
			listModel.removeAllElements();
			for(String item : data) {
				listModel.addElement(item);
			}
		}
	}
	
	class DescendingString implements Comparator<String>{
		@Override
		public int compare(String a, String b) {
			return b.compareToIgnoreCase(a);
		}
	}
	
}




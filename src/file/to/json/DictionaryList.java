package file.to.json;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GraphicsConfiguration;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

public class DictionaryList extends JFrame implements ActionListener{
	private List<String> data;
	JTextField textField;
	JButton bntSeartchButton;
	JList<String> list;


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

		textField = new JTextField();
		panel.add(textField);
		textField.setColumns(10);

		bntSeartchButton = new JButton("검색");
		bntSeartchButton.addActionListener(this);
		panel.add(bntSeartchButton);

		DefaultListModel<String> listmodel = new DefaultListModel<>();
		for(String item : data) {
			listmodel.addElement(item);
		}
		list = new JList<String>();
		list.setFont(new Font(null, Font.BOLD,20));
		scrollpane.setViewportView(list);
		list.setModel(listmodel);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == bntSeartchButton) {
			System.out.println(textField.getText());
			filterModel((DefaultListModel<String>)list.getModel(), textField.getText());
		}
	}
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
}



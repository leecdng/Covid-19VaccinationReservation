package admin.rsv;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import database.CenterDAO;
import database.CenterVO;
import database.RsvDAO;
import database.RsvVO;
import database.TestDAO;
import database.TestVO;
import user.home.SetStyle;
import user.searchcenter.SearchCenterCombo;

public class ManageTest extends JPanel implements ActionListener { // ���� // �˻� ���� ���� (�����ڿ�)
	private String loc1 =""; // ������ �Ҽ�
	
	// NORTH
	SearchCenterCombo search; // �˻�
	
	// CENTER
	JPanel pane = new JPanel(new BorderLayout());
		JScrollPane tbPane; // center
			JTable table = new JTable();
				DefaultTableModel model;
					String[] col = {"����� �ڵ�", "�õ�", "�ñ���", "����Ҹ�", "���೯¥", "����ð�", "������ ���̵�", "������ ����", "������ �ֹε�Ϲ�ȣ", "������ �޴�����ȣ"}; // �÷���
					int[] colSize = {50,40,100,100,100,60,90,60,100,100}; // �÷� �ּ� ������
		JPanel bottomPane = new JPanel(new BorderLayout()); // south
			JPanel btnPane = new JPanel();
				JPanel btnInnerPane = new JPanel(null);
					JButton[] btn = {new JButton("��ü���"), new JButton("���� ����")};
				
	// ��Ʈ // �÷�
	SetStyle st = new SetStyle();
	
	public ManageTest() {
		start();
	}
	
	
	public ManageTest(String loc1) {
		this.loc1 = loc1;
		start();
	}
	
	
	
	// ȭ�� ����
	public void start() {
		setLayout(new BorderLayout());
		
		// �� �����ϸ鼭 ���� �Ұ��ϰ� ����
		model = new DefaultTableModel(col, 0) {
			public boolean isCellEditable(int rowIndex, int mColIndex) {
				return false;
			}
		};
		
		setManageTestBtn(); // ��ü���/���� ��ư ����
		
		if(loc1==null || loc1.equals("")) {	// ���� ������ ȭ��
			search = new SearchCenterCombo(); // �˻�â ����
			setAllTestList();	// ��ü ����� ��� �ҷ�����
		} else {	// ���� ȭ��
			search = new SearchCenterCombo(loc1); // �˻�â ���� (�õ� �޺��ڽ� ����)
			setSearchList(); // �ش� �Ҽ� ���
		}
		
		
		// ���̺� ���� -- �÷� ������ ����
//		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF); // ������ �÷� ����� �ְ� ��ũ�� ����
		table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS); // ��ũ�� ���� �÷��� â ����� �°� �þ
		for(int i=0; i<col.length; i++) {
			table.getColumnModel().getColumn(i).setMinWidth(colSize[i]);
		}
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		
		tbPane = new JScrollPane(table);

		btnPane.setPreferredSize(new Dimension(500,75));
		bottomPane.setBorder(new EmptyBorder(15,0,0,0));
		bottomPane.add(btnPane, BorderLayout.SOUTH);
		
		search.setBorder(new EmptyBorder(0,0,15,0));
		pane.add(tbPane);
		pane.add(bottomPane, BorderLayout.SOUTH);
		pane.setBorder(new EmptyBorder(0,20,0,20));
		
		add(search, BorderLayout.NORTH);
		add(pane);
		
		// �̺�Ʈ ���
		search.btn.addActionListener(this);
		search.tf.addActionListener(this);
	}
	
	
	// �˻� ���� ���� --------- ��ü �˻� ���� ���
	public void setAllTestList(){
		TestDAO dao = new TestDAO();
		List<TestVO> list = dao.selectAllTestData();
		
		setTestList(list);
	}
	
	// ��� �ҷ����� // ��ü��� or �˻� ��� ���
	public void setTestList(List<TestVO> list) {
		model.setRowCount(0);

		for(int i=0; i<list.size(); i++) {
			TestVO vo = list.get(i);
			String user_num = vo.getUser_num().substring(0,6) + "-" + vo.getUser_num().substring(6,7).concat("******"); // �ֹε�Ϲ�ȣ 000000-0****** �������� �ٲٱ�
			String user_tel = vo.getUser_tel().substring(0,3) + "-" + vo.getUser_tel().substring(3,7) + "-" + vo.getUser_tel().substring(7); // �޴�����ȣ 000-0000-0000 �������� �ٲٱ�
			Object[] record = {vo.getCenter_code(), vo.getLoc1(), vo.getLoc2(), vo.getCenter_name(), vo.getRsv_date(), vo.getRsv_hour(), vo.getUser_id(), vo.getUser_name(), user_num, user_tel};
			model.addRow(record);
		}
		table.setModel(model);
		table.updateUI();
	}

	
	// ��ü���/���� ��ư ����
	public void setManageTestBtn() {
		for(int i=0; i<btn.length; i++) {
			btn[i].setFont(st.fnt1);
			btn[i].setBounds(165*i, 0, 150, 40);
			btn[i].setBackground(st.clr1);
			btn[i].setForeground(Color.WHITE);
			btn[i].setBorderPainted(false);
			btnInnerPane.add(btn[i]);
			
			btn[i].addActionListener(this);
		}
		btnInnerPane.setPreferredSize(new Dimension(315,75));
		btnPane.add(btnInnerPane);
	}
	
	
	// [����] ��ư �̺�Ʈ �߻� �� ------- ���� ���� ���� �˻� / ���� ������ ȸ�� ���̵� ����
	public void selectDelTest(){
		int row = table.getSelectedRow();
		if(row==-1) { // ���� ���õ��� �ʾ��� ��
			JOptionPane.showMessageDialog(this, "������ �˻� ���� ������ ������ �ּ���.");
		} else {
			int result = JOptionPane.showConfirmDialog(this, "���� �����Ͻðڽ��ϱ�?", "����", JOptionPane.OK_CANCEL_OPTION);
			if(result == JOptionPane.OK_OPTION) {
				String user_id = (String) model.getValueAt(row, 7); // ����� �ڵ� ������
				deleteTest(user_id);
			}
		}		
	}
	
	// �˻� ���� ���� ����
	public void deleteTest(String user_id) {
		TestDAO dao = new TestDAO();
		int cnt = dao.deleteTestData(user_id);
		if(cnt>0) {
			JOptionPane.showMessageDialog(this, "�ش� �˻� ���� ������ �����Ǿ����ϴ�.");
			setAllTestList();
		} else {
			JOptionPane.showMessageDialog(this, "�˻� ���� ���� ������ �����Ͽ����ϴ�. �ٽ� �õ��� �ּ���.");
		}
	}
	
	// �˻� ��ư ������ �� --- �˻� ��� ����ϰ�
	public void setSearchList() {
		String loc1 = (String) search.cb1.getSelectedItem();
		String loc2 = (String) search.cb2.getSelectedItem();
		String searchTxt = search.tf.getText();
		
		TestDAO dao = new TestDAO();
		List<TestVO> list;
		
		if(loc1.equals("�á���") && loc2.equals("�á�������")) { // 
			list = dao.getSearchTestData(searchTxt);
		} else if(!loc1.equals("�á���") && loc2.equals("�á�������")) { // 
			list = dao.getSearchTestData(loc1, searchTxt);
		} else {
			list = dao.getSearchTestData(loc1, loc2, searchTxt);
		}
		setTestList(list);  // �ش� ��� ���
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		Object evt = e.getSource();
		String evtStr= e.getActionCommand();
		if(evtStr.equals("��ü���")) {
			search.tf.setText("");			// �˻�â �ʱ�ȭ
			if(loc1==null || loc1.equals("")) { // ���� ������
				search.cb1.setSelectedIndex(0); // �õ� �޺��ڽ� �ʱ�ȭ
				setAllTestList(); // ��ü ���
			} else {							// ���� ������
				search.cb2.setSelectedIndex(0); // �ñ��� �޺��ڽ� �ʱ�ȭ
				setSearchList();	// �ش� �Ҽ� ��ü ���
			}
		} else if(evtStr.equals("���� ����")) {
			selectDelTest();
		} else if(evt == search.tf || evtStr.equals("�˻�")) {
			setSearchList();
		}
	}
}

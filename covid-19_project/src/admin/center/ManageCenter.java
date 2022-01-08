package admin.center;

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
import database.LocDAO;
import database.LocVO;
import database.VaccineDAO;
import database.VaccineVO;
import user.home.SetStyle;
import user.home.UserTabMenu;
import user.searchcenter.SearchCenterCombo;

public class ManageCenter extends JPanel implements ActionListener { // ���� // ����� ���� (�����ڿ�)
	private String loc1 ="";  // ������ �Ҽ�
	
	// NORTH
	SearchCenterCombo search; // �˻�
	
	// CENTER
	JPanel pane = new JPanel(new BorderLayout());
		JScrollPane tbPane; // center
			JTable table = new JTable();
				DefaultTableModel model;
					String[] col = {"����� �ڵ�", "����Ҹ�", "�õ�", "�ñ���", "���ּ�", "��ǥ ��ȭ��ȣ", "���� ��ð�", "����� ��ð�", "�Ͽ���/������ ��ð�"};
					int[] colSize = {100,200,100,100,300,100,100,100,100}; // �÷� �ּ� ������
		JPanel bottomPane = new JPanel(new BorderLayout()); // south
			JPanel btnPane = new JPanel();
				JPanel btnInnerPane = new JPanel(null);
					JButton[] btn = {new JButton("��ü���"), new JButton("�߰�"), new JButton("����"), new JButton("����")};
				
	// ��Ʈ // �÷�
	SetStyle st = new SetStyle();
	
	UpdateCenter uCenter;
	
	public ManageCenter() {
		start();
	}
	
	
	public ManageCenter(String loc1) {
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
		
		setManageCenterBtn(); // ��ü���/�߰�/����/���� ��ư ����
		
		if(loc1==null || loc1.equals("")) {	// ���� ������ ȭ��
			search = new SearchCenterCombo(); // �˻�â ����
			setAllCenterList();	// ��ü ����� ��� �ҷ�����
		} else {	// ���� ȭ��
			search = new SearchCenterCombo(loc1); // �˻�â ���� (�õ� �޺��ڽ� ����)
			setSearchList(); // �ش� �Ҽ� ���
		}
		
		// ���̺� ���� -- �÷� ������ ����
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
//		centerTb.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		for(int i=0; i<col.length; i++) {
			table.getColumnModel().getColumn(i).setMinWidth(colSize[i]);
		}
//		System.out.println(centerTb.getColumnModel().getColumn(0).getPreferredWidth());
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
	
	
	
	// ����� ���� --- ��ü ����� ��� �ҷ�����
	public void setAllCenterList() {
		CenterDAO dao = new CenterDAO();
		List<CenterVO> list = dao.selectAllCenterData();
		
		setCenterList(list);
	}
	
	
	// ��� �ҷ����� // ��ü��� or �˻� ��� ���
	public void setCenterList(List<CenterVO> list) {
		model.setRowCount(0);

		for(int i=0; i<list.size(); i++) {
			CenterVO vo = list.get(i);
			Object[] record = {vo.getCenter_code(), vo.getCenter_name(), vo.getLoc1(), vo.getLoc2(), vo.getCenter_addr(), vo.getCenter_tel(), vo.getCenter_time1(), vo.getCenter_time2(), vo.getCenter_time3()};
			model.addRow(record);
		}
		table.setModel(model);
		table.updateUI();
	}

	
	// ��ü���/�߰�/����/���� ��ư ����
	public void setManageCenterBtn() {
		for(int i=0; i<btn.length; i++) {
			btn[i].setFont(st.fnt1);
			btn[i].setBounds(165*i, 0, 150, 40);
			btn[i].setBackground(st.clr1);
			btn[i].setForeground(Color.WHITE);
			btn[i].setBorderPainted(false);
			btnInnerPane.add(btn[i]);
			
			btn[i].addActionListener(this);
		}
		btnInnerPane.setPreferredSize(new Dimension(645,75));
		btnPane.add(btnInnerPane);
	}
	
	// [����] ��ư �̺�Ʈ �߻� �� ���� ------- ����� ���� ���� �˻� / ���� ����� �ڵ� ����
	public void selectDelCenter(){
		int row = table.getSelectedRow();
		if(row==-1) { // ���� ���õ��� �ʾ��� ��
			JOptionPane.showMessageDialog(this, "������ ����Ҹ� ������ �ּ���.");
		} else {
			int result = JOptionPane.showConfirmDialog(this, "���� �����Ͻðڽ��ϱ�?", "����", JOptionPane.OK_CANCEL_OPTION);
			if(result == JOptionPane.OK_OPTION) {
				int center_code = (Integer) model.getValueAt(row, 0); // ����� �ڵ� ������
				deleteCenter(center_code);
			}
		}		
	}
	
	// ����� ����
	public void deleteCenter(int center_code) {
		CenterDAO dao = new CenterDAO();
		int cnt = dao.deleteCenterData(center_code);
		if(cnt>0) {
			JOptionPane.showMessageDialog(this, "�ش� ����Ұ� �����Ǿ����ϴ�.");
			setAllCenterList();
		} else {
			JOptionPane.showMessageDialog(this, "����� ������ �����Ͽ����ϴ�. �ٽ� �õ��� �ּ���.");
		}
	}

	
	// [����] ��ư �̺�Ʈ �߻� �� ���� ------- ����� ���� ���� �˻� / ���� ����� �ڵ� ����
	public void selectEditCenter(){
		int row = table.getSelectedRow();
		if(row==-1) { // ���� ���õ��� �ʾ��� ��
			JOptionPane.showMessageDialog(this, "������ ����Ҹ� ������ �ּ���.");
		} else {
			int center_code = (Integer) model.getValueAt(row, 0); // ����� �ڵ� ������
			uCenter = new UpdateCenter(center_code);
			
			UserTabMenu.centerpane.removeAll();
			UserTabMenu.centerpane.add(uCenter);
			UserTabMenu.centerpane.updateUI();
		}		
	}
	
	// �˻� ��ư ������ �� --- �˻� ��� ����ϰ�
	public void setSearchList() {
		String loc1 = (String) search.cb1.getSelectedItem();
		String loc2 = (String) search.cb2.getSelectedItem();
		String searchTxt = search.tf.getText();
		
		CenterDAO dao = new CenterDAO();
		List<CenterVO> list;
		
		if(loc1.equals("�á���") && loc2.equals("�á�������")) { // 
			list = dao.getSearchCenterData(searchTxt);
		} else if(!loc1.equals("�á���") && loc2.equals("�á�������")) { // 
			list = dao.getSearchCenterData(loc1, searchTxt);
		} else {
			list = dao.getSearchCenterData(loc1, loc2, searchTxt);
		}
		setCenterList(list);  // �ش� ��� ���
	}

	
	
	// --- ��ư Ŭ�� �̺�Ʈ
	public void actionPerformed(ActionEvent e) {
		Object evt = e.getSource();
		String evtStr= e.getActionCommand();
		if(evtStr.equals("��ü���")) {
			search.tf.setText("");			// �˻�â �ʱ�ȭ
			if(loc1==null || loc1.equals("")) { // ���� ������
				search.cb1.setSelectedIndex(0); // �õ� �޺��ڽ� �ʱ�ȭ
				setAllCenterList(); // ��ü ���
			} else {							// ���� ������
				search.cb2.setSelectedIndex(0); // �ñ��� �޺��ڽ� �ʱ�ȭ
				setSearchList();	// �ش� �Ҽ� ��ü ���
			}
		} else if(evtStr.equals("�߰�")) {
			uCenter  = new UpdateCenter();
			UserTabMenu.centerpane.removeAll();
			UserTabMenu.centerpane.add(uCenter);
			UserTabMenu.centerpane.updateUI();
		} else if(evtStr.equals("����")) {
			selectEditCenter(); // �� �޼��� �ȿ��� ȭ�� ��ȯ �ʿ�
		} else if(evtStr.equals("����")) {
			selectDelCenter();
		} else if(evt == search.tf || evtStr.equals("�˻�")) {
			setSearchList();
		}
	}
}

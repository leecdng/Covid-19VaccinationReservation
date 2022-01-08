package admin.center;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import database.VaccineDAO;
import database.VaccineVO;
import user.home.SetStyle;
import user.searchcenter.SearchCenterCombo;

public class ManageVacc extends JPanel implements ActionListener{ // ����	 // ��� ���� ���� (�����ڿ�)
	private String loc1 ="";  // ������ �Ҽ�
	
	// NORTH
	SearchCenterCombo search;
	
	// CENTER
	JPanel pane = new JPanel(new BorderLayout());
		JScrollPane tbPane; // center
			JTable table = new JTable();
				DefaultTableModel model;
					String[] col = {"����� �ڵ�", "�õ�", "�ñ���", "����Ҹ�", "��ǥ ��ȭ��ȣ", "�Ἶ", "�ƽ�Ʈ������ī", "ȭ����", "�����"};
					int[] colSize = {60,10,100,240,130,40,40,40,40}; // �÷� �ּ� ������
		JPanel bottomPane = new JPanel(new BorderLayout()); // south
			JPanel editPane = new JPanel();
				JPanel editInnerPane = new JPanel(null);
					JLabel[] vaccLbl = new JLabel[4];
						String[] vaccStr = {"�Ἶ","�ƽ�Ʈ������ī","ȭ����","�����"};
					JTextField[] vaccTf = new JTextField[4];
					JLabel[] minusLbl = new JLabel[4];
					JLabel[] plusLbl = new JLabel[4];
			JPanel btnPane = new JPanel();
				JPanel btnInnerPane = new JPanel(null);
					JButton[] btn = {new JButton("��ü���"), new JButton("���� �Ϸ�")};
				
	// ��Ʈ // �÷�
	SetStyle st = new SetStyle();
	
	
	// ���� �����ڿ�
	public ManageVacc() {
		start();
	}
	
	
	// ���� �����ڿ�
	public ManageVacc(String loc1) {
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
		
		setEditVaccArea(); // ���� �Է� �κ� ����
		setManageVaccBtn(); // ��ü���, �����Ϸ� ��ư ����
		
		if(loc1==null || loc1.equals("")) {	// ���� ������ ȭ��
			search = new SearchCenterCombo(); // �˻�â ����
			setAllVaccCountList(); // ��ü ���
		} else {	// ���� ȭ��
			search = new SearchCenterCombo(loc1); // �˻�â ���� (�õ� �޺��ڽ� ����)
			setSearchList(); // �ش� �Ҽ� ���
		}
		
		// ���̺� ���� -- �÷� ������ ����
		for(int i=0; i<col.length; i++) {
			table.getColumnModel().getColumn(i).setMinWidth(colSize[i]);
		}
		table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		
		tbPane = new JScrollPane(table);

		btnPane.setPreferredSize(new Dimension(500,75));
		bottomPane.setBorder(new EmptyBorder(15,0,0,0));
		bottomPane.add(editPane);
		bottomPane.add(btnPane, BorderLayout.SOUTH);
		
		search.setBorder(new EmptyBorder(0,0,15,0));
		pane.add(tbPane);
		pane.add(bottomPane, BorderLayout.SOUTH);
		pane.setBorder(new EmptyBorder(0,20,0,20));
		
		add(search, BorderLayout.NORTH);
		add(pane);
		
		// �̺�Ʈ ���
		table.addMouseListener(new MyMouseEvent());
		table.addKeyListener(new MyKeyEvent());
		search.btn.addActionListener(this);
		search.tf.addActionListener(this);
	}
	
	
	// ��Ű��� --- ��ü ��� ���� ��� �ҷ�����
	public void setAllVaccCountList() {		
		VaccineDAO dao = new VaccineDAO();
		List<VaccineVO> list = dao.getAllVaccData();;
		
		setVaccList(list);
	}
	
	// ��� �ҷ����� // ��ü��� or �˻� ��� ���
	public void setVaccList(List<VaccineVO> list) {
		model.setRowCount(0);
		
		for(int i=0; i<list.size(); i++) {
			VaccineVO vo = list.get(i);
			Object[] record = {vo.getCenter_code(), vo.getLoc1(), vo.getLoc2(), vo.getCenter_name(), vo.getCenter_tel(), vo.getJansen(), vo.getAz(), vo.getPfizer(), vo.getModerna()};
			model.addRow(record);
		}
		table.setModel(model);
		table.updateUI();
		
		for(int i=0; i<vaccTf.length; i++){ // ���� �Է� �ʱ�ȭ
			vaccTf[i].setText("");
		}
	}

	
	// ���� �Ϸ� ��ư ����
	public void setManageVaccBtn() {
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

	// ��� ���� ���� �ؽ�Ʈ�ʵ� + ��(-,+)
	public void setEditVaccArea() {
		for(int i=0; i<vaccLbl.length; i++) {
			vaccLbl[i] = new JLabel(vaccStr[i]);
			vaccLbl[i].setBounds(150*i, 0, 150, 20);
			vaccLbl[i].setFont(st.fnt2);
			vaccLbl[i].setHorizontalAlignment(JLabel.CENTER);
			editInnerPane.add(vaccLbl[i]);
			
			minusLbl[i] = new JLabel("-");
			minusLbl[i].setBounds(150*i+15, 30, 30, 35);
			minusLbl[i].setFont(st.fnt1);
			minusLbl[i].setHorizontalAlignment(JLabel.CENTER);
			editInnerPane.add(minusLbl[i]);
			minusLbl[i].addMouseListener(new MyMouseEvent());
			
			vaccTf[i] = new JTextField();
			vaccTf[i].setBounds(150*i+45, 30, 60, 35);
			vaccTf[i].setFont(st.fnt3);
			vaccTf[i].setHorizontalAlignment(JLabel.RIGHT);
			editInnerPane.add(vaccTf[i]);
			
			plusLbl[i] = new JLabel("+");
			plusLbl[i].setBounds(150*i+105, 30, 30, 35);
			plusLbl[i].setFont(st.fnt1);
			plusLbl[i].setHorizontalAlignment(JLabel.CENTER);
			editInnerPane.add(plusLbl[i]);
			plusLbl[i].addMouseListener(new MyMouseEvent());
		}
		editInnerPane.setPreferredSize(new Dimension(600,85));
		editPane.add(editInnerPane);
	}
	
	// ���̺��� ����� �ϳ� ����(Ŭ��)�ϸ� �߻�
	public void setVaccineCount() {
		int row = table.getSelectedRow();
		int jansenCnt = (Integer) model.getValueAt(row, 5);
		int azCnt = (Integer) model.getValueAt(row, 6);
		int pfizerCnt = (Integer) model.getValueAt(row, 7);
		int modernaCnt = (Integer) model.getValueAt(row, 8);
		
		vaccTf[0].setText(Integer.toString(jansenCnt));
		vaccTf[1].setText(Integer.toString(azCnt));
		vaccTf[2].setText(Integer.toString(pfizerCnt));
		vaccTf[3].setText(Integer.toString(modernaCnt));
	}
	
	// - �� Ŭ������ �� (��� ���� -1 �ϱ�)
	public void clickMinus(int i) {
		if(vaccTf[i].getText()==null || vaccTf[i].getText().equals("")) {
			JOptionPane.showMessageDialog(this, "����Ҹ� ������ �ּ���.");
		} else {
			int vaccCnt = Integer.parseInt(vaccTf[i].getText());
			if(vaccCnt>0) {
				vaccTf[i].setText(Integer.toString(vaccCnt-1));
			} else {
				JOptionPane.showMessageDialog(this, "0 �̻��� ���� �Է��� �ּ���.");
			}
		}
	}
	
	// + �� Ŭ������ �� (��� ���� +1 �ϱ�)
	public void clickPlus(int i) {
		if(vaccTf[i].getText()==null || vaccTf[i].getText().equals("")) {
			JOptionPane.showMessageDialog(this, "����Ҹ� ������ �ּ���.");
		} else {
			int vaccCnt = Integer.parseInt(vaccTf[i].getText());
			if(vaccCnt<=1000) {
				vaccTf[i].setText(Integer.toString(vaccCnt+1));
			} else {
				JOptionPane.showMessageDialog(this, "1000 ������ ���� �Է��� �ּ���.");
			}
		}
	}
	
	// ���� ��ư �̺�Ʈ �߻� �� ---------- ����� ���� ���� �˻� / ���� ����� �ڵ� ����
	public void clickModifyBtn() {
		int row = table.getSelectedRow();
		if(row==-1) { // ���� ���õ��� �ʾ��� ��
			JOptionPane.showMessageDialog(this, "��� ������ ������ ����Ҹ� ������ �ּ���.");
		} else {
			int result = JOptionPane.showConfirmDialog(this, "�����Ͻðڽ��ϱ�?", "��� ���� ����", JOptionPane.OK_CANCEL_OPTION);
			if(result==JOptionPane.OK_OPTION) {
				int center_code = (Integer) model.getValueAt(row, 0); // ����� �ڵ� ������
				updateVacc(center_code);
			}
		}
	}

	// ��� ������ ���� ����
	public void updateVacc(int center_code) {
		int jansenCnt = Integer.parseInt(vaccTf[0].getText());
		int azCnt = Integer.parseInt(vaccTf[1].getText());
		int pfizerCnt = Integer.parseInt(vaccTf[2].getText());
		int modernaCnt = Integer.parseInt(vaccTf[3].getText());
		
		VaccineVO vo = new VaccineVO();
		VaccineDAO dao = new VaccineDAO();
		vo.setCenter_code(center_code);
		vo.setJansen(jansenCnt);
		vo.setAz(azCnt);
		vo.setPfizer(pfizerCnt);
		vo.setModerna(modernaCnt);
		
		int cnt = dao.updateVaccData(vo);
		
		if(cnt>0) { // ���� ����
			JOptionPane.showMessageDialog(this, "�ش� ������� ��� ������ �����Ǿ����ϴ�.");
			setAllVaccCountList();
		} else { // ���� ����
			JOptionPane.showMessageDialog(this, "��� ���� ������ �����Ͽ����ϴ�. �ٽ� �õ��� �ּ���.");
		}
	}
	
	// �˻� ��ư ������ �� --- �˻� ��� ����ϰ�
	public void setSearchList() {
		String loc1 = (String) search.cb1.getSelectedItem();
		String loc2 = (String) search.cb2.getSelectedItem();
		String searchTxt = search.tf.getText();
		
		VaccineDAO dao = new VaccineDAO();
		List<VaccineVO> list;

		if(loc1.equals("�á���") && loc2.equals("�á�������")) { // 
			list = dao.getSearchVaccData(searchTxt);
		} else if(!loc1.equals("�á���") && loc2.equals("�á�������")) { // 
			list = dao.getSearchVaccData(loc1, searchTxt);
		} else {
			list = dao.getSearchVaccData(loc1, loc2, searchTxt);
		}
		setVaccList(list); // �ش� ��� ���
	}

	
	
	
	// --- ��ư Ŭ�� �̺�Ʈ
	public void actionPerformed(ActionEvent e) {
		Object evt = e.getSource();
		String evtStr= e.getActionCommand();
		if(evtStr.equals("��ü���")) {
			search.tf.setText("");			// �˻�â �ʱ�ȭ
			if(loc1==null || loc1.equals("")) { // ���� ������
				search.cb1.setSelectedIndex(0); // �õ� �޺��ڽ� �ʱ�ȭ
				setAllVaccCountList(); // ��ü ���
			} else {							// ���� ������
				search.cb2.setSelectedIndex(0); // �ñ��� �޺��ڽ� �ʱ�ȭ
				setSearchList();	// �ش� �Ҽ� ��ü ���
			}
		} else if(evtStr.equals("���� �Ϸ�")) {
			clickModifyBtn();
		} else if(evt == search.tf || evtStr.equals("�˻�")) {
			setSearchList();
		}
	}
	
	// --- ���̺� ���콺 �̺�Ʈ
	public class MyMouseEvent extends MouseAdapter{
		public MyMouseEvent() {}
		
		// ���̺� ���ڵ� �������� �� ---- Mouse �̺�Ʈ �߻�
		public void mouseReleased(MouseEvent e) {
			Object evt = e.getSource();
			if(evt instanceof JTable) {
				setVaccineCount();
			} else if(evt instanceof JLabel) {
				for(int i=0; i<minusLbl.length; i++) {
					if(evt == minusLbl[i]) clickMinus(i);
					else if(evt == plusLbl[i]) clickPlus(i);
				}
			}
		}
	}
	
	// --- ���̺� Ű �̺�Ʈ
	public class MyKeyEvent extends KeyAdapter{
		public MyKeyEvent() {}
		
		// ���̺� ���ڵ� �������� �� ---- Mouse �̺�Ʈ �߻�
		public void keyReleased(KeyEvent e) {
			Object evt = e.getSource();
			if(evt == table) setVaccineCount();
		}
	}
	
}

package admin.center;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Vector;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import database.CenterDAO;
import database.CenterVO;
import database.LocDAO;
import database.LocVO;
import database.VaccineDAO;
import user.home.SetStyle;
import user.home.UserTabMenu;

public class UpdateCenter extends JPanel implements FocusListener, ActionListener, ItemListener{ // ����
	/* ����� ��� �� �⺻ �����ڷ� ��ü ���� */
	/* ����� ���� �� ������(������ڵ�) ��ü ���� */
	
	JPanel centerPane = new JPanel(new BorderLayout());	// ��� ���� �г�
		// 1. �Է�â �г�
		JPanel inputPane = new JPanel(null);
			JLabel[] lbl = new JLabel[6];
				String[] colName = {"����Ҹ�", "�ּ�", "��ǥ ��ȭ��ȣ", "���� ��ð�", "����� ��ð�", "�Ͽ���/������ ��ð�"};
			JTextField[] tf = new JTextField[3];
			DefaultComboBoxModel<String> model1;
			JComboBox<String> loc1CB, loc2CB;
			JComboBox<String>[] timeCB = new JComboBox[12];
				String[] hour = new String[24];
				String[] minute = new String[12];
			JLabel[] timeLbl = new JLabel[15];
			JCheckBox[] timeChk = {new JCheckBox("�̿"), new JCheckBox("�̿")};
			
		// 2. ��ư �г�
		JPanel btnPane = new JPanel(null);			
			JButton cancleBtn = new JButton("���");
			JButton insertBtn = new JButton("���");

	// ��Ʈ // �÷�
	SetStyle st = new SetStyle();
	
	ManageCenter mCenter;

	private int center_code; // ���� �� ����� �ڵ� ���;� ��
	
	// ----- ����� ��� �� ���� -----
	public UpdateCenter() {		
		// �ּ� �޺��ڽ�(�õ�, �ñ���) �⺻ ����
		setDefaultLocCB();
		// ��ð� �޺��ڽ� �⺻ ����
		setDefaultTime();
	
		// 1. �Է�â ���� // ��, �ؽ�Ʈ�ʵ�
		for(int i=0; i<lbl.length; i++) {
			lbl[i] = new JLabel(colName[i]);
			lbl[i].setFont(st.fnt2);
			lbl[i].setHorizontalAlignment(JLabel.CENTER);
			// ���� �κ� ��ġ ����
			if(i<=1) {
				tf[i] = new JTextField();
				tf[i].setFont(st.fnt3);
				lbl[i].setBounds(0,38*i,170,30);
				if(i==0) {
					tf[i].setBounds(174,38*i,300,30);
				} else if(i==1){
					tf[i].setBounds(174,38*(i+1),300,30);
					loc1CB.setBounds(174,38*i,115,30);
					loc2CB.setBounds(294,38*i,180,30);
				}
				inputPane.add(tf[i]);
			} else if(i>=2) {
				lbl[i].setBounds(0,38*(i+1),170,30);
				if(i==2) {
					tf[i] = new JTextField();
					tf[i].setFont(st.fnt3);
					tf[i].setBounds(174,38*(i+1),300,30);
					inputPane.add(tf[i]);
				} else if(i>=3){
					timeCB[(i-3)*4+0].setBounds(174,38*(i+1),50,30); // 00(��)
					timeLbl[(i-3)*5+0].setBounds(224,38*(i+1),22,30); // ��
					timeCB[(i-3)*4+1].setBounds(246,38*(i+1),50,30); // 00(��)
					timeLbl[(i-3)*5+1].setBounds(296,38*(i+1),22,30); // ��
					
					timeLbl[(i-3)*5+4].setBounds(318,38*(i+1),16,30); // ~ // �󺧹迭 �ε��� 4,9,14��°
					
					timeCB[((i-3)*4)+2].setBounds(334,38*(i+1),50,30); // 00(��)
					timeLbl[(i-3)*5+2].setBounds(384,38*(i+1),22,30); // ��
					timeCB[(i-3)*4+3].setBounds(406,38*(i+1),50,30); // 00(��)
					timeLbl[(i-3)*5+3].setBounds(456,38*(i+1),22,30); // ��
					
					inputPane.add(timeCB[(i-3)*4+0]);
					inputPane.add(timeCB[(i-3)*4+1]);
					inputPane.add(timeCB[(i-3)*4+2]);
					inputPane.add(timeCB[(i-3)*4+3]);
					inputPane.add(timeLbl[(i-3)*5+0]);
					inputPane.add(timeLbl[(i-3)*5+1]);
					inputPane.add(timeLbl[(i-3)*5+2]);
					inputPane.add(timeLbl[(i-3)*5+3]);
					inputPane.add(timeLbl[(i-3)*5+4]);
					if(i>=4) {
						timeChk[i-4].setBounds(488,38*(i+1),80,30);
						timeChk[i-4].setFont(st.fnt3);
						inputPane.add(timeChk[i-4]);
					}
				}
			}
			inputPane.add(lbl[i]);
		}
		
		tf[1].setText("���ּ�"); // ���ּ� �۾� �̸� ����
		tf[1].setForeground(Color.GRAY);
		
		loc1CB.setFont(st.fnt3);
		loc2CB.setFont(st.fnt3);
		loc1CB.setBackground(Color.WHITE);
		loc2CB.setBackground(Color.WHITE);
		inputPane.add(loc1CB);
		inputPane.add(loc2CB);
		
		
		// 2. ��ư ����
		cancleBtn.setFont(st.fnt1);
		cancleBtn.setForeground(Color.WHITE);
		cancleBtn.setBackground(st.clr1);
		cancleBtn.setBorderPainted(false);
		cancleBtn.setBounds(90,0,150,40);
		insertBtn.setFont(st.fnt1);
		insertBtn.setForeground(Color.WHITE);
		insertBtn.setBackground(st.clr1);
		insertBtn.setBorderPainted(false);
		insertBtn.setBounds(260,0,150,40);
		btnPane.add(cancleBtn);
		btnPane.add(insertBtn);
		btnPane.setPreferredSize(new Dimension(500,40));
		btnPane.setBorder(new EmptyBorder(150,0,0,0));
		
		// ��� ���� �гο� 1,2 �г� �־��ֱ�
		centerPane.add(inputPane);
		centerPane.add(btnPane, BorderLayout.SOUTH);
		centerPane.setBorder(new EmptyBorder(0,100,0,0));
		centerPane.setPreferredSize(new Dimension(700,350)); //500, 350

		add(centerPane);
		setBorder(new EmptyBorder(100,0,0,0));	// ���� ����
		
		// �̺�Ʈ �߻� -- �ؽ�Ʈ �ʵ� "���ּ�"��
		tf[1].addFocusListener(this);
		cancleBtn.addActionListener(this);
		insertBtn.addActionListener(this);
		timeChk[0].addItemListener(this);
		timeChk[1].addItemListener(this);
	}
	
	// ----- ����� ���� �� ���� -----
	public UpdateCenter(int center_code) {
		this();
		this.center_code = center_code;
		
		CenterDAO dao = new CenterDAO();
		CenterVO vo = dao.selectCenter(center_code);
		
		tf[0].setText(vo.getCenter_name());
		selectLocCB(vo.getLoc_code());
		tf[1].setText(vo.getCenter_addr());
		tf[1].setForeground(Color.BLACK);
		tf[2].setText(vo.getCenter_tel());
		
		// ��ð� �ҷ�����
		String[] timeTxt = {vo.getCenter_time1(), vo.getCenter_time2(), vo.getCenter_time3()};
		for(int i=0; i<timeTxt.length; i++) {
			if(i==0) {
				timeCB[(i*4)+0].setSelectedItem(timeTxt[i].substring(0, 2));
				timeCB[(i*4)+1].setSelectedItem(timeTxt[i].substring(3, 5));
				timeCB[(i*4)+2].setSelectedItem(timeTxt[i].substring(8, 10));
				timeCB[(i*4)+3].setSelectedItem(timeTxt[i].substring(11));
				
			} else {
				if(timeTxt[i].equals("�̿")) {
					timeChk[i-1].setSelected(true);
				} else {
					timeCB[(i*4)+0].setSelectedItem(timeTxt[i].substring(0, 2));
					timeCB[(i*4)+1].setSelectedItem(timeTxt[i].substring(3, 5));
					timeCB[(i*4)+2].setSelectedItem(timeTxt[i].substring(8, 10));
					timeCB[(i*4)+3].setSelectedItem(timeTxt[i].substring(11));
				}
			}
		}		
		insertBtn.setText("���� �Ϸ�");
	}
	
	
	
	// �ּ�(�õ�, �ñ���) �޺��ڽ� �⺻ ����
	public void setDefaultLocCB(){
		LocDAO dao = new LocDAO();
		Vector<String> list1 = dao.combo1();
		model1 = new DefaultComboBoxModel<String>(list1);
		loc1CB = new JComboBox<String>(model1);
		loc1CB.insertItemAt("�á���", 0);
		loc1CB.setSelectedIndex(0);
		
		loc2CB = new JComboBox<String>();
		loc2CB.addItem("�á�������");
		
		// loc1 �޺��ڽ� �̺�Ʈ �߻� ���
		loc1CB.addActionListener(this);
	}
	
	// �õ�(loc1) �޺��ڽ� ���� �̺�Ʈ �߻� ---> �ñ���(loc2) �޺��ڽ� �ٲ�
	public void setLoc2CB(){
		LocDAO dao = new LocDAO();
		if(loc1CB.getSelectedItem().equals("�á���")) {
			loc2CB.removeAllItems();
			loc2CB.addItem("�á�������");
		} else {
			Vector<String> list2 = dao.combo2((String)loc1CB.getSelectedItem());
			DefaultComboBoxModel<String> model2 = new DefaultComboBoxModel<String>(list2);
			loc2CB.setModel(model2);
		}
		loc2CB.updateUI();	// loc2 (�ñ���) �޺��ڽ� ���� ������Ʈ
	}
	
	// ����� ������ --- �ش� ����� �õ�/�ñ��� ���� �����ϱ�
	public void selectLocCB(int loc_code) {
		LocDAO dao = new LocDAO();
		LocVO vo = new LocVO();
		vo = dao.selectLocData(loc_code);	//

		loc1CB.setSelectedItem(vo.getLoc1()); // loc1(�õ�) ���ð� �⺻ ����
		loc2CB.setSelectedItem(vo.getLoc2()); // loc2(�ñ���) ���ð� �⺻ ����
		
		// loc1 �޺��ڽ� �̺�Ʈ �߻� ���
		loc1CB.addActionListener(this);
	}
	
	
	
	// ��ð� �޺��ڽ� �⺻����
	public void setDefaultTime(){
		// �� �����
		for(int i=0; i<hour.length; i++) {
			if(i<10) hour[i]="0"+i;
			else hour[i]=""+i;
		}
		
		// �� �����
		for(int i=0; i<minute.length; i++) {
			if(i*5<10) minute[i]="0"+(i*5);
			else minute[i]=""+(i*5);
		}
		
		// ��/�� �޺��ڽ� �����
		for(int i=0; i<timeCB.length; i++) {
			if(i%2==0) timeCB[i] = new JComboBox<String>(hour);
			else timeCB[i] = new JComboBox<String>(minute);

			timeCB[i].insertItemAt("--", 0);
			timeCB[i].setSelectedIndex(0);
			timeCB[i].setFont(st.fnt3);
			timeCB[i].setBackground(Color.WHITE);
		}
		
		// ��/�� ��
		for(int i=0; i<timeLbl.length; i++) {
			if(i%5==4) timeLbl[i] = new JLabel("~");
			else if(i%5==0 || i%5==2) timeLbl[i] = new JLabel("��");
			else timeLbl[i] = new JLabel("��");
			
			if(i%5==4) timeLbl[i].setFont(st.fnt2);
			else timeLbl[i].setFont(st.fnt3);
			
			timeLbl[i].setBorder(new EmptyBorder(0,2,0,0));
		}
	}
	
	
	// �Էµ� ����� ���� CenterVO�� �����ϱ� ---> ���, ���� �޼��忡 ���� ����
	public CenterVO setCenterVO() {
		LocDAO ldao = new LocDAO();
		CenterVO cvo = new CenterVO();
		CenterDAO cdao = new CenterDAO();
		
		String loc1 = (String)loc1CB.getSelectedItem();
		String loc2 = (String)loc2CB.getSelectedItem();
		int loc_code = ldao.getLocCode(loc1, loc2); // ���õ� �õ�/�ñ��� ������ �ּ� �ڵ� ������
		
		cvo.setCenter_name(tf[0].getText());
		cvo.setLoc_code(loc_code);
		cvo.setCenter_addr(tf[1].getText());
		cvo.setCenter_tel(tf[2].getText());
		String[] timeTxt = new String[3];
		for(int i=0; i<timeTxt.length; i++) {
			if(i==0) {
				timeTxt[i] = (String)timeCB[(i*4)+0].getSelectedItem()+":"+timeCB[(i*4)+1].getSelectedItem()+" ~ "+timeCB[(i*4)+2].getSelectedItem()+":"+timeCB[(i*4)+3].getSelectedItem();
			} else {
				if(timeChk[i-1].isSelected()) timeTxt[i] = "�̿";
				else timeTxt[i] = (String)timeCB[(i*4)+0].getSelectedItem()+":"+timeCB[(i*4)+1].getSelectedItem()+" ~ "+timeCB[(i*4)+2].getSelectedItem()+":"+timeCB[(i*4)+3].getSelectedItem();
			}
		}
		cvo.setCenter_time1(timeTxt[0]);
		cvo.setCenter_time2(timeTxt[1]);
		cvo.setCenter_time3(timeTxt[2]);
		
		return cvo;
	}
	
	// ����� ��� ----> ��� ��ư �̺�Ʈ
	public void insertCenter() {
		CenterVO cvo = setCenterVO(); // ����� center_code ������ �ڵ� ����̶� �ʿ� ����
		CenterDAO cdao = new CenterDAO();
		
		int cnt = cdao.insertCenterData(cvo);	
		if(cnt>0) { // ����� ��� ����
			insertVacc(cvo); // ��� ������ ����ϱ�
		} else { // ����� ��� ����
			JOptionPane.showMessageDialog(this, "����� ���� ����� �����߽��ϴ�. �ٽ� �õ��� �ּ���.");
		}
	}
	
	// ��� ������ ��� ----> ����� ��� �����ϸ� ���� // ��� ������ ��� �⺻ 0
	public void insertVacc(CenterVO cvo) {
		CenterDAO cdao = new CenterDAO();
		VaccineDAO vdao = new VaccineDAO();
	
		int center_code = cdao.getCenterCordData(cvo); // ��� ��ϵ� ����� ������ ����� �ڵ� ��������
		int cnt = vdao.insertVaccData(center_code); // ��� ������ ����ϱ�
		if(cnt>0) { // ��� ������ ��� ����
			JOptionPane.showMessageDialog(this, "����� ������ ��ϵǾ����ϴ�.");
			returnManageCenter(); //------------------------------------------------------------------------------------------ ȭ�� ��ȯ
		} else { // ��� ������ ��� ���� ��
			int cnt2 = cdao.deleteCenterData(center_code); // ��� ��ϵ� ����� ���� �����ϱ�
			if(cnt2>0) { // �����, ��� ��� ���X
				JOptionPane.showMessageDialog(this, "����� ���� ����� �����߽��ϴ�. �ٽ� �õ��� �ּ���.");
			} else { // ����Ҹ� ��ϵǰ� ��� ������ ��� ���� �� (����� ���� ���� ��)
				JOptionPane.showMessageDialog(this, "����� ������ ��ϵǾ����ϴ�. ��� ������ ����Ϸ��� �����ڿ��� ���� �ٶ��ϴ�.");
				returnManageCenter(); //-------------------------------------------------------------------------------------- ȭ�� ��ȯ
			}
		}
	}
	
	
	// ����� ���� ----> ���� �Ϸ� ��ư �̺�Ʈ
	public void updateCenter() {
		CenterVO cvo = setCenterVO();
		CenterDAO cdao = new CenterDAO();
		
		cvo.setCenter_code(center_code);	// ������ ���� center_code �ʿ���
		
		int cnt = cdao.updateCenterData(cvo);
		if(cnt>0) {
			JOptionPane.showMessageDialog(this, "����� ���� ������ �Ϸ�Ǿ����ϴ�.");
			returnManageCenter(); //------------------------------------------------------------------------------------------- ȭ�� ��ȯ
		} else {
			JOptionPane.showMessageDialog(this, "����� ���� ������ �����߽��ϴ�. �ٽ� �õ��� �ּ���.");
		}
	}
	
	// ���/���� �� �Է� ���� �˻�
	public boolean checkInfo() {
		boolean chk = true;
		if(tf[0].getText()==null || tf[0].getText().equals("")) {
			JOptionPane.showMessageDialog(this, "����Ҹ��� �Է��� �ּ���.");
			chk = false;
		} else if(loc1CB.getSelectedItem().equals("�á���")) {
			JOptionPane.showMessageDialog(this, "������ ������ �ּ���.");
			chk = false;
		} else if(tf[1].getText()==null || tf[1].getText().equals("") || tf[1].getText().equals("���ּ�")) {
			JOptionPane.showMessageDialog(this, "���ּҸ� �Է��� �ּ���.");
			chk = false;
		} else if(tf[2].getText()==null || tf[2].getText().equals("")) {
			JOptionPane.showMessageDialog(this, "��ǥ ��ȭ��ȣ�� �Է��� �ּ���.");
			chk = false;
		} else {
			// ��ð� ���� �˻�
//			boolean chk = true;
			for(int i=0; i<timeCB.length; i++) {
				if(i<=3) { // 0,1,2,3
					if(timeCB[i].getSelectedItem().equals("--")) {	// ���� ��ð�
						JOptionPane.showMessageDialog(this, "��ð��� ��� �������ּ���.");
						chk = false;
						break;
					}
				} else if(i>=4 && i<8){ // 4,5,6,7
					if(timeCB[i].getSelectedItem().equals("--") && timeChk[0].isSelected()==false) { // ����� ��ð�
						JOptionPane.showMessageDialog(this, "��ð��� ��� �������ּ���.");
						chk = false;
						break;
					}
				} else { // 8,9,10,11
					if(timeCB[i].getSelectedItem().equals("--") && timeChk[1].isSelected()==false) { // �Ͽ���/������ ��ð�
						JOptionPane.showMessageDialog(this, "��ð��� ��� �������ּ���.");
						chk = false;
						break;
					}
				}
			} //for
		} // if
		return chk;
	}
	
	
	// ȭ����ȯ ----> [����� ����]�� �̵�
	public void returnManageCenter() {
		UserTabMenu.centerpane.removeAll();
		mCenter = new ManageCenter();
		UserTabMenu.centerpane.add(mCenter);
		UserTabMenu.centerpane.updateUI();
	}
	
	
	// --------------- �̺�Ʈ ��� ---------------------
	
	// ��ư/�޺��ڽ� ���� �̺�Ʈ // ���� ȭ�� ��ȯ�� ���⼭
	public void actionPerformed(ActionEvent e) {
		Object evt = e.getSource();
		String evtStr = e.getActionCommand();
		if(evt instanceof JComboBox) {
			setLoc2CB();
		} else if(evt instanceof JButton) {
			if(evtStr.equals("���")) {
				boolean chk = checkInfo(); // ���� �� �Է��߳� üũ
				if(chk) insertCenter(); // ����� ���
			} else if(evtStr.equals("���� �Ϸ�")) {
				boolean chk = checkInfo(); // ���� �� �Է��߳� üũ
				if(chk) updateCenter(); // ����� ����
			} else if(evtStr.equals("���")) {
				returnManageCenter(); //------------------------------------------------------------------------------------- ȭ�� ��ȯ
			}
		}
		
	}
	
	// �̿ üũ�ڽ� �̺�Ʈ
	public void itemStateChanged(ItemEvent e) {
		Object evt = e.getSource();
		if(evt==timeChk[0]) {
			if(e.getStateChange()==ItemEvent.SELECTED) {
				for(int i=4; i<=7; i++) {
					timeCB[i].setEnabled(false);
					timeCB[i].setSelectedIndex(0);
				}
			} else {
				for(int i=4; i<=7; i++) {
					timeCB[i].setEnabled(true);
				}
			}
		} else if(evt==timeChk[1]) {
			if(e.getStateChange()==ItemEvent.SELECTED) {
				for(int i=8; i<=11; i++) {
					timeCB[i].setEnabled(false);
					timeCB[i].setSelectedIndex(0);
				}
			} else {
				for(int i=8; i<=11; i++) {
					timeCB[i].setEnabled(true);
				}
			}
		}
		
	}
	
	
	// ���ּҶ� ��Ŀ�� �� �̺�Ʈ
	public void focusGained(FocusEvent e) {
		if(tf[1].getText().equals("���ּ�")) {
			tf[1].setText("");
			tf[1].setForeground(Color.BLACK);
		}
	}

	// ���ּҶ� ��Ŀ�� ���� �� �̺�Ʈ
	public void focusLost(FocusEvent e) {
		if(tf[1].getText()==null || tf[1].getText().equals("")) {
			tf[1].setText("���ּ�");
			tf[1].setForeground(Color.GRAY);
		}
	}

}

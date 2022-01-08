package admin.admin;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import database.AdminDAO;
import database.AdminVO;
import user.home.SetStyle;

public class AdminData extends JPanel implements ActionListener {//������ ������
	//����
	//��� ���� : �˻��� ���� ��, �˾�â �߱�
	//Ȩ ��� ��ü���� ��ư ���� / �̺�Ʈ ���� �ص�
	//�׹ۿ� ��¦ ������
	
	AdminDAO dao = new AdminDAO();
	JTable table;
	JScrollPane sp;
	DefaultTableModel model;
	//	JTable ���� �������� ����� �迭
	String title[] = {"���̵�","�̸�","�ֹε�Ϲ�ȣ","�޴�����ȣ","�Ҽ�"};
	//Ȩ���� ������ư
	JPanel click = new JPanel(new GridLayout(2,1));
	JPanel cen = new JPanel();
	JPanel clickbtn = new JPanel();

	JPanel checkpanel = new JPanel(new GridLayout(2,1));
	JButton allbtn = new JButton("��ü ���");
	JButton deletebtn = new JButton("����");

	String id;

	//�����ڰ˻�
	JPanel northPane = new JPanel();
	DefaultComboBoxModel<String> searchModel = new DefaultComboBoxModel<String>();
	JComboBox<String> searchKey = new JComboBox<String>(searchModel);	
	JTextField searchadmin = new JTextField(15);
	JButton searchBtn = new JButton("�˻�");
    
	SetStyle st = new SetStyle();
	
	public AdminData() {
		
		setLayout(new BorderLayout());

		model = new DefaultTableModel(title,0);
		table = new JTable(model);
		sp = new JScrollPane(table);

		add(sp);
		add(BorderLayout.SOUTH,click);
		cen.add(checkpanel);
		click.add(cen);
		click.add(clickbtn);
		clickbtn.add(allbtn);
		allbtn.setFont(st.fnt1);
		allbtn.setBackground(st.clr1);
		allbtn.setForeground(Color.WHITE);
		allbtn.setBorderPainted(false);
		clickbtn.add(deletebtn);
		deletebtn.setFont(st.fnt1);
		deletebtn.setBackground(st.clr1);
		deletebtn.setForeground(Color.WHITE);
		deletebtn.setBorderPainted(false);
		deletebtn.addActionListener(this);
		searchadmin.addActionListener(this);
		
		AdminAllList();
		setSearchForm();
		
//		setSize(1200,800);		
//		setVisible(true);
//		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}
	//������ �˻� ��
	public void setSearchForm() {
		add(BorderLayout.NORTH,northPane);

		searchModel.addElement("���̵�");
		searchModel.addElement("�̸�");
		searchModel.addElement("�޴�����ȣ");
		searchModel.addElement("�Ҽ�");
		northPane.add(searchKey);//�޺��ڽ�

		northPane.add(searchadmin);
		northPane.add(searchBtn);
		searchBtn.setFont(st.fnt1);
		searchBtn.setBackground(st.clr1);
		searchBtn.setForeground(Color.WHITE);
		searchBtn.setBorderPainted(false);
		searchBtn.addActionListener(this);
		allbtn.addActionListener(this);
	}
	@Override
	public void actionPerformed(ActionEvent ae) {
		String eventBtn = ae.getActionCommand();
		Object obj = ae.getSource();
		if(eventBtn.equals("����")) {
			selectDelAdmin();
		}else if(eventBtn.equals("�˻�")) {
			adminSearch();
		}else if(obj==searchadmin) {
			adminSearch();
		}
		//��� �߰�
		else if(obj==allbtn) {
			//System.out.println("��ü���� Ŭ��");
			AdminAllList();
		}
	}
	//������ ����
	public void AdminDelete(){

		int row = table.getSelectedRow();
		id = String.valueOf(table.getValueAt(row, 0));
		//		System.out.println("Ȯ�ο�");
		dao.deleteAdmin(id);
		AdminAllList();
	}
	public void selectDelAdmin() {

		int resurt =JOptionPane.showConfirmDialog(this, "�����ڸ� ���� �����Ͻðڽ��ϱ�?", "����", JOptionPane.OK_CANCEL_OPTION,JOptionPane.PLAIN_MESSAGE);
		if(resurt==JOptionPane.OK_OPTION) {
			AdminDelete();
		}
	}
	//������ ���̽����� ������ü ���(�̸�����) �������� - JTable����� �����ش�.
	public void AdminAllList() {
		List<AdminVO> list = dao.admingrade1();
		setAdminTable(list);
	}
	public void setAdminTable(List<AdminVO> list ) {
		model.setRowCount(0);
		for(int i=0;i<list.size();i++) {
			AdminVO vo = list.get(i);
			Object[] obj = {vo.getAdmin_id(),vo.getAdmin_name(),vo.getAdmin_num(),vo.getAdmin_tel(),vo.getAdmin_local()};
			model.addRow(obj);
		}
	}
	//�˻�
	public void adminSearch() {
		String search = searchadmin.getText();
		if(search!=null && !search.equals("")) {//�˻���ִ�.
			String searchField = (String)searchKey.getSelectedItem();
			//�˻�Ű "���̵�","�̸�",,"�޴�����ȣ","�Ҽ�"

			String fieldName = "";
			if(searchField.equals("���̵�")) {
				fieldName = "admin_id";
			}else if(searchField.equals("�̸�")) {
				fieldName = "admin_name";
			}else if(searchField.equals("�޴�����ȣ")) {
				fieldName = "admin_tel";
			}else if(searchField.equals("�Ҽ�")) {
				fieldName = "admin_local";
			}

			AdminDAO dao = new AdminDAO();
			List<AdminVO>list = dao.searchadmindata(fieldName,search);
			setAdminTable(list);
			searchadmin.setText("");
		}
		else {
			JOptionPane.showMessageDialog(this, "�˻�� �Է��ϼ���.");
		}
	}
	public static void main(String[] args) {
//		new AdminData();
	}
}
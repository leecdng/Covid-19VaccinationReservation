package admin.user;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;
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
import database.UserDAO;
import database.UserVO;
import oracle.net.aso.v;
import user.home.SetStyle;
public class UserData extends JPanel implements ActionListener{ //����
	//��� ���� : �˻��� ���� ��, �˾�â �߱�
	//Ȩ ��� ��ü���� ��ư ���� / �̺�Ʈ ���� �ص�
	//�׹ۿ� ��¦ ������
	
	UserDAO dao = new UserDAO();
	JTable table;
	JScrollPane sp;
	DefaultTableModel model;
	//	JTable ���� �������� ����� �迭
	String title[] = {"���̵�","�̸�","�ֹε�Ϲ�ȣ","��ȭ��ȣ"};
	//Ȩ���� ������ư
	JPanel click = new JPanel(new GridLayout(2,1));
	JPanel cen = new JPanel();
	JPanel clickbtn = new JPanel();

	JPanel checkpanel = new JPanel(new GridLayout(2,1));
	JButton allbtn = new JButton("��ü ���");
	JButton deletebtn = new JButton("����");

	String id;
	//ȸ���˻�
	JPanel northPane = new JPanel();
	DefaultComboBoxModel<String> searchModel = new DefaultComboBoxModel<String>();
	JComboBox<String> searchKey = new JComboBox<String>(searchModel);	
	JTextField searchuser = new JTextField(15);
	JButton searchBtn = new JButton("�˻�");
	SetStyle st = new SetStyle();
	public UserData() {
		
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
		searchuser.addActionListener(this);
		allbtn.addActionListener(this);
		
		userAllList();
		setSearchForm();
		
//		setSize(1200,800);		
//		setVisible(true);
//		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}
	//ȸ�� �˻� ��
	public void setSearchForm() {
		add(BorderLayout.NORTH,northPane);

		searchModel.addElement("���̵�");
		searchModel.addElement("�̸�");
		searchModel.addElement("�޴�����ȣ");
		northPane.add(searchKey);//�޺��ڽ�

		northPane.add(searchuser);
		northPane.add(searchBtn);
		searchBtn.setFont(st.fnt1);
		searchBtn.setBackground(st.clr1);
		searchBtn.setForeground(Color.WHITE);
		searchBtn.setBorderPainted(false);
		searchBtn.addActionListener(this);
	}
	
	////////////////////////////////////////////
	@Override
	public void actionPerformed(ActionEvent e) {
		String eventBtn = e.getActionCommand();
		Object obj = e.getSource();
		if(eventBtn.equals("����")) {
			selectDelUser();
		}else if(eventBtn.equals("�˻�")) {
			userSearch();
		}else if(obj==searchuser) {
			userSearch();
		}
		//��� �߰�
		else if(obj==allbtn) {
			//System.out.println("��ü���� Ŭ��");
			userAllList();
		}
	}
	//////////////////////////////////////////////
	
	public void UserDelete(){
		//���� �� ����
		int row = table.getSelectedRow();
		id = String.valueOf(table.getValueAt(row, 0));
		//		System.out.println("Ȯ�ο�");
		dao.deleteUser(id);
		userAllList();
		
	}
	public void selectDelUser() {

		int resurt =JOptionPane.showConfirmDialog(this, "ȸ���� ���� �����Ͻðڽ��ϱ�?", "����", JOptionPane.OK_CANCEL_OPTION,JOptionPane.PLAIN_MESSAGE);
		if(resurt==JOptionPane.OK_OPTION) {
			UserDelete();
		}
	}
	//������ ���̽����� ȸ����ü ���(�̸�����) �������� - JTable����� �����ش�.
	public void userAllList() {
		List<UserVO> list = dao.UserRecord();
		setUserTable(list);
	}
	public void setUserTable(List<UserVO> list ) {
		model.setRowCount(0);
		for(int i=0;i<list.size();i++) {
			UserVO vo = list.get(i);
			Object[] obj = {vo.getUser_id(),vo.getUser_name(),vo.getUser_num(),vo.getUser_tel()};
			model.addRow(obj);
		}
	}
	public void userSearch() {
		String search = searchuser.getText();
		if(search!=null && !search.equals("")) {//�˻���ִ�.
			String searchField = (String)searchKey.getSelectedItem();
			//�˻�Ű "���̵�","�̸�","�޴�����ȣ"

			String fieldName = "";
			if(searchField.equals("���̵�")) {
				fieldName = "user_id";
			}else if(searchField.equals("�̸�")) {
				fieldName = "user_name";
			}else if(searchField.equals("�޴�����ȣ")) {
				fieldName = "user_tel";
			}

			UserDAO dao = new UserDAO();
			List<UserVO>list = dao.searchuserdata(fieldName,search);
			setUserTable(list);
			searchuser.setText("");
		}
		else {
			JOptionPane.showMessageDialog(this, "�˻�� �Է��ϼ���.");
		}
	}
	public static void main(String[] args) {
//		new UserData();
	}
}
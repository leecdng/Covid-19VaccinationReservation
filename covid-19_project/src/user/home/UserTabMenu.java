package user.home;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;

import admin.admin.AdminTab;
import admin.center.ManageCenter;
import admin.center.ManageVacc;
import admin.home.AdminInfo;
import admin.home.SignUpMain;
import admin.rsv.RsvTab;
import admin.user.UserData;
import database.AdminDAO;
import database.AdminVO;
import database.RsvSettingDAO;
import database.RsvSettingVO;
import user.check.TabCheckReservationinformation;
import user.rsv.RsvSouthBtn;
import user.rsv.RsvVaccineRadio;
import user.searchcenter.SearchCenterMain;
import user.searchcenter.SearchCenterSplit;

public class UserTabMenu extends JFrame implements MouseListener {// �ǿ���������

	// ���� �⺻ �Ǹ޴�
	JSplitPane allsp;
	JTabbedPane maintab = new JTabbedPane(JTabbedPane.LEFT);

	JPanel leftpane = new JPanel(new BorderLayout());
	JPanel userpane = new JPanel(new BorderLayout());
	JPanel pane = new JPanel(new GridLayout(0, 1));
	JButton[] btn = { new JButton("Ȩ"), new JButton("�˻� ����"), new JButton("��� ���� ����"), new JButton("���� ��ȸ"),
			new JButton("����� ã��"), new JButton("������ ���") };

	JPanel adminpane = new JPanel(new BorderLayout());
	JPanel pane2 = new JPanel(new GridLayout(0, 1));
	JButton[] btn2 = { new JButton("Ȩ"), new JButton("����� ����"), new JButton("��� ����"), new JButton("���� ����"),
			new JButton("ȸ�� ����"), new JButton("���� ����"), new JButton("ȸ�� ���") };

	JPanel rightpane = new JPanel(new BorderLayout());
	JScrollPane sp = new JScrollPane(rightpane);
	public static JPanel centerpane = new JPanel(new BorderLayout());

	SetStyle style = new SetStyle();
	MainTitle title = new MainTitle();

	Firstpage firstmain;
	SignUpMain signUp;
	Login login;
	AdminInfo info;

	UserData userdata;
	AdminTab admintab;
	TabCheckReservationinformation rsvCheck;
	SearchCenterMain sCenter;
	RsvVaccineRadio vRadio;
	ManageCenter mCenter;
	ManageVacc mVacc;

	public UserTabMenu() {

		setResizable(false);

		allsp = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, leftpane, sp);
		leftpane.add(userpane);

		for (int i = 0; i < btn.length; i++) {
			JPanel btnpane = new JPanel();
			if (i != btn.length - 1) {
				btnpane.add(btn[i]);
				pane.add(btnpane);
				userpane.add(BorderLayout.NORTH, pane);
			} else {
				btnpane.add(btn[i]);
				userpane.add(BorderLayout.SOUTH, btnpane);
			}

			// userpane.add(BorderLayout.NORTH, pane);

			btn[i].setFont(style.fnt1);
			btn[i].setPreferredSize(new Dimension(180, 40));
			btn[i].setBackground(style.clr1);
			btn[i].setForeground(Color.white);
			btn[i].setFocusPainted(false);
			btn[i].addMouseListener(this);
		}

		for (int i = 0; i < btn2.length; i++) {
			JPanel btnpane = new JPanel();
			if (i != btn2.length - 1) {
				btnpane.add(btn2[i]);
				pane2.add(btnpane);
				adminpane.add(BorderLayout.NORTH, pane2);
			} else {
				btnpane.add(btn2[i]);
				adminpane.add(BorderLayout.SOUTH, btnpane);
			}

			// adminpane.add(BorderLayout.NORTH,pane2);

			btn2[i].setFont(style.fnt1);
			btn2[i].setPreferredSize(new Dimension(180, 40));
			btn2[i].setBackground(Color.white);
			btn2[i].setForeground(style.clr1);
			btn2[i].setFocusPainted(false);
			btn2[i].addMouseListener(this);
		}

		MainTitle.lbl.setText("��");

		rightpane.add(BorderLayout.NORTH, title);
		rightpane.add(centerpane);

		firstmain = new Firstpage(MainTitle.mode);
		centerpane.add(firstmain);
		rightpane.setBackground(Color.white);
		add(allsp);

		setTitle("�ڷγ� �˻�/��� ���� �ý���");
		setSize(1200, 800);
		setVisible(true);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);

		MainTitle.lbl2.addMouseListener(this);
		MainTitle.lbl3.addMouseListener(this);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		Object event = e.getSource();
		// �� �̺�Ʈ
		if (event == MainTitle.lbl2) {
			if (MainTitle.lbl2.getText().equals("ȸ������")) {
				MainTitle.lbl.setText("ȸ������");
				signUp = new SignUpMain();
				centerpane.removeAll();
				centerpane.add(signUp);
				if (MainTitle.mode == 1) {
					signUp.cb1.setVisible(false);
					signUp.lbl[6].setVisible(false);
				}
				// centerpane.updateUI();
			} else if (MainTitle.lbl2.getText().equals("���� ����")) {
				MainTitle.lbl.setText("���� ����");
				info = new AdminInfo();
				centerpane.removeAll();
				centerpane.add(info);
				if (MainTitle.mode == 1) {
					info.affiliationlbl.setVisible(false);
					info.affiliation.setVisible(false);
				}
			}
			centerpane.updateUI();
		} else if (event == MainTitle.lbl3) {
			if (MainTitle.lbl3.getText().equals("�α���")) {
				MainTitle.lbl.setText("�α���");
				login = new Login();
				centerpane.removeAll();
				centerpane.add(login);
				// centerpane.updateUI();

				// �׽�Ʈ�� ���� ���
				if (MainTitle.mode == 1) {
					System.out.println("ȸ����� �α���");
				} else
					System.out.println("�����ڸ�� �α���");
			} else if (MainTitle.lbl3.getText().equals("�α׾ƿ�")) {
				MainTitle.user_id = null;
				MainTitle.admin_id = null;
				JOptionPane.showMessageDialog(null, "�α׾ƿ� �Ǿ����ϴ�.");
				firstmain = new Firstpage(MainTitle.mode);
				centerpane.removeAll();
				centerpane.add(firstmain);
				// centerpane.updateUI();

				MainTitle.lbl2.setText("ȸ������");
				MainTitle.lbl3.setText("�α���");
			}
			centerpane.updateUI();
		}

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		Object event = e.getSource();
		// ��ư �̺�Ʈ (ȸ�����)
		if (event == btn[0]) {
			MainTitle.lbl.setText("��");
			// System.out.println("Ȩ Ŭ��");
			firstmain = new Firstpage(MainTitle.mode);
			centerpane.removeAll();
			centerpane.add(firstmain);
			centerpane.updateUI();
		} else if (event == btn[1]) {
			MainTitle.lbl.setText("�˻� ����");
			firstmain = new Firstpage(MainTitle.mode);
			centerpane.removeAll();
			SearchCenterSplit.centerName = null;
			if (MainTitle.user_id != null) {

				sCenter = new SearchCenterMain();
				RsvSouthBtn sBtn = new RsvSouthBtn();
				centerpane.add(sCenter);
				sBtn.rsvBtn.setText("�ڷγ� �˻� �����ϱ�");
				centerpane.add(BorderLayout.SOUTH, sBtn);
			} else {
				loginfirstUser();
			}
			centerpane.updateUI();
		} else if (event == btn[2]) {
			MainTitle.lbl.setText("��� ���� ����");
			centerpane.removeAll();
			SearchCenterSplit.centerName = null;
			RsvVaccineRadio.vc_type = null;
			if (MainTitle.user_id != null) {
				// ���� �߰� --------- ���� ��� ���⿡ �ش�Ǹ� ���� ����, �ƴϸ� �˸�â
				RsvSettingDAO dao = new RsvSettingDAO();
				RsvSettingVO vo = dao.selectRsvSetting();
				if(MainTitle.user_year>=vo.getAge1() && MainTitle.user_year<=vo.getAge2()) {
					sCenter = new SearchCenterMain();
					centerpane.removeAll();
					vRadio = new RsvVaccineRadio();
					centerpane.add(BorderLayout.NORTH, vRadio);
					vRadio.rBtn0.setVisible(false);
					centerpane.add(sCenter);

					RsvSouthBtn sBtn = new RsvSouthBtn();
					sBtn.rsvBtn.setText("��� ���� �����ϱ�");
					centerpane.add(BorderLayout.SOUTH, sBtn);
				} else {
					JOptionPane.showMessageDialog(this, "�ش� ��� ���� �Ⱓ�� ���� ����� �ƴմϴ�.");
					firstmain = new Firstpage(MainTitle.mode);
					centerpane.removeAll();
					centerpane.add(firstmain);
				} //-----------------
			} else {
				loginfirstUser();
			}
			centerpane.updateUI();
		} else if (event == btn[3]) {
			MainTitle.lbl.setText("���� ��ȸ");
			centerpane.removeAll();

			if (MainTitle.user_id != null) {
				rsvCheck = new TabCheckReservationinformation();
				centerpane.add(rsvCheck);
			} else {
				loginfirstUser();
			}
			centerpane.updateUI();
		} else if (event == btn[4]) {
			MainTitle.lbl.setText("����� ã��");
			centerpane.removeAll();
			sCenter = new SearchCenterMain();
			centerpane.add(sCenter);
			centerpane.updateUI();
		} else if (event == btn[5]) {
			MainTitle.lbl.setText("������ ��� HOME");
			MainTitle.mode = 2;
			MainTitle.user_id = null;
			firstmain = new Firstpage(MainTitle.mode);
			login = new Login();

			MainTitle.lbl2.setText("ȸ������");
			MainTitle.lbl3.setText("�α���");

			centerpane.removeAll();
			centerpane.add(login);
			centerpane.updateUI();

			leftpane.removeAll();
			leftpane.add(adminpane);
			leftpane.updateUI();
		}

		// ��ư �̺�Ʈ (�����ڸ��)
		else if (event == btn2[0]) {
			MainTitle.lbl.setText("������ ��� HOME");
			firstmain = new Firstpage(MainTitle.mode);
			MainTitle.mode = 2;
			centerpane.removeAll();
			if (MainTitle.admin_id != null) {
				centerpane.add(firstmain);
			} else
				loginfirstAdmin();
			centerpane.updateUI();
		} else if (event == btn2[1]) {
			MainTitle.lbl.setText("����� ����");

			centerpane.removeAll();

			if (MainTitle.admin_id != null) {
				AdminDAO dao = new AdminDAO();
				AdminVO vo = dao.setMyInfo(MainTitle.admin_id);
				String locname = vo.getAdmin_local();

				if (locname.equals("�á���")) {
					mCenter = new ManageCenter();
				} else {
					mCenter = new ManageCenter(locname);
				}
				centerpane.add(mCenter);
			} else {
				loginfirstAdmin();
			}
			centerpane.updateUI();
		} else if (event == btn2[2]) {
			MainTitle.lbl.setText("��� ����");
			centerpane.removeAll();
			
			AdminDAO admindao = new AdminDAO();
			AdminVO adminvo = admindao.setMyInfo(MainTitle.admin_id);
			
			if (MainTitle.admin_id != null) {
				if(adminvo.getAdmin_local().equals("�á���")) {
					mVacc = new ManageVacc();
				}
				else {
					mVacc = new ManageVacc(adminvo.getAdmin_local());
				}
				centerpane.add(mVacc);
			} else {
				loginfirstAdmin();
			}
			centerpane.updateUI();
		} else if (event == btn2[3]) {
			MainTitle.lbl.setText("���� ����");
			RsvTab rsvtab = new RsvTab();
			centerpane.removeAll();
			if (MainTitle.admin_id != null) {
				centerpane.add(rsvtab);
			} else {
				loginfirstAdmin();
			}
			centerpane.updateUI();
		} else if (event == btn2[4]) {		
			MainTitle.lbl.setText("ȸ�� ����");
			userdata = new UserData();
			centerpane.removeAll();
			if (MainTitle.admin_id != null) {
				centerpane.add(userdata);
			} else {
				loginfirstAdmin();
			}
			centerpane.updateUI();
		} else if (event == btn2[5]) {
			MainTitle.lbl.setText("���� ����");
			admintab = new AdminTab();
			centerpane.removeAll();

			AdminDAO dao = new AdminDAO();
			AdminVO vo = dao.setMyInfo(MainTitle.admin_id);

			if (MainTitle.admin_id == null) { // �α���X
				loginfirstAdmin();
			}

			else if (vo.getAdmin_local().equals("�á���")) { // ���۰�����
				centerpane.add(admintab);
			}

			else if (MainTitle.admin_id != null) { // �α���O
				JOptionPane.showMessageDialog(null, "���� �����ڸ� ������ �� �ֽ��ϴ�.");
				firstmain = new Firstpage(MainTitle.mode);
				centerpane.add(firstmain);
			}
			centerpane.updateUI();
		} else if (event == btn2[6]) {
			MainTitle.lbl.setText("��");
			MainTitle.mode = 1;
			firstmain = new Firstpage(MainTitle.mode);
			MainTitle.admin_id = null;
			MainTitle.lbl2.setText("ȸ������");
			MainTitle.lbl3.setText("�α���");

			centerpane.removeAll();
			centerpane.add(firstmain);
			centerpane.updateUI();

			leftpane.removeAll();
			leftpane.add(userpane);
			leftpane.updateUI();
		}

	}

	// �α��� ���â
	public void loginfirstUser() {
		JOptionPane.showMessageDialog(null, "���� �α������ּ���.");
		MainTitle.lbl.setText("�α���");
		login = new Login();
		centerpane.add(login);
	}

	// ������ �α��� ���â
	public void loginfirstAdmin() {
		JOptionPane.showMessageDialog(null, "���� ������ �α����� ���ּ���.");
		MainTitle.lbl.setText("�α���");
		login = new Login();
		centerpane.add(login);
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

//	public static void main(String[] args) {
//		new UserTabMenu();
//
//	}

}
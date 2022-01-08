package user.home;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import database.RsvSettingDAO;
import database.RsvSettingVO;

public class Firstpage extends JPanel implements ActionListener {//����
	/* �̱��� ���� -> �������� �ؽ�Ʈ�� ȭ���� �ٲ� ����Ǿ�� �ϰ�, �������������� �Ϲ�ȸ���������� �ٲ� ������ ����Ǿ�� �մϴ�. */
	
	// ��� ���� �г�
	JPanel centerPane = new JPanel(null);
		// Ÿ��Ʋ
		JLabel mainTitle = new JLabel();
		Font mainFnt = new Font("���� ���", Font.BOLD, 38);	
		//String.valueOf()
		// Ȯ���� ��Ȳ
		JPanel notice1 = new JPanel();
		CoronaAPI api = new CoronaAPI();
		
		// �������� (�ȳ�)
		JPanel notice2 = new JPanel(new BorderLayout());
			JEditorPane subPane = new JEditorPane();
				String txt1; // �������� ����
			JEditorPane txtPane = new JEditorPane();
				String txt2; // �������� ����
			JPanel editPane = new JPanel(new GridLayout(1, 1, 10, 0));
				JButton cancleBtn = new JButton("���");
				JButton editBtn = new JButton();
		
	// ��Ʈ // �÷�
	// ��Ʈ // �÷�
	SetStyle st = new SetStyle();
	//Font fnt1 = new Font("���� ���", Font.BOLD, 20);
	
	public Firstpage(int mode) {
		
		setLayout(new FlowLayout());
		setBackground(Color.WHITE);

		// Ÿ��Ʋ
		mainTitle.setText("�ڷγ� �˻�/��� ���� �ý���");
		mainTitle.setFont(mainFnt);
		mainTitle.setBounds(0, 50, 660, 50);
		mainTitle.setHorizontalAlignment(JLabel.CENTER);
		mainTitle.setVerticalAlignment(JLabel.CENTER);
		
		
		notice1.setBorder(new EmptyBorder(20,20,20,20)); // �г� �ȿ� ���� //�ϼ����� ����
		notice1.setBackground(Color.DARK_GRAY);
		notice1.setBounds(0, 150, 320, 400);
		notice1.add(api);

		
		subPane.setFont(st.fnt20b); // ���� ��Ʈ
		txtPane.setFont(st.fnt3); // ���� ��Ʈ
		txtPane.setBorder(new EmptyBorder(10,0,0,0)); // �г� �ȿ� ���� //�ϼ����� ����
		
		setNotice2(); // �������� ����, ���� ���� ����
		
		if(mode == 2) {
			setDefaultEditBtn(); // ���� ��ư ���� - <�����ڿ�>������ �ʿ���
		}
		
		notice2.setBorder(new EmptyBorder(20,20,20,20)); // �г� �ȿ� ���� //�ϼ����� ����
		notice2.setBackground(st.clr1);
		notice2.setBounds(340, 150, 320, 400);
		
		notice2.add(subPane, BorderLayout.NORTH);
		notice2.add(txtPane, BorderLayout.CENTER);
		notice2.add(editPane, BorderLayout.SOUTH);
		
		// ��ü ����
		centerPane.setPreferredSize(new Dimension(660,580));
		centerPane.setBackground(Color.WHITE);
		centerPane.add(mainTitle);
		centerPane.add(notice1);
		centerPane.add(notice2);
		
		add(centerPane);
		
//		setSize(1200,800);
//		setVisible(true);
//		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		editBtn.addActionListener(this); // �����ϱ�, �����Ϸ� �̺�Ʈ �߻�
	}
	
	
	// �������� ����, ���� ����
	public void setNotice2() {
		// �������� ���� �ҷ�����
		RsvSettingDAO dao = new RsvSettingDAO();
		RsvSettingVO vo = dao.selectRsvSetting();
		txt1 = vo.getNotice_title();
		txt2 = vo.getNotice();
		
		subPane.setText(txt1);
		subPane.setForeground(Color.YELLOW);
		subPane.setOpaque(false);  // ���� �г� �����ϰ�
		subPane.setFocusable(false); // ���� ��Ȱ��ȭ
		
		txtPane.setText(txt2);
		txtPane.setForeground(Color.WHITE);
		txtPane.setOpaque(false); // ���� �г� �����ϰ�
		txtPane.setFocusable(false); // ���� ��Ȱ��ȭ
	}
	
	// ���� ��ư ����
	public void setDefaultEditBtn() {
		editBtn.setFont(st.fnt2);
		editBtn.setText("�����ϱ�");
		editBtn.setForeground(st.clr1);
		editBtn.setBackground(Color.WHITE);
		editBtn.setBorderPainted(false);
		editPane.setOpaque(false); // ��ư �г� �����ϰ�
		editPane.setBorder(new EmptyBorder(10,0,0,0));
		editPane.add(editBtn);
	}
	
	// �����ϱ� ��ư ������ ����
	public void editNotice() {
		txt1 = subPane.getText(); // ���� �� ���� �ؽ�Ʈ ��Ƶα�
		subPane.setOpaque(true);
		subPane.setForeground(Color.BLACK);
		subPane.setBackground(new Color(225, 225, 225));
		subPane.setFocusable(true);
		
		txt2 = txtPane.getText(); // ���� �� ���� �ؽ�Ʈ ��Ƶα�
		txtPane.setOpaque(true);
		txtPane.setForeground(Color.BLACK);
		txtPane.setBackground(new Color(225, 225, 225));
		txtPane.setFocusable(true);
		
		cancleBtn.setFont(st.fnt2);
		cancleBtn.setForeground(st.clr1);
		cancleBtn.setBackground(Color.WHITE);
		cancleBtn.setBorderPainted(false);
		editPane.add(cancleBtn);
		editBtn.setText("�����Ϸ�");
		
		cancleBtn.addActionListener(this);	// ��� ��ư �̺�Ʈ �߻�
	}

	// �����Ϸ� ��ư ������ ���� ---- �������� ����, ���� �����ϱ�
	public void editCheck() {
		txt1 = subPane.getText(); // ������ ���� �ؽ�Ʈ ���
		txt2 = txtPane.getText(); // ������ ���� �ؽ�Ʈ ���
		
		RsvSettingDAO dao = new RsvSettingDAO();
		int cnt = dao.updateRsvNotice(txt1, txt2);
		if(cnt>0) {
			JOptionPane.showMessageDialog(this, "������ �Ϸ�Ǿ����ϴ�.");
			editPane.remove(cancleBtn); // ��� ��ư ���ֱ�
			setNotice2(); // �������� ����, ���� ����
			setDefaultEditBtn(); //���� ��ư ����
		} else {
			JOptionPane.showMessageDialog(this, "������ �����Ͽ����ϴ�. �ٽ� �õ��� �ּ���.");
		}
	}
	
	// ��� ��ư ������ ����
	public void editCancle() {
		editPane.remove(cancleBtn); // ��� ��ư ���ֱ�
		setNotice2(); // �������� ����, ���� ���� --> ���� �� �ؽ�Ʈ�鵵 �ٽ� ���õ�
		setDefaultEditBtn(); //���� ��ư ����
	}
	
	
	
	
	
	@Override
	public void actionPerformed(ActionEvent ae) {
		String evtTxt = ae.getActionCommand();
		if(evtTxt.equals("�����ϱ�")) {
			editNotice();
		} else if(evtTxt.equals("�����Ϸ�")) {
			editCheck();
		} else if(evtTxt.equals("���")) {
			editCancle();
		}
	}
	
	
	public static void main(String[] args) {
//		new Firstpage_user();

	}
}

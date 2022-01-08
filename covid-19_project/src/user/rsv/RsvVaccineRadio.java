package user.rsv;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import user.home.SetStyle;

public class RsvVaccineRadio extends JPanel implements ActionListener { //��� // ���� ����
	public static String vc_type = "";

	//��ſ��� ��, ��� ���� ������ư
	public JRadioButton rBtn0 = new JRadioButton("��ü ���");
	public JRadioButton rBtn1 = new JRadioButton("�Ἶ");
	public JRadioButton rBtn2 = new JRadioButton("�ƽ�Ʈ������ī");
	public JRadioButton rBtn3 = new JRadioButton("ȭ����");
	public JRadioButton rBtn4 = new JRadioButton("�����");
	public ButtonGroup bg = new ButtonGroup();
	
//	//4���� ������ư�� ���� �г�
//	JPanel radioPane = new JPanel();
	
	SetStyle st = new SetStyle();
	
	public RsvVaccineRadio() {
		setLayout(new FlowLayout());
		
		rBtn0.setFont(st.fnt2);
		rBtn1.setFont(st.fnt2);
		rBtn2.setFont(st.fnt2);
		rBtn3.setFont(st.fnt2);
		rBtn4.setFont(st.fnt2);
		
		bg.add(rBtn0);
		bg.add(rBtn1);
		bg.add(rBtn2);
		bg.add(rBtn3);
		bg.add(rBtn4);
		
		add(rBtn0);
		add(rBtn1);
		add(rBtn2);
		add(rBtn3);
		add(rBtn4);
		
		rBtn0.setSelected(true);
		
		rBtn0.addActionListener(this);
		rBtn1.addActionListener(this);
		rBtn2.addActionListener(this);
		rBtn3.addActionListener(this);
		rBtn4.addActionListener(this);
		
//	    setSize(1200,800);
//	 	setVisible(true);
//	 	setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Object evt = e.getSource();
		if(evt == rBtn0) {
			vc_type="";
		} else if(evt == rBtn1) {
			vc_type="�Ἶ";
		} else if(evt == rBtn2) {
			vc_type="�ƽ�Ʈ������ī";
		} else if(evt == rBtn3) {
			vc_type="ȭ����";
		} else if(evt == rBtn4) {
			vc_type="�����";
		}
		
	}

//	public static void main(String[] args) {
//		new RsvVaccineRadio();
//
//	}

}

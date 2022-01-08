package admin.rsv;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import user.home.SetStyle;

public class RsvCalendar extends JPanel implements ActionListener{
	String rsvDate = ""; // ���õ� ��¥(��)
	String rsvTime = ""; // ���õ� �ð�
	
	Calendar now = Calendar.getInstance();
	int nowYear = now.get(Calendar.YEAR); // nowYear�� yearArr �����ϱ� ����
	int nowMonth = now.get(Calendar.MONTH)+1;
	int lastday; int week;
	int year = nowYear; int month = nowMonth;// year, month: ��� �޺��ڽ� ���ð�
	int rsvStartDay = now.get(Calendar.DAY_OF_MONTH)+1; // ���� ���� ������
	
	// -------- ȭ�� ���� �����̳�+������Ʈ ---------
		// ��¥ ����
		JPanel pane1 = new JPanel(new BorderLayout());
			JPanel dateTopPane = new JPanel(new BorderLayout());
				JLabel dateLbl = new JLabel("��¥ ����", JLabel.LEFT);
			JPanel datePane = new JPanel(new BorderLayout());
				JPanel ymPane = new JPanel();
					Integer[] yearArr = {nowYear-1, nowYear, nowYear+1};
					Integer[] monthArr = {1,2,3,4,5,6,7,8,9,10,11,12};
					JComboBox<Integer> yearCB = new JComboBox<Integer>(yearArr);
					JComboBox<Integer> monthCB = new JComboBox<Integer>(monthArr);
					JButton leftBtn = new JButton("<");
					JButton rightBtn = new JButton(">");
				JPanel dPane = new JPanel(new BorderLayout());
					JPanel weekPane = new JPanel(new GridLayout(1, 7));
						String weekStr = "�Ͽ�ȭ�������";
					JPanel dayPane = new JPanel(new GridLayout(0, 7));
						JButton[] dayBtn = new JButton[38];
						
	// ��Ʈ // �÷�
	SetStyle st = new SetStyle();
	
	public RsvCalendar() {
//		setBackground(Color.WHITE);
		
		// ----- pane1 ------ ��¥ ���� �г�
		dateLbl.setFont(st.fnt1);
		dateTopPane.add(dateLbl);
		dateTopPane.setBorder(new EmptyBorder(10,10,10,0)); // ���� �ϼ����� ����
		dateTopPane.setOpaque(false);
		//dateTopPane.setBackground(Color.WHITE); //
		
		leftBtn.setFont(st.fnt2);
		leftBtn.setBackground(Color.WHITE);
		leftBtn.setBorderPainted(false);
		rightBtn.setFont(st.fnt2);
		rightBtn.setBackground(Color.WHITE);
		rightBtn.setBorderPainted(false);
		yearCB.setSelectedItem(year);
		yearCB.setFont(st.fnt1);
		yearCB.setBackground(Color.WHITE);
		monthCB.setSelectedItem(month);
		monthCB.setFont(st.fnt1);
		monthCB.setBackground(Color.WHITE);
		
		ymPane.add(leftBtn);
		ymPane.add(yearCB);
		ymPane.add(monthCB);
		ymPane.add(rightBtn);
		ymPane.setBackground(Color.WHITE); //
		
		setWeek(); // ���� ����
		setDefaultDate(); // ��¥ ����
		
		weekPane.setBackground(Color.WHITE);
		dayPane.setBackground(Color.WHITE);
		dPane.add(weekPane, BorderLayout.NORTH);
		dPane.add(dayPane);
		
		datePane.add(ymPane, BorderLayout.NORTH);
		datePane.add(dPane);
		
		pane1.add(dateTopPane, BorderLayout.NORTH);
		pane1.add(datePane);
		
		pane1.setBounds(0, 20, 400, 290);
		pane1.setBackground(Color.WHITE); //


		
		add(pane1);
		
		// �̺�Ʈ �߻� ���
		yearCB.addActionListener(this);
		monthCB.addActionListener(this);
		leftBtn.addActionListener(this);
		rightBtn.addActionListener(this);

	}
	
	// ���� ����
	public void setWeek() {
		for(int i=0; i<weekStr.length(); i++) {
			JLabel weekLbl = new JLabel(weekStr.substring(i, i+1));
			if(i==0) weekLbl.setForeground(Color.RED);
			if(i==6) weekLbl.setForeground(Color.BLUE);
			weekLbl.setFont(st.fnt1);
			weekLbl.setHorizontalAlignment(JLabel.CENTER);
			weekPane.add(weekLbl);
		}
	}
	
	// ���� ��¥ ����
	public void setDefaultDate() {
		now.set(year, month-1, 1);
		week = now.get(Calendar.DAY_OF_WEEK);
		lastday = now.getActualMaximum(Calendar.DAY_OF_MONTH);

		for(int i=0; i<dayBtn.length; i++) {
			if(i<week-1) {	// ����
				dayBtn[i] = new JButton("");
				dayBtn[i].setBackground(Color.WHITE);
				dayBtn[i].setBorderPainted(false);
				dayBtn[i].setEnabled(false);
				dayPane.add(dayBtn[i]);
			} else if(i<lastday+week-1) { // ��¥ ��ư
				dayBtn[i] = new JButton((i+1)-(week-1)+"");
				
				if((i+1)%7==1) { // �Ͽ���
					dayBtn[i].setForeground(Color.RED);
//					if(center_time3.equals("�̿")) dayBtn[i].setEnabled(false);
				}
				else if((i+1)%7==0) { // �����
					dayBtn[i].setForeground(Color.BLUE);
//					if(center_time2.equals("�̿")) dayBtn[i].setEnabled(false);
				}
				dayBtn[i].setFont(st.fnt1);
				dayBtn[i].setHorizontalAlignment(JLabel.CENTER);
				dayBtn[i].setBackground(Color.WHITE);
				dayBtn[i].setBorderPainted(false);
				// ��¥ ��Ȱ��ȭ
				if(year<nowYear || (year==nowYear && month<nowMonth)) { // ���� ��¥ ���� �޺��ʹ� ���� ��Ȱ��ȭ
					dayBtn[i].setEnabled(false);
				} else if(year==nowYear && month==nowMonth) {
					if((i+1)-(week-1) < rsvStartDay) dayBtn[i].setEnabled(false);	// ���� ���� �����Ϻ��� ��¥ Ȱ��ȭ
				}
				// ��ư ��� + �̺�Ʈ ���
				dayBtn[i].addActionListener(this);
				dayPane.add(dayBtn[i]);
			} else { // ���� �� �ϰ� ������ �� ��ư // �迭�� ���� ������ ������ ��ư ������ ��. dayPane���� �� �־���
				dayBtn[i] = new JButton(""); 
			}
		}
	}
	
	// ��¥ ���� �̺�Ʈ �߻� �� ��¥ �ٽ� ���� --- ���õ� �� �����ֱ� ����
	public void setDate() { //
		dayPane.removeAll();
		dayPane.setVisible(false);
		setDefaultDate();
		dayPane.setVisible(true);
	}
	
	
	// ���� ��ư ������ ��
	public void setPrev() {
		int yearIdx = (Integer) yearCB.getSelectedItem();
		int monthIdx = (Integer) monthCB.getSelectedItem();
		if(monthIdx==1) {
			yearCB.setSelectedItem(yearIdx-1);
			monthCB.setSelectedItem(12);
			if(yearIdx==nowYear-1) {
				yearCB.setSelectedItem(yearIdx);
				monthCB.setSelectedItem(monthIdx);
			}
		} else {
			monthCB.setSelectedItem(monthIdx-1);
		}
	}
	
	// ���� ��ư ������ ��
	public void setNext() {
		int yearIdx = (Integer) yearCB.getSelectedItem();
		int monthIdx = (Integer) monthCB.getSelectedItem();
		if(monthIdx==12) {
			yearCB.setSelectedItem(yearIdx+1);
			monthCB.setSelectedItem(1);
			if(yearIdx==nowYear+1) {
				yearCB.setSelectedItem(yearIdx);
				monthCB.setSelectedItem(monthIdx);
			}
		} else {
			monthCB.setSelectedItem(monthIdx+1);
		}
	}
	
	// ��¥ �����ϸ� ����
	public void selectDate() {
		for(int i=0; i<dayBtn.length; i++) {	// ���� �� �� ������ ��ư ���� ������� ��������
			if((i+1)%7==1) dayBtn[i].setForeground(Color.RED);
			else if((i+1)%7==0) dayBtn[i].setForeground(Color.BLUE);
			else dayBtn[i].setForeground(Color.BLACK);
			dayBtn[i].setBackground(Color.WHITE);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object evt = e.getSource();
		String evtStr = e.getActionCommand();
		if(evt instanceof JComboBox) {	// �޺��ڽ� �̺�Ʈ�� �߻��ϸ�
			year = (Integer)yearCB.getSelectedItem();
			month = (Integer)monthCB.getSelectedItem();
			setDate();	// Ķ���� �缳��
		} else if(evt instanceof JButton) {	// ��ư �̺�Ʈ�� �߻��ϸ�
			JButton evtBtn = (JButton) e.getSource();
			if(evt==leftBtn) {	// < Ķ���� ���� ��ư
				setPrev();
				
			} else if(evt==rightBtn) {	// > Ķ���� ���� ��ư
				setNext();
				
			} else if(evtStr.equals("���")) {//------------------ ��� ��ư ������ ��
				//
				
			} else {	// ��¥ �����ϸ�
				rsvDate = evtStr; // ���õ� ��¥(��)�� ����
				selectDate();	// ��¥ ���� �޼���
				evtBtn.setForeground(Color.WHITE);	// ���õ� ��¥ ��ư �� ����
				evtBtn.setBackground(st.clr1);				
			}
		}
		
	}

}

package user.rsv;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JEditorPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import org.json.simple.JSONObject;

import database.CenterDAO;
import database.CenterVO;
import database.RsvDAO;
import database.RsvSettingDAO;
import database.RsvSettingVO;
import database.RsvVO;
import database.TestDAO;
import database.TestVO;
import database.UserDAO;
import database.UserVO;
import database.VaccineDAO;
import database.VaccineVO;
import net.nurigo.java_sdk.api.Message;
import user.check.TabCheckReservationinformation;
import user.check.TestRsvCheck;
import user.check.VaccineRsvCheck;
import user.home.MainTitle;
import user.home.SetStyle;
import user.home.UserTabMenu;
import user.searchcenter.SearchCenterMain;
import user.searchcenter.SearchCenterSplit;

public class RsvSelectDate extends JPanel implements ActionListener{ // ���� // �˻�+��� ���� ��¥�ð� ���� ȭ��
   // ---- �޾ƿ;� �� ��
   private String user_id = MainTitle.user_id; // �α��� ȸ�� ���̵� ����
   private int center_code; // ����� �ڵ�
   private String vc_type; // ��� �ڵ�
   // ----------------------------------
   private String center_name;
   private String center_time1;
   private String center_time2;
   private String center_time3;
   
   String rsvDate = ""; // ���õ� ��¥(��)
   String rsvTime = ""; // ���õ� �ð�
   
   Calendar now = Calendar.getInstance();
   // ���� ������
   int startYear = now.get(Calendar.YEAR);
   int startMonth = now.get(Calendar.MONTH)+1;
   int startDay = now.get(Calendar.DAY_OF_MONTH)+1;
   // ���� ������
   int endYear = now.get(Calendar.YEAR); 
   int endMonth = now.get(Calendar.MONTH)+1; 
   int endDay = now.get(Calendar.DAY_OF_MONTH)+1;
   
   int lastday; int week;
   int year = startYear; int month = startMonth;// year, month: ��� �޺��ڽ� ���ð�
   
   String vaccStr;
   String testStr;
   
   // -------- ȭ�� ���� �����̳�+������Ʈ ---------
   JPanel centerPane = new JPanel(null);
      // ��¥ ����
      JPanel pane1 = new JPanel(new BorderLayout());
         JPanel dateTopPane = new JPanel(new BorderLayout());
            JLabel dateLbl = new JLabel("��¥ ����", JLabel.LEFT);
         JPanel datePane = new JPanel(new BorderLayout());
            JPanel ymPane = new JPanel();
               Integer[] yearArr = {startYear-1, startYear, startYear+1};
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
      // �ð� ����
      JPanel pane2 = new JPanel(new BorderLayout());
         JPanel timeTopPane = new JPanel(new BorderLayout());
            JLabel timeLbl = new JLabel("�ð� ����", JLabel.LEFT);
         JPanel timePane = new JPanel();
            JButton[] timeBtn = new JButton[10];;
            JLabel timeTxt = new JLabel("������ ��¥�� �������ּ���.");
         
      // �ȳ����� + ���/����Ϸ� ��ư
      JPanel pane3 = new JPanel(new BorderLayout());
         JEditorPane rsvTxt = new JEditorPane();
         JPanel btnPane = new JPanel();
            JButton cancleBtn = new JButton("���");
            JButton rsvBtn = new JButton("����Ϸ�");
   
   // ��Ʈ // �÷�
   SetStyle st = new SetStyle();
         
   
   public RsvSelectDate() {}
   
   // [�˻� ����]�� ���
   public RsvSelectDate(int center_code) { // ����� �ڵ�
      this.center_code = center_code;
      
      // ----------- �˻� ���� �Ⱓ ���� (�⺻ 2�޷� ������) ------------
//      // ���� ������
//      startYear = now.get(Calendar.YEAR);
//      startMonth = now.get(Calendar.MONTH)+1;
//      startDay = now.get(Calendar.DAY_OF_MONTH)+1;
      
      // ���� ������ (2�� ��)
      endYear = now.get(Calendar.YEAR); 
      if(startMonth+2>12) { // 2�� �İ� 12������ ũ��
         endMonth = startMonth+2-12;   // ���� ����, ���� �ٲٱ�
         endYear = now.get(Calendar.YEAR)+1;
      } else {
         endMonth = startMonth+2;
      }
      endDay = now.get(Calendar.DAY_OF_MONTH)+1;
      //--------------------------------------------------------
      
      start();
   }
   
   // [��� ����]�� ���
   public RsvSelectDate(int center_code, String vc_type) { // ����� �ڵ�, ��� ����
      this.center_code = center_code;
      this.vc_type = vc_type;
      
      RsvSettingDAO dao = new RsvSettingDAO();
      RsvSettingVO vo = dao.selectRsvSetting();
      
      String startDate = vo.getStartDate();
      String endDate = vo.getEndDate();
      System.out.println(startDate+","+endDate);
      
      //----------- ��� ���� �Ⱓ ���� ----------
      // ���� ������
      startYear = Integer.parseInt(startDate.substring(0,4));
      startMonth = Integer.parseInt(startDate.substring(5,7));
      startDay = Integer.parseInt(startDate.substring(8));
      // ���� ������
      endYear = Integer.parseInt(endDate.substring(0,4));
      endMonth = Integer.parseInt(endDate.substring(5,7));
      endDay = Integer.parseInt(endDate.substring(8));
      //--------------------------------------
      
      start();
   }
   
   public void start() {
//      setBackground(Color.WHITE);
      getCenterInfo(); // �ش� ����� ���� �������� (����Ҹ�, ��ð� 3����)
      
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
      
      // ----- pane2 ------ �ð� ���� �г�
      timeLbl.setFont(st.fnt1);
      timeTopPane.setBorder(new EmptyBorder(10,10,10,0)); // ���� �ϼ����� ����
      timeTopPane.setBackground(Color.WHITE);
      timeTopPane.add(timeLbl);
      setDefaultTimeBtn(); // �ð� ��ư ����
      timePane.setBorder(new EmptyBorder(10,10,10,10)); // ���� �ϼ����� ����
      timePane.setBackground(Color.WHITE);
      pane2.add(timeTopPane, BorderLayout.NORTH);
      pane2.add(timePane, BorderLayout.CENTER);
      pane2.setBounds(0, 330, 400, 140);
      
      // ----- pane3 ------ �ȳ� �ؽ�Ʈ + ���/����Ϸ� �г�
      rsvTxt.setText("���� ��¥�� �ð��� ������ �ּ���.");
      rsvTxt.setFont(st.fnt2);
      rsvTxt.setOpaque(false);
      rsvTxt.setAlignmentX(JEditorPane.CENTER_ALIGNMENT);
      rsvTxt.setFocusable(false);
      rsvTxt.setBorder(new EmptyBorder(10,10,10,10));
      cancleBtn.setFont(st.fnt1);
      cancleBtn.setForeground(Color.WHITE);
      cancleBtn.setBackground(st.clr1);
      cancleBtn.setBorderPainted(false);
      rsvBtn.setFont(st.fnt1);
      rsvBtn.setForeground(Color.WHITE);
      rsvBtn.setBackground(st.clr1);
      rsvBtn.setBorderPainted(false);
      btnPane.add(cancleBtn);   btnPane.add(rsvBtn);
      pane3.add(rsvTxt);
      pane3.add(btnPane, BorderLayout.SOUTH);
      pane3.setBounds(0, 480, 400, 110);
      
      centerPane.setPreferredSize(new Dimension(400,600));
//      centerPane.setBackground(Color.WHITE);
      centerPane.add(pane1);
      centerPane.add(pane2);
      centerPane.add(pane3);
      
      add(centerPane);
      
      // �̺�Ʈ �߻� ���
      yearCB.addActionListener(this);
      monthCB.addActionListener(this);
      leftBtn.addActionListener(this);
      rightBtn.addActionListener(this);
      cancleBtn.addActionListener(this);
      rsvBtn.addActionListener(this);
   }
   
   // �ش� ����� ���� �������� (����Ҹ�, ��ð� 3����)
   public void getCenterInfo() {
      CenterDAO dao = new CenterDAO();
      CenterVO vo = dao.selectCenter(center_code);
      center_name = vo.getCenter_name();
      center_time1 = vo.getCenter_time1();
      center_time2 = vo.getCenter_time2();
      center_time3 = vo.getCenter_time3();
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
         if(i<week-1) {   // ����
            dayBtn[i] = new JButton("");
            dayBtn[i].setBackground(Color.WHITE);
            dayBtn[i].setBorderPainted(false);
            dayBtn[i].setEnabled(false);
            dayPane.add(dayBtn[i]);
         } else if(i<lastday+week-1) { // ��¥ ��ư
            dayBtn[i] = new JButton((i+1)-(week-1)+"");
            
            if((i+1)%7==1) { // �Ͽ���
               dayBtn[i].setForeground(Color.RED);
               if(center_time3.equals("�̿")) dayBtn[i].setEnabled(false);
            }
            else if((i+1)%7==0) { // �����
               dayBtn[i].setForeground(Color.BLUE);
               if(center_time2.equals("�̿")) dayBtn[i].setEnabled(false);
            }
            dayBtn[i].setFont(st.fnt1);
            dayBtn[i].setHorizontalAlignment(JLabel.CENTER);
            dayBtn[i].setBackground(Color.WHITE);
            dayBtn[i].setBorderPainted(false);
            // ��¥ ��Ȱ��ȭ
            if(year==endYear && month==endMonth) { // ----- ���� ������ ���� ��Ȱ��ȭ
               if((i+1)-(week-1) > endDay) dayBtn[i].setEnabled(false);
               if(year==startYear && month==startMonth) { // ----- ���� ������ ���� ��Ȱ��ȭ
                  if((i+1)-(week-1) < startDay) dayBtn[i].setEnabled(false);
               } 
            } else if(year>endYear || (year==endYear && month>endMonth)) {
               dayBtn[i].setEnabled(false);
            } else if(year<startYear || (year==startYear && month<startMonth)) { // ----- ���� ������ ���� ��Ȱ��ȭ
               dayBtn[i].setEnabled(false);
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
         if(yearIdx==startYear-1) {
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
         if(yearIdx==startYear+1) {
            yearCB.setSelectedItem(yearIdx);
            monthCB.setSelectedItem(monthIdx);
         }
      } else {
         monthCB.setSelectedItem(monthIdx+1);
      }
   }
   
   // ó�� �ð� ���� Pane ���� ���� --- ��¥ �����϶�� �ȳ� �޼���
   public void setDefaultTimeBtn() {
      timeTxt.setFont(st.fnt2);
      timePane.add(timeTxt);
   }
   
   // ��¥ ���� �� �ð� ���� ����
   public void setTimeBtn() {
      timePane.removeAll();
      timePane.setVisible(false);
      timePane.setLayout(new GridLayout(2, 5, 12, 12));   // 5�� // ���� ���Ʒ� 12

      String center_time="";
      
      // ���� ��¥ ��������
      now.set(year, month-1, Integer.parseInt(rsvDate));
      week = now.get(Calendar.DAY_OF_WEEK);
      // ���Ͽ� ���� ��ð� ��������
      switch(week) {
         case 7: center_time = center_time2; break; // ����� ��ð�
         case 1: center_time = center_time3; break; // �Ͽ��� ��ð�
         default: center_time = center_time1; // ���� ��ð�
      }
      
      if(center_time.equals("�̿")) {
         timePane.setLayout(new FlowLayout());
         timeTxt.setText("����� �̿");
         timeTxt.setFont(st.fnt2);
         timePane.add(timeTxt);
      } else {
         int openTime = Integer.parseInt(center_time.substring(0,2));
         int closeTime = Integer.parseInt(center_time.substring(8,10));

         // 00:00~24:00 ���� �ð��� ���� ���� �ð� ����
         if(openTime<8) openTime=9;
         if(closeTime==0 || closeTime>8) closeTime=19;
         //-------------------------------------------
         
         for(int i=0; i<10; i++) {
            if(i+openTime<10) timeBtn[i] = new JButton("0"+(i+openTime)+":00");
            else timeBtn[i] = new JButton(i+openTime+":00");   // ��ư 10���� ����
            if(i<closeTime-openTime) {   // ��ð� ��ư 10������ ���� ������ �׸�ŭ�� �гο� �����ϱ�
               timeBtn[i].setFont(st.fnt2);
               timeBtn[i].setBorder(new LineBorder(Color.GRAY, 1));
               timeBtn[i].setBackground(new Color(255,255,255)); // ��ư ��Ȱ��ȭ �� �ּ� ó��
               // �ð� ��ư ��Ȱ��ȭ �� �Ʒ� �ּ� ���� ���
//               timeBtn[i].setBackground(new Color(220,220,220));
//               timeBtn.setBorderPainted(false);
//               timeBtn.setEnabled(false);
               timeBtn[i].addActionListener(this);
               timePane.add(timeBtn[i]);
            } else { // �������� ���гη� ä���� ���̾ƿ� ��Ʈ������ �ʰ� ��.
               JPanel emptyPane = new JPanel();
               emptyPane.setOpaque(false); // �����ϰ�
               timePane.add(emptyPane);
            }
         } // for
      }
      timePane.setVisible(true);
   }
   
   // ��¥ �����ϸ� ����
   public void selectDate() {
      for(int i=0; i<dayBtn.length; i++) {   // ���� �� �� ������ ��ư ���� ������� ��������
         if((i+1)%7==1) dayBtn[i].setForeground(Color.RED);
         else if((i+1)%7==0) dayBtn[i].setForeground(Color.BLUE);
         else dayBtn[i].setForeground(Color.BLACK);
         dayBtn[i].setBackground(Color.WHITE);
      }
      setTimeBtn();   // �ð� ��ư ����
      rsvTxt.setText("���� �ð��� ������ �ּ���.");
   }
   
   
   // [�ڷγ� �˻� ����] �ð� �����ϸ� ���� --- �˻� ���� ���� ���
   public void selectTime() {
      for(int i=0; i<timeBtn.length; i++) {   // ���� �� �� ������ ��ư ���� ������� ��������
         timeBtn[i].setForeground(Color.BLACK);
         timeBtn[i].setBackground(Color.WHITE);
         timeBtn[i].setBorderPainted(true);
      }
      // �˻� ������ ���
      testStr = year + "�� " + month + "�� " + rsvDate + "�� " + rsvTime + "�� " + center_name + "���� �ڷγ� �˻� �����Դϴ�.";
		rsvTxt.setText(testStr);
   }
   
   // [��� ����] �ð� �����ϸ� ���� --- ��� ���� ���� ���
   public void selectTime(String vc_type) {
      for(int i=0; i<timeBtn.length; i++) {   // ���� �� �� ������ ��ư ���� ������� ��������
         timeBtn[i].setForeground(Color.BLACK);
         timeBtn[i].setBackground(Color.WHITE);
         timeBtn[i].setBorderPainted(true);
      }
      // ��� ������ ���
      vaccStr = year + "�� " + month + "�� " + rsvDate + "�� " + rsvTime + "�� " + center_name + "���� " + vc_type
				+ " ��� ���� ���� �����Դϴ�.";
		rsvTxt.setText(vaccStr);
   }
   
   
   
   // [�ڷγ� �˻� ����] ���
   public void insertTest() {
      TestVO vo = new TestVO();
      TestDAO dao = new TestDAO();
      
      vo.setUser_id(user_id);
      vo.setCenter_code(center_code);
      vo.setRsv_date(year+"/"+month+"/"+rsvDate);
      vo.setRsv_hour(rsvTime);

      int cnt = dao.insertTestData(vo);
      if(cnt>0) {
         JOptionPane.showMessageDialog(this, "�ڷγ� �˻� ������ �Ϸ�Ǿ����ϴ�.");
			UserTabMenu.centerpane.removeAll();
			TabCheckReservationinformation rsvCheck = new TabCheckReservationinformation();
			UserTabMenu.centerpane.add(rsvCheck);
			TestRsvCheck ct = new TestRsvCheck(MainTitle.user_id);
			rsvCheck.inData.removeAll();
			rsvCheck.inData.add(ct);

			// ����-----------------------------------------------------------------------------------------����
			sendMessage(testStr);
      } else {
         JOptionPane.showMessageDialog(this, "�ڷγ� �˻� ������ �����Ͽ����ϴ�. �ٽ� �õ��� �ּ���.");
      }
   }
   
   // [��� ����] ���
   public void insertRsv() {
      RsvVO vo = new RsvVO();
      RsvDAO dao = new RsvDAO();
      
      vo.setUser_id(user_id);
      vo.setCenter_code(center_code);
      vo.setVc_type(vc_type);
      vo.setRsv_date(year+"/"+month+"/"+rsvDate);
      vo.setRsv_hour(rsvTime);

      int cnt = dao.insertRsvData(vo);
      if(cnt>0) { // ���� ���� ��
         JOptionPane.showMessageDialog(this, "��� ���� ������ �Ϸ�Ǿ����ϴ�.");
         UserTabMenu.centerpane.removeAll();
			TabCheckReservationinformation rsvCheck = new TabCheckReservationinformation();
			UserTabMenu.centerpane.add(rsvCheck);
			VaccineRsvCheck cr = new VaccineRsvCheck(MainTitle.user_id);
			rsvCheck.inData.removeAll();
			rsvCheck.inData.add(cr);
			// ����-----------------------------------------------------------------------------------------����
			sendMessage(vaccStr);
      } else { // ���� ���� ��
         // ������ ��� ���� �ٽ� ���ϱ�
         String vc_col=getVaccCol();
         VaccineDAO vdao = new VaccineDAO();
         int cnt2 = vdao.plusVaccData(vc_col, center_code);
         if(cnt>0) { // ���� ���� ���� ��
            JOptionPane.showMessageDialog(this, "��� ���� ������ �����߽��ϴ�. �ٽ� �õ��� �ּ���.");
         } else { // ���� ���� ���� ��
            JOptionPane.showMessageDialog(this, "��� ���� ������ �����߽��ϴ�. �����ڿ��� �������ּ���.");
         }
      }
   }
   
   // ���� �Ϸ� �� �ش� ������� �ش� ��� ���� -1 �����ϱ�
   public void minusVaccCount() {
      String vc_col=getVaccCol();
      
      // ��� ���� ���� �ϱ�
      VaccineDAO dao = new VaccineDAO();
      int cnt = dao.minusVaccData(vc_col, center_code);
      if(cnt>0) { // ���� ���� ���� ��
         insertRsv(); // [��� ����] ����ϱ�
      } else { // ���� ���� ���� ��
         JOptionPane.showMessageDialog(this, "��� ���� ������ �����߽��ϴ�. �����ڿ��� �������ּ���.");
      }
   }
   
   
   // �ش� ��� ������ �÷��� ������
   public String getVaccCol() {
      String vc_col = "";
      if(vc_type.equals("�Ἶ")) vc_col = "jansen";
      else if(vc_type.equals("�ƽ�Ʈ������ī")) vc_col = "az";
      else if(vc_type.equals("ȭ����")) vc_col = "pfizer";
      else if(vc_type.equals("�����")) vc_col = "moderna";
      
      return vc_col;
   }
   
   
   //
   public void cancleRsv() {
      int result = JOptionPane.showConfirmDialog(this, "���� ����Ͻðڽ��ϱ�?", "���� ���", JOptionPane.OK_CANCEL_OPTION);
      if(result==JOptionPane.OK_OPTION) {
         if(vc_type==null || vc_type.equals("")) { // �˻� ���� ���
        	 MainTitle.lbl.setText("�˻� ����");
				UserTabMenu.centerpane.removeAll();
				SearchCenterSplit.centerName = null;
				SearchCenterMain sCenter = new SearchCenterMain();
				RsvSouthBtn sBtn = new RsvSouthBtn();
				UserTabMenu.centerpane.add(sCenter);
				sBtn.rsvBtn.setText("�ڷγ� �˻� �����ϱ�");
				UserTabMenu.centerpane.add(BorderLayout.SOUTH, sBtn);
				UserTabMenu.centerpane.updateUI();
         } else { // ��� ���� ���
        	 MainTitle.lbl.setText("��� ���� ����");
				UserTabMenu.centerpane.removeAll();
				SearchCenterSplit.centerName = null;
				RsvVaccineRadio.vc_type = null;
				SearchCenterMain sCenter = new SearchCenterMain();
				UserTabMenu.centerpane.removeAll();
				RsvVaccineRadio vRadio = new RsvVaccineRadio();
				UserTabMenu.centerpane.add(BorderLayout.NORTH, vRadio);
				vRadio.rBtn0.setVisible(false);
				UserTabMenu.centerpane.add(sCenter);

				RsvSouthBtn sBtn = new RsvSouthBtn();
				sBtn.rsvBtn.setText("��� ���� �����ϱ�");
				UserTabMenu.centerpane.add(BorderLayout.SOUTH, sBtn);
				UserTabMenu.centerpane.updateUI();
         }
      }
   }
   

   @Override
   public void actionPerformed(ActionEvent ae) {
      Object evt = ae.getSource();
      String evtStr = ae.getActionCommand();
      if(evt instanceof JComboBox) {   // �޺��ڽ� �̺�Ʈ�� �߻��ϸ�
         year = (Integer)yearCB.getSelectedItem();
         month = (Integer)monthCB.getSelectedItem();
         setDate();   // Ķ���� �缳��
      } else if(evt instanceof JButton) {   // ��ư �̺�Ʈ�� �߻��ϸ�
         JButton evtBtn = (JButton) ae.getSource();
         if(evt==leftBtn) {   // < Ķ���� ���� ��ư
            setPrev();
            
         } else if(evt==rightBtn) {   // > Ķ���� ���� ��ư
            setNext();
            
         } else if(evtStr.equals("���")) {
            cancleRsv(); // ���
            
         } else if(evtStr.equals("����Ϸ�")) {
            if(vc_type==null || vc_type.equals("")) insertTest(); // [�˻� ����]
            else minusVaccCount(); // [��� ����] --- ��� ��� �������� ��
            
         } else if(evtStr.indexOf(":")!=-1){ // �ð� �����ϸ�
            rsvTime = evtStr;   // ���õ� �ð��� ����
            if(vc_type==null || vc_type.equals("")) selectTime();   // �ð� ���� // �˻� ���� ��--- ��� ���� ���
            else selectTime(vc_type); // �ð� ���� // ��� ���� ��--- ��� ���� ���� ���
            evtBtn.setForeground(Color.WHITE);   // ���õ� �ð� ��ư �� ����
            evtBtn.setBackground(st.clr1);
            evtBtn.setBorderPainted(false);
            
         } else {   // ��¥ �����ϸ�
            rsvDate = evtStr; // ���õ� ��¥(��)�� ����
            selectDate();   // ��¥ ���� �޼���
            evtBtn.setForeground(Color.WHITE);   // ���õ� ��¥ ��ư �� ����
            evtBtn.setBackground(st.clr1);
         }
      }
   }
   
	public void sendMessage(String str) {
		String api_key = "NCSFF6HVTATSPNV2";
		String api_secret = "EXFTHIJS8DYJOSKBYDNINSDO65MSW3UM";
		Message coolsms = new Message(api_key, api_secret);

		// 4 params(to, from, type, text) are mandatory. must be filled
		HashMap<String, String> params = new HashMap<String, String>();
		String text = str;

		UserDAO userdao = new UserDAO();
		UserVO uservo = userdao.setMyInfo(MainTitle.user_id);
		params.put("to", uservo.getUser_tel());
		params.put("from", "01087885202");
		params.put("type", "SMS");
		params.put("text", text);
		params.put("app_version", "test app 1.2"); // application name and version

		try {
			JSONObject obj = (JSONObject) coolsms.send(params);
			System.out.println(obj.toString());

		} catch (Exception e) {
			System.out.println("���� �޽��� �߼� ����");
		}
	}
}
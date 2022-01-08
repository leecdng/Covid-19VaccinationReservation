package admin.home;

//������������ �����ϴ°� 
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import org.json.simple.JSONObject;

import database.AdminDAO;
import database.AdminVO;
import database.LocDAO;
import database.UserDAO;
import database.UserVO;
import net.nurigo.java_sdk.api.Message;
import user.home.Firstpage;
import user.home.MainTitle;
import user.home.SetStyle;
import user.home.UserTabMenu;

public class AdminInfo extends JPanel implements ActionListener {// �ǿ���������

   JPanel centerPanel = new JPanel(new BorderLayout());// ������� �������
   JPanel inPanel = new JPanel(null);
   JLabel idlbl = new JLabel("���̵�");
   JLabel idlbl2 = new JLabel();
   // ���Ȯ��
   JLabel pwlbl = new JLabel("��й�ȣ");
   JPasswordField pwfd = new JPasswordField(25);// ��й�ȣ �ؽ�Ʈ �ʵ�
   JLabel pwlbl2 = new JLabel("��й�ȣ Ȯ��");
   JPasswordField pwfd2 = new JPasswordField(25);// ���̵��ؽ�Ʈ�ʵ�

   // �̸�
   JLabel namelbl = new JLabel("�̸�");
   JLabel namelbl2 = new JLabel();
   // �ֹ�
   JLabel numlbl = new JLabel("�ֹε�Ϲ�ȣ");
   JLabel numlbl2 = new JLabel();
   // �޴�����ȣ
   JLabel tellbl = new JLabel("�޴�����ȣ");
   JTextField telfd = new JTextField(25);
   JButton telbtn = new JButton("�����ϱ�");

   int approval =0;

   public JLabel affiliationlbl = new JLabel("�Ҽ�");

   JButton homebtn[] = new JButton[3];
   String[] col = { "���", "���� �Ϸ�", "ȸ�� Ż��" };

   public JComboBox<String> affiliation;

   // ��ư�г�
   JPanel btnPanel = new JPanel(new GridLayout(1, 3, 10, 10));
   // ��Ÿ�ϰ�ü����
   SetStyle st = new SetStyle();
   AdminVO adminvo;
   UserVO uservo;

   int aprroval =0;

   public AdminInfo() {
      // JFrame�� flow�� ������
      setLayout(new FlowLayout());

      centerPanel.setPreferredSize(new Dimension(600, 540));
      centerPanel.setBackground(Color.yellow);
      centerPanel.add(inPanel);
      centerPanel.add(BorderLayout.SOUTH, btnPanel);
      add(centerPanel);

      inPanel.add(idlbl);// ���̵��
      idlbl.setBounds(0, 30, 200, 200);
      idlbl.setFont(st.fnt2);
      inPanel.add(idlbl2); // ���̵� member1 ��
      idlbl2.setBounds(165, 109, 280, 40);
      idlbl2.setFont(st.fnt2);

      // ��й�ȣ
      inPanel.add(pwlbl);// ��й�ȣ
      pwlbl.setBounds(0, 80, 200, 200);
      pwlbl.setFont(st.fnt2);
      inPanel.add(pwfd); // ��� �ؽ�Ʈ �ʵ�
      pwfd.setBounds(165, 160, 280, 40);
      pwfd.setFont(st.fnt2);

      inPanel.add(pwlbl2);// ��й�ȣȮ��
      pwlbl2.setBounds(0, 130, 200, 200);
      pwlbl2.setFont(st.fnt2);
      inPanel.add(pwfd2); // ���Ȯ�� �ؽ�Ʈ �ʵ�
      pwfd2.setBounds(165, 210, 280, 40);
      pwfd2.setFont(st.fnt2);

      inPanel.add(namelbl); // �̸� ��
      namelbl.setBounds(0, 210, 200, 200);
      namelbl.setFont(st.fnt2);
      inPanel.add(namelbl2);
      namelbl2.setBounds(165, 290, 280, 40);
      namelbl2.setFont(st.fnt2);

      inPanel.add(numlbl);// �ֹε�Ϲ�ȣ ��
      numlbl.setBounds(0, 260, 200, 200);
      numlbl.setFont(st.fnt2);
      inPanel.add(numlbl2);// �ֹε�Ϲ�ȣ ���ڶ�
      numlbl2.setBounds(165, 340, 280, 40);
      numlbl2.setFont(st.fnt2);

      inPanel.add(tellbl);// �޴��� ��ȣ ��
      tellbl.setBounds(0, 310, 200, 200);
      tellbl.setFont(st.fnt2);
      inPanel.add(telfd);// �޴��� ��ȣ �ؽ�Ʈ�ʵ�
      telfd.setBounds(165, 390, 280, 40);
      // �޺��ڽ�

      // locdao �޺��ڽ� �Ҽ� �߰�
      LocDAO locdao = new LocDAO();
      Vector<String> list1 = locdao.combo1();
      affiliation = new JComboBox<String>(list1);
      // �޺��ڽ���
      affiliation.insertItemAt("�Ҽ�", 0);
      affiliation.setSelectedIndex(0);
      affiliation.setBounds(165, 450, 100, 30);
      affiliation.setBackground(Color.white);
      affiliation.setFont(st.fnt3);
      inPanel.add(affiliationlbl);
      affiliationlbl.setBounds(0, 370, 200, 200);
      affiliationlbl.setFont(st.fnt2);

      inPanel.add(affiliation);

      inPanel.add(telbtn);// �����ϱ� ��ư
      telbtn.setBounds(470, 390, 130, 40);
      telbtn.setFont(st.fnt1);
      telbtn.setBackground(st.clr1);// ��ư���� ������.
      telbtn.setForeground(Color.WHITE); 
      telbtn.setFocusPainted(false);
      // Ȩ���� , �����Ϸ�, ȸ��Ż�� ��ư
      for (int i = 0; i < col.length; i++) {
         homebtn[i] = new JButton(col[i]);
         btnPanel.add(homebtn[i]);
         homebtn[i].setFont(st.fnt1);
         homebtn[i].setBackground(st.clr1);
         homebtn[i].setForeground(Color.WHITE); 
         homebtn[i].setFocusPainted(false);

         //         setSize(1000, 660);
         //        setVisible(true);
         //         setDefaultCloseOperation(DISPOSE_ON_CLOSE);
      }
      setDefaultInfo();
      homebtn[0].addActionListener(this);
      homebtn[1].addActionListener(this);
      homebtn[2].addActionListener(this);
      telbtn.addActionListener(this);
   }

   // �����������ҷ�����.
   public void setDefaultInfo() {
      if(MainTitle.mode==1) {
         UserDAO userdao = new UserDAO();// �ٿ���ä�� ���� �ٿ����� ���̵� ���� �����ϸ��.
         uservo = userdao.setMyInfo(MainTitle.user_id); // �ٿ��� ���̵� �ٰ� //////////////���̵�ִ°�
         idlbl2.setText(uservo.getUser_id());// ���̵��
         pwfd.setText(uservo.getUser_pw());// �н������ؽ�Ʈ

         namelbl2.setText(uservo.getUser_name());
         // ���������ϴ� �۾�
         String num1 = uservo.getUser_num().substring(0, 6);
         String num2 = uservo.getUser_num().substring(6, 7);
         numlbl2.setText(num1+" - "+num2+"******");// �ֹι�ȣ�޴°� //�������
         telfd.setText(uservo.getUser_tel());
      }
      else if(MainTitle.mode==2) {
         AdminDAO admindao = new AdminDAO();// �ٿ���ä�� ���� �ٿ����� ���̵� ���� �����ϸ��.
         adminvo = admindao.setMyInfo(MainTitle.admin_id); // �ٿ��� ���̵� �ٰ� //////////////���̵�ִ°�
         idlbl2.setText(adminvo.getAdmin_id());// ���̵��
         pwfd.setText(adminvo.getAdmin_pw());// �н������ؽ�Ʈ

         namelbl2.setText(adminvo.getAdmin_name());
         // ���������ϴ� �۾�
         String num1 = adminvo.getAdmin_num().substring(0, 6);
         String num2 = adminvo.getAdmin_num().substring(6, 7);
         numlbl2.setText(num1+" - "+num2+"******");// �ֹι�ȣ�޴°� //�������
         telfd.setText(adminvo.getAdmin_tel());
         affiliation.setSelectedItem(adminvo.getAdmin_local());
      }


   }
   // ������ ���� �ҷ��°� ����21.07.31

   public void modifyDefaultInfo() {
      // ���� �˻�޼��带 �ؾ��Ѵ�. �޼��带 �ϳ� �� ���� �ȿ� ����ؾ���.
      int cnt = 0;

      if(MainTitle.mode==1) {
         UserDAO userdao = new UserDAO();
         uservo = new UserVO();
         //�ؽ�Ʈ�ʵ��н������ �����ͺ��̽� �н������ Ȯ��
         // if(pwlbl.getText().equals(String.valueOf(pwfd2.getPassword()))){

         if (String.valueOf(pwfd.getPassword()).equals(String.valueOf(pwfd2.getPassword()))) {

            uservo.setUser_id(idlbl2.getText());
            uservo.setUser_pw(String.valueOf(pwfd2.getPassword()));
            uservo.setUser_tel(telfd.getText());
            cnt = userdao.updateInfo(uservo);
            JOptionPane.showMessageDialog(this, "�������� ��������");
            returnfirst();
            //System.out.println(cnt);
         }
         else if(MainTitle.mode==2) {
            AdminDAO admindao = new AdminDAO();
            adminvo = new AdminVO();
            //�ؽ�Ʈ�ʵ��н������ �����ͺ��̽� �н������ Ȯ��
            // if(pwlbl.getText().equals(String.valueOf(pwfd2.getPassword()))){

            if (String.valueOf(pwfd.getPassword()).equals(String.valueOf(pwfd2.getPassword()))) {

               adminvo.setAdmin_id(idlbl2.getText());
               adminvo.setAdmin_pw(String.valueOf(pwfd2.getPassword()));
               adminvo.setAdmin_tel(telfd.getText());
               cnt = admindao.updateInfo(adminvo);
               JOptionPane.showMessageDialog(this, "�������� ��������");
               returnfirst();
               //System.out.println(cnt);
            } else {
               JOptionPane.showMessageDialog(this, "����");
            }
         }
      }
   }


   public void modifyCheck() {
      System.out.println("����...");
      if (telfd.getText().equals("") || telfd.getText() == null) { // �� �ȿ� �ִ� ������ ��������Ѵ�
         JOptionPane.showMessageDialog(this, "��ȭ��ȣ�� �ǹٸ��� �Է��ϼ���");
      } else {
         modifyDefaultInfo();
      }

   }// else(telfd.equals(""){
   // JOptionPane.showMessageDialog(this, "��ȭ��ȣ�� �ǹٸ��� �Է��ϼ���");
   // ȸ��Ż�� ��ư�� ������

   // }\

   // ȸ��Ż����
   public void drop() {
      if(MainTitle.mode==1) {
         UserDAO userdao = new UserDAO();
         // ����� vo���� ���̵� �޾ƿͼ� ���� �޼��带 ����
         int result = userdao.deleteUser(uservo.getUser_id());
         if (result > 0) {// ȸ��������
            JOptionPane.showMessageDialog(this, " Ż���ϼ̽��ϴ�.");
            
            MainTitle.lbl2.setText("ȸ������");
            MainTitle.lbl3.setText("�α���");
            MainTitle.user_id=null;
            UserTabMenu.centerpane.removeAll();
            Firstpage first = new Firstpage(1);
            UserTabMenu.centerpane.add(first);
            UserTabMenu.centerpane.updateUI();
         } else {// ȸ������������
            JOptionPane.showMessageDialog(this, "������ �����Ͽ����ϴ�.");
         } // result!>0
      }

      else if(MainTitle.mode==2) {
         AdminDAO admindao = new AdminDAO();
         // ����� vo���� ���̵� �޾ƿͼ� ���� �޼��带 ����
         int result = admindao.deleteAdmin(adminvo.getAdmin_id());
         if (result > 0) {// ȸ��������
            JOptionPane.showMessageDialog(this, " Ż���ϼ̽��ϴ�.");
            MainTitle.lbl2.setText("ȸ������");
            MainTitle.lbl3.setText("�α���");
            MainTitle.admin_id=null;
            UserTabMenu.centerpane.removeAll();
            Firstpage first = new Firstpage(2);
            UserTabMenu.centerpane.add(first);
            UserTabMenu.centerpane.updateUI();
         } else {// ȸ������������
            JOptionPane.showMessageDialog(this, "Ż���� �����Ͽ����ϴ�.");
         } // result!>0
      }

   }
   ////////////////////////////////////////////////////////////
   @Override
   public void actionPerformed(ActionEvent e) {
      Object event = e.getSource();
      if(event == homebtn[0]) {
         returnfirst();
      }
      //�����ϱ�
      else if (event == homebtn[1]&&approval==1&&String.valueOf(pwfd.getPassword()).equals(String.valueOf(pwfd2.getPassword()))) {
         modifyCheck();
         //returnfirst();
      } else if (event == homebtn[2]&&approval==1) { // ȸ��Ż��//
         System.out.println("ȸ��Ż��Ф�");
         int msg;
         if (String.valueOf(pwfd.getPassword()).equals(String.valueOf(pwfd2.getPassword()))) {
        	 if(MainTitle.mode==1) {
        		 msg = JOptionPane.showConfirmDialog(null, "������ Ż�� �Ͻðڽ��ϱ�? ���� ���೻���� ��ҵ˴ϴ�.","Ż�� �ȳ�", JOptionPane.OK_CANCEL_OPTION);
        	 }
        	 else {
        		 msg = JOptionPane.showConfirmDialog(null, "������ Ż�� �Ͻðڽ��ϱ�?", "Ż�� �ȳ�", JOptionPane.OK_CANCEL_OPTION);
        	 }
            
            if (msg == 0)
               drop();
            else if (msg == 1) {
            }
         }else{
            JOptionPane.showMessageDialog(this, "��й�ȣ�� ���� �ʽ��ϴ�");
         }
      }else if(event==telbtn){
         phone();   
      }else if(!String.valueOf(pwfd.getPassword()).equals(String.valueOf(pwfd2.getPassword()))) {
         JOptionPane.showMessageDialog(this, "��й�ȣ�� ���� �ʽ��ϴ�");
      }
      else if(approval==0){//�ٸ���ư �����ÿ�
         JOptionPane.showMessageDialog(this, "������ ���ּ���");
      }

   }
   ////////////////////////////////////////////////////////////////////////  
   
   public void returnfirst() {
      Firstpage firstmain = new Firstpage(MainTitle.mode);
      UserTabMenu.centerpane.removeAll();
      UserTabMenu.centerpane.add(firstmain);
      UserTabMenu.centerpane.updateUI();
   }
   
   public void phone() {
      //�������� �ƴ��� Ȯ��
      if(telfd.getText()==null || telfd.getText().equals("") || telfd.getText().length()!=11) {
         JOptionPane.showMessageDialog(null, "�޴��� ��ȣ�� ��Ȯ�ϰ� �Է����ּ���.");
      }
      else {//������� ���� ��� �߿� ����Ǵ� if����

         AdminDAO dao = new AdminDAO();
         UserDAO dao2 = new UserDAO();
         int check;

         if(MainTitle.mode==1) {
            check = dao.telNumCheck(telfd.getText());
         } else {
            check = dao2.telNumCheck(telfd.getText());
         }

         checkPhoneNumeber(check);
      }
   }

   //�޴��� ��ȣ�� ���� ���� Ȯ��
   public void checkPhoneNumeber(int check) {
      if(check==0){//������ ������ �̹� ���Ե� ����̴�.
         JOptionPane.showMessageDialog(null, "�̹� ���Ե� ��ȣ�Դϴ�.");

      }
      else {
         //��� ���� �����鼭 ���ǿ� ������ ���//�����Ͷ� ���� ������� ������ ���ԵǾ� ���� �ʾƾ� ���ǿ� �����Ǵ°�.
         int ran=(int)Math.round(Math.random()*(9999-1000+1))+1000;    //1000~9999������.  
         System.out.println(ran);
         sendMessage(ran,telfd.getText()); //-----------------------------------------------------------------����
         String msg=JOptionPane.showInputDialog(null, "������ȣ�� �Է����ּ���");
         if(msg == null) 
            JOptionPane.showMessageDialog(null, "������ȣ�� ����� �Է����ּ���.");
         else if(msg.equals(""+ran)){
            // String���� ��ȯ����
            JOptionPane.showMessageDialog(null, "�����Ǿ����ϴ�.");
            approval = 1;// 1�� ������ �Ǿ �Ѿ��,.
            telfd.setEnabled(false);   
         }
         else 
            JOptionPane.showMessageDialog(null, "���� ����");   
      }
   }
   
   //�޽��� �߼�
   public void sendMessage(int ran,String fulltel) {
      String api_key = "NCSFF6HVTATSPNV2";
      String api_secret = "EXFTHIJS8DYJOSKBYDNINSDO65MSW3UM";
      Message coolsms = new Message(api_key, api_secret);

      // 4 params(to, from, type, text) are mandatory. must be filled
      HashMap<String, String> params = new HashMap<String, String>();
      String text = "������ȣ�� �Է����ּ��� ["+String.valueOf(ran)+"]";
      params.put("to", fulltel);
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

   public static void main(String[] args) {
      //new AdminInfo();
   }
}
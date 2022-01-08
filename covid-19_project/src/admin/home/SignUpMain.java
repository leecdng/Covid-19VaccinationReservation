package admin.home;
//ȸ������<��ȭ> ������� ������. ������ ��ϿϷ� admindao���� 

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

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

public class SignUpMain extends JPanel implements ActionListener ,MouseListener { // ���

   // ȸ������ (������)

   JPanel mainpane = new JPanel(new BorderLayout());

   // ���� (üũ�ڽ�, ��ư)
   JPanel southpane = new JPanel(new BorderLayout());
   JPanel sNorthpane = new JPanel(new GridLayout(2, 1));
   JPanel cPane1 = new JPanel();
   JPanel cPane2 = new JPanel();
   JCheckBox ch1 = new JCheckBox("(�ʼ�)�̿��� ����"); //��Ʈ�ٲٱ�
   JCheckBox ch2 = new JCheckBox("(�ʼ�)�������� ���� �� �̿� ����");//��Ʈ�ٲٱ�

   JLabel chlbl1 = new JLabel(">[�ڼ��� ����]");
   JLabel chlbl2 = new JLabel(">[�ڼ��� ����]");
   JPanel sSouthpane = new JPanel();
   JButton btn1 = new JButton("���");
   JButton btn2 = new JButton("Ȯ��");

   // ����(��)
   // String[] title = {"���̵� ","��й�ȣ ","��й�ȣ Ȯ�� ","�̸� ","�ֹε�Ϲ�ȣ ","�޴�����ȣ ","�Ҽ� "};
   public JLabel[] lbl = {new JLabel("���̵�"), new JLabel("��й�ȣ"), new JLabel("��й�ȣ Ȯ��"), new JLabel("�̸�"), new JLabel("�ֹε�Ϲ�ȣ"), new JLabel("�޴�����ȣ"), new JLabel("�Ҽ�")};
   Font font = new Font("���� ���", 1, 20);
   JPanel formpane = new JPanel(new GridLayout(lbl.length, 1));
   JTextField idformTf = new JTextField(30);
   JPasswordField pwTf = new JPasswordField(30);
   JPasswordField pwTf2 = new JPasswordField(30);
   JTextField nameformTf = new JTextField(30);

   JTextField formTf2 = new JTextField(14); 
   JPasswordField formTf21 = new JPasswordField(14);//�ֹε��ڸ�


   JTextField[] formTf3 = { new JTextField(6), new JTextField(7), new JTextField(7) };
   JButton telbtn = new JButton("����");

   public JComboBox<String> cb1;
   LocDAO locdao = new LocDAO();

   SetStyle style = new SetStyle();
   Firstpage firstmain = new Firstpage(MainTitle.mode);

   int approval =0;
   int mode;
   int check;
   
   String fulltel;

   public SignUpMain() {
      MainTitle.lbl.setText("ȸ������");
      add(BorderLayout.NORTH, mainpane);
      // ���� (üũ�ڽ�, ��ư)
      mainpane.add(BorderLayout.SOUTH, southpane);
      southpane.add(BorderLayout.NORTH, sNorthpane);
      southpane.add(BorderLayout.SOUTH, sSouthpane);
      sNorthpane.add(cPane1);
      sNorthpane.add(cPane2);
      cPane1.add(ch1);
      cPane1.add(chlbl1);
      cPane2.add(ch2);
      cPane2.add(chlbl2);
      sSouthpane.add(btn1);
      sSouthpane.add(btn2);

      // ����(��)
      mainpane.add(BorderLayout.NORTH, formpane);

      for (int i = 0; i < lbl.length; i++) {
         JPanel pane = new JPanel();
         JPanel tfpane = new JPanel();
         JPanel lblpane = new JPanel();

         formpane.add(pane);

         pane.add(lblpane);
         pane.add(tfpane);

         lblpane.setOpaque(true);
         //lblpane.setBackground(Color.LIGHT_GRAY);

         lblpane.add(lbl[i]);
         lbl[i].setFont(font);

         Dimension size1 = new Dimension(200, 40);
         lblpane.setPreferredSize(size1);

         Dimension size2 = new Dimension(350, 40);
         tfpane.setPreferredSize(size2);

         if (i == 0) {
            tfpane.add(idformTf);
            //idformTf.setText("");
         }

         else if (i == 1) {
            tfpane.add(pwTf);
            //pwTf.setText("");
         }

         else if (i == 2) {
            tfpane.add(pwTf2);
            // pwTf2.setText("");
         }

         else if (i == 3) {
            tfpane.add(nameformTf);
            //nameformTf.setText("");
         }

         else if (i == 4) { // �ֹε�Ϲ�ȣ
            JLabel lb1 = new JLabel("-");
            tfpane.add(formTf2);
            //formTf2[0].setText("");
            tfpane.add(lb1);
            tfpane.add(formTf21);
            //formTf2[1].setText("");
         } else if (i == 5) { // �޴��� ��ȣ
            JLabel lb1 = new JLabel("-");
            JLabel lb2 = new JLabel("-");
            tfpane.add(formTf3[0]);
            //formTf3[0].setText("");
            tfpane.add(lb1);
            tfpane.add(formTf3[1]);
            //formTf3[1].setText("");
            tfpane.add(lb2);
            tfpane.add(formTf3[2]);
            //formTf3[2].setText("");
            tfpane.add(telbtn);
            telbtn.setFont(style.fnt1);
            telbtn.setBackground(style.clr1);
            telbtn.setForeground(Color.white);
         } else if (i == 6) {// �Ҽ�
            Vector<String> list1 = locdao.combo1();
            cb1 = new JComboBox(list1);
            cb1.insertItemAt("�á���", 0);
            cb1.setSelectedIndex(0);
            cb1.setFont(style.fnt16);
            cb1.setBackground(Color.WHITE);
            tfpane.add(cb1);
         }

      }

      btn1.setBackground(style.clr1);
      btn2.setBackground(style.clr1);
      btn1.setForeground(Color.white);
      btn2.setForeground(Color.white);
      btn1.setFont(style.fnt1);
      btn2.setFont(style.fnt1);
      cb1.setBackground(Color.white);
      //////////////////�޺��ڽ��۾�����
      ch1.setFont(style.fnt1);
      ch2.setFont(style.fnt1);
      chlbl1.setFont(style.fnt1);//�ڼ������� ����Ʈ
      chlbl2.setFont(style.fnt1);

      //      setSize(1200, 800);
      //      setVisible(true);
      //      setDefaultCloseOperation(DISPOSE_ON_CLOSE);

      btn1.addActionListener(this);
      btn2.addActionListener(this);
      telbtn.addActionListener(this);
      chlbl1.addMouseListener(this);
      chlbl2.addMouseListener(this);

   }


   ////////////////////////////////////////////////////////////

   //��� ���� �� �����ϸ� ��μ� ȸ������! DB�� ������ ����!
   public void adminSign() {// ȸ������
      int cnt=0;
      if(MainTitle.mode==1) { //�����
         System.out.println("����� ȸ������");
         UserVO vo = new UserVO();
         vo.setUser_id(idformTf.getText());
         vo.setUser_pw(String.valueOf(pwTf.getPassword()));// ��й�ȣ

         vo.setUser_name(nameformTf.getText());// �̸�
         vo.setUser_num(formTf2.getText() + (String.valueOf(formTf21.getPassword())));// �ֹε�Ϲ�ȣ
         vo.setUser_tel(formTf3[0].getText() + formTf3[1].getText() + formTf3[2].getText());

         UserDAO dao = new UserDAO();
         cnt = dao.userSignUp(vo);

         if (cnt > 0) {
            JOptionPane.showMessageDialog(this, "ȸ������ �Ϸ�.");
            //ȸ������ �Ϸ� �� �ٽ� Ȩ����
            returnHome();
         }
      }
      else if(MainTitle.mode==2) { //������
         System.out.println("������ ȸ������");
         AdminVO vo = new AdminVO();
         //System.out.println(String.valueOf(pwTf.getPassword()));
         vo.setAdmin_id(idformTf.getText());
         vo.setAdmin_pw(String.valueOf(pwTf.getPassword()));// ��й�ȣ

         vo.setAdmin_name(nameformTf.getText());// �̸�
         vo.setAdmin_num(formTf2.getText() + (String.valueOf(formTf21.getPassword())));// �ֹε�Ϲ�ȣ
         vo.setAdmin_tel(formTf3[0].getText() + formTf3[1].getText() + formTf3[2].getText());
         // ���ؽ�Ʈ������ string ���̶�� �Ŵ�.
         vo.setAdmin_local((String) cb1.getSelectedItem());// �Ҽ� �޺��ڽ�
         AdminDAO dao = new AdminDAO();
         cnt = dao.adminSignUp(vo);

         if (cnt > 0) {
            JOptionPane.showMessageDialog(this, "ȸ������ �Ϸ�.");
            //ȸ������ �Ϸ� �� �ٽ� Ȩ����
            returnHome();
         }
      }

   }

   //���� �Ϸ� ��ư �����ÿ� �̺�Ʈ �߻� //��ȭ
   public void checkSign() {// ȸ������ ���Ϸα� ����
      if((idformTf.getText().length()<5 ||idformTf.getText().length()>15)) {
         JOptionPane.showMessageDialog(this, "���̵�� 6�ڸ��̻� 15�ڸ� ���ϸ� �����մϴ�.");
      } else if (String.valueOf(pwTf.getPassword()) == null || (String.valueOf(pwTf.getPassword()).equals(""))) {
         JOptionPane.showMessageDialog(this, "��й�ȣ�� ��Ȯ�ϰ� �Է����ּ���");
      } else if (nameformTf.getText() == null || nameformTf.getText().equals("")) {
         JOptionPane.showMessageDialog(this, "�̸��� ��Ȯ�ϰ� �Է����ּ���");
      } else if (formTf2.getText() == null || (String.valueOf(formTf21.getPassword())) == null || formTf2.getText().equals("")
            ||(String.valueOf(formTf21.getPassword())).equals("")) {
         JOptionPane.showMessageDialog(this, "�ֹε�� ��ȣ�� ��Ȯ�ϰ� �Է����ּ���");
      } else if (formTf3[0].getText() == null || formTf3[1].getText() == null || formTf3[2].getText() == null
            || formTf3[0].getText().equals("") || formTf3[1].getText().equals("")
            || formTf3[2].getText().equals("")) {
         JOptionPane.showMessageDialog(this, "��ȭ��ȣ�� ��Ȯ�ϰ� �Է����ּ���");
      } else if (!ch1.isSelected() || !ch2.isSelected()) {
         JOptionPane.showMessageDialog(this, "����� �������ּ���");
      }else if(approval==0) {
         JOptionPane.showMessageDialog(this, "�޴��� ��ȣ ������ ���ּ���");
      } else if (check==2 && ((String) cb1.getSelectedItem()).equals("�á���")) {
         JOptionPane.showMessageDialog(this, "�á��� �� �������ּ���");
      } else {
         checkPassWord();
      }
   }

   //��й�ȣ=��й�ȣȮ�� ��ġ�ϴ��� Ȯ��
   public void checkPassWord() {
      // System.out.println(String.valueOf(pwTf.getPassword())+"/"+String.valueOf(pwTf2.getPassword()));
      if (String.valueOf(pwTf.getPassword()).equals(String.valueOf(pwTf2.getPassword()))) {
         adminSign();
      } else {
         JOptionPane.showMessageDialog(this, "��й�ȣ�� ��ġ���� �ʽ��ϴ�.");
      }

   }

   //�޴��� ���� ��ư ������ �� ���� ���� Ȯ��
   public void confirmFail() {

      //�������� �ƴ��� Ȯ��
      if(formTf3[0].getText()==null||formTf3[0].getText().equals("")||formTf3[1].getText()==null||formTf3[1].getText().equals("")||formTf3[2].getText()==null||formTf3[2].getText().equals("")) {
         JOptionPane.showMessageDialog(null, "�޴��� ��ȣ�� ��Ȯ�ϰ� �Է����ּ���.");
      }

      else {//������� ���� ��� �߿� ����Ǵ� if����
         List<AdminVO> telList = new ArrayList<AdminVO>();

         String tel1,tel2,tel3;
         tel1= formTf3[0].getText();
         tel2= formTf3[1].getText();
         tel3= formTf3[2].getText();
         fulltel=tel1+tel2+tel3;

         //3���� �ؽ�Ʈ�ʵ带 ������ �س���
         //if �������ѰŰ� fulltel�� �ڵ��� ��ȣ��� �� ��ġ���� �������� ������ �༭ 0 ,1 1 ������Ű�� 0~1�ιٲ�
         //System.out.println("���ξ����ι�"+approval); //��¹�

         AdminDAO dao = new AdminDAO();
         UserDAO dao2 = new UserDAO();

         if(mode==1) {
            check = dao.telNumCheck(fulltel);
         } else {
            check = dao2.telNumCheck(fulltel);
         }

         checkPhoneNumeber(check);
      }
   }

   //�޴��� ��ȣ�� ���� ���� Ȯ��
   public void checkPhoneNumeber(int check) {
      if(check==0){//������ ������ �̹� ���Ե� ����̴�.
         JOptionPane.showMessageDialog(null, "�̹� ���Ե� ����Դϴ�.");

      }else {

         //��� ���� �����鼭 ���ǿ� ������ ���//�����Ͷ� ���� ������� ������ ���ԵǾ� ���� �ʾƾ� ���ǿ� �����Ǵ°�.
         int ran=(int)Math.round(Math.random()*(9999-1000+1))+1000;    //1000~9999������.  
         System.out.println(ran);
         sendMessage(ran,fulltel); //--------------------------------------------------------------------------���ڹ߼�
         String msg=JOptionPane.showInputDialog(null, "������ȣ�� �Է����ּ���");
         if(msg == null) 
            JOptionPane.showMessageDialog(null, "������ȣ�� ����� �Է����ּ���.");
         if(msg.equals(""+ran)){
            // String���� ��ȯ����
            approval = 1;// 1�� ������ �Ǿ �Ѿ��,.
            JOptionPane.showMessageDialog(null, "�����Ǿ����ϴ�.");
            formTf3[0].setEnabled(false);
            formTf3[1].setEnabled(false);
            formTf3[2].setEnabled(false);

         }
         else 
            JOptionPane.showMessageDialog(null, "��������");   
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

   //Ȩ���� ���ư��� �޼ҵ�
   public void returnHome() {
      MainTitle.lbl.setText("��");
      UserTabMenu.centerpane.removeAll();
      UserTabMenu.centerpane.add(firstmain);
      UserTabMenu.centerpane.updateUI();
   }

   //////////////////////////////////////�׼�
   public void actionPerformed(ActionEvent e) {
      Object event = e.getSource();
      //String event = e.getActionCommand();
      if (event==(telbtn)) {
         confirmFail();
      }
      else if(event==(btn1)) { //���
         //����߰�
         returnHome();
      }
      else if(event==btn2) {
         checkSign();
      }
   }

   ////////////////////////////////////////////////////////

   @Override
   public void mouseClicked(MouseEvent e) {
      Object lblevent=e.getSource();
      if(lblevent==chlbl1) {
         JOptionPane.showMessageDialog(this, "1. �� ����� ������Ż���� �� 31 ��, �� �� �����Ģ �� 21���� 2�� ���� ���������� ��ģ �� Ȩ�������� ���Ͽ� \n�̸� �����ϰų� ���ڿ��� ��Ÿ�� ������� �̿��ڿ��� ���������ν� ȿ���� �߻��մϴ�.\r\n"
               + "2. ȸ��� �� ����� ���� ���� ���� ������ �� ������, ������ ����� ��9���� ���� ������� �����մϴ�.\n ȸ���� ������ ����� �������� �ƴ��ϴ� ��� ������ ȸ������� ���(ȸ��Ż��)�� �� ������,\n ��� ����� ���� ��� ������ ���� ���Ƿ� ���ֵ˴ϴ�. ������ ����� ������ ���ÿ� �� ȿ���� �߻��˴ϴ�.");
      }else if(lblevent==chlbl2) {
         JOptionPane.showMessageDialog(this, "�ش� ����Ʈ�� ��������� ���ϴ� �ٿ� ���� ȸ�� ��������� ������ ȸ���� ���������� ��ȣ�ϱ� ���� ����մϴ�.\n"
               + "ȸ�� ���������� ��ȣ �� ��뿡 ���ؼ��� ���ù��� �� �� ����Ʈ�� �������� ��ȣ��å�� ����˴ϴ�.\n"
               + "�ٸ�, �� ����Ʈ �̿ܿ� ��ũ�� ����Ʈ������ �� ����Ʈ�� �������� ��ȣ��å�� ������� �ʽ��ϴ�.");
      }

   }

   @Override
   public void mousePressed(MouseEvent e) {
      // TODO Auto-generated method stub

   }

   @Override
   public void mouseReleased(MouseEvent e) {
      // TODO Auto-generated method stub

   }

   @Override
   public void mouseEntered(MouseEvent e) {
      // TODO Auto-generated method stub

   }

   @Override
   public void mouseExited(MouseEvent e) {
      // TODO Auto-generated method stub

   }


   public static void main(String[] args) {
      //            new SignUpMain();
   }
}
package user.check;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import user.home.MainTitle;
//import user.home.MainTitle;
import user.home.SetStyle;

public class TabCheckReservationinformation extends JPanel implements ActionListener{

   //��ư�� �������� �˻� ���� ������ ��� ���� ���� ��� ���� Ŭ����

   SetStyle st = new SetStyle();//��Ʈ���� Ŭ����

   TestRsvCheck ct; //�˻� ���� ���� Ŭ����
   VaccineRsvCheck cr; //��� ���� ���� Ŭ����

   JPanel checkbtnpane = new JPanel();//�˻翹������ ��ſ������� ��ư ���� �г�
   JButton checkTestbtn = new JButton("�˻� ���� ����");
   JButton checkRsvbtn = new JButton("��� ���� ����");

   public static JPanel inData = new JPanel();//�ҷ��� ���� ����� �г� ���� 
   static JLabel lbl = new JLabel("�˻� ���� ������ �����ϴ�.");
   static JLabel lbl2 = new JLabel("��� ���� ������ �����ϴ�.");

   public TabCheckReservationinformation() {


      setLayout(new BorderLayout());

      add(BorderLayout.NORTH,checkbtnpane); //�������� ��ư �־���
      checkbtnpane.add(checkTestbtn);
      checkTestbtn.setFont(st.fnt1);
      checkTestbtn.setBackground(st.clr1);
      checkTestbtn.setForeground(Color.WHITE);
      checkTestbtn.setBorderPainted(false);
      checkbtnpane.add(checkRsvbtn);
      checkRsvbtn.setFont(st.fnt1);
      checkRsvbtn.setBackground(st.clr1);
      checkRsvbtn.setForeground(Color.WHITE);
      checkRsvbtn.setBorderPainted(false);
      lbl.setFont(st.fnt2);
      lbl2.setFont(st.fnt2);

      add(BorderLayout.CENTER,inData);   //���ʹ�ư Ŭ���� �߻��ϴ� �̺�Ʈ ���� �־��ִ°���


      checkTestbtn.addActionListener(this);   //�˻� ��������â ��� �̺�Ʈ����
      checkRsvbtn.addActionListener(this);   //��� ��������â ��� �̺�Ʈ ����

//      setSize(1200,900);
//      setVisible(true);
//      setDefaultCloseOperation(DISPOSE_ON_CLOSE);
   }

   @Override
   public void actionPerformed(ActionEvent e) {
      String eventBtn = e.getActionCommand();
      if(eventBtn.equals("�˻� ���� ����")) {
         inData.removeAll();
         ct = new TestRsvCheck(MainTitle.user_id);
         if(ct.strdata!=null) { 	 
            inData.add(ct);            
         }else inData.add(lbl);
         inData.updateUI();
      }
      else if(eventBtn.equals("��� ���� ����")) {
         inData.removeAll();
         cr = new VaccineRsvCheck(MainTitle.user_id);
         if(cr.strdata!=null) {     	 
            inData.add(cr);            
         }else inData.add(lbl2);
         inData.updateUI();
      }
   }

   public static void main(String[] args) {
//      new TabCheckReservationinformation();
   }
}
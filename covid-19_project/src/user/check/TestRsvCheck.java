package user.check;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import database.RsvVO;
import database.TestDAO;
import database.TestVO;
import database.UserDAO;
import user.home.SetStyle;

public class TestRsvCheck extends JPanel implements ActionListener{//�ǿ���������
   //20210801�輺��
   //�ڷγ� �˻� ���� �����ڷγ� �˻� ���� �����ڷγ� �˻� ���� �����ڷγ� �˻� ���� ����
   //�߰��� �г� ��������
   SetStyle st = new SetStyle();
   JPanel centerPanel= new JPanel(new BorderLayout());//������� �������
   JPanel inPanel = new JPanel(null);

   //����������
   JLabel reserveiplbl= new JLabel("<������ ����>");
   JLabel reservenamelbl= new JLabel("������ �̸�");
   JLabel reservenumlbl= new JLabel("������ �ֹε�Ϲ�ȣ");
   JLabel reservetellbl= new JLabel("������ �޴�����ȣ");

   //��������
   JLabel resimlbl2= new JLabel("<���� ����>");
   JLabel redatalbl= new JLabel("�˻��Ͻ�");
   JLabel readdr1= new JLabel("�˻� �����");
   JLabel readdr2= new JLabel("����� �ּ�");
   JLabel readdrtel= new JLabel("����� ��ȣ");

   //�̸� ���� ������ �� 
   JLabel pure1= new JLabel();
   JLabel pure2= new JLabel();
   JLabel pure3= new JLabel();
   JLabel pure4= new JLabel();
   JLabel pure5= new JLabel();
   JEditorPane pure6= new JEditorPane();
   JLabel pure7= new JLabel();

   JButton cancelbtn= new JButton("���� ����ϱ�");

   Font font3 = new Font("���� ���",0, 20);
   Font fontidpw = new Font("���� ���",Font.BOLD, 20);
   Font searchft = new Font("���� ���",0, 16);//ã����Ʈ
   Font rightlblft = new Font("���",0, 12);//8~16�� ���� 
   EmptyBorder empty= new EmptyBorder(0,30,0,30);// �� �̸� ���ݵ�

   String  strdata;
   
   String id = "";
   RsvVO vo;
   public  TestRsvCheck(String id){
      this.id =id;
      setLayout(new FlowLayout());

      TestDAO dao = new TestDAO();
      TestVO vo = dao.selectTestRsv(id);
      pure1.setText(vo.getUser_name());
      pure2.setText(vo.getUser_num());
      pure3.setText(vo.getUser_tel());

      pure4.setText(vo.getRsv_date()+" "+vo.getRsv_hour());
      pure5.setText(vo.getCenter_name());
      pure6.setText(vo.getLoc1()+" "+vo.getLoc2()+" "+vo.getCenter_addr());
      pure7.setText(vo.getCenter_tel());

      strdata = vo.getRsv_date();

      centerPanel.setPreferredSize(new Dimension(800,800));
      centerPanel.add(inPanel);
      add(centerPanel);

      //������ ����
      inPanel.add(reserveiplbl);//������ ����
      reserveiplbl.setBounds(0,0,140,40);
      reserveiplbl.setFont(fontidpw);

      //�������̸�
      inPanel.add(reservenamelbl).setBounds(0,40,230,50);
      reservenamelbl.setFont(fontidpw);
      reservenamelbl.setOpaque(true);//�󺧹���
      reservenamelbl.setBackground(Color.LIGHT_GRAY);
      reservenamelbl.setHorizontalAlignment(JLabel.CENTER);

      //������ �ֹε�Ϲ�ȣ
      inPanel.add(reservenumlbl).setBounds(0,92,230,50);
      reservenumlbl.setFont(fontidpw);
      reservenumlbl.setOpaque(true);//�󺧹���
      reservenumlbl.setBackground(Color.LIGHT_GRAY);
      reservenumlbl.setHorizontalAlignment(JLabel.CENTER);

      //������ �޴�����ȣ
      inPanel.add(reservetellbl).setBounds(0,144,230,50);
      reservetellbl.setFont(fontidpw);
      reservetellbl.setOpaque(true);//�󺧹���
      reservetellbl.setBackground(Color.LIGHT_GRAY);
      reservetellbl.setHorizontalAlignment(JLabel.CENTER); 

      //��������
      inPanel.add(resimlbl2).setBounds(0,190,140,40);
      resimlbl2.setFont(fontidpw);

      //�˻��Ͻ�
      inPanel.add(redatalbl).setBounds(0,235,230,50);
      redatalbl.setFont(fontidpw);
      redatalbl.setOpaque(true);//�󺧹���
      redatalbl.setBackground(Color.LIGHT_GRAY);
      redatalbl.setHorizontalAlignment(JLabel.CENTER);

      //�˻� ����� 
      inPanel.add(readdr1).setBounds(0,287,230,50);
      readdr1.setFont(fontidpw);
      readdr1.setOpaque(true);//�󺧹���
      readdr1.setBackground(Color.LIGHT_GRAY);
      readdr1.setHorizontalAlignment(JLabel.CENTER);

      //����� �ּ�
      inPanel.add(readdr2).setBounds(0,339,230,90);
      readdr2.setFont(fontidpw);
      readdr2.setOpaque(true);//�󺧹���
      readdr2.setBackground(Color.LIGHT_GRAY);
      readdr2.setHorizontalAlignment(JLabel.CENTER);

      //����� ��ȣ
      inPanel.add(readdrtel).setBounds(0,431,230,50);
      readdrtel.setFont(fontidpw);
      readdrtel.setOpaque(true);//�󺧹���
      readdrtel.setBackground(Color.LIGHT_GRAY);
      readdrtel.setHorizontalAlignment(JLabel.CENTER);

      //������ �� �Է�ĭ (��ȸ�� ���)
      inPanel.add(pure1).setBounds(230,40,500,50);
      pure1.setFont(font3);
      pure1.setBorder(empty);//�� ����
      pure1.setOpaque(true);//�󺧹���
      pure1.setBackground(Color.white);

      //�������ֹε�Ϲ�ȣ 
      inPanel.add(pure2).setBounds(230,92,500,50);
      pure2.setFont(font3);
      pure2.setBorder(empty);
      pure2.setOpaque(true);//�󺧹���
      pure2.setBackground(Color.white);

      //������ �޴��� ��ȣ 
      inPanel.add(pure3).setBounds(230,144,500,50);
      pure3.setFont(font3);
      pure3.setBorder(empty);
      pure3.setOpaque(true);//�󺧹���
      pure3.setBackground(Color.white);

      //�˻��Ͻú��� 
      inPanel.add(pure4).setBounds(230,235,500,50);
      pure4.setFont(font3);
      pure4.setBorder(empty);//
      pure4.setOpaque(true);//�󺧹���
      pure4.setBackground(Color.white);
      //�˻������
      inPanel.add(pure5).setBounds(230,287,500,50);
      pure5.setFont(font3);
      pure5.setBorder(empty);
      pure5.setOpaque(true);//�󺧹���
      pure5.setBackground(Color.white);
      //������ּ�
      inPanel.add(pure6).setBounds(230,339,500,90);
      pure6.setBorder(new EmptyBorder(15,30,0,30));//��� �ٲٸ�ȉ�
      pure6.setFont(font3);
      pure6.setOpaque(true);//�󺧹���
      pure6.setBackground(Color.white);

      //����ҹ�ȣ
      inPanel.add(pure7).setBounds(230,431,500,50);
      pure7.setFont(font3);
      pure7.setBorder(empty);
      pure7.setOpaque(true);//�󺧹���
      pure7.setBackground(Color.white);

      // ��������ϱ� ��ư
      inPanel.add(cancelbtn);
      cancelbtn.setBounds(280,500,280,40);
      cancelbtn.setBackground(Color.LIGHT_GRAY);
      cancelbtn.setFocusPainted(false);
      cancelbtn.setFont(st.fnt1);
      cancelbtn.setBackground(st.clr1);
      cancelbtn.setForeground(Color.WHITE);
      cancelbtn.setBorderPainted(false);
      cancelbtn.addActionListener(this);
      //      setSize(1200,800);
      //      setVisible(true);
      //      setDefaultCloseOperation(DISPOSE_ON_CLOSE);
   }
   @Override
   public void actionPerformed(ActionEvent e) {
      String eventBtn = e.getActionCommand();
      if(eventBtn.equals("���� ����ϱ�")) {
         delTestData();
      }

   }
   //�˻翹�� ��� �޼ҵ�
   public void delTestData() {
      TestDAO dao = new TestDAO();
      
      int cnt = dao.deleteTestData(id);
      if(cnt>0) {
         JOptionPane.showMessageDialog(this, "�˻� ������ ��� �Ͽ����ϴ�.");
         TabCheckReservationinformation.inData.removeAll();
         TabCheckReservationinformation.inData.add(TabCheckReservationinformation.lbl);
         TabCheckReservationinformation.inData.updateUI();
      }else {
         JOptionPane.showMessageDialog(this, "�˻� ������ ��Ҹ� ���� �Ͽ����ϴ�.");
      }
   }
   public static void main(String[] args) {
      //      new TestRsvCheck();
   }
}
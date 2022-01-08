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

import database.RsvDAO;
import database.RsvVO;
import database.TestDAO;
import database.TestVO;
import database.VaccineDAO;
import user.home.SetStyle;

public class VaccineRsvCheck extends JPanel implements ActionListener {//�ǿ���������
   //20210801�輺��
   //��� ���� ������� ���� ������� ���� ������� ���� ������� ���� ������� ���� ����
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
   JLabel reservationNum= new JLabel("��� ����");
   JLabel inoculate1= new JLabel("1�� �����Ͻ�");
   JLabel inoculate2= new JLabel("2�� �����Ͻ�");
   JLabel clinic= new JLabel("���� �����");
   JLabel clinicAdr= new JLabel("����� �ּ�");
   JLabel clinicNum= new JLabel("����� ��ȣ");

   //�̸� ���� ������ �� 
   JLabel pure1= new JLabel();
   JLabel pure2= new JLabel();
   JLabel pure3= new JLabel();

   JLabel pure4= new JLabel();
   JLabel pure5= new JLabel();
   JLabel pure6= new JLabel();
   JLabel pure7= new JLabel();
   JEditorPane pure8= new JEditorPane();
   JLabel pure9= new JLabel();

   JButton cancelbtn= new JButton("���� ����ϱ�");

   Font font3 = new Font("���� ���",0, 20);
   Font fontidpw = new Font("���� ���",Font.BOLD, 20);
   Font searchft = new Font("���� ���",0, 16);//ã����Ʈ
   Font rightlblft = new Font("���",0, 12);//8~16�� ���� 
   EmptyBorder empty= new EmptyBorder(0,30,0,30);// �� �̸� ���ݵ�
   String  strdata;

   String id = "";
   RsvVO vo;
   public VaccineRsvCheck(String id){
      this.id = id;
      setLayout(new FlowLayout());
      RsvDAO dao = new RsvDAO();
      vo = dao.selectRsv(id);
      pure1.setText(vo.getUser_name());
      pure2.setText(vo.getUser_num());
      pure3.setText(vo.getUser_tel());

      pure4.setText(vo.getVc_type());
      pure5.setText(vo.getRsv_date()+" "+vo.getRsv_hour());
      pure6.setText(vo.getRsv_date2()+" "+vo.getRsv_hour());
      pure7.setText(vo.getCenter_name());
      pure8.setText(vo.getLoc1()+" "+vo.getLoc2()+" "+vo.getCenter_addr());
      pure9.setText(vo.getCenter_tel());

      strdata = vo.getRsv_date();

      centerPanel.setPreferredSize(new Dimension(800,800));
      centerPanel.add(inPanel);
      add(centerPanel);

      ///////�󺧵�

      //2.������ ����
      inPanel.add(reserveiplbl);//������ ����
      reserveiplbl.setBounds(0,0,140,40);
      reserveiplbl.setFont(fontidpw);

      //2-1.�������̸�
      inPanel.add(reservenamelbl).setBounds(0,40,230,50);
      reservenamelbl.setFont(fontidpw);
      reservenamelbl.setOpaque(true);//�󺧹���
      reservenamelbl.setBackground(Color.LIGHT_GRAY);
      reservenamelbl.setHorizontalAlignment(JLabel.CENTER);

      //2-2.������ �ֹε�Ϲ�ȣ
      inPanel.add(reservenumlbl).setBounds(0,92,230,50);
      reservenumlbl.setFont(fontidpw);
      reservenumlbl.setOpaque(true);//�󺧹���
      reservenumlbl.setBackground(Color.LIGHT_GRAY);
      reservenumlbl.setHorizontalAlignment(JLabel.CENTER);

      //2-3.������ �޴�����ȣ
      inPanel.add(reservetellbl).setBounds(0,144,230,50);
      reservetellbl.setFont(fontidpw);
      reservetellbl.setOpaque(true);//�󺧹���
      reservetellbl.setBackground(Color.LIGHT_GRAY);
      reservetellbl.setHorizontalAlignment(JLabel.CENTER); 

      //3.��������
      inPanel.add(resimlbl2).setBounds(0,190,140,40);
      resimlbl2.setFont(fontidpw);

      //���� ��ȣ
      inPanel.add(reservationNum).setBounds(0,235,230,50);
      reservationNum.setFont(fontidpw);
      reservationNum.setOpaque(true);//�󺧹���
      reservationNum.setBackground(Color.LIGHT_GRAY);
      reservationNum.setHorizontalAlignment(JLabel.CENTER); 

      //1�� ���� �Ͻ�
      inPanel.add(inoculate1).setBounds(0,287,230,50);
      inoculate1.setFont(fontidpw);
      inoculate1.setOpaque(true);//�󺧹���
      inoculate1.setBackground(Color.LIGHT_GRAY);
      inoculate1.setHorizontalAlignment(JLabel.CENTER); 

      //2�� ���� �Ͻ�
      inPanel.add(inoculate2).setBounds(0,339,230,50);
      inoculate2.setFont(fontidpw);
      inoculate2.setOpaque(true);//�󺧹���
      inoculate2.setBackground(Color.LIGHT_GRAY);
      inoculate2.setHorizontalAlignment(JLabel.CENTER);

      //���� ����� clinic
      inPanel.add(clinic).setBounds(0,390,230,50);
      clinic.setFont(fontidpw);
      clinic.setOpaque(true);//�󺧹���
      clinic.setBackground(Color.LIGHT_GRAY);
      clinic.setHorizontalAlignment(JLabel.CENTER);

      //����� �ּ�
      inPanel.add(clinicAdr).setBounds(0,442,230,80);
      clinicAdr.setFont(fontidpw);
      clinicAdr.setOpaque(true);//�󺧹���
      clinicAdr.setBackground(Color.LIGHT_GRAY);
      clinicAdr.setHorizontalAlignment(JLabel.CENTER);

      //����� ��ȣ
      inPanel.add(clinicNum).setBounds(0,523,230,50);
      clinicNum.setFont(fontidpw);
      clinicNum.setOpaque(true);//�󺧹���
      clinicNum.setBackground(Color.LIGHT_GRAY);
      clinicNum.setHorizontalAlignment(JLabel.CENTER);

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

      //�����ȣ
      inPanel.add(pure4).setBounds(230,235,500,50);
      pure4.setFont(font3);
      pure4.setBorder(empty);//
      pure4.setOpaque(true);//�󺧹���
      pure4.setBackground(Color.white);

      //1�� �����Ͻ�
      inPanel.add(pure5).setBounds(230,287,500,50);
      pure5.setFont(font3);
      pure5.setBorder(empty);
      pure5.setOpaque(true);//�󺧹���
      pure5.setBackground(Color.white);

      //2�� �����Ͻ�
      inPanel.add(pure6).setBounds(230,339,500,50);
      pure6.setBorder(new EmptyBorder(15,30,0,30));//��� �ٲٸ�ȉ�
      pure6.setFont(font3);
      pure6.setOpaque(true);//�󺧹���
      pure6.setBackground(Color.white);

      //���� �����
      inPanel.add(pure7).setBounds(230,390,500,50);
      pure7.setFont(font3);
      pure7.setBorder(empty);
      pure7.setOpaque(true);//�󺧹���
      pure7.setBackground(Color.white);

      //����� �ּ�
      inPanel.add(pure8).setBounds(230,442,500,80);
      pure8.setBorder(new EmptyBorder(15,30,0,30));//��� �ٲٸ�ȉ�
      pure8.setFont(font3);
      pure8.setOpaque(true);//�󺧹���
      pure8.setBackground(Color.white);

      //����� ��ȣ
      inPanel.add(pure9).setBounds(230,523,500,50);
      pure9.setFont(font3);
      pure9.setBorder(empty);
      pure9.setOpaque(true);//�󺧹���
      pure9.setBackground(Color.white);

      //��������ϱ� ��ư
      inPanel.add(cancelbtn);
      cancelbtn.setBounds(280,600,280,40);
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
         rsvDelete();
      }
   }
   //20210802 �輺�� ���� �̺�Ʈ �߻��ϱ����� �޼ҵ� 
   public void rsvDelete() {// ȭ�� ��ȯ ���ּž��մϴ�
      RsvDAO dao = new RsvDAO();

      int cnt = dao.deleteRsvData(id);
      if(cnt>0) {//���� ������ ����
         vaccineAdd();//��ż��� +1
         //         JOptionPane.showMessageDialog(this,"������ ��Ұ� �Ǿ����ϴ�");
      }else {//������� ����
         JOptionPane.showMessageDialog(this,"���� ��Ұ� �����Ͽ����ϴ�");
      }
   }
   public void vaccineAdd() {//��ż���+1�޼ҵ�
      VaccineDAO dao2 = new VaccineDAO();
      String type = "";
      if(vo.getVc_type().equals("ȭ����")) {
         type = "PFIZER";
      }else if(vo.getVc_type().equals("�����")) {
         type = "MODERNA";
      }else if(vo.getVc_type().equals("�Ἶ")) {
         type = "Jansen";
      }else if(vo.getVc_type().equals("�ƽ�Ʈ������ī")) {
         type = "AZ";
      }
      dao2.plusVaccData(type, vo.getCenter_code());
      JOptionPane.showMessageDialog(this,"������ ��Ұ� �Ǿ����ϴ�");
      TabCheckReservationinformation.inData.removeAll();
      TabCheckReservationinformation.inData.add(TabCheckReservationinformation.lbl2);
      TabCheckReservationinformation.inData.updateUI();
      //ȭ����ȯ�ڸ�
   }
   public static void main(String[] args) {
      //      new CheckRsv();
   }
}
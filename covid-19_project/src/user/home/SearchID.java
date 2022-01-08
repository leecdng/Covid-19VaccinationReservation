package user.home;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import database.AdminDAO;
import database.UserDAO;
public class SearchID extends JPanel implements ActionListener{//�ǿ���������
   //��ȭ
   //��� ����

   JPanel centerPanel= new JPanel(new BorderLayout());//������� �������
   JPanel inPanel = new JPanel(null);
   JLabel namelbl= new JLabel("�̸�");
   JLabel numlbl= new JLabel("�ֹε�Ϲ�ȣ");
   JLabel tellbl= new JLabel("�޴�����ȣ");
   JLabel underballbl= new JLabel("��");
   JTextField namefd= new JTextField(25);//�̸��ؽ�Ʈ�ʵ�
   JTextField numfd= new JTextField(15);//�ֹξտ��ؽ�Ʈ�ʵ�

   JPasswordField numfd2= new JPasswordField(15);//-�ڿ��ؽ�Ʈ�ʵ�
   JTextField telfd= new JTextField(25);

   JButton selectbtn = new JButton("Ȯ��");

   //��Ʈ
   //       Font font3 = new Font("���� ���",0, 20);
   //       Font fontidpw = new Font("���� ���",Font.BOLD, 20);
   //       Font searchft = new Font("���� ���",0, 16);//ã����Ʈ
   Font rightlblft = new Font("���",0, 12);//8~16�� ���� 
   SetStyle st = new SetStyle();

   Firstpage firstmain;

   public  SearchID() {
      setLayout(new FlowLayout());

      centerPanel.setPreferredSize(new Dimension(600,540));
      centerPanel.add(inPanel);
      add(centerPanel);

      inPanel.add(namelbl); //�̸� ��
      namelbl.setBounds(0,120,200,200);
      namelbl.setFont(st.fnt20b);
      inPanel.add(namefd); //�̸� �ؽ�Ʈ�ʵ�
      namefd.setBounds(145,200,280,40);

      inPanel.add(numlbl); //�ֹε�Ϲ�ȣ��
      numlbl.setBounds(0,178,200,200);
      numlbl.setFont(st.fnt20b);
      inPanel.add(numfd); //�ֹε�Ϲ�ȣ ù��°�ؽ�Ʈ�ʵ�
      numfd.setBounds(145,260,135,40);
      inPanel.add(underballbl);//�ֹε�Ϲ�ȣ - ��

      underballbl.setBounds(285,260,132,40);//-�� ũ��
      inPanel.add(numfd2);
      numfd2.setBounds(297,260,132,40);//-�ֹε�Ϲ�ȣ �ι�°ĭ�ؽ�Ʈ�ʵ�

      inPanel.add(tellbl);
      tellbl.setBounds(0,235,200,200);//�޴�����ȣ��
      tellbl.setFont(st.fnt20b);
      inPanel.add(telfd);
      telfd.setBounds(145,316,280,40);//�޴��� �ؽ�Ʈ�ʵ�

      //          setSize(1000,660);
      //          setVisible(true);
      //          setDefaultCloseOperation(DISPOSE_ON_CLOSE);

      //Ȯ�ι�ư /////////��
      inPanel.add(selectbtn);
      selectbtn.setBounds(145,370,280,40);
      selectbtn.setForeground(Color.white);
      selectbtn.setBackground(st.clr1); 
      selectbtn.setFocusPainted(false);
      selectbtn.setFont(st.fnt1);
      selectbtn.addActionListener(this);

   }         @Override
   public void actionPerformed(ActionEvent e) {
      Object event =  e.getSource(); //�̺�Ʈ�߻���
      if(event==selectbtn) {//�ֹܼ��� ������Ѷ�
         String name, num,num1,num2,tel;
         name= namefd.getText();//�����ʵ� �ȿ� ����ִ� ������ �����°�
         num1= numfd.getText();
         num2= (String.valueOf(numfd2.getPassword()));
         num=num1.concat(num2);//��������ִ� �޼���

         tel = telfd.getText();
         //System.out.println(name+num+tel);

         if(MainTitle.mode == 1) {//ȸ�� ���ã��
            UserDAO dao=new UserDAO();
            String searchid= dao.searchId(name, num, tel);
            if(searchid==null|| searchid.equals("")) {
               JOptionPane.showMessageDialog(this, "��ġ�ϴ� ������ �����ϴ�");
               namefd.setText(""); //��ġ�ϴ� ������ ������ �ڵ����� ��������.
               numfd.setText("");
               numfd2.setText("");
               telfd.setText("");
            }else {
               JOptionPane.showMessageDialog(this,"< "+name+" > ���� ���̵�� < "+searchid+" > �Դϴ�.");

               firstmain = new Firstpage(MainTitle.mode);
               UserTabMenu.centerpane.removeAll();
               UserTabMenu.centerpane.add(firstmain);
               UserTabMenu.centerpane.updateUI();
            }
         }
         else {
            AdminDAO dao2=new AdminDAO();
            String searchid= dao2.searchId(name, num, tel);
            if(searchid==null|| searchid.equals("")) {
               JOptionPane.showMessageDialog(this, "��ġ�ϴ� ������ �����ϴ�");
               namefd.setText(""); //��ġ�ϴ� ������ ������ �ڵ����� ��������.
               numfd.setText("");
               numfd2.setText("");
               telfd.setText("");
            }else {
               JOptionPane.showMessageDialog(this,"< "+name+" > ���� ���̵�� < "+searchid+" > �Դϴ�.");

               firstmain = new Firstpage(MainTitle.mode);
               UserTabMenu.centerpane.removeAll();
               UserTabMenu.centerpane.add(firstmain);
               UserTabMenu.centerpane.updateUI();
            }
         }
      }
   }

   public static void main(String[] args) {
      //      new SearchID();

   }

}
package user.home;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import admin.home.SignUpMain;
import database.AdminDAO;
import database.AdminVO;
import database.UserDAO;
import database.UserVO;



public class Login extends JPanel implements ActionListener, MouseListener{//��ȭ
   //�α��� ù ȭ�� (���̵� ���)
   
    JPanel centerPanel= new JPanel(new BorderLayout());//������� �������
    JPanel inPanel = new JPanel(null);
        
          JLabel idlbl= new JLabel("���̵�");
          JLabel pwlbl= new JLabel("��й�ȣ");
          JTextField idfd= new JTextField(25);
          JPasswordField pwfd= new JPasswordField(25);
          JButton loginbtn = new JButton("�α���");
          
          JLabel searchidlbl= new JLabel("���̵� ã��");
          JLabel searchpwlbl= new JLabel("��й�ȣ ã��");
          JLabel memberlbl= new JLabel("ȸ������");
          
     SetStyle style = new SetStyle();   
     Firstpage firstmain = new Firstpage(MainTitle.mode);
     SearchID sID;
     SearchPW sPW;
     SignUpMain sign;
          
       public Login() {
          
          setLayout(new FlowLayout());
       
          centerPanel.setPreferredSize(new Dimension(600,540));
          centerPanel.add(inPanel);
          add(centerPanel);
         
         
          inPanel.add(idlbl); //���̵� ��
          idlbl.setBounds(0,120,200,200);
          idlbl.setFont(style.fnt20b);
          inPanel.add(idfd); //���̵� �ؽ�Ʈ�ʵ�
          idfd.setBounds(165,200,280,40);
        
          inPanel.add(pwlbl); //��� ��
          pwlbl.setBounds(0,180,200,200);
          pwlbl.setFont(style.fnt20b);
          inPanel.add(pwfd); //��й�ȣ �ؽ�Ʈ�ʵ�
          pwfd.setBounds(165,260,280,40);
        
          //�α��� ��ư
          inPanel.add(loginbtn);
          loginbtn.setBounds(165,320,280,40);
          loginbtn.setBackground(style.clr1);
          loginbtn.setForeground(Color.white);
          loginbtn.setFocusPainted(false);
          loginbtn.setFont(style.fnt20b);
        
        
          inPanel.add(searchidlbl);//���̵�ã��
          inPanel.add(searchpwlbl);//��й�ȣã��
          inPanel.add(memberlbl);//ȸ������
        
          searchidlbl.setBounds(140,370,100,40);//���̵�ã��
          searchidlbl.setFont(style.fnt16);
        
          searchpwlbl.setBounds(260,370,120,40);//��й�ȣã��
          searchpwlbl.setFont(style.fnt16);
        
          memberlbl.setBounds(390,370,100,40);//ȸ������
          memberlbl.setFont(style.fnt16); 
        
        
//          setSize(1000,660);
//          setVisible(true);
//          setDefaultCloseOperation(DISPOSE_ON_CLOSE);
          
          loginbtn.addActionListener(this);
          searchidlbl.addMouseListener(this);
          searchpwlbl.addMouseListener(this);
          memberlbl.addMouseListener(this);
          pwfd.addActionListener(this);
   }
       
       @Override
       public void actionPerformed(ActionEvent e) {
          Object event = e.getSource();
        
          //�α��� ��ư Ŭ�� ��
          if(event==loginbtn || event==pwfd) {
             String insertid = idfd.getText();
             String insertpw = String.valueOf(pwfd.getPassword());
             
             //��ĭ -> �α��� ����
             if(insertid.equals("") || insertpw.equals("")) {
                JOptionPane.showMessageDialog(null,"���̵�� ��й�ȣ�� �Է��ϼ���.","�α��� ����",JOptionPane.ERROR_MESSAGE);
             }
             
             //�α��� ����
             else {
                
                if(MainTitle.mode == 1) { //ȸ����� �α���
                   UserDAO dao = new UserDAO();
                    UserVO vo = dao.setMyInfo(insertid);
                    if(insertpw.equals(vo.getUser_pw())) {
                       JOptionPane.showMessageDialog(null,vo.getUser_name()+"�� �α��� ����");
                       MainTitle.user_id= insertid;
                       //System.out.println("�α����� ���̵� : "+ MainTitle.user_id);
                       // ���� �߰� ---- ȸ�� �ֹι�ȣ �޾ƿͼ� ���� 4�ڸ� ���ϱ�
                       if(Integer.parseInt(vo.getUser_num().substring(0,2)) < 22) { // 00~21�� �ֹι�ȣ ������ ��
                    	   MainTitle.user_year = Integer.parseInt("20"+vo.getUser_num().substring(0,2)); // 20XX
                       } else { // 22~99�� �ֹι�ȣ ������ ��
                    	   MainTitle.user_year = Integer.parseInt("19"+vo.getUser_num().substring(0,2)); //19XX
                       }
                       // ------------------------------------------
                       MainTitle.lbl.setText("��");
                       UserTabMenu.centerpane.removeAll();
                       UserTabMenu.centerpane.add(firstmain);
                       UserTabMenu.centerpane.updateUI();
                       
                       MainTitle.lbl2.setText("���� ����");
                       MainTitle.lbl3.setText("�α׾ƿ�");
                    }
                    else {
                       JOptionPane.showMessageDialog(null,"���Ե��� ���� �����Դϴ�.");
                    }
                }
                else { //�����ڸ�� �α���
                   AdminDAO dao2 = new AdminDAO();
                   AdminVO vo2 = dao2.setMyInfo(insertid);
                   if(insertpw.equals(vo2.getAdmin_pw()) && vo2.getAdmin_grade()==1) {
                       JOptionPane.showMessageDialog(null,"������ "+vo2.getAdmin_name()+" �α��� ����");
                       MainTitle.admin_id= insertid;
                       
                       MainTitle.lbl.setText("��");
                       UserTabMenu.centerpane.removeAll();
                       UserTabMenu.centerpane.add(firstmain);
                       UserTabMenu.centerpane.updateUI();
                       
                       MainTitle.lbl2.setText("���� ����");
                       MainTitle.lbl3.setText("�α׾ƿ�");
                    }
                   
                    else if(insertpw.equals(vo2.getAdmin_pw()) && vo2.getAdmin_grade()==0){
                       JOptionPane.showMessageDialog(null,"������ ������ �޾ƾ� �α����� �����մϴ�.");
                    }
                   
                    else {
                       JOptionPane.showMessageDialog(null,"���Ե��� ���� �����Դϴ�.");
                    }
                }
             }
          }
       }
       
   
   @Override
   public void mouseClicked(MouseEvent e) {
      Object event = e.getSource();
      if(event==searchidlbl && searchidlbl.getText().equals("���̵� ã��")) {
         MainTitle.lbl.setText("���̵� ã��");
         sID = new SearchID();
         
         UserTabMenu.centerpane.removeAll();
         UserTabMenu.centerpane.add(sID);
         UserTabMenu.centerpane.updateUI();
      }
      else if(event==searchpwlbl && searchpwlbl.getText().equals("��й�ȣ ã��")) {
         MainTitle.lbl.setText("��й�ȣ ã��");
         sPW = new SearchPW();
         
         UserTabMenu.centerpane.removeAll();
         UserTabMenu.centerpane.add(sPW);
         UserTabMenu.centerpane.updateUI();
      }
      else if (event==memberlbl && memberlbl.getText().equals("ȸ������")) {
         MainTitle.lbl.setText("ȸ������");
         sign = new SignUpMain();
         if (MainTitle.mode == 1) {
            sign.cb1.setVisible(false);
            sign.lbl[6].setVisible(false);
         }
         UserTabMenu.centerpane.removeAll();
         UserTabMenu.centerpane.add(sign);
         UserTabMenu.centerpane.updateUI();
      }
   }

   @Override
   public void mousePressed(MouseEvent e) {
   }

   @Override
   public void mouseReleased(MouseEvent e) {   
   }
   
   @Override
   public void mouseEntered(MouseEvent e) {
   }

   @Override
   public void mouseExited(MouseEvent e) {
   }


   public static void main(String[] args) {
//      new Login();
   }


}
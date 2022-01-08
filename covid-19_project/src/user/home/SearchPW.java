package user.home;
//�α���1-1
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;

import database.AdminDAO;
import database.AdminVO;
import database.UserDAO;
import database.UserVO;

public class SearchPW extends JPanel implements ActionListener {//�ǿ���������

      JPanel centerPanel = new JPanel(new BorderLayout());
      JPanel inPanel= new JPanel(null);

           JPanel panel = new JPanel();
            JLabel idlbl= new JLabel("���̵�");
            JLabel namelbl= new JLabel("�̸�");
         JLabel numlbl= new JLabel("�ֹε�Ϲ�ȣ");
         JLabel tellbl= new JLabel("�޴�����ȣ");
         JLabel underballbl= new JLabel("��");
         
         JTextField idfd= new JTextField(25);//���̵��ؽ�Ʈ�ʵ�
         JTextField namefd= new JTextField(25);//�̸��ؽ�Ʈ�ʵ�
         JTextField numfd= new JTextField(15);//�ֹξտ��ؽ�Ʈ�ʵ�
         
         JPasswordField numfd2= new JPasswordField(15);//-�ڿ��ؽ�Ʈ�ʵ�
         JTextField telfd= new JTextField(25);//��ȭ��ȣ�ʵ�
         
         JButton selectbtn = new JButton("Ȯ��");
         SetStyle st = new SetStyle();   
//          Font font3 = new Font("���� ���",0, 20);
//        Font fontidpw = new Font("���� ���",Font.BOLD, 20);
//        Font searchft = new Font("���� ���",0, 16);//ã����Ʈ
//            
        static String id;
      
      public  SearchPW (){
          
         setLayout(new FlowLayout());
         
         centerPanel.setPreferredSize(new Dimension(600,540));
         centerPanel.add(inPanel);
         add(centerPanel);
         
       inPanel.add(idlbl);//���̵��
       idlbl.setBounds(0,60,200,200);
       idlbl.setFont(st.fnt20b);
       inPanel.add(idfd); //�̸� �ؽ�Ʈ�ʵ�
       idfd.setBounds(130,140,280,40);
       
      
       inPanel.add(namelbl); //�̸� ��
       namelbl.setBounds(0,120,200,200);
       namelbl.setFont(st.fnt20b);
       inPanel.add(namefd); //�̸� �ؽ�Ʈ�ʵ�
       namefd.setBounds(130,200,280,40);
      
       inPanel.add(numlbl); //�ֹε�Ϲ�ȣ��
       numlbl.setBounds(0,178,200,200);
       numlbl.setFont(st.fnt20b);
       inPanel.add(numfd); //�ֹε�Ϲ�ȣ �ؽ�Ʈ�ʵ�
       numfd.setBounds(130,260,135,40);
     
       inPanel.add(underballbl);//�ֹε�Ϲ�ȣ - ��
       underballbl.setBounds(267,260,132,40);//-�� ũ��
       inPanel.add(numfd2);
       numfd2.setBounds(280,260,131,40);//-�ڿ� �ؽ�Ʈ�ʵ�
       
       inPanel.add(tellbl);
       tellbl.setBounds(0,235,200,200);//�޴�����ȣ��
       tellbl.setFont(st.fnt20b);
       inPanel.add(telfd);
       telfd.setBounds(130,316,280,40);
       
       //Ȯ�ι�ư
       inPanel.add(selectbtn);
       selectbtn.setBounds(130,370,280,40);
       selectbtn.setBackground(st.clr1);
       selectbtn.setForeground(Color.WHITE); // �۾���
       selectbtn.setFocusPainted(false);
       selectbtn.setFont(st.fnt1);
       
       selectbtn.addActionListener(this);
       selectbtn.setBorderPainted(false);
       
         
      }

         //��й�ȣ ã��.
      public void pwSearch() {
         if(MainTitle.mode==1) {
            UserVO uservo= new UserVO();
             uservo.setUser_id(idfd.getText());
             uservo.setUser_name(namefd.getText());
             uservo.setUser_num(numfd.getText().concat(String.valueOf(numfd2.getPassword())));
             uservo.setUser_tel(telfd.getText());
             
             UserDAO userdao = new UserDAO();
             id = userdao.searchPw(uservo);  
         }
         else if(MainTitle.mode==2) {
            AdminVO vo= new AdminVO();
             vo.setAdmin_id(idfd.getText());
             vo.setAdmin_name(namefd.getText());
             vo.setAdmin_num(numfd.getText().concat(String.valueOf(numfd2.getPassword())));
             vo.setAdmin_tel(telfd.getText());
             
             AdminDAO dao = new AdminDAO();
             id = dao.searchAdminPw(vo);  
         }
     
      }
      
      public void checkSign() {
         if(idfd.getText()==null || idfd.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "���̵� ��Ȯ�ϰ� �Է����ּ���");
         }else if(namefd.getText()==null || namefd.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "�̸��� ��Ȯ�ϰ� �Է����ּ���");
         }else if(numfd.getText()==null || String.valueOf(numfd2.getPassword())==null || 
               numfd.getText().equals("")|| String.valueOf(numfd2.getPassword()).equals("")){
            JOptionPane.showMessageDialog(this, "�ֹε�� ��ȣ�� ��Ȯ�ϰ� �Է����ּ���");
         }else if(telfd.getText()==null || telfd.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "��ȭ��ȣ�� ��Ȯ�ϰ� �Է����ּ���");
         }else { //��� �߰�
            //�޼ҵ� �������� ������ �ȵǰ� �־��� �ФФ�
            pwSearch();
            //System.out.println(id);
            if(id != null) {
              JOptionPane.showMessageDialog(this, "��й�ȣ�� �缳���մϴ�.");
              Passwordsetting pwset = new Passwordsetting();
              
              UserTabMenu.centerpane.removeAll();
              UserTabMenu.centerpane.add(pwset);
              UserTabMenu.centerpane.updateUI();
            }
            else {
               JOptionPane.showMessageDialog(this, "��ġ�ϴ� ������ �����ϴ�.");
            }
            
            
         }
      }
   
      @Override
      public void actionPerformed(ActionEvent e) {
         Object event = e.getSource();
         if(event==(selectbtn)){
            checkSign();
         }
         
      }   
   }
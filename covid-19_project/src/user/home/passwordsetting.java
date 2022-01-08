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
import database.UserDAO;


public class Passwordsetting extends JPanel implements ActionListener{//�ǿ���������
	//��ȭ

    

       //null ���̾ƿ� ���� �г�
    JPanel centerPanel = new JPanel(new BorderLayout());
    JPanel inPanel= new JPanel(null);
       JLabel newpwlbl= new JLabel("�� ��й�ȣ");
    JLabel newpwSelectlbl= new JLabel("�� ��й�ȣ Ȯ��");
    //JLabel drawlbl= new JLabel("8~16�� ���� �� �ҹ���,����,Ư�����ڸ� ����ϼ���.");
    //JLabel drawlbl2= new JLabel("��й�ȣ�� ��ġ�մϴ�.");
    JPasswordField newPwfd= new JPasswordField(25);
    JPasswordField newPwSelfd= new JPasswordField(25);
    
    JButton selectbtn = new JButton("Ȯ��");
    //���Ƿ� �����ͺ��̽� ���� �ߵǾ����� ���°� !
    JPasswordField id= new JPasswordField(25);
 
    Firstpage firstmain;
    

       Font font3 = new Font("���� ���",0, 20);
       Font fontidpw = new Font("���� ���",Font.BOLD, 20);
       Font searchft = new Font("���� ���",0, 16);//ã����Ʈ
       Font rightlblft = new Font("���",0, 12);//8~16�� ���� 

       
       String pw= null;
       int cnt;

       public  Passwordsetting() {
       
       setLayout(new FlowLayout());
 
       centerPanel.setPreferredSize(new Dimension(600,540));
       centerPanel.add(inPanel);
       add(centerPanel);


      inPanel.add(newpwlbl); //����й�ȣ ��
       newpwlbl.setBounds(10,118,200,200);
       newpwlbl.setFont(fontidpw);
       inPanel.add(newPwfd); //����й�ȣ �ؽ�Ʈ�ʵ�
       newPwfd.setBounds(180,200,280,40);
       //inPanel.add(drawlbl);//8~6���� ...��
     //  drawlbl.setFont(rightlblft);
      // drawlbl.setBounds(470,200,300,40);
       
       inPanel.add(newpwSelectlbl); //����й�ȣ Ȯ��
       newpwSelectlbl.setBounds(0,175,200,200);
       newpwSelectlbl.setFont(fontidpw);
       inPanel.add(newPwSelfd); //����й�ȣ Ȯ���ʵ�
       newPwSelfd.setBounds(180,255,280,40);
       //inPanel.add(drawlbl2);
       //drawlbl2.setFont(rightlblft);
       //drawlbl2.setBounds(290,255,300,40);
       
       inPanel.add(selectbtn);
       selectbtn.setBounds(180,315,280,40);
       selectbtn.setBackground(Color.LIGHT_GRAY);
       selectbtn.setFocusPainted(false);
       selectbtn.setFont(font3);
       
//       setSize(1000,660);
//       setVisible(true);
//       setDefaultCloseOperation(DISPOSE_ON_CLOSE);
       
       selectbtn.addActionListener(this);
       
       
    }
    
    public void modify() {
    String user_id = SearchPW.id;
    if(MainTitle.mode == 1) {
    	UserDAO dao = new UserDAO();
    	cnt= dao.passWordModify(String.valueOf(newPwfd.getPassword()),user_id);
    }
    else if(MainTitle.mode==2){
    	AdminDAO dao2 = new AdminDAO();
    	cnt= dao2.passWordModify(String.valueOf(newPwfd.getPassword()),user_id);
    }

    if(cnt>0) {
       JOptionPane.showMessageDialog(this, "��й�ȣ�� ����Ǿ����ϴ�.");
       firstmain = new Firstpage(MainTitle.mode);
       
       UserTabMenu.centerpane.removeAll();
       UserTabMenu.centerpane.add(firstmain);
       UserTabMenu.centerpane.updateUI();
    }      
 }
    
    
    public void checkSign() {
     if(newPwfd.getPassword()==null || newPwfd.getPassword().equals("")){
        JOptionPane.showMessageDialog(this, "��й�ȣ�� ��Ȯ�ϰ� �Է����ּ���");
     }else {
        checkPassWord();
     }
  }
    public void checkPassWord() {
     if(String.valueOf(newPwfd.getPassword()).equals(String.valueOf(newPwSelfd.getPassword()))) {
        modify();
     }else {
        JOptionPane.showMessageDialog(this,"��й�ȣ�� ��ġ���� �ʽ��ϴ�." );
        }
     
  }
    public void actionPerformed(ActionEvent e) {
    String event = e.getActionCommand();
    if(event.equals("Ȯ��")) {
       checkSign();
    }
 }   



 public static void main(String[] args) {
//    new PasswordSetting();

 }

}

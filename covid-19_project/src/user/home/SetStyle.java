package user.home;

import java.awt.Color;
import java.awt.Font;

import javax.swing.UIManager;

public class SetStyle { // ����
   // ���� ��Ʈ------------
   public Font fnt1 = new Font("���� ���", Font.BOLD, 16); // ��ư
   public Font fnt2 = new Font("���� ���", Font.BOLD, 14); // ��
   public Font fnt3 = new Font("���� ���", Font.PLAIN, 14); // ����
   
   public Font fnt20b = new Font("���� ���", Font.BOLD, 20);
   public Font fnt20 = new Font("���� ���",0, 20);
   public Font fnt16 = new Font("���� ���",0, 16);
   public Font fnt30b = new Font("���� ���",1, 30);
   
   // ���� �÷� ------------
   public Color clr1 = new Color(38,80,150); // �Ķ��� ----- ���� ���� �÷� 
   
   // ----- �����ϸ� ���� ������ �� ������ ------------
   // SetStyle st = new SetStyle();
   // ex) st.fnt1
   
   
   // �ٲ㼭 �ᵵ �Ǵ� ��Ʈ
   public Font fnt4 = new Font("���� ���", Font.BOLD, 26);
   public Font fnt5 = new Font("���� ���", Font.BOLD, 30);
   public Font fnt6 = new Font("���� ���", Font.PLAIN,12);
   // �ٲ㼭 �ᵵ �Ǵ� �÷�
   public Color clr2 = new Color(220,220,220); // ȸ��
   public Color clr3 = new Color(38,100,150); //
   public Color clr4 = new Color(225,225,150);
   public Color clr5 = new Color(220,100,100);
   public Color clr6 = new Color(100,100,100);
   
   public SetStyle() {
      /*
      // ��ư ������ ����
      btn.setFont(st.fnt1); // ��Ʈ ����
      btn.setBackground(st.clr1); // ����
      btn.setForeground(Color.WHITE); // �۾���
      btn.setBorderPainted(false); // �׵θ� ���ֱ�
      
      */
      
      // JOptionPane ��Ʈ ����
      UIManager.put("OptionPane.messageFont", fnt3); // �޼��� ����
      UIManager.put("OptionPane.buttonFont", fnt2); // ��ư
   }
}
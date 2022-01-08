package user.searchcenter;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Vector;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JTextField;

import database.LocDAO;
import user.home.SetStyle;



public class SearchCenterCombo extends JPanel implements ItemListener { //��� // ���� ����
   //���� �õ�/�ñ��� �޺��ڽ�
   
   public JComboBox<String> cb1;
   public JComboBox<String> cb2;
   
   public JTextField tf = new JTextField(20);
   public JButton btn = new JButton("�˻�");
   public LocDAO locdao = new LocDAO();
   public LocDAO locdao2 = new LocDAO();
   
   SetStyle st = new SetStyle();
   
   public SearchCenterCombo() {
      //�õ� �޺��ڽ� ����
      Vector<String> list1 = locdao.combo1();
      cb1= new JComboBox<String>(list1);
      cb1.insertItemAt("�á���", 0);
      cb1.setSelectedIndex(0);
       
//      String sido = (String)cb1.getSelectedItem();
//      Vector<String> list2 = locdao.combo2(sido);
//      cb2 = new JComboBox(list2);
      
      cb2 = new JComboBox<String>();
      cb2.addItem("�á�������");
      
      add(cb1);
      add(cb2);
      add(tf);
      add(btn);
      
      searchBasic();
      
      cb1.addItemListener(this);
   }
   
   
   public SearchCenterCombo(String loc1) { // ����
      //�õ� �޺��ڽ� ����
      Vector<String> list1 = locdao.combo1();
      cb1= new JComboBox<String>(list1);
      cb1.insertItemAt("�á���", 0);
      cb1.setSelectedItem(loc1);
      
      // �ñ��� �޺��ڽ� ����
      cb2 = new JComboBox<String>();
      Vector<String> list2 = locdao2.combo2(loc1);               
     DefaultComboBoxModel<String> cb2model = new DefaultComboBoxModel<String>(list2);
     cb2.setModel(cb2model);
     cb2.insertItemAt("�á�������", 0);
     cb2.setSelectedIndex(0);
      
//     add(cb1); // ����� �ϵ� ȭ�鿡 ���X
      add(cb2);
      add(tf);
      add(btn);
      
      searchBasic();
   }
   
   
   public void searchBasic() {
    //---- ������Ʈ ������ ����+ ��ư �׵θ� ���� //���� �߰� ----
      cb1.setPreferredSize(new Dimension(70,30));
      cb2.setPreferredSize(new Dimension(130,30));
      tf.setPreferredSize(new Dimension(210,30));
      btn.setPreferredSize(new Dimension(70,30));
      btn.setBorderPainted(false);
      // ------------------------------------
      
      cb1.setFont(st.fnt3);
      cb2.setFont(st.fnt3);
      btn.setFont(st.fnt1);
      tf.setFont(st.fnt3);
      cb1.setBackground(Color.white);
      cb2.setBackground(Color.white);
      btn.setBackground(st.clr1);
      btn.setForeground(Color.white);
      btn.setFocusable(false);//searchcenter.combo��ȭ����
   }
   

   @Override
   public void itemStateChanged(ItemEvent ie) {
      if(ie.getStateChange()==ItemEvent.SELECTED) {
         cb2.removeAllItems();
         //System.out.println("�׽�Ʈ");
         String sido = (String)cb1.getSelectedItem();
         if(sido.equals("�á���")) {
            //cb2.removeAllItems();
            cb2.addItem("�á�������");
         }
         else {
            Vector<String> list2 = locdao2.combo2(sido);               
             DefaultComboBoxModel<String> cb2model = new DefaultComboBoxModel<String>(list2);
             cb2.setModel(cb2model);
         }
        
      }
   }

}
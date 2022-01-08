package user.searchcenter;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.table.DefaultTableModel;

import database.AdminDAO;
import database.AdminVO;
import database.CenterDAO;
import database.CenterVO;
import user.home.MainTitle;
import user.home.SetStyle;

public class SearchCenterMain extends JPanel implements ActionListener, KeyListener{
   //����� ã�� �Ǹ޴� //���

   SetStyle style = new SetStyle();

   //��
   SearchCenterCombo cb = new SearchCenterCombo();
   SearchCenterSplit sp = new SearchCenterSplit();


   //��1 (��Ϻ���)
   public SearchCenterMain() {
      setLayout(new BorderLayout());


      //��1 (��Ϻ���)
      add(BorderLayout.NORTH, cb);
      add(sp);
      sp.setPreferredSize(new Dimension(880,500));

//      setSize(1020,800);
//      setVisible(true);
//      setDefaultCloseOperation(DISPOSE_ON_CLOSE);

      cb.btn.addActionListener(this);
      cb.tf.addActionListener(this);
      
   }
   //  //ȸ����! ����� �˻�21.08.01 ��ȭ�߰�  //�õ� �ñ��� �޺��ڽ� üũ���ϰ� �ؽ�Ʈ�ʵ常 �˻������� 
   public void centerSearch() {
      String loc1=(String)cb.cb1.getSelectedItem();//�޺��ڽ��� �ҷ����°� 
      String loc2=(String)cb.cb2.getSelectedItem();
      String center=cb.tf.getText();
      CenterDAO centerdao = new CenterDAO();
      //���� �˻�� �Է������� �޺��ڽ��� ���������� dao ���� �˻��϶�� ���� ���Ѿ߉�. 
      if(loc1.equals("�á���")&&loc2.equals("�á�������")) {
         // �ٿ���ä�� ���� �ٿ����� ���̵� ���� �����ϸ��.
         List<CenterVO>list = centerdao.memberCenterSearch(center);//�˻���˷��� �����̺���Ʈ����
         memberCenter(list);//�˻��� ����� ����Ʈ�� �������� ��.
      }else {
         List<CenterVO>list = centerdao.memberCenterSearch(loc1,loc2,center);//�˻���˷��� �����̺���Ʈ����1
         memberCenter(list);
      }
   }
   public void memberCenter( List<CenterVO> list) { //list �� �迭�̶�� �����ϸ�α� Ÿ���� memberVO Ÿ��
      sp.model.setRowCount(0);//�ɹ����� ��ü ������ִ±��
      sp.model2.setRowCount(0);
      for (int i=0; i<list.size();i++) {// for �������̺� �ִ� ���� ��Ǯ� �����ٰ� ~
         CenterVO vo= list.get(i);//�ϳ������°Ÿ� ����� 
         Object[]obj = {vo.getLoc1()+" "+vo.getLoc2(),vo.getCenter_name()};
         sp.model.addRow(obj);

         Object[] obj0 = {vo.getLoc1(),vo.getLoc2(),vo.getCenter_addr(),vo.getCenter_tel(),vo.getCenter_time1(),vo.getCenter_time2(),vo.getCenter_time3()};
         sp.model2.addRow(obj0);

      }
      sp.centerlist.setModel(sp.model);//�ٽ��ѹ� ���� �������ִ°�.
      sp.centerlist.updateUI();
   }


   @Override
   public void actionPerformed(ActionEvent e) {
      String event= e.getActionCommand();
      Object evr=e.getSource();
      if(event.equals("�˻�")|| evr==cb.tf) {
         centerSearch();
      }
      
      
//      
//   }
}
   @Override
   public void keyTyped(KeyEvent e) {
      // TODO Auto-generated method stub
      
   }
   @Override
   public void keyPressed(KeyEvent e) {
      if(e.getKeyCode()==KeyEvent.VK_ENTER) {
         centerSearch();
      }
   }
   @Override
   public void keyReleased(KeyEvent e) {
      // TODO Auto-generated method stub
      
   }public static void main(String[] args) {
//      new SearchCenterMain();   
   }
}
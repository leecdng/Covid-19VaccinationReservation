package user.searchcenter;

import javax.swing.table.DefaultTableModel;

import database.CenterDAO;
import database.CenterVO;
import database.LocDAO;
import database.LocVO;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;

import javax.swing.*;

public class SearchCenterSplit extends JPanel implements MouseListener, KeyListener{
   //�����ã�� (����Ʈ-������) //���
   
   JSplitPane splitpane;
   
   //���� (����Ҹ�� ���̺�)
   String title[] = {"����","����Ҹ�"};
   //String title[] = {"����Ҹ�"};
   DefaultTableModel model;
   JTable centerlist;
   JScrollPane sp;
   
   //�̸� �� ������� �����͵�
   String title2[] = {"�õ�","�ñ���","�ּ�","��ȭ��ȣ","�ð�1","�ð�2","�ð�3","�ڵ�"};
   DefaultTableModel model2;
   JTable codelist;
   
   Font font = new Font("���� ���",1, 25);
   Font font2 = new Font("���� ���",0, 15);
   Font font3 = new Font("���� ���",1, 15);
   Font font4 = new Font("���� ���",0, 15);
   
   JPanel mainpane = new JPanel(new BorderLayout()); //������ �г�
   JPanel allpane = new JPanel(new BorderLayout());
   JPanel northpane = new JPanel(new GridLayout(2,1));
      JPanel namepane = new JPanel(new BorderLayout());
         JLabel namelbl = new JLabel("����Ҹ�");
         JPanel btnpane = new JPanel();
            JButton mapbtn = new JButton("����");
      JLabel addrlbl = new JLabel("�ּ�");
   JPanel centerpane = new JPanel(new GridLayout(1,2,5,5));
      JPanel titlepane = new JPanel(new GridLayout(4,1,10,10));
         JLabel titlelbl = new JLabel();
         String[] infotitle = {"��ȭ��ȣ","���� ��ð�","����� ��ð�","�Ͽ���/������ ��ð�"};
      JPanel infopane = new JPanel (new GridLayout(4,1,10,10));
         JLabel infolbl[] = new JLabel[4];
         String[] infonull = {"-","~","~","~"};
         
      Color clr1 = new Color(38,80,150); // �Ķ���
         
      int row;
      public static String centerName;
      public static int code;
      
      CenterDAO dao = new CenterDAO();
      
      String centerAddr;

   public SearchCenterSplit() {  
      mainpane.setBorder(BorderFactory.createEmptyBorder(20,0,0,0)); //����
      mainpane.add(BorderLayout.NORTH,allpane);
      Dimension dd = new Dimension(400,500);
      mainpane.setPreferredSize(dd);
      allpane.add(BorderLayout.NORTH,northpane);
         northpane.setBorder(BorderFactory.createEmptyBorder(20,20,20,20)); //����
         northpane.add(namepane);
            namepane.setBorder(BorderFactory.createEmptyBorder(20,0,20,0)); //����
            namepane.add(BorderLayout.WEST,namelbl);
            namepane.add(BorderLayout.EAST,btnpane);
            btnpane.add(mapbtn);
         northpane.add(addrlbl);
         
     allpane.add(centerpane);
        centerpane.setBorder(BorderFactory.createEmptyBorder(20,20,20,20)); //����
        centerpane.add(titlepane);
        centerpane.add(infopane);
        
        for(int i=0; i<infotitle.length;i++) {
           titlelbl = new JLabel(infotitle[i]);
           titlelbl.setFont(font3);
           titlepane.add(titlelbl);
        }
        for(int i=0; i<infonull.length;i++) {
           infolbl[i] = new JLabel(infonull[i]);
           infolbl[i].setFont(font4);
           infopane.add(infolbl[i]);
        }
         
      //��Ʈ ����
      namelbl.setFont(font);
      addrlbl.setFont(font2);
      mapbtn.setBackground(clr1);
      mapbtn.setForeground(Color.white);
  
      //���̺�
      setTable();   
      centerAllList();
      centerlist.getColumnModel().getColumn(0).setPreferredWidth(10);
      centerlist.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

          
       //����ȭ�� ����
       splitpane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,sp,mainpane);
       add(splitpane);
       splitpane.setDividerLocation(300);

//       setSize(1200,800);
//       setVisible(true);
//       setDefaultCloseOperation(DISPOSE_ON_CLOSE);
       
       centerlist.addMouseListener(this);
       centerlist.addKeyListener(this);
       mapbtn.addMouseListener(this);
   }
   
   //���̺� ��� �������
   public void centerAllList() {  
      List<CenterVO> list = dao.selectAllCenterData();
      setCenterTable(list);
      
   }
   
   //���̺� ��� �����ϱ�
   public void setCenterTable(List<CenterVO> list) {
      model.setRowCount(0);
      
      for(int i=0;i<list.size();i++) {
         CenterVO vo = list.get(i);   
         
         Object[] obj = {vo.getLoc1()+" "+vo.getLoc2(),vo.getCenter_name()};
         model.addRow(obj);
         
         Object[] obj0 = {vo.getLoc1(),vo.getLoc2(),vo.getCenter_addr(),vo.getCenter_tel(),vo.getCenter_time1(),vo.getCenter_time2(),vo.getCenter_time3(), vo.getCenter_code()};
         model2.addRow(obj0);

      }
      
      centerlist.setRowHeight(20);
      centerlist.setFont(font4);

   }
   
   //���̺� ����
   public void setTable() {
      model = new DefaultTableModel(title,0);
      centerlist = new JTable();
      centerlist.setModel(model);//21.08.01������ȭ
      sp = new JScrollPane(centerlist);
      
      model2 = new DefaultTableModel(title2,0);
      codelist = new JTable(model2);
   }


@Override
public void mouseClicked(MouseEvent e) {
   if(e.getSource()==mapbtn) {
	      APIMain mapimg = new APIMain(centerAddr);
   }
   
}

@Override
public void mousePressed(MouseEvent e) {
   // TODO Auto-generated method stub
   
}

@Override
public void mouseReleased(MouseEvent e) {
   if(e.getButton()==1) {
      //
	   
      row = centerlist.getSelectedRow();
      eventsection(row);
   }
}

//�̺�Ʈ �޼���
public void eventsection(int row) {
    //����� �� ����
    centerName = String.valueOf(centerlist.getValueAt(row, 1));
    namelbl.setText(centerName);
    
    String addr = String.valueOf(codelist.getValueAt(row, 2));
    String tel = String.valueOf(codelist.getValueAt(row, 3));
    String time1 = String.valueOf(codelist.getValueAt(row, 4));
    String time2 = String.valueOf(codelist.getValueAt(row, 5));
    String time3 = String.valueOf(codelist.getValueAt(row, 6));
        
    String sido = String.valueOf(codelist.getValueAt(row, 0));
    String sigungu = String.valueOf(codelist.getValueAt(row, 1));
    centerAddr = addr;
    addrlbl.setText(sido+" "+sigungu+" "+centerAddr);
    
    code = (Integer)codelist.getValueAt(row, 7);
    centerAddr = addr;
    
    String infonull2[] = {tel,time1,time1,time3};
      for(int i=0; i<infonull.length;i++) {
         infolbl[i].setText(infonull2[i]);
         infolbl[i].setFont(font4);
      }
}

   @Override
   public void mouseEntered(MouseEvent e) {
      // TODO Auto-generated method stub
      
   }
   
   @Override
   public void mouseExited(MouseEvent e) {
      // TODO Auto-generated method stub
      
   }
   
   @Override
   public void keyTyped(KeyEvent e) {
      // TODO Auto-generated method stub
      
   }
   
   @Override
   public void keyPressed(KeyEvent e) {
      if(e.getKeyCode()==KeyEvent.VK_ENTER||e.getKeyCode()==KeyEvent.VK_UP||e.getKeyCode()==KeyEvent.VK_DOWN) {
         row = centerlist.getSelectedRow();
         eventsection(row);
      }
   }
   
   @Override
   public void keyReleased(KeyEvent e) {
   
   }
   
   
   public static void main(String[] args) {
//      new SearchCenterSplit();
   
   }
   
   }
//Ű�̺�Ʈ ���� ����Ű �����ÿ� �Ǵ°� 07.30 (o)
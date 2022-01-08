package admin.rsv;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import database.RsvDAO;
import database.RsvVO;
import user.home.SetStyle;
import user.rsv.RsvVaccineRadio;
import user.searchcenter.SearchCenterCombo;

public class ManageRsv extends JPanel implements ActionListener { // ���� // ��� ���� ���� (�����ڿ�)
   private String loc1 =""; // ������ �Ҽ�
   
   // NORTH
   JPanel northPane = new JPanel(new BorderLayout());
      RsvVaccineRadio vaccBtn = new RsvVaccineRadio();
      SearchCenterCombo search = new SearchCenterCombo(); // �˻�
   
   // CENTER
   JPanel pane = new JPanel(new BorderLayout());
      JScrollPane tbPane; // center
         JTable table = new JTable();
            DefaultTableModel model;
               String[] col = {"����� �ڵ�", "�õ�", "�ñ���", "����Ҹ�", "���೯¥", "����ð�", "���� ���", "������ ���̵�", "������ ����", "������ �ֹε�Ϲ�ȣ", "������ �޴�����ȣ"}; // �÷���
               int[] colSize = {50,20,100,100,100,40,70,90,60,100,100}; // �÷� �ּ� ������
      JPanel bottomPane = new JPanel(new BorderLayout()); // south
         JPanel btnPane = new JPanel();
            JPanel btnInnerPane = new JPanel(null);
            JButton[] btn = {new JButton("���� ����"), new JButton("��ü���"), new JButton("���� ����")};
            
   // ��Ʈ // �÷�
   SetStyle st = new SetStyle();
   
   public ManageRsv() {
      start();
   }
   
   
   public ManageRsv(String loc1) {
      this.loc1 = loc1;
      start();
   }

   
   
   // ȭ�� ����
   public void start() {
      setLayout(new BorderLayout());
      
      // �� �����ϸ鼭 ���� �Ұ��ϰ� ����
      model = new DefaultTableModel(col, 0) {
         public boolean isCellEditable(int rowIndex, int mColIndex) {
            return false;
         }
      };
      
      setManageRsvBtn(); // ���༳��/��ü���/������� ��ư ����
      
      if(loc1==null || loc1.equals("")) {   // ���� ������ ȭ��
         search = new SearchCenterCombo(); // �˻�â ����
         setAllRsvList();   // ��ü ����� ��� �ҷ�����
      } else {   // ���� ȭ��
         search = new SearchCenterCombo(loc1); // �˻�â ���� (�õ� �޺��ڽ� ����)
         setSearchList();   // �ش� �Ҽ� ���
      }
      
      
      // ���̺� ���� -- �÷� ������ ����
//      table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF); // ������ �÷� ����� �ְ� ��ũ�� ����
      table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS); // ��ũ�� ���� �÷��� â ����� �°� �þ
      for(int i=0; i<col.length; i++) {
         table.getColumnModel().getColumn(i).setMinWidth(colSize[i]);
      }
      table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
      
      
      tbPane = new JScrollPane(table);

      btnPane.setPreferredSize(new Dimension(500,75));
      bottomPane.setBorder(new EmptyBorder(15,0,0,0));
      bottomPane.add(btnPane, BorderLayout.SOUTH);
      
      search.setBorder(new EmptyBorder(0,0,15,0));
      pane.add(tbPane);
      pane.add(bottomPane, BorderLayout.SOUTH);
      pane.setBorder(new EmptyBorder(0,20,0,20));
      
      northPane.add(vaccBtn, BorderLayout.NORTH); // ---- ��� ���������� ��� ���� ������ư ��
      northPane.add(search);
      add(northPane, BorderLayout.NORTH);
      add(pane);
      
      // �̺�Ʈ ���
      search.btn.addActionListener(this);
      search.tf.addActionListener(this);
   }
   
   
   // ��� ���� ���� --------- ��ü ��� ���� ���
   public void setAllRsvList() {
      RsvDAO dao = new RsvDAO();
      List<RsvVO> list = dao.selectAllRsvData();
      
      setRsvList(list);
   }
   
   
   // ��� ���� ���� --------- ��ü ��� ���� ���
   public void setRsvList(List<RsvVO> list) {
      model.setRowCount(0);

      for(int i=0; i<list.size(); i++) {
         RsvVO vo = list.get(i);
         String user_num = vo.getUser_num().substring(0,6) + "-" + vo.getUser_num().substring(6,7).concat("******"); // �ֹε�Ϲ�ȣ 000000-0****** �������� �ٲٱ�
         String user_tel = vo.getUser_tel().substring(0,3) + "-" + vo.getUser_tel().substring(3,7) + "-" + vo.getUser_tel().substring(7); // �޴�����ȣ 000-0000-0000 �������� �ٲٱ�
         Object[] record = {vo.getCenter_code(), vo.getLoc1(), vo.getLoc2(), vo.getCenter_name(), vo.getRsv_date(), vo.getRsv_hour(), vo.getVc_type(), vo.getUser_id(), vo.getUser_name(), user_num, user_tel};
         model.addRow(record);
      }
      table.setModel(model);
      table.updateUI();
   }

   
   // ���༳��/��ü���/���� ��ư ����
   public void setManageRsvBtn() {
      for(int i=0; i<btn.length; i++) {
         btn[i].setFont(st.fnt1);
         btn[i].setBounds(165*i, 0, 150, 40);
         btn[i].setBackground(st.clr1);
         btn[i].setForeground(Color.WHITE);
         btn[i].setBorderPainted(false);
         if(loc1==null || loc1.equals("")) { // ���۰����ڴ� ���༳�� ��ư ����
        	 btn[i].setBounds(165*i, 0, 150, 40);
        	 btnInnerPane.add(btn[i]);
         } else {							// ���������ڸ� ���༳�� ��ư ����
        	 if(i>=1) {
	        	 btn[i].setBounds(165*(i-1), 0, 150, 40);
	        	 btnInnerPane.add(btn[i]);
        	 }
         }         
         btn[i].addActionListener(this);
      }
      if(loc1==null || loc1.equals("")) btnInnerPane.setPreferredSize(new Dimension(480,75)); // ���۰�����
      else btnInnerPane.setPreferredSize(new Dimension(315,75)); // ����������

      btnPane.add(btnInnerPane);
   }
   
   
   // [����] ��ư �̺�Ʈ �߻� �� ------- ���� ���� ���� �˻� / ���� ������ ȸ�� ���̵� ����
   public void selectDelRsv() {
      int row = table.getSelectedRow();
      if(row==-1) { // ���� ���õ��� �ʾ��� ��
         JOptionPane.showMessageDialog(this, "������ ��� ���� ������ ������ �ּ���.");
      } else {
         int result = JOptionPane.showConfirmDialog(this, "���� �����Ͻðڽ��ϱ�?", "����", JOptionPane.OK_CANCEL_OPTION);
         if(result == JOptionPane.OK_OPTION) {
            String user_id = (String) model.getValueAt(row, 7); // ����� �ڵ� ������
            deleteRsv(user_id);
         }
      }      
   }
   
   // ��� ���� ���� ����
   public void deleteRsv(String user_id) {
      RsvDAO dao = new RsvDAO();
      int cnt = dao.deleteRsvData(user_id);
      if(cnt>0) {
         JOptionPane.showMessageDialog(this, "�ش� ��� ���� ������ �����Ǿ����ϴ�.");
         setAllRsvList();
      } else {
         JOptionPane.showMessageDialog(this, "��� ���� ���� ������ �����Ͽ����ϴ�. �ٽ� �õ��� �ּ���.");
      }
   }
   
   
   // [���� ����] â ����
   public void openRsvSetting() {
      RsvSetting dialog = new RsvSetting();
//      JDialog dialog = new JDialog();

      dialog.setVisible(true);
   }
   
   // �˻� ��ư ������ �� --- �˻� ��� ����ϰ�
   public void setSearchList() {
      String loc1 = (String) search.cb1.getSelectedItem();
      String loc2 = (String) search.cb2.getSelectedItem();
      String searchTxt = search.tf.getText();
      String vc_type = vaccBtn.vc_type;
      
      RsvDAO dao = new RsvDAO();
      List<RsvVO> list;

      if(loc1.equals("�á���") && loc2.equals("�á�������")) { // 
         list = dao.getSearchRsvData(vc_type, searchTxt);
      } else if(!loc1.equals("�á���") && loc2.equals("�á�������")) { // 
         list = dao.getSearchRsvData(vc_type, loc1, searchTxt);
      } else {
         list = dao.getSearchRsvData(vc_type, loc1, loc2, searchTxt);
      }
      setRsvList(list);  // �ش� ��� ���
   }


   @Override
   public void actionPerformed(ActionEvent e) {
      Object evt = e.getSource();
      String evtStr= e.getActionCommand();
      if(evtStr.equals("���� ����")) {
         openRsvSetting();
      } else if(evtStr.equals("��ü���")) {
         search.tf.setText("");         // �˻�â �ʱ�ȭ
         vaccBtn.rBtn0.setSelected(true); // ��ü ��� ����
         vaccBtn.vc_type = ""; // ��� �� �ʱ�ȭ
         if(loc1==null || loc1.equals("")) { // ���� ������
            search.cb1.setSelectedIndex(0); // �õ� �޺��ڽ� �ʱ�ȭ
            setAllRsvList(); // ��ü ���
         } else {                     // ���� ������
            search.cb2.setSelectedIndex(0); // �ñ��� �޺��ڽ� �ʱ�ȭ
            setSearchList();   // �ش� �Ҽ� ��ü ���
         }
      } else if(evtStr.equals("���� ����")) {
         selectDelRsv();
      } else if(evt == search.tf || evtStr.equals("�˻�")) {
         setSearchList();
      }
   }
}
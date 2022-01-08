package database;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class CenterDAO extends DBCON {

   public CenterDAO() {
      
   }
   
   // ���� �߰� ------------- //��� ����
   // ����� ���̺� ��ü �ҷ����� // ���� �ɷ��� �ϴ��� ������ �߰��ߴµ� ���� ����. (where center_code<1100)
   public List<CenterVO> selectAllCenterData(){
      List<CenterVO> list = new ArrayList<CenterVO>();
      try {
         dbConn();
         
         String sql = "select c.center_code, c.center_name, c.loc_code, c.center_addr, c.center_tel, c.center_time1, c.center_time2, c.center_time3, l.loc1, l.loc2 "
               + "from centerData c join locData l "
               + "on c.loc_code=l.loc_code order by center_code";
         
         pstmt = conn.prepareStatement(sql);
         rs = pstmt.executeQuery();
         while(rs.next()) {
            CenterVO vo = new CenterVO();
            vo.setCenter_code(rs.getInt(1));
            vo.setCenter_name(rs.getString(2));
            vo.setLoc_code(rs.getInt(3));
            vo.setCenter_addr(rs.getString(4));
            vo.setCenter_tel(rs.getString(5));
            vo.setCenter_time1(rs.getString(6));
            vo.setCenter_time2(rs.getString(7));
            vo.setCenter_time3(rs.getString(8));
            vo.setLoc1(rs.getString(9));
            vo.setLoc2(rs.getString(10));
            
            list.add(vo);
         }
      } catch(Exception e) {
         System.out.println("����� ��ü ��� �ҷ����� ����");
         e.printStackTrace();
      } finally {
         dbClose();
      }
      return list;
   }
   
   
   // ���� �߰� -------------
   // ����� �ڵ�� �ش� ����� 1�� ���� ���� �ҷ����� ----- List ��ȯ -------------- selectCenter(int center_code)�� �ߺ��̰� ��ȯ Ÿ���� �ٸ�
   public List<CenterVO> selectCenterData(int center_code) {
      List<CenterVO> list = new ArrayList<CenterVO>();
      
      try {
         dbConn();
         
         String sql = "select center_code, center_name, loc_code, center_addr, center_tel, center_time1, center_time2, center_time3 from centerdata where center_code=?";
         
         pstmt=conn.prepareStatement(sql);
         pstmt.setInt(1, center_code);
         
         rs=pstmt.executeQuery();
         while(rs.next()) {
            CenterVO vo= new CenterVO();
            vo.setCenter_code(rs.getInt(1));
            vo.setCenter_name(rs.getString(2));
            vo.setLoc_code(rs.getInt(3));
            vo.setCenter_addr(rs.getString(4));
            vo.setCenter_tel(rs.getString(5));
            vo.setCenter_time1(rs.getString(6));
            vo.setCenter_time2(rs.getString(7));
            vo.setCenter_time3(rs.getString(8));
            
            list.add(vo);
         }
      } catch (Exception e) {
         System.out.println("����� ���� �ҷ����� ����");
         e.printStackTrace();
      } finally {
         dbClose();
      }
      return list;
   }
   // -----------------------
   
   
   
   // ���� �߰� -------------
   // ����� �ڵ�� �ش� ����� 1�� ���� ���� �ҷ����� ----- CenterVO ��ȯ(�����ϸ� �̰ɷ� ����)
   public CenterVO selectCenter(int center_code) {
      CenterVO vo= new CenterVO();
      try {
         dbConn();
         
         String sql = "select center_code, center_name, loc_code, center_addr, center_tel, center_time1, center_time2, center_time3 from centerdata where center_code=?";
         
         pstmt=conn.prepareStatement(sql);
         pstmt.setInt(1, center_code);
         
         rs=pstmt.executeQuery();
         while(rs.next()) {
            vo.setCenter_code(rs.getInt(1));
            vo.setCenter_name(rs.getString(2));
            vo.setLoc_code(rs.getInt(3));
            vo.setCenter_addr(rs.getString(4));
            vo.setCenter_tel(rs.getString(5));
            vo.setCenter_time1(rs.getString(6));
            vo.setCenter_time2(rs.getString(7));
            vo.setCenter_time3(rs.getString(8));
         }
      } catch (Exception e) {
         System.out.println("����� ���� �ҷ����� ����");
         e.printStackTrace();
      } finally {
         dbClose();
      }
      return vo;
   }
   // -----------------------
   
   
   // ���� �߰� -------------
   // ����� 1�� ��ȸ ----- ����� ������� ����� �ڵ� ������ (��� ������ ��Ͽ�)
   public int getCenterCordData(CenterVO vo) {
      int center_code=0;
      try {
         dbConn();
         
         String sql = "select center_code from centerdata where center_name=? and loc_code=? and center_addr=? and center_tel=?";
         
         pstmt=conn.prepareStatement(sql);
         pstmt.setString(1, vo.getCenter_name());
         pstmt.setInt(2, vo.getLoc_code());
         pstmt.setString(3, vo.getCenter_addr());
         pstmt.setString(4, vo.getCenter_tel());
         
         rs=pstmt.executeQuery();
         while(rs.next()) {
            center_code = rs.getInt(1);
         }
      } catch (Exception e) {
         System.out.println("����� ���� �ҷ����� ����");
         e.printStackTrace();
      } finally {
         dbClose();
      }
      return center_code;
   }
   // -----------------------
   

   // ���� �߰�
   // ����� ���� ��� (�߰�)
   public int insertCenterData(CenterVO vo) {
      int cnt = 0;
      try {
         dbConn();
         
         String sql = "insert into centerdata(center_name, loc_code, center_addr, center_tel, center_time1, center_time2, center_time3)"
                  + " values(?, ?, ?, ?, ?, ?, ?)";
         
         pstmt = conn.prepareStatement(sql);
         pstmt.setString(1, vo.getCenter_name());
         pstmt.setInt(2, vo.getLoc_code());
         pstmt.setString(3, vo.getCenter_addr());
         pstmt.setString(4, vo.getCenter_tel());
         pstmt.setString(5, vo.getCenter_time1());
         if(vo.getCenter_time2()==null || vo.getCenter_time2().equals("")) pstmt.setString(6, "�̿");
         else pstmt.setString(6, vo.getCenter_time2());
         if(vo.getCenter_time3()==null || vo.getCenter_time3().equals("")) pstmt.setString(7, "�̿");
         else pstmt.setString(7, vo.getCenter_time3());
         
         cnt = pstmt.executeUpdate();
         
      } catch(Exception e) {
         System.out.println("����� ���� ��� ����");
         e.printStackTrace();
      } finally {
         dbClose();
      }
      return cnt;
   }
   
   
   // ���� �߰�
   // ����� ���� ����
   public int updateCenterData(CenterVO vo) {
      int cnt = 0;
      try {
         dbConn();
         
         String sql = "update centerdata set center_name=?, loc_code=?, center_addr=?, center_tel=?, center_time1=?, center_time2=?, center_time3=? where center_code=?";
         
         pstmt=conn.prepareStatement(sql);
         pstmt.setString(1, vo.getCenter_name());
         pstmt.setInt(2, vo.getLoc_code());
         pstmt.setString(3, vo.getCenter_addr());
         pstmt.setString(4, vo.getCenter_tel());
         pstmt.setString(5, vo.getCenter_time1());
         if(vo.getCenter_time2()==null || vo.getCenter_time2().equals("")) pstmt.setString(6, "�̿");
         else pstmt.setString(6, vo.getCenter_time2());
         if(vo.getCenter_time3()==null || vo.getCenter_time3().equals("")) pstmt.setString(7, "�̿");
         else pstmt.setString(7, vo.getCenter_time3());
         pstmt.setInt(8, vo.getCenter_code());
         
         cnt = pstmt.executeUpdate();
      } catch (Exception e) {
         System.out.println("����� ���� ����");
         e.printStackTrace();
      } finally {
         dbClose();
      }
      return cnt;
   }
   
   
   // ���� �߰�
   // ����� ���� ����
   public int deleteCenterData(int center_code) {
      int cnt = 0;
      try {
         dbConn();
         
         String sql = "delete from centerdata where center_code=?";
         
         pstmt = conn.prepareStatement(sql);
         pstmt.setInt(1, center_code);
         
         cnt = pstmt.executeUpdate();
         
      } catch(Exception e) {
         System.out.println("����� ���� ���� ����");
         e.printStackTrace();
      } finally {
         dbClose();
      }
      return cnt;
   }
   
   
   //ȸ����! ����� �˻�21.08.01 ��ȭ�߰�  //�õ� �ñ��� �޺��ڽ� üũ���ϰ� �ؽ�Ʈ�ʵ常 �˻������� (������ ���� ����� ���� �߿���)
   public List<CenterVO> memberCenterSearch(String centerName){
     List<CenterVO> list = new ArrayList<CenterVO>();
     try {
        dbConn();
      
        String sql = "select c.center_code, c.center_name, c.loc_code ,l.loc1,l.loc2, c.center_addr,c.center_time1,c.center_time2,c.center_time3,center_tel"
                 + " from centerdata c join locdata l on c.loc_code=l.loc_code where center_name like ? order by center_code";
        pstmt = conn.prepareStatement(sql);
        //?���� ���� �־��ְ� �ؿ��Ÿ� �Ǿ��ؾ��Ѵ�.
        pstmt.setString(1,"%"+centerName+"%"); // '%����%' centername �˻�� ���ϴ°Ŵ�.
        rs = pstmt.executeQuery();//������̺� ����ִµ�
        
        while(rs.next()) { //������̺��� Ǭ��
           CenterVO vo = new CenterVO();
           vo.setCenter_code(rs.getInt(1));
           vo.setCenter_name(rs.getString(2));
           vo.setLoc_code(rs.getInt(3));
           vo.setLoc1(rs.getString(4));
           vo.setLoc2(rs.getString(5));
           vo.setCenter_addr(rs.getString(6));
           vo.setCenter_time1(rs.getString(7));
           vo.setCenter_time2(rs.getString(8));
           vo.setCenter_time3(rs.getString(9));
           vo.setCenter_tel(rs.getString(10));

           list.add(vo);
        }
     } catch(Exception e) {
        System.out.println("ȸ���� ����� �ؽ�Ʈ�ʵ� �˻� ����...");
        e.printStackTrace();
     } finally {
        dbClose();
     }
     return list;
   }
   
   
   //ȸ����! ����� �˻�21.08.01 ��ȭ�߰�  //�õ� �ñ��� �޺��ڽ��� ����������  (������ ���� ����� ���� �߿���)
   public List<CenterVO> memberCenterSearch(String loc1, String loc2, String centerName){
     List<CenterVO> list = new ArrayList<CenterVO>();
     try {
        dbConn();
        //�õ� �ñ��� �޺��ڽ� üũ���ϰ� �ؽ�Ʈ�ʵ常 �˻������� (������ ���� ����� ���� �߿���)
        String sql = "select c.center_code, c.center_name, c.loc_code ,l.loc1,l.loc2, c.center_addr,c.center_time1,c.center_time2,c.center_time3,center_tel"
              + " from centerdata c join locdata l on c.loc_code=l.loc_code where loc1=? and loc2=? and center_name like ? order by center_code";
        pstmt = conn.prepareStatement(sql);
        //?���� ���� �־��ְ� �ؿ��Ÿ� �Ǿ��ؾ��Ѵ�.
       
        pstmt.setString(1,loc1);
        pstmt.setString(2,loc2);
        pstmt.setString(3,"%"+centerName+"%"); // '%����%' centername �˻�� ���ϴ°Ŵ�.
        rs = pstmt.executeQuery();//������̺� ����ִµ�
        
        while(rs.next()) { //������̺��� Ǭ��
           CenterVO vo = new CenterVO();
           vo.setCenter_code(rs.getInt(1));
           vo.setCenter_name(rs.getString(2));
           vo.setLoc_code(rs.getInt(3));
           vo.setLoc1(rs.getString(4));
           vo.setLoc2(rs.getString(5));
           vo.setCenter_addr(rs.getString(6));
           vo.setCenter_time1(rs.getString(7));
           vo.setCenter_time2(rs.getString(8));
           vo.setCenter_time3(rs.getString(9));
           vo.setCenter_tel(rs.getString(10));

           list.add(vo);
        }
     } catch(Exception e) {
        System.out.println("ȸ���� ����� �õ�, �ñ���, �ؽ�Ʈ�ʵ� �˻� ����...");
        e.printStackTrace();
     } finally {
        dbClose();
     }
     return list;
  }
   
   
   // ���� �߰� -------------
   // (�����ڿ�) ����� ���� --- ����� �˻�
   // �ؽ�Ʈ�ʵ常 �˻����� ��
   public List<CenterVO> getSearchCenterData(String searchTxt){
      List<CenterVO> list = new ArrayList<CenterVO>();
      try {
         dbConn();
         
         String sql = "select center_code, center_name, c.loc_code, loc1, loc2, center_addr, center_tel, center_time1, center_time2, center_time3"
         		+ " from centerData c join locData l"
         		+ " on c.loc_code=l.loc_code"
         		+ " where center_code like ? or center_name like ? or center_addr like ? or center_tel like ?"
         		+ " order by center_code";
         
         pstmt = conn.prepareStatement(sql);
         pstmt.setString(1, "%"+searchTxt+"%");
         pstmt.setString(2, "%"+searchTxt+"%");
         pstmt.setString(3, "%"+searchTxt+"%");
         pstmt.setString(4, "%"+searchTxt+"%");
         
         rs = pstmt.executeQuery();
         while(rs.next()) {
            CenterVO vo = new CenterVO();
            vo.setCenter_code(rs.getInt(1));
            vo.setCenter_name(rs.getString(2));
            vo.setLoc_code(rs.getInt(3));
            vo.setLoc1(rs.getString(4));
            vo.setLoc2(rs.getString(5));
            vo.setCenter_addr(rs.getString(6));
            vo.setCenter_tel(rs.getString(7));
            vo.setCenter_time1(rs.getString(8));
            vo.setCenter_time2(rs.getString(9));
            vo.setCenter_time3(rs.getString(10));
            
            list.add(vo);
         }
      } catch(Exception e) {
         System.out.println("�����ڿ� ����� ���� �ؽ�Ʈ�ʵ� �˻� �ҷ����� ����");
         e.printStackTrace();
      } finally {
         dbClose();
      }
      return list;
   }
   
   
   // ���� �߰� -------------
   // (�����ڿ�) ����� ���� --- ����� �˻�
   // �õ�, �ñ���, �ؽ�Ʈ�ʵ� �˻����� ��
   public List<CenterVO> getSearchCenterData(String loc1, String loc2, String searchTxt){
      List<CenterVO> list = new ArrayList<CenterVO>();
      try {
         dbConn();
         
         String sql = "select center_code, center_name, c.loc_code, loc1, loc2, center_addr, center_tel, center_time1, center_time2, center_time3"
         		+ " from centerData c join locData l"
         		+ " on c.loc_code=l.loc_code"
         		+ " where loc1=? and loc2=? and (center_code like ? or center_name like ? or center_addr like ? or center_tel like ?)"
         		+ " order by center_code";
         
         pstmt = conn.prepareStatement(sql);
         pstmt.setString(1, loc1);
         pstmt.setString(2, loc2);
         pstmt.setString(3, "%"+searchTxt+"%");
         pstmt.setString(4, "%"+searchTxt+"%");
         pstmt.setString(5, "%"+searchTxt+"%");
         pstmt.setString(6, "%"+searchTxt+"%");
         
         rs = pstmt.executeQuery();
         while(rs.next()) {
            CenterVO vo = new CenterVO();
            vo.setCenter_code(rs.getInt(1));
            vo.setCenter_name(rs.getString(2));
            vo.setLoc_code(rs.getInt(3));
            vo.setLoc1(rs.getString(4));
            vo.setLoc2(rs.getString(5));
            vo.setCenter_addr(rs.getString(6));
            vo.setCenter_tel(rs.getString(7));
            vo.setCenter_time1(rs.getString(8));
            vo.setCenter_time2(rs.getString(9));
            vo.setCenter_time3(rs.getString(10));
            
            list.add(vo);
         }
      } catch(Exception e) {
         System.out.println("�����ڿ� ����� ���� �õ�, �ñ���, �ؽ�Ʈ�ʵ� �˻� �ҷ����� ����");
         e.printStackTrace();
      } finally {
         dbClose();
      }
      return list;
   }
   
   
   // ���� �߰� -------------
   // (���� �����ڿ�) ����� ���� --- ����� �˻�
   // �õ�, �ؽ�Ʈ�ʵ� �˻����� ��
   public List<CenterVO> getSearchCenterData(String loc1, String searchTxt){
      List<CenterVO> list = new ArrayList<CenterVO>();
      try {
         dbConn();
         
         String sql = "select center_code, center_name, c.loc_code, loc1, loc2, center_addr, center_tel, center_time1, center_time2, center_time3"
         		+ " from centerData c join locData l"
         		+ " on c.loc_code=l.loc_code"
         		+ " where loc1=? and (center_code like ? or center_name like ? or center_addr like ? or center_tel like ?)"
         		+ " order by center_code";
         
         pstmt = conn.prepareStatement(sql);
         pstmt.setString(1, loc1);
         pstmt.setString(2, "%"+searchTxt+"%");
         pstmt.setString(3, "%"+searchTxt+"%");
         pstmt.setString(4, "%"+searchTxt+"%");
         pstmt.setString(5, "%"+searchTxt+"%");
         
         rs = pstmt.executeQuery();
         while(rs.next()) {
            CenterVO vo = new CenterVO();
            vo.setCenter_code(rs.getInt(1));
            vo.setCenter_name(rs.getString(2));
            vo.setLoc_code(rs.getInt(3));
            vo.setLoc1(rs.getString(4));
            vo.setLoc2(rs.getString(5));
            vo.setCenter_addr(rs.getString(6));
            vo.setCenter_tel(rs.getString(7));
            vo.setCenter_time1(rs.getString(8));
            vo.setCenter_time2(rs.getString(9));
            vo.setCenter_time3(rs.getString(10));
            
            list.add(vo);
         }
      } catch(Exception e) {
         System.out.println("�����ڿ� ����� ���� �õ�, �ؽ�Ʈ�ʵ� �˻� �ҷ����� ����");
         e.printStackTrace();
      } finally {
         dbClose();
      }
      return list;
   }
   
}
package database;

public class RsvSettingDAO extends DBCON {

	public RsvSettingDAO() {
		// TODO Auto-generated constructor stub
	}
	
	
	// ���� �߰� -------------
   // ���� ������ �ҷ�����
   public RsvSettingVO selectRsvSetting() {
	   RsvSettingVO vo= new RsvSettingVO();
      try {
         dbConn();
         
         String sql = "select no, to_char(startdate, 'yyyy/mm/dd'), to_char(enddate, 'yyyy/mm/dd'), age1, age2, notice_title, notice from rsvSettingData where no=1";
         
         pstmt=conn.prepareStatement(sql);
         
         rs=pstmt.executeQuery();
         while(rs.next()) {
            vo.setNo(rs.getInt(1));
            vo.setStartDate(rs.getString(2));
            vo.setEndDate(rs.getString(3));
            vo.setAge1(rs.getInt(4));
            vo.setAge2(rs.getInt(5));
            vo.setNotice_title(rs.getString(6));
            vo.setNotice(rs.getString(7));
         }
      } catch (Exception e) {
         System.out.println("���� ������ �ҷ����� ����");
         e.printStackTrace();
      } finally {
         dbClose();
      }
      return vo;
   }
   
   
   // ���� �߰�
   // ���� ���� ����
   public int updateRsvSetting(RsvSettingVO vo) {
      int cnt = 0;
      try {
         dbConn();
         
         String sql = "update rsvSettingData set startdate=?, enddate=?, age1=?, age2=? where no=1";
         
         pstmt=conn.prepareStatement(sql);
         pstmt.setString(1, vo.getStartDate());
         pstmt.setString(2, vo.getEndDate());
         pstmt.setInt(3, vo.getAge1());
         pstmt.setInt(4, vo.getAge2());
         
         cnt = pstmt.executeUpdate();
      } catch (Exception e) {
         System.out.println("���� ���� ���� ����");
         e.printStackTrace();
      } finally {
         dbClose();
      }
      return cnt;
   }
   
   // ���� �߰�
   // ���� ���� �������� ���� ����
   public int updateRsvNotice(String notice_title, String notice) {
      int cnt = 0;
      try {
         dbConn();
         
         String sql = "update rsvSettingData set notice_title=?, notice=? where no=1";
         
         pstmt=conn.prepareStatement(sql);
         pstmt.setString(1, notice_title);
         pstmt.setString(2, notice);
         
         cnt = pstmt.executeUpdate();
      } catch (Exception e) {
         System.out.println("���� �������� ���� ����");
         e.printStackTrace();
      } finally {
         dbClose();
      }
      return cnt;
   }

}

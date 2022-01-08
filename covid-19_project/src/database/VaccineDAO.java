package database;

import java.util.ArrayList;
import java.util.List;

public class VaccineDAO extends DBCON{

	public VaccineDAO() {
		
	}
	
	// ���� �߰� -------------
	// ��ü vaccineData ��� ���̺� �ҷ�����
	public List<VaccineVO> getAllVaccData(){
		List<VaccineVO> list = new ArrayList<VaccineVO>();
		try {
			dbConn();
			
			String sql = "select v.center_code, c.loc1, c.loc2, c.center_name, c.center_tel, v.jansen, v.az, v.pfizer, v.moderna"
					+ " from vaccinedata v"
					+ " join (select center_code, center_name, loc1, loc2, center_tel from centerData c join locData l on c.loc_code=l.loc_code) c"
					+ " on v.center_code=c.center_code"
					+ " order by c.center_code";
			
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				VaccineVO vo = new VaccineVO();
				vo.setCenter_code(rs.getInt(1));
				vo.setLoc1(rs.getString(2));
				vo.setLoc2(rs.getString(3));
				vo.setCenter_name(rs.getString(4));
				vo.setCenter_tel(rs.getString(5));
				vo.setJansen(rs.getInt(6));
				vo.setAz(rs.getInt(7));
				vo.setPfizer(rs.getInt(8));
				vo.setModerna(rs.getInt(9));

				list.add(vo);
			}
		} catch(Exception e) {
			System.out.println("��� ��ü ��� �ҷ����� ����");
			e.printStackTrace();
		} finally {
			dbClose();
		}
		return list;
	}

	
	// ���� �߰� -------------
	// ��� --- 1���� ��� ������ ��� // ����� ��� �� �ڵ� ��ϵ�
	public int insertVaccData(int center_code){
		int cnt=0;
		try {
			dbConn();
			
			String sql = "insert into vaccinedata(center_code) values(?)";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, center_code);
		
			cnt = pstmt.executeUpdate();

		} catch(Exception e) {
			System.out.println("��� ������ ��� ����");
				e.printStackTrace();
			} finally {
				dbClose();
			}
		return cnt;
	}
	
	
	
	// ���� �߰� -------------
	// ���� --- 1���� vaccineData ��� ���̺� ����
	public int updateVaccData(VaccineVO vo){
		int cnt=0;
		try {
			dbConn();
			
			String sql = "update vaccinedata set jansen=?, az=?, pfizer=?, moderna=? where center_code=?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, vo.getJansen());
			pstmt.setInt(2, vo.getAz());
			pstmt.setInt(3, vo.getPfizer());
			pstmt.setInt(4, vo.getModerna());
			pstmt.setInt(5, vo.getCenter_code());
		
			cnt = pstmt.executeUpdate();

		} catch(Exception e) {
			System.out.println("��� ���� ���� ����");
				e.printStackTrace();
			} finally {
				dbClose();
			}
		return cnt;
	}
	
	
	// ���� �߰� ------------- 210801
	// �˻� // vaccineData ��� �κ� ���̺� �ҷ�����
	// �õ�, �ñ��� ���� �� �˻� ��
	public List<VaccineVO> getSearchVaccData(String loc1, String loc2, String searchTxt){
		List<VaccineVO> list = new ArrayList<VaccineVO>();
		try {
			dbConn();
			
			String sql = "select v.center_code, c.loc1, c.loc2, c.center_name, c.center_tel, v.jansen, v.az, v.pfizer, v.moderna"
					+ " from vaccinedata v"
					+ " join (select center_code, center_name, loc1, loc2, center_tel from centerData c join locData l on c.loc_code=l.loc_code) c"
					+ " on v.center_code=c.center_code"
					+ " where c.loc1=? and c.loc2=? and (c.center_name like ? or c.center_code like ? or c.center_tel like ?)"
					+ " order by c.center_code";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, loc1);
			pstmt.setString(2, loc2);
			pstmt.setString(3, "%"+searchTxt+"%");
			pstmt.setString(4, "%"+searchTxt+"%");
			pstmt.setString(5, "%"+searchTxt+"%");
			
			rs = pstmt.executeQuery();
			while(rs.next()) {
				VaccineVO vo = new VaccineVO();
				vo.setCenter_code(rs.getInt(1));
				vo.setLoc1(rs.getString(2));
				vo.setLoc2(rs.getString(3));
				vo.setCenter_name(rs.getString(4));
				vo.setCenter_tel(rs.getString(5));
				vo.setJansen(rs.getInt(6));
				vo.setAz(rs.getInt(7));
				vo.setPfizer(rs.getInt(8));
				vo.setModerna(rs.getInt(9));

				list.add(vo);
			}
		} catch(Exception e) {
			System.out.println("��� ��ü ��� �ҷ����� ����");
			e.printStackTrace();
		} finally {
			dbClose();
		}
		return list;
	}
	
	
	// ���� �߰� ------------- 210801
	// �˻� // vaccineData ��� �κ� ���̺� �ҷ�����
	// �õ�, �ñ��� ���� �� �ϰ� �˻� ��
	public List<VaccineVO> getSearchVaccData(String searchTxt){
		List<VaccineVO> list = new ArrayList<VaccineVO>();
		try {
			dbConn();
			
			String sql = "select v.center_code, c.loc1, c.loc2, c.center_name, c.center_tel, v.jansen, v.az, v.pfizer, v.moderna"
					+ " from vaccinedata v"
					+ " join (select center_code, center_name, loc1, loc2, center_tel from centerData c join locData l on c.loc_code=l.loc_code) c"
					+ " on v.center_code=c.center_code"
					+ " where c.center_name like ? or c.center_code like ? or c.center_tel like ?"
					+ " order by c.center_code";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%"+searchTxt+"%");
			pstmt.setString(2, "%"+searchTxt+"%");
			pstmt.setString(3, "%"+searchTxt+"%");
			
			rs = pstmt.executeQuery();
			while(rs.next()) {
				VaccineVO vo = new VaccineVO();
				vo.setCenter_code(rs.getInt(1));
				vo.setLoc1(rs.getString(2));
				vo.setLoc2(rs.getString(3));
				vo.setCenter_name(rs.getString(4));
				vo.setCenter_tel(rs.getString(5));
				vo.setJansen(rs.getInt(6));
				vo.setAz(rs.getInt(7));
				vo.setPfizer(rs.getInt(8));
				vo.setModerna(rs.getInt(9));

				list.add(vo);
			}
		} catch(Exception e) {
			System.out.println("��� ��ü ��� �ҷ����� ����");
			e.printStackTrace();
		} finally {
			dbClose();
		}
		return list;
	}
	
	
	// ���� �߰� ------------- 210801
	// �˻� // vaccineData ��� �κ� ���̺� �ҷ�����
	// ���� ������ ���� �� ----- (�õ� ���� �� �˻�)
	public List<VaccineVO> getSearchVaccData(String loc1, String searchTxt){
		List<VaccineVO> list = new ArrayList<VaccineVO>();
		try {
			dbConn();
			
			String sql = "select v.center_code, c.loc1, c.loc2, c.center_name, c.center_tel, v.jansen, v.az, v.pfizer, v.moderna"
					+ " from vaccinedata v"
					+ " join (select center_code, center_name, loc1, loc2, center_tel from centerData c join locData l on c.loc_code=l.loc_code) c"
					+ " on v.center_code=c.center_code"
					+ " where c.loc1=? and (c.center_name like ? or c.center_code like ? or c.center_tel like ?)"
					+ " order by c.center_code";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, loc1);
			pstmt.setString(2, "%"+searchTxt+"%");
			pstmt.setString(3, "%"+searchTxt+"%");
			pstmt.setString(4, "%"+searchTxt+"%");
			
			rs = pstmt.executeQuery();
			while(rs.next()) {
				VaccineVO vo = new VaccineVO();
				vo.setCenter_code(rs.getInt(1));
				vo.setLoc1(rs.getString(2));
				vo.setLoc2(rs.getString(3));
				vo.setCenter_name(rs.getString(4));
				vo.setCenter_tel(rs.getString(5));
				vo.setJansen(rs.getInt(6));
				vo.setAz(rs.getInt(7));
				vo.setPfizer(rs.getInt(8));
				vo.setModerna(rs.getInt(9));

				list.add(vo);
			}
		} catch(Exception e) {
			System.out.println("��� ��ü ��� �ҷ����� ����");
			e.printStackTrace();
		} finally {
			dbClose();
		}
		return list;
	}
	
	
	// ���� �߰� ------------- 210802
	// ���� --- ��� ���� �� ���� ���� -1 �ϱ�
	public int minusVaccData(String vc_type, int center_code){
		int cnt=0;
		try {
			dbConn();
			
			String sql = "update vaccinedata set " + vc_type + "=" + vc_type +"-1 where center_code=?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, center_code);

			cnt = pstmt.executeUpdate();

		} catch(Exception e) {
			System.out.println("���� ��� ���� ���� ����");
				e.printStackTrace();
			} finally {
				dbClose();
			}
		return cnt;
	}
	
	
	//0802 ���� ��� ���� +1���� 
   public int plusVaccData(String vc_type, int center_code) {
      int cnt = 0;
      try{
         dbConn();
         String sql = "update vaccinedata set "+vc_type+"="+vc_type+"+1 where center_code=?";
         
         pstmt = conn.prepareStatement(sql);
         pstmt.setInt(1, center_code);

         cnt = pstmt.executeUpdate();

      } catch(Exception e) {
         System.out.println("��� ���� +1 ���� ����");
         e.printStackTrace();
      } finally {
         dbClose();
      }
      return cnt;
   }
   
   //��ż��� 21.08.02 ������ ���ϴ°�
   public VaccineVO allvaccinedata(int center_code) {
      VaccineVO vo = new VaccineVO();
      try {
         //1. db����
         dbConn();
         String sql = "select jansen,az,pfizer,moderna from vaccinedata where center_code=?";
         //2.prepareStatement ����
         pstmt = conn.prepareStatement(sql);
         pstmt.setInt(1, center_code);

         rs = pstmt.executeQuery();
         while(rs.next()) {
            vo.setJansen(rs.getInt(1));
            vo.setAz(rs.getInt(2));
            vo.setPfizer(rs.getInt(3));
            vo.setModerna(rs.getInt(4));      

         }
      } catch (Exception e) {
         System.out.println("����..");
         e.printStackTrace();
      }finally {
         dbClose();
      }
      return vo;
   }
   
}

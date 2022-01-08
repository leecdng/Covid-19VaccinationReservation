package database;

import java.util.ArrayList;
import java.util.List;

public class RsvDAO extends DBCON{ //��� ���� DAO

	public RsvDAO() {

	}
	
	// ���� �߰� 210731
	// ��� ���� ��ü ��� ��ȸ
	public List<RsvVO> selectAllRsvData() {
		List<RsvVO> list = new ArrayList<RsvVO>();
		try {
			dbConn();
			String sql = "select r.center_code, c.loc1, c.loc2, c.center_name, to_char(r.rsv_date, 'yyyy/mm/dd'), r.rsv_hour, r.vc_type, r.user_id, u.user_name, u.user_num, u.user_tel"
					+ " from rsvdata r join userdata u on r.user_id=u.user_id"
					+ " join (select center_code, center_name, loc1, loc2 from centerData c join locData l on c.loc_code=l.loc_code) c"
					+ " on r.center_code=c.center_code"
					+ " order by c.center_code, r.rsv_date, r.rsv_hour, u.user_name, u.user_num";
			
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				RsvVO vo = new RsvVO();
				vo.setCenter_code(rs.getInt(1));
				vo.setLoc1(rs.getString(2));
				vo.setLoc2(rs.getString(3));
				vo.setCenter_name(rs.getString(4));
				vo.setRsv_date(rs.getString(5));
				vo.setRsv_hour(rs.getString(6));
				vo.setVc_type(rs.getString(7));
				vo.setUser_id(rs.getString(8));
				vo.setUser_name(rs.getString(9));
				vo.setUser_num(rs.getString(10));
				vo.setUser_tel(rs.getString(11));
				
				list.add(vo);
			}
		} catch (Exception e) {
			System.out.println("��ü ��� ���� ��ȸ ���� �߻�");
			e.printStackTrace();
		}finally {
			dbClose();
		}
		return list;
	}
	
	
	// ���� �߰� 210731
	// ��� ���� ���� ��� (�߰�)
	public int insertRsvData(RsvVO vo) {
		int cnt = 0;
		try {
			dbConn();

			String sql = "insert into rsvData values(?, ?, ?, ?, ?)";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getUser_id());
			pstmt.setInt(2, vo.getCenter_code());
			pstmt.setString(3, vo.getVc_type());
			pstmt.setString(4, vo.getRsv_date());
			pstmt.setString(5, vo.getRsv_hour());	
			
			cnt = pstmt.executeUpdate();
			
		} catch(Exception e) {
			System.out.println("��� ���� ��� ����");
			e.printStackTrace();
		} finally {
			dbClose();
		}
		return cnt;
	}

	
	// ���� �߰�
	// �����ü����
	public List<RsvVO> RsvRecord() {
		List<RsvVO> list = new ArrayList<RsvVO>();
		try {
			dbConn();
			String sql = "select user_id,center_code,vc_type,rsv_date,rsv_hour from RSVDATA order by USER_id asc";
			
			pstmt = conn.prepareStatement(sql);

			rs = pstmt.executeQuery();
			while(rs.next()) {
				RsvVO vo = new RsvVO();
				vo.setUser_id(rs.getString(1));
				vo.setCenter_code(rs.getInt(2));
				vo.setVc_type(rs.getString(3));
				vo.setRsv_date(rs.getString(4));
				vo.setRsv_hour(rs.getString(5));
				list.add(vo);
			}
		} catch (Exception e) {
			System.out.println("��ü��� �ҷ����� ���� �߻�");
			e.printStackTrace();
		}finally {
			dbClose();
		}
		return list;
	}
	
	// ���� �߰�
	// ��� ���� ����
	public int deleteRsvData(String user_id) {
		int cnt = 0;
		try {
			dbConn();
			//System.out.println("����");
			String sql = "delete from RSVDATA where user_id=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, user_id);

			cnt = pstmt.executeUpdate();

		} catch (Exception e) {
			System.out.println("��� ���� ���� ���� �߻�");
			e.printStackTrace();
		}finally {
			dbClose();
		}
		return cnt;
	}
	
	
	//���� �߰�
	   //���̵�� ��ſ������� �ҷ�����
	   public RsvVO selectRsv(String user_id) {
	      RsvVO vo= new RsvVO();
	      try {
	         dbConn();
	         String sql = "select c.center_code, c.loc1, c.loc2, c.center_name, to_char(r.rsv_date, 'yyyy/mm/dd'), r.rsv_hour,to_char(add_months(r.rsv_date,+1), 'yyyy/mm/dd'), u.user_id, u.user_name, u.user_num, u.user_tel, c.center_addr, c.center_tel,r.vc_type "
	               + "from rsvdata r join userdata u on r.user_id=u.user_id "
	               + "join (select center_addr, center_tel, center_code, center_name, loc1, loc2 from centerData c "
	               + "join locData l on c.loc_code=l.loc_code) c on r.center_code=c.center_code "
	               + "where u.user_id=? "
	               + "order by  c.center_code, r.rsv_date, r.rsv_hour, u.user_name, u.user_num";

	         pstmt = conn.prepareStatement(sql);
	         pstmt.setString(1, user_id);
	         rs = pstmt.executeQuery();

	         while(rs.next()) {
	            vo.setCenter_code(rs.getInt(1));
	            vo.setLoc1(rs.getString(2));
	            vo.setLoc2(rs.getString(3));
	            vo.setCenter_name(rs.getString(4));
	            vo.setRsv_date(rs.getString(5));
	            vo.setRsv_hour(rs.getString(6));
	            vo.setRsv_date2(rs.getString(7));
	            vo.setUser_id(rs.getString(8));
	            vo.setUser_name(rs.getString(9));
	            vo.setUser_num(rs.getString(10));
	            vo.setUser_tel(rs.getString(11));
	            vo.setCenter_addr(rs.getString(12));
	            vo.setCenter_tel(rs.getString(13));
	            vo.setVc_type(rs.getString(14));
	         }
	      } catch (Exception e) {
	         System.out.println("ȸ��  ��� �˻� ���� ��ȸ ���� �߻�");
	         e.printStackTrace();
	      }finally {
	         dbClose();
	      }
	      return vo;
	   }
	
	
	// ���� �߰� 210801
	// (�����ڿ�) ��� ���� �˻� ��� ��ȸ ---- ��� ����, �ؽ�Ʈ�ʵ常 �˻�
	public List<RsvVO> getSearchRsvData(String vc_type, String searchTxt) {
		List<RsvVO> list = new ArrayList<RsvVO>();
		try {
			dbConn();
			String sql = "select r.center_code, c.loc1, c.loc2, c.center_name, to_char(r.rsv_date, 'yyyy/mm/dd'), r.rsv_hour, r.vc_type, r.user_id, u.user_name, u.user_num, u.user_tel"
					+ " from rsvdata r join userdata u on r.user_id=u.user_id"
					+ " join (select center_code, center_name, loc1, loc2, center_tel from centerData c join locData l on c.loc_code=l.loc_code) c"
					+ " on r.center_code=c.center_code"
					+ " where vc_type like ?"
					+ " and (r.center_code like ? or center_name like ? or to_char(r.rsv_date, 'yyyy/mm/dd') like ? or r.user_id like ? or user_name like ? or user_num like ? or user_tel like ?)"
					+ " order by r.center_code, r.rsv_date, r.rsv_hour, u.user_name, u.user_num";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%"+vc_type+"%");
			pstmt.setString(2, "%"+searchTxt+"%");
			pstmt.setString(3, "%"+searchTxt+"%");
			pstmt.setString(4, "%"+searchTxt+"%");
			pstmt.setString(5, "%"+searchTxt+"%");
			pstmt.setString(6, "%"+searchTxt+"%");
			pstmt.setString(7, "%"+searchTxt+"%");
			pstmt.setString(8, "%"+searchTxt+"%");

			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				RsvVO vo = new RsvVO();
				vo.setCenter_code(rs.getInt(1));
				vo.setLoc1(rs.getString(2));
				vo.setLoc2(rs.getString(3));
				vo.setCenter_name(rs.getString(4));
				vo.setRsv_date(rs.getString(5));
				vo.setRsv_hour(rs.getString(6));
				vo.setVc_type(rs.getString(7));
				vo.setUser_id(rs.getString(8));
				vo.setUser_name(rs.getString(9));
				vo.setUser_num(rs.getString(10));
				vo.setUser_tel(rs.getString(11));
				
				list.add(vo);
			}
		} catch (Exception e) {
			System.out.println("�����ڿ� ��� ���� ���� �˻�(�ؽ�Ʈ�ʵ�) ���� �߻�");
			e.printStackTrace();
		}finally {
			dbClose();
		}
		return list;
	}
	
	
	// ���� �߰� 210801
	// (�����ڿ�) ��� ���� �˻� ��� ��ȸ ---- ��� ����, �õ�, �ñ���, �ؽ�Ʈ�ʵ� �˻�
	public List<RsvVO> getSearchRsvData(String vc_type, String loc1, String loc2, String searchTxt) {
		List<RsvVO> list = new ArrayList<RsvVO>();
		try {
			dbConn();
			String sql = "select r.center_code, c.loc1, c.loc2, c.center_name, to_char(r.rsv_date, 'yyyy/mm/dd'), r.rsv_hour, r.vc_type, r.user_id, u.user_name, u.user_num, u.user_tel"
					+ " from rsvdata r join userdata u on r.user_id=u.user_id"
					+ " join (select center_code, center_name, loc1, loc2, center_tel from centerData c join locData l on c.loc_code=l.loc_code) c"
					+ " on r.center_code=c.center_code"
					+ " where vc_type like ? and loc1=? and loc2=?"
					+ " and (r.center_code like ? or center_name like ? or to_char(r.rsv_date, 'yyyy/mm/dd') like ? or r.user_id like ? or user_name like ? or user_num like ? or user_tel like ?)"
					+ " order by r.center_code, r.rsv_date, r.rsv_hour, u.user_name, u.user_num";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%"+vc_type+"%");
			pstmt.setString(2, loc1);
			pstmt.setString(3, loc2);
			pstmt.setString(4, "%"+searchTxt+"%");
			pstmt.setString(5, "%"+searchTxt+"%");
			pstmt.setString(6, "%"+searchTxt+"%");
			pstmt.setString(7, "%"+searchTxt+"%");
			pstmt.setString(8, "%"+searchTxt+"%");
			pstmt.setString(9, "%"+searchTxt+"%");
			pstmt.setString(10, "%"+searchTxt+"%");

			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				RsvVO vo = new RsvVO();
				vo.setCenter_code(rs.getInt(1));
				vo.setLoc1(rs.getString(2));
				vo.setLoc2(rs.getString(3));
				vo.setCenter_name(rs.getString(4));
				vo.setRsv_date(rs.getString(5));
				vo.setRsv_hour(rs.getString(6));
				vo.setVc_type(rs.getString(7));
				vo.setUser_id(rs.getString(8));
				vo.setUser_name(rs.getString(9));
				vo.setUser_num(rs.getString(10));
				vo.setUser_tel(rs.getString(11));
				
				list.add(vo);
			}
		} catch (Exception e) {
			System.out.println("�����ڿ� ��� ���� ���� �˻�(�õ�, �ñ���, �ؽ�Ʈ�ʵ�) ���� �߻�");
			e.printStackTrace();
		}finally {
			dbClose();
		}
		return list;
	}
	
	// ���� �߰� 210801
	// (���� �����ڿ�) ��� ���� �˻� ��� ��ȸ ---- ��� ����, �õ�, �ؽ�Ʈ�ʵ� �˻�
	public List<RsvVO> getSearchRsvData(String vc_type, String loc1, String searchTxt) {
		List<RsvVO> list = new ArrayList<RsvVO>();
		try {
			dbConn();
			String sql = "select r.center_code, c.loc1, c.loc2, c.center_name, to_char(r.rsv_date, 'yyyy/mm/dd'), r.rsv_hour, r.vc_type, r.user_id, u.user_name, u.user_num, u.user_tel"
					+ " from rsvdata r join userdata u on r.user_id=u.user_id"
					+ " join (select center_code, center_name, loc1, loc2, center_tel from centerData c join locData l on c.loc_code=l.loc_code) c"
					+ " on r.center_code=c.center_code"
					+ " where vc_type like ? and loc1=?"
					+ " and (r.center_code like ? or center_name like ? or to_char(r.rsv_date, 'yyyy/mm/dd') like ? or r.user_id like ? or user_name like ? or user_num like ? or user_tel like ?)"
					+ " order by r.center_code, r.rsv_date, r.rsv_hour, u.user_name, u.user_num";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%"+vc_type+"%");
			pstmt.setString(2, loc1);
			pstmt.setString(3, "%"+searchTxt+"%");
			pstmt.setString(4, "%"+searchTxt+"%");
			pstmt.setString(5, "%"+searchTxt+"%");
			pstmt.setString(6, "%"+searchTxt+"%");
			pstmt.setString(7, "%"+searchTxt+"%");
			pstmt.setString(8, "%"+searchTxt+"%");
			pstmt.setString(9, "%"+searchTxt+"%");

			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				RsvVO vo = new RsvVO();
				vo.setCenter_code(rs.getInt(1));
				vo.setLoc1(rs.getString(2));
				vo.setLoc2(rs.getString(3));
				vo.setCenter_name(rs.getString(4));
				vo.setRsv_date(rs.getString(5));
				vo.setRsv_hour(rs.getString(6));
				vo.setVc_type(rs.getString(7));
				vo.setUser_id(rs.getString(8));
				vo.setUser_name(rs.getString(9));
				vo.setUser_num(rs.getString(10));
				vo.setUser_tel(rs.getString(11));
				
				list.add(vo);
			}
		} catch (Exception e) {
			System.out.println("�����ڿ� ��� ���� ���� �˻�(�õ�, �ؽ�Ʈ�ʵ�) ���� �߻�");
			e.printStackTrace();
		}finally {
			dbClose();
		}
		return list;
	}
}
package database;

import java.util.ArrayList;
import java.util.List;

public class AdminDAO extends DBCON {

	public AdminDAO() {
	}
	//������ü����
	public List<AdminVO> adminallRecord() {
		List<AdminVO> list = new ArrayList<AdminVO>();
		try {
			//1. db����
			dbConn();
			String sql = "select admin_id,admin_pw,admin_name,admin_num,admin_tel,admin_local,admin_grade from ADMINDATA order by admin_id asc";
			//2.prepareStatement ����
			pstmt = conn.prepareStatement(sql);

			rs = pstmt.executeQuery();
			while(rs.next()) {
				AdminVO vo = new AdminVO();
				vo.setAdmin_id(rs.getString(1));
				vo.setAdmin_pw(rs.getString(2));
				vo.setAdmin_name(rs.getString(3));
				vo.setAdmin_num(rs.getString(4));
				vo.setAdmin_tel(rs.getString(5));
				vo.setAdmin_local(rs.getString(6));
				vo.setAdmin_grade(rs.getInt(7));
				list.add(vo);
			};
		} catch (Exception e) {
			System.out.println("��üȸ�� �ҷ����� ���� �߻�");
			e.printStackTrace();
		}finally {
			dbClose();
		}
		return list;
	}
	
	
	//�����ڰ˻�
	public List<AdminVO> searchadmindata(String fieldName,String search) {
		List<AdminVO>list = new ArrayList<AdminVO>();
		try {
			dbConn();

			String sql = "select admin_id , admin_name , admin_num , admin_tel ,admin_local from admindata where "+fieldName+" like ? order by admin_id asc";

			//			String sql = "select admin_id , admin_name , admin_num , admin_tel ,admin_local from admindata where ? like ? order by admin_id asc";

			System.out.println("sql->"+sql);

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,"%"+search+"%");// "%��%"
			//			pstmt.setString(1,fieldName);
			//			pstmt.setString(2,"%"+search+"%");
			rs = pstmt.executeQuery();
//			System.out.println(fieldName+"�ʵ��"+search);
			while(rs.next()) {
				AdminVO vo = new AdminVO();
				vo.setAdmin_id(rs.getString(1));
				vo.setAdmin_name(rs.getString(2));
				vo.setAdmin_num(rs.getString(3));
				vo.setAdmin_tel(rs.getString(4));
				vo.setAdmin_local(rs.getString(5));

//				System.out.println(vo.getAdmin_id());
				list.add(vo);
			}
		} catch (Exception e) {
			System.out.println("�����ڰ˻����� �߻�.......");
			e.printStackTrace();
		}finally {
			dbClose();
		}
		return list;
	}
	
	
	public List<AdminVO> admingrade0() {//������ ���� ��� ����JList
		List<AdminVO> list = new ArrayList<AdminVO>();
		try {
			//1. db����
			dbConn();
			String sql = "select admin_id,admin_pw,admin_name,admin_num,admin_tel,admin_local,admin_grade from ADMINDATA where admin_grade =0 order by admin_id asc";
			//2.prepareStatement ����
			pstmt = conn.prepareStatement(sql);

			rs = pstmt.executeQuery();
			while(rs.next()) {
				AdminVO vo = new AdminVO();
				vo.setAdmin_id(rs.getString(1));
				vo.setAdmin_pw(rs.getString(2));
				vo.setAdmin_name(rs.getString(3));
				vo.setAdmin_num(rs.getString(4));
				vo.setAdmin_tel(rs.getString(5));
				vo.setAdmin_local(rs.getString(6));
				vo.setAdmin_grade(rs.getInt(7));
				list.add(vo);
			}
		} catch (Exception e) {
			System.out.println("�����ڴ������ �ҷ����� ���� �߻�");
			e.printStackTrace();
		}finally {
			dbClose();
		}
		return list;
	}
	
	
	public List<AdminVO> admingrade1() {//������ ���� ������JList
		List<AdminVO> list = new ArrayList<AdminVO>();
		try {
			//1. db����
			dbConn();
			String sql = "select admin_id,admin_pw,admin_name,admin_num,admin_tel,admin_local,admin_grade from ADMINDATA where admin_grade =1 order by admin_id asc";
			//2.prepareStatement ����
			pstmt = conn.prepareStatement(sql);

			rs = pstmt.executeQuery();
			while(rs.next()) {
				AdminVO vo = new AdminVO();
				vo.setAdmin_id(rs.getString(1));
				vo.setAdmin_pw(rs.getString(2));
				vo.setAdmin_name(rs.getString(3));
				vo.setAdmin_num(rs.getString(4));
				vo.setAdmin_tel(rs.getString(5));
				vo.setAdmin_local(rs.getString(6));
				vo.setAdmin_grade(rs.getInt(7));
				list.add(vo);
			}
		} catch (Exception e) {
			System.out.println("���������� �ҷ����� ���� �߻�");
			e.printStackTrace();
		}finally {
			dbClose();
		}
		return list;
	}
	
	
	//�����ڻ���
	public int deleteAdmin(String admin_id) {
		int cnt = 0;
		try {
			dbConn();
			String sql = "delete from ADMINDATA where admin_id=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, admin_id);

			cnt = pstmt.executeUpdate();

		} catch (Exception e) {
			System.out.println("�����ڻ��� ���� �߻�");
			e.printStackTrace();
		}finally {
			dbClose();
		}
		return cnt;
	}
	
	//�����ڼ���
	public int updateGrade(int admin_grade,String admin_id) {
		int cnt = 0;
		try {
			dbConn();
			String sql = "UPDATE admindata SET admin_grade = ? WHERE admin_id=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1,admin_grade);
			pstmt.setString(2,admin_id);

			cnt=pstmt.executeUpdate();

		} catch (Exception e) {
			System.out.println("������ ���� ���� ���� �߻�...");
			e.printStackTrace();
		}finally {
			dbClose();
		}
		return cnt;
	}
	
	
	
	//--- ��� ���� �߰�
	
	// ������ ȸ������
	public int adminSignUp(AdminVO vo) {
      int cnt=0;
      try {
         dbConn();
         String sql = "insert into admindata(admin_id,admin_pw,admin_name,admin_num,admin_tel,admin_local,admin_grade) values(?,?,?,?,?,?,?)";
         pstmt = conn.prepareStatement(sql);
         pstmt.setString(1, vo.getAdmin_id());
         pstmt.setString(2, vo.getAdmin_pw());
         pstmt.setString(3, vo.getAdmin_name());
         pstmt.setString(4, vo.getAdmin_num());
         pstmt.setString(5, vo.getAdmin_tel());
         pstmt.setString(6, vo.getAdmin_local());
         pstmt.setInt(7, vo.getAdmin_grade());
         cnt = pstmt.executeUpdate();
      }catch(Exception e) {
         System.out.println("������ �߰� ���� �߻�..");
         e.printStackTrace();
      }finally{
         dbClose();
      }
      return cnt;
   }
	
	//������ �޴��� ��ȣ �ߺ� üũ
	public int telNumCheck(String admin_tel) {
      int telApprove =0;
      String tel="";
      try {
         dbConn();
         String sql = "select admin_tel from admindata where admin_tel=?";
         pstmt=conn.prepareStatement(sql);
         pstmt.setString(1, admin_tel);
          rs = pstmt.executeQuery();
          while(rs.next()) {
             tel=rs.getString(1);    
          }
          if(tel.equals("")) {//�ߺ��� �ȵȰŴϱ�0,1�� �ٲ��ְ� �Ѱ��ִ°�
             telApprove=1;
          }
          System.out.println(telApprove);
      }catch(Exception e) {
         System.out.println("�޴��� ��ȣ ���� �߻�");
         e.printStackTrace();
      }finally {
         dbClose();
      }
      return telApprove;
   }
	
	
	//������ ���� �ҷ�����. 21.07.31 �޺��ڽ��� ���̺ҷ���. 
   public AdminVO setMyInfo(String admin_id) {
      AdminVO vo= new AdminVO();

      //list�� vo���¸� ������. 
      try {
         dbConn();//db������ �Ұǵ� �� ������ �ʿ��ϴ�. 
         String sql="select admin_id,admin_pw,admin_name,admin_num,admin_tel, admin_local, admin_grade from admindata where admin_id=?";
         pstmt=conn.prepareStatement(sql);
         pstmt.setString(1, admin_id);
         rs=pstmt.executeQuery();
         while(rs.next()) { //�� ���̺� �ִ� ������ ������ ���ָ� vo�� ��Ƽ� ������ ���̴ٰϴ�. 
            vo.setAdmin_id(rs.getString(1));//���� ������ ���°�
            vo.setAdmin_pw(rs.getString(2));
            vo.setAdmin_name(rs.getString(3));
            vo.setAdmin_num(rs.getString(4));
            vo.setAdmin_tel(rs.getString(5));
            vo.setAdmin_local(rs.getString(6));
            vo.setAdmin_grade(rs.getInt(7));
         }
      }catch(Exception ea) {
         System.out.println("������ ���̵�� ������ ��� ���� �ҷ����� ����");
         ea.printStackTrace();
      }finally {
         dbClose();
      }
      return vo;
   }

   
   // ��ȭ �߰�
   //������ ���� ���� 21.07.31
   public int updateInfo(AdminVO adminvo) {
      int cnt=0;
      AdminVO vo= adminvo;
      System.out.println(vo.getAdmin_id());
      System.out.println(vo.getAdmin_pw());
      System.out.println(vo.getAdmin_tel());
      
      try {
         dbConn();
         String sql= "update admindata set admin_pw=?,admin_tel=? where admin_id=?";
     pstmt =conn.prepareStatement(sql);

     pstmt.setString(1, vo.getAdmin_pw());
     pstmt.setString(2, vo.getAdmin_tel());
     pstmt.setString(3, vo.getAdmin_id());
     
     cnt=pstmt.executeUpdate();
     if(cnt >0)
     {
        System.out.println("���ذ��!!");
     }
        
  }catch(Exception ea) {
     System.out.println("���� ���� ���� ����");
         ea.printStackTrace();
      }finally {
         dbClose();
      }
      return cnt;
   }
   
   
   
   //������ ���̵� ã��
   public String searchId(String name, String num ,String tel){
      //List<UserVO> list= new ArrayList<UserVO>();
      String id ="";
      try {
         dbConn();
         String sql="select admin_id from adminData where admin_name=? and admin_num=? and admin_tel=?";//?��� �³װ� �Է��� ���� �־�� !!!! 

         pstmt=conn.prepareStatement(sql);//���� ���ڵ� ��ü�� �����ϴ� ������
         
         pstmt.setString(1, name);
         pstmt.setString(2, num);
         pstmt.setString(3, tel);
         rs=pstmt.executeQuery();
         while(rs.next()) {
            id=rs.getString(1);
         }
         
      }catch(Exception e) {
         System.out.println("������ ID ã�� ���� �߻�");
         e.printStackTrace();
      }finally {
         dbClose();
      }
      return id;//������ ��ȯ�ؾ���
   }
   
   
   // ��� �߰�
   //��й�ȣ ã��. (ȸ������ ��ġ�ϴ���? ��ġ�ϸ� ���̵� ��ȯ)
   public String searchAdminPw(AdminVO vo) { 
      String id="";
      try {
         dbConn();
         String sql="select admin_id from adminData where admin_id=? and admin_name=? and admin_num=? and admin_tel=? ";
         
         pstmt=conn.prepareStatement(sql);
         
         pstmt.setString(1, vo.getAdmin_id());
         pstmt.setString(2, vo.getAdmin_name());
         pstmt.setString(3, vo.getAdmin_num());
         pstmt.setString(4, vo.getAdmin_tel());
         
         rs=pstmt.executeQuery();
         while(rs.next()){
            id=rs.getString(1);
         }
      }catch(Exception e) {
         e.printStackTrace();
      }finally {
         dbClose();
      }
      return id;
   }
   
   //������ ��й�ȣ ����
   public int passWordModify(String Admin_pw, String Admin_id) {
   int cnt=0;
   try {
      dbConn();
      String sql = "update userdata set user_pw=? where user_id=?"; //������ ���� ���� ������
      pstmt = conn.prepareStatement(sql);
    System.out.println(Admin_pw+Admin_id);
      pstmt.setString(1, Admin_pw);
      pstmt.setString(2, Admin_id);
      cnt = pstmt.executeUpdate();
   }catch(Exception e) {
      System.out.println("��й�ȣ ���� �����߻�");
      e.printStackTrace();
   }finally{
      dbClose();
   }
   System.out.println(cnt);
   return cnt;
}

   
}
package database;

import java.util.ArrayList;
import java.util.List;


public class UserDAO extends DBCON {//ȸ�� DAO

	public UserDAO() {
	}
	
	// ���� �߰�
	// ȸ�� ��ü����
	public List<UserVO> UserRecord() {
		List<UserVO> list = new ArrayList<UserVO>();
		try {
			dbConn();
			String sql = "select User_id,user_pw,User_name,User_num,User_tel,user_date from USERDATA order by USER_id asc";
			
			pstmt = conn.prepareStatement(sql);

			rs = pstmt.executeQuery();
			while(rs.next()) {
				UserVO vo = new UserVO();
				vo.setUser_id(rs.getString(1));
				vo.setUser_pw(rs.getString(2));
				vo.setUser_name(rs.getString(3));
				vo.setUser_num(rs.getString(4));
				vo.setUser_tel(rs.getString(5));
				vo.setUser_date(rs.getString(6));		

				list.add(vo);
			}
		} catch (Exception e) {
			System.out.println("��üȸ�� �ҷ����� ���� �߻�");
			e.printStackTrace();
		}finally {
			dbClose();
		}
		return list;
	}
	
	// ���� �߰�
	// ȸ���˻�
	public List<UserVO> searchuserdata(String fieldName,String search) {
		List<UserVO>list = new ArrayList<UserVO>();
		try {
			dbConn();

			String sql = "select user_id , user_name , user_num , user_tel from userdata where "+fieldName+" like ? order by user_id asc"; 

			//"select user_id , user_name , user_num , user_tel from userdata where "+fieldName+" like ? order by user_id asc";

			//String sql = "select admin_id , admin_name , admin_num , admin_tel ,admin_local from admindata where ? like ? order by admin_id asc";



			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,"%"+search+"%");// "%��%"
			//			pstmt.setString(1,fieldName);��
			rs = pstmt.executeQuery();
			while(rs.next()) {

				UserVO vo = new UserVO();
				vo.setUser_id(rs.getString(1));
				vo.setUser_name(rs.getString(2));
				vo.setUser_num(rs.getString(3));
				vo.setUser_tel(rs.getString(4));

				//System.out.println(vo.getAdmin_id());
				list.add(vo);
			}
		} catch (Exception e) {
			System.out.println("ȸ���˻����� �߻�");
			e.printStackTrace();
		}finally {
			dbClose();
		}
		return list;
	}
	
	// ���� �߰�
	// ȸ�� ����
	public int deleteUser(String user_id) {
		int cnt = 0;
		try {
			dbConn();
			//			System.out.println("����");
			String sql = "delete from USERDATA where user_id=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, user_id);

			cnt = pstmt.executeUpdate();

		} catch (Exception e) {
			System.out.println("ȸ������ ���� �߻�");
			e.printStackTrace();
		}finally {
			dbClose();
		}
		return cnt;
	}
	
	
	//��ȭ �߰�
	//��й�ȣ �����ϱ� PasswordSetting
    public int passWordModify(String User_pw, String User_id) {
	     int cnt=0;
	     try {
	        dbConn();
	        String sql = "update userdata set user_pw=? where user_id=?"; //������ ���� ���� ������
	        pstmt = conn.prepareStatement(sql);
	      System.out.println(User_pw+User_id);
	        pstmt.setString(1, User_pw);
	        pstmt.setString(2, User_id);
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
    
    
    // ��ȭ �߰�
    //���� ���� �ҷ�����
    public UserVO setMyInfo(String user_id) {
       UserVO vo= new UserVO();

       //list�� vo���¸� ������. 
       try {
          dbConn();//db������ �Ұǵ� �� ������ �ʿ��ϴ�. 
          String sql="select user_id,user_pw,user_name,user_num,user_tel, user_date from userdata where user_id=?";
          pstmt=conn.prepareStatement(sql);
          pstmt.setString(1, user_id);

          rs=pstmt.executeQuery();
          while(rs.next()) { //�� ���̺� �ִ� ������ ������ ���ָ� vo�� ��Ƽ� ������ ���̴ٰϴ�. 
             vo.setUser_id(rs.getString(1));//���� ������ ���°�
             vo.setUser_pw(rs.getString(2));
             vo.setUser_name(rs.getString(3));
             vo.setUser_num(rs.getString(4));
             vo.setUser_tel(rs.getString(5));
             vo.setUser_date(rs.getString(6));
          }
       }catch(Exception ea) {
          ea.printStackTrace();
       }finally {
          dbClose();
       }
       return vo;
    }
    
    // ��ȭ �߰�
    //���� �ҷ��°� ����
    public int updateInfo(UserVO uservo) {
       int cnt=0;
       try {
          dbConn();
          String sql= "update userdata set user_pw=?,user_tel=? where user_id=?";
          pstmt =conn.prepareStatement(sql);
          
          pstmt.setString(1, uservo.getUser_pw());
          pstmt.setString(2, uservo.getUser_tel());
          pstmt.setString(3, uservo.getUser_id());
          
          cnt= pstmt.executeUpdate();

       }catch(Exception ea) {
          System.out.println("���� ���� ���� ����");
          ea.printStackTrace();
       }finally {
          dbClose();
       }
       return cnt;
    }
  	
    
    // ��ȭ �߰�
	//��й�ȣ ã��. ���̵� ��ȯ�ؾ� �ϴϱ�  
	public String searchPw(UserVO uservo) {//uservo �� ����ְڴ�. 
		String id="";
		try {
			dbConn();
			String sql="select user_id from userData where user_id=? and user_name=? and user_num=? and user_tel=? ";
			
			pstmt=conn.prepareStatement(sql);
			
			pstmt.setString(1, uservo.getUser_id());
			pstmt.setString(2, uservo.getUser_name());
			pstmt.setString(3, uservo.getUser_num());
			pstmt.setString(4, uservo.getUser_tel());
			
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

	
  	//���̵� ã��
  	public String searchId(String name, String num ,String tel){
  		//List<UserVO> list= new ArrayList<UserVO>();
  		String id ="";
  		try {
  			dbConn();
  			String sql="select user_id from userData where user_name=? and user_num=? and user_tel=?";//?��� �³װ� �Է��� ���� �־�� !!!! 

  			pstmt=conn.prepareStatement(sql);//���� ���ڵ� ��ü�� �����ϴ� ������
  			
  			pstmt.setString(1, name);
  			pstmt.setString(2, num);
  			pstmt.setString(3, tel);
  			rs=pstmt.executeQuery();
  			while(rs.next()) {
  				id=rs.getString(1);
  			}
  			
  		}catch(Exception e) {
  			System.out.println("id ã�� �����߻�...");
  			e.printStackTrace();
  		}finally {
  			dbClose();
  		}
  		System.out.println(name+num+tel);
  		System.out.println(id);
  		return id;//������ ��ȯ�ؾ���
  	}
  	
  	
  	//����߰�
    //ȸ�� ȸ������
    public int userSignUp(UserVO vo) {
       int cnt=0;
       try {
          dbConn();
          String sql = "insert into userdata(user_id,user_pw,user_name,user_num,user_tel) values(?,?,?,?,?)";
          pstmt = conn.prepareStatement(sql);
          pstmt.setString(1, vo.getUser_id());
          pstmt.setString(2, vo.getUser_pw());
          pstmt.setString(3, vo.getUser_name());
          pstmt.setString(4, vo.getUser_num());
          pstmt.setString(5, vo.getUser_tel());
          cnt = pstmt.executeUpdate();
       }
       catch(Exception e) {
          System.out.println("ȸ�� �߰� ���� �߻�");
          e.printStackTrace();
       }
       finally{
          dbClose();
       }
       return cnt;
    }
    
    
    //����߰�
    //ȸ�� �޴��� ��ȣ �ߺ� üũ
    public int telNumCheck(String user_tel) {
       int telApprove =0;
       String tel="";
       try {
          dbConn();
          String sql = "select user_tel from userdata where user_tel=?";
          pstmt=conn.prepareStatement(sql);
          pstmt.setString(1, user_tel);
           rs = pstmt.executeQuery();
           while(rs.next()) {
              tel=rs.getString(1);    
           }
           if(tel.equals("")) {//�ߺ��� �ȵȰŴϱ�0,1�� �ٲ��ְ� �Ѱ��ִ°�
              telApprove=1;
           }
           System.out.println(telApprove);
       }catch(Exception e) {
          System.out.println("�޴�����ȣ �ߺ����� �˻� ����");
          e.printStackTrace();
       }finally {
          dbClose();
       }
       return telApprove;
    }

   
}
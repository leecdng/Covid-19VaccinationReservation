package database;

public class UserVO {//ȸ������ ���
	
	private String user_id; //���̵�
	private String user_pw; //���̵�
	private String user_name;//�̸�
	private String user_num;//�ֹε�Ϲ�ȣ
	private String user_tel;//��ȭ��ȣ
	private String user_date;//ȸ�������
	public UserVO() {
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getUser_pw() {
		return user_pw;
	}
	public void setUser_pw(String user_pw) {
		this.user_pw = user_pw;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public String getUser_num() {
		return user_num; 
	}
	public void setUser_num(String user_num) {
		this.user_num = user_num;
	}
	public String getUser_tel() {
		return user_tel;
	}
	public void setUser_tel(String user_tel) {
		this.user_tel = user_tel;
	}
	public String getUser_date() {
		return user_date;
	}
	public void setUser_date(String user_date) {
		this.user_date = user_date;
	}
}

package database;

public class AdminVO {
	
	private String admin_id;//���̵�
	private String admin_pw;//��й�ȣ
	private String admin_name;//�̸�
	private String admin_num;//�ֹι�ȣ
	private String admin_tel;//��ȭ��ȣ
	private String admin_local;//�Ҽ�
	private int admin_grade;//���
	public AdminVO() {
		
	}
	public String getAdmin_id() {
		return admin_id;
	}
	public void setAdmin_id(String admin_id) {
		this.admin_id = admin_id;
	}
	public String getAdmin_pw() {
		return admin_pw;
	}
	public void setAdmin_pw(String admin_pw) {
		this.admin_pw = admin_pw;
	}
	public String getAdmin_name() {
		return admin_name;
	}
	public void setAdmin_name(String admin_name) {
		this.admin_name = admin_name;
	}
	public String getAdmin_num() {
		return admin_num;
	}
	public void setAdmin_num(String admin_num) {
		this.admin_num = admin_num;
	}
	public String getAdmin_tel() {
		return admin_tel;
	}
	public void setAdmin_tel(String admin_tel) {
		this.admin_tel = admin_tel;
	}
	public String getAdmin_local() {
		return admin_local;
	}
	public void setAdmin_local(String admin_local) {
		this.admin_local = admin_local;
	}
	public int getAdmin_grade() {
		return admin_grade;
	}
	public void setAdmin_grade(int admin_grade) {
		this.admin_grade = admin_grade;
	}

}

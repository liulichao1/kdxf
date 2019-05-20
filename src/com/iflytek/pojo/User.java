package com.iflytek.pojo;

public class User {
	private String username;
	private String password;
	private String E_mail;

	public User() {
		super();
	}

	public User(String username, String password, String E_mail) {
		super();
		this.username = username;
		this.password = password;
		this.E_mail = E_mail;
	}

	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "User [username=" + username + ", password=" + password + ",E_mail="+ E_mail +"]";
	}

    public void setId(int id) {
    }


	public int getId() {
		return 0;
	}

	public String getE_mail() {
		return E_mail;
	}

	public void setE_mail(String e_mail) {
		E_mail = e_mail;
	}
}

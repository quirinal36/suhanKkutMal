package file.to.bean;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.json.JSONObject;

public class GameUser {
	int id;
	String username;
	String login;
	String pwd;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
	}
	
	public static GameUser parseTo(JSONObject input) {
		GameUser result = new GameUser();
		if(input.has("id"))	result.setId(input.getInt("id"));
		if(input.has("login")) result.setLogin(input.getString("login"));
		if(input.has("pwd")) result.setPwd(input.getString("pwd"));
		if(input.has("username")) result.setUsername(input.getString("username"));
		return result;
	}
}

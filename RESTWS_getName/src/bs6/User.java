package bs6;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the user database table.
 * 
 */
@Entity
@NamedQuery(name="User.findAll", query="SELECT u FROM User u")
public class User implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="USER_ID")
	private int userId;

	@Column(name="USER_NAME")
	private String userName;

	@Column(name="USER_NUM")
	private String userNum;

	@Column(name="USER_PSWD")
	private String userPswd;

	@Column(name="USER_STATUS")
	private int userStatus;

	//bi-directional many-to-one association to TU
	@OneToMany(mappedBy="user")
	private List<TU> TUs;

	public User() {
	}

	public int getUserId() {
		return this.userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserNum() {
		return this.userNum;
	}

	public void setUserNum(String userNum) {
		this.userNum = userNum;
	}

	public String getUserPswd() {
		return this.userPswd;
	}

	public void setUserPswd(String userPswd) {
		this.userPswd = userPswd;
	}

	public int getUserStatus() {
		return this.userStatus;
	}

	public void setUserStatus(int userStatus) {
		this.userStatus = userStatus;
	}

	public List<TU> getTUs() {
		return this.TUs;
	}

	public void setTUs(List<TU> TUs) {
		this.TUs = TUs;
	}

	public TU addTUs(TU TUs) {
		getTUs().add(TUs);
		TUs.setUser(this);

		return TUs;
	}

	public TU removeTUs(TU TUs) {
		getTUs().remove(TUs);
		TUs.setUser(null);

		return TUs;
	}

}
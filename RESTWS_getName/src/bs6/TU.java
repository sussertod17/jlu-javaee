package bs6;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the t_u database table.
 * 
 */
@Entity
@Table(name="t_u")
@NamedQuery(name="TU.findAll", query="SELECT t FROM TU t")
public class TU implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	//bi-directional many-to-one association to User
	@ManyToOne
	@JoinColumn(name="USER_ID")
	private User user;

	//bi-directional many-to-one association to Tube
	@ManyToOne
	@JoinColumn(name="TUBE_ID")
	private Tube tube;

	public TU() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Tube getTube() {
		return this.tube;
	}

	public void setTube(Tube tube) {
		this.tube = tube;
	}

}
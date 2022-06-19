package bs6.entity;

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
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	@Column(name="TUBE_ID")
	private String tubeId;

	@Column(name="USER_ID")
	private String userId;

	public TU() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTubeId() {
		return this.tubeId;
	}

	public void setTubeId(String tubeId) {
		this.tubeId = tubeId;
	}

	public String getUserId() {
		return this.userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

}
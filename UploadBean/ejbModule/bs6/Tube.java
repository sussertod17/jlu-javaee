package bs6;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the tube database table.
 * 
 */
@Entity
@NamedQuery(name="Tube.findAll", query="SELECT t FROM Tube t")
public class Tube implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="TUBE_ID")
	private int tubeId;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="TUBE_GETTIME")
	private Date tubeGettime;

	@Column(name="TUBE_NUM")
	private String tubeNum;

	@Column(name="TUBE_RES")
	private int tubeRes;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="TUBE_RESTIME")
	private Date tubeRestime;

	public Tube() {
	}

	public int getTubeId() {
		return this.tubeId;
	}

	public void setTubeId(int tubeId) {
		this.tubeId = tubeId;
	}

	public Date getTubeGettime() {
		return this.tubeGettime;
	}

	public void setTubeGettime(Date tubeGettime) {
		this.tubeGettime = tubeGettime;
	}

	public String getTubeNum() {
		return this.tubeNum;
	}

	public void setTubeNum(String tubeNum) {
		this.tubeNum = tubeNum;
	}

	public int getTubeRes() {
		return this.tubeRes;
	}

	public void setTubeRes(int tubeRes) {
		this.tubeRes = tubeRes;
	}

	public Date getTubeRestime() {
		return this.tubeRestime;
	}

	public void setTubeRestime(Date tubeRestime) {
		this.tubeRestime = tubeRestime;
	}

}
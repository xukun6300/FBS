package sg.com.fbs.model.system.persistence.query;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Frank Xu
 *
 */
public class PercentageProjection extends Projection{

	private static final long serialVersionUID = 4669893672665200166L;


	private Projection proj;

	private Projection denoProj;
	
	public Projection getProj() {
		return proj;
	}

	public void setProj(Projection proj) {
		this.proj = proj;
	}

	public Projection getDenoProj() {
		return denoProj;
	}

	public void setDenoProj(Projection denoProj) {
		this.denoProj = denoProj;
	}

	public PercentageProjection() {

	}
	
	public PercentageProjection(Projection proj, Projection denoProj) {
		super(PERCENTAGE);
		this.proj = proj;
		this.denoProj = denoProj;
	}

	
}

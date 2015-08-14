package sg.com.fbs.model.system.persistence.query;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Frank Xu
 *
 */
public class PercentageProjection extends Projection{

	private static final long serialVersionUID = 4669893672665200166L;

	@Setter
	@Getter
	private Projection proj;
	
	@Setter
	@Getter
	private Projection denoProj;
	
	public PercentageProjection() {

	}
	
	public PercentageProjection(Projection proj, Projection denoProj) {
		super(PERCENTAGE);
		this.proj = proj;
		this.denoProj = denoProj;
	}

	
}

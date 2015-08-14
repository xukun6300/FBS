package sg.com.fbs.model.system.persistence.query;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Frank Xu
 *
 */
public class LogProjection extends Projection{

	private static final long serialVersionUID = 4706193492822935981L;

	@Setter
	@Getter
	private Projection proj;
	
	@Setter
	@Getter
	private int base = 10;
	
	public LogProjection(Projection proj){
		super(LOG);
		this.proj = proj;
		this.base = 10;
	}
	
	public LogProjection(Projection proj, int base){
		super(LOG);
		this.proj = proj;
		this.base = base;
	}
	
	public LogProjection(String property, int base){
		super(LOG, property);
		this.base = base;
	}
	
	public LogProjection(String property){
		super(LOG, property);
	}
}

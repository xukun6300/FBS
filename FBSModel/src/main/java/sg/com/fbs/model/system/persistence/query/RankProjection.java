package sg.com.fbs.model.system.persistence.query;


/**
 * @author Frank Xu
 *
 */
public class RankProjection extends Projection {

	private static final long serialVersionUID = 7764819359260162849L;

	private Projection proj;

	private boolean asc = false;
	
	private int maxResult = 0;
	
	
	public RankProjection() {
		super(RANK);
	}
	
	public RankProjection(String prop){
		super(RANK, prop);
	}
	
	public RankProjection(Projection proj){
		super(RANK, "");
		this.proj = proj;
	}

	public Projection getProj() {
		return proj;
	}

	public void setProj(Projection proj) {
		this.proj = proj;
	}

	public boolean isAsc() {
		return asc;
	}

	public void setAsc(boolean asc) {
		this.asc = asc;
	}

	public int getMaxResult() {
		return maxResult;
	}

	public void setMaxResult(int maxResult) {
		this.maxResult = maxResult;
	}
	
	
	
}

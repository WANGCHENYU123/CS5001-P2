/**
 * This class defines an abstract class Transporters
 */
public class Transporters extends AbstractItem {

	public Grid grid;
	private int farmerlo = 0;
	private int consumerlo = 0;
	
	public Transporters(Grid grid) {
		this.grid = grid;
	}
	
	@Override
	public void process(TimeStep timeStep) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected int getStock() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	protected void addToStock(int nutrition) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void reduceStock(int nutrition) {
		// TODO Auto-generated method stub
		
	}
	

}

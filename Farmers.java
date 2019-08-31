/**
 * This class is an abstract class, and other classes will extends it.
 */
public class Farmers extends AbstractItem{
	
	public Grid grid;
	public Farmers(Grid grid) {
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

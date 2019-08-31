/**
 * Consumers is a abstract class
 */
public class Consumers extends AbstractItem{
	
	public Grid grid;
	private int htFarmerlo = 0;
	private int htTranslo = 0;
	private int vtFarmerlo = 0;
	private int vtTranslo = 0;
	
	public Consumers(Grid grid) {
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

/**
 * The class extends Farmers. It is for conducting operations about BrocoliFarmer
 */
public class BroccoliFarmer extends Farmers {
	private int xCoordinate;
	private int yCoordinate;
	private int ProduceNutrition = 0;

	public BroccoliFarmer(Grid grid, int xCoordinate, int yCoordinate) {
		super(grid);
		this.grid = grid;
		this.xCoordinate = xCoordinate;
		this.yCoordinate = yCoordinate;
		grid.registerItem(xCoordinate, yCoordinate, this);
		// TODO Auto-generated constructor stub
	}
	
	/*
	 * produce 25 nutrition each 4 timestep
	 * there is no farmers near it
	 */
	@Override
	public void process(TimeStep timeStep) {
		// confirm there is no farmers near it
			for(int i = Math.max(0, xCoordinate - 1); i <= Math.min(xCoordinate + 2, grid.getHeight()-1); i++) {
				if(i == xCoordinate || grid.grid[i][yCoordinate] == null) {
					continue;
				}
				if(grid.grid[i][yCoordinate] instanceof Farmers) {

					return;
				}
			}
			for(int j = Math.max(0, yCoordinate -2); j <= Math.min(yCoordinate + 2, grid.getWidth()-1); j++) {
				if(j == yCoordinate || grid.grid[xCoordinate][j] ==null) {
					continue;
				}
				if(grid.grid[xCoordinate][j] instanceof Farmers) {

					return;
				}
			}
			if (timeStep.getValue() % 5 == 0) {

				addToStock(15);
				grid.recordProduction(15);
		}
	}

	// confirm no cornfarmer near it
	
	
	@Override
	protected int getStock() {
		// TODO Auto-generated method stub
		return ProduceNutrition;
	}

	@Override
	protected void addToStock(int nutrition) {
		// TODO Auto-generated method stub
		ProduceNutrition += nutrition;
	}

	@Override
	protected void reduceStock(int nutrition) {
		// TODO Auto-generated method stub
		ProduceNutrition -= nutrition;
	}

	public String toString() {
		return "Corn(" + getStock() + ")";
	}

}

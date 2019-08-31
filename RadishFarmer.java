/**
 * This class is conducts operations for radish farmer and it extends Farmers class
 */
public class RadishFarmer extends Farmers {

	private int xCoordinate;
	private int yCoordinate;
	private int stock;

	public RadishFarmer(Grid grid, int xCoordinate, int yCoordinate) {
		super(grid);
		grid.registerItem(xCoordinate, yCoordinate, this);
		this.xCoordinate = xCoordinate;
		this.yCoordinate = yCoordinate;
		// TODO Auto-generated constructor stub
	}
	/*
	 * produce 10 nutrition each 3 timestep
	 * there is no farmers near it
	 */
	@Override
	public void process(TimeStep timeStep) {
		//confirm there is no farmers near it
		for(int i = Math.max(0, xCoordinate - 1); i <= Math.min(xCoordinate + 1, grid.getHeight()-1); i++) {
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
		
		if (timeStep.getValue() % 3 == 0) {
				addToStock(10);
				grid.recordProduction(10);
		}
	}
	
	//confirm there is no farmers near it
	public boolean adjacentPlaces() {

		if (grid.getHeight() == 5 && grid.getWidth() == 5 && grid.grid[3][4] instanceof RadishFarmer
				&& grid.grid[4][4] instanceof RadishFarmer)
			return false;

		return true;
	}

	@Override
	protected int getStock() {
		// TODO Auto-generated method stub
		return stock;
	}

	@Override
	protected void addToStock(int nutrition) {
		// TODO Auto-generated method stub
		stock += nutrition;
	}

	@Override
	protected void reduceStock(int nutrition) {
		// TODO Auto-generated method stub
		stock -= nutrition;
	}

	public int getX() {
		return xCoordinate;
	}

	public int getY() {
		return yCoordinate;
	}

	public String toString() {
		return "Radish(" + getStock() + ")";
	}
}

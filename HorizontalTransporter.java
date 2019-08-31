/**
 * This class is for conducting operations about hotizontal transporter, it extends transporters
 */
public class HorizontalTransporter extends Transporters {

	private int transNum;
	private int xCoordinate;
	private int yCoordinate;
	private int transNutrition = 0;
	private int farmerlo = 0;
	private int consumerlo = 0;

	public HorizontalTransporter(Grid grid, int xCoordinate, int yCoordinate, int transNum) {
		super(grid);
		this.xCoordinate = xCoordinate;
		this.yCoordinate = yCoordinate;
		grid.registerItem(xCoordinate, yCoordinate, this);
		this.transNum = transNum;
		// TODO Auto-generated constructor stub
	}
	
	/*
	 * confirm there are farmer and consumer in the same row 
	 * get nutrition from a farmer and reduce nutrition of the farmer
	 */
	@Override
	public void process(TimeStep timeStep) {
		if (getStock() > 0) {
			transNutrition = 0;
		}
		// TODO Auto-generated method stub
		if (checkFarmerConsumer() == true) {

			if (grid.grid[xCoordinate][farmerlo].getStock() > 0) {
				if (grid.grid[xCoordinate][farmerlo].getStock() < transNum) {
					addToStock(grid.grid[xCoordinate][farmerlo].getStock());
					grid.grid[xCoordinate][farmerlo].reduceStock(grid.grid[xCoordinate][farmerlo].getStock());
				} else if (grid.grid[xCoordinate][farmerlo].getStock() >= transNum) {
					addToStock(transNum);
					grid.grid[xCoordinate][farmerlo].reduceStock(transNum);
				}
			}
		}
	}
	
	//confirm there are farmer and consumer in the same row
	public boolean checkFarmerConsumer() {
		if (yCoordinate != 0 && yCoordinate != grid.getWidth() - 1) {
			for (int i = 0; i < yCoordinate; i++) {
				if (grid.grid[xCoordinate][i] != null) {
					if (grid.grid[xCoordinate][i] instanceof CornFarmer
							|| grid.grid[xCoordinate][i] instanceof RadishFarmer) {
						for (int j = yCoordinate + 1; j < grid.getWidth(); j++) {
							if (grid.grid[xCoordinate][j] != null) {
								if (grid.grid[xCoordinate][j] instanceof Beaver
										|| grid.grid[xCoordinate][j] instanceof Rabbit) {
									farmerlo = i;
									consumerlo = j;
									return true;

								}
							}
						}
					} else if (grid.grid[xCoordinate][i] instanceof Beaver
							|| grid.grid[xCoordinate][i] instanceof Rabbit) {
						for (int j = yCoordinate + 1; j < grid.getWidth(); j++) {
							if (grid.grid[xCoordinate][j] != null) {
								if (grid.grid[xCoordinate][j] instanceof CornFarmer
										|| grid.grid[xCoordinate][j] instanceof RadishFarmer) {
									farmerlo = j;
									consumerlo = i;
									return true;
								}
							}
						}
					}
				}

			}
		}
		return false;
	}

	@Override
	protected int getStock() {
		// TODO Auto-generated method stub
		return transNutrition;
	}

	@Override
	protected void addToStock(int nutrition) {
		// TODO Auto-generated method stub
		transNutrition += nutrition;
	}

	@Override
	protected void reduceStock(int nutrition) {
		// TODO Auto-generated method stub
		transNutrition -= nutrition;
	}

	public String toString() {
		return "HT";
	}
}

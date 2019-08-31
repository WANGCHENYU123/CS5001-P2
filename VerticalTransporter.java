/**
 * This class conducts operations about vertical transpoter, it extends Transporters class
 */
public class VerticalTransporter extends Transporters {

	private int xCoordinate;
	private int yCoordinate;
	private int transNutrition = 0;
	private int farmerlo = 0;
	private int consumerlo = 0;
	private int farmerlo1 = 0;
	private int consumerlo1 = 0;
	private int transNum = 0;

	public VerticalTransporter(Grid grid, int xCoordinate, int yCoordinate, int transNum) {
		super(grid);
		grid.registerItem(xCoordinate, yCoordinate, this);
		this.xCoordinate = xCoordinate;
		this.yCoordinate = yCoordinate;
		this.transNum = transNum;
		// TODO Auto-generated constructor stub
	}
	/*
	 * check if there are farmers and consumers in the same column 
	 * get nutrition from the farmer and reduce the nutrition from the farmer
	 */
	@Override
	public void process(TimeStep timeStep) {
		// TODO Auto-generated method stub

		if (getStock() > 0) {
			transNutrition = 0;
		}

		// TODO Auto-generated method stub
		if (checkFarmerConsumer() == true) {
			if (grid.grid[farmerlo][yCoordinate].getStock() > 0) {
				if (grid.grid[farmerlo][yCoordinate].getStock() < transNum) {
					addToStock(grid.grid[farmerlo][yCoordinate].getStock());
					grid.grid[farmerlo][yCoordinate].reduceStock(grid.grid[farmerlo][yCoordinate].getStock());
				} else if (grid.grid[farmerlo][yCoordinate].getStock() >= transNum) {
					addToStock(transNum);
					grid.grid[farmerlo][yCoordinate].reduceStock(transNum);
				}
			}
		}
	}
	//check if there are farmers and consumers in the same column
	public boolean checkFarmerConsumer() {
		if (xCoordinate != 0 && xCoordinate != grid.getHeight() - 1) {
			for (int i = 0; i < xCoordinate; i++) {
				if (grid.grid[i][yCoordinate] != null) {
					if (grid.grid[i][yCoordinate] instanceof CornFarmer
							|| grid.grid[i][yCoordinate] instanceof RadishFarmer) {
						for (int j = xCoordinate + 1; j < grid.getHeight(); j++) {
							if (grid.grid[j][yCoordinate] != null) {
								if (grid.grid[j][yCoordinate] instanceof Beaver
										|| grid.grid[j][yCoordinate] instanceof Rabbit) {
									farmerlo = i;
									consumerlo = j;
									return true;
								}
							}
						}
					} else if (grid.grid[i][yCoordinate] instanceof Beaver
							|| grid.grid[i][yCoordinate] instanceof Rabbit) {
						for (int j = xCoordinate + 1; j < grid.getHeight(); j++) {
							if (grid.grid[j][yCoordinate] != null) {
								if (grid.grid[j][yCoordinate] instanceof CornFarmer
										|| grid.grid[j][yCoordinate] instanceof RadishFarmer) {
									consumerlo = i;
									farmerlo = j;
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
		return "VT";
	}
}

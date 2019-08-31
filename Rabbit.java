/**
 * This class is designed for rabbit, it extends Consumers class
 */
public class Rabbit extends Consumers {
	private int xCoordinate;
	private int yCoordinate;
	private int consumeNutrition = 0;
	private int htFarmerlo = 0;
	private int htTranslo = 0;
	private int vtFarmerlo = 0;
	private int vtTranslo = 0;
	private int index = -1;

	public Rabbit(Grid grid, int xCoordinate, int yCoordinate) {
		super(grid);
		grid.registerItem(xCoordinate, yCoordinate, this);
		this.xCoordinate = xCoordinate;
		this.yCoordinate = yCoordinate;
		// TODO Auto-generated constructor stub
	}
	/*
	 * check if there is transporters in the same row or in the same column 
	 * get nutrition from the transporter and reduce the nutrition of the transporter
	 * have to get all the nutrition of the transporter, but only consuming 8
	 */
	@Override
	public void process(TimeStep timeStep) {
		// TODO Auto-generated method stub
		// only one nearresttransporter
		if (checkNT()) {
			if (getStock() >= 8) {
				grid.recordConsumption(8);
				reduceStock(getStock());
			} else {
				grid.recordConsumption(getStock());
				grid.reduceStockAt(xCoordinate, yCoordinate, getStock());
			}
		}
		// only one transporter in the same row
		if (checkFarmerHT() == true && checkFarmerVT() != true) {
			index = 0;
		}
		// only one transporter in the same column
		if (checkFarmerHT() != true && checkFarmerVT() == true) {
			index = 1;
		}
		// multiple transporters in the same row and column
		if (checkFarmerHT() == true && checkFarmerVT() == true) {
			if (grid.grid[xCoordinate][htTranslo].getStock() > grid.grid[vtTranslo][yCoordinate].getStock()) {
				index = 2;
			} else {
				index = 3;
			}
		}
		if (index == 0 || index == 2) {
			if (grid.grid[xCoordinate][htTranslo].getStock() > 0 && grid.grid[xCoordinate][htTranslo].getStock() < 8) {
				grid.recordConsumption(grid.grid[xCoordinate][htTranslo].getStock());
				grid.grid[xCoordinate][htTranslo].reduceStock(grid.grid[xCoordinate][htTranslo].getStock());
			} else if (grid.grid[xCoordinate][htTranslo].getStock() >= 8) {
				grid.recordConsumption(8);
				grid.grid[xCoordinate][htTranslo].reduceStock(grid.grid[xCoordinate][htTranslo].getStock());

			}
		}

		if (index == 1 || index == 3) {
			if (grid.grid[vtTranslo][yCoordinate].getStock() > 0 && grid.grid[vtTranslo][yCoordinate].getStock() < 8) {
				grid.grid[vtTranslo][yCoordinate].reduceStock(grid.grid[vtTranslo][yCoordinate].getStock());
				grid.recordConsumption(grid.grid[vtTranslo][yCoordinate].getStock());

			} else if (grid.grid[vtTranslo][yCoordinate].getStock() >= 8) {
				grid.grid[vtTranslo][yCoordinate].reduceStock(grid.grid[vtTranslo][yCoordinate].getStock());

				grid.recordConsumption(8);
			}
		}

	}

	// check if there is transporters in the same row or in the same column
	public boolean checkFarmerHT() {
		// different positions, different functions to find out the transporters
		if (yCoordinate != 0) {
			for (int i = 0; i < yCoordinate; i++) {
				if (grid.grid[xCoordinate][i] != null) {
					if (grid.grid[xCoordinate][i] instanceof CornFarmer
							|| grid.grid[xCoordinate][i] instanceof RadishFarmer) {
						for (int j = i + 1; j < yCoordinate; j++) {
							if (grid.grid[xCoordinate][j] != null) {
								if (grid.grid[xCoordinate][j] instanceof HorizontalTransporter) {
									htFarmerlo = i;
									htTranslo = j;
									return true;
								}
							}
						}
					}
				}
			}
		} else {
			for (int i = grid.getWidth() - 1; i > yCoordinate; i--) {
				if (grid.grid[xCoordinate][i] != null) {
					if (grid.grid[xCoordinate][i] instanceof CornFarmer
							|| grid.grid[xCoordinate][i] instanceof RadishFarmer) {
						for (int j = i - 1; j > yCoordinate; j--) {
							if (grid.grid[xCoordinate][j] != null) {
								if (grid.grid[xCoordinate][j] instanceof HorizontalTransporter) {
									htFarmerlo = i;
									htTranslo = j;
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

	public boolean checkFarmerVT() {
		if (xCoordinate != 0) {
			for (int i = 0; i < xCoordinate; i++) {
				if (grid.grid[i][yCoordinate] != null) {
					if (grid.grid[i][yCoordinate] instanceof CornFarmer
							|| grid.grid[i][yCoordinate] instanceof RadishFarmer) {
						for (int j = i + 1; j < xCoordinate; j++) {
							if (grid.grid[xCoordinate][j] != null) {
								if (grid.grid[j][yCoordinate] instanceof VerticalTransporter) {
									vtFarmerlo = i;
									vtTranslo = j;
									return true;
								}
							}
						}
					}
				}
			}
		} else {
			for (int i = grid.getHeight() - 1; i > xCoordinate; i--) {
				if (grid.grid[i][yCoordinate] != null) {
					if (grid.grid[i][yCoordinate] instanceof CornFarmer
							|| grid.grid[i][yCoordinate] instanceof RadishFarmer) {
						for (int j = i - 1; j > xCoordinate; j--) {
							if (grid.grid[xCoordinate][j] != null) {
								if (grid.grid[j][yCoordinate] instanceof VerticalTransporter) {
									vtFarmerlo = i;
									vtTranslo = j;
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

	// check if there is a nearesttransporter
	public boolean checkNT() {
		for (int i = 0; i < grid.getHeight(); i++) {
			for (int j = 0; j < grid.getWidth(); j++) {
				if (grid.grid[i][j] != null && grid.grid[i][j] instanceof NearestTransporter) {

					return true;
				}
			}
		}

		return false;
	}

	@Override
	protected int getStock() {
		// TODO Auto-generated method stub
		return consumeNutrition;
	}

	@Override
	protected void addToStock(int nutrition) {
		// TODO Auto-generated method stub
		consumeNutrition += nutrition;
	}

	@Override
	protected void reduceStock(int nutrition) {
		// TODO Auto-generated method stub
		consumeNutrition -= nutrition;
	}

	public String toString() {
		return "Rabbit(" + getStock() + ")";
	}

}

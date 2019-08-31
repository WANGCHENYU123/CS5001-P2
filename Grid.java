import java.awt.geom.Area;
/**
 * This class is for generating a grid
 */
public class Grid extends AbstractGrid {

	private int xCoordinate;
	private int yCoordinate;
	public int production = 0;
	public int consumption = 0;

	public Grid(int xCoordinate, int yCoordinate) {
		this.xCoordinate = xCoordinate;
		this.yCoordinate = yCoordinate;
		super.grid = new AbstractItem[xCoordinate][yCoordinate];
		grid = new AbstractItem[xCoordinate][yCoordinate];
		stock = new int[xCoordinate][yCoordinate];
	}

	@Override
	public int getWidth() {
		// TODO Auto-generated method stub
		return this.yCoordinate;
	}

	@Override
	public int getHeight() {
		// TODO Auto-generated method stub
		return this.xCoordinate;
	}

	@Override
	public void registerItem(int xCoordinate, int yCoordinate, AbstractItem item) {
		// TODO Auto-generated method stub
		grid[xCoordinate][yCoordinate] = item;
	}

	@Override
	public AbstractItem getItem(int xCoordinate, int yCoordinate) {
		// TODO Auto-generated method stub
		return grid[xCoordinate][yCoordinate];

	}

	@Override
	public int getStockAt(int xCoordinate, int yCoordinate) {
		// TODO Auto-generated method stub
		return stock[xCoordinate][yCoordinate];
		// return 0;

	}

	@Override
	public void emptyStockAt(int xCoordinate, int yCoordinate) {
		// TODO Auto-generated method stub
		stock[xCoordinate][yCoordinate] = 0;
	}

	@Override
	public void addToStockAt(int xCoordinate, int yCoordinate, int nutrition) {
		// TODO Auto-generated method stub
		stock[xCoordinate][yCoordinate] += nutrition;
	}

	@Override
	public void reduceStockAt(int xCoordinate, int yCoordinate, int nutrition) {
		// TODO Auto-generated method stub
		stock[xCoordinate][yCoordinate] -= nutrition;

	}

	@Override
	public void setStockAt(int xCoordinate, int yCoordinate, int nutrition) {
		// TODO Auto-generated method stub
		stock[xCoordinate][yCoordinate] = nutrition;

	}

	// An entrance to all kinds of items
	// firstly the farmer, then the transporters and then the consumers
	@Override
	public void processItems(TimeStep timeStep) {
		// TODO Auto-generated method stub
		int m = 0;
		int n = 0;
		for (int k = 0; k < 3; k++) {
			if (k == 0) {
				for (int i = 0; i < xCoordinate; i++) {
					for (int j = 0; j < yCoordinate; j++) {
						if (grid[i][j] != null) {
							if (grid[i][j].getClass().equals(CornFarmer.class)) {
								grid[i][j].process(timeStep);
							} else if (grid[i][j].getClass().equals(RadishFarmer.class)) {
								grid[i][j].process(timeStep);
							}
						}

					}
				}
			} else if (k == 1) {
				for (int i = 0; i < xCoordinate; i++) {
					for (int j = 0; j < yCoordinate; j++) {
						if (grid[i][j] != null) {
							if (grid[i][j].getClass().equals(HorizontalTransporter.class)) {
								grid[i][j].process(timeStep);
							} else if (grid[i][j].getClass().equals(VerticalTransporter.class)) {
								grid[i][j].process(timeStep);
							} else if (grid[i][j].getClass().equals(NearestTransporter.class)) {
								grid[i][j].process(timeStep);
							}
						}

					}
				}
			} else if (k == 2) {
				for (int i = 0; i < xCoordinate; i++) {
					for (int j = 0; j < yCoordinate; j++) {
						if (grid[i][j] != null) {
							if (grid[i][j].getClass().equals(Beaver.class)) {
								grid[i][j].process(timeStep);
							} else if (grid[i][j].getClass().equals(Rabbit.class)) {
								grid[i][j].process(timeStep);
							}
						}

					}
				}
			}
		}

	}

	@Override
	public void recordProduction(int nutrition) {
		// TODO Auto-generated method stub
		production += nutrition;
	}

	@Override
	public int getTotalProduction() {
		// TODO Auto-generated method stub
		return production;
	}

	@Override
	public void recordConsumption(int nutrition) {
		// TODO Auto-generated method stub
		consumption += nutrition;
	}

	@Override
	public int getTotalConsumption() {
		// TODO Auto-generated method stub
		return consumption;
	}

}

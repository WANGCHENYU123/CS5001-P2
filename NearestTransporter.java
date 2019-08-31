import java.util.ArrayList;
/**
 * This class conducts operations about nearest transporter
 */
public class NearestTransporter extends Transporters {

	private int transNum;
	private int xCoordinate;
	private int yCoordinate;
	private int transNutrition = 0;
	private int FarmerX = 0;
	private int FarmerY = 0;
	private int consumerX = 0;
	private int consumerY = 0;
	private AbstractItem farmer;
	private AbstractItem consumer;

	public NearestTransporter(Grid grid, int xCoordinate, int yCoordinate, int transNum) {
		super(grid);
		this.xCoordinate = xCoordinate;
		this.yCoordinate = yCoordinate;
		grid.registerItem(xCoordinate, yCoordinate, this);
		this.transNum = transNum;
		// TODO Auto-generated constructor stub
	}
	/*
	 * check if there is farmer and consumer near it
	 *  get nutrition from the nearest farmer
	 */
	@Override
	public void process(TimeStep timeStep) {
		// TODO Auto-generated method stub
		if (getStock() > 0) {
			transNutrition = 0;
		}
		// TODO Auto-generated method stub
		double disFarmer;
		double disConsumer;
		double shortDistance = Math.sqrt(grid.getHeight() * grid.getHeight() + grid.getWidth() * grid.getWidth()) + 1;
		for (int i = 0; i < grid.getHeight(); i++) {
			for (int j = 0; j < grid.getWidth(); j++) {
				if (grid.grid[i][j] instanceof Farmers) {
					disFarmer = Math
							.sqrt((i - xCoordinate) * (i - xCoordinate) + (j - yCoordinate) * (j - yCoordinate));
					if (disFarmer < shortDistance) {
						shortDistance = disFarmer;
						FarmerX = i;
						FarmerY = j;
						farmer = grid.grid[i][j];
					}

					else if (disFarmer == shortDistance) {

						farmer = null;
					}
				}
			}
		}
		double shortDistance1 = Math.sqrt(grid.getHeight() * grid.getHeight() + grid.getWidth() * grid.getWidth()) + 1;
		for (int i = 0; i < grid.getHeight(); i++) {
			for (int j = 0; j < grid.getWidth(); j++) {
				if (grid.grid[i][j] instanceof Consumers) {
					disConsumer = Math
							.sqrt((i - xCoordinate) * (i - xCoordinate) + (j - yCoordinate) * (j - yCoordinate));
					if (disConsumer < shortDistance1) {
						shortDistance1 = disConsumer;
						consumerX = i;
						consumerY = j;
						consumer = grid.grid[i][j];
					} else if (disConsumer == shortDistance1) {
						consumer = null;
					}
				}
			}
		}
		if (farmer != null && consumer != null) {
			if (grid.grid[FarmerX][FarmerY].getStock() > 0) {
				if (grid.grid[FarmerX][FarmerY].getStock() < transNum) {
					addToStock(grid.grid[FarmerX][FarmerY].getStock());
					grid.grid[FarmerX][FarmerY].reduceStock(grid.grid[FarmerX][FarmerY].getStock());
					grid.addToStockAt(consumerX, consumerY, grid.grid[FarmerX][FarmerY].getStock());

				} else if (grid.grid[FarmerX][FarmerY].getStock() >= transNum) {
					addToStock(transNum);
					grid.grid[FarmerX][FarmerY].reduceStock(transNum);

					grid.grid[consumerX][consumerY].addToStock(transNum);
				}
			}
		}

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
		return "NT";
	}
}

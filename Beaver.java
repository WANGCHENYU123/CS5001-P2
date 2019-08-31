/**
 * The clss extends Consumers. It is for conducting operations about Beaver
 */
public class Beaver extends Consumers {
	private int xCoordinate;
	private int yCoordinate;
	private int consumeNutrition = 0;
	private int htFarmerlo = 0;
	private int htTranslo = 0;
	private int vtFarmerlo = 0;
	private int vtTranslo = 0;
	private int vtTranslo1 = 0;
	private int vtFarmerlo1 = 0;
	private int index = -1;
	private int vtTransNum = 0;
	private int index1 = 0;

	public Beaver(Grid grid, int xCoordinate, int yCoordinate) {
		super(grid);
		this.xCoordinate = xCoordinate;
		this.yCoordinate = yCoordinate;
		grid.registerItem(xCoordinate, yCoordinate, this);
	}

	@Override
	public void process(TimeStep timeStep) {
		// TODO Auto-generated method stub

		if (checkNT()) {
			if (getStock() >= 8) {
				grid.recordConsumption(8);
			} else {
				grid.recordConsumption(getStock());
			}
		}
		if (checkFarmerHT() == true && checkFarmerVT() != true) {
			index = 0;
		}
		if (checkFarmerHT() != true && checkFarmerVT() == true) {
			index = 1;
		}
		if (checkFarmerHT() == true && checkFarmerVT() == true) {
			if (grid.grid[xCoordinate][htTranslo].getStock() > grid.grid[vtTranslo][yCoordinate].getStock()) {
				index = 2;
			} else {
				index = 3;
			}
		}
		if (index == 0 || index == 2 && index1 == 0) {
			if (grid.grid[xCoordinate][htTranslo].getStock() > 0) {
				if (getStock() < 50 && getStock() >= 0) {
					addToStock(grid.grid[xCoordinate][htTranslo].getStock() - 5);
					grid.recordConsumption(5);
					grid.grid[xCoordinate][htTranslo].reduceStock(grid.grid[xCoordinate][htTranslo].getStock());
				} else {
					grid.recordConsumption(5);
					grid.grid[xCoordinate][htTranslo].reduceStock(grid.grid[xCoordinate][htTranslo].getStock());
				}
			}

			else if (grid.grid[xCoordinate][htTranslo].getStock() == 0) {
				if (getStock() > 0) {
					grid.recordConsumption(5);
					reduceStock(5);
				}
			}
		} else if (index == 1 || index == 3 && index1 == 0) {
			if (grid.grid[vtTranslo][yCoordinate].getStock() > 0) {
				if (getStock() < 50 && getStock() >= 0) {
					addToStock(grid.grid[vtTranslo][yCoordinate].getStock() - 5);
					grid.grid[vtTranslo][yCoordinate].reduceStock(grid.grid[vtTranslo][yCoordinate].getStock());
					grid.recordConsumption(5);
				} else {
					grid.recordConsumption(5);
					grid.grid[vtTranslo][yCoordinate].reduceStock(grid.grid[vtTranslo][yCoordinate].getStock());
				}
			} else if (grid.grid[vtTranslo][yCoordinate].getStock() == 0) {
				if (getStock() > 0) {
					grid.recordConsumption(5);
					reduceStock(5);
				}
			}
		}
		if (index1 == 1) {
			if (grid.grid[vtTranslo][yCoordinate].getStock() > 0
					&& grid.grid[vtTranslo1][yCoordinate].getStock() == 0) {
				if (getStock() < 50 && getStock() >= 0) {
					addToStock(grid.grid[vtTranslo][yCoordinate].getStock() - 5);
					grid.grid[vtTranslo][yCoordinate].reduceStock(grid.grid[vtTranslo][yCoordinate].getStock());
					grid.recordConsumption(5);
				} else {
					grid.recordConsumption(5);
					grid.grid[xCoordinate][htTranslo].reduceStock(grid.grid[xCoordinate][htTranslo].getStock());
				}
			} else if (grid.grid[vtTranslo][yCoordinate].getStock() == 0
					&& grid.grid[vtTranslo1][yCoordinate].getStock() == 0) {

				if (getStock() > 0) {
					grid.recordConsumption(5);
					reduceStock(5);
				}
			} else if (grid.grid[vtTranslo1][yCoordinate].getStock() > 0
					&& grid.grid[vtTranslo][yCoordinate].getStock() == 0) {
				if (getStock() < 50 && getStock() >= 0) {
					addToStock(grid.grid[vtTranslo1][yCoordinate].getStock() - 5);
					grid.grid[vtTranslo1][yCoordinate].reduceStock(grid.grid[vtTranslo1][yCoordinate].getStock());
					grid.recordConsumption(5);
				} else {
					grid.recordConsumption(5);
					grid.grid[vtTranslo1][yCoordinate].reduceStock(grid.grid[vtTranslo1][yCoordinate].getStock());
				}
			}

			else if (grid.grid[vtTranslo1][yCoordinate].getStock() > 0
					&& grid.grid[vtTranslo][yCoordinate].getStock() > 0) {
				if (getStock() < 50 && getStock() >= 0) {
					addToStock(grid.grid[vtTranslo1][yCoordinate].getStock() - 5);
					addToStock(grid.grid[vtTranslo][yCoordinate].getStock());
					grid.grid[vtTranslo1][yCoordinate].reduceStock(grid.grid[vtTranslo1][yCoordinate].getStock());
					grid.grid[vtTranslo][yCoordinate].reduceStock(grid.grid[vtTranslo][yCoordinate].getStock());
					grid.recordConsumption(5);
				} else {
					grid.recordConsumption(5);
					grid.grid[vtTranslo1][yCoordinate].reduceStock(grid.grid[vtTranslo1][yCoordinate].getStock());
					grid.grid[vtTranslo][yCoordinate].reduceStock(grid.grid[vtTranslo][yCoordinate].getStock());
				}
			}
		}
	}

	public boolean checkFarmerHT() {
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
		int condition = 0;
		if (checkVtTransBelow()) {
			condition = 1;
		}
		if (condition == 0) {
			for (int i = 0; i < xCoordinate; i++) {
				if (grid.grid[i][yCoordinate] != null) {
					if (grid.grid[i][yCoordinate] instanceof CornFarmer
							|| grid.grid[i][yCoordinate] instanceof RadishFarmer) {
						for (int j = i + 1; j < xCoordinate; j++) {
							if (grid.grid[j][yCoordinate] != null) {
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
		if (condition == 1 && xCoordinate == 0) {
			for (int i = grid.getHeight() - 1; i > xCoordinate; i--) {
				if (grid.grid[i][yCoordinate] != null) {
					if (grid.grid[i][yCoordinate] instanceof CornFarmer
							|| grid.grid[i][yCoordinate] instanceof RadishFarmer) {
						for (int j = i - 1; j > xCoordinate; j--) {
							if (grid.grid[j][yCoordinate] != null) {
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

		if (condition == 1) {
			for (int i = 0; i < xCoordinate; i++) {
				if (grid.grid[i][yCoordinate] != null) {
					if (grid.grid[i][yCoordinate] instanceof CornFarmer
							|| grid.grid[i][yCoordinate] instanceof RadishFarmer) {
						for (int j = i + 1; j < xCoordinate; j++) {
							if (grid.grid[j][yCoordinate] != null) {
								if (grid.grid[j][yCoordinate] instanceof VerticalTransporter) {
									vtFarmerlo = i;
									vtTranslo = j;
									break;
								}
							}
						}
					}
				}
			}
			for (int i = grid.getHeight() - 1; i > xCoordinate; i--) {
				if (grid.grid[i][yCoordinate] != null) {
					if (grid.grid[i][yCoordinate] instanceof CornFarmer
							|| grid.grid[i][yCoordinate] instanceof RadishFarmer) {
						for (int j = i - 1; j > xCoordinate; j--) {
							if (grid.grid[j][yCoordinate] != null) {
								if (grid.grid[j][yCoordinate] instanceof VerticalTransporter) {
									vtFarmerlo1 = i;
									vtTranslo1 = j;
									index1 = 1;
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

	public boolean checkVtTransBelow() {
		int num = 0;
		for (int i = xCoordinate + 1; i < grid.getHeight() - 1; i++) {
			if (grid.grid[i][yCoordinate] != null)
				num++;
		}
		if (num != 0) {
			return true;
		} else
			return false;
	}

	public boolean checkNT() {
		for (int i = 0; i < grid.getHeight() - 1; i++) {
			for (int j = 0; j < grid.getWidth() - 1; j++) {

				if (grid.grid[i][j] != null && grid.grid[i][j].getClass().equals(NearestTransporter.class)) {
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
		return "Beaver(" + getStock() + ")";
	}
}

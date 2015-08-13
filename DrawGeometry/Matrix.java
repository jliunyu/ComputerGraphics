
public class Matrix implements IMatrix {

	public int col;
	public int row;
	public double[][] matrix;

	public Matrix(int row, int col) {
		this.col = col;
		this.row = row;
		this.matrix = new double[row][col];
	}

	int w = 640; // THE WIDTH OF YOUR APPLET IN PIXELS

	int h = 480; // THE HEIGHT OF YOUR APPLET IN PIXELS

	double FL = 10.0; // "FOCAL LENGTH" OF THE CAMERA
	

	@Override
	public double[][] identity(int number) {
		double[][] Identitymatrix = new double[][]{{1, 0, 0, 0},
				{0, 1, 0, 0},
				{0, 0, 1, 0},
				{0, 0, 0, 1}};
		//As the size of multiplyMatrix should be differ according to number, I have to use new to allocate memory here
		double[][]  multiplyMatrix = new double[number][col];

		for (int i = 0; i < number; i++) {// rows of this.matrix
			for (int j = 0; j < col; j++) {// columns of other.matrix
				for (int k = 0; k < col; k++) {// columns of this.matrix = rows
												// of other.matrix
					multiplyMatrix[i][j] = multiplyMatrix[i][j]
							+ this.matrix[i][k] * Identitymatrix[k][j];
				}
			}
		}

		return multiplyMatrix;
	}

	@Override
	public void set(int col, int row, double value) {
		// TODO Auto-generated method stub
		matrix[col][row] = value;

	}

	@Override
	public double get(int col, int row) {
		// TODO Auto-generated method stub
		return matrix[col][row];

	}

	@Override
	public double[][] translate(double a, double b, double c, int number) {
		double[][] translationMatrix = new double[][]{{1, 0, 0, a},
				{0, 1, 0, b},
				{0, 0, 1, c},
				{0, 0, 0, 1}
		};
		//As the size of multiplyMatrix should be differ according to number, I have to use new to allocate memory here

		double[][] multiplyMatrix = new double[number][col];

		for (int i = 0; i < number; i++) {// rows of this.matrix
			for (int j = 0; j < col; j++) {// columns of other.matrix
				for (int k = 0; k < col; k++) {// columns of this.matrix = rows
												// of other.matrix
					multiplyMatrix[i][j] = multiplyMatrix[i][j]
							+ this.matrix[i][k] * translationMatrix[k][j];
				}
			}
		}

		return multiplyMatrix;

	}

	@Override
	public double[][] rotateX(double radians, int number) {
		double[][] RotationXmatrix = new double[][] { { 1, 0, 0, 0 },
				{ 0, Math.cos(radians), -Math.sin(radians), 0 },
				{ 0, Math.sin(radians), Math.cos(radians), 0 }, { 0, 0, 0, 1 } };
		//As the size of multiplyMatrix should be differ according to number, I have to use new to allocate memory here

		double[][] multiplyMatrix = new double[number][col];

		for (int i = 0; i < number; i++) {// rows of this.matrix
			for (int j = 0; j < col; j++) {// columns of other.matrix
				for (int k = 0; k < col; k++) {// columns of this.matrix = rows
												// of other.matrix
					multiplyMatrix[i][j] = multiplyMatrix[i][j]
							+ this.matrix[i][k] * RotationXmatrix[k][j];
				}
			}
		}

		return multiplyMatrix;

	}

	@Override
	public double[][] rotateY(double radians, int number) {

		double[][] RotationYmatrix = new double[][] {
				{ Math.cos(radians), 0, Math.sin(radians), 0 }, { 0, 1, 0, 0 },
				{ -Math.sin(radians), 0, Math.cos(radians), 0 }, { 0, 0, 0, 1 }

		};
		//As the size of multiplyMatrix should be differ according to number, I have to use new to allocate memory here

		double[][] multiplyMatrix = new double[number][col];

		for (int i = 0; i < number; i++) {// rows of this.matrix
			for (int j = 0; j < col; j++) {// columns of other.matrix
				for (int k = 0; k < col; k++) {// columns of this.matrix = rows
												// of other.matrix
					multiplyMatrix[i][j] = multiplyMatrix[i][j]
							+ this.matrix[i][k] * RotationYmatrix[k][j];
				}
			}
		}

		return multiplyMatrix;
	}

	@Override
	public double[][] rotateZ(double radians, int number) {
		
		double[][] RotationZmatrix = new double[][]{{Math.cos(radians), -Math.sin(radians), 0,0},
				{Math.sin(radians), Math.cos(radians), 0, 0},
				{0, 0, 1, 0},
				{0, 0, 0, 1}};
		//As the size of multiplyMatrix should be differ according to number, I have to use new to allocate memory here
		double[][] multiplyMatrix = new double[number][col];

		for (int i = 0; i < number; i++) {// rows of this.matrix
			for (int j = 0; j < col; j++) {// columns of other.matrix
				for (int k = 0; k < col; k++) {// columns of this.matrix = rows
												// of other.matrix
					multiplyMatrix[i][j] = multiplyMatrix[i][j]
							+ this.matrix[i][k] * RotationZmatrix[k][j];
				}
			}
		}

		return multiplyMatrix;

	}

	@Override
	public double[][] scale(int number, double a, double b, double c) {
		double[][] Scalematrix = new double[][] { { a, 0, 0, 0 },
				{ 0, b, 0, 0 }, { 0, 0, c, 0 }, { 0, 0, 0, 1 } };
		//As the size of multiplyMatrix should be differ according to number, I have to use new to allocate memory here

		double[][] multiplyMatrix = new double[number][col];

		for (int i = 0; i < number; i++) {// rows of this.matrix
			for (int j = 0; j < col; j++) {// columns of other.matrix
				for (int k = 0; k < col; k++) {// columns of this.matrix = rows
												// of other.matrix
					multiplyMatrix[i][j] = multiplyMatrix[i][j]
							+ this.matrix[i][k] * Scalematrix[k][j];
				}
			}
		}

		return multiplyMatrix;

	}

	
	public double[] transform(double[] src, double[] dst, int MyX, int MyY) {
		double x = src[0];
		double y = src[1];
		double z = src[2];

		// dst[0] = w / 2 + (int) (h * x / (FL - z));
		// dst[1] = h / 2 - (int) (h * y / (FL - z));

		dst[0] = MyX + (int) (h * x / (FL - z));
		dst[1] = MyY - (int) (h * y / (FL - z));
		return dst;
	}
	
	 
	public double[][] linertransform(double[][] other1, Matrix other, int number) {
		double[][] multiplyMatrix = new double[number][col];

		for (int i = 0; i < number; i++) {// rows of this.matrix
			for (int j = 0; j < col; j++) {// columns of other.matrix
				for (int k = 0; k < col; k++) {// columns of this.matrix = rows
												// of other.matrix
					multiplyMatrix[i][j] = multiplyMatrix[i][j]
							+ other1[i][k] * other.matrix[k][j];
				}
			}
		}
		/*
		for(int i = 0; i<number; i++)
		{
			for(int j = 0; j<col; j++)
			{
				System.out.print(other1[i][j]);
			}
			System.out.println();
		}
		*/
		

		return multiplyMatrix;
	}


	

}

import java.awt.Graphics;

public class Matrix extends BufferedApplet implements IMatrix {
	// IMatrix IMatrixObject;
	//private double[][] matrix={{1.00,0.00,0.00,0.00},{0.00,1.00,0.00,0.00},{0.00,0.00,1.00,0.00},{0.00,0.00,0.00,1.00}};
    
	
	//public double[][]matrix;
	public int col;
	public int row;
	public double[][] matrix;
	
	public Matrix( int row, int col){
		this.col = col;
		this.row = row;
		this.matrix = new double[row][col];
	}
	
	
	
	

	double time = System.currentTimeMillis() / 1000.0;
    int timeHelper =  Math.sin(time * 5.0) > 0.8 ? 1:0;
	int w = 640; // THE WIDTH OF YOUR APPLET IN PIXELS

	int h = 480; // THE HEIGHT OF YOUR APPLET IN PIXELS

	double FL = 10.0; // "FOCAL LENGTH" OF THE CAMERA

	double startTime = System.currentTimeMillis() / 1000.0;

	public void identity() {

	}

	public void set(int col, int row, double value) {
		matrix[col][row] = value;
	}

	public double get(int col, int row) {
		return matrix[col][row];
	}

	public void rotateX(double radians) {
		radians = Math.PI / 4;
	}

	public void rotateY(double radians) {
		radians = Math.PI / 4;
	}

	public void rotateZ(double radians) {
		radians = Math.PI / 4;
	}

	public void scale(double x, double y, double z) {
		x = 0.2;
		y = 0.2;
		z = 2.0;
	}

	public void leftMultiply(Matrix other) {
		double[][] tmp = new double[row][col];

		for (int i = 0; i < row; i++) {

			for (int j = 0; j < col; j++) {

				int result = 0;

				for (int k = 0; k < col; k++) {

					result += this.matrix[i][k] * other.matrix[k][j];

				}

				tmp[i][j] = result;
				System.out.println(tmp[i][j]);

			}

		}
		this.matrix = tmp;
	}
/*
	public void rightMultiply(Matrix other) {

				int result = 0;

				for (int i = 0; i < 4; i++) {

					result += (other.matrix[0][i] +Math.sin(timeHelper))* this.matrix[i][0];

				}

				this.matrix[3][0] = result;

		}
*/
	public double[] transform(double[] src, double[] dst) {
		double x = src[0];
		double y = src[1];
		double z = src[2];

		dst[0] = w / 2 + (int) (h * x / (FL - z));
		dst[1] = h / 2 - (int) (h * y / (FL - z));
		return dst;
	}

	@Override
	public void translate(double x, double y, double z) {
		// TODO Auto-generated method stub

	}

	@Override
	public void render(Graphics g) {
		// TODO Auto-generated method stub

	}

	@Override
	public void rightMultiply(Matrix other) {
		// TODO Auto-generated method stub
		
	}
	
	

}

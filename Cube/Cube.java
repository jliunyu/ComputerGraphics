import java.awt.*;

public class Cube extends BufferedApplet {
	private static final double[][] connection = new double[][] {
			{ 0, 1, 1, 0, 1, 0, 0, 0 }, { 1, 0, 0, 1, 0, 1, 0, 0 },
			{ 1, 0, 0, 1, 0, 0, 1, 0 }, { 0, 1, 1, 0, 0, 0, 0, 1 },
			{ 1, 0, 0, 0, 0, 1, 1, 0 }, { 0, 1, 0, 0, 1, 0, 0, 1 },
			{ 0, 0, 1, 0, 1, 0, 0, 1 }, { 0, 0, 0, 1, 0, 1, 1, 0 } };
	private static Matrix traMatrix = new Matrix(4, 4);

	int w = 0, h = 0;
	Font myFont = new Font("Courier", Font.ITALIC, 30);
	Color myColor = new Color(128, 200, 100, 128);
	boolean isMyMouseDown;
	int myX, myY;
	Color ovalColor = Color.pink;

	double startTime = System.currentTimeMillis() / 1000.0;
	

	public void render(Graphics g) {
		Matrix m = new Matrix(8, 4);
		m.matrix = new double[][] { { 1.00, 1.00, 0.00, 1.00 },
				{ 2.00, 1.00, 0.00, 1.00 }, { 1.00, 2.00, 0.00, 1.00 },
				{ 2.00, 2.00, 0.00, 1.00 },
				{ 1.1, 1.1, 1.00, 1.00 },
				{ 1.9, 1.1, 1, 1.00 },
				{ 1.1, 1.9, 1.00, 1.00 },
				{ 1.9, 1.9, 1.00, 1.00 } };
		
		double time = System.currentTimeMillis() / 1000.0 - startTime;

		traMatrix.matrix = new double[][] {
				{ Math.sin(time), 0.00, 0.00, 0.00 },
				{ 0.00, Math.sin(time), 0.00, 0.00 },
				{ 0.00, 0.00, Math.sin(time ), 0.00 },
				{ 0.00, 0.00, 0.00, Math.sin(time) } };

		//m.leftMultiply(traMatrix);

		if (w == 0) {
			w = getWidth();
			h = getHeight();
		}

		// m.multimatrix[][]={{100.00,200.00,100.00,200.00,110.00,190.00,110.00,190.00},{100.00,100.00,200.00,200.00,110.00,110.00,190.00,190.00}};

		Matrix projectMatrix = new Matrix(8, 2);
		for (int i = 0; i < 8; i++) {
			projectMatrix.matrix[i] = m.transform(m.matrix[i], projectMatrix.matrix[i]);
		}
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				if (connection[i][j] != 0) {
					g.drawLine((int) projectMatrix.matrix[i][0],
							(int) projectMatrix.matrix[i][1],
							(int) projectMatrix.matrix[j][0],
							(int) projectMatrix.matrix[j][1]);
				}
			}
		}

	}
}

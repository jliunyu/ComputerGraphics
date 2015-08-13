import java.util.ArrayList;


//public class Geometry extends BufferedApplet implements IGeoemtry
public class Geometry implements IGeometry {
	int myX = 200, myY = 200;
	// double angle = 0;
	double startTime = System.currentTimeMillis() / 2000.0;
	int M = 10;
	int N = 10;
	public int tempnum=0;
	public Matrix SphereMatrix = new Matrix((M + 1) * (N + 1), 2);
	public Matrix mCylinder = new Matrix((M + 1) * (N + 1), 4);
	public Matrix mSphere = new Matrix((M+1)*(N+1),4);
	public Matrix linMatrix = new Matrix(4, 4);
	double[][] vertices = new double[(M + 1) * (N + 1)][4];
	double[][] verticesSphere = new double[(M + 1) * (N + 1)][4];

	int[][] faces = new int[M * N][4];
	
	int numberOfChildren = 0;
	Geometry parent = null;
	ArrayList<Geometry> children = new ArrayList<Geometry>();

	public void CreateCylinder() {

		double u = 0;
		double v = 0;
		int number = 0;
		for (int i = 0; i < (M + 1); i++) {
			u = (double) i / M;
			for (int j = 0; j < (N + 1); j++) {
				v = (double) j / N;

				vertices[number][0] = Math.cos(2 * Math.PI * u);
				vertices[number][1] = Math.sin(2 * Math.PI * u);
				vertices[number][2] = 2 * v - 1;
				vertices[number][3] = 1;

				number++;
			}

		}
		mCylinder.matrix = vertices;

		for (int i = 0; i < M; i++) {
			for (int j = 0; j < N; j++) {
				faces[i * N + j][0] = i * (N + 1) + j;
				faces[i * N + j][1] = (i + 1) * (N + 1) + j;
				faces[i * N + j][2] = (i + 1) * (N + 1) + j + 1;
				faces[i * N + j][3] = i * (N + 1) + j + 1;

			}
		}

		
	}
	
	public void CreateSphere() {
		double u = 0; 
		double v = 0; 

		//Matrix mS = new Matrix((M+1)*(N+1), 4);
		//int[][] faces = new int [M*N][4];
		int number = 0;
		
		for(int i = 0; i< (M+1); i++)
		{
			u = (double) i/M;
			for(int j = 0; j<(N+1); j++)
			{
				v = (double) j/N;
				verticesSphere[number][0] = Math.cos(2*Math.PI*u)*Math.cos(Math.PI * (v-.5));
				verticesSphere[number][1] = Math.sin(2*Math.PI*u)*Math.cos(Math.PI*(v-.5));
				verticesSphere[number][2] = Math.sin(Math.PI*(v-.5));
				verticesSphere[number][3] = 1;
				number++;
			}
			
		}
		
		for(int i = 0; i<M; i++)
		{
			for(int j = 0; j<N; j++)
			{
				faces[i*N+j][0] = i*(N+1)+j;
				faces[i*N+j][1] = (i+1)*(N+1)+j;
				faces[i*N+j][2] = (i+1)*(N+1)+j+1;
				faces[i*N+j][3] = i*(N+1)+j+1;
				
			}
		}
		
		mSphere.matrix = verticesSphere;
		
		linMatrix.matrix = new double[][] { { 0.8, 0, 0, 0 }, { 0, 0.8, 0, 0 },
				{ 0, 0, 0.8, 0 }, { 0, 0, 0, 1 } };
		
		
	}
	

	public void add(Geometry child) {
		//this.mCylinder.matrix = child.mCylinder.linertransform(
		//				mCylinder.matrix, getMatrix(), (M + 1) * (N + 1));

		this.children.add(child);
		child.parent = this;
		numberOfChildren++;
		//this.mCylinder.matrix = child.mCylinder.linertransform(
		//		mCylinder.matrix, getMatrix(), (M + 1) * (N + 1));

	}

	@Override
	public Geometry getChild(int i) {
		// TODO Auto-generated method stub
		return this.children.get(i);
	}

	@Override
	public Matrix getMatrix() {
		return this.linMatrix;
		// TODO Auto-generated method stub

	}

	@Override
	public int getNumChildren() {
		// TODO Auto-generated method stub
		return this.numberOfChildren;
	}

	@Override
	public void remove(Geometry child) {
		// TODO Auto-generated method stub
		this.children.remove(child);
		numberOfChildren--;

	}

}

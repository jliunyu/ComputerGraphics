import java.awt.*;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;


//public class Geometry extends BufferedApplet implements KeyListener, MouseListener, MouseMotionListener 
public class Geometry extends BufferedApplet implements MouseListener, MouseMotionListener 

{

	// double angle = 0;
	double startTime = System.currentTimeMillis() / 2000.0;
	Matrix projectMatrix = new Matrix(24, 2);
	int M = 30;
	int N = 30;
	private Matrix SphereMatrix = new Matrix((M+1)*(N+1), 2);
	private Matrix mSphere = new Matrix((M+1)*(N+1), 4);
	private Matrix mCylinder = new Matrix((M+1)*(N+1), 4);
	private Matrix mCylinder1 = new Matrix((M+1)*(N+1), 4);
	private Matrix mCylinder2 = new Matrix((M+1)*(N+1), 4);
	private Matrix mCylinder3 = new Matrix((M+1)*(N+1), 4);
	private Matrix m = new Matrix(24, 4);
	
	private Matrix RotationX = new Matrix(4, 4);
	private Matrix RotationY = new Matrix(4, 4);
	private Matrix RotationZ = new Matrix(4, 4);
	private Matrix Scale = new Matrix(4, 4);
	private Matrix Identity = new Matrix(4,4);
	private Matrix translationMatrix = new Matrix(4,4);

	


	int[][] faces = new int [M*N][4];
	
	boolean isMyMouseDown;
	   int myX = 200, myY = 200;
	   Color ovalColor = Color.black;

	public boolean keyUp(Event e, int key) {
	      System.err.println(key);
	      switch (key) {
	      case 'r':
	         ovalColor = Color.red;
	         break;
	      case 'g':
	         ovalColor = Color.green;
	         break;
	      case 'b':
	         ovalColor = Color.blue;
	         break;
	      }
	      return true;
	   }
	double angle = 0;
	public void createCube(){
		double time = System.currentTimeMillis() / 2000.0 - startTime;
		
		//Matrix m = new Matrix(24, 4);
		
		m.matrix = new double[][] { // coordinates of the cube
			//m.matrix[24][4] = { // coordinates of the cube
				{0, 1, 0, 1}, {1, 1, 0, 1},{0, 0, 0, 1}, {1, 0, 0, 1},
				{1, 1, 0, 1}, {1, 1, -1, 1}, {1, 0, 0, 1}, {1, 0, -1, 1},
				{0, 1, -1, 1}, {1, 1, -1, 1}, {0, 1, 0, 1}, {1, 1, 0, 1},
				{0, 1, 0, 1}, {0, 1, -1, 1}, {0, 0, 0, 1}, {0, 0, -1, 1}, 
				{0, 1, -1, 1}, {1, 1, -1, 1}, {0, 1, 0, 1}, {1, 1, 0, 1}, 
				{0, 0, -1, 1}, {1, 0, -1, 1}, {0, 0, 0, 1}, {1, 0, 0, 1}
		};
		angle = time;
		// System.out.println(timeHelper);
		RotationX.matrix = new double[][] { { 1, 0, 0, 0 },
				{ 0, Math.cos(angle), -Math.sin(angle), 0 },
				{ 0, Math.sin(angle), Math.cos(angle), 0 }, { 0, 0, 0, 1 } };
		// m.rotateX(angle, RotationX);
		/*
		RotationY.matrix = new double[][]{ {Math.cos(angle), 0, Math.sin(angle), 0},
				{0, 1, 0, 0},
				{-Math.sin(angle), 0, Math.cos(angle), 0},
				{0, 0, 0, 1}
				
		};
		*/
		
		RotationZ.matrix = new double[][]{{Math.cos(angle), -Math.sin(angle), 0,0},
				{Math.sin(angle), Math.cos(angle), 0, 0},
				{0, 0, 1, 0},
				{0, 0, 0, 1}
		};
		//
		Scale.matrix = new double[][]{{2, 0, 0, 0},
				{0, 2, 0, 0}, 
				{0, 0, 2, 0},
				{0, 0, 0, 1}
		};
		//
		Identity.matrix = new double[][]{{1, 0, 0, 0},
			{0, 1, 0, 0},
			{0, 0, 1, 0},
			{0, 0, 0, 1}};
		
		translationMatrix.matrix = new double[][]{{1, 0, 0, 2},
				{0, 1, 0, 2},
				{0, 0, 1, 2},
				{0, 0, 0, 1}
		};
		
		m.matrix = m.identity(Identity, 24);
		m.matrix = m.scale(24, 15, 6, 1);
		m.matrix = m.translate(translationMatrix, 24);
		m.matrix = m.rotateX(Math.PI/2, 24);
		m.matrix = m.rotateY(-Math.PI/6, 24);
		//m.matrix = m.rotateZ(RotationZ, 24);


		//Matrix projectMatrix = new Matrix(24, 2);
		for (int i = 0; i < 24; i++) {
			projectMatrix.matrix[i] = m.transform(m.matrix[i],
					projectMatrix.matrix[i],myX+10, myY-80);
		}
	}
	
	private static final double[][] cubeFaces = new double[][] {
			{ 0, 1, 2, 3 }, { 4, 5, 6, 7 }, { 8, 9, 10, 11 },
			{ 12, 13, 14, 15 }, { 16, 17, 18, 19 }, { 20, 21, 22, 23 } };
			
    
    public void mouseMoved(MouseEvent e) {
    	//myX = e.getX();
		//myY = e.getY();    
    	}
	
    public void mouseClicked(MouseEvent e) {
		   myX = e.getX();
		   myY = e.getY();
	   }
	
	public void render(Graphics g) {
		int w = 0, h = 0;

		if (w == 0) {
			w = getWidth();
			h = getHeight();
		}
		
		 //addKeyListener(this);
	     addMouseMotionListener(this);


		//
		g.setColor(Color.white);
		g.fillRect(0, 0, w, h);
		g.setColor(Color.black);
		
		
		
		createCube();
		
		g.setColor(Color.black);

		//CreateCylinder();
		

		g.setColor(Color.red);
		
		for (int i = 0; i < 24; i = i+4) {

			g.drawLine((int) projectMatrix.matrix[i][0],
					(int) projectMatrix.matrix[i][1],
					(int) projectMatrix.matrix[i + 1][0],
					(int) projectMatrix.matrix[i + 1][1]);

			g.drawLine((int) projectMatrix.matrix[i][0],
					(int) projectMatrix.matrix[i][1],
					(int) projectMatrix.matrix[i + 2][0],
					(int) projectMatrix.matrix[i + 2][1]);

			g.drawLine((int) projectMatrix.matrix[i + 2][0],
					(int) projectMatrix.matrix[i + 2][1],
					(int) projectMatrix.matrix[i + 3][0],
					(int) projectMatrix.matrix[i + 3][1]);

			g.drawLine((int) projectMatrix.matrix[i + 1][0],
					(int) projectMatrix.matrix[i + 1][1],
					(int) projectMatrix.matrix[i + 3][0],
					(int) projectMatrix.matrix[i + 3][1]);
		}
		
		CreateCylinder1();

		g.setColor(Color.black);
		for(int i = 0; i< M*N; i++)
		{
			//System.out.println(faces[i][1]);
			g.drawLine((int) SphereMatrix.matrix[faces[i][0]][0],
					(int) SphereMatrix.matrix[faces[i][0]][1],
					(int) SphereMatrix.matrix[faces[i][1]][0],
					(int) SphereMatrix.matrix[faces[i][1]][1]);
			
			g.drawLine((int) SphereMatrix.matrix[faces[i][0]][0],
					(int) SphereMatrix.matrix[faces[i][0]][1],
					(int) SphereMatrix.matrix[faces[i][2]][0],
					(int) SphereMatrix.matrix[faces[i][2]][1]);
			g.drawLine((int) SphereMatrix.matrix[faces[i][2]][0],
					(int) SphereMatrix.matrix[faces[i][2]][1],
					(int) SphereMatrix.matrix[faces[i][3]][0],
					(int) SphereMatrix.matrix[faces[i][3]][1]);
			
			g.drawLine((int) SphereMatrix.matrix[faces[i][0]][0],
					(int) SphereMatrix.matrix[faces[i][0]][1],
					(int) SphereMatrix.matrix[faces[i][3]][0],
					(int) SphereMatrix.matrix[faces[i][3]][1]);
					
		}
		
		CreateCylinder();
		g.setColor(Color.black);
		for(int i = 0; i< M*N; i++)
		{
			//System.out.println(faces[i][1]);
			g.drawLine((int) SphereMatrix.matrix[faces[i][0]][0],
					(int) SphereMatrix.matrix[faces[i][0]][1],
					(int) SphereMatrix.matrix[faces[i][1]][0],
					(int) SphereMatrix.matrix[faces[i][1]][1]);
			
			g.drawLine((int) SphereMatrix.matrix[faces[i][0]][0],
					(int) SphereMatrix.matrix[faces[i][0]][1],
					(int) SphereMatrix.matrix[faces[i][2]][0],
					(int) SphereMatrix.matrix[faces[i][2]][1]);
			g.drawLine((int) SphereMatrix.matrix[faces[i][2]][0],
					(int) SphereMatrix.matrix[faces[i][2]][1],
					(int) SphereMatrix.matrix[faces[i][3]][0],
					(int) SphereMatrix.matrix[faces[i][3]][1]);
			
			g.drawLine((int) SphereMatrix.matrix[faces[i][0]][0],
					(int) SphereMatrix.matrix[faces[i][0]][1],
					(int) SphereMatrix.matrix[faces[i][3]][0],
					(int) SphereMatrix.matrix[faces[i][3]][1]);
					
		}
		
		CreateSphere();
		g.setColor(Color.yellow);
		for(int i = 0; i< M*N; i++)
		{
			//System.out.println(faces[i][1]);
					g.setColor(Color.yellow);

			g.drawLine((int) SphereMatrix.matrix[faces[i][0]][0],
					(int) SphereMatrix.matrix[faces[i][0]][1],
					(int) SphereMatrix.matrix[faces[i][1]][0],
					(int) SphereMatrix.matrix[faces[i][1]][1]);
			
			g.drawLine((int) SphereMatrix.matrix[faces[i][0]][0],
					(int) SphereMatrix.matrix[faces[i][0]][1],
					(int) SphereMatrix.matrix[faces[i][2]][0],
					(int) SphereMatrix.matrix[faces[i][2]][1]);
					
			g.setColor(Color.red);

			g.drawLine((int) SphereMatrix.matrix[faces[i][2]][0],
					(int) SphereMatrix.matrix[faces[i][2]][1],
					(int) SphereMatrix.matrix[faces[i][3]][0],
					(int) SphereMatrix.matrix[faces[i][3]][1]);
			
			g.drawLine((int) SphereMatrix.matrix[faces[i][0]][0],
					(int) SphereMatrix.matrix[faces[i][0]][1],
					(int) SphereMatrix.matrix[faces[i][3]][0],
					(int) SphereMatrix.matrix[faces[i][3]][1]);
					
		}
		
		CreateCylinder2();

		g.setColor(Color.black);
		for(int i = 0; i< M*N; i++)
		{
			//System.out.println(faces[i][1]);
			g.drawLine((int) SphereMatrix.matrix[faces[i][0]][0],
					(int) SphereMatrix.matrix[faces[i][0]][1],
					(int) SphereMatrix.matrix[faces[i][1]][0],
					(int) SphereMatrix.matrix[faces[i][1]][1]);
			
			g.drawLine((int) SphereMatrix.matrix[faces[i][0]][0],
					(int) SphereMatrix.matrix[faces[i][0]][1],
					(int) SphereMatrix.matrix[faces[i][2]][0],
					(int) SphereMatrix.matrix[faces[i][2]][1]);
			g.drawLine((int) SphereMatrix.matrix[faces[i][2]][0],
					(int) SphereMatrix.matrix[faces[i][2]][1],
					(int) SphereMatrix.matrix[faces[i][3]][0],
					(int) SphereMatrix.matrix[faces[i][3]][1]);
			
			g.drawLine((int) SphereMatrix.matrix[faces[i][0]][0],
					(int) SphereMatrix.matrix[faces[i][0]][1],
					(int) SphereMatrix.matrix[faces[i][3]][0],
					(int) SphereMatrix.matrix[faces[i][3]][1]);
					
		}
		
		CreateCylinder3();

		g.setColor(Color.black);
		for(int i = 0; i< M*N; i++)
		{
			//System.out.println(faces[i][1]);
			g.drawLine((int) SphereMatrix.matrix[faces[i][0]][0],
					(int) SphereMatrix.matrix[faces[i][0]][1],
					(int) SphereMatrix.matrix[faces[i][1]][0],
					(int) SphereMatrix.matrix[faces[i][1]][1]);
			
			g.drawLine((int) SphereMatrix.matrix[faces[i][0]][0],
					(int) SphereMatrix.matrix[faces[i][0]][1],
					(int) SphereMatrix.matrix[faces[i][2]][0],
					(int) SphereMatrix.matrix[faces[i][2]][1]);
			g.drawLine((int) SphereMatrix.matrix[faces[i][2]][0],
					(int) SphereMatrix.matrix[faces[i][2]][1],
					(int) SphereMatrix.matrix[faces[i][3]][0],
					(int) SphereMatrix.matrix[faces[i][3]][1]);
			
			g.drawLine((int) SphereMatrix.matrix[faces[i][0]][0],
					(int) SphereMatrix.matrix[faces[i][0]][1],
					(int) SphereMatrix.matrix[faces[i][3]][0],
					(int) SphereMatrix.matrix[faces[i][3]][1]);
					
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
				mSphere.matrix[number][0] = Math.cos(2*Math.PI*u)*Math.cos(Math.PI * (v-.5));
				mSphere.matrix[number][1] = Math.sin(2*Math.PI*u)*Math.cos(Math.PI*(v-.5));
				mSphere.matrix[number][2] = Math.sin(Math.PI*(v-.5));
				mSphere.matrix[number][3] = 1;
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
		
		
		mSphere.matrix = mSphere.identity(Identity, (M+1)*(N+1));
		mSphere.matrix = mSphere.scale((M+1)*(N+1), 1, 1, 1);
		mSphere.matrix = mSphere.translate(translationMatrix, (M+1)*(N+1));
		mSphere.matrix = mSphere.rotateX(angle, (M+1)*(N+1));
		mSphere.matrix = mSphere.rotateY(angle, (M+1)*(N+1));
		mSphere.matrix = mSphere.rotateZ(RotationZ, (M+1)*(N+1));
		
		//Matrix projectMatrix = new Matrix(24, 2);
		for (int i = 0; i < (M+1)*(N+1); i++) {
			SphereMatrix.matrix[i] = mSphere.transform(mSphere.matrix[i],
					SphereMatrix.matrix[i], myX+150, myY-129);
			//System.out.println(mS.matrix[i]);
		}
		
	}
	
	/*

	public void CreateSphere() {
		double u = 0; 
		double v = 0; 

		Matrix mS = new Matrix((M+1)*(N+1), 4);
		//int[][] faces = new int [M*N][4];
		int number = 0;
		
		for(int i = 0; i< (M+1); i++)
		{
			u = (double) i/M;
			for(int j = 0; j<(N+1); j++)
			{
				v = (double) j/N;
				mS.matrix[number][0] = Math.cos(2*Math.PI*u)*Math.cos(Math.PI * (v-.5));
				mS.matrix[number][1] = Math.sin(2*Math.PI*u)*Math.cos(Math.PI*(v-.5));
				mS.matrix[number][2] = Math.sin(Math.PI*(v-.5));
				mS.matrix[number][3] = 1;
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
		
		
		mS.matrix = mS.identity(Identity, (M+1)*(N+1));
		mS.matrix = mS.scale((M+1)*(N+1), 1, 1, 1);
		mS.matrix = mS.translate(translationMatrix, (M+1)*(N+1));
		mS.matrix = mS.rotateX(angle, (M+1)*(N+1));
		mS.matrix = mS.rotateY(angle, (M+1)*(N+1));
		mS.matrix = mS.rotateZ(RotationZ, (M+1)*(N+1));
		
		//Matrix projectMatrix = new Matrix(24, 2);
		for (int i = 0; i < (M+1)*(N+1); i++) {
			SphereMatrix.matrix[i] = mS.transform(mS.matrix[i],
					SphereMatrix.matrix[i], myX+150, myY-129);
			//System.out.println(mS.matrix[i]);
		}
		
	}
	
	*/

public void CreateCylinder() {
		
		//double angle = 0;
		
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
				
				mCylinder.matrix[number][0] = Math.cos(2*Math.PI*u);
				mCylinder.matrix[number][1] = Math.sin(2*Math.PI*u);
				mCylinder.matrix[number][2] = 2*v-1;

				mCylinder.matrix[number][3] = 1;
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
		
		
		mCylinder.matrix = mCylinder.identity(Identity, (M+1)*(N+1));
		mCylinder.matrix = mCylinder.scale((M+1)*(N+1), 0.2,0.5,2);
		mCylinder.matrix = mCylinder.translate(translationMatrix, (M+1)*(N+1));
		mCylinder.matrix = mCylinder.rotateX((double) Math.PI/2, (M+1)*(N+1));
		//mS.matrix = mS.rotateY(RotationY, (M+1)*(N+1));
		//mS.matrix = mS.rotateZ(RotationZ, (M+1)*(N+1));
		
		//Matrix projectMatrix = new Matrix(24, 2);
		for (int i = 0; i < (M+1)*(N+1); i++) {
			SphereMatrix.matrix[i] = mCylinder.transform(mCylinder.matrix[i],
					SphereMatrix.matrix[i], myX+18, myY+68);
			
		}
		
	}


public void CreateCylinder1() {
	
	//double angle = 0;
	
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
			
			mCylinder1.matrix[number][0] = Math.cos(2*Math.PI*u);
			mCylinder1.matrix[number][1] = Math.sin(2*Math.PI*u);
			mCylinder1.matrix[number][2] = 2*v-1;
			mCylinder1.matrix[number][3] = 1;
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
	
	
	mCylinder1.matrix = mCylinder1.identity(Identity, (M+1)*(N+1));
	mCylinder1.matrix = mCylinder1.scale((M+1)*(N+1), 0.2,0.5,2);
	mCylinder1.matrix = mCylinder1.translate(translationMatrix, (M+1)*(N+1));
	mCylinder1.matrix = mCylinder1.rotateX((double) Math.PI/2, (M+1)*(N+1));
	//mS.matrix = mS.rotateY(RotationY, (M+1)*(N+1));
	//mS.matrix = mS.rotateZ(RotationZ, (M+1)*(N+1));
	
	//Matrix projectMatrix = new Matrix(24, 2);
	for (int i = 0; i < (M+1)*(N+1); i++) {
		SphereMatrix.matrix[i] = mCylinder1.transform(mCylinder1.matrix[i],
				SphereMatrix.matrix[i], myX+355, myY+48);
		
	}
	
}

public void CreateCylinder2() {
	
	//double angle = 0;
	
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
			
			mCylinder2.matrix[number][0] = Math.cos(2*Math.PI*u);
			mCylinder2.matrix[number][1] = Math.sin(2*Math.PI*u);
			mCylinder2.matrix[number][2] = 2*v-1;

			mCylinder2.matrix[number][3] = 1;
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
	
	
	mCylinder2.matrix = mCylinder2.identity(Identity, (M+1)*(N+1));
	mCylinder2.matrix = mCylinder2.scale((M+1)*(N+1), 0.2,0.5,2);
	mCylinder2.matrix = mCylinder2.translate(translationMatrix, (M+1)*(N+1));
	mCylinder2.matrix = mCylinder2.rotateX((double) Math.PI/2, (M+1)*(N+1));
	//mS.matrix = mS.rotateY(RotationY, (M+1)*(N+1));
	//mS.matrix = mS.rotateZ(RotationZ, (M+1)*(N+1));
	
	//Matrix projectMatrix = new Matrix(24, 2);
	for (int i = 0; i < (M+1)*(N+1); i++) {
		SphereMatrix.matrix[i] = mCylinder2.transform(mCylinder2.matrix[i],
				SphereMatrix.matrix[i], myX-75, myY+51);
		
	}
	
}

public void CreateCylinder3() {
	
	//double angle = 0;
	
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
			
			mCylinder3.matrix[number][0] = Math.cos(2*Math.PI*u);
			mCylinder3.matrix[number][1] = Math.sin(2*Math.PI*u);
			mCylinder3.matrix[number][2] = 2*v-1;

			mCylinder3.matrix[number][3] = 1;
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
	
	
	mCylinder3.matrix = mCylinder3.identity(Identity, (M+1)*(N+1));
	mCylinder3.matrix = mCylinder3.scale((M+1)*(N+1), 0.2,0.5,2);
	mCylinder3.matrix = mCylinder3.translate(translationMatrix, (M+1)*(N+1));
	mCylinder3.matrix = mCylinder3.rotateX((double) Math.PI/2, (M+1)*(N+1));
	//mS.matrix = mS.rotateY(RotationY, (M+1)*(N+1));
	//mS.matrix = mS.rotateZ(RotationZ, (M+1)*(N+1));
	
	//Matrix projectMatrix = new Matrix(24, 2);
	for (int i = 0; i < (M+1)*(N+1); i++) {
		SphereMatrix.matrix[i] = mCylinder3.transform(mCylinder3.matrix[i],
				SphereMatrix.matrix[i], myX+225, myY+43);
		
	}
	
}

	   public void mouseEntered(MouseEvent e) {
		      System.err.println("mouse entered " + e.getX() + " " + e.getY());
		   }
		   public void mouseExited(MouseEvent e) {
		      System.err.println("mouse exited " + e.getX() + " " + e.getY());
		   }
		   public void mousePressed(MouseEvent e) {
		      System.err.println("mouse pressed " + e.getX() + " " + e.getY());
		   }
		   public void mouseReleased(MouseEvent e) {
		      System.err.println("mouse released " + e.getX() + " " + e.getY());
		   }
		   

		public void mouseDragged(MouseEvent e) {
			myX = e.getX();
			myY = e.getY();
		}


}

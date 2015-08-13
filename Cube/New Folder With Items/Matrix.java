import java.awt.*;
import java.awt.event.*;


public class Matrix extends BufferedApplet implements IMatrix
{
	//IMatrix IMatrixObject;
	private double[][] multimatrix;
	
	int w = 640; // THE WIDTH OF YOUR APPLET IN PIXELS

  int h = 480; // THE HEIGHT OF YOUR APPLET IN PIXELS

  double FL = 10.0; // "FOCAL LENGTH" OF THE CAMERA
	
	double startTime = System.currentTimeMillis() / 1000.0;
	
	IMatrixObject.identity()
	{
		
	}
	
	void set(int col, int row, double value)
	{
		multimatrix[col][row] = value;
	}
	
	double get(int col, int row)
	{
		return multimatrix[col][row];
	}
	
	void rotateX(double radians)
	{
		radians = Math.PI/4;
	}
	
   void rotateY(double radians)
   {
   		radians = Math.PI/4;
   }
  
  void rotateZ(double radians)
  {
  		radians = Math.PI/4;
  }
  
   void scale(double x, double y, double z)
   {
			x = 0.2;
			y = 0.2;
			z = 2.0;
   }
  
  void leftMultiply(Matrix other)
  {
  for(int i=0;i<4;i++){

   for(int j=0;j<4;j++){

    int result=0;

    for(int k=0;k<m;k++){

     result+=this.multimatrix[i][k]*other.multimatrix[k][j];

    }

    this.multimatrix[i][j]=result;

   }

  }
  }
  
  void rightMultiply(Matrix other)
  {
  	for(int i=0;i<4;i++){

   for(int j=0;j<4;j++){

    int result=0;

    for(int k=0;k<m;k++){

     result+=this.multimatrix[i][k]*newmatrix[k][j];

    }

    this.multimatrix[i][j]=result;

   }

  }
  }
  
  void transform(double[] src, double[] dst)
  {
  	  double x = src[0];
      double y = src[1];
      double z = src[2];
      
      dst[0] = w / 2 + (int)(h * x / (FL - z));
      dst[1] = h / 2 - (int)(h * y / (FL - z));
  }
	
	
	
 }

				


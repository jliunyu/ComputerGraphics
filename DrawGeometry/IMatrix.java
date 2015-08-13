
/*
Note:

   set(row,col,value) sets the value for a single row and column.
   get(row,col) retrieves the value at a single row and column.

   There are two possible ways to multiply this matrix by another matrix:

      leftMultiply  means:  this <= other times this
      rightMultiply means:  this <= this times other

   transform(src,dst) is how you apply the matrix transformation
   to individual points.
*/

public interface IMatrix
{
   public double[][] identity(int number);
   public void set(int col, int row, double value);
   public double get(int col, int row);
   public double[][] translate(double a, double b, double c, int number);
   public double[][] rotateX(double radians, int number);
   public double[][] rotateY(double radians, int number);
   public double[][] rotateZ(double radians, int number);
   public double[][] scale(int number, double a, double b, double c);
   
   public double[][] linertransform(double[][] other1, Matrix other, int number);
   
   public double[] transform(double[] src, double[] dst, int MyX, int MyY);
}


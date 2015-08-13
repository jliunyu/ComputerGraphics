
import java.awt.*;



public class Cube extends BufferedApplet
{
   int w = 0, h = 0;
   Font myFont = new Font("Courier", Font.ITALIC, 30);
   Color myColor = new Color(128, 200, 100, 128);
   boolean isMyMouseDown;
   int myX, myY;
   Color ovalColor = Color.pink;

   double startTime = System.currentTimeMillis() / 1000.0;

   

   public void render(Graphics g) {
      if (w == 0) {
         w = getWidth();
         h = getHeight();
      }

      double time = System.currentTimeMillis() / 1000.0 - startTime;
      int timeHelper =  Math.sin(time * 5.0) > 0.8 ? 1:0;
/*
      g.setColor(Color.black);
      g.drawLine(25, 25, 25, 125);
      
      g.setColor(Color.black);
      g.drawLine(25, 125, 125, 125);
      
      g.setColor(Color.black);
      g.drawLine(125, 25, 125, 125);
      
      g.setColor(Color.black);
      g.drawLine(125, 25, 25, 25);
*/
      
      g.setColor(Color.black);
      g.drawRect(w/2-50, h/2-50, 100, 100);
      
      g.setColor(Color.black);
      g.drawLine(w/2-50, h/2-50, w/2-40, h/2-40);
      
      g.setColor(Color.black);
      g.drawLine(w/2-40, h/2-40, w/2-40, h/2+40);
      
      g.setColor(Color.black);
      g.drawLine(w/2-50, h/2+50, w/2-40, h/2+40);
      
      g.setColor(Color.black);
      g.drawLine(w/2-40, h/2-40, w/2+40, h/2-40);
      
      g.setColor(Color.black);
      g.drawLine(w/2+40, h/2-40, w/2+50, h/2-50);
      
      g.setColor(Color.black);
      g.drawLine(w/2-40, h/2+40, w/2+40, h/2+40);
      
       g.setColor(Color.black);
      g.drawLine(w/2+40, h/2+40, w/2+50, h/2+50);
      
      g.setColor(Color.black);
      g.drawLine(w/2+40, h/2-40, w/2+40, h/2+40);
      
      //g.drawString("Happy Valentine's Day", 150 + (int)(50 * Math.sin(time)),
      //75 + (int)(50 * Math.cos(time)));

      //g.setColor(ovalColor);
      //g.fill3DRect(100 - timeHelper*10, 200 - timeHelper * 10, 100 + timeHelper* 20, 100 + timeHelper* 20);
      //g.setColor(ovalColor);
      //g.fill3DRect(180 - timeHelper*10, 200 - timeHelper*10, 100 + timeHelper*20, 100 + timeHelper*20);
      
    
     
    // Matrix m = new Matrix();
    // m.transform();
     
     

      
      
     
     
      
   }
}


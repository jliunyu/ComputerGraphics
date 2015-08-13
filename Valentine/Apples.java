import java.awt.*;

public class Apples extends BufferedApplet
{
   int w = 0, h = 0, count = 0;
   Font myFont = new Font("Courier", Font.ITALIC, 50);
   
   int myX, myY;
   Color ovalColor = Color.black;

   double startTime = System.currentTimeMillis() / 1000.0;

   public void render(Graphics g) {
      if (w == 0) {
         w = getWidth();
         h = getHeight();
      }

     double time = System.currentTimeMillis() / 1000.0 - startTime;

   Font myFont = new Font("Courier", Font.ITALIC, 50 + (int)(100 * ((Math.cos(time))+1)));

      g.setColor(Color.white);
      g.fillRect(0, 0, w, h);

      g.setColor(Color.red);
      g.setFont(myFont);
      
      g.drawString("Happy Valentine's Day", 50 + (int)(100 * Math.sin(time)),
                              100 + (int)(100 * Math.cos(time)));

   }
}


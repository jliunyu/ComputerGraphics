
import java.awt.*;

public class Valentine extends BufferedApplet
{
   int w = 0, h = 0;
   Font myFont = new Font("Courier", Font.ITALIC, 30);
   Color myColor = new Color(128, 200, 100, 128);
   boolean isMyMouseDown;
   int myX, myY;
   Color ovalColor = Color.pink;

   


   double startTime = System.currentTimeMillis() / 1000.0;

   public boolean keyUp(Event e, int key) {
      //System.err.println(key);
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

   public boolean mouseMove(Event e, int x, int y) {
      return true;
   }

   public boolean mouseDown(Event e, int x, int y) {
      isMyMouseDown = true;
      myX = x;
      myY = y;
      return true;
   }

   public boolean mouseDrag(Event e, int x, int y) {
      myX = x;
      myY = y;
      return true;
   }

   public boolean mouseUp(Event e, int x, int y) {
      isMyMouseDown = false;
      return true;
   }

   public void render(Graphics g) {
      if (w == 0) {
         w = getWidth();
         h = getHeight();
      }

      double time = System.currentTimeMillis() / 1000.0 - startTime;
      int timeHelper =  Math.sin(time * 5.0) > 0.8 ? 1:0;

      g.setColor(Color.white);
      g.fillRect(0, 0, w, h);
      
      g.setColor(Color.red);
      g.setFont(myFont);

      g.drawString("Happy Valentine's Day", 150 + (int)(50 * Math.sin(time)),
      75 + (int)(50 * Math.cos(time)));

      g.setColor(ovalColor);
      g.fillOval(100 - timeHelper*10, 200 - timeHelper * 10, 100 + timeHelper* 20, 100 + timeHelper* 20);
      g.setColor(ovalColor);
      g.fillOval(180 - timeHelper*10, 200 - timeHelper*10, 100 + timeHelper*20, 100 + timeHelper*20);
      int[] X = { 115 - timeHelper*7, 190, 265 + timeHelper*7, 150, 150 };
      int[] Y = { 285 + timeHelper*7, 345 + timeHelper*14, 285 + timeHelper*7, 250, 250 };
      g.fillPolygon(X,Y,5);
      
   }
}


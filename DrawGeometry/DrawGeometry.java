import java.awt.Color;
import java.awt.Event;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class DrawGeometry extends BufferedApplet implements MouseListener,
		MouseMotionListener {
	
	int myX = 200, myY = 200;

	Geometry world = new Geometry();
	Geometry child1 = new Geometry();
	Geometry child2 = new Geometry();
	Geometry child3 = new Geometry();
	
	

	// Geo.add();
	double startTime = System.currentTimeMillis() / 2000.0;

	int M = 10, N = 10;
	boolean isMyMouseDown;

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
	public void init(){
			
		world.CreateSphere();
		child1.CreateSphere();
		child2.CreateSphere();
		child3.CreateSphere();
		addMouseMotionListener(this);
		world.add(child1);
		child1.add(child2);

	}
	
	
	public void mouseDragged(MouseEvent e) {
		myX = e.getX();
		myY = e.getY();
	}

	public void mouseMoved(MouseEvent e) {
		// myX = e.getX();
		// myY = e.getY();
	}

	public void mouseClicked(MouseEvent e) {
		myX = e.getX();
		myY = e.getY();
	}
int w = 0;
int h = 0;
	public void render(Graphics g) {
		
			w = getWidth();
			h = getHeight();
		
		//w = getWidth();
		//h = getHeight();
		g.setColor(Color.white);
		g.fillRect(0, 0, w, h);
		
		

		double time = System.currentTimeMillis() / 2000.0 - startTime;

		// Matrix m = new Matrix(24, 4);
		double angle = time;
		world.mSphere.matrix = world.mSphere.identity((M + 1) * (N + 1));
		world.mSphere.matrix = world.mSphere.scale((M + 1) * (N + 1), 1,
				1, 1);
		world.mSphere.matrix = world.mSphere.translate(1, 1, 0.5, (M + 1)
				* (N + 1));
		world.mSphere.matrix = world.mSphere.rotateX(angle,
				(M + 1) * (N + 1));
		// world.mCylinder.matrix = world.mCylinder.rotateY((double) angle,
		// (M+1)*(N+1));
		// world.mCylinder.matrix = world.mCylinder.rotateZ((double) Math.PI/3,
		// (M+1)*(N+1));

		for (int i = 0; i < (M + 1) * (N + 1); i++) {
			world.SphereMatrix.matrix[i] = world.mSphere.transform(
					world.mSphere.matrix[i], world.SphereMatrix.matrix[i],
					myX + 18, myY + 60);

		}
		
		child1.mSphere.matrix = child1.mSphere.linertransform(world.mSphere.matrix, child1.getMatrix(), (M+1)*(N+1));

		child1.mSphere.matrix = child1.mSphere.identity((M + 1) * (N + 1));
		child1.mSphere.matrix = child1.mSphere.scale((M + 1) * (N + 1),
				1, 1, 1);
		child1.mSphere.matrix = child1.mSphere.translate(2, 2, 2, (M + 1)
				* (N + 1));
		
		
		child1.mSphere.matrix = child1.mSphere.linertransform(world.mSphere.matrix, child1.getMatrix(), (M+1)*(N+1));


		for (int i = 0; i < (M + 1) * (N + 1); i++) {
			child1.SphereMatrix.matrix[i] = child1.mSphere.transform(
					child1.mSphere.matrix[i], child1.SphereMatrix.matrix[i],
					myX + 40, myY-10);

		}
		

		

		child2.mSphere.matrix = child2.mSphere.identity((M + 1) * (N + 1));
		child2.mSphere.matrix = child2.mSphere.scale((M + 1) * (N + 1),
				0.3, 0.3, 0.3);
		child2.mSphere.matrix = child2.mSphere.translate(2, 2, 1, (M + 1)
				* (N + 1));
		//child1.mSphere.matrix = child1.mSphere.rotateX(
		//				Math.PI/2, (M + 1) * (N + 1));
		child2.mSphere.matrix = child2.mSphere.linertransform(child1.mSphere.matrix, child2.getMatrix(), (M+1)*(N+1));


		for (int i = 0; i < (M + 1) * (N + 1); i++) {
			child2.SphereMatrix.matrix[i] = child2.mSphere.transform(
					child2.mSphere.matrix[i], child2.SphereMatrix.matrix[i],
					myX+80, myY+40);

		}
		
		g.setColor(Color.black);

		drawWhole(world,g);


	}
	
	private void drawNode(Geometry world, Graphics g){
		for (int i = 0; i < M * N; i++) {
			// System.out.println(faces[i][1]);
			g.setColor(Color.red);
			g.drawLine((int) world.SphereMatrix.matrix[world.faces[i][0]][0],
					(int) world.SphereMatrix.matrix[world.faces[i][0]][1],
					(int) world.SphereMatrix.matrix[world.faces[i][1]][0],
					(int) world.SphereMatrix.matrix[world.faces[i][1]][1]);

			g.drawLine((int) world.SphereMatrix.matrix[world.faces[i][0]][0],
					(int) world.SphereMatrix.matrix[world.faces[i][0]][1],
					(int) world.SphereMatrix.matrix[world.faces[i][2]][0],
					(int) world.SphereMatrix.matrix[world.faces[i][2]][1]);
			g.setColor(Color.yellow);

			g.drawLine((int) world.SphereMatrix.matrix[world.faces[i][2]][0],
					(int) world.SphereMatrix.matrix[world.faces[i][2]][1],
					(int) world.SphereMatrix.matrix[world.faces[i][3]][0],
					(int) world.SphereMatrix.matrix[world.faces[i][3]][1]);

			g.drawLine((int) world.SphereMatrix.matrix[world.faces[i][0]][0],
					(int) world.SphereMatrix.matrix[world.faces[i][0]][1],
					(int) world.SphereMatrix.matrix[world.faces[i][3]][0],
					(int) world.SphereMatrix.matrix[world.faces[i][3]][1]);

		}
	}
	
	private void drawWhole(Geometry geometry, Graphics g){
		if(geometry != null ){
			drawNode(geometry, g);
		}
		if(geometry.getNumChildren() > 0){
			for(int i = 0; i < geometry.getNumChildren(); i++){
				drawWhole(geometry.getChild(i),g);
			}
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

}

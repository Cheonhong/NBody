import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

public class NBody extends JPanel implements ActionListener{
	public static int number_stars;
	public static double intial_x;
	public static double intial_y;
	public static MyList<CelestialBodies> data;
    
	private static final long serialVersionUID = 1L;	// The version that was used
	
	Timer tm = new Timer(5, this);
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(Color.blue);
		for (int i = 0; i < number_stars; i++) {
			try {
				g.fillOval((int)data.get(i).get_x_coordinate(), (int)data.get(i).get_y_coordinate(), data.get(i).get_size(), 
						data.get(i).get_size());
				intial_x = data.get(i).get_intial_x_direction();	// Getting the x velocity
				intial_y = data.get(i).get_intial_y_direction();	// getting the y velocity
				
				tm.start();
			} catch (Exception e) {
				System.out.println("Error Problem in the PainComponent");
				e.printStackTrace();
			}
		}
	}
	public void addforces(int N) throws Exception {
	    for (int i = 0; i < N; i++) {
	      data.get(i).resetForce();			// reset force for the next iteration
	      for (int j = 0; j < N; j++) {
	        if (i != j) data.get(i).addForce(data.get(j));	// nested for loop to get the force exerted on each other
	      }
	    }
	    for (int i = 0; i < N; i++) {
	    	data.get(i).update();	// update the data within Celestialbodies
	    }
	  }
	public void actionPerformed(ActionEvent e) {
		try {
			for (int i = 0; i < number_stars; i++) {
				data.get(i).set_x_coordinate(data.get(i).get_x_coordinate()+ data.get(i).get_intial_x_direction());
				data.get(i).set_y_coordinate(data.get(i).get_y_coordinate()+ data.get(i).get_intial_y_direction());
			}
			addforces(number_stars);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		repaint();
	}
	public static void animation(MyList<CelestialBodies> d) throws Exception {
		
		NBody t = new NBody();
		JFrame jf = new JFrame();
		jf.setTitle("NBody");
		jf.setSize(768, 768);
		jf.add(t);
		jf.setVisible(true);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	public static void main(String[] args) {
		try {
			File myObj = new File("nbody_input.txt");
			Scanner myReader = new Scanner(myObj);
			
			String data_type = myReader.nextLine();
			System.out.println(data_type);
			
			float scale = myReader.nextFloat();
			System.out.println(scale);
			
			
			Arraylist<String> lines = new Arraylist<String>();		// created a arraylist with each line as elements from the input file
			while(myReader.hasNext()) {
				lines.add(myReader.next());
			}
			
			if (data_type.equals("ArrayList")) {					// choosing the data type from the input file
				data = new Arraylist<CelestialBodies>();
			} else {
				data = new Linkedlist<CelestialBodies>();
			}
			
			int i = 0;
			while(i != lines.size()) {							// serperating the data from the lines and casting them into the right data type to be constructed into Celestial bodies 
				String[] raw_data = lines.get(i).split(",");	// which is then stoed into a arraylist or linkedlist called data.
				data.add(new CelestialBodies(raw_data[0], Double.parseDouble(raw_data[1]), 
						Double.parseDouble(raw_data[2]), Double.parseDouble(raw_data[3]),  // Hence data stores the object Celestialbodies as elements than using data and the methods I can access
						Double.parseDouble(raw_data[4]), Double.parseDouble(raw_data[5]),  // different types of data like mass, x-coordinate and so on.
						Integer.parseInt(raw_data[6]) ));
				i++;
			}
			
			number_stars = lines.size();		// the number of stars that will be modeled
			animation(data);					// animation start
			
			
			
			myReader.close();
		} catch (FileNotFoundException e) {
		      System.out.println("An error occurred.");
		      e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

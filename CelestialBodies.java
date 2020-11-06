import java.awt.Color;
import java.awt.Graphics;

public class CelestialBodies {
	private static final double G = 6.673e-11;   // gravitational constant
	public double fx, fy;
	String name;
	public double mass;
	public double x_coordinate;
	public double y_coordinate;
	public double x_direction;
	public double y_direction;
	int size;

	public CelestialBodies(String n, double d, double x, double y, double x_velocity, double y_velocity, int s) {
		name = n;
		mass = d;
		x_coordinate = x;
		y_coordinate = y;
		x_direction = x_velocity;
		y_direction = y_velocity;
		size = s;
	}
	
	public String toString() {
		String star = "Name=" + name + ", Mass=" + mass + ", x-coordinate="
				+ x_coordinate + ", y-coordinate=" + y_coordinate + ", x-direction = "
				+ x_direction + ", y-direction = " + y_direction + ", Size" + size;
		return star;  
	}
	public void draw(Graphics g) {
		g.setColor(Color.black);
		g.drawOval((int) x_coordinate, (int)y_coordinate, 50, 50);
	}
	public double get_x_coordinate() {
		return x_coordinate;
	}
	public void set_x_coordinate(double x) {
		x_coordinate = x;
	}
	public double get_y_coordinate() {
		return y_coordinate;
	}
	public void set_y_coordinate(double y) {
		y_coordinate = y;
	}
	public int get_size() {
		return size;
	}
	public double get_intial_x_direction() {
		return x_direction;
	}
	public double get_intial_y_direction() {
		return y_direction;
	}
	public void update () {				// update my values with the velocity and coordinates
		x_direction += fx / mass;
		y_direction += fy / mass;
		x_coordinate += x_direction;
		y_coordinate += y_direction;
	}
	public double distanceTo(CelestialBodies b) {
	    double dx = x_coordinate - b.x_coordinate;	// getting the distance between celestial bodies
	    double dy = y_coordinate - b.y_coordinate;
	    return Math.sqrt(dx*dx + dy*dy);
	}
	public void resetForce() {
	    fx = 0.0;		// reset force for the next iteration
	    fy = 0.0;
	}
	public void addForce(CelestialBodies b) {
		CelestialBodies a = this;					// finding the force exerted on this celestial body
	    double P = 3E4; // softening parameter
	    double dx = b.x_coordinate - a.x_coordinate;
	    double dy = b.y_coordinate - a.y_coordinate;
	    double dist = Math.sqrt(dx*dx + dy*dy);
	    double F = (G * a.mass * b.mass) / (dist*dist + P*P);
	    a.fx += F * dx / dist;
	    a.fy += F * dy / dist;
	}
}

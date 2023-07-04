public class NBody{
	public static String background = "images/starfield.jpg";
	
	public static void main(String[] args) {
        double T = Double.parseDouble(args[0]);
		double dt = Double.parseDouble(args[1]);
		String filename = args[2];
		double radius = readRadius(filename);
		Planet[] planets = readPlanets(filename);
		double currentTime = 0;
		
		StdDraw.setScale(-radius, radius);
		StdDraw.enableDoubleBuffering();
		
		while (currentTime < T) {
			double xForces[] = new double[planets.length];
			double yForces[] = new double[planets.length];
			
			for(int index=0; index < planets.length; index++) {
				xForces[index] = planets[index].calcNetForceExertedByX(planets);
				yForces[index] = planets[index].calcNetForceExertedByY(planets);
			}
			for(int index=0; index < planets.length; index++) {
				planets[index].update(dt, xForces[index], yForces[index]);
			}
			
			/* Clears the drawing window. */
			StdDraw.clear();
			/* Draw the background. */
			StdDraw.picture(0, 0, background);
			for(int index=0; index < planets.length; index++) {
				planets[index].draw();
			}
			
			StdDraw.show();
			StdDraw.pause(10);
			currentTime += dt;
		}
		
		StdOut.printf("%d\n", planets.length);
		StdOut.printf("%.2e\n", radius);
		for (int i = 0; i < planets.length; i++) {
			StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                  planets[i].xxPos, planets[i].yyPos, planets[i].xxVel,
                  planets[i].yyVel, planets[i].mass, planets[i].imgFileName);   
}
		
	}
	
	public static double readRadius(String path) {
		In in = new In(path);
		int numOfPlanets = in.readInt();
		double radius = in.readDouble();
		in.close();
		return radius;
	}
	
	public static Planet[] readPlanets(String path) {
		In in = new In(path);
		int numOfPlanets = in.readInt();
		double radius = in.readDouble();
		Planet[] planets = new Planet[numOfPlanets];
		int index = 0;
		while(index < numOfPlanets) {
			double xxPos = in.readDouble();
			double yyPos = in.readDouble();
			double xxVel = in.readDouble();
			double yyVel = in.readDouble();
			double mass = in.readDouble();
			String imgFileName = in.readString();
			planets[index] = new Planet(xxPos, yyPos, xxVel, yyVel, mass, imgFileName);
			index += 1;
		}
		return planets;
	}
}
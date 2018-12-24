public class NBody {
	

public static double readRadius (String fileName)
{
	In in = new In(fileName);
	int num = in.readInt();
	double radius = in.readDouble();
	return radius;
}

public static Planet[] readPlanets (String fileName)
{
	In in = new In(fileName);
	Planet[] allPlanets = new Planet[5];
	int num = in.readInt();
	double radius = in.readDouble();
	for (int i = 0;i<5;i++)
	{
		allPlanets[i]= new Planet(in.readDouble(),in.readDouble(),in.readDouble(),in.readDouble(),in.readDouble(),in.readString());
	}

	return allPlanets;

}

public static void draw_backgrount (double radius)
{
	String background_file = "images/starfield.jpg";
	StdDraw.setScale(-radius,radius);
	StdDraw.clear();
	StdDraw.picture(0,0,background_file);
}

public static void main(String[] args) {

	double T  = Double.parseDouble(args[0]);
	double dt = Double.parseDouble(args[1]);
	String filename = args[2];
	Planet[] allPlanets = readPlanets(filename);
	double radius = readRadius(filename);
	double t = 0.0;

	//StdDraw.enableDoubleBuffering();

	for(;t<T;t+= dt)
	{
		double[] xForces = new double[5];
		double[] yForces = new double[5];
		for(int i=0;i<5;i++)
		{
			xForces[i] = allPlanets[i].calcNetForceExertedByX(allPlanets);
			yForces[i] = allPlanets[i].calcNetForceExertedByY(allPlanets);
		}

		for (int i= 0; i<5; i++)
		{
			allPlanets[i].update(dt,xForces[i],yForces[i]);
		}

		draw_backgrount(radius);
		for(int i = 0;i<5;i++)
		{
			allPlanets[i].draw();
		}

		StdDraw.show(10);
	}

	StdOut.printf("%d\n", allPlanets.length);
	StdOut.printf("%.2e\n", radius);
	for (int i = 0; i < allPlanets.length; i++) {
		StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
   		allPlanets[i].xxPos, allPlanets[i].yyPos, allPlanets[i].xxVel, allPlanets[i].yyVel, allPlanets[i].mass, allPlanets[i].imgFileName);	
}


	
	
}

}

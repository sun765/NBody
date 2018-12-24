public class Planet {

    public double xxPos;      //Its current x position
    public double yyPos;       //Its current y position
    public double xxVel;       //Its current velocity in the x direction
    public double yyVel;       //Its current velocity in the y direction
    public double mass;                   //Its mass
    public String imgFileName;              //The name of an image in the images directory that depicts the planet

    public Planet(double xP, double yP, double xV,
                  double yV, double m, String img) {
        xxPos = xP;
        yyPos = yP;
        xxVel = xV;
        yyVel = yV;
        mass = m;
        imgFileName = img;
    }

    public Planet(Planet p) {
        xxPos = p.xxPos;
        yyPos = p.yyPos;
        xxVel = p.xxVel;
        yyVel = p.yyVel;
        mass = p.mass;
        imgFileName = p.imgFileName;
    }

    public double calcDistance(Planet p){
    	double d;
    	d= (xxPos-p.xxPos)*(xxPos-p.xxPos)+(yyPos-p.yyPos)*(yyPos-p.yyPos);
    	d= java.lang.Math.sqrt(d);
    	return d;
    }

    public double calcForceExertedBy( Planet p)
    {
    	double G = 6.67*Math.pow(10.0,-11.0);
    	double d = this.calcDistance(p);
    	double F = (G*mass*p.mass)/(d*d);
    	return F;
    }

    public double calcForceExertedByX (Planet p)
    {
    	double F = this.calcForceExertedBy(p);
    	double r = this.calcDistance(p);
    	double dx = p.xxPos - xxPos;
    	double Fx= F*(dx/r);
    	return Fx;
    }

       public double calcForceExertedByY (Planet p)
    {
    	double F = this.calcForceExertedBy(p);
    	double r = this.calcDistance(p);
    	double dy = p.yyPos - yyPos;
    	double Fy= F*(dy/r);
    	return Fy;
    }

    	public double calcNetForceExertedByX(Planet[] mix)
    {
    	double NetFx = 0;
    	for (int i = 0; i< mix.length;i++)
    	{
    		if (this.equal(mix[i])!=true) {
    			NetFx += this.calcForceExertedByX(mix[i]);
    		}
    		
    	}
    	return NetFx;

    }


    	public double calcNetForceExertedByY(Planet[] mix)
    {
    	double NetFy = 0;
    	for (int i = 0; i< mix.length;i++)
    	{
    		if (this.equal(mix[i])!=true) {
    			NetFy += this.calcForceExertedByY(mix[i]);
    		}
    		
    	}
    	return NetFy;

    }

    	public boolean equal(Planet p)
    	{
    		if (this == p)
    		{
    			return true;
    		}
    		else{
    			return false;
    		}
    	}

    	public void update (double dt,double fX, double fY)
    	{
    		double ax = fX/this.mass;
    		double ay = fY/this.mass;
    		xxVel = xxVel + ax*dt;
    		yyVel = yyVel + ay*dt;
    		xxPos = xxPos + xxVel*dt;
    		yyPos = yyPos + yyVel*dt;
    	}

}

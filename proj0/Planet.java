public class Planet{
	public double xxPos;
	public double yyPos;
	public double xxVel;
	public double yyVel;
	public double mass;
	public String imgFileName;
	final static double gravitation = 6.67e-11;
	final static String imgPathPrefix = "images/";
	
	public Planet(double xP, double yP, double xV, double yV, double m, String img) {
		this.xxPos = xP;
		this.yyPos = yP;
		this.xxVel = xV;
		this.yyVel = yV;
		this.mass = m;
		this.imgFileName = img;
	}
	public Planet(Planet b) {
		this.xxPos = b.xxPos;
		this.yyPos = b.yyPos;
		this.xxVel = b.xxVel;
		this.yyVel = b.yyVel;
		this.mass = b.mass;
		this.imgFileName = b.imgFileName;
	}
	
	public double calcDistance(Planet b) {
		double deltaY = b.yyPos - this.yyPos;
		double deltaX = b.xxPos - this.xxPos;
		return Math.sqrt(Math.pow(deltaY, 2) + Math.pow(deltaX, 2));
	}
	
	public double calcForceExertedBy(Planet b) {
		double distance = this.calcDistance(b);
		return (Planet.gravitation * this.mass * b.mass) / Math.pow(distance, 2);
	}
	
	public double calcForceExertedByX(Planet b) {
		double deltaX = b.xxPos - this.xxPos;
		double distance = this.calcDistance(b);
		double force = this.calcForceExertedBy(b);
		return force * deltaX / distance;
	} 
	
	public double calcForceExertedByY(Planet b) {
		double deltaY = b.yyPos - this.yyPos;
		double distance = this.calcDistance(b);
		double force = this.calcForceExertedBy(b);
		return force * deltaY / distance;
	}
	
	public double calcNetForceExertedByX(Planet[] planets) {
		double netForceX = 0;
		int index = 0;
		while(index < planets.length){
			if (!this.equals(planets[index])) {
				netForceX += calcForceExertedByX(planets[index]);
			}
			index += 1;
		}
		return netForceX;
	}
	
	public double calcNetForceExertedByY(Planet[] planets) {
		double netForceY = 0;
		int index = 0;
		while(index < planets.length){
			if (!this.equals(planets[index])) {
				netForceY += calcForceExertedByY(planets[index]);
			}
			index += 1;
		}
		return netForceY;
	}
	
	public void update(double dt, double fX, double fY) {
		double aX = fX / this.mass;
		double aY = fY / this.mass;
		this.xxVel += aX * dt;
		this.yyVel += aY * dt;
		this.xxPos += this.xxVel * dt;
		this.yyPos += this.yyVel * dt;
	}
	
	public void draw() {
		//System.out.println(imgPathPrefix + this.imgFileName);
		StdDraw.picture(this.xxPos, this.yyPos, imgPathPrefix + this.imgFileName);
	}

}
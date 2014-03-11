package a5jedi;

public class ColorPixel implements Pixel {
	
	//instance fields
	private double red;
	private double green;
	private double blue;

	//constructor
	public ColorPixel(double red, double green, double blue) {
		this.red = red;
		this.green = green;
		this.blue = blue;
	}
	
	//getters
	public double getRed() {
		return red;
	}

	public double getGreen() {
		return green;
	}

	public double getBlue() {
		return blue;
	}

	public double getBrightness() {
		return 0.2126*red + 0.7152*green + 0.0722*blue;
	}

	//Compare pixels
	public boolean equals(Pixel p) {
		//difference between instance pixel brightness and input pixel brightness
		double difference = p.getBrightness()-this.getBrightness();
		//check that absolute value of difference is less than 0.01 for error
		if(Math.abs(difference)<0.01){
			return true;
		}else{
			return false;
		}
	}

	public char asChar() {
		//series of test cases
		if (getBrightness()<0.1) {
			return ' ';
		}else if(getBrightness()<0.2){
			return '.';
		}else if(getBrightness()<0.3){
			return ':';
		}else if(getBrightness()<0.4){
			return '*';
		}else if(getBrightness()<0.5){
			return '+';
		}else if(getBrightness()<0.6){
			return '?';
		}else if(getBrightness()<0.7){
			return 'X';
		}else if(getBrightness()<0.8){
			return '%';
		}else if(getBrightness()<0.9){
			return '#';
		}else{
			return '@';
		}
	}	
}
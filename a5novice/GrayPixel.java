package a5novice;

public class GrayPixel implements Pixel {

	//instance field
	private double level;

	//constructor
	public GrayPixel(double level) {
		this.level = level;
	}
	
	//getters
	public double getRed() {
		return level;
	}

	public double getGreen() {
		return level;
	}

	public double getBlue() {
		return level;
	}

	public double getBrightness() {
		return level;
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
		if (level<0.1) {
			return ' ';
		}else if(level<0.2){
			return '.';
		}else if(level<0.3){
			return ':';
		}else if(level<0.4){
			return '*';
		}else if(level<0.5){
			return '+';
		}else if(level<0.6){
			return '?';
		}else if(level<0.7){
			return 'X';
		}else if(level<0.8){
			return '%';
		}else if(level<0.9){
			return '#';
		}else{
			return '@';
		}
	}	
}
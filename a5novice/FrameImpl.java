package a5novice;

abstract public class FrameImpl implements Frame{
	
	//declare instance variables
	protected Pixel pixels[][];
	protected int width;
	protected int height;
	protected String title;
	
	//constructor
	public FrameImpl(int width, int height, Pixel init_color, String title) {
		//check that title is not null
		if (title==null) {
			throw new RuntimeException("Title is null");
		}
		
		this.title = title;
		
		//check that width and height are valid values
		if (width < 1 || height < 1) {
			throw new RuntimeException("Illegal dimensions.");
		}

		this.width = width;
		this.height = height;

		//check that input pixel is not null
		if (init_color == null) {
			throw new RuntimeException("Illegal initial pixel: null");
		}
	}
	
	//getters and setters
	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public Pixel getPixel(int x, int y) {
		//check that inputs are legal contingent on the instance's array
		if (x < 0 || x >= getWidth() || y < 0 || y >= getHeight()) {
			throw new RuntimeException("x,y coordinates out of bounds");
		}

		return pixels[y][x];
	}

	public void setPixel(int x, int y, Pixel p) {
		//check that inputs are legal contingent on the instance's array
		if (x < 0 || x >= getWidth() || y < 0 || y >= getHeight()) {
			throw new RuntimeException("x,y coordinates out of bounds");
		}
		
		pixels[y][x] = p;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String new_title) {
		//check if input is not null
		if (new_title==null) {
			throw new RuntimeException("Input is null");
		}
		title = new_title;
	}
	
	//Compare frames
	public boolean equals(Frame f) {
		boolean result = true;
		//check if height and width of input frame match the instance frame
		if ((f.getHeight()==this.getHeight()) && (f.getWidth()==this.getWidth())){
			//iterate across columns
			for(int x=0;x<f.getWidth();x++){
				//iterate down rows
				for(int y=0;y<f.getHeight();y++){
					//check that each pixel in frame is equal to pixel in input frame
					if (f.getPixel(x,y).equals(getPixel(x,y))){
						result = true;
						//keep checking all pixels
						continue;
					}else{
						//if a pixel doesn't match, method returns false
						result = false;
						return false;
					}
				}
			}
			return result;
		}else{
			//if frame dimensions don't match input frame dimensions, method returns false
			result = false;
			return result;
		}
	}
	
	//print name and dimensions of frame
	public String toString(){
		//combine strings and field values, then return string
		String returnString = "Frame: " + title + " (" + width + " x " + height + ")";
		return returnString;
	}
	
	public ColorPixel getAverage() {
		//initialize sums
		double redSum = 0;
		double greenSum = 0;
		double blueSum = 0;
		double size = getWidth() * getHeight();
		//iterate through the matrix
		for(int x=0;x<getWidth();x++){
			for(int y=0;y<getHeight();y++){
				Pixel p = getPixel(x,y);
				redSum += p.getRed();
				greenSum += p.getGreen();
				blueSum += p.getBlue();
			}
		}
		return new ColorPixel((redSum/size),(greenSum/size),(blueSum/size));
	}
	
	public String render() {
		//initialize eventual return value
		String renderedString = "";
		//iterate through matrix horizontally to build strings
		for(int y=0;y<getHeight();y++){
			for(int x=0;x<getWidth();x++){
				renderedString += getPixel(x,y).asChar();
			}
			if(y<getHeight()-1){
				renderedString += '\n';
			}
		}
		return renderedString;
	}
	
	//abstract method declaration
	abstract public GrayFrame[] separate();
}
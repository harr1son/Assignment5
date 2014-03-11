package a5adept;

public class IndirectFrame implements Frame{
	
	//Initialize fields
	private Frame source;
	private int x_offset;
	private int y_offset;
	private int width;
	private int height;
	
	public IndirectFrame(Frame source, int x_offset, int y_offset, int width, int height) throws IllegalArgumentException{
		//test for illegal values
		try{
			if (y_offset>=source.getHeight()||y_offset<0){
				throw new IllegalArgumentException("y offset value is illegal");
			}else if(x_offset>=source.getWidth()||x_offset<0){
				throw new IllegalArgumentException("x offset value is illegal");
			}else if(height>source.getHeight()||height<0){
				throw new IllegalArgumentException("height input value is illegal");
			}else if(width>source.getWidth()||width<0){
				throw new IllegalArgumentException("width input value is illegal");
			}else{
				this.source = source;
				this.x_offset = x_offset;
				this.y_offset = y_offset;
				this.width = width;
				this.height = height;
			}
		} catch (IllegalArgumentException e){
			System.err.println("Caught IllegalArgumentException: "
					+  e.getMessage() + ".");
		}
	}

	public String toString(){
		//combine strings and field values, then return string
		String returnString = "Indirect Frame: " + source.getTitle() + " (" + width + " x " + height + ") at (" + x_offset + ", "+ y_offset + ")";
		return returnString;
	}

	//getters and setters
	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public Pixel getPixel(int x, int y) {
		//check that inputs are legal contingent on the indirect frame's specified fields
		if (x < 0 || x >= getWidth() || y < 0 || y >= getHeight()) {
			throw new RuntimeException("x,y coordinates out of bounds");
		}

		return source.getPixel(x+x_offset,y+y_offset);
	}

	public void setPixel(int x, int y, Pixel p) {
		//check that inputs are legal contingent on the instance's array
		if (x < 0 || x >= getWidth() || y < 0 || y >= getHeight()) {
			throw new RuntimeException("x,y coordinates out of bounds");
		}
				
		source.setPixel(x+x_offset,y+y_offset,p);
	}

	public String getTitle() {
		return source.getTitle();
	}

	public void setTitle(String new_title) {
		source.setTitle(new_title);
	}

	public boolean equals(Frame f) {
		boolean result = true;
		//check if height and width of input frame match the instance frame
		if ((f.getHeight()==this.getHeight()) && (f.getWidth()==this.getWidth())){
			//iterate across columns
			for(int x=0;x<f.getWidth();x++){
				//iterate down rows
				for(int y=0;y<f.getHeight();y++){
					//check that each pixel in frame is equal to pixel in input frame
					if (f.getPixel(x,y).equals(source.getPixel(x_offset+x,y_offset+y))){
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

	public GrayFrame[] separate() {
		if(source instanceof ColorFrame){
			GrayFrame GrayFrameRed = new GrayFrame(width,height);
			GrayFrame GrayFrameGreen = new GrayFrame(width,height);
			GrayFrame GrayFrameBlue = new GrayFrame(width,height);
			
			//iterate across columns
			for(int x=0;x<getWidth();x++){
				//iterate down rows
				for(int y=0;y<getHeight();y++){
					//use getter to get each color value from ColorFrame
					//allocate value to the corresponding pixel in the color correct GrayFrame just created
					Pixel p = source.getPixel(x_offset+x,y_offset+y);
					GrayFrameRed.setPixel(x,y,new GrayPixel(p.getRed()));
					GrayFrameGreen.setPixel(x,y,new GrayPixel(p.getGreen()));
					GrayFrameBlue.setPixel(x,y,new GrayPixel(p.getBlue()));
				}
			}
			//create an array of the GrayFrames after loop has completed, and return the array
			return new GrayFrame[] {GrayFrameRed,GrayFrameGreen,GrayFrameBlue};
		}else{
			//declare one new GrayFrame, because value is same for each color in GrayFrame
			GrayFrame ReturnFrame = new GrayFrame(width,height);
			//copy title so that new GrayFrame is a copy
			ReturnFrame.setTitle(source.getTitle());
			
			//iterate across columns
			for(int x=0;x<getWidth();x++){
				//iterate down rows
				for(int y=0;y<getHeight();y++){
					//use getter to get  encapsulated value from GrayFrame
					//allocate value to the corresponding pixel in the new GrayFrame just created
					Pixel p = source.getPixel(x_offset+x,y_offset+y);
					ReturnFrame.setPixel(x,y,new GrayPixel(p.getRed()));
				}
			}
			//create an array of the ReturnFrame after loop has completed, and return the array
			return new GrayFrame[] {ReturnFrame};
		}
	}

	public ColorPixel getAverage() {
		//initialize sums
		double redSum = 0;
		double greenSum = 0;
		double blueSum = 0;
		double size = getWidth() * getHeight();
		//iterate through the matrix
		for(int x=x_offset;x<x_offset+getWidth();x++){
			for(int y=y_offset;y<y_offset+getHeight();y++){
				Pixel p = source.getPixel(x,y);
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
		for(int y=y_offset;y<y_offset+getHeight();y++){
			for(int x=x_offset;x<x_offset+getWidth();x++){
				renderedString += source.getPixel(x,y).asChar();
			}
			if(y<y_offset+getHeight()-1){
				renderedString += '\n';
			}
		}
		return renderedString;
	}

	public IndirectFrame crop(int x, int y, int width, int height) {
		return new IndirectFrame(this.source,this.x_offset+x,this.y_offset+y,width,height);
	}
}
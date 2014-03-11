package a5novice;

public class ColorFrame extends FrameImpl {

	//class field specific to ColorFrame declared
	private static final Pixel DEFAULT_PIXEL_VALUE = new ColorPixel(0.5, 0.5, 0.5);
	
	//constructor
	public ColorFrame(int width, int height, Pixel init_color, String title) {
		//chain constructor to superclass' constructor
		super(width,height,init_color,title);
				
		pixels = new ColorPixel[height][width];
		//iterate across columns
		for (int x = 0; x<width; x++) {
			//iterate down rows
			for (int y = 0; y<height; y++) {
				//allocate specific pixel to location in array
				pixels[y][x] = init_color;
			}
		}
	}
	
	public ColorFrame(int width, int height) {
		//chain constructor
		this(width, height, ColorFrame.DEFAULT_PIXEL_VALUE, "untitled");
	}

	public void setPixel(int x, int y, Pixel p) {
		//check that p "is-a" ColorPixel
		if (!(p instanceof ColorPixel)) {
			throw new RuntimeException("ColorFrame can only accept ColorPixel");
		}
		//chain to superclass' version of method
		super.setPixel(x, y, p);
	}

	//separating RGB values
	public GrayFrame[] separate() {
		//declare three new GrayFrames, one for each color value
		GrayFrame GrayFrameRed = new GrayFrame(width,height);
		GrayFrame GrayFrameGreen = new GrayFrame(width,height);
		GrayFrame GrayFrameBlue = new GrayFrame(width,height);
		
		//iterate across columns
		for(int x=0;x<getWidth();x++){
			//iterate down rows
			for(int y=0;y<getHeight();y++){
				//use getter to get each color value from ColorFrame
				//allocate value to the corresponding pixel in the color correct GrayFrame just created
				Pixel p = this.getPixel(x,y);
				GrayFrameRed.setPixel(x,y,new GrayPixel(p.getRed()));
				GrayFrameGreen.setPixel(x,y,new GrayPixel(p.getGreen()));
				GrayFrameBlue.setPixel(x,y,new GrayPixel(p.getBlue()));
			}
		}
		//create an array of the GrayFrames after loop has completed, and return the array
		return new GrayFrame[] {GrayFrameRed,GrayFrameGreen,GrayFrameBlue};
	}
}
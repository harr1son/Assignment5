package a5jedi;

public class GrayFrame extends DirectFrame {

	//class field specific to GrayFrame declared
	private static final Pixel DEFAULT_PIXEL_VALUE = new GrayPixel(0.5);
	
	//constructor
	public GrayFrame(int width, int height, Pixel init_color, String title) {
		//chain constructor to superclass' constructor
		super(width,height,init_color,title);
		
		pixels = new GrayPixel[height][width];
		//iterate across columns
		for (int x = 0; x<width; x++) {
			//iterate down rows
			for (int y = 0; y<height; y++) {
				//allocate specific pixel to location in array
				pixels[y][x] = init_color;
			}
		}
	}
	
	public GrayFrame(int width, int height) {
		//chain constructor
		this(width, height, DEFAULT_PIXEL_VALUE, "untitled");
	}

	public void setPixel(int x, int y, Pixel p) {
		//check that p "is-a" GrayPixel
		if (!(p instanceof GrayPixel)) {
			throw new RuntimeException("GrayFrame can only accept GrayPixel");
		}
		//chain to superclass' version of method
		super.setPixel(x, y, p);
	}

	//separating RGB values
	public GrayFrame[] separate() {
		//declare one new GrayFrame, because value is same for each color in GrayFrame
		GrayFrame ReturnFrame = new GrayFrame(width,height);
		//copy title so that new GrayFrame is a copy
		ReturnFrame.setTitle(title);
		
		//iterate across columns
		for(int x=0;x<getWidth();x++){
			//iterate down rows
			for(int y=0;y<getHeight();y++){
				//use getter to get  encapsulated value from GrayFrame
				//allocate value to the corresponding pixel in the new GrayFrame just created
				Pixel p = this.getPixel(x,y);
				ReturnFrame.setPixel(x,y,new GrayPixel(p.getRed()));
			}
		}
		//create an array of the ReturnFrame after loop has completed, and return the array
		return new GrayFrame[] {ReturnFrame};
	}
}
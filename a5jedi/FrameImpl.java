package a5jedi;

abstract public class FrameImpl implements Frame {
	
	//declare fields
	protected int width;
	protected int height;
	
	//getters and settters
	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}
	
	public void setPixel(int x, int y, Pixel p) {
		//check that inputs are legal contingent on the instance's array
		if (x < 0 || x >= width || y < 0 || y >= height) {
			throw new RuntimeException("x,y coordinates out of bounds");
		}
	}
	
	//abstract methods
	abstract public Pixel getPixel(int x, int y);
	
	abstract public String toString();
	
	abstract public String getTitle();

	abstract public void setTitle(String new_title);

	abstract public boolean equals(Frame f);

	abstract public GrayFrame[] separate();

	abstract public ColorPixel getAverage();

	abstract public String render();

	abstract public IndirectFrame crop(int x, int y, int width, int height);
	
	//makeTiles
	public IndirectFrame[][] makeTiles(int num_across, int num_down) {
		//instantiate variables
		IndirectFrame[][] returnFrame = new IndirectFrame[num_across][num_down];
		int tileWidth = width/num_across;
		int tileHeight = height/num_down;
		int widthRemainder=width%num_across;
		int heightRemainder=height%num_down;
		//if no remainders
		if(widthRemainder==0&&heightRemainder==0){
			for(int x=0;x<num_across;x++){
				for(int y=0;y<num_down;y++){
					//use * operator in the crop constructor to increment tile position
					returnFrame[x][y]=this.crop(x*tileWidth, y*tileHeight, tileWidth, tileHeight);
				}
			}
		//if remainders exist	
		}else{
			//newTileHeight contingent on if tile is delegated remainders
			int newTileHeight;
			int newTileWidth;
			int x_index=0;
			for(int x=0;x<num_across;x++){
				int y_index=0;
				//check for if the tile will be one with remainder width
				if(x<widthRemainder){
					newTileWidth = tileWidth+1;
				}else{
					newTileWidth = tileWidth;
				}
				for(int y=0;y<num_down;y++){
					//check for if the tile will be one with remainder length
					if(y<heightRemainder){
						newTileHeight = tileHeight+1;
					}else{
						newTileHeight = tileHeight;
					}
					returnFrame[x][y]=this.crop(x_index, y_index, newTileWidth, newTileHeight);
					//increment tile position
					y_index+=newTileHeight;
				}
				//increment tile position
				x_index+=newTileWidth;
			}
		}
		return returnFrame;
	}
}

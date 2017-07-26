//used to distinguish between the different types of user interfaces
public enum ViewMode{
	TUI(0), GUI1(1), GUI2(2);
	
	private int value;
	
	private ViewMode(int v){
		value = v;
	}
	
	public int getValue(){
		return value;
	}
}

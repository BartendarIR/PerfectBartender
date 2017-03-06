package Backend;

public class Sets implements Comparable<Sets>{
	private Data d;
	private int i;
	public Sets(Data d, int i){
		this.d=d;
		this.i=i;
	}
	@Override
	public int compareTo(Sets arg0) {
		return arg0.i-this.i ;
		
	}
	public int getValue() {
		// TODO Auto-generated method stub
		return this.i;
	}
	public Data getData() {
		// TODO Auto-generated method stub
		return this.d;
	}
}


public class block {
	private String str;
	private int index;
	public block() {
	}
	public block(String s, int i) {
		str = s;
		index = i;
	}
	
	public String getblock() {
		String temp = (str + " , " + index);
		
		return temp;
	}
	public int get_index() {
		return index;
	}
	
	public String get_str() {
		return str;
	}

}

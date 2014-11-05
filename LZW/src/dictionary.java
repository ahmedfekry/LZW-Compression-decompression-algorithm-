
public class dictionary {
	
	private block dictionary[] = new block[265];
	private int size = 0 ; 	
	public dictionary() {
	}
	public void add(block obj) {
		dictionary[size] = obj;
		size++;
	}

	public block get_block(int index) {
		return dictionary[index];
	}
	
	public int  search(String sub) {
		
		for(int i=0; i < size; ++i) {
			String blk = dictionary[i].getblock();
			if(blk.contains(sub))
				return i;
		}
		return 0;
	}
	public String search_by_index(int index) {
		int x;
		for(int i=0; i < size; ++i) {
			if(dictionary[i].get_index() == index)
				return dictionary[i].get_str();
		}
		return "";
	}
	
}

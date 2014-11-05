
import java.util.Arrays;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
public class LZW {
	private String input_string; //string in file 
	private String current_string; 
	private char  input_char;	
	//////////
	//initial dictionary
	private char[] charctr ; //= new char[100]; //unique sorted array
	private int[] char_code ; //= new int[100]; // initial value for each charcter
	/////////
	
	private int[] encoded_output; // = new int[100]; //array of index 
	private dictionary dict = new dictionary(); // array of blocks
	private block bloc = new block(); // block (substring , index)
	private int buffersize ; //size of initial dictionary
	public LZW() {
	}
	public LZW(String is) {
		input_string = is;
		current_string = "";
		
		String rs = removeDuplicates(is);
		charctr = rs.toCharArray();
		Arrays.sort(charctr);
		buffersize = charctr.length;
		char_code = new int[buffersize];
		for(int i = 0; i < buffersize; ++i) {
			char_code[i] = i;
		}
		for(int i=0; i < buffersize; ++i) {
			String tempstr = "";
			tempstr += charctr[i];
			block temp = new block(tempstr,char_code[i]);
			System.out.println(temp.getblock());
			dict.add(temp);	
		}
		System.out.println("/////////////////////////////");
		System.out.print("buffer size = ");
		System.out.println(buffersize);
		encoded_output = new int[100];
		
	}
	
	
	public void compression(){
		 
		try {
			File out1 = new File("/home/ahmed/FCI/java/LZW/src/output.txt");
			if(!out1.exists()) {
				System.out.println("File is Not exist");
				out1.createNewFile();
			}
			FileWriter out2 = new FileWriter(out1.getAbsoluteFile());
			BufferedWriter out = new BufferedWriter(out2);
			int i = 0,in = 0;
			int index = 0;
//			block tempblk;
			System.out.println(input_string);
			System.out.println(input_string.length());
			
			while(i < input_string.length()) {
//				String sub;
				input_char = input_string.charAt(i);
				String temp = current_string + input_char; 
				System.out.print("temp = ");
				System.out.println(temp);
				int q = dict.search(temp); 
				if( q > 0){
					System.out.print("q = ");
					System.out.println(q);
					/////////
					System.out.print(temp);
					System.out.println(" is seen before");
					///////
					      bloc = dict.get_block(q);
					//////
					System.out.println(bloc.getblock());
					/////
					      index = bloc.get_index();
					      current_string += input_char;
					
//					encoded_output[in] = index;
//					in++;
					///////
					System.out.println(index);
					//////
				}
				else {
					block temp1 = new block(temp,buffersize);
					encoded_output[in] = dict.search(current_string);
					in++;
					dict.add(temp1);
					//////////
					System.out.print("this block is added to the dictionary : ");
					System.out.println(temp1.getblock());
					/////////

					System.out.print("current_string = ");
					System.out.println(current_string);
						
					current_string = "";
					current_string += input_char;
					/////////
					System.out.print("current_string = ");
					System.out.println(current_string);
					////////
					buffersize++;
					
				}
				
				i++;
				System.out.println("----------");
			}
//			System.out.println(in);
			for(int q=0; q < in; ++q){
				int c = encoded_output[q];
				System.out.println(c);
				 c = (char) encoded_output[q];
				out.write(String.valueOf(c));
				out.newLine();
				out.flush();
			}	
			out.close();
//			out.write(encoded_output);
			
		}catch(IOException e) {
			e.printStackTrace();
			
		}
		
		for(int i=0; i < input_string.length(); ++i) {
			System.out.println(dict.get_block(i).getblock());
		}
		System.out.println("//////////////////////////////////");
	}
	
	public void decompression() {
		for(int i=0; i < input_string.length(); ++i) {
			System.out.print(dict.search_by_index(encoded_output[i]));
		}
	}

	private static String removeDuplicates(String s) {
	    StringBuilder noDupes = new StringBuilder();
	    for (int i = 0; i < s.length(); i++) {
	        String si = s.substring(i, i + 1);
	        if (noDupes.indexOf(si) == -1) {
	            noDupes.append(si);
	        }
	    }
	    return noDupes.toString();
	}

}
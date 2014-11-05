import java.util.*;
import java.io.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Main {
	
	public static void main(String[] args) throws IOException
	{

		
		
		FileInputStream inp = null;
		String input = "";
		try{
			
			inp = new FileInputStream("/home/ahmed/FCI/java/LZW/src/input.txt");
			char c;
			while((inp.available()) > 0) {
				c = (char) inp.read();
				input += c;
				
			}
			
			System.out.print(input);
			LZW obj = new LZW(input);
		
			obj.compression();
			System.out.println();
			obj.decompression();
		}finally{
			if (inp != null) {
	            inp.close();
	         }			
		}
//		System.out.println(input);
	}
}

import java.io.*;
import java.util.*;

public class TreeTest
{
	
	public static void main( String args[] ){
		TreeSet<String> uses = new TreeSet<String>();
		int i = 10;
		int j = 9;
		int k = 0;
		int p = 0;

		uses.add(String.format("%1$07d",i) + "tut");

		uses.add(String.format("%1$07d",j) + "ted");
		uses.add(String.format("%1$07d",k) + "tab");

		uses.add(String.format("%1$07d",p) + "taa");


		System.out.println(uses);
	}


}
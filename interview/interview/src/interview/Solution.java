package interview;

import java.util.ArrayList;

public class Solution {
	public static void main (String[] args){
		ArrayList<Integer> input = new ArrayList<Integer>();
		input.add(3);
		input.add(3);
		String input2 = new String("ab");
		
		Solution solution = new Solution();
		solution.here(input);
		for (int re : input){
			System.out.println(re);
		}
		solution.there(input2);
		System.out.println(input2);
	}
	
    public void here(ArrayList<Integer> input){
    	input = new ArrayList<Integer>();
    	input.add(5);
    }
    public void there(String input2){
    	input2= "abc";
    }
}
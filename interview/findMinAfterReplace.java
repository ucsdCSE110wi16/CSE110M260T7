import java.io.*;
import java.util.Scanner;

class findMinAfterReplace
{
    public static void main (String[] args) throws java.lang.Exception
    {
        Scanner in = new Scanner(System.in);
        int num = in.nextInt();

        System.out.println(Solution(num));
    }
    
    public static int Solution(int S){
        
        String stringNum = Integer.toString(S);
        if (stringNum.length() == 1) {
            return S;
        }
        
        int minNum = Integer.MAX_VALUE;
        for (int i=0; i<stringNum.length()-1;i++){
            int newNum = Integer.parseInt(
                stringNum.substring(0,i) +
                Integer.toString(Math.max(Integer.parseInt(stringNum.charAt(i)+""), Integer.parseInt(stringNum.charAt(i+1)+""))) +
                stringNum.substring(i+2)               
            );
            minNum = Math.min(minNum, newNum);
        }
        return minNum;
    }
}
package tbd;

public class print {
	static class PrintDemo{

		public static void recursivefun(int n) { 
			
			String a ="abc";
			String b ="abcd";
			
			if(a.equals(b))
			if(n <= 10) {
				System.out.println(n); 
				recursivefun(n+1);   }
		}
		public static void main(String args[]) {
			recursivefun(1); 
		}
	}

}

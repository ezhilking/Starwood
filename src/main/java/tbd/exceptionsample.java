package tbd;

public class exceptionsample {

	public static void main(String[] args) {
		try{
			f();
		}catch(Exception e){
			System.out.println(e);
		}
	}

	public static void f() {
		try{
			int x,y;
			x=9;y=0;

			int u = x/y;
			System.out.println(u);
		}catch(ArithmeticException e){
			
			System.out.println(e);
			throw e;
		}catch(Exception e){
			throw e;
		}
	}
}

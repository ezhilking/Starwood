package tbd;

public class factorial {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
fact(5);
	}
	
	public static void fact(int n){
		int fact=1;
		for(int i=1;i<=n;i++){
			fact=fact*i;
		}
		System.out.println(fact);
	}

}

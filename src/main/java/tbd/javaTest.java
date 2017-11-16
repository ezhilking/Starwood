package tbd;

public class javaTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		try{
		System.out.println ("starting loop:");
		for (int m = 0;m < 2; ++m)
		if(m==1)
			continue;
		for (int n = 0; n < 7; ++n)
		{
		    System.out.println ("in loop: " + n);
		    if (n == 2) {
		        continue;
		    }
		    System.out.println ("   survived first guard");
		    if (n == 4) {
		        break;
		    }
		    System.out.println ("   survived second guard");
		    // continue at head of loop
		}
//		break;
//		continue;
		// break out of loop
		System.out.println ("end of loop or exit via break");
//	}catch(Exception e){
		
	}finally{
		
	}
}
}


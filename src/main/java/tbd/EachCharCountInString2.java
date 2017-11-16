package tbd;

import java.util.HashMap;

class EachCharCountInString2
{
	static void characterCount(String inputString)
	{
		HashMap<Character, Integer> f= new HashMap<>();
		char[] c = inputString.toCharArray();
		for(char v:c){
			if(f.containsKey(v)){
				f.put(v, f.get(v)+1);
			}else{
				f.put(v, 1);
			}


		}
		
		System.out.println(f);







		//Creating a HashMap containing char as a key and occurrences as  a value

		HashMap<Character, Integer> charCountMap = new HashMap<Character, Integer>();

		//Converting given string to char array

		char[] strArray = inputString.toCharArray();

		//checking each char of strArray

		for (char c1 : strArray)
		{
			if(charCountMap.containsKey(c1))
			{
				//If char is present in charCountMap, incrementing it's count by 1
 
				charCountMap.put(c1, charCountMap.get(c1)+1);
			}
			else
			{
				//If char is not present in charCountMap,
				//putting this char to charCountMap with 1 as it's value

				charCountMap.put(c1, 1);
			}
		}

		//Printing the charCountMap

		System.out.println(charCountMap);
	}

	public static void main(String[] args)
	{
		characterCount("aaab     ccgg");

		characterCount("All Is Well");

		characterCount("Done And Gone");
	}
}
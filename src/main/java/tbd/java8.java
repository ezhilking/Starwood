package tbd;

import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.formula.functions.Match;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;

public class java8 {

	public static void main(String[] args) {

		List<String> list = new ArrayList();
	

		list.add("Apple");
		list.add("Orange");
		list.add("Grape");

		for(String item:list){
			System.out.println(item);
		}

		list.forEach(item -> System.out.println(item));

//		Select select=new Select(driver.findElement(By.id("selectId")));

		//Validating drop-down option without For Loop
//		select.getOptions().stream().anyMatch(item->item.getText().substring(1, 2));

	}

}

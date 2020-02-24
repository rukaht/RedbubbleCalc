package redbubbleCalc;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;


public class RedbubbleCalc {

	public static void main(String[] args) throws FileNotFoundException, IOException {
		// TODO Auto-generated method stub
		
		if(args.length <2) {
			System.out.println("Invalid Arguments");
			System.exit(1);
		}
		
		String basePriceFile=args[0];
		String cartFile=args[1];
		
		File cFile = new File(cartFile);
		
		if(!cFile.exists()) {
			System.out.println("Cart file not found");
			System.exit(1);
		}
		
		File bPriceFile = new File(basePriceFile);
		
		if(!bPriceFile.exists()) {
			System.out.println("Base price file not found");
		}
		
		//mapping the json files
		
		ObjectMapper mapper = new ObjectMapper();
		
		BasePrice[] basePrice = mapper.readValue(bPriceFile, BasePrice[].class);
		
		ProductCart[] productCart = mapper.readValue(cFile, ProductCart[].class);
		
		PriceCalculator calculator=new PriceCalculator();
		calculator.initializeBasePrice(basePrice);
		
		Double total=calculator.calculateTotal(productCart);
		
		System.out.println("Total Price:"+total);

	}

}

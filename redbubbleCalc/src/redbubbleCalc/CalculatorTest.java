/**
 * 
 */
package redbubbleCalc;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;

import org.junit.Assert;
import org.junit.Test;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author rukahtnibarp
 *
 */
public class CalculatorTest {

	@Test
	public void test() throws JsonParseException, JsonMappingException, IOException {
		
		File basePriceFile = new File("src/redbubbleCalc/base-prices.json");
		File cartFile=new File("src/redbubbleCalc/cart-9363.json");
		
		
		PriceCalculator calc=new PriceCalculator();
		
		ObjectMapper mapper = new ObjectMapper();
		
		BasePrice[] basePrice = mapper.readValue(basePriceFile, BasePrice[].class);
		
		ProductCart[] productCart = mapper.readValue(cartFile, ProductCart[].class);
		calc.initializeBasePrice(basePrice);
		
		Double total=calc.calculateTotal(productCart);
		
			//assertEquals(9500.00,(double)total);//(9500.0,total);
			
		assertEquals(9363, total,0);
	}

}

package redbubbleCalc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;


public class PriceCalculator {
	
	Map<String, ArrayList<BasePrice>> mapBasePrice;
	
	public PriceCalculator(){
		this.mapBasePrice = new HashMap<>();
	}
	
	//initialize the map for easier searching
	public void initializeBasePrice(BasePrice[] basePrice) {
		
		for(int i=0; i< basePrice.length;i++) {
			String type=basePrice[i].productType;
			
			if(this.mapBasePrice.containsKey(basePrice[i].productType)) {
				this.mapBasePrice.get(type).add(basePrice[i]);
			}
			else {
				ArrayList<BasePrice> prc=new ArrayList<>();
				prc.add(basePrice[i]);
				
				this.mapBasePrice.put(basePrice[i].getProductType(), prc);
				
			}
			
		}
		
	}
	
	public Double calculateTotal(ProductCart[] products) {
		Double total=0.0;
		for(ProductCart item: products) {
			ArrayList<BasePrice> prdList=this.mapBasePrice.get(item.getProductType());
			
			
			// predicate to compare the producttype name between the base price and the cart item
			Predicate<BasePrice> productEqualsPredicate=p->p.getProductType().equalsIgnoreCase(item.getProductType());
			
			// this array stores the list to predicates that will be applied to hte filter
			ArrayList<Predicate> optionsPredicates =new ArrayList<>();
			optionsPredicates.add(productEqualsPredicate);
			
			//the check is performed so  that is does not fail to 
			if(prdList.size()>0) { // or this might not be required
				//since the first object in the baseprice of a product option determines the structure of the successding base prices, taking first as sample
				BasePrice bp=prdList.get(0);
				
				//get set of Keys only of the key value pair for the Baseoptions and the item in the cart
				
				// probaly need need to check if the options are empty
				// if the options is empty skip this process
				
				// short circuit check if either of them is empty
				
				if(!bp.getOptions().isEmpty() && !item.getOptions().isEmpty()) {
					Set<String> baseOptions=bp.getOptions().keySet();
					Set<String> productOptions=item.getOptions().keySet();
					baseOptions.retainAll(productOptions); //baseoptions retains only the intersections of the key between the two sets
					
					//loop through each keys pair that is present to create a predicate that can be used to apply the filter to get the appropriate base price for the item 
					// product type, options can be considered the unique values that can be used to compare the base prices values
					for(String optionItem:baseOptions) {
						//create a predicate the applies checks the value in the product option value in the list of the base price list to bet the appropriate base price
						Predicate<BasePrice> predBp=p->Arrays.asList(p.getOptions().get(optionItem)).contains(item.getOptions().get(optionItem));
						optionsPredicates.add(predBp); // add to the list of predicates 
					}
				}
			}
			
			//applies the list of predicates to the collection of basePrices for the specific item
			// The predicates are merged using "AND" logic  via code "optionsPredicates.stream().reduce(Predicate::and).orElse(x->true)" in which 
			//reduction i.e. reduce() is applied that provided the final state of the  filter that is applied in the resulting set
			//A reduction was done since ANDing three  predicates did not yeild a same result as expected.
			// the resultant list is the list of base price that should only have a single record. If multiple values are foudn then either the base prices had some common items 
			// or the filter has failed to work and needs to be tweaked
			List<BasePrice> lst=(List<BasePrice>)prdList.stream().filter(optionsPredicates.stream().reduce(Predicate::and).orElse(x->true)).collect(Collectors.toList());
			
			
			int size=lst.size();
			
			if(size ==1 ) { // success only when a single value is in the list of returned prices
				BasePrice itemPrice=lst.get(0); // get first item in the list since it only has one item
				total=total + (itemPrice.getBasePrice() + Math.round(itemPrice.basePrice * item.artistMarkup/100))* item.getQuantity();
				//System.out.println(itemPrice.getProductType()+":"+total);
			}else if(size >1){ //multiple prices
				System.out.println("Multiple prices found");
			}else {
				System.out.println("Price not found");
			}

		}
		return total;
	}
	
}

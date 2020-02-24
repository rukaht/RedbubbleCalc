package redbubbleCalc;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;

public class BasePrice {
	
	@JsonProperty("product-type")
	public String productType;
	
	@JsonProperty("options")
	//public HashMap<String,String> options;
    public HashMap<String,String[]> options;

	@JsonProperty("base-price")
	public Integer basePrice;

	public String getProductType() {
		return productType;
	}

	public void setProductType(String productType) {
		this.productType = productType;
	}
	
	
	public HashMap<String, String[]> getOptions() {
		return options;
	}

	public void setOptions(HashMap<String, String[]> options) {
		this.options = options;
	}

	public Integer getBasePrice() {
		return basePrice;
	}

	public void setBasePrice(Integer basePrice) {
		this.basePrice = basePrice;
	}
	
	
	

}

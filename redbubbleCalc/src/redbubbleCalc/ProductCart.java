package redbubbleCalc;

import java.util.HashMap;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ProductCart {
	
	@JsonProperty("product-type")
	public String productType;
	
	@JsonProperty("options")
	public HashMap<String,String> options;
	
	@JsonProperty("artist-markup")
	public Double artistMarkup;
	
	@JsonProperty("quantity")
	public Integer quantity;

	public String getProductType() {
		return productType;
	}

	public void setProductType(String productType) {
		this.productType = productType;
	}

	public HashMap<String, String> getOptions() {
		return options;
	}

	public void setOptions(HashMap<String, String> options) {
		this.options = options;
	}

	public Double getArtistMarkup() {
		return artistMarkup;
	}

	public void setArtistMarkup(Double artistMarkup) {
		this.artistMarkup = artistMarkup;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	

}

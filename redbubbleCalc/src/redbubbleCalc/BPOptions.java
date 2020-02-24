package redbubbleCalc;

import com.fasterxml.jackson.annotation.JsonProperty;

public class BPOptions {
	
	@JsonProperty("colour")
	public String[] color;
	
	@JsonProperty("size")
	public String[] size;

	public String[] getColor() {
		return color;
	}

	public void setColor(String[] color) {
		this.color = color;
	}

	public String[] getSize() {
		return size;
	}

	public void setSize(String[] size) {
		this.size = size;
	}

}

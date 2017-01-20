package com.jpassion.domain;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "line-item")
public class LineItem {
	
	protected String product;
	protected String cost;

	public String getProduct() {
		return product;
	}

	public void setProduct(String product) {
		this.product = product;
	}

	public String getCost() {
		return cost;
	}

	public void setCost(String cost) {
		this.cost = cost;
	}
}

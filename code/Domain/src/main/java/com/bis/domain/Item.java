package com.bis.domain;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

public class Item implements java.io.Serializable {

	private Integer itemCode;
	private String itemName;
	private String description;
	private char itemLife;
	private char returnable;

	public Item() {
	}

    public Item(Integer itemCode) {
        this.itemCode = itemCode;
    }

    public Item(String itemName, String description, char itemLife,char returnable) {
		this.itemName = itemName;
		this.description = description;
		this.itemLife = itemLife;
		this.returnable = returnable;
	}

	public Integer getItemCode() {
		return this.itemCode;
	}

	public void setItemCode(Integer itemCode) {
		this.itemCode = itemCode;
	}

	public String getItemName() {
		return this.itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public char getItemLife() {
		return this.itemLife;
	}

    public String getItemLifeDescription() {
        return ItemCycle.getNameByCode(this.itemLife);
	}

	public void setItemLife(char itemLife) {
		this.itemLife = itemLife;
	}

	public char getReturnable() {
		return this.returnable;
	}

    public String getReturnableDescription() {
		return ItemReturnType.getNameByCode(this.returnable);
	}

	public void setReturnable(char returnable) {
		this.returnable = returnable;
	}

    @Override
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this);
    }

    @Override
    public boolean equals(Object obj) {
        return EqualsBuilder.reflectionEquals(this,obj);
    }
}

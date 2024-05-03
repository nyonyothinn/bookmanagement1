package bookmanagementsystem.models;

import java.util.List;

import javax.validation.constraints.NotEmpty;


import org.hibernate.validator.constraints.Range;

public class BookBean {
	@NotEmpty
	private String code;
	@NotEmpty
	private String name;
	
	private int author_id;
	private String author_name;
	

	@Range(min=1,max=50000)
	private double price;
	
	public BookBean(){		
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	public int getAuthor_id() {
		return author_id;
	}

	public void setAuthor_id(int author_id) {
		this.author_id = author_id;
	}
	public String getAuthor_name() {
		return author_name;
	}

	public void setAuthor_name(String author_name) {
		this.author_name = author_name;
	}
	

	

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}
	
	
}

package pojo;

import java.io.Serializable;

public class Book implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String name;
	private String isbn;
	private String category;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public Book(Integer id, String name, String isbn, String category) {
		super();
		this.id = id;
		this.name = name;
		this.isbn = isbn;
		this.category = category;
	}

	public Book() {
		super();
	}
}

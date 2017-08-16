package jp.co.rakus.stockmanagement.web;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.web.multipart.MultipartFile;

public class RegisterBookForm {

	/** 書籍名 **/
	@NotBlank(message = "値を入力してください")
	private String name;
	
	/** 著者 **/
	@NotBlank(message = "値を入力してください")
	private String author;
	
	/** 出版社 **/
	@NotBlank(message = "値を入力してください")
	private String publisher;
	
	/** 価格 **/
	@NotBlank(message = "値を入力してください")
	private String price;
	
	/** ISBNコード **/
	@NotBlank(message = "値を入力してください")
	@Pattern(message = "ISBNコードの入力形式が不正です。", regexp = "[A-Za-z0-9]-[A-Za-z0-9][A-Za-z0-9][A-Za-z0-9][A-Za-z0-9][A-Za-z0-9]-[A-Za-z0-9][A-Za-z0-9][A-Za-z0-9][A-Za-z0-9]-[A-Za-z0-9]")
	private String isbncode;
	
	/** 発売日 **/
	@NotBlank(message = "値を入力してください")
	@Pattern(message = "発売日の入力形式が不正です。", regexp = "[1-2][0-9][0-9][0-9]-[0-1][0-9]-[0-3][0-9]")
	private String saledate;
	
	/** 説明 **/
	@NotBlank(message = "値を入力してください")
	private String explanation;
	
	/** 発売日 **/
	private MultipartFile imageFile;
	
	/** 在庫数 **/
	@NotNull(message = "値を入力してください")
	private Integer stock;
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getPublisher() {
		return publisher;
	}
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getIsbncode() {
		return isbncode;
	}
	public void setIsbncode(String isbncode) {
		this.isbncode = isbncode;
	}
	public String getSaledate() {
		return saledate;
	}
	public void setSaledate(String saledate) {
		this.saledate = saledate;
	}
	public String getExplanation() {
		return explanation;
	}
	public void setExplanation(String explanation) {
		this.explanation = explanation;
	}
	public MultipartFile getImageFile() {
		return imageFile;
	}
	public void setImageFile(MultipartFile imageFile) {
		this.imageFile = imageFile;
	}
	public Integer getStock() {
		return stock;
	}
	public void setStock(Integer stock) {
		this.stock = stock;
	}


	
}

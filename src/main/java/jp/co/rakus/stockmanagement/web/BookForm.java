package jp.co.rakus.stockmanagement.web;

import javax.validation.constraints.NotNull;

/**
 * 書籍関連のリクエストパラメータが入るフォーム.
 * @author igamasayuki
 *
 */
public class BookForm {
	/** id  */
    @NotNull
	private Integer id;
	/** 在庫  */
    @NotNull(message = "値を入力してください")
    private String stock;
    
    private String name;
    private String author;
    private String publisher;
    private String price;
    private String isbncode;
    private String saledate;
    private String explanation;
    private String pubexplanationisher;
    private String image;
    
    
    
    
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
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
	public String getPubexplanationisher() {
		return pubexplanationisher;
	}
	public void setPubexplanationisher(String pubexplanationisher) {
		this.pubexplanationisher = pubexplanationisher;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getStock() {
		return stock;
	}
	public Integer getIntStock() {
		return Integer.parseInt(stock);
	}
	public void setStock(String stock) {
		this.stock = stock;
	}
    
    
}

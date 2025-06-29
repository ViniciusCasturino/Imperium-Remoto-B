package br.edu.atitus.product_service.dtos;

import jakarta.persistence.*;

@Entity
@Table(name = "tb_product")
public class ProductDTO {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String description_product;
	private String brand_product;
	private String model_product;
	private double price_product;
	private String currency_product;
	private Integer stock_product;

	@Column(name = "image_url")
	private String imageUrl;
	
	@Transient
	private String enviroment;
	@Transient
	private double convertedPrice;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getDescription() {
		return description_product;
	}
	public void setDescription(String description) {
		this.description_product = description;
	}
	public String getBrand() {
		return brand_product;
	}
	public void setBrand(String brand) {
		this.brand_product = brand;
	}
	public String getModel() {
		return model_product;
	}
	public void setModel(String model) {
		this.model_product = model;
	}
	public double getPrice() {
		return price_product;
	}
	public void setPrice(double price) {
		this.price_product = price;
	}
	public String getCurrency() {
		return currency_product;
	}
	public void setCurrency(String currency) {
		this.currency_product = currency;
	}
	public Integer getStock() {
		return stock_product;
	}
	public void setStock(Integer stock) {
		this.stock_product = stock;
	}
	public String getEnviroment() {
		return enviroment;
	}
	public void setEnviroment(String enviroment) {
		this.enviroment = enviroment;
	}
	public double getConvertedPrice() {
		return convertedPrice;
	}
	public void setConvertedPrice(double convertedPrice) {
		this.convertedPrice = convertedPrice;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
}
package br.edu.atitus.product_service.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "tb_product")
public class ProductEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "description")
	private String description;
	
	@Column(name = "brand")
	private String brand;
	@Column(name = "model")
	private String model;
	@Column(name = "price")
	private double price;
	@Column(name = "currency")
	private String currency;
	@Column(name = "stock")
	private Integer stock;

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
		return description;
	}
	public void setDescription(String description_product) {
		this.description = description_product;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand_product) {
		this.brand = brand_product;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model_product) {
		this.model = model_product;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price_product) {
		this.price = price_product;
	}
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency_product) {
		this.currency = currency_product;
	}
	public Integer getStock() {
		return stock;
	}
	public void setStock(Integer stock_product) {
		this.stock = stock_product;
	}
	public String getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
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
	
	
}
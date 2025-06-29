package br.edu.atitus.product_service.clients;

public class CurrencyResponse {
	
	private Long id;
	
	private String source;
	
	private String target;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getTarget() {
		return target;
	}

	public void setTarget(String target) {
		this.target = target;
	}

	public double getCoversionRate() {
		return coversionRate;
	}

	public void setCoversionRate(double coversionRate) {
		this.coversionRate = coversionRate;
	}

	public double getConvertedValue() {
		return convertedValue;
	}

	public void setConvertedValue(double convertedValue) {
		this.convertedValue = convertedValue;
	}

	public String getEnviroment() {
		return enviroment;
	}

	public void setEnviroment(String enviroment) {
		this.enviroment = enviroment;
	}

	private double coversionRate;
	
	private double convertedValue;
	
	private String enviroment;
	

}

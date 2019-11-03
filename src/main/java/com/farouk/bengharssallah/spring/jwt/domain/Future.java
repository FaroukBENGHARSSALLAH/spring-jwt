package com.farouk.bengharssallah.spring.jwt.domain;

import javax.persistence.Column;
import javax.persistence.Entity;


@Entity
public class Future extends AEntity {

	@Column
	private String ticker;
	@Column
	private String name;
	@Column
	private String exchange;
	@Column
	private long volume;
	
	
	public String getTicker() {
		return ticker;
	}
	public void setTicker(String ticker) {
		this.ticker = ticker;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getExchange() {
		return exchange;
	}
	public void setExchange(String exchange) {
		this.exchange = exchange;
	}
	public long getVolume() {
		return volume;
	}
	public void setVolume(long volume) {
		this.volume = volume;
	}
	
	
}

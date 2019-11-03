package com.farouk.bengharssallah.spring.jwt.domain;

import javax.persistence.Version;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;

@MappedSuperclass
public class AEntity implements Serializable
{
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    protected Long id;
    @Version
    protected Integer version;
    
   
    
    public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getVersion() {
        return this.version;
    }
    
    public void setVersion(final Integer version) {
        this.version = version;
    }
}


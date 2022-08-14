package com.alkemy.aceleracion.disney.auth.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "user")
public class UserEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@Email
	private String username;
	@Size
	private String password;
	private boolean accountNonExpire;
	private boolean accountNonLocked;
	private boolean credentialNonExpire;
	private boolean enabled;
	
	public UserEntity() {
		this.accountNonExpire = true;
		this.accountNonLocked = true;
		this.credentialNonExpire = true;
		this.enabled = true;
	}
}

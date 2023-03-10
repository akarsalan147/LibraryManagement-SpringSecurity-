package com.example.MinorProject2.model;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MyUser implements UserDetails{
	
	private static final String AUTHORITY_DELIMITER = ",";
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String name;
	
	@Column(unique = true, nullable = false)
	private String username;
	
	private String password;
	
	private String authority;
	
	@OneToOne(mappedBy = "myUser")
	@JsonIgnoreProperties("myUser")
	private Student student;
	
	@OneToOne(mappedBy = "myUser")
	@JsonIgnoreProperties("myUser")
	private Admin admin;
	
	

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		String authorities[] = this.authority.split(AUTHORITY_DELIMITER);
		return Arrays.stream(authorities).map(x -> new SimpleGrantedAuthority(x)).collect(Collectors.toList());
	}

	@Override
	public String getPassword() {
		return this.password;
	}

	@Override
	public String getUsername() {
		return this.username;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

}

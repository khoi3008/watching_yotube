package com.poly.dtos;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class FavoriteUserDto implements Serializable{
	@Serial
	private static final long serialVersionUID = -1984225620282112027L;
	@Id
	private Long id;
	private String username;
	private String fullName;
	private String email;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm a z")
	private Date favoriteDate;
	
	public FavoriteUserDto(String username, String fullName, String email, Date favoriteDate) {
		this.username = username;
		this.fullName = fullName;
		this.email = email;
		this.favoriteDate = favoriteDate;
	}
}

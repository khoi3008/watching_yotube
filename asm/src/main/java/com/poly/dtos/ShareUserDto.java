package com.poly.dtos;

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
public class ShareUserDto {
	@Id
	private Long id;
	private String username;
	private String email;
	private String receiveEmails;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm a z")
	private Date shareDate;
	
	public ShareUserDto(String username, String email, String receiveEmails, Date shareDate) {
		super();
		this.username = username;
		this.email = email;
		this.receiveEmails = receiveEmails;
		this.shareDate = shareDate;
	}
}

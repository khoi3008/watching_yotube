package com.poly.entity;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "shares")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@NamedQueries({
	@NamedQuery(name = "Share.findByUserAndVideo", query = "SELECT o FROM Share o WHERE o.video.id=:vid AND o.user.id=:uid")
})
public class Share {
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy  = GenerationType.IDENTITY)
	Long id;
	
	@Column(name="emails")
	String emails;
	
	@Column(name="sharedate")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm a z")
	Date shareDate;
	
	@ManyToOne
	@JoinColumn(name="userid")
	@JsonIgnore
	User user;
	
	@ManyToOne()
	@JoinColumn(name="videoid")
	@JsonIgnore
	Video video;
}

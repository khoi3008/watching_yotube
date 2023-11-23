package com.poly.entity;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "users")
@Getter
@Setter
@NamedQueries({
		@NamedQuery(name = "User.findUserLikedVideo", query = "SELECT o.user FROM Favorite o WHERE o.video.id=:vid AND o.user.id=:uid"),
		@NamedQuery(name = "User.findAllFavoriteUser", query = "SELECT new FavoriteUserDto(o.user.id, o.user.fullname, o.user.email, o.likeDate) FROM Favorite o WHERE o.video.id=:vid"),
		@NamedQuery(name = "User.findAllShareUser", query = "SELECT new ShareUserDto(o.user.id, o.user.email, o.emails, o.shareDate) FROM Share o WHERE o.video.id=:vid "), 
		@NamedQuery(name = "User.findByEmail", query = "SELECT o FROM User o WHERE o.email=:email"),
		@NamedQuery(name = "User.findAll", query = "SELECT o FROM User o"),
		@NamedQuery(name = "User.updateInformation", query = "UPDATE User o SET o.fullname=:fullname, o.email=:email WHERE o.id=:uid"),})
public class User {
	@Id
	String id;
	@Column(name = "password")
	String password;
	@Column(name = "fullname")
	String fullname;
	@Column(name = "email")
	String email;
	@Column(name = "admin")
	boolean admin = false;
	@OneToMany(mappedBy = "user")
	List<Favorite> favorites;
	@OneToMany(mappedBy = "user")
	List<Share> shares;
}
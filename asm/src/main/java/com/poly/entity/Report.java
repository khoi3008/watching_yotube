package com.poly.entity;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.NamedStoredProcedureQuery;
import jakarta.persistence.StoredProcedureParameter;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@NamedStoredProcedureQuery(name = "sp", procedureName = "spFavoriteByYear", resultClasses = Report.class, parameters = {
		@StoredProcedureParameter(type = Integer.class, name = "year") 
})
public class Report {
	@Id
	@Column(name = "group")
	String group;
	@Column(name="likes")
	Long likes;
	@Column(name = "newest")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm a z")
	Date newest;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm a z")
	@Column(name="oldest")
	Date oldest;

}
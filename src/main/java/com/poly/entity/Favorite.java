package com.poly.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name = "favorites",
        uniqueConstraints = {@UniqueConstraint(columnNames = {"videoid", "userid"})})
@Getter
@Setter
@NamedQueries({
        @NamedQuery(name = "Favorite.countFavoriteVideoByUser", query = "SELECT o FROM Favorite o WHERE o.user.id=:id"),
})
public class Favorite {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @ManyToOne
    @JoinColumn(name = "userid")
    @JsonIgnore
    User user;
    @ManyToOne
    @JoinColumn(name = "videoid")
    @JsonIgnore
    Video video;
    @Temporal(TemporalType.DATE)
    @Column(name = "likedate")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    Date likeDate = new Date();
}

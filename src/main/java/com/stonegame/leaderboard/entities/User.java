package com.stonegame.leaderboard.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Entity @Data
@Table(name="Users")
@NoArgsConstructor
public class User {

    @Id @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "login")
    private String login;

    @Column(name = "password")
    private String password;

    @Column(name = "nickname")
    private String nickname;

    @Column(name = "rating")
    private int rating;

    @Column(name = "max_rating")
    private int maxRating;

    @Column(name = "games_played")
    private int gamesPlayed;

    @Column(name = "wins")
    private int wins;


    public double getWinrate() {
        if(wins==0) { return 0.0; }
        return (double) wins / gamesPlayed * 100;
    }


    @Override
    public int hashCode() {
        return Objects.hash(id, login, nickname);
    }

    @Override
    public boolean equals(Object o) {
        if(this == o) return true;
        if(o == null || getClass() != o.getClass()) return false;
        User other = (User) o;
        return id == other.id && Objects.equals(login, other.login) && Objects.equals(nickname, other.nickname);
    }

}

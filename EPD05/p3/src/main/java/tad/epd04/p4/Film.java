/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tad.epd04.p4;

/**
 *
 * @author ridao
 */
public class Film {
    String room;
    String sessions;
    String name;
    String director;
    String synopsis;
    Integer year;
    Integer score;

    public Film(String room, String sessions, String name, String director, String synopsis, Integer year, Integer score) {
        this.room = room;
        this.sessions = sessions;
        this.name = name;
        this.director = director;
        this.synopsis = synopsis;
        this.year = year;
        this.score = score;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public String getSessions() {
        return sessions;
    }

    public void setSessions(String sessions) {
        this.sessions = sessions;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getSynopsis() {
        return synopsis;
    }

    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }
    
    
   
}

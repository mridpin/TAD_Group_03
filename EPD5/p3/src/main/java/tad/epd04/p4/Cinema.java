/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tad.epd04.p4;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ridao
 */
public class Cinema {

    List<Film> films;

    public Cinema() {
        this.films = generateFilms();
    }

    public List<Film> getFilms() {
        return films;
    }

    public void setFilms(List<Film> films) {
        this.films = films;
    }

    public List<Film> generateFilms() {
        List<Film> filmList = new ArrayList<>();
        
        String sess1 = "19:00, 21:00, 23:00";
        Film f1 = new Film("1", sess1, "South Park", "Trey Parker", "Best movie ever", 2001, 5);
        String sess2 = "19:00, 21:00, 23:00";
        Film f2 = new Film("2", sess2, "Alien", "Ridley Scott", "Best sci-fi movie ever", 1979, 5);
        String sess3 = "19:00, 21:00, 23:00";
        Film f3 = new Film("3", sess3, "Team America", "Trey Parker", "Also Best movie ever", 2003, 5);
        String sess4 = "19:00, 21:00, 23:00";
        Film f4 = new Film("4", sess4, "Terminator 2 Judgement Day", "James Cameron", "Best movie ever too", 1991, 5);
        String sess5 = "19:00, 21:00, 23:00";
        Film f5 = new Film("5", sess5, "The Avengers 9", "A computer", "Worst movie ever", 2020, 0);
        
        filmList.add(f1);
        filmList.add(f2);
        filmList.add(f3);
        filmList.add(f4);
        filmList.add(f5);
        
        return filmList;
    }

    List<Film> searchByName(String search) {
        List<Film> filteredFilms = new ArrayList<>();
        for (Film f : this.films) {
            if (f.getName().toLowerCase().contains(search.toLowerCase())) {
                filteredFilms.add(f);
            }
        }
        return filteredFilms;
    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author gnsdu
 */
public class GenreComposite implements Genre {
    private List<Genre> genres = new ArrayList<>();

    public void addGenre(Genre genre) {
        genres.add(genre);
    }

    public void removeGenre(Genre genre) {
        genres.remove(genre);
    }

    public List<Genre> getGenres() {
        return genres;
    }

    @Override
    public String getContent() {
        StringBuilder builder = new StringBuilder();
        for (Genre genre : genres) {
            builder.append(genre.getContent());
        }
        return builder.toString();
    }
}
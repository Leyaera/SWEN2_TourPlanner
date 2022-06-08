package com.glatzerkratzer.tourplanner.dal;

import com.glatzerkratzer.tourplanner.model.TourItem;

import java.util.List;

/**
 * Dao interface defines an abstract API that performs CRUD operations on objects of type T
 * see: https://www.baeldung.com/java-dao-pattern
 *
 * @param <T> the type of the model
 */
public interface Dao<T> {

    List<T> getAll(int id);

    List<T> getLatestEntries(int lastTourIdInCurrentList);

    int getIdByName(String name);

    void add(T t);

    void updateById(int tourId, T t);

    void delete(T t);
}
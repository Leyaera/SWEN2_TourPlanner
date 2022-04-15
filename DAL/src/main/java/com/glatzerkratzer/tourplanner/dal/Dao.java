package com.glatzerkratzer.tourplanner.dal;

import com.glatzerkratzer.tourplanner.model.TourItem;

import java.util.List;
import java.util.Optional;

/**
 * Dao interface defines an abstract API that performs CRUD operations on objects of type T
 * see: https://www.baeldung.com/java-dao-pattern
 *
 * @param <T> the type of the model
 */
public interface Dao<T> {

    List<T> getAll();

    List<TourItem> getLatestEntries(int lastTourIdInCurrentList);

    int getTourItemIdByName(String name);

    void add(T t);

    void updateById(int tourId, T t);

    void updateByName(String nameBeforeUpdate, T t);

    void delete(T t);
}
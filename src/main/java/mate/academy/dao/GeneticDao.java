package mate.academy.dao;

import java.util.List;

public interface GeneticDao<T> {
    T add(T t);

    List<T> getAll();
}

package mate.academy.service;

import java.util.List;

public interface GeneticService<T> {
    T add(T t);

    List<T> getAll();
}

package org.vega.appmockito.examples.repositories;

import org.vega.appmockito.examples.models.Examen;

import java.util.List;

public interface ExamenRepository {
    List<Examen> findAll();
    
}

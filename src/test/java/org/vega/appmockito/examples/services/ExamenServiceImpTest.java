package org.vega.appmockito.examples.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

import org.vega.appmockito.examples.models.Examen;
import org.vega.appmockito.examples.repositories.ExamenRepository;
import org.vega.appmockito.examples.repositories.ExamenRepositoryImpl;
import org.vega.appmockito.examples.repositories.ExamenRepositoryOtro;
import org.vega.appmockito.examples.repositories.PreguntaRepository;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class ExamenServiceImpTest {
    ExamenRepository repository;
    ExamenService service;
    PreguntaRepository preguntaRepository;

    @BeforeEach
    void setUp() {
        repository = mock(ExamenRepositoryOtro.class);
        preguntaRepository = mock(PreguntaRepository.class);
        service = new ExamenServiceImp(repository, preguntaRepository);
    }

    @Test
    void findExamenPorNombre() {
        List<Examen> datos = Arrays.asList(new Examen(5L, "Matematicas")
                , new Examen(6L, "Lenguaje")
                , new Examen(7L, "Historia"));

        when(repository.findAll()).thenReturn(datos);

        Optional<Examen> examen = service.findExamenPorNombre("Matematicas");

        assertTrue(examen.isPresent());
        assertEquals(5L, examen.orElseThrow().getId());
        assertEquals("Matematicas", examen.get().getNombre());

    }

    @Test
    void findExamenPorNombreListaVacia() {
        List<Examen> datos = Collections.emptyList();

        when(repository.findAll()).thenReturn(datos);
        Optional<Examen> examen = service.findExamenPorNombre("Matematicas");

        assertFalse(examen.isPresent());
    }
}
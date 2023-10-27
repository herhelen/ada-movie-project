package com.project.movies.repository;

import com.project.movies.model.ItemLista;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemListaRepository extends JpaRepository<ItemLista, Long> {
}
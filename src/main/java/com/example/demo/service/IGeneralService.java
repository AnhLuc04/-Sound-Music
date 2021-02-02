package com.example.demo.service;


import com.example.demo.model.User;
import javassist.NotFoundException;

import java.util.Optional;

public interface IGeneralService<T>  {
    Iterable<T> findAll();

    Optional<T> findById(Long id) throws NotFoundException;

    T save(T model);

    void remove(Long id);
}

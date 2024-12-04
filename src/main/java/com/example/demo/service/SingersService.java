package com.example.demo.service;

import com.example.demo.entity.Singers;

import java.util.List;

public interface SingersService {
    public Integer saveSingers(Singers singers);
    public void update(Singers singers);
    public void delete(Integer id);
    public Singers getOneSinger(Integer id);
    public boolean isAvailable(Integer id);
    public List<Singers> getAllSingers();
}

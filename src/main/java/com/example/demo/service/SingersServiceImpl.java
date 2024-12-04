package com.example.demo.service;

import com.example.demo.entity.Singers;
import com.example.demo.repository.SingersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class SingersServiceImpl implements SingersService {
    @Autowired
    private SingersRepository singersRepository;

    @Override
    @Transactional
    public Integer saveSingers(Singers singers) {
        return singersRepository.save(singers).getSingersPosition();
    }

    @Override
    @Transactional
    public void update(Singers singers) {
        singersRepository.save(singers);
    }

    @Override
    @Transactional
    public void delete(Integer id) {
        singersRepository.deleteById(id);
    }

    @Override
    @Transactional
    public Singers getOneSinger(Integer id) {
        return singersRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public boolean isAvailable(Integer id) {
        return singersRepository.existsById(id);
    }

    @Override
    @Transactional
    public List<Singers> getAllSingers() {
        return singersRepository.findAll();
    }

}
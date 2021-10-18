package br.com.jsoncall.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.jsoncall.entity.Atm;
import br.com.jsoncall.exception.ServiceException;
import br.com.jsoncall.repository.AtmRepository;

@Service
public class AtmService {

    private final AtmRepository atmRepository;

    @Autowired
    public AtmService(AtmRepository atmRepository) {
        this.atmRepository = atmRepository;
    }

    public List<Atm> findAll() {
        return atmRepository.findAll();
    }

    public Atm findById(String id) {
        return atmRepository.findById(id).orElseThrow(() -> new ServiceException(20001, "Method not found"));
    }

    
    @Transactional
    public Atm save(Atm atm) {
        return atmRepository.save(atm);
    }

    @Transactional
    public Atm update(Atm atm) {
        return atmRepository.saveAndFlush(atm);
    }

    @Transactional
    public void deleteById(String id) {
    	atmRepository.deleteById(id);
    }

}

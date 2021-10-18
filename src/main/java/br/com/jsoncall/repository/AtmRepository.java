package br.com.jsoncall.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.jsoncall.entity.Atm;

@Repository
public interface AtmRepository extends JpaRepository<Atm, String> {

	void deleteById(String id);

}

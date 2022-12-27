package project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import project.model.Prijava;

@Repository
public interface PrijavaRepository extends JpaRepository<Prijava, Long>{

}

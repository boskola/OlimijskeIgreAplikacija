package project.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import project.model.Takmicar;

@Repository
public interface TakmicarRepository extends JpaRepository<Takmicar, Long>{
	Page<Takmicar> findByDrzavaIdAndBrojMedaljaBetween(Long drzavaId, Integer BrojMedaljaMin, Integer BrojMedaljaMax, Pageable pageable);
	Page<Takmicar> findByBrojMedaljaBetween(Integer BrojMedaljaMin, Integer BrojMedaljaMax, Pageable pageable);
}

package com.mgs.empregados.repositories;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.mgs.empregados.models.EmpregadoModel;

@Repository
public interface EmpregadoRepository extends JpaRepository<EmpregadoModel, Integer> {

}






	

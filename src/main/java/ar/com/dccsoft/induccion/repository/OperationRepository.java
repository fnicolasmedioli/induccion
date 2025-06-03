package ar.com.dccsoft.induccion.repository;

import ar.com.dccsoft.induccion.entity.Operation;
import ar.com.dccsoft.induccion.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OperationRepository extends JpaRepository<Operation, Long> {}

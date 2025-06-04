package ar.com.dccsoft.induccion.service;

import ar.com.dccsoft.induccion.entity.Group;
import ar.com.dccsoft.induccion.entity.Operation;
import ar.com.dccsoft.induccion.repository.OperationRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class OperationService {

    private final OperationRepository operationRepository;

    public List<Operation> listOperations() {
        return operationRepository.findAll();
    }

    public Operation getOperationById(Long operationid) {
        Optional<Operation> operation = this.operationRepository.findById(operationid);
        return operation.orElse(null);
    }

}

package ar.com.dccsoft.induccion.service;

import ar.com.dccsoft.induccion.dto.PermissionRequest;
import ar.com.dccsoft.induccion.entity.Group;
import ar.com.dccsoft.induccion.entity.Operation;
import ar.com.dccsoft.induccion.exception.InvalidFormException;
import ar.com.dccsoft.induccion.exception.RelationNotFoundException;
import ar.com.dccsoft.induccion.repository.GroupRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class PermissionService {

    private final GroupRepository groupRepository;

    private final GroupService groupService;
    private final OperationService operationService;

    public void deletePermission(PermissionRequest permissionRequest) {

        Group group = groupRepository.findById(permissionRequest.getGroupid()).orElseThrow(RelationNotFoundException::new);

        boolean removed = group.getOperations()
                .removeIf(op -> op.getOperationid().equals(permissionRequest.getOperationid()));

        if (!removed) {
            throw new RelationNotFoundException();
        }

        groupRepository.save(group);
    }

    public void addPermission(PermissionRequest permissionRequest) {

        Group group = groupService.getGroupById(permissionRequest.getGroupid());
        Operation operation = operationService.getOperationById(permissionRequest.getOperationid());

        if (group == null || operation == null) {
            throw new InvalidFormException();
        }

        group.getOperations().add(operation);

        groupRepository.save(group);
    }

}

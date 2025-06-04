package ar.com.dccsoft.induccion.service;

import ar.com.dccsoft.induccion.dto.GroupRequest;
import ar.com.dccsoft.induccion.entity.Group;
import ar.com.dccsoft.induccion.entity.User;
import ar.com.dccsoft.induccion.repository.GroupRepository;
import ar.com.dccsoft.induccion.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class GroupService {

    private final GroupRepository groupRepository;

    public List<Group> listGroupsWithUsers() {
        return groupRepository.getGroupsWithUsers();
    }

    public void createGroup(GroupRequest groupRequest) {
        Group newGroup = new Group();
        newGroup.setName(groupRequest.getName());
        newGroup.setDescription(groupRequest.getDescription());
        groupRepository.saveAndFlush(newGroup);
    }

    public List<Group> listGroupsWithOperations() {
        return groupRepository.getGroupsWithOperations();
    }

    public Group getGroupById(Long groupid) {
        Optional<Group> group = this.groupRepository.findById(groupid);
        return group.orElse(null);
    }

}

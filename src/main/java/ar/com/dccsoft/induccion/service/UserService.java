package ar.com.dccsoft.induccion.service;

import ar.com.dccsoft.induccion.dto.UserRequest;
import ar.com.dccsoft.induccion.entity.Operation;
import ar.com.dccsoft.induccion.entity.User;
import ar.com.dccsoft.induccion.exception.UserAlreadyExistsException;
import ar.com.dccsoft.induccion.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public List<User> getFullUsers() {
        return this.userRepository.findWithPermissionsCriteria();
    }

    public void createUser(UserRequest userRequest) {

        if (userRepository.findByLogin(userRequest.getLogin()).isPresent()) {
            throw new UserAlreadyExistsException();
        }

        User newUser = new User();
        newUser.setLogin(userRequest.getLogin());
        newUser.setPassword(userRequest.getPassword());

        userRepository.saveAndFlush(newUser);
    }

    public Set<Operation> getUserOperations(Long userid) {
        User user = userRepository.findById(userid)
                .orElseThrow(() -> new EntityNotFoundException("Not found"));

        return user.getGroups().stream()
                .flatMap(group -> group.getOperations().stream())
                .collect(Collectors.toSet());
    }

}

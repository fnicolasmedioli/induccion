package ar.com.dccsoft.induccion.seed;

import ar.com.dccsoft.induccion.entity.*;
import ar.com.dccsoft.induccion.repository.*;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DataSeederRunner implements CommandLineRunner {

    private final UserRepository userRepository;
    private final GroupRepository groupRepository;
    private final OperationRepository operationRepository;

    @Override
    @Transactional
    public void run(String... args) {
        DataSeeder seeder = new DataSeeder();
        operationRepository.saveAll(seeder.getOperations());
        userRepository.saveAll(seeder.getUsers());
        groupRepository.saveAll(seeder.getGroups());
    }
}

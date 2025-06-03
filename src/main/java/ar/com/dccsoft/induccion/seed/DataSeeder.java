package ar.com.dccsoft.induccion.seed;

import ar.com.dccsoft.induccion.entity.Group;
import ar.com.dccsoft.induccion.entity.Operation;
import ar.com.dccsoft.induccion.entity.User;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class DataSeeder {

    private final List<User> users = new ArrayList<>();
    private final List<Group> groups = new ArrayList<>();
    private final List<Operation> operations = new ArrayList<>();

    public DataSeeder() {
        seedOperations();
        seedGroups();
        seedUsers();
    }

    private void seedOperations() {
        Operation op1 = new Operation();
        op1.setName("Operacion_A");
        op1.setDescription("Administración del sistema");
        op1.setType(Operation.OperationType.A);

        Operation op2 = new Operation();
        op2.setName("Operacion_B");
        op2.setDescription("Moderación de usuarios");
        op2.setType(Operation.OperationType.B);

        Operation op3 = new Operation();
        op3.setName("Operacion_C");
        op3.setDescription("Exportación de datos");
        op3.setType(Operation.OperationType.C);

        operations.addAll(List.of(op1, op2, op3));
    }

    private void seedGroups() {
        Group admins = new Group();
        admins.setName("Admins");
        admins.setDescription("Administradores del sistema");
        admins.setOperations(List.of(operations.get(0), operations.get(1), operations.get(2)));

        Group mods = new Group();
        mods.setName("Mods");
        mods.setDescription("Moderadores");
        mods.setOperations(List.of(operations.get(1), operations.get(2)));

        Group usuarios = new Group();
        usuarios.setName("Usuarios");
        usuarios.setDescription("Usuarios generales");
        usuarios.setOperations(List.of(operations.get(2)));

        groups.addAll(List.of(admins, mods, usuarios));
    }

    private void seedUsers() {
        Group admins = groups.get(0);
        Group mods = groups.get(1);
        Group usuarios = groups.get(2);

        User admin = new User();
        admin.setLogin("admin");
        admin.setPassword("admin123");
        //admin.setDefaultgroup(admins);
        admin.setGroups(List.of(admins, mods));

        User user1 = new User();
        user1.setLogin("user1");
        user1.setPassword("user1_123");
        //user1.setDefaultgroup(usuarios);
        user1.setGroups(List.of(usuarios));

        User user2 = new User();
        user2.setLogin("user2");
        user2.setPassword("user2_123");
        //user2.setDefaultgroup(usuarios);
        user2.setGroups(List.of(usuarios, mods));

        User mod = new User();
        mod.setLogin("mod");
        mod.setPassword("mod123");
        //mod.setDefaultgroup(mods);
        mod.setGroups(List.of(mods, usuarios));

        admins.setUsers(List.of(admin));
        mods.setUsers(List.of(admin, mod, user2));
        usuarios.setUsers(List.of(user1, user2, mod));

        admins.setDefaultGroupUsers(List.of(admin));
        mods.setDefaultGroupUsers(List.of(mod));
        usuarios.setDefaultGroupUsers(List.of(user1, user2));

        users.addAll(List.of(admin, user1, user2, mod));
    }
}
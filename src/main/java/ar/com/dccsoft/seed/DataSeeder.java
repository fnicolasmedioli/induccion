package ar.com.dccsoft.seed;

import ar.com.dccsoft.model.Group;
import ar.com.dccsoft.model.Operation;
import ar.com.dccsoft.model.OperationType;
import ar.com.dccsoft.model.User;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
        op1.setType(OperationType.A);

        Operation op2 = new Operation();
        op2.setName("Operacion_B");
        op2.setDescription("Moderación de usuarios");
        op2.setType(OperationType.B);

        Operation op3 = new Operation();
        op3.setName("Operacion_C");
        op3.setDescription("Exportación de datos");
        op3.setType(OperationType.C);

        operations.addAll(Arrays.asList(op1, op2, op3));
    }

    private void seedGroups() {
        Group admins = new Group();
        admins.setName("Admins");
        admins.setDescription("Administradores del sistema");
        admins.setOperations(Arrays.asList(operations.get(0), operations.get(1), operations.get(2)));

        Group mods = new Group();
        mods.setName("Mods");
        mods.setDescription("Moderadores");
        mods.setOperations(Arrays.asList(operations.get(1), operations.get(2)));

        Group usuarios = new Group();
        usuarios.setName("Usuarios");
        usuarios.setDescription("Usuarios generales");
        usuarios.setOperations(Arrays.asList(operations.get(2)));

        groups.addAll(Arrays.asList(admins, mods, usuarios));
    }

    private void seedUsers() {
        Group admins = groups.get(0);
        Group mods = groups.get(1);
        Group usuarios = groups.get(2);

        User admin = new User();
        admin.setLogin("admin");
        admin.setPassword("admin123");
        //admin.setDefaultgroup(admins);
        admin.setGroups(Arrays.asList(admins, mods));

        User user1 = new User();
        user1.setLogin("user1");
        user1.setPassword("user1_123");
        //user1.setDefaultgroup(usuarios);
        user1.setGroups(Arrays.asList(usuarios));

        User user2 = new User();
        user2.setLogin("user2");
        user2.setPassword("user2_123");
        //user2.setDefaultgroup(usuarios);
        user2.setGroups(Arrays.asList(usuarios, mods));

        User mod = new User();
        mod.setLogin("mod");
        mod.setPassword("mod123");
        //mod.setDefaultgroup(mods);
        mod.setGroups(Arrays.asList(mods, usuarios));

        admins.setUsers(Arrays.asList(admin));
        mods.setUsers(Arrays.asList(admin, mod, user2));
        usuarios.setUsers(Arrays.asList(user1, user2, mod));

        admins.setDefaultGroupUsers(Arrays.asList(admin));
        mods.setDefaultGroupUsers(Arrays.asList(mod));
        usuarios.setDefaultGroupUsers(Arrays.asList(user1, user2));

        users.addAll(Arrays.asList(admin, user1, user2, mod));
    }

    public List<User> getUsers() {
        return users;
    }

    public List<Group> getGroups() {
        return groups;
    }

    public List<Operation> getOperations() {
        return operations;
    }
}

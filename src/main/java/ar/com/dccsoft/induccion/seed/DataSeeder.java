package ar.com.dccsoft.induccion.seed;

import ar.com.dccsoft.induccion.entities.GroupEntity;
import ar.com.dccsoft.induccion.entities.OperationEntity;
import ar.com.dccsoft.induccion.entities.UserEntity;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class DataSeeder {

    private final List<UserEntity> users = new ArrayList<>();
    private final List<GroupEntity> groups = new ArrayList<>();
    private final List<OperationEntity> operations = new ArrayList<>();

    public DataSeeder() {
        seedOperations();
        seedGroups();
        seedUsers();
    }

    private void seedOperations() {
        OperationEntity op1 = new OperationEntity();
        op1.setName("Operacion_A");
        op1.setDescription("Administración del sistema");
        op1.setType(OperationEntity.OperationType.A);

        OperationEntity op2 = new OperationEntity();
        op2.setName("Operacion_B");
        op2.setDescription("Moderación de usuarios");
        op2.setType(OperationEntity.OperationType.B);

        OperationEntity op3 = new OperationEntity();
        op3.setName("Operacion_C");
        op3.setDescription("Exportación de datos");
        op3.setType(OperationEntity.OperationType.C);

        operations.addAll(List.of(op1, op2, op3));
    }

    private void seedGroups() {
        GroupEntity admins = new GroupEntity();
        admins.setName("Admins");
        admins.setDescription("Administradores del sistema");
        admins.setOperations(List.of(operations.get(0), operations.get(1), operations.get(2)));

        GroupEntity mods = new GroupEntity();
        mods.setName("Mods");
        mods.setDescription("Moderadores");
        mods.setOperations(List.of(operations.get(1), operations.get(2)));

        GroupEntity usuarios = new GroupEntity();
        usuarios.setName("Usuarios");
        usuarios.setDescription("Usuarios generales");
        usuarios.setOperations(List.of(operations.get(2)));

        groups.addAll(List.of(admins, mods, usuarios));
    }

    private void seedUsers() {
        GroupEntity admins = groups.get(0);
        GroupEntity mods = groups.get(1);
        GroupEntity usuarios = groups.get(2);

        UserEntity admin = new UserEntity();
        admin.setLogin("admin");
        admin.setPassword("admin123");
        //admin.setDefaultgroup(admins);
        admin.setGroups(List.of(admins, mods));

        UserEntity user1 = new UserEntity();
        user1.setLogin("user1");
        user1.setPassword("user1_123");
        //user1.setDefaultgroup(usuarios);
        user1.setGroups(List.of(usuarios));

        UserEntity user2 = new UserEntity();
        user2.setLogin("user2");
        user2.setPassword("user2_123");
        //user2.setDefaultgroup(usuarios);
        user2.setGroups(List.of(usuarios, mods));

        UserEntity mod = new UserEntity();
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
package ar.com.dccsoft.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "`groups`")
public class Group {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int groupid;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @OneToMany(mappedBy = "defaultgroup")
    private List<User> defaultGroupUsers;

    @ManyToMany
    @JoinTable(
        name = "group_assignment",
        joinColumns = @JoinColumn(name = "groupid"),
        inverseJoinColumns = @JoinColumn(name = "userid")
    )
    private List<User> users;

    @ManyToMany
    @JoinTable(
            name = "permissions",
            joinColumns = @JoinColumn(name = "groupid"),
            inverseJoinColumns = @JoinColumn(name = "operationid")
    )
    private List<Operation> operations;

    public int getGroupid() {
        return groupid;
    }

    public void setGroupid(int groupid) {
        this.groupid = groupid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<User> getDefaultGroupUsers() {
        return defaultGroupUsers;
    }

    public void setDefaultGroupUsers(List<User> defaultGroupUsers) {
        this.defaultGroupUsers = defaultGroupUsers;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public List<Operation> getOperations() {
        return operations;
    }

    public void setOperations(List<Operation> operations) {
        this.operations = operations;
    }
}

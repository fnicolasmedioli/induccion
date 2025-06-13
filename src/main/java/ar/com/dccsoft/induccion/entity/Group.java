package ar.com.dccsoft.induccion.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.Cascade;

import java.util.List;
import java.util.Set;

@Entity
@Table(name = "`groups`")
@Getter
@Setter
@ToString
public class Group {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long groupid;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @OneToMany(mappedBy = "defaultgroup")
    private Set<User> defaultGroupUsers;

    @ManyToMany
    @JoinTable(
            name = "group_assignment",
            joinColumns = @JoinColumn(name = "groupid"),
            inverseJoinColumns = @JoinColumn(name = "userid")
    )
    @OrderBy("login ASC")
    private Set<User> users;

    @ManyToMany
    @JoinTable(
            name = "permissions",
            joinColumns = @JoinColumn(name = "groupid"),
            inverseJoinColumns = @JoinColumn(name = "operationid")
    )
    @OrderBy("name ASC")
    private Set<Operation> operations;

    public void addUser(User user) {
        if (users == null) {
            users = new java.util.HashSet<>();
        }
        users.add(user);
        if (user.getGroups() == null) {
            user.setGroups(new java.util.HashSet<>());
        }
        user.getGroups().add(this);
    }

    public void removeUser(User user) {
        if (users != null) {
            users.remove(user);
        }
        if (user.getGroups() != null) {
            user.getGroups().remove(this);
        }
    }

}
package ar.com.dccsoft.induccion.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.Set;

@Entity
@Table(name = "users")
@Getter
@Setter
@ToString
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userid;

    @Column(name = "login")
    private String login;

    @Column(name = "password")
    private String password;

    @ManyToOne
    @JoinColumn(name = "defaultgroupid")
    private Group defaultgroup;

    @ManyToMany(mappedBy = "users", fetch = FetchType.EAGER)
    @OrderBy("name ASC")
    private Set<Group> groups;

    public void addGroup(Group group) {
        if (groups == null) {
            groups = new java.util.HashSet<>();
        }
        groups.add(group);
        if (group.getUsers() == null) {
            group.setUsers(new java.util.HashSet<>());
        }
        group.getUsers().add(this);
    }

    public void removeGroup(Group group) {
        if (groups != null) {
            groups.remove(group);
        }
        if (group.getUsers() != null) {
            group.getUsers().remove(this);
        }
    }

}
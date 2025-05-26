package ar.com.dccsoft.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "groups")
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
        name = "GroupAssignment",
        joinColumns = @JoinColumn(name = "groupid"),
        inverseJoinColumns = @JoinColumn(name = "userid")
    )
    private List<User> users;

    @ManyToMany
    @JoinTable(
            name = "Permissions",
            joinColumns = @JoinColumn(name = "groupid"),
            inverseJoinColumns = @JoinColumn(name = "operationid")
    )
    private List<Operation> operations;

}

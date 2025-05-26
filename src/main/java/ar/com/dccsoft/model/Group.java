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
    private User user;

    @ManyToMany
    @JoinTable(
        name = "GroupAssignment",
        joinColumns = @JoinColumn(name = "groupid"),
        inverseJoinColumns = @JoinColumn(name = "userid")
    )
    private List<User> users;

}

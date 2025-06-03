package ar.com.dccsoft.induccion.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.Cascade;

import java.util.List;

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
}
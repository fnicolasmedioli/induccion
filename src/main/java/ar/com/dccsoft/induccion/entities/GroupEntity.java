package ar.com.dccsoft.induccion.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Entity
@Table(name = "`groups`")
@Getter
@Setter
@ToString
public class GroupEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int groupid;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @OneToMany(mappedBy = "defaultgroup")
    private List<UserEntity> defaultGroupUsers;

    @ManyToMany
    @JoinTable(
            name = "group_assignment",
            joinColumns = @JoinColumn(name = "groupid"),
            inverseJoinColumns = @JoinColumn(name = "userid")
    )
    private List<UserEntity> users;

    @ManyToMany
    @JoinTable(
            name = "permissions",
            joinColumns = @JoinColumn(name = "groupid"),
            inverseJoinColumns = @JoinColumn(name = "operationid")
    )
    private List<OperationEntity> operations;
}
package ar.com.dccsoft.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userid;

    @Column(name = "login")
    private String login;

    @Column(name = "password")
    private String password;

    @ManyToOne
    @JoinColumn(name = "defaultgroupid")
    private Group defaultgroup;

    @ManyToMany(mappedBy = "cursos")
    private List<Group> groups;
}

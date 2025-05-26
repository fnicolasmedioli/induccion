package ar.com.dccsoft.model;

import javax.persistence.*;

@Entity
@Table(name = "operations")
public class Operation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int operationid;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    private OperationType type;

    @ManyToMany(mappedBy = "operations")
    private Group group;
}
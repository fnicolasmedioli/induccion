package ar.com.dccsoft.induccion.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Entity
@Table(name = "operations")
@Getter
@Setter
@ToString
public class Operation {

    public enum OperationType {
        A, B, C
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long operationid;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    private OperationType type;

    @ManyToMany(mappedBy = "operations")
    private List<Group> groups;

}
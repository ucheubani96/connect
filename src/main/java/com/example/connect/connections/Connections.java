package com.example.connect.connections;

import com.example.connect.shared.models.Auditable;
import jakarta.persistence.*;

@Entity
@Table(name = "connections")
public class Connections extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "first_id", nullable = false)
    private Long first_id;

    @Column(name = "second_id", nullable = false)
    private Long second_id;

    public Long getSecondId() {
        return second_id;
    }

    public void setSecondId(Long second_id) {
        this.second_id = second_id;
    }

    public Long getFirstId() {
        return first_id;
    }

    public void setFirstId(Long first_id) {
        this.first_id = first_id;
    }

    public Long getId() {
        return id;
    }
}

package com.graduate.restaurant_rating.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.graduate.restaurant_rating.HasId;

import javax.persistence.*;
import java.util.Objects;

/**
 * Created by Johann Stolz 14.08.2018
 */
@MappedSuperclass
public class AbstractBaseEntity implements HasId {
    public static final int SEQ_START = 100000;
    @Id
    @SequenceGenerator(name = "global_seq", sequenceName = "global_seq", initialValue = SEQ_START, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "global_seq")
    protected Integer id;

    public AbstractBaseEntity() {
    }

    public AbstractBaseEntity(Integer id) {
        this.id = id;
    }

    @JsonIgnore
    public boolean isNew() {
        return id == null;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AbstractBaseEntity that = (AbstractBaseEntity) o;
        return Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}

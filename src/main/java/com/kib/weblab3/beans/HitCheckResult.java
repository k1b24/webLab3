package com.kib.weblab3.beans;

import com.kib.weblab3.entity.TagEntity;
import lombok.*;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.persistence.*;
import javax.persistence.Table;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Objects;

import static javax.persistence.CascadeType.ALL;

@ManagedBean
@RequestScoped
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
@Table(name = "hit_check_result_table")
public class HitCheckResult {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Double x;

    private Double y = 0d;

    private Integer r;

    private Boolean hitResult;

    private ZonedDateTime date;

    private String sessionId;


    @ManyToMany(fetch = FetchType.LAZY, cascade = ALL)
    @JoinTable(name = "hit_tag_relation", joinColumns = @JoinColumn(name = "hit_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id", referencedColumnName = "id"))
    private List<TagEntity> tagEntities;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HitCheckResult that = (HitCheckResult) o;
        return id != null && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

}

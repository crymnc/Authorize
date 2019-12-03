package com.anatoliapark.nursinghome.model.base;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@MappedSuperclass
public class BaseEntity implements Serializable {

    private static final long serialVersionUID = 1122124123123L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
    protected Long id;

    @Column(name="discontinue_date")
    @Temporal(TemporalType.TIMESTAMP)
    protected Date discontinueDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) { this.id = id; }

    public Date getDiscontinueDate(){return discontinueDate;}

    public void setDiscontinueDate(Date discontinueDate){this.discontinueDate=discontinueDate;}

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (this.getId() != null ? this.getId().hashCode() : 0);

        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object)
            return true;
        if (object == null)
            return false;
        if (getClass() != object.getClass())
            return false;

        BaseEntity other = (BaseEntity) object;
        if (this.getId() != other.getId() && (this.getId() == null || !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return this.getClass().getName() + " [ID=" + id + "]";
    }

    public Boolean isNew(){
        return getId() == null;
    }
}

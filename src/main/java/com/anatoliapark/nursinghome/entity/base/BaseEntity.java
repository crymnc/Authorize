package com.anatoliapark.nursinghome.entity.base;

import com.anatoliapark.nursinghome.model.base.BaseModel;
import lombok.ToString;
import lombok.extern.log4j.Log4j2;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Log4j2
@ToString
@MappedSuperclass
public abstract class BaseEntity implements Serializable {

    private static final long serialVersionUID = 1122124123123L;

    public BaseEntity(BaseModel model){
        this.setId(model.getId());
        this.setDiscontinueDate(model.getDiscontinueDate());
    }

    public BaseEntity(){}

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Basic(optional = false)
    @Column(name = "id", nullable = false, updatable = false)
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

    public Boolean isNew(){
        return getId() == null;
    }

}

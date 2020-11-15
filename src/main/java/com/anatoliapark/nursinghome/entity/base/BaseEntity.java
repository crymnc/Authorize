package com.anatoliapark.nursinghome.entity.base;

import com.anatoliapark.nursinghome.annotation.ModelMapping;
import com.anatoliapark.nursinghome.model.base.BaseModel;
import lombok.ToString;
import lombok.extern.log4j.Log4j2;

import javax.persistence.*;
import java.io.Serializable;
import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.util.Date;

@Log4j2
@ToString
@MappedSuperclass
public abstract class BaseEntity implements Serializable {

    private static final long serialVersionUID = 1122124123123L;

    public BaseEntity(BaseModel model){
        this.setDiscontinueDate(model.getDiscontinueDate());
    }

    public BaseEntity(){}

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

    public Boolean isNew(){
        return getId() == null;
    }


    public <T extends BaseModel> T getModal() {
        try {
            Class<T> modelClass = getModalClass();
            for(Constructor constructor: modelClass.getConstructors()){
                if(constructor.getParameterCount() == 1){
                    return (T) constructor.newInstance(this);
                }
            }
            throw new RuntimeException("Model constructor with parameter is missing");
        } catch (Exception e) {
            log.error(e.getStackTrace());
        }
        return null;
    }

    public <T extends BaseModel> Class<T> getModalClass(){
        Annotation annotation = this.getClass().getAnnotation(ModelMapping.class);
        if(annotation != null){
            try {
                Class<T> modelClass = ((ModelMapping) annotation).modelClass();
                return modelClass;
            }
            catch (Exception e){
                log.error(e);
            }
        }
        else{
            throw new RuntimeException("ModelMapping annotation not found on entity");
        }
        return null;
    }
}

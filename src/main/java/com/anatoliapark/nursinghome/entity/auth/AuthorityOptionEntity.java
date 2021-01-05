package com.anatoliapark.nursinghome.entity.auth;

import com.anatoliapark.nursinghome.entity.base.BaseEntityAudit;
import com.anatoliapark.nursinghome.entity.webpage.WebPageComponentEntity;
import com.anatoliapark.nursinghome.entity.webpage.WebPageEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@Entity(name = "authority_option")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@DynamicUpdate
public class AuthorityOptionEntity extends BaseEntityAudit {

    @ManyToOne(cascade = {CascadeType.REMOVE, CascadeType.DETACH} )
    @JoinColumn(name = "authority_id", referencedColumnName = "id")
    private AuthorityEntity authority;

    @ManyToOne(cascade = {CascadeType.REMOVE, CascadeType.DETACH} )
    @JoinColumn(name = "component_id", referencedColumnName = "id")
    private WebPageComponentEntity component;

    @ManyToOne(cascade = {CascadeType.REMOVE, CascadeType.DETACH} )
    @JoinColumn(name = "page_id", referencedColumnName = "id")
    private WebPageEntity webPage;

    @Column(name = "active")
    private Boolean isActive;

    @Column(name = "visible")
    private Boolean isVisible;
}

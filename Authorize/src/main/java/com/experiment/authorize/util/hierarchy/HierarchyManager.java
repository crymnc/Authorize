package com.experiment.authorize.util.hierarchy;

import com.experiment.authorize.entity.UserComponentEntity;
import com.experiment.authorize.entity.auth.AuthorityEntity;
import com.experiment.authorize.entity.auth.AuthorityGroupEntity;
import com.experiment.authorize.entity.auth.RoleEntity;
import com.experiment.authorize.entity.webpage.WebPageComponentEntity;
import com.experiment.authorize.entity.webpage.WebPageComponentTypeEntity;
import com.experiment.authorize.entity.webpage.WebPageEntity;
import com.experiment.authorize.exception.BusinessExceptions;

import java.util.ArrayList;
import java.util.List;

public class HierarchyManager {
    private static final List<ConstantHierarchy> constants = new ArrayList<>();

    static {
        ConstantHierarchy roleHierarchy = new ConstantHierarchy();
        ConstantHierarchy userComponentHierarchy = new ConstantHierarchy();
        ConstantHierarchy authorityHierarchy = new ConstantHierarchy();
        ConstantHierarchy authorityGroupHierarchy = new ConstantHierarchy();
        ConstantHierarchy webPageComponentHierarchy = new ConstantHierarchy();
        ConstantHierarchy webPageComponentTypeHierarchy = new ConstantHierarchy();
        ConstantHierarchy webPageHierarchy = new ConstantHierarchy();

        roleHierarchy.getSubConstants().add(authorityGroupHierarchy);
        roleHierarchy.getSubConstants().add(userComponentHierarchy);
        roleHierarchy.setClazz(RoleEntity.class);
        roleHierarchy.setName("role");
        roleHierarchy.setDesc("Role");

        userComponentHierarchy.setClazz(UserComponentEntity.class);
        userComponentHierarchy.setName("usercomponent");
        userComponentHierarchy.setDesc("User Component");

        authorityHierarchy.setClazz(AuthorityEntity.class);
        authorityHierarchy.setName("authority");
        authorityHierarchy.setDesc("Authority");

        authorityGroupHierarchy.getSubConstants().add(authorityHierarchy);
        authorityGroupHierarchy.setName("authoritygroup");
        authorityGroupHierarchy.setDesc("Authority Group");
        authorityGroupHierarchy.setClazz(AuthorityGroupEntity.class);

        webPageComponentHierarchy.getSubConstants().add(webPageComponentTypeHierarchy);
        webPageComponentHierarchy.getSubConstants().add(webPageHierarchy);
        webPageComponentHierarchy.setClazz(WebPageComponentEntity.class);
        webPageComponentHierarchy.setName("webpagecomponent");
        webPageComponentTypeHierarchy.setDesc("Web Page Component");

        webPageComponentTypeHierarchy.setClazz(WebPageComponentTypeEntity.class);
        webPageComponentTypeHierarchy.setName("webpagecomponenttype");
        webPageComponentTypeHierarchy.setDesc("Web Page Component Type");

        webPageHierarchy.setClazz(WebPageEntity.class);
        webPageHierarchy.setName("webpage");
        webPageHierarchy.setDesc("Web Page");

        constants.add(roleHierarchy);
        constants.add(userComponentHierarchy);
        constants.add(authorityHierarchy);
        constants.add(authorityGroupHierarchy);
        constants.add(webPageComponentHierarchy);
        constants.add(webPageComponentTypeHierarchy);
        constants.add(webPageHierarchy);
    }

    public static ConstantHierarchy getHierarchyByName(String name){
        return constants.stream().filter(constantHierarchy -> constantHierarchy.name.equals(name)).findFirst().orElseThrow(() -> BusinessExceptions.CONSTANT_NAME_IS_NOT_ACCEPTABLE);
    }


}

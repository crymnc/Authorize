package com.anatoliapark.nursinghome.annotation;

import java.lang.annotation.*;

@Documented
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface EntityMapping {
    Class entityClass();
}

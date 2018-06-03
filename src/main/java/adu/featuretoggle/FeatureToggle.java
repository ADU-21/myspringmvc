package adu.featuretoggle;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface FeatureToggle {
    java.lang.String[] value() default { };
}

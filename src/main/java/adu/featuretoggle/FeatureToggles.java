package adu.featuretoggle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import java.util.Properties;

@Component
@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class FeatureToggles {
    public static final String TEST_TOGGLE = "test-toggle";
    public static final String FEATURE_HANDLER_ENABLE = "feature-handler-enable";

    private static final boolean ENABLE_BY_DEFAULT = false;

    private final Properties featureToggleProps;

    @Autowired
    public FeatureToggles(@Qualifier("featuresProperties") Properties featureToggleProps) {
        this.featureToggleProps = featureToggleProps;
    }

    public boolean enabled(String feature) {
        String toggle = toggleFor(feature);
        return Boolean.parseBoolean(toggle);
    }

    private String toggleFor(String feature) {
        return featureToggleProps.getProperty(feature, Boolean.toString(ENABLE_BY_DEFAULT));
    }
}


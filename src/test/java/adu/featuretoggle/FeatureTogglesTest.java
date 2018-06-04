package adu.featuretoggle;

import org.junit.Before;
import org.junit.Test;

import java.util.Properties;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class FeatureTogglesTest {
    private Properties featureToggleProps;
    private FeatureToggles featureToggles;

    @Before
    public void setUp() throws Exception {
        featureToggleProps = new Properties();
    }

    @Test
    public void allowsFeaturesToBeAlwaysEnabled() {
        featureToggleProps.setProperty("feature-always-enabled", "true");

        featureToggles = new FeatureToggles(featureToggleProps);
        assertTrue(featureToggles.enabled("feature-always-enabled"));
    }

    @Test
    public void allowsFeaturesToBeAlwaysDisabled() {
        featureToggleProps.setProperty("feature-always-disabled", "false");

        featureToggles = new FeatureToggles(featureToggleProps);
        assertFalse(featureToggles.enabled("feature-always-disabled"));
    }
}
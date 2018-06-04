package adu.featuretoggle;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class InMemoryFeatureToggles extends FeatureToggles {
    private final Set<String> enabledFeatures;

    public InMemoryFeatureToggles(String... enabledFeatures) {
        this.enabledFeatures = new HashSet<String>(Arrays.asList(enabledFeatures));
    }

    @Override
    public boolean enabled(String feature) {
        return enabledFeatures.contains(feature);
    }

    public void enable(String feature) {
        enabledFeatures.add(feature);
    }

    public void disable(String feature) {
        enabledFeatures.remove(feature);
    }
}

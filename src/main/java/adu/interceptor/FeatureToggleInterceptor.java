package adu.interceptor;

import adu.featuretoggle.FeatureToggle;
import adu.featuretoggle.FeatureToggles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class FeatureToggleInterceptor extends HandlerInterceptorAdapter {
    private final FeatureToggles featureToggles;

    @Autowired
    public FeatureToggleInterceptor(FeatureToggles featureToggles) {
        this.featureToggles = featureToggles;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception{
        if (handler instanceof HandlerMethod && !featureEnabled((HandlerMethod) handler)) {
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            return false;
        }
        return true;
    }

    private boolean featureEnabled(HandlerMethod method) {
        FeatureToggle annotation = method.getMethodAnnotation(FeatureToggle.class);
        boolean isFeatureEnabled = true;
        if (annotation == null) {
            return isFeatureEnabled;
        }
        for (String feature : annotation.value()) {
            isFeatureEnabled = isFeatureEnabled && featureToggles.enabled(feature);
        }
        return isFeatureEnabled;
    }
}

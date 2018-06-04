package adu.interceptor;

import adu.featuretoggle.FeatureToggle;
import adu.featuretoggle.InMemoryFeatureToggles;
import org.apache.commons.httpclient.HttpStatus;
import org.junit.Before;
import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.web.method.HandlerMethod;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class FeatureToggleInterceptorTest {
    private static final String FEATURE = "this-is-a-feature";

    private FeatureToggleInterceptor interceptor;
    private InMemoryFeatureToggles featureToggles;
    private MockHttpServletRequest request;
    private MockHttpServletResponse response;

    @Before
    public void setUp() throws Exception {
        featureToggles = new InMemoryFeatureToggles(FEATURE);
        interceptor = new FeatureToggleInterceptor(featureToggles);
        request = new MockHttpServletRequest();
        response = new MockHttpServletResponse();
    }

    @Test
    public void doesNothingWhenHandlerIsNotAMethod() throws Exception {
        boolean continueHanding = interceptor.preHandle(request, response, new Object());

        assertThat(continueHanding, is(true));
        assertThat(response.getStatus(), is(HttpStatus.SC_OK));
    }

    @Test
    public void doesNothingWhenHandlerIsNotAnnotated() throws Exception {
        boolean continueHanding = interceptor.preHandle(request, response,
                new HandlerMethod(new TestableController(), "unannotated"));

        assertThat(continueHanding, is(true));
        assertThat(response.getStatus(), is(HttpStatus.SC_OK));
    }

    @Test
    public void allowsAccessWhenFeatureIsEnabled() throws Exception{
        boolean continueHanding = interceptor.preHandle(request, response,
                new HandlerMethod(new TestableController(), "feature"));

        assertThat(continueHanding, is(true));
        assertThat(response.getStatus(), is(HttpStatus.SC_OK));
    }

    @Test
    public void set403ForbiddenWhenFeatureIsDisabled() throws Exception{
        featureToggles.disable(FEATURE);
        boolean continueHanding = interceptor.preHandle(request, response,
                new HandlerMethod(new TestableController(), "feature"));

        assertThat(continueHanding, is(false));
        assertThat(response.getStatus(), is(HttpStatus.SC_FORBIDDEN));
    }

    private class TestableController {
        public String unannotated() {
            return "/unannotated-view";
        }

        @FeatureToggle(FEATURE)
        public String feature() {
            return "/feature-view";
        }
    }
}
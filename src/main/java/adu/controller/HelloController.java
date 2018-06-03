package adu.controller;


import adu.featuretoggle.FeatureToggle;
import adu.featuretoggle.FeatureToggles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HelloController {
    private final FeatureToggles featureToggles;

    @Autowired
    public HelloController(FeatureToggles featureToggles) {
        this.featureToggles = featureToggles;
    }

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String printHello(ModelMap model) {

        String handlerFeatureToggle = String.valueOf(featureToggles.enabled(FeatureToggles.TEST_TOGGLE));
        model.addAttribute("msg", "Spring MVC Hello World");
        model.addAttribute("name", "adu");
        model.addAttribute("handlerFeatureToggle", handlerFeatureToggle);
        model.addAttribute("featureToggles", featureToggles);
        return "hello";
    }

    @FeatureToggle(FeatureToggles.FEATURE_HANDLER_ENABLE)
    @RequestMapping(value = "/feature", method = RequestMethod.GET)
    public String getFeature() {
        return "feature";
    }

}

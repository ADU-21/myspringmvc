package adu.testparameter;

import adu.domain.Number;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Test;
import org.junit.runner.RunWith;

import static junitparams.JUnitParamsRunner.$;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

@RunWith(JUnitParamsRunner.class)
public class TestParameter {

    private Object[] parametersForTestLowerThan4() {
        return $(
                $(Number.ONE),
                $(Number.TWO),
                $(Number.THREE)
        );
    }

    @Test
    @Parameters
    public void testLowerThan4(Number number) {
        assertThat(Integer.valueOf(number.getValue()) < 4, is(true));
    }
}

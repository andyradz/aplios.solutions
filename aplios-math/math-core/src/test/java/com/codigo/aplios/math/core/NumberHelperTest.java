package com.codigo.aplios.math.core;

import com.codigo.aplios.math.core.NumberHelper.DecimalPrecision;
import org.hamcrest.CoreMatchers;
import static org.hamcrest.MatcherAssert.assertThat;
import org.junit.Rule;
import org.junit.Test;

public class NumberHelperTest {

    @Rule
    public final JUnitStopWatch stopWatch = new JUnitStopWatch();

    @Test
    public void shouldReturnTrue() {

        final double myFraction = NumberHelper.fraction(1.7976931348623157, DecimalPrecision.PRECTO6);

        assertThat(0.797693d, CoreMatchers.equalTo(myFraction));
    }

    // -----------------------------------------------------------------------------------------------------------------
}

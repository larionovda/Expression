package com.larionov.epam;

import com.larionov.epam.negative.TestNegativeCalculator;
import com.larionov.epam.positive.TestPositiveCalculator;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.runner.RunWith;

@RunWith(JUnitPlatform.class)
@SelectClasses({TestNegativeCalculator.class, TestPositiveCalculator.class})
public class TestSuite {
}

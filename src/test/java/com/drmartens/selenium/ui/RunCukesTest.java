package com.drmartens.selenium.ui;

import org.junit.jupiter.api.BeforeAll;
import org.junit.platform.suite.api.*;
import static io.cucumber.junit.platform.engine.Constants.*;

@Suite
@IncludeEngines("cucumber")
@IncludeTags("debug")
@SelectClasspathResource("features")
@ConfigurationParameter(key = GLUE_PROPERTY_NAME, value = "com.drmartens.selenium.ui.step_def")
@ConfigurationParameter(key = PLUGIN_PROPERTY_NAME, value = "pretty, json:reports/tests/cucumber/json/cucumberTestReport.json, html:target/cucumber-reports/cucumber.html, junit:target/cucumber-reports/junit_report.xml")
public class RunCukesTest {
//    @BeforeAll
//    public static void setup() {
//        String tags = System.getProperty(FILTER_TAGS_PROPERTY_NAME, "@defaultTag");
//        System.setProperty(FILTER_TAGS_PROPERTY_NAME, tags);
//    }
}
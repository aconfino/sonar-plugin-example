package com.confino.sonar.differential;

import java.util.Arrays;
import java.util.List;

import org.sonar.api.SonarPlugin;

import com.confino.sonar.differential.batch.DifferentialMetrics;
import com.confino.sonar.differential.batch.DifferentialSensor;
import com.confino.sonar.differential.ui.DifferentialCoverageRubyWidget;

/**
 * This class is the entry point for all extensions
 */
public final class DifferentialPlugin extends SonarPlugin {

// This is where you're going to declare all your Sonar extensions
@SuppressWarnings({ "unchecked", "rawtypes" })
public List getExtensions() {
    return Arrays.asList(
    		DifferentialMetrics.class, 
    		DifferentialSensor.class,
    		DifferentialCoverageRubyWidget.class);
  }
}

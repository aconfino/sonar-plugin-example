package com.confino.sonar.differential.batch;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.RandomUtils;
import org.sonar.api.batch.Decorator;
import org.sonar.api.batch.DecoratorContext;
import org.sonar.api.measures.MeasureUtils;
import org.sonar.api.resources.Java;
import org.sonar.api.resources.Project;
import org.sonar.api.resources.Resource;
import org.sonar.api.resources.Scopes;

public class DifferentialDecorator implements Decorator {

  public boolean shouldExecuteOnProject(Project project) {
    // Execute only on Java projects
    return StringUtils.equals(project.getLanguageKey(), Java.KEY);
  }

  public void decorate(Resource resource, DecoratorContext context) {
    // This method is executed on the whole tree of resources.
    // Bottom-up navigation : Java methods -> Java classes -> files -> packages -> modules -> project
	  if(Scopes.isProject(resource)){
		  double sample = 95;
		  context.saveMeasure(DifferentialMetrics.DIFFERENTIAL, sample);
	  }
  }

  @Override
  public String toString() {
    return getClass().getSimpleName();
  }

}

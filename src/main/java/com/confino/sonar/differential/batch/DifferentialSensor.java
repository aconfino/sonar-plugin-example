package com.confino.sonar.differential.batch;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.sonar.api.batch.Sensor;
import org.sonar.api.batch.SensorContext;
import org.sonar.api.config.Settings;
import org.sonar.api.resources.Project;
import org.sonar.api.resources.Resource;
import org.sonar.api.resources.Scopes;
import org.sonar.api.rules.Rule;
import org.sonar.api.rules.RulePriority;
import org.sonar.api.rules.Violation;

public class DifferentialSensor implements Sensor {

	private Settings settings;
	private List<Resource> fileResources = new ArrayList<Resource>();

	public DifferentialSensor(Settings settings) {
		this.settings = settings;
	}

	@SuppressWarnings("rawtypes")
	public void analyse(Project project, SensorContext context) {
		Collection<Resource> resources = context.getChildren(project);
		for (Resource resource : resources) {
			getJavaResources(resource, context);
		}
		addMetrics(fileResources, context);
	}

	@SuppressWarnings("rawtypes")
	public void getJavaResources(Resource resource, SensorContext context) {
		if (Scopes.isFile(resource)) {
			fileResources.add(resource);
		}
		Collection<Resource> children = context.getChildren(resource);
		for (Resource child : children) {
			getJavaResources(child, context);
		}
	}

	public void addMetrics(Collection<Resource> javaResources,
			SensorContext context) {
		for (Resource resource : javaResources) {
			if (resource.getLongName().equals("com.confino.gav.notifier.GavNotifier")) {
				context.saveMeasure(resource, DifferentialMetrics.DIFFERENTIAL,new Double(80));
				context.saveViolation(getViolation(resource, 20));
			}
			if (resource.getLongName().equals("com.confino.gav.notifier.MavenUtils")) {
				context.saveMeasure(resource, DifferentialMetrics.DIFFERENTIAL,new Double(70));
				context.saveViolation(getViolation(resource, 20));
			}
			if (resource.getLongName().equals("com.confino.gav.notifier.NotificationService")) {
				context.saveMeasure(resource, DifferentialMetrics.DIFFERENTIAL, new Double(60));
				context.saveViolation(getViolation(resource, 20));
			}
			if (resource.getLongName().equals("com.confino.gav.notifier.ProjectInfo")) {
				context.saveMeasure(resource, DifferentialMetrics.DIFFERENTIAL,new Double(50));
				context.saveViolation(getViolation(resource, 20));
			}
		}
	}

	public Violation getViolation(Resource resource, Integer lineId) {
		Rule simpleRule = Rule.create(MyRuleConstants.REPOSITORY_KEY, MyRuleConstants.RULE_KEY, MyRuleConstants.RULE_NAME);
		simpleRule.setSeverity(RulePriority.CRITICAL);
		return Violation.create(simpleRule, resource).setLineId(lineId).setMessage("Can't do that jack");
	}

	public boolean shouldExecuteOnProject(Project project) {
		return true;
	}

	@Override
	public String toString() {
		return getClass().getSimpleName();
	}

}
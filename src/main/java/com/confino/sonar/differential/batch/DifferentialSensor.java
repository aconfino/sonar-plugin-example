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

public class DifferentialSensor implements Sensor {

	private Settings settings;
	private List<Resource> javaResources = new ArrayList<Resource>();
	
	public DifferentialSensor(Settings settings) {
	    this.settings = settings;
	  }
	
	@SuppressWarnings("rawtypes")
	public void analyse(Project project, SensorContext context) {
		Collection<Resource> resources = context.getChildren(project);
		for (Resource resource : resources){
			getJavaResources(resource, context);
		}
		for (Resource javaResource : javaResources){
			System.out.println(javaResource.getName());
		}
	}
	
	@SuppressWarnings("rawtypes")
	public void getJavaResources(Resource resource, SensorContext context){
		 if(Scopes.isProgramUnit(resource)){
			 javaResources.add(resource);
		 } 
		Collection<Resource> children = context.getChildren(resource);
		 for (Resource child : children){
			 getJavaResources(child, context);
		 }	 
	}
	
	public void printResource(Resource resource, String title){
		if (resource != null){
			System.out.println(title + resource.getName());
			if(Scopes.isProgramUnit(resource)){
				System.out.println("Java class or interface");
			}
		}
	}

	public boolean shouldExecuteOnProject(Project project) {
		return true;
	}

	@Override
	public String toString() {
		return getClass().getSimpleName();
	}

}
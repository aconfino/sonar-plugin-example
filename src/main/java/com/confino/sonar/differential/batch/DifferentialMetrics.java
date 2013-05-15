package com.confino.sonar.differential.batch;

import java.util.Arrays;
import java.util.List;

import org.sonar.api.measures.AverageFormula;
import org.sonar.api.measures.CoreMetrics;
import org.sonar.api.measures.MeanAggregationFormula;
import org.sonar.api.measures.Metric;
import org.sonar.api.measures.Metrics;

public final class DifferentialMetrics implements Metrics {

	public static final Metric DIFFERENTIAL = new Metric.Builder("differential_coverage", "Differential Coverage",
			Metric.ValueType.FLOAT).setDescription("Differential Coverage value")
			.setDirection(Metric.DIRECTION_BETTER).setQualitative(false)
			.setDomain(CoreMetrics.DOMAIN_SIZE).setFormula(new MeanAggregationFormula())
			.create();

	// getMetrics() method is defined in the Metrics interface and is used by
	// Sonar to retrieve the list of new metrics
	public List<Metric> getMetrics() {
		return Arrays.asList(DIFFERENTIAL);
	}
}

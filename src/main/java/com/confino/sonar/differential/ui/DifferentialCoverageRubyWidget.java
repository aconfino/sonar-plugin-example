package com.confino.sonar.differential.ui;

import org.sonar.api.web.AbstractRubyTemplate;
import org.sonar.api.web.Description;
import org.sonar.api.web.RubyRailsWidget;
import org.sonar.api.web.UserRole;
import org.sonar.api.web.WidgetCategory;
import org.sonar.api.web.WidgetProperties;
import org.sonar.api.web.WidgetProperty;
import org.sonar.api.web.WidgetPropertyType;

@UserRole(UserRole.USER)
@Description("Shows the differential coverage of a project")
@WidgetCategory("Differential Coverage")

public class DifferentialCoverageRubyWidget extends AbstractRubyTemplate implements RubyRailsWidget {

  public String getId() {
    return "differentialCoverage";
  }

  public String getTitle() {
    return "Differential Coverage";
  }

  @Override
  protected String getTemplatePath() {
    return "/coverage/differential_coverage.html.erb";
  }
}

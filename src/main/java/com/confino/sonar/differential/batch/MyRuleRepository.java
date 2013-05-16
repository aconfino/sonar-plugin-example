package com.confino.sonar.differential.batch;

import java.util.List;

import org.sonar.api.resources.Java;
import org.sonar.api.rules.Rule;
import org.sonar.api.rules.RuleRepository;

import com.google.common.collect.Lists;

public class MyRuleRepository extends RuleRepository {

	public MyRuleRepository() {
		 super(MyRuleConstants.REPOSITORY_KEY, Java.KEY);
		 setName(MyRuleConstants.REPOSITORY_NAME);
	}

	@Override
	public List<Rule> createRules() {
		List<Rule> rules = Lists.newArrayList();
		Rule simpleRule = Rule.create(MyRuleConstants.REPOSITORY_KEY, MyRuleConstants.RULE_KEY, MyRuleConstants.RULE_NAME);
		simpleRule.setDescription("You can't do that Jack!");
		rules.add(simpleRule);
	    return rules;
	}

}

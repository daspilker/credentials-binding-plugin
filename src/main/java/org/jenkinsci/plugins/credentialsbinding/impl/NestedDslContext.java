package org.jenkinsci.plugins.credentialsbinding.impl;

import javaposse.jobdsl.dsl.Context;

import java.util.HashMap;
import java.util.Map;

public class NestedDslContext implements Context {
    final Map<String, String> variables = new HashMap<String, String>();

    public void user(String variable, String credentialsId) {
        variables.put(variable, credentialsId);
    }
}

package org.jenkinsci.plugins.credentialsbinding.impl;

import hudson.Extension;
import javaposse.jobdsl.dsl.DslExtensionMethod;
import javaposse.jobdsl.dsl.helpers.wrapper.WrapperContext;
import javaposse.jobdsl.plugin.ContextExtensionPoint;

import java.util.Collections;

@Extension(optional = true)
public class JobDslExtension extends ContextExtensionPoint {
    @DslExtensionMethod(context = WrapperContext.class)
    public Object creds(String variable, String credentialsId) {
        return new SecretBuildWrapper(Collections.singletonList(new StringBinding(variable, credentialsId)));
    }
}

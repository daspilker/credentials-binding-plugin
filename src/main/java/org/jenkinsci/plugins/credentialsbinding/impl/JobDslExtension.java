package org.jenkinsci.plugins.credentialsbinding.impl;

import hudson.Extension;
import javaposse.jobdsl.dsl.DslExtensionMethod;
import javaposse.jobdsl.dsl.helpers.wrapper.WrapperContext;
import javaposse.jobdsl.plugin.ContextExtensionPoint;
import org.jenkinsci.plugins.credentialsbinding.MultiBinding;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Extension(optional = true)
public class JobDslExtension extends ContextExtensionPoint {
    @DslExtensionMethod(context = WrapperContext.class)
    public Object creds(String variable, String credentialsId) {
        return new SecretBuildWrapper(Collections.singletonList(new StringBinding(variable, credentialsId)));
    }

    @DslExtensionMethod(context = WrapperContext.class)
    public Object creds(Runnable context) {
        NestedDslContext nestedDslContext = new NestedDslContext();
        executeInContext(context, nestedDslContext);

        List<MultiBinding<?>> bindings = new ArrayList<MultiBinding<?>>();
        for (Map.Entry<String, String> entry : nestedDslContext.variables.entrySet()) {
            bindings.add(new StringBinding(entry.getKey(), entry.getValue()));
        }

        return new SecretBuildWrapper(bindings);
    }
}

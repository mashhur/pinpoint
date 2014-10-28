package com.nhn.pinpoint.profiler.modifier.redis.filter;

import java.lang.reflect.Modifier;
import java.util.Set;

import com.nhn.pinpoint.bootstrap.instrument.Method;
import com.nhn.pinpoint.bootstrap.instrument.MethodFilter;

/**
 * Name based on method filter
 * 
 * @author jaehong.kim
 *
 */
public class NameBasedMethodFilter implements MethodFilter {
    private final Set<String> methodNames;
    
    public NameBasedMethodFilter(Set<String> methodNames) {
        this.methodNames = methodNames;
    }
    
    @Override
    public boolean filter(Method ctMethod) {
        final int modifiers = ctMethod.getModifiers();
        if (!Modifier.isPublic(modifiers) || Modifier.isStatic(modifiers) || Modifier.isAbstract(modifiers) || Modifier.isNative(modifiers)) {
            return true;
        }

        if (methodNames.contains(ctMethod.getName())) {
            return false;
        }

        return true;
    }
}
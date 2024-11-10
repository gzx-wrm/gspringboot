package com.gzx.gspringboot.server;

import com.gzx.gspringboot.annotation.ConditionalOnClass;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

import java.util.Map;

public class OnClassCondition implements Condition {
    @Override
    public boolean matches(ConditionContext conditionContext, AnnotatedTypeMetadata annotatedTypeMetadata) {
        Map<String, Object> conditionalMetaData = annotatedTypeMetadata.getAnnotationAttributes(ConditionalOnClass.class.getName());
        String className = (String) conditionalMetaData.get("value");

        try {
            conditionContext.getClass().getClassLoader().loadClass(className);
            return true;
        } catch (ClassNotFoundException e) {
            return false;
        }
    }
}

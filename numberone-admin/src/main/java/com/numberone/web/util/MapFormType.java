package com.numberone.web.util;

import org.activiti.engine.form.AbstractFormType;

public class MapFormType extends AbstractFormType {
    @Override
    public Object convertFormValueToModelValue(String propertyValue) {
        return propertyValue;
    }

    @Override
    public String convertModelValueToFormValue(Object modelValue) {
        return modelValue.toString();
    }

    @Override
    public String getName() {
        return "map";
    }
}

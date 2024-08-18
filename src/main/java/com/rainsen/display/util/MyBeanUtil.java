package com.rainsen.display.util;

import org.springframework.beans.BeanUtils;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;

public class MyBeanUtil {

    private static PropertyDescriptor getTargetDescriptor(PropertyDescriptor[] targetDescriptors, String propertyName) {
        for (PropertyDescriptor targetDescriptor : targetDescriptors) {
            if (targetDescriptor.getName().equals(propertyName)) {
                return targetDescriptor;
            }
        }
        return null;
    }

    public static void copyProperties(Object source, Object target) {
        PropertyDescriptor[] sourceDescriptors = BeanUtils.getPropertyDescriptors(source.getClass());
        PropertyDescriptor[] targetDescriptors = BeanUtils.getPropertyDescriptors(target.getClass());
        for (PropertyDescriptor sd : sourceDescriptors) {
            try {
                Method sourceGetter = sd.getReadMethod();
                if (sourceGetter == null) continue;

                PropertyDescriptor targetDescriptor = getTargetDescriptor(targetDescriptors, sd.getName());
                if (targetDescriptor == null) continue;

                Method targetSetter = targetDescriptor.getWriteMethod();
                if (targetSetter == null) continue;

                Object sourceValue = sourceGetter.invoke(source);
                Object targetValue = targetDescriptor.getReadMethod().invoke(target);

                if (sourceValue != null || targetValue != null) {
                    targetSetter.invoke(target, sourceValue);
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }
}

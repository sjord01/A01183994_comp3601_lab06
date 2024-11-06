package ca.bcit.a01183994.lab06.util;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.BeanUtils;

public class BeanUtilities {

	public static void populateBean(Object formBean, HttpServletRequest request) {
		populateBean(formBean, request.getParameterMap());
	}

	public static void populateBean(Object bean, Map<String, String[]> propertyMap) {
		try {
			BeanUtils.populate(bean, propertyMap);
		} catch (Exception e) {
			System.err.println(e);
		}
	}
}

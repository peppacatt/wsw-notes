package com.peppacatt.notes.spring_source_code_5x.bean;

import com.peppacatt.notes.spring_source_code_5x.entity.MyTestBean;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;

@SuppressWarnings("deprecation")
public class BeanFactoryTest {
    @Test
    void t() {
        XmlBeanFactory xmlBeanFactory = new XmlBeanFactory(new ClassPathResource("myTestXml.xml"));
        MyTestBean bean = xmlBeanFactory.getBean("myTestBean", MyTestBean.class);
        System.out.println(bean.getName());
    }
}

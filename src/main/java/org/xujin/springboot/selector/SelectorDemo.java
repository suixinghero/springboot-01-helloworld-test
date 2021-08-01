package org.xujin.springboot.selector;

import org.springframework.context.annotation.DeferredImportSelector;
import org.springframework.core.type.AnnotationMetadata;

/**
 * @author suixing
 * @date 2020-08-25-5:41 PM
 */
public class SelectorDemo implements DeferredImportSelector {
    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {
        return new String[]{"org.xujin.service.XuJinService"};
    }
}

package org.xujin.springboot;

import java.lang.annotation.*;

/**
 * @author suixing
 * @date 2021-03-19-5:53 PM
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.TYPE})
@Documented
public @interface ScoreRanking
{

    String value() default "";
}
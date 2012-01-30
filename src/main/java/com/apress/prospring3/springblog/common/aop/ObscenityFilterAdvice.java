/**
 * Created on Oct 3, 2011
 */
package com.apress.prospring3.springblog.common.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Component;

import com.apress.prospring3.springblog.domain.BlogPosting;

/**
 * @author Clarence
 *
 */
@Component
@Configurable
@Aspect
public class ObscenityFilterAdvice {

	@Autowired
    private ObscenityFilter obscenityFilter;

    public void setObscenityFilter(ObscenityFilter obscenityFilter) {
        this.obscenityFilter = obscenityFilter;       
    }

    @Before("execution(* com.apress.prospring3.springblog.service..*(..))")
    public void filterObscenities(JoinPoint joinPoint)
            throws Throwable {
    	Object[] args = joinPoint.getArgs();
        for (int x = 0; x < args.length; x++) {
            if (args[x] instanceof BlogPosting) {
                BlogPosting arg = (BlogPosting) args[x];
                if (obscenityFilter.containsObscenities(arg.getBody())) {
                    arg.setBody(obscenityFilter.obfuscateObscenities(arg.getBody()));
                }
                if (obscenityFilter.containsObscenities(arg.getSubject())) {
                    arg.setSubject(obscenityFilter
                            .obfuscateObscenities(arg.getSubject()));
                }
            }
        }
    }
	
}

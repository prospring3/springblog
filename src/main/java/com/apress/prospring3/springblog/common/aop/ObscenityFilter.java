/**
 * Created on Oct 3, 2011
 */
package com.apress.prospring3.springblog.common.aop;

/**
 * @author Clarence
 *
 */
public interface ObscenityFilter {

    public boolean containsObscenities(String data);
    
    public String obfuscateObscenities(String data);	
}

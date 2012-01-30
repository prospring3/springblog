/**
 * Created on Oct 3, 2011
 */
package com.apress.prospring3.springblog.common.aop;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author Clarence
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/app-context-test.xml"})
public class ObscenityFilterTest {

	@Autowired
	private ObscenityFilter obscenityFilter;
	
    @Test
    public void testReplaceObscenity() {

    	String testData = "Crap! Kiss my arse, you damn bugger!";

    	assertTrue("Test data should contain obscenities", obscenityFilter.containsObscenities(testData));

    	String val = obscenityFilter.obfuscateObscenities(testData);

    	System.out.println(val);

    	assertTrue(val.indexOf("arse") == -1); 
    	assertTrue(val.indexOf("Crap") == -1); 
    	assertTrue(val.indexOf("damn") == -1); 
    	assertTrue(val.indexOf("bugger") == -1); 
    	assertTrue(val.indexOf("Kiss") > -1);     	
    }
	
}

package org.jarsearcher.utils;

import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author jica
 */
public class ConfigurationTest {

    @Test
    public void getExistingProperty(){
        Assert.assertEquals(".",Configuration.getProperty(Configuration.PROP_DEFAULT_PATH));
        Assert.assertEquals("org.jarsearcher",Configuration.getProperty(Configuration.PROP_DEFAULT_QUERY));
    }

    @Test
    public void getNotExistingProperty(){
        Assert.assertEquals(null,Configuration.getProperty("NOT_EXISTS"));
    }
    
}

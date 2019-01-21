package com.appdynamics.extensions.apache;

import com.singularity.ee.agent.systemagent.api.exception.TaskExecutionException;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author Satish Muddam
 */

public class Dummy2IT {

    @Test
    public void test() throws TaskExecutionException {
        Assert.assertEquals(4, 2 + 2);

    }
}

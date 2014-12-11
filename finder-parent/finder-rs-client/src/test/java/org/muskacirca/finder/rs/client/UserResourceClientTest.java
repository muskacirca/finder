package org.muskacirca.finder.rs.client;


import com.finder.model.ws.EAppMessage;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class UserResourceClientTest {

    @Test
    public void test() {

        UserResourceClient client = new UserResourceClient();
        ServiceResponse muskacirca = client.authenticate("muskacirca", "1234");
        assertEquals(EAppMessage.SUCCESS, muskacirca.getAppMessage());
    }

}
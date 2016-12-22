package org.eurekaclinical.emorysendtoehcdwh.resource;

/*-
 * #%L
 * Emory Send-to-EHCDWH Service
 * %%
 * Copyright (C) 2016 Emory University
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */
import com.sun.jersey.api.client.ClientResponse;
import java.io.IOException;
import java.io.InputStream;
import javax.ws.rs.core.MediaType;
import org.eurekaclinical.common.comm.clients.ClientException;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

/**
 *
 * @author Andrew Post
 */
public class PatientSetResourceTest extends AbstractEmorySendToEHCDWHResourceTest {

    @Test
    public void testPostPatientSet() throws IOException, ClientException {

        try (InputStream input = getClass().getResourceAsStream("/testFull.json")) {
            ClientResponse response = this.resource()
                    .path("/api/protected/patientsets")
                    .accept(MediaType.APPLICATION_JSON)
                    .type(MediaType.APPLICATION_JSON)
                    .post(ClientResponse.class, input);
            assertEquals(ClientResponse.Status.CREATED, response.getClientResponseStatus());
        }
    }

}

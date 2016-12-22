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

import com.google.inject.persist.Transactional;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URI;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.StreamingOutput;
import org.eurekaclinical.patientset.client.PatientSetJsonReader;
import org.eurekaclinical.patientset.client.PatientSetJsonWriter;
import org.eurekaclinical.standardapis.exception.HttpStatusException;

/**
 *
 * @author Andrew Post
 */
@Transactional
@Path("/protected/patientsets")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class PatientSetResource {

    @POST
    public Response create(InputStream is) {
        try (PatientSetJsonReader reader = new PatientSetJsonReader(is)) {
            while (reader.hasMorePatients()) {
                System.out.println("patient id " + reader.nextPatientId());
            }
            System.out.println("name: " + reader.getName());
            System.out.println("username: " + reader.getUsername());
        } catch (IOException ex) {
            ex.printStackTrace();
            throw new HttpStatusException(Response.Status.BAD_REQUEST, ex);
        }
        return Response.created(URI.create("/" + 1)).build();
    }

    @GET
    @Path("/{id}")
    public Response getPatientSet(@PathParam("id") Long id) {
        if (true) {
            throw new HttpStatusException(Response.Status.NOT_FOUND);
        }
        StreamingOutput stream = (OutputStream os) -> {
            try (PatientSetJsonWriter writer = new PatientSetJsonWriter(os, "query", "username")) {
                writer.writePatient("patientId");
            }
            os.flush();
        };
        return Response.ok(stream).build();
    }
}

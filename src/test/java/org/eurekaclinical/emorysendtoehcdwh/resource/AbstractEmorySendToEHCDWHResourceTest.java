/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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

import com.google.inject.Module;
import javax.servlet.ServletContextListener;
import org.eurekaclinical.emorysendtoehcdwh.config.AppTestModule;
import org.eurekaclinical.emorysendtoehcdwh.config.ContextTestListener;
import org.eurekaclinical.emorysendtoehcdwh.test.AbstractResourceTest;
import org.eurekaclinical.emorysendtoehcdwh.test.Setup;
import org.eurekaclinical.emorysendtoehcdwh.test.TestDataProvider;

/**
 *
 * @author Andrew Post
 */
public abstract class AbstractEmorySendToEHCDWHResourceTest extends AbstractResourceTest {
    @Override
    protected final Class<? extends ServletContextListener> getListener() {
        return ContextTestListener.class;
    }

    @Override
    protected Class<? extends TestDataProvider> getDataProvider() {
        return Setup.class;
    }

    @Override
    protected Module[] getModules() {
        return new Module[]{new AppTestModule()};
    }
}

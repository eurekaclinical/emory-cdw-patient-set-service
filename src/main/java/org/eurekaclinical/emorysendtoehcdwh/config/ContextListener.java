package org.eurekaclinical.emorysendtoehcdwh.config;

/*-
 * #%L
 * emory-send-to-ehcdwh
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

import com.google.inject.Injector;
import com.google.inject.Module;
import com.google.inject.persist.jpa.JpaPersistModule;
import com.google.inject.servlet.GuiceServletContextListener;
import org.eurekaclinical.common.config.InjectorSupport;
import org.eurekaclinical.emorysendtoehcdwh.props.ServiceProperties;

import javax.servlet.ServletContextEvent;
import java.util.ResourceBundle;

/**
 * Created by akalsan on 10/4/16.
 */
public class ContextListener extends GuiceServletContextListener {
	private static final String JPA_UNIT = "service-jpa-unit";
	private InjectorSupport injectorSupport;
	private static final ResourceBundle projectNameProperty=ResourceBundle.getBundle("main");
	ServiceProperties properties = new ServiceProperties(projectNameProperty.getString("module-name"));

	@Override
	protected Injector getInjector() {
        /*
         * Must be created here in order for the modules to initialize
         * correctly.
         */
		if (this.injectorSupport == null) {
			this.injectorSupport = new InjectorSupport(
					new Module[]{
							new AppModule(),
							new ServletModule(this.properties),
							new JpaPersistModule(JPA_UNIT)
					},
					this.properties);
		}
		return this.injectorSupport.getInjector();
	}
}

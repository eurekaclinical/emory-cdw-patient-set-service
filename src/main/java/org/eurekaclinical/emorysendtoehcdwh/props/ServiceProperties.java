package org.eurekaclinical.emorysendtoehcdwh.props;

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

/**
 * Created by akalsan on 10/4/16.
 */
import org.eurekaclinical.standardapis.props.CasEurekaClinicalProperties;
public class ServiceProperties extends CasEurekaClinicalProperties {
	public ServiceProperties(String projectName){
		super("/etc/"+projectName);
	}

	@Override
	public String getProxyCallbackServer() {
		return getValue("eurekaclinical."+this.getProjectName()+".service.callbackserver");
	}
	public String getProjectName() {
		return getValue("eurekaclinical.project.name");
	}
    @Override
	public String getUrl() {
		return getValue("eurekaclinical."+this.getProjectName()+".service.url");
	}

}

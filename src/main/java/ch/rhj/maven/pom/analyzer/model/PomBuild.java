package ch.rhj.maven.pom.analyzer.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PomBuild {

	@JsonProperty("pluginManagement")
	private PomPluginManagement pluginManagement;

	public PomPluginManagement pluginManagement() {

		return pluginManagement == null ? (pluginManagement = new PomPluginManagement()) : pluginManagement;
	}
}

package ch.rhj.maven.pom.analyzer.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PomModel {

	@JsonProperty("build")
	public PomBuild build;

	@JsonProperty("dependencyManagement")
	public PomDependencyManagement dependencyManagement;
}

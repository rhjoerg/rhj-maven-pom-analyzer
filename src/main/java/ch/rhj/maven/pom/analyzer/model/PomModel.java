package ch.rhj.maven.pom.analyzer.model;

import java.util.List;
import java.util.stream.Stream;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PomModel {

	@JsonProperty("build")
	private PomBuild build;

	@JsonProperty("dependencyManagement")
	private PomDependencyManagement dependencyManagement;

	@JsonProperty("dependencies")
	private List<PomDependency> dependencies;

	public PomBuild build() {

		return build == null ? (build = new PomBuild()) : build;
	}

	public PomDependencyManagement dependencyManagement() {

		return dependencyManagement == null ? (dependencyManagement = new PomDependencyManagement()) : dependencyManagement;
	}

	public Stream<PomDependency> dependencies() {

		return dependencies == null ? Stream.empty() : dependencies.stream();
	}

	public PomDependency dependency(String name) {

		return dependencies().filter(p -> p.name().equals(name)).findFirst().orElse(null);
	}
}

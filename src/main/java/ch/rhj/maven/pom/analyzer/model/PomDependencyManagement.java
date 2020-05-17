package ch.rhj.maven.pom.analyzer.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PomDependencyManagement {

	@JsonProperty("dependencies")
	public List<PomDependency> dependencies;

	public PomDependency dependency(String name) {

		return dependencies.stream().filter(p -> p.name().equals(name)).findFirst().orElse(null);
	}
}

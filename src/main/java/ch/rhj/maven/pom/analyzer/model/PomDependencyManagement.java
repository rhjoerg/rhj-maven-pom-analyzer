package ch.rhj.maven.pom.analyzer.model;

import java.util.List;
import java.util.stream.Stream;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PomDependencyManagement {

	@JsonProperty("dependencies")
	private List<PomDependency> dependencies;

	public Stream<PomDependency> dependencies() {

		return dependencies == null ? Stream.empty() : dependencies.stream();
	}

	public PomDependency dependency(String name) {

		return dependencies().filter(p -> p.name().equals(name)).findFirst().orElse(null);
	}
}

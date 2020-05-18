package ch.rhj.maven.pom.analyzer.model;

import java.util.List;
import java.util.stream.Stream;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PomPluginManagement {

	@JsonProperty("plugins")
	private List<PomPlugin> plugins;

	public Stream<PomPlugin> plugins() {

		return plugins == null ? Stream.empty() : plugins.stream();
	}

	public PomPlugin plugin(String name) {

		return plugins().filter(p -> p.name().equals(name)).findFirst().orElse(null);
	}
}

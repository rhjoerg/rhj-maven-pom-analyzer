package ch.rhj.maven.pom.analyzer.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PomPluginManagement {

	@JsonProperty("plugins")
	public List<PomPlugin> plugins;

	public PomPlugin plugin(String name) {

		return plugins.stream().filter(p -> p.name().equals(name)).findFirst().orElse(null);
	}
}

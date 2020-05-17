package ch.rhj.maven.pom.analyzer.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PomArtifact {

	@JsonProperty("groupId")
	public PomString groupId;

	@JsonProperty("artifactId")
	public PomString artifactId;

	@JsonProperty("version")
	public PomString version;

	public String groupId() {

		return groupId == null ? "org.apache.maven.plugins" : groupId.value;
	}

	public String artifactId() {

		return artifactId == null ? null : artifactId.value;
	}

	public String name() {

		return String.format("%1$s:%2$s", groupId(), artifactId());
	}

	public String version() {

		return version == null ? null : version.value;
	}

}

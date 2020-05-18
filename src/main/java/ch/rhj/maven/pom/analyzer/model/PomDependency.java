package ch.rhj.maven.pom.analyzer.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PomDependency extends PomArtifact {
}

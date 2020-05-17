package ch.rhj.maven.pom.analyzer;

import static java.util.stream.Collectors.toSet;

import java.io.File;
import java.nio.file.Paths;
import java.util.Set;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import ch.rhj.maven.pom.analyzer.model.PomDependencyManagement;
import ch.rhj.maven.pom.analyzer.model.PomModel;
import ch.rhj.maven.pom.analyzer.model.PomPluginManagement;

public class PomAnalyzer {

	public static final File pomFile1 = Paths.get("samples", "rhj-java-parent-effective-pom.xml").toFile();
	public static final File pomFile2 = Paths.get("samples", "spring-boot-starter-effective-pom.xml").toFile();

	public static void main(String[] args) throws Exception {

		XmlMapper mapper = new XmlMapper();

		PomModel src = mapper.readValue(pomFile1, PomModel.class);
		PomModel ref = mapper.readValue(pomFile2, PomModel.class);

		int conflicts = 0;

		conflicts += analyzePluginManagement(src, ref);
		conflicts += analyzeDependencyManagement(src, ref);

		System.out.println(String.format("%1$d total conflicts", conflicts));
	}

	private static int analyzePluginManagement(PomModel src, PomModel ref) {

		System.out.println("Analyzing pluginManagement");

		int conflicts = 0;

		PomPluginManagement srcPluginManagement = src.build.pluginManagement;
		PomPluginManagement refPluginManagement = ref.build.pluginManagement;

		Set<String> srcNames = srcPluginManagement.plugins.stream().map(p -> p.name()).collect(toSet());
		Set<String> refNames = refPluginManagement.plugins.stream().map(p -> p.name()).collect(toSet());

		srcNames.retainAll(refNames);

		for (String name : srcNames) {

			String srcVersion = srcPluginManagement.plugin(name).version();
			String refVersion = refPluginManagement.plugin(name).version();

			if (!srcVersion.equals(refVersion)) {

				System.out.println(String.format("- %1$s [%2$s] vs [%3$s]", name, srcVersion, refVersion));
				++conflicts;
			}
		}

		return conflicts;
	}

	private static int analyzeDependencyManagement(PomModel src, PomModel ref) {

		System.out.println("Analyzing dependencyManagement");

		int conflicts = 0;

		PomDependencyManagement srcDependencyManagement = src.dependencyManagement;
		PomDependencyManagement refDependencyManagement = ref.dependencyManagement;

		if (srcDependencyManagement == null || refDependencyManagement == null)
			return 0;

		Set<String> srcNames = srcDependencyManagement.dependencies.stream().map(d -> d.name()).collect(toSet());
		Set<String> refNames = refDependencyManagement.dependencies.stream().map(d -> d.name()).collect(toSet());

		srcNames.retainAll(refNames);

		for (String name : srcNames) {

			String srcVersion = srcDependencyManagement.dependency(name).version();
			String refVersion = refDependencyManagement.dependency(name).version();

			if (!srcVersion.equals(refVersion)) {

				System.out.println(String.format("- %1$s [%2$s] vs [%3$s]", name, srcVersion, refVersion));
				++conflicts;
			}
		}
		return conflicts;
	}
}

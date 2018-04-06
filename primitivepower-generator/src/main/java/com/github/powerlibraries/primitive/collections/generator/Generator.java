package com.github.powerlibraries.primitive.collections.generator;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.io.FileUtils;
import org.jtwig.JtwigModel;
import org.jtwig.JtwigTemplate;
import org.jtwig.environment.EnvironmentConfiguration;
import org.jtwig.environment.EnvironmentConfigurationBuilder;
import org.jtwig.property.selection.cache.NoSelectionPropertyResolverCache;

public class Generator {

	public static void main(String[] args) throws IOException {
		File targetDirectory = new File("../primitivepower");
		if(args.length==1) {
			targetDirectory = new File(args[0]);
		}
		
		generateCode(
			new File("templates"),
			targetDirectory
		);
	}
	
	public static void generateCode(File in, File target) throws IOException {
		System.setProperty("org.slf4j.simpleLogger.defaultLogLevel", "trace");
		final EnvironmentConfiguration configuration = EnvironmentConfigurationBuilder
            .configuration()
            	.render()
            		.withStrictMode(true)
            	.and()
            	.propertyResolver()
            		.withCache(NoSelectionPropertyResolverCache.noSelectionPropertyResolverCache())
            		.propertyResolverStrategies()
            		.and()
            	.and()
	        .build();
		
		
		List<File> fileList = Files
			.walk(in.toPath())
			.map(Path::toFile)
			.filter(File::isFile)
			.collect(Collectors.toList());
		
		for(File f:fileList) {
			File folder = new File(target, in.toPath().relativize(f.getParentFile().toPath()).toString());
			folder.mkdirs();
			
			if(f.getName().endsWith(".twig")) {
				JtwigTemplate nameTemplate = JtwigTemplate.inlineTemplate(f.getName().substring(0,f.getName().length()-5), configuration);
				JtwigTemplate template = JtwigTemplate.fileTemplate(f, configuration);
				
				
				for(Type t:Type.values()) {
					JtwigModel model = JtwigModel
						.newModel()
						.with("t", t)
						.with("allTypes", Type.values());
					
					File res = new File(folder, nameTemplate.render(model));
					try (OutputStream out = new FileOutputStream(res)) {
						template.render(model, out);
					} catch(Exception e) {
						throw new RuntimeException("Failed in "+f.getName()+" with k="+t, e);
					}
				}
			}
			else {
				File res = new File(folder, f.getName());
				FileUtils.copyFile(f, res);
			}
		}
	}
}

package edu.iitdu.jdgen.configuration;


public class ConfigurationFactory {
	public <T> Configurable<T> getConfiguration(Class<T> type){
		return new Configuration<>(type);
	}
}

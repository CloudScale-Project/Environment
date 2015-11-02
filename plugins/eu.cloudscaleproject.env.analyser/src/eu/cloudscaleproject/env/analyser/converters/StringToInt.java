package eu.cloudscaleproject.env.analyser.converters;

import org.eclipse.core.databinding.conversion.Converter;

public class StringToInt extends Converter{

	public StringToInt() {
		super(String.class, Integer.class);
	}

	@Override
	public Object convert(Object fromObject) {
		return Integer.parseInt((String)fromObject);
	}

}

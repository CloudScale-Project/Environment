package eu.cloudscaleproject.env.analyser.converters;

import org.eclipse.core.databinding.conversion.Converter;

public class IntToString extends Converter{

	public IntToString() {
		super(Integer.class, String.class);
	}
	
	@Override
	public Object convert(Object fromObject) {
		return ((Integer)fromObject).toString();
	}

}

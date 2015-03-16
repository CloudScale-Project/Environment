package eu.cloudscaleproject.env.common;

import java.text.ParseException;

import javax.measure.Measure;
import javax.measure.MeasureFormat;

import org.eclipse.core.databinding.conversion.IConverter;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;


public class Converters {
	
	public static IConverter[] getBoolEObjectConverter(final EObject eObject){
		IConverter[] converter = new IConverter[2];
		
		//t2m
		converter[0] = new IConverter() {
			
			@Override
			public Object getToType() {
				return Object.class;
			}
			
			@Override
			public Object getFromType() {
				return Boolean.TYPE;
			}
			
			@Override
			public Object convert(Object fromObject) {
				boolean b = (boolean)fromObject;
				if(b){
					return EcoreUtil.copy(eObject);
				}
				return null;
			}
		};
		
		//m2t
		converter[1] = new IConverter() {
			
			@Override
			public Object getToType() {
				return Boolean.TYPE;
			}
			
			@Override
			public Object getFromType() {
				return Object.class;
			}
			
			@Override
			public Object convert(Object fromObject) {
				if(fromObject == null){
					return false;
				}
				return true;
			}
		};
		
		return converter;
	}
	
	public static IConverter[] getStringMeasureConverter(){
		IConverter[] converter = new IConverter[2];
		
		//t2m
		converter[0] = new IConverter() {
			
			@Override
			public Object getToType() {
				return Measure.class;
			}
			
			@Override
			public Object getFromType() {
				return String.class;
			}
			
			@Override
			public Object convert(Object fromObject) {
				String str = (String)fromObject;
				if(str == null || str.isEmpty()){
					return null;
				}
				
				try {
					return MeasureFormat.getInstance().parseObject(str);
				} catch (ParseException e) {
					e.printStackTrace();
					return null;
				}
			}
		};
		
		//m2t
		converter[1] = new IConverter() {
			
			@Override
			public Object getToType() {
				return String.class;
			}
			
			@Override
			public Object getFromType() {
				return Measure.class;
			}
			
			@Override
			public Object convert(Object fromObject) {
				Measure<?, ?> measure = (Measure<?,?>)fromObject;
				return MeasureFormat.getInstance().format(measure);
			}
		};
		
		return converter;
	}
}

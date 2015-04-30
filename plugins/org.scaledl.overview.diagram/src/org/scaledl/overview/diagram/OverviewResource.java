package org.scaledl.overview.diagram;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;

import eu.cloudscaleproject.env.toolchain.ToolchainUtils;
import eu.cloudscaleproject.env.toolchain.resources.types.EditorInputResource;

public class OverviewResource extends EditorInputResource{
	
	private final IFile resource; 
	
	public OverviewResource(IFile file) {
		resource = file;
	}

	@Override
	public IResource getResource() {
		return resource;
	}
	
	@Override
	public String getID() {
		return ToolchainUtils.OVERVIEW_ID;
	}

	@Override
	public String getType() {
		return ToolchainUtils.OVERVIEW_ID;
	}

	@Override
	public void save() {
		throw new UnsupportedOperationException();
	}

	@Override
	public void load() {
		throw new UnsupportedOperationException();		
	}

	@Override
	public void delete() {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean isDirty() {
		return false;
	}

	@Override
	public String getProperty(String key) {
		return null;
	}

	@Override
	public void setProperty(String key, String value) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void copyFrom(IResource file) {
		throw new UnsupportedOperationException();
	}

	@Override
	public String getName() {
		return resource.getName();
	}

	@Override
	public void setName(String name) {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean isLoaded() {
		return true;
	}

}

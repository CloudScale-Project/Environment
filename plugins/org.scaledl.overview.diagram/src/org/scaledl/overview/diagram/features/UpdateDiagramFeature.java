package org.scaledl.overview.diagram.features;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.graphiti.features.IAddFeature;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.IReason;
import org.eclipse.graphiti.features.IRemoveFeature;
import org.eclipse.graphiti.features.IUpdateFeature;
import org.eclipse.graphiti.features.context.IUpdateContext;
import org.eclipse.graphiti.features.context.impl.AddConnectionContext;
import org.eclipse.graphiti.features.context.impl.AddContext;
import org.eclipse.graphiti.features.context.impl.RemoveContext;
import org.eclipse.graphiti.features.context.impl.UpdateContext;
import org.eclipse.graphiti.features.impl.DefaultUpdateDiagramFeature;
import org.eclipse.graphiti.features.impl.Reason;
import org.eclipse.graphiti.mm.pictograms.Anchor;
import org.eclipse.graphiti.mm.pictograms.AnchorContainer;
import org.eclipse.graphiti.mm.pictograms.ContainerShape;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.mm.pictograms.Shape;
import org.eclipse.graphiti.services.Graphiti;
import org.scaledl.overview.Overview;
import org.scaledl.overview.architecture.CloudEnvironment;
import org.scaledl.overview.architecture.Connection;
import org.scaledl.overview.architecture.ExternalConnection;
import org.scaledl.overview.architecture.InternalConnection;
import org.scaledl.overview.architecture.Proxy;

public class UpdateDiagramFeature  extends DefaultUpdateDiagramFeature{

	public UpdateDiagramFeature(IFeatureProvider fp) {
		super(fp);
		// TODO Auto-generated constructor stub
	}
	
	public boolean canUpdate(IUpdateContext context) {
		// return true, if linked business object is an EClass
		Object bo = getBusinessObjectForPictogramElement(context.getPictogramElement());
		return (bo instanceof Overview);
	}

	public IReason updateNeeded(IUpdateContext context) {
		if (updateNeededCloudEnvironments(context))
		{
			return Reason.createTrueReason("CloudEnvironments out of sync.");
		}
		
		if (updateNeededProxies(context))
		{
			return Reason.createTrueReason("Proxies out of sync.");
		}

		if (updateNeededConnections(context))
		{
			return Reason.createTrueReason("Connections out of sync.");
		}

		return Reason.createFalseReason();
	}

	public boolean update(IUpdateContext context) {
		// retrieve name from business model

		updateCloudEnviroments(context);
		updateProxies(context);
		updateConnections(context);
		return true;
	}
	
	private boolean updateNeededCloudEnvironments (IUpdateContext context)
	{
		List<CloudEnvironment> cloudEnvironments = getCloudEnvironments(context);
		Map<CloudEnvironment, Shape> ceMap = getCloudEnvironmentsDiagram(context);
		Set<CloudEnvironment> cloudEnvironmentsDiagram = ceMap.keySet();
		
		// Easy check
		if (cloudEnvironments.size() != cloudEnvironmentsDiagram.size())
			return true;
		
		cloudEnvironments.removeAll(cloudEnvironmentsDiagram);
		if (!cloudEnvironments.isEmpty())
			return true;
		
		return false;
	}
	
	private boolean updateNeededProxies (IUpdateContext context)
	{
		List<Proxy> proxies = getProxies(context);
		Map<Proxy, Shape> proxyMap = getProxiesDiagram(context);
		Set<Proxy> proxiesDiagram = proxyMap.keySet();
		
		// Easy check
		if (proxies.size() != proxiesDiagram.size())
			return true;
		
		proxies.removeAll(proxiesDiagram);
		if (!proxies.isEmpty())
			return true;
		
		return false;
		
	}

	private boolean updateNeededConnections (IUpdateContext context)
	{
		List<Connection> connections = getConnections(context);
		Map<Connection, org.eclipse.graphiti.mm.pictograms.Connection> connectionsMap = getConnectionsDiagram(context);
		Set<Connection> connectionsDiagram = connectionsMap.keySet();
		
		// Easy check
		if (connections.size() != connectionsDiagram.size())
			return true;
		
		connections.removeAll(connectionsDiagram);
		if (!connections.isEmpty())
			return true;
		
		return false;
	}
	
	private void updateCloudEnviroments (IUpdateContext context)
	{
		List<CloudEnvironment> cloudEnvironments = getCloudEnvironments(context);

		Map<CloudEnvironment, Shape> ceMap = getCloudEnvironmentsDiagram(context);
		Set<CloudEnvironment> cloudEnvironmentsDiagram = ceMap.keySet();
		
		// TODO: Remove deleted cloud environments

		// Add created (missing) cloud environments
		for (CloudEnvironment ce : cloudEnvironments)
		{
			if (!cloudEnvironmentsDiagram.contains(ce))
			{
				addCloudEnvironment(context, ce);
			}
		}

		for (CloudEnvironment ce : cloudEnvironmentsDiagram)
		{
			if (!cloudEnvironments.contains(ce))
				removeElement(ceMap.get(ce));
		}
	}

	private void updateProxies (IUpdateContext context)
	{
		List<Proxy> proxies = getProxies(context);
		Map<Proxy, Shape> proxyMap = getProxiesDiagram(context);
		Set<Proxy> proxiesDiagram = proxyMap.keySet();
		
		// TODO: Remove deleted proxies

		// Add created (missing) proxies
		for (Proxy proxy : proxies)
		{
			if (!proxiesDiagram.contains(proxy))
			{
				addProxy(context, proxy);
			}
		}

		for (Proxy proxy : proxiesDiagram)
		{
			if (!proxies.contains(proxy))
				removeElement(proxyMap.get(proxy));
		}
	}

	private void updateConnections (IUpdateContext context)
	{
		List<Connection> connections = getConnections(context);
		Map<Connection, org.eclipse.graphiti.mm.pictograms.Connection> connectionsMap = getConnectionsDiagram(context);
		Set<Connection> connectionsDiagram = connectionsMap.keySet();

		System.out.println("dd");
		
		// TODO: Remove deleted cloud environments

		// Add created (missing) cloud environments
		for (Connection connection : connections)
		{
			if (!connectionsDiagram.contains(connection))
			{
				addConnection(context, connection);
			}
		}
		
		for (Connection connection : connectionsDiagram)
		{
			if (!connections.contains(connection))
				removeElement(connectionsMap.get(connection));
		}
	}

	private void addProxy (IUpdateContext context, Proxy proxy)
	{
		AddContext ac = new AddContext();
		ac.setLocation(30, 30);
		ac.setSize(100, 100);
		ac.setNewObject(proxy);
		ac.setTargetContainer(getDiagram());
		IAddFeature addFeature = getFeatureProvider().getAddFeature(ac);
		
		if (addFeature.canExecute(ac))
			addFeature.execute(ac);
	}

	private void addConnection (IUpdateContext context, Connection connection)
	{
		Graphiti.getPeCreateService();

		List<PictogramElement> targetElements;
		List<PictogramElement> sourceElements;
		
		if (connection instanceof InternalConnection)
		{
			InternalConnection ic = (InternalConnection) connection;
			sourceElements = Graphiti.getLinkService().getPictogramElements(getDiagram(), ic.getSource());
			targetElements = Graphiti.getLinkService().getPictogramElements(getDiagram(), ic.getTarget());
		}
		else
		{
			ExternalConnection ec = (ExternalConnection) connection;
			sourceElements = Graphiti.getLinkService().getPictogramElements(getDiagram(), ec.getSource());
			targetElements = Graphiti.getLinkService().getPictogramElements(getDiagram(), ec.getTarget());
		}

		if (sourceElements.size() != 1 || targetElements.size() != 1)
		{
			// Ignore
			// Source or target does not exist yet
			// Probably CloudEnvironment must be updated, which
			// updates also diagram
			return;
		}
		
		PictogramElement source = sourceElements.get(0);
		PictogramElement target = targetElements.get(0);
		
		if (!(source instanceof AnchorContainer && target instanceof AnchorContainer))
		{
			throw new IllegalStateException();
		}
		
		
		Anchor sourceAnchor = ((AnchorContainer)source).getAnchors().get(0);
		Anchor targetAnchor = ((AnchorContainer)target).getAnchors().get(0);

		AddConnectionContext ac = new AddConnectionContext(sourceAnchor, targetAnchor);
		ac.setNewObject(connection);
		IAddFeature addFeature = getFeatureProvider().getAddFeature(ac);
		
		if (addFeature.canExecute(ac))
			addFeature.execute(ac);
	}
	
	private void addCloudEnvironment (IUpdateContext context, CloudEnvironment ce)
	{
		AddContext ac = new AddContext();
		ac.setLocation(30, 30);
		ac.setSize(100, 100);
		ac.setNewObject(ce);
		ac.setTargetContainer(getDiagram());
		IAddFeature addFeature = getFeatureProvider().getAddFeature(ac);
		
		if (addFeature.canExecute(ac))
			addFeature.execute(ac);

		PictogramElement  pe = Graphiti.getLinkService().getPictogramElements(getDiagram(), ce).get(0);

		// Force update - if not user needs to click on update action to trigger update
		// If not updates, service pictograms does not exist and connections cannot be made
		UpdateContext uc = new UpdateContext(pe);
		IUpdateFeature updateFeature = getFeatureProvider().getUpdateFeature(uc);
		if (updateFeature.canExecute(uc))
			updateFeature.execute(uc);
	}
	
	private void removeElement (PictogramElement element)
	{
		RemoveContext removeContext = new RemoveContext(element);
		IRemoveFeature removeFeature = getFeatureProvider().getRemoveFeature(removeContext);
		if (removeFeature.canExecute(removeContext))
			removeFeature.execute(removeContext);
	}
	
	public Overview getOverview (IUpdateContext context)
	{
		PictogramElement pictogramElement = context.getPictogramElement();
		Overview overview = (Overview) getBusinessObjectForPictogramElement(pictogramElement);
		return overview;
	}
	
	public List<Proxy> getProxies (IUpdateContext context)
	{
		Overview overview = getOverview(context);
		return new LinkedList<Proxy>(overview.getArchitecture().getProxies());
	}
	
	public Map<Proxy, Shape> getProxiesDiagram (IUpdateContext context)
	{
		HashMap<Proxy, Shape> mapProxies = new HashMap<Proxy, Shape>();
		ContainerShape cs = (ContainerShape)context.getPictogramElement();

		for (Shape shape : cs.getChildren())
		{
			if (shape.getLink() != null)
			{
				if (!shape.getLink().getBusinessObjects().isEmpty());
				{
					EObject eObject = shape.getLink().getBusinessObjects().get(0);
					if (eObject instanceof Proxy)
					{
						mapProxies.put((Proxy) eObject, shape);
					}
				}
			}
		}
		
		return mapProxies;
	}
	
	public List<CloudEnvironment> getCloudEnvironments (IUpdateContext context)
	{
		Overview overview = getOverview(context);
		return overview.getArchitecture().getCloudEnvironments();
	}
	
	public Map<CloudEnvironment, Shape> getCloudEnvironmentsDiagram (IUpdateContext context)
	{
		ContainerShape cs = (ContainerShape) context.getPictogramElement();
		HashMap<CloudEnvironment, Shape> mapEnvironments = new HashMap<CloudEnvironment, Shape>();

		for (Shape shape : cs.getChildren())
		{
			if (shape.getLink() != null)
			{
				if (!shape.getLink().getBusinessObjects().isEmpty());
				{
					EObject eObject = shape.getLink().getBusinessObjects().get(0);
					if (eObject instanceof CloudEnvironment)
					{
						mapEnvironments.put((CloudEnvironment) eObject, shape);
					}
				}
			}
			
		}
		
		return mapEnvironments;
	}
	
	public List<Connection> getConnections (IUpdateContext context)
	{
		Overview overview = getOverview(context);
		LinkedList<Connection> connections = new LinkedList<Connection>();

		TreeIterator<EObject> allContents = overview.eResource().getAllContents();
		while (allContents.hasNext())
		{
			EObject o = allContents.next();
			if (o instanceof Connection)
				connections.add((Connection)o);
		}
		
		return connections;
	}
	
	public Map<Connection, org.eclipse.graphiti.mm.pictograms.Connection> getConnectionsDiagram (IUpdateContext context)
	{
		HashMap<Connection,org.eclipse.graphiti.mm.pictograms.Connection> mapConnections = new HashMap<Connection, org.eclipse.graphiti.mm.pictograms.Connection>();

		for (org.eclipse.graphiti.mm.pictograms.Connection connectionDiagram : getDiagram().getConnections())
		{
				if (connectionDiagram.getLink() != null)
				{
					if (!connectionDiagram.getLink().getBusinessObjects().isEmpty())
					{
						EObject o = connectionDiagram.getLink().getBusinessObjects().get(0);
						if (o instanceof Connection)
							mapConnections.put((Connection) o, connectionDiagram);
					}
				}
			
		}
		return mapConnections;
	}
	
	@SuppressWarnings("unused")
	private void removeUnlinkedElements (IUpdateContext context)
	{
		for (org.eclipse.graphiti.mm.pictograms.Connection connectionDiagram : getDiagram().getConnections())
		{
				if (connectionDiagram.getLink() == null ||
					connectionDiagram.getLink().getBusinessObjects().isEmpty() //||
					)
				{
					if (!connectionDiagram.getLink().getBusinessObjects().isEmpty())
					{
						EObject o = connectionDiagram.getLink().getBusinessObjects().get(0);
					}
				}
			
		}
		
	}
	
}

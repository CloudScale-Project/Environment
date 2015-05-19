package eu.cloudscaleproject.env.toolchain.util;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import eu.cloudscaleproject.env.toolchain.resources.types.IEditorInput;
import eu.cloudscaleproject.env.toolchain.util.AbstractSidebarEditor.EditorItem;

public class EditorRegistry
{
	// TODO: unregister
	
	private static EditorRegistry INSTANCE = new EditorRegistry();
	
	public static EditorRegistry getInstance()
	{
		return INSTANCE;
	}
	
	private Set<AbstractSidebarEditor> editors = new HashSet<>();
	private Map<EditorItem, AbstractSidebarEditor> mapEditorItemsToEditor = new HashMap<>();
	private Map<IEditorInput, EditorItem> mapEditorInputToItem = new HashMap<>();

	public void registerEditor (AbstractSidebarEditor editor)
	{
		editors.add(editor);
	}
	
	public void registerEditorItem (AbstractSidebarEditor ase, EditorItem editorItem)
	{
		mapEditorItemsToEditor.put(editorItem, ase);
		mapEditorInputToItem.put(editorItem.getInput(), editorItem);
	}
	
	public EditorItem getEditorItem (IEditorInput input)
	{
		return mapEditorInputToItem.get(input);
	}
	
	public EditorItem getParent (EditorItem item)
	{
		Set<EditorItem> items = mapEditorItemsToEditor.keySet();
		
		for (EditorItem parent : items)
		{
			if (parent.getComposite() instanceof SidebarEditorComposite)
			{
				AbstractSidebarEditor sidebarEditor = (AbstractSidebarEditor)((SidebarEditorComposite)parent.getComposite()).getSidebarEditor();
				if (sidebarEditor.getEntries().containsKey(item.getInput()))
				{
					return parent;
				}
			}
		}
		
		return null;
	}
}

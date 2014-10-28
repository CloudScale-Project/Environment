package eu.cloudscaleproject.env.product;


public class ToolBarVisibilityHandler {
	/*
	@Inject private IEventBroker eventBroker;
	@Inject private EModelService modelService;
	@Inject private MApplication application;
	
	private final Handler handler = new Handler();
	
	@PostConstruct
	void hookListeners() {
		//eventBroker.subscribe(UIEvents.ElementContainer.TOPIC_SELECTEDELEMENT,
			//	handler);
	}
	
	@PreDestroy
	void unhookListeners() {
		///eventBroker.unsubscribe(handler);
	}
	
	private  class Handler implements EventHandler{
		
		@Override
		public void handleEvent(Event event) {
			Object part = event.getProperty(UIEvents.EventTags.NEW_VALUE);
			
			if (part instanceof MPerspective) {
				handleVisible((MPerspective)part);
			}
		}
		
		public void handleVisible(MPerspective perspective){
			if(perspective == null){
				return;
			}
			
			MUIElement toolBar = modelService.find("org.eclipse.ui.main.toolbar", application);
			if(toolBar != null){
				if(perspective.getTags().contains("hideToolbar")){
					toolBar.setVisible(false);
				}
				else{
					toolBar.setVisible(true);
				}
			}
			
//			MUIElement menuBar = modelService.find("org.eclipse.ui.main.menu", application);
//			if(menuBar != null){
//				if(perspective.getTags().contains("hideToolbar")){
//					menuBar.setVisible(false);
//				}
//				else{
//					menuBar.setVisible(true);
//				}
//			}
			
    	}
	}
	*/
}

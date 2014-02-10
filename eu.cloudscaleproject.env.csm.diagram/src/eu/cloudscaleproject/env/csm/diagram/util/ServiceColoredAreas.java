package eu.cloudscaleproject.env.csm.diagram.util;

import org.eclipse.emf.common.util.EList;
import org.eclipse.graphiti.mm.algorithms.styles.AdaptedGradientColoredAreas;
import org.eclipse.graphiti.mm.algorithms.styles.GradientColoredArea;
import org.eclipse.graphiti.mm.algorithms.styles.GradientColoredAreas;
import org.eclipse.graphiti.mm.algorithms.styles.LocationType;
import org.eclipse.graphiti.mm.algorithms.styles.StylesFactory;
import org.eclipse.graphiti.util.IPredefinedRenderingStyle;
import org.eclipse.graphiti.util.PredefinedColoredAreas;

public class ServiceColoredAreas extends PredefinedColoredAreas {
	
	public static final String STYLE_ID = "cloudprovider-style";

	@SuppressWarnings("unused")
	private static GradientColoredAreas getServiceDefaultAreas() {
		final GradientColoredAreas gradientColoredAreas = StylesFactory.eINSTANCE
				.createGradientColoredAreas();
		final EList<GradientColoredArea> gcas = gradientColoredAreas
				.getGradientColor();

		addGradientColoredArea(gcas, "CCFFCC", 0,
				LocationType.LOCATION_TYPE_ABSOLUTE_START, "CCFFCC", 1,
				LocationType.LOCATION_TYPE_ABSOLUTE_START);
		addGradientColoredArea(gcas, "CCFF99", 1,
				LocationType.LOCATION_TYPE_ABSOLUTE_START, "CCFF99", 2,
				LocationType.LOCATION_TYPE_ABSOLUTE_START);
		addGradientColoredArea(gcas, "CCFF66", 2,
				LocationType.LOCATION_TYPE_ABSOLUTE_START, "CCFF66", 3,
				LocationType.LOCATION_TYPE_ABSOLUTE_START);
		addGradientColoredArea(gcas, "66FF00", 3,
				LocationType.LOCATION_TYPE_ABSOLUTE_START, "CCFFCC", 2,
				LocationType.LOCATION_TYPE_ABSOLUTE_END);
		addGradientColoredArea(gcas, "CCFFCC", 2,
				LocationType.LOCATION_TYPE_ABSOLUTE_END, "CCFFCC", 0,
				LocationType.LOCATION_TYPE_ABSOLUTE_END);
		gradientColoredAreas
				.setStyleAdaption(IPredefinedRenderingStyle.STYLE_ADAPTATION_DEFAULT);
		return gradientColoredAreas;
	}

	@SuppressWarnings("unused")
	private static GradientColoredAreas getServicePrimarySelectedAreas() {
		final GradientColoredAreas gradientColoredAreas = StylesFactory.eINSTANCE
				.createGradientColoredAreas();
		gradientColoredAreas
				.setStyleAdaption(IPredefinedRenderingStyle.STYLE_ADAPTATION_PRIMARY_SELECTED);
		final EList<GradientColoredArea> gcas = gradientColoredAreas
				.getGradientColor();

		addGradientColoredArea(gcas, "66CCCC", 0,
				LocationType.LOCATION_TYPE_ABSOLUTE_START, "66CCCC", 1,
				LocationType.LOCATION_TYPE_ABSOLUTE_START);
		addGradientColoredArea(gcas, "66CC99", 1,
				LocationType.LOCATION_TYPE_ABSOLUTE_START, "66CC99", 2,
				LocationType.LOCATION_TYPE_ABSOLUTE_START);
		addGradientColoredArea(gcas, "66CC66", 2,
				LocationType.LOCATION_TYPE_ABSOLUTE_START, "66CC66", 3,
				LocationType.LOCATION_TYPE_ABSOLUTE_START);
		addGradientColoredArea(gcas, "00CC00", 3,
				LocationType.LOCATION_TYPE_ABSOLUTE_START, "00CC66", 2,
				LocationType.LOCATION_TYPE_ABSOLUTE_END);
		addGradientColoredArea(gcas, "00CC99", 2,
				LocationType.LOCATION_TYPE_ABSOLUTE_END, "00CC99", 0,
				LocationType.LOCATION_TYPE_ABSOLUTE_END);
		return gradientColoredAreas;
	}

	@SuppressWarnings("unused")
	private static GradientColoredAreas getServiceSecondarySelectedAreas() {
		final GradientColoredAreas gradientColoredAreas = StylesFactory.eINSTANCE
				.createGradientColoredAreas();
		gradientColoredAreas
				.setStyleAdaption(IPredefinedRenderingStyle.STYLE_ADAPTATION_SECONDARY_SELECTED);
		final EList<GradientColoredArea> gcas = gradientColoredAreas
				.getGradientColor();

		addGradientColoredArea(gcas, "33CCCC", 0,
				LocationType.LOCATION_TYPE_ABSOLUTE_START, "33CCCC", 1,
				LocationType.LOCATION_TYPE_ABSOLUTE_START);
		addGradientColoredArea(gcas, "33CC99", 1,
				LocationType.LOCATION_TYPE_ABSOLUTE_START, "33CC99", 2,
				LocationType.LOCATION_TYPE_ABSOLUTE_START);
		addGradientColoredArea(gcas, "33CC66", 2,
				LocationType.LOCATION_TYPE_ABSOLUTE_START, "33CC66", 3,
				LocationType.LOCATION_TYPE_ABSOLUTE_START);
		addGradientColoredArea(gcas, "33CC00", 3,
				LocationType.LOCATION_TYPE_ABSOLUTE_START, "33CC99", 2,
				LocationType.LOCATION_TYPE_ABSOLUTE_END);
		addGradientColoredArea(gcas, "66CC99", 2,
				LocationType.LOCATION_TYPE_ABSOLUTE_END, "66CC99", 0,
				LocationType.LOCATION_TYPE_ABSOLUTE_END);
		return gradientColoredAreas;
	}

	public static AdaptedGradientColoredAreas getServiceAdaptions() {
		return getLightGrayAdaptions();
/*		final AdaptedGradientColoredAreas agca = StylesFactory.eINSTANCE
				.createAdaptedGradientColoredAreas();
		agca.setDefinedStyleId(STYLE_ID);
		agca.setGradientType(IGradientType.VERTICAL);
		agca.getAdaptedGradientColoredAreas().add(
				IPredefinedRenderingStyle.STYLE_ADAPTATION_DEFAULT,
				getServiceDefaultAreas());
		agca.getAdaptedGradientColoredAreas().add(
				IPredefinedRenderingStyle.STYLE_ADAPTATION_PRIMARY_SELECTED,
				getServicePrimarySelectedAreas());
		agca.getAdaptedGradientColoredAreas().add(
				IPredefinedRenderingStyle.STYLE_ADAPTATION_SECONDARY_SELECTED,
				getServiceSecondarySelectedAreas());
		return agca;*/
	}
}
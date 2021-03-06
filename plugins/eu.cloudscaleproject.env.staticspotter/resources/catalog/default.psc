<?xml version="1.0" encoding="UTF-8"?>
<specification:PSCatalog xmi:version="2.0" xmlns:xmi="http://www.omg.org/XMI" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
    xmlns:specification="http://www.reclipse.org/ns/specification" id="_3n5RYPVhEeOgbJdIqiztvw" name="New Catalog" metamodel="org.reclipse.kdm2pcm.sourcecodedecorator">
  <patternSpecifications id="_8u6OgPVhEeOgbJdIqiztvw" name="AcquireReleasePair">
    <connections xsi:type="specification:PSLink" id="_VF19IPViEeOgbJdIqiztvw" name="link1"
        source="#_-WpBUPVhEeOgbJdIqiztvw" target="#_LWXAEPViEeOgbJdIqiztvw" instanceOf="http://palladiosimulator.org/PalladioComponentModel/5.1#//seff/ResourceDemandingBehaviour/steps_Behaviour"/>
    <connections xsi:type="specification:PSLink" id="_V51l4PViEeOgbJdIqiztvw" name="link2"
        source="#_-WpBUPVhEeOgbJdIqiztvw" target="#_LrRgIPViEeOgbJdIqiztvw" instanceOf="http://palladiosimulator.org/PalladioComponentModel/5.1#//seff/ResourceDemandingBehaviour/steps_Behaviour"/>
    <connections xsi:type="specification:PSLink" id="_nlJKAPViEeOgbJdIqiztvw" name="link3"
        source="#_LrRgIPViEeOgbJdIqiztvw" target="#_jVVhsPViEeOgbJdIqiztvw" instanceOf="http://palladiosimulator.org/PalladioComponentModel/5.1#//seff/ReleaseAction/passiveResource_ReleaseAction"/>
    <connections xsi:type="specification:PSLink" id="_oHb-wPViEeOgbJdIqiztvw" name="link4"
        source="#_LWXAEPViEeOgbJdIqiztvw" target="#_jVVhsPViEeOgbJdIqiztvw" instanceOf="http://palladiosimulator.org/PalladioComponentModel/5.1#//seff/AcquireAction/passiveresource_AcquireAction"/>
    <connections xsi:type="specification:PSLink" id="_t4ohUPViEeOgbJdIqiztvw" name="annotatedElement"
        source="#_8u6OgfVhEeOgbJdIqiztvw" target="#_LrRgIPViEeOgbJdIqiztvw" qualifier="release"
        instanceOf="http://org.reclipse.structure.inference.annotations#//ASGAnnotation/annotatedElements"/>
    <connections xsi:type="specification:PSLink" id="_ufK84PViEeOgbJdIqiztvw" name="annotatedElement"
        source="#_8u6OgfVhEeOgbJdIqiztvw" target="#_LWXAEPViEeOgbJdIqiztvw" qualifier="acquire"
        instanceOf="http://org.reclipse.structure.inference.annotations#//ASGAnnotation/annotatedElements"/>
    <connections xsi:type="specification:PSLink" id="_DCkuYNMJEeWcFIwc26d39g" name="link7"
        source="#_vH03MNMIEeWcFIwc26d39g" target="#_-WpBUPVhEeOgbJdIqiztvw" qualifier="seff"
        instanceOf="http://org.reclipse.structure.inference.annotations#//ASGAnnotation/annotatedElements"/>
    <nodes xsi:type="specification:PSAnnotation" id="_8u6OgfVhEeOgbJdIqiztvw" outgoing="#_t4ohUPViEeOgbJdIqiztvw #_ufK84PViEeOgbJdIqiztvw"
        type="#_8u6OgPVhEeOgbJdIqiztvw"/>
    <nodes xsi:type="specification:PSObject" id="_-WpBUPVhEeOgbJdIqiztvw" name="behaviour"
        outgoing="#_VF19IPViEeOgbJdIqiztvw #_V51l4PViEeOgbJdIqiztvw" incoming="#_DCkuYNMJEeWcFIwc26d39g"
        instanceOf="http://palladiosimulator.org/PalladioComponentModel/5.1#//seff/ResourceDemandingSEFF"/>
    <nodes xsi:type="specification:PSObject" id="_LWXAEPViEeOgbJdIqiztvw" name="acquireAction"
        outgoing="#_oHb-wPViEeOgbJdIqiztvw" incoming="#_VF19IPViEeOgbJdIqiztvw #_ufK84PViEeOgbJdIqiztvw"
        instanceOf="http://palladiosimulator.org/PalladioComponentModel/5.1#//seff/AcquireAction"/>
    <nodes xsi:type="specification:PSObject" id="_LrRgIPViEeOgbJdIqiztvw" name="releaseAction"
        outgoing="#_nlJKAPViEeOgbJdIqiztvw" incoming="#_V51l4PViEeOgbJdIqiztvw #_t4ohUPViEeOgbJdIqiztvw"
        instanceOf="http://palladiosimulator.org/PalladioComponentModel/5.1#//seff/ReleaseAction"/>
    <nodes xsi:type="specification:PSObject" id="_jVVhsPViEeOgbJdIqiztvw" name="passiveResource"
        incoming="#_nlJKAPViEeOgbJdIqiztvw #_oHb-wPViEeOgbJdIqiztvw" instanceOf="http://palladiosimulator.org/PalladioComponentModel/5.1#//repository/PassiveResource"/>
    <nodes xsi:type="specification:PSAnnotation" id="_vH03MNMIEeWcFIwc26d39g" name="seff"
        outgoing="#_DCkuYNMJEeWcFIwc26d39g" type="#_2aa-cLXlEeWOkIpooXnIiQ"/>
  </patternSpecifications>
  <patternSpecifications id="_7E0R8PVmEeO8apkjJxxSYA" name="FindPrimitiveComponents">
    <connections xsi:type="specification:PSLink" id="_BdcOoPVsEeOhXMVyYRdIxA" name="annotatedElement"
        source="#_7E05APVmEeO8apkjJxxSYA" target="#_WLDqAPVoEeO8apkjJxxSYA" qualifier="primitiveComponent"
        instanceOf="http://org.reclipse.structure.inference.annotations#//ASGAnnotation/annotatedElements"/>
    <connections xsi:type="specification:PSLink" id="_EKcAYPVsEeOhXMVyYRdIxA" name="link2"
        source="#_Dg1cAPVnEeO8apkjJxxSYA" target="#_WLDqAPVoEeO8apkjJxxSYA" instanceOf="http://somox.org/SourceCodeDecorator/2.0#//ComponentImplementingClassesLink/component"/>
    <nodes xsi:type="specification:PSAnnotation" id="_7E05APVmEeO8apkjJxxSYA" outgoing="#_BdcOoPVsEeOhXMVyYRdIxA"
        type="#_7E0R8PVmEeO8apkjJxxSYA"/>
    <nodes xsi:type="specification:PSObject" id="_Dg1cAPVnEeO8apkjJxxSYA" name="componentImplementingClassesLink"
        outgoing="#_EKcAYPVsEeOhXMVyYRdIxA" instanceOf="http://somox.org/SourceCodeDecorator/2.0#//ComponentImplementingClassesLink"/>
    <nodes xsi:type="specification:PSObject" id="_WLDqAPVoEeO8apkjJxxSYA" name="primitiveComponent"
        incoming="#_BdcOoPVsEeOhXMVyYRdIxA #_EKcAYPVsEeOhXMVyYRdIxA" instanceOf="http://palladiosimulator.org/PalladioComponentModel/5.1#//repository/BasicComponent"/>
  </patternSpecifications>
  <patternSpecifications id="_cpjMkPbKEeOeYJP9coicjQ" name="SynchronizedMethod">
    <connections xsi:type="specification:PSLink" id="_KuCMwPbLEeOeYJP9coicjQ" name="link1"
        source="#_d3HL4PbKEeOeYJP9coicjQ" target="#_js-MAPbKEeOeYJP9coicjQ" instanceOf="http://www.eclipse.org/MoDisco/Java/0.2.incubation/java#//BodyDeclaration/modifier"/>
    <connections xsi:type="specification:PSLink" id="_Lrsu4PbLEeOeYJP9coicjQ" name="annotatedElement"
        source="#_cpjMkfbKEeOeYJP9coicjQ" target="#_d3HL4PbKEeOeYJP9coicjQ" qualifier="synchronizedMethods"
        instanceOf="http://org.reclipse.structure.inference.annotations#//ASGAnnotation/annotatedElements"/>
    <connections xsi:type="specification:PSLink" id="_YiHjsNMKEeWcFIwc26d39g" name="link3"
        source="#_W53zgNMKEeWcFIwc26d39g" target="#_d3HL4PbKEeOeYJP9coicjQ" qualifier="method"
        instanceOf="http://org.reclipse.structure.inference.annotations#//ASGAnnotation/annotatedElements"/>
    <nodes xsi:type="specification:PSAnnotation" id="_cpjMkfbKEeOeYJP9coicjQ" outgoing="#_Lrsu4PbLEeOeYJP9coicjQ"
        type="#_cpjMkPbKEeOeYJP9coicjQ"/>
    <nodes xsi:type="specification:PSObject" id="_d3HL4PbKEeOeYJP9coicjQ" name="method"
        outgoing="#_KuCMwPbLEeOeYJP9coicjQ" incoming="#_Lrsu4PbLEeOeYJP9coicjQ #_YiHjsNMKEeWcFIwc26d39g"
        instanceOf="http://www.eclipse.org/MoDisco/Java/0.2.incubation/java#//MethodDeclaration"/>
    <nodes xsi:type="specification:PSObject" id="_js-MAPbKEeOeYJP9coicjQ" name="modifier"
        incoming="#_KuCMwPbLEeOeYJP9coicjQ" instanceOf="http://www.eclipse.org/MoDisco/Java/0.2.incubation/java#//Modifier">
      <nodeConstraints xsi:type="specification:PSAttributeConstraint" id="_-132gPbLEeOeYJP9coicjQ"
          valueExpression="true" operator="EQUAL" attribute="http://www.eclipse.org/MoDisco/Java/0.2.incubation/java#//Modifier/synchronized"/>
    </nodes>
    <nodes xsi:type="specification:PSAnnotation" id="_W53zgNMKEeWcFIwc26d39g" name="seff"
        outgoing="#_YiHjsNMKEeWcFIwc26d39g" type="#_2aa-cLXlEeWOkIpooXnIiQ"/>
  </patternSpecifications>
  <patternSpecifications id="_cY9jIPbbEeOeYJP9coicjQ" name="FindMethods">
    <connections xsi:type="specification:PSLink" id="_x73q8PbbEeOeYJP9coicjQ" name="link1"
        source="#_dkI80PbbEeOeYJP9coicjQ" target="#_vNn8APbbEeOeYJP9coicjQ" instanceOf="http://somox.org/SourceCodeDecorator/2.0#//MethodLevelSourceCodeLink/function"/>
    <connections xsi:type="specification:PSLink" id="_yu-i4PbbEeOeYJP9coicjQ" name="annotatedElement"
        source="#_cY-KMPbbEeOeYJP9coicjQ" target="#_vNn8APbbEeOeYJP9coicjQ" qualifier="methods"
        instanceOf="http://org.reclipse.structure.inference.annotations#//ASGAnnotation/annotatedElements"/>
    <nodes xsi:type="specification:PSAnnotation" id="_cY-KMPbbEeOeYJP9coicjQ" outgoing="#_yu-i4PbbEeOeYJP9coicjQ"
        type="#_cY9jIPbbEeOeYJP9coicjQ"/>
    <nodes xsi:type="specification:PSObject" id="_dkI80PbbEeOeYJP9coicjQ" name="methodLevelSourceCodeLink"
        outgoing="#_x73q8PbbEeOeYJP9coicjQ" instanceOf="http://somox.org/SourceCodeDecorator/2.0#//MethodLevelSourceCodeLink"/>
    <nodes xsi:type="specification:PSObject" id="_vNn8APbbEeOeYJP9coicjQ" name="methodDeclaration"
        incoming="#_x73q8PbbEeOeYJP9coicjQ #_yu-i4PbbEeOeYJP9coicjQ" instanceOf="http://www.eclipse.org/MoDisco/Java/0.2.incubation/java#//MethodDeclaration"/>
  </patternSpecifications>
  <patternSpecifications id="_PFOU0PbeEeOeYJP9coicjQ" name="FindClasses">
    <nodes xsi:type="specification:PSAnnotation" id="_PFOU0fbeEeOeYJP9coicjQ" type="#_PFOU0PbeEeOeYJP9coicjQ"/>
    <nodes xsi:type="specification:PSObject" id="_P-e4EPbeEeOeYJP9coicjQ" name="obj1"
        instanceOf="http://palladiosimulator.org/PalladioComponentModel/5.1#//seff/CallAction"/>
  </patternSpecifications>
  <patternSpecifications id="_2aa-cLXlEeWOkIpooXnIiQ" name="CandidateSeffMethod">
    <connections xsi:type="specification:PSLink" id="_AP510LXmEeWOkIpooXnIiQ" name="link1"
        source="#_3WrLULXlEeWOkIpooXnIiQ" target="#_5jTa8LXlEeWOkIpooXnIiQ" instanceOf="http://somox.org/SourceCodeDecorator/2.0#//Seff2MethodLink/blockstatement"/>
    <connections xsi:type="specification:PSLink" id="_A8EYQLXmEeWOkIpooXnIiQ" name="annotatedElement"
        source="#_2aa-cbXlEeWOkIpooXnIiQ" target="#_5jTa8LXlEeWOkIpooXnIiQ" qualifier="seffblock"
        instanceOf="http://org.reclipse.structure.inference.annotations#//ASGAnnotation/annotatedElements"/>
    <connections xsi:type="specification:PSLink" id="_1bvsUNMIEeWcFIwc26d39g" name="link3"
        source="#_3WrLULXlEeWOkIpooXnIiQ" target="#_wYnwsNMIEeWcFIwc26d39g" instanceOf="http://somox.org/SourceCodeDecorator/2.0#//Seff2MethodLink/seff"/>
    <connections xsi:type="specification:PSLink" id="_3ur_UNMIEeWcFIwc26d39g" name="annotatedElement"
        source="#_2aa-cbXlEeWOkIpooXnIiQ" target="#_wYnwsNMIEeWcFIwc26d39g" qualifier="seff"
        instanceOf="http://org.reclipse.structure.inference.annotations#//ASGAnnotation/annotatedElements"/>
    <connections xsi:type="specification:PSLink" id="__INKkNMIEeWcFIwc26d39g" name="link5"
        source="#_55fqANMIEeWcFIwc26d39g" target="#_5jTa8LXlEeWOkIpooXnIiQ" instanceOf="http://www.eclipse.org/MoDisco/Java/0.2.incubation/java#//AbstractMethodDeclaration/body"/>
    <connections xsi:type="specification:PSLink" id="__3DNkNMIEeWcFIwc26d39g" name="annotatedElement"
        source="#_2aa-cbXlEeWOkIpooXnIiQ" target="#_55fqANMIEeWcFIwc26d39g" qualifier="method"
        instanceOf="http://org.reclipse.structure.inference.annotations#//ASGAnnotation/annotatedElements"/>
    <nodes xsi:type="specification:PSAnnotation" id="_2aa-cbXlEeWOkIpooXnIiQ" outgoing="#_A8EYQLXmEeWOkIpooXnIiQ #_3ur_UNMIEeWcFIwc26d39g #__3DNkNMIEeWcFIwc26d39g"
        type="#_2aa-cLXlEeWOkIpooXnIiQ"/>
    <nodes xsi:type="specification:PSObject" id="_3WrLULXlEeWOkIpooXnIiQ" name="link"
        outgoing="#_AP510LXmEeWOkIpooXnIiQ #_1bvsUNMIEeWcFIwc26d39g" instanceOf="http://somox.org/SourceCodeDecorator/2.0#//Seff2MethodLink"/>
    <nodes xsi:type="specification:PSObject" id="_5jTa8LXlEeWOkIpooXnIiQ" name="block"
        incoming="#_AP510LXmEeWOkIpooXnIiQ #_A8EYQLXmEeWOkIpooXnIiQ #__INKkNMIEeWcFIwc26d39g"
        instanceOf="http://www.eclipse.org/MoDisco/Java/0.2.incubation/java#//Block"/>
    <nodes xsi:type="specification:PSObject" id="_wYnwsNMIEeWcFIwc26d39g" name="seff"
        incoming="#_1bvsUNMIEeWcFIwc26d39g #_3ur_UNMIEeWcFIwc26d39g" instanceOf="http://palladiosimulator.org/PalladioComponentModel/5.1#//seff/ServiceEffectSpecification"/>
    <nodes xsi:type="specification:PSObject" id="_55fqANMIEeWcFIwc26d39g" name="containingMethod"
        outgoing="#__INKkNMIEeWcFIwc26d39g" incoming="#__3DNkNMIEeWcFIwc26d39g" instanceOf="http://www.eclipse.org/MoDisco/Java/0.2.incubation/java#//MethodDeclaration"/>
  </patternSpecifications>
  <patternSpecifications id="_GNgcsLXmEeWOkIpooXnIiQ" name="ExcessiveAllocationCandidate">
    <connections xsi:type="specification:PSPath" id="_cqUoALXmEeWOkIpooXnIiQ" name="path1"
        source="#_S5PvgLXmEeWOkIpooXnIiQ" target="#_Tl9EgLXmEeWOkIpooXnIiQ"/>
    <connections xsi:type="specification:PSPath" id="_c_EwALXmEeWOkIpooXnIiQ" name="path2"
        source="#_SJjX8LXmEeWOkIpooXnIiQ" target="#_S5PvgLXmEeWOkIpooXnIiQ"/>
    <connections xsi:type="specification:PSLink" id="_dpQ8gLXmEeWOkIpooXnIiQ" name="link1"
        source="#_UrFgkLXmEeWOkIpooXnIiQ" target="#_SJjX8LXmEeWOkIpooXnIiQ" qualifier="seffblock"
        instanceOf="http://org.reclipse.structure.inference.annotations#//ASGAnnotation/annotatedElements"/>
    <connections xsi:type="specification:PSLink" id="_eLXkALXmEeWOkIpooXnIiQ" name="annotatedElement"
        source="#_GNgcsbXmEeWOkIpooXnIiQ" target="#_Tl9EgLXmEeWOkIpooXnIiQ" qualifier="excessiveAllocation"
        instanceOf="http://org.reclipse.structure.inference.annotations#//ASGAnnotation/annotatedElements"/>
    <nodes xsi:type="specification:PSAnnotation" id="_GNgcsbXmEeWOkIpooXnIiQ" outgoing="#_eLXkALXmEeWOkIpooXnIiQ"
        type="#_GNgcsLXmEeWOkIpooXnIiQ"/>
    <nodes xsi:type="specification:PSObject" id="_SJjX8LXmEeWOkIpooXnIiQ" name="obj1"
        outgoing="#_c_EwALXmEeWOkIpooXnIiQ" incoming="#_dpQ8gLXmEeWOkIpooXnIiQ" instanceOf="http://www.eclipse.org/MoDisco/Java/0.2.incubation/java#//Block"/>
    <nodes xsi:type="specification:PSObject" id="_S5PvgLXmEeWOkIpooXnIiQ" name="obj2"
        outgoing="#_cqUoALXmEeWOkIpooXnIiQ" incoming="#_c_EwALXmEeWOkIpooXnIiQ" instanceOf="http://www.eclipse.org/MoDisco/Java/0.2.incubation/java#//ForStatement"/>
    <nodes xsi:type="specification:PSObject" id="_Tl9EgLXmEeWOkIpooXnIiQ" name="obj3"
        incoming="#_cqUoALXmEeWOkIpooXnIiQ #_eLXkALXmEeWOkIpooXnIiQ" instanceOf="http://www.eclipse.org/MoDisco/Java/0.2.incubation/java#//ArrayCreation"/>
    <nodes xsi:type="specification:PSAnnotation" id="_UrFgkLXmEeWOkIpooXnIiQ" name="seffMethod"
        outgoing="#_dpQ8gLXmEeWOkIpooXnIiQ" type="#_2aa-cLXlEeWOkIpooXnIiQ"/>
  </patternSpecifications>
</specification:PSCatalog>

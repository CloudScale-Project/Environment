<?xml version="1.0" encoding="UTF-8"?>
<specification:PSCatalog xmi:version="2.0" xmlns:xmi="http://www.omg.org/XMI" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
    xmlns:specification="http://www.reclipse.org/ns/specification" id="_3n5RYPVhEeOgbJdIqiztvw" name="New Catalog" metamodel="org.reclipse.kdm2pcm.sourcecodedecorator">
  <patternSpecifications id="_8u6OgPVhEeOgbJdIqiztvw" name="AcquireReleasePair">
    <connections xsi:type="specification:PSLink" id="_VF19IPViEeOgbJdIqiztvw" name="link1"
        source="#_-WpBUPVhEeOgbJdIqiztvw" target="#_LWXAEPViEeOgbJdIqiztvw" instanceOf="http://sdq.ipd.uka.de/PalladioComponentModel/5.0#//seff/ResourceDemandingBehaviour/steps_Behaviour"/>
    <connections xsi:type="specification:PSLink" id="_V51l4PViEeOgbJdIqiztvw" name="link2"
        source="#_-WpBUPVhEeOgbJdIqiztvw" target="#_LrRgIPViEeOgbJdIqiztvw" instanceOf="http://sdq.ipd.uka.de/PalladioComponentModel/5.0#//seff/ResourceDemandingBehaviour/steps_Behaviour"/>
    <connections xsi:type="specification:PSLink" id="_nlJKAPViEeOgbJdIqiztvw" name="link3"
        source="#_LrRgIPViEeOgbJdIqiztvw" target="#_jVVhsPViEeOgbJdIqiztvw" instanceOf="http://sdq.ipd.uka.de/PalladioComponentModel/5.0#//seff/ReleaseAction/passiveResource_ReleaseAction"/>
    <connections xsi:type="specification:PSLink" id="_oHb-wPViEeOgbJdIqiztvw" name="link4"
        source="#_LWXAEPViEeOgbJdIqiztvw" target="#_jVVhsPViEeOgbJdIqiztvw" instanceOf="http://sdq.ipd.uka.de/PalladioComponentModel/5.0#//seff/AcquireAction/passiveresource_AcquireAction"/>
    <connections xsi:type="specification:PSLink" id="_t4ohUPViEeOgbJdIqiztvw" name="annotatedElement"
        source="#_8u6OgfVhEeOgbJdIqiztvw" target="#_LrRgIPViEeOgbJdIqiztvw" qualifier="release"
        instanceOf="http://org.reclipse.structure.inference.annotations#//ASGAnnotation/annotatedElements"/>
    <connections xsi:type="specification:PSLink" id="_ufK84PViEeOgbJdIqiztvw" name="annotatedElement"
        source="#_8u6OgfVhEeOgbJdIqiztvw" target="#_LWXAEPViEeOgbJdIqiztvw" qualifier="acquire"
        instanceOf="http://org.reclipse.structure.inference.annotations#//ASGAnnotation/annotatedElements"/>
    <connections xsi:type="specification:PSLink" id="_iBGuIPVsEeOhXMVyYRdIxA" name="link7"
        source="#_R8FLUPVsEeOhXMVyYRdIxA" target="#_bXgj8PVsEeOhXMVyYRdIxA" qualifier="primitiveComponent"
        instanceOf="http://org.reclipse.structure.inference.annotations#//ASGAnnotation/annotatedElements"/>
    <connections xsi:type="specification:PSLink" id="_Ao5W0PVtEeOhXMVyYRdIxA" name="link8"
        source="#_bXgj8PVsEeOhXMVyYRdIxA" target="#_-WpBUPVhEeOgbJdIqiztvw" instanceOf="http://sdq.ipd.uka.de/PalladioComponentModel/5.0#//repository/BasicComponent/serviceEffectSpecifications__BasicComponent"/>
    <nodes xsi:type="specification:PSAnnotation" id="_8u6OgfVhEeOgbJdIqiztvw" outgoing="#_t4ohUPViEeOgbJdIqiztvw #_ufK84PViEeOgbJdIqiztvw"
        type="#_8u6OgPVhEeOgbJdIqiztvw"/>
    <nodes xsi:type="specification:PSObject" id="_-WpBUPVhEeOgbJdIqiztvw" name="behaviour"
        outgoing="#_VF19IPViEeOgbJdIqiztvw #_V51l4PViEeOgbJdIqiztvw" incoming="#_Ao5W0PVtEeOhXMVyYRdIxA"
        instanceOf="http://sdq.ipd.uka.de/PalladioComponentModel/5.0#//seff/ResourceDemandingSEFF"/>
    <nodes xsi:type="specification:PSObject" id="_LWXAEPViEeOgbJdIqiztvw" name="acquireAction"
        outgoing="#_oHb-wPViEeOgbJdIqiztvw" incoming="#_VF19IPViEeOgbJdIqiztvw #_ufK84PViEeOgbJdIqiztvw"
        instanceOf="http://sdq.ipd.uka.de/PalladioComponentModel/5.0#//seff/AcquireAction"/>
    <nodes xsi:type="specification:PSObject" id="_LrRgIPViEeOgbJdIqiztvw" name="releaseAction"
        outgoing="#_nlJKAPViEeOgbJdIqiztvw" incoming="#_V51l4PViEeOgbJdIqiztvw #_t4ohUPViEeOgbJdIqiztvw"
        instanceOf="http://sdq.ipd.uka.de/PalladioComponentModel/5.0#//seff/ReleaseAction"/>
    <nodes xsi:type="specification:PSObject" id="_jVVhsPViEeOgbJdIqiztvw" name="passiveResource"
        incoming="#_nlJKAPViEeOgbJdIqiztvw #_oHb-wPViEeOgbJdIqiztvw" instanceOf="http://sdq.ipd.uka.de/PalladioComponentModel/5.0#//repository/PassiveResource"/>
    <nodes xsi:type="specification:PSAnnotation" id="_R8FLUPVsEeOhXMVyYRdIxA" name="annoPrimitiveComponent"
        outgoing="#_iBGuIPVsEeOhXMVyYRdIxA" type="#_7E0R8PVmEeO8apkjJxxSYA"/>
    <nodes xsi:type="specification:PSObject" id="_bXgj8PVsEeOhXMVyYRdIxA" name="primitiveComponent"
        outgoing="#_Ao5W0PVtEeOhXMVyYRdIxA" incoming="#_iBGuIPVsEeOhXMVyYRdIxA" instanceOf="http://sdq.ipd.uka.de/PalladioComponentModel/5.0#//repository/BasicComponent"/>
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
        incoming="#_BdcOoPVsEeOhXMVyYRdIxA #_EKcAYPVsEeOhXMVyYRdIxA" instanceOf="http://sdq.ipd.uka.de/PalladioComponentModel/5.0#//repository/BasicComponent"/>
  </patternSpecifications>
  <patternSpecifications id="_yArXoPbJEeOeYJP9coicjQ" name="Pool">
    <nodes xsi:type="specification:PSAnnotation" id="_yBX7MPbJEeOeYJP9coicjQ" type="#_yArXoPbJEeOeYJP9coicjQ"/>
    <nodes xsi:type="specification:PSObject" id="_9IXdwPbJEeOeYJP9coicjQ" name="obj1"
        instanceOf="http://www.eclipse.org/MoDisco/Java/0.2.incubation/java#//ASTNode"/>
  </patternSpecifications>
  <patternSpecifications id="_cpjMkPbKEeOeYJP9coicjQ" name="SynchronizedMethod">
    <connections xsi:type="specification:PSLink" id="_KuCMwPbLEeOeYJP9coicjQ" name="link1"
        source="#_d3HL4PbKEeOeYJP9coicjQ" target="#_js-MAPbKEeOeYJP9coicjQ" instanceOf="http://www.eclipse.org/MoDisco/Java/0.2.incubation/java#//BodyDeclaration/modifier"/>
    <connections xsi:type="specification:PSLink" id="_Lrsu4PbLEeOeYJP9coicjQ" name="annotatedElement"
        source="#_cpjMkfbKEeOeYJP9coicjQ" target="#_d3HL4PbKEeOeYJP9coicjQ" qualifier="synchronizedMethods"
        instanceOf="http://org.reclipse.structure.inference.annotations#//ASGAnnotation/annotatedElements"/>
    <connections xsi:type="specification:PSLink" id="_wP2YkPbcEeOeYJP9coicjQ" name="link3"
        source="#_KiQx4PbcEeOeYJP9coicjQ" target="#_d3HL4PbKEeOeYJP9coicjQ" qualifier="methods"
        instanceOf="http://org.reclipse.structure.inference.annotations#//ASGAnnotation/annotatedElements"/>
    <nodes xsi:type="specification:PSAnnotation" id="_cpjMkfbKEeOeYJP9coicjQ" outgoing="#_Lrsu4PbLEeOeYJP9coicjQ"
        type="#_cpjMkPbKEeOeYJP9coicjQ"/>
    <nodes xsi:type="specification:PSObject" id="_d3HL4PbKEeOeYJP9coicjQ" name="method"
        outgoing="#_KuCMwPbLEeOeYJP9coicjQ" incoming="#_Lrsu4PbLEeOeYJP9coicjQ #_wP2YkPbcEeOeYJP9coicjQ"
        instanceOf="http://www.eclipse.org/MoDisco/Java/0.2.incubation/java#//MethodDeclaration"/>
    <nodes xsi:type="specification:PSObject" id="_js-MAPbKEeOeYJP9coicjQ" name="modifier"
        incoming="#_KuCMwPbLEeOeYJP9coicjQ" instanceOf="http://www.eclipse.org/MoDisco/Java/0.2.incubation/java#//Modifier">
      <nodeConstraints xsi:type="specification:PSAttributeConstraint" id="_-132gPbLEeOeYJP9coicjQ"
          valueExpression="true" operator="EQUAL" attribute="http://www.eclipse.org/MoDisco/Java/0.2.incubation/java#//Modifier/synchronized"/>
    </nodes>
    <nodes xsi:type="specification:PSAnnotation" id="_KiQx4PbcEeOeYJP9coicjQ" name="methods"
        outgoing="#_wP2YkPbcEeOeYJP9coicjQ" type="#_cY9jIPbbEeOeYJP9coicjQ"/>
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
        instanceOf="http://sdq.ipd.uka.de/PalladioComponentModel/5.0#//seff/CallAction"/>
  </patternSpecifications>
</specification:PSCatalog>

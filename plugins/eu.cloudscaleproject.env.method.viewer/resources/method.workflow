<?xml version="1.0" encoding="ASCII"?>
<xmi:XMI xmi:version="2.0" xmlns:xmi="http://www.omg.org/XMI" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:al="http://eclipse.org/graphiti/mm/algorithms" xmlns:method="http://eu.cloudscale.env.method/MethodComponentModel/1.0" xmlns:pi="http://eclipse.org/graphiti/mm/pictograms">
  <pi:Diagram visible="true" gridUnit="10" diagramTypeId="Workflow diagram" name="method" snapToGrid="true" pictogramLinks="/0/@children.0/@link /0/@children.0/@children.1/@link /0/@children.1/@link /0/@children.2/@link /0/@children.3/@link /0/@children.0/@children.2/@link /0/@children.0/@children.3/@link /0/@children.1/@children.1/@link /0/@children.1/@children.2/@link /0/@children.1/@children.3/@link /0/@children.2/@children.1/@link /0/@children.2/@children.2/@link /0/@children.2/@children.3/@link /0/@children.3/@children.1/@link /0/@children.3/@children.2/@link /0/@children.3/@children.3/@link /0/@children.3/@children.4/@link /0/@children.3/@children.5/@link /0/@children.3/@children.6/@link /0/@children.2/@children.1/@children.1/@link /0/@children.2/@children.1/@children.2/@link /0/@children.4/@link /0/@children.5/@link /0/@children.6/@link /0/@children.7/@link /0/@connections.0/@link /0/@connections.1/@link /0/@connections.2/@link /0/@connections.3/@link /0/@connections.4/@link /0/@connections.5/@link /0/@connections.6/@link /0/@connections.7/@link /0/@connections.8/@link /0/@connections.9/@link /0/@connections.10/@link /0/@connections.11/@link /0/@connections.12/@link /0/@connections.13/@link /0/@connections.14/@link /0/@connections.15/@link /0/@children.2/@children.2/@children.1/@link /0/@children.0/@children.3/@children.1/@link /0/@children.0/@children.2/@children.1/@link /0/@children.2/@children.1/@children.3/@link /0/@children.2/@children.4/@link /0/@children.0/@children.4/@link /0/@children.3/@children.4/@children.1/@link /0/@children.3/@children.5/@children.1/@link /0/@children.2/@children.2/@children.2/@link /0/@children.2/@children.2/@children.3/@link /0/@children.2/@children.2/@children.4/@link /0/@children.2/@children.1/@children.4/@link /0/@children.1/@children.3/@children.1/@link /0/@children.2/@children.5/@link /0/@children.8/@link /0/@connections.16/@link /0/@children.3/@children.7/@link /0/@children.3/@children.8/@link /0/@children.3/@children.1/@children.1/@link /0/@children.3/@children.2/@children.1/@link /0/@children.2/@children.1/@children.5/@link /0/@children.2/@children.1/@children.6/@link /0/@children.0/@children.1/@children.1/@link /0/@children.0/@children.1/@children.2/@link /0/@children.1/@children.1/@children.1/@link /0/@children.2/@children.2/@children.5/@link /0/@connections.17/@link /0/@children.2/@children.1/@children.7/@link /0/@children.1/@children.4/@link" version="0.11.0">
    <graphicsAlgorithm xsi:type="al:Rectangle" background="/0/@colors.1" foreground="/0/@colors.0" lineWidth="1" transparency="0.0" width="1000" height="1000"/>
    <children xsi:type="pi:ContainerShape" visible="true" active="true">
      <properties key="node" value="node"/>
      <properties key="container" value="container"/>
      <graphicsAlgorithm xsi:type="al:RoundedRectangle" background="/0/@colors.1" foreground="/0/@colors.2" lineWidth="1" transparency="0.0" width="311" height="261" x="60" y="20" cornerHeight="15" cornerWidth="15"/>
      <link businessObjects="_SqDU0C61EeSADewJHO2y3w"/>
      <children visible="true">
        <properties key="node_name" value="node_name"/>
        <graphicsAlgorithm xsi:type="al:Text" foreground="/0/@colors.2" lineWidth="1" filled="false" transparency="0.0" width="306" height="20" x="5" y="5" value="Extractor"/>
      </children>
      <children xsi:type="pi:ContainerShape" visible="true" active="true">
        <properties key="node" value="node"/>
        <properties key="section" value="section"/>
        <graphicsAlgorithm xsi:type="al:RoundedRectangle" background="/0/@colors.3" foreground="/0/@colors.2" lineWidth="1" transparency="0.0" width="141" height="111" x="160" y="120" cornerHeight="15" cornerWidth="15"/>
        <link businessObjects="eu.cloudscaleproject.env.toolchain.ToolchainUtils.extractorRes"/>
        <anchors xsi:type="pi:ChopboxAnchor" outgoingConnections="/0/@connections.13 /0/@connections.14 /0/@connections.15" incomingConnections="/0/@connections.7"/>
        <children visible="true">
          <properties key="node_name" value="node_name"/>
          <graphicsAlgorithm xsi:type="al:Text" foreground="/0/@colors.2" lineWidth="1" filled="false" transparency="0.0" width="136" height="20" x="5" y="5" value="Results"/>
        </children>
        <children xsi:type="pi:ContainerShape" visible="true" active="true">
          <properties key="node" value="node"/>
          <properties key="requirement" value="requirement"/>
          <graphicsAlgorithm xsi:type="al:RoundedRectangle" background="/0/@colors.3" foreground="/0/@colors.2" lineWidth="1" transparency="0.0" width="131" height="20" x="5" y="53" cornerHeight="5" cornerWidth="5"/>
          <link businessObjects="eu.cloudscaleproject.env.toolchain.ToolchainUtils.extractorRes.eu.cloudscaleproject.env.toolchain.ToolchainUtils.path_repository"/>
          <children visible="true">
            <properties key="node_name" value="node_name"/>
            <graphicsAlgorithm xsi:type="al:Text" foreground="/0/@colors.4" lineWidth="1" filled="false" transparency="0.0" width="131" height="20" horizontalAlignment="ALIGNMENT_CENTER" value="Repository"/>
          </children>
        </children>
        <children xsi:type="pi:ContainerShape" visible="true" active="true">
          <properties key="node" value="node"/>
          <properties key="requirement" value="requirement"/>
          <graphicsAlgorithm xsi:type="al:RoundedRectangle" background="/0/@colors.3" foreground="/0/@colors.2" lineWidth="1" transparency="0.0" width="131" height="20" x="5" y="75" cornerHeight="5" cornerWidth="5"/>
          <link businessObjects="eu.cloudscaleproject.env.toolchain.ToolchainUtils.extractorRes.eu.cloudscaleproject.env.toolchain.ToolchainUtils.path_system"/>
          <children visible="true">
            <properties key="node_name" value="node_name"/>
            <graphicsAlgorithm xsi:type="al:Text" foreground="/0/@colors.4" lineWidth="1" filled="false" transparency="0.0" width="131" height="20" horizontalAlignment="ALIGNMENT_CENTER" value="System"/>
          </children>
        </children>
      </children>
      <children xsi:type="pi:ContainerShape" visible="true" active="true">
        <properties key="node" value="node"/>
        <properties key="section" value="section"/>
        <graphicsAlgorithm xsi:type="al:RoundedRectangle" background="/0/@colors.3" foreground="/0/@colors.2" lineWidth="1" transparency="0.0" width="121" height="71" x="20" y="50" cornerHeight="15" cornerWidth="15"/>
        <link businessObjects="eu.cloudscaleproject.env.toolchain.ToolchainUtils.extractorInput"/>
        <anchors xsi:type="pi:ChopboxAnchor" outgoingConnections="/0/@connections.6"/>
        <children visible="true">
          <properties key="node_name" value="node_name"/>
          <graphicsAlgorithm xsi:type="al:Text" foreground="/0/@colors.2" lineWidth="1" filled="false" transparency="0.0" width="116" height="20" x="5" y="5" value="Input"/>
        </children>
        <children xsi:type="pi:ContainerShape" visible="true" active="true">
          <properties key="node" value="node"/>
          <properties key="command" value="command"/>
          <graphicsAlgorithm xsi:type="al:RoundedRectangle" background="/0/@colors.3" foreground="/0/@colors.2" lineWidth="1" transparency="0.0" width="21" height="20" x="5" y="25" cornerHeight="5" cornerWidth="5"/>
          <link businessObjects="_1QP7cDRVEeSMLJ8vnBP-0A"/>
          <children visible="true">
            <properties key="node_name" value="node_name"/>
            <graphicsAlgorithm xsi:type="al:Text" foreground="/0/@colors.4" lineWidth="1" filled="false" transparency="0.0" width="21" height="20" horizontalAlignment="ALIGNMENT_CENTER" value="v"/>
          </children>
        </children>
      </children>
      <children xsi:type="pi:ContainerShape" visible="true" active="true">
        <properties key="node" value="node"/>
        <properties key="section" value="section"/>
        <graphicsAlgorithm xsi:type="al:RoundedRectangle" background="/0/@colors.3" foreground="/0/@colors.2" lineWidth="1" transparency="0.0" width="121" height="71" x="20" y="140" cornerHeight="15" cornerWidth="15"/>
        <link businessObjects="eu.cloudscaleproject.env.toolchain.ToolchainUtils.extractorConf"/>
        <anchors xsi:type="pi:ChopboxAnchor" outgoingConnections="/0/@connections.7" incomingConnections="/0/@connections.6"/>
        <children visible="true">
          <properties key="node_name" value="node_name"/>
          <graphicsAlgorithm xsi:type="al:Text" foreground="/0/@colors.2" lineWidth="1" filled="false" transparency="0.0" width="116" height="20" x="5" y="5" value="Extractor"/>
        </children>
        <children xsi:type="pi:ContainerShape" visible="true" active="true">
          <properties key="node" value="node"/>
          <properties key="command" value="command"/>
          <graphicsAlgorithm xsi:type="al:RoundedRectangle" background="/0/@colors.3" foreground="/0/@colors.2" lineWidth="1" transparency="0.0" width="21" height="20" x="5" y="25" cornerHeight="5" cornerWidth="5"/>
          <link businessObjects="_6bS5cDRUEeSMLJ8vnBP-0A"/>
          <children visible="true">
            <properties key="node_name" value="node_name"/>
            <graphicsAlgorithm xsi:type="al:Text" foreground="/0/@colors.4" lineWidth="1" filled="false" transparency="0.0" width="21" height="20" horizontalAlignment="ALIGNMENT_CENTER" value="v"/>
          </children>
        </children>
      </children>
      <children xsi:type="pi:ContainerShape" visible="true" active="true">
        <properties key="node" value="node"/>
        <properties key="command" value="command"/>
        <graphicsAlgorithm xsi:type="al:RoundedRectangle" background="/0/@colors.3" foreground="/0/@colors.2" lineWidth="1" transparency="0.0" width="21" height="20" x="285" y="5" cornerHeight="5" cornerWidth="5"/>
        <link businessObjects="_3sl3EDc_EeSoZdJQhqDUDw"/>
        <children visible="true">
          <properties key="node_name" value="node_name"/>
          <graphicsAlgorithm xsi:type="al:Text" foreground="/0/@colors.4" lineWidth="1" filled="false" transparency="0.0" width="21" height="20" horizontalAlignment="ALIGNMENT_CENTER" value="?"/>
        </children>
      </children>
    </children>
    <children xsi:type="pi:ContainerShape" visible="true" active="true">
      <properties key="node" value="node"/>
      <properties key="container" value="container"/>
      <graphicsAlgorithm xsi:type="al:RoundedRectangle" background="/0/@colors.1" foreground="/0/@colors.2" lineWidth="1" transparency="0.0" width="181" height="231" x="470" y="120" cornerHeight="15" cornerWidth="15"/>
      <link businessObjects="_YVJPYC61EeSADewJHO2y3w"/>
      <children visible="true">
        <properties key="node_name" value="node_name"/>
        <graphicsAlgorithm xsi:type="al:Text" foreground="/0/@colors.2" lineWidth="1" filled="false" transparency="0.0" width="176" height="20" x="5" y="5" value="ScaleDL"/>
      </children>
      <children xsi:type="pi:ContainerShape" visible="true" active="true">
        <properties key="node" value="node"/>
        <properties key="section" value="section"/>
        <graphicsAlgorithm xsi:type="al:RoundedRectangle" background="/0/@colors.3" foreground="/0/@colors.2" lineWidth="1" transparency="0.0" width="141" height="51" x="20" y="40" cornerHeight="15" cornerWidth="15"/>
        <link businessObjects="eu.cloudscaleproject.env.toolchain.ToolchainUtils.overview"/>
        <anchors xsi:type="pi:ChopboxAnchor" outgoingConnections="/0/@connections.2" incomingConnections="/0/@connections.1"/>
        <children visible="true">
          <properties key="node_name" value="node_name"/>
          <graphicsAlgorithm xsi:type="al:Text" foreground="/0/@colors.2" lineWidth="1" filled="false" transparency="0.0" width="136" height="20" x="5" y="5" value="Overview model"/>
        </children>
        <children xsi:type="pi:ContainerShape" visible="true" active="true">
          <properties key="node" value="node"/>
          <properties key="command" value="command"/>
          <graphicsAlgorithm xsi:type="al:RoundedRectangle" background="/0/@colors.3" foreground="/0/@colors.2" lineWidth="1" transparency="0.0" width="21" height="20" x="5" y="25" cornerHeight="5" cornerWidth="5"/>
          <link businessObjects="_KUsqwBMzEeWrnYazqRxW7w"/>
          <children visible="true">
            <properties key="node_name" value="node_name"/>
            <graphicsAlgorithm xsi:type="al:Text" foreground="/0/@colors.4" lineWidth="1" filled="false" transparency="0.0" width="21" height="20" horizontalAlignment="ALIGNMENT_CENTER" value="v"/>
          </children>
        </children>
      </children>
      <children xsi:type="pi:ContainerShape" visible="true" active="true">
        <properties key="node" value="node"/>
        <properties key="section" value="section"/>
        <graphicsAlgorithm xsi:type="al:RoundedRectangle" background="/0/@colors.3" foreground="/0/@colors.2" lineWidth="1" transparency="0.0" width="141" height="46" x="20" y="105" cornerHeight="15" cornerWidth="15"/>
        <link businessObjects="architectureTemplates"/>
        <anchors xsi:type="pi:ChopboxAnchor"/>
        <children visible="true">
          <properties key="node_name" value="node_name"/>
          <graphicsAlgorithm xsi:type="al:Text" foreground="/0/@colors.2" lineWidth="1" filled="false" transparency="0.0" width="136" height="20" x="5" y="5" value="Architecture Templ."/>
        </children>
      </children>
      <children xsi:type="pi:ContainerShape" visible="true" active="true">
        <properties key="node" value="node"/>
        <properties key="section" value="section"/>
        <graphicsAlgorithm xsi:type="al:RoundedRectangle" background="/0/@colors.3" foreground="/0/@colors.2" lineWidth="1" transparency="0.0" width="141" height="56" x="20" y="165" cornerHeight="15" cornerWidth="15"/>
        <link businessObjects="eu.cloudscaleproject.env.toolchain.ToolchainUtils.usageevolution"/>
        <anchors xsi:type="pi:ChopboxAnchor"/>
        <children visible="true">
          <properties key="node_name" value="node_name"/>
          <graphicsAlgorithm xsi:type="al:Text" foreground="/0/@colors.2" lineWidth="1" filled="false" transparency="0.0" width="136" height="20" x="5" y="5" value="Usage evolution"/>
        </children>
        <children xsi:type="pi:ContainerShape" visible="true" active="true">
          <properties key="node" value="node"/>
          <properties key="command" value="command"/>
          <graphicsAlgorithm xsi:type="al:RoundedRectangle" background="/0/@colors.3" foreground="/0/@colors.2" lineWidth="1" transparency="0.0" width="21" height="20" x="5" y="25" cornerHeight="5" cornerWidth="5"/>
          <link businessObjects="_iueiAHPSEeS3b6FdlQPPqA"/>
          <children visible="true">
            <properties key="node_name" value="node_name"/>
            <graphicsAlgorithm xsi:type="al:Text" foreground="/0/@colors.4" lineWidth="1" filled="false" transparency="0.0" width="21" height="20" horizontalAlignment="ALIGNMENT_CENTER" value="v"/>
          </children>
        </children>
      </children>
      <children xsi:type="pi:ContainerShape" visible="true" active="true">
        <properties key="node" value="node"/>
        <properties key="command" value="command"/>
        <graphicsAlgorithm xsi:type="al:RoundedRectangle" background="/0/@colors.3" foreground="/0/@colors.2" lineWidth="1" transparency="0.0" width="-1" height="-1" x="180" y="130" cornerHeight="5" cornerWidth="5"/>
        <link businessObjects="_OjbRUDabEeWxRdp7yxfGjg"/>
        <children visible="true">
          <properties key="node_name" value="node_name"/>
          <graphicsAlgorithm xsi:type="al:Text" foreground="/0/@colors.4" lineWidth="1" filled="false" transparency="0.0" width="-6" height="20" x="5" y="5" value="name"/>
        </children>
      </children>
    </children>
    <children xsi:type="pi:ContainerShape" visible="true" active="true">
      <properties key="node" value="node"/>
      <properties key="container" value="container"/>
      <graphicsAlgorithm xsi:type="al:RoundedRectangle" background="/0/@colors.1" foreground="/0/@colors.2" lineWidth="1" transparency="0.0" width="331" height="571" x="810" y="20" cornerHeight="15" cornerWidth="15"/>
      <link businessObjects="_Yqbi4C61EeSADewJHO2y3w"/>
      <children visible="true">
        <properties key="node_name" value="node_name"/>
        <graphicsAlgorithm xsi:type="al:Text" foreground="/0/@colors.2" lineWidth="1" filled="false" transparency="0.0" width="326" height="20" x="5" y="5" value="Analyser"/>
      </children>
      <children xsi:type="pi:ContainerShape" visible="true" active="true">
        <properties key="node" value="node"/>
        <properties key="section" value="section"/>
        <graphicsAlgorithm xsi:type="al:RoundedRectangle" background="/0/@colors.3" foreground="/0/@colors.2" lineWidth="1" transparency="0.0" width="251" height="201" x="40" y="80" cornerHeight="15" cornerWidth="15"/>
        <link businessObjects="eu.cloudscaleproject.env.toolchain.ToolchainUtils.analyserInput"/>
        <anchors xsi:type="pi:ChopboxAnchor" outgoingConnections="/0/@connections.4" incomingConnections="/0/@connections.0 /0/@connections.3 /0/@connections.16"/>
        <children visible="true">
          <properties key="node_name" value="node_name"/>
          <graphicsAlgorithm xsi:type="al:Text" foreground="/0/@colors.2" lineWidth="1" filled="false" transparency="0.0" width="246" height="20" x="5" y="5" value="Analyser input"/>
        </children>
        <children xsi:type="pi:ContainerShape" visible="true" active="true">
          <properties key="node" value="node"/>
          <properties key="requirement" value="requirement"/>
          <graphicsAlgorithm xsi:type="al:RoundedRectangle" background="/0/@colors.3" foreground="/0/@colors.2" lineWidth="1" transparency="0.0" width="241" height="20" x="5" y="97" cornerHeight="5" cornerWidth="5"/>
          <link businessObjects="eu.cloudscaleproject.env.toolchain.ToolchainUtils.analyserInput.eu.cloudscaleproject.env.toolchain.ToolchainUtils.path_allocation"/>
          <children visible="true">
            <properties key="node_name" value="node_name"/>
            <graphicsAlgorithm xsi:type="al:Text" foreground="/0/@colors.4" lineWidth="1" filled="false" transparency="0.0" width="241" height="20" horizontalAlignment="ALIGNMENT_CENTER" value="Allocation"/>
          </children>
        </children>
        <children xsi:type="pi:ContainerShape" visible="true" active="true">
          <properties key="node" value="node"/>
          <properties key="requirement" value="requirement"/>
          <graphicsAlgorithm xsi:type="al:RoundedRectangle" background="/0/@colors.3" foreground="/0/@colors.2" lineWidth="1" transparency="0.0" width="241" height="20" x="5" y="119" cornerHeight="5" cornerWidth="5"/>
          <link businessObjects="eu.cloudscaleproject.env.toolchain.ToolchainUtils.analyserInput.eu.cloudscaleproject.env.toolchain.ToolchainUtils.path_resourceenvironment"/>
          <children visible="true">
            <properties key="node_name" value="node_name"/>
            <graphicsAlgorithm xsi:type="al:Text" foreground="/0/@colors.4" lineWidth="1" filled="false" transparency="0.0" width="241" height="20" horizontalAlignment="ALIGNMENT_CENTER" value="Resource environment"/>
          </children>
        </children>
        <children xsi:type="pi:ContainerShape" visible="true" active="true">
          <properties key="node" value="node"/>
          <properties key="command" value="command"/>
          <graphicsAlgorithm xsi:type="al:RoundedRectangle" background="/0/@colors.3" foreground="/0/@colors.2" lineWidth="1" transparency="0.0" width="21" height="20" x="5" y="25" cornerHeight="5" cornerWidth="5"/>
          <link businessObjects="_7zWcEDRVEeSMLJ8vnBP-0A"/>
          <children visible="true">
            <properties key="node_name" value="node_name"/>
            <graphicsAlgorithm xsi:type="al:Text" foreground="/0/@colors.4" lineWidth="1" filled="false" transparency="0.0" width="21" height="20" horizontalAlignment="ALIGNMENT_CENTER" value="v"/>
          </children>
        </children>
        <children xsi:type="pi:ContainerShape" visible="true" active="true">
          <properties key="node" value="node"/>
          <properties key="requirement" value="requirement"/>
          <graphicsAlgorithm xsi:type="al:RoundedRectangle" background="/0/@colors.3" foreground="/0/@colors.2" lineWidth="1" transparency="0.0" width="241" height="20" x="5" y="141" cornerHeight="5" cornerWidth="5"/>
          <link businessObjects="eu.cloudscaleproject.env.toolchain.ToolchainUtils.analyserInput.eu.cloudscaleproject.env.toolchain.ToolchainUtils.path_usage"/>
          <children visible="true">
            <properties key="node_name" value="node_name"/>
            <graphicsAlgorithm xsi:type="al:Text" foreground="/0/@colors.4" lineWidth="1" filled="false" transparency="0.0" width="241" height="20" horizontalAlignment="ALIGNMENT_CENTER" value="Usage"/>
          </children>
        </children>
        <children xsi:type="pi:ContainerShape" visible="true" active="true">
          <properties key="node" value="node"/>
          <properties key="requirement" value="requirement"/>
          <graphicsAlgorithm xsi:type="al:RoundedRectangle" background="/0/@colors.3" foreground="/0/@colors.2" lineWidth="1" transparency="0.0" width="241" height="20" x="5" y="53" cornerHeight="5" cornerWidth="5"/>
          <link businessObjects="eu.cloudscaleproject.env.toolchain.ToolchainUtils.analyserInput.eu.cloudscaleproject.env.toolchain.ToolchainUtils.path_repository"/>
          <children visible="true">
            <properties key="node_name" value="node_name"/>
            <graphicsAlgorithm xsi:type="al:Text" foreground="/0/@colors.4" lineWidth="1" filled="false" transparency="0.0" width="241" height="20" horizontalAlignment="ALIGNMENT_CENTER" value="Repository"/>
          </children>
        </children>
        <children xsi:type="pi:ContainerShape" visible="true" active="true">
          <properties key="node" value="node"/>
          <properties key="requirement" value="requirement"/>
          <graphicsAlgorithm xsi:type="al:RoundedRectangle" background="/0/@colors.3" foreground="/0/@colors.2" lineWidth="1" transparency="0.0" width="241" height="20" x="5" y="75" cornerHeight="5" cornerWidth="5"/>
          <link businessObjects="eu.cloudscaleproject.env.toolchain.ToolchainUtils.analyserInput.eu.cloudscaleproject.env.toolchain.ToolchainUtils.path_system"/>
          <children visible="true">
            <properties key="node_name" value="node_name"/>
            <graphicsAlgorithm xsi:type="al:Text" foreground="/0/@colors.4" lineWidth="1" filled="false" transparency="0.0" width="241" height="20" horizontalAlignment="ALIGNMENT_CENTER" value="System"/>
          </children>
        </children>
        <children xsi:type="pi:ContainerShape" visible="true" active="true">
          <properties key="node" value="node"/>
          <properties key="requirement" value="requirement"/>
          <graphicsAlgorithm xsi:type="al:RoundedRectangle" background="/0/@colors.3" foreground="/0/@colors.2" lineWidth="1" transparency="0.0" width="241" height="20" x="5" y="163" cornerHeight="5" cornerWidth="5"/>
          <link businessObjects="eu.cloudscaleproject.env.toolchain.ToolchainUtils.analyserInput.eu.cloudscaleproject.env.toolchain.ToolchainUtils.path_usageevolution"/>
          <children visible="true">
            <properties key="node_name" value="node_name"/>
            <graphicsAlgorithm xsi:type="al:Text" foreground="/0/@colors.4" lineWidth="1" filled="false" transparency="0.0" width="241" height="20" horizontalAlignment="ALIGNMENT_CENTER" value="Usage Evolution"/>
          </children>
        </children>
      </children>
      <children xsi:type="pi:ContainerShape" visible="true" active="true">
        <properties key="node" value="node"/>
        <properties key="section" value="section"/>
        <graphicsAlgorithm xsi:type="al:RoundedRectangle" background="/0/@colors.3" foreground="/0/@colors.2" lineWidth="1" transparency="0.0" width="251" height="151" x="40" y="310" cornerHeight="15" cornerWidth="15"/>
        <link businessObjects="eu.cloudscaleproject.env.toolchain.ToolchainUtils.analyserConf"/>
        <anchors xsi:type="pi:ChopboxAnchor" outgoingConnections="/0/@connections.5" incomingConnections="/0/@connections.4 /0/@connections.17"/>
        <children visible="true">
          <properties key="node_name" value="node_name"/>
          <graphicsAlgorithm xsi:type="al:Text" foreground="/0/@colors.2" lineWidth="1" filled="false" transparency="0.0" width="246" height="20" x="5" y="5" value="Analyser"/>
        </children>
        <children xsi:type="pi:ContainerShape" visible="true" active="true">
          <properties key="node" value="node"/>
          <properties key="command" value="command"/>
          <graphicsAlgorithm xsi:type="al:RoundedRectangle" background="/0/@colors.3" foreground="/0/@colors.2" lineWidth="1" transparency="0.0" width="21" height="20" x="5" y="25" cornerHeight="5" cornerWidth="5"/>
          <link businessObjects="_7Eo-8DHDEeSAkNNGxeU_SA"/>
          <children visible="true">
            <properties key="node_name" value="node_name"/>
            <graphicsAlgorithm xsi:type="al:Text" foreground="/0/@colors.4" lineWidth="1" filled="false" transparency="0.0" width="21" height="20" horizontalAlignment="ALIGNMENT_CENTER" value="v"/>
          </children>
        </children>
        <children xsi:type="pi:ContainerShape" visible="true" active="true">
          <properties key="node" value="node"/>
          <properties key="requirement" value="requirement"/>
          <graphicsAlgorithm xsi:type="al:RoundedRectangle" background="/0/@colors.3" foreground="/0/@colors.2" lineWidth="1" transparency="0.0" width="241" height="20" x="5" y="75" cornerHeight="5" cornerWidth="5"/>
          <link businessObjects="eu.cloudscaleproject.env.toolchain.ToolchainUtils.analyserConf.eu.cloudscaleproject.env.toolchain.ToolchainUtils.path_pcmmeasuringpoint"/>
          <children visible="true">
            <properties key="node_name" value="node_name"/>
            <graphicsAlgorithm xsi:type="al:Text" foreground="/0/@colors.4" lineWidth="1" filled="false" transparency="0.0" width="241" height="20" horizontalAlignment="ALIGNMENT_CENTER" value="Measuring points"/>
          </children>
        </children>
        <children xsi:type="pi:ContainerShape" visible="true" active="true">
          <properties key="node" value="node"/>
          <properties key="requirement" value="requirement"/>
          <graphicsAlgorithm xsi:type="al:RoundedRectangle" background="/0/@colors.3" foreground="/0/@colors.2" lineWidth="1" transparency="0.0" width="241" height="20" x="5" y="97" cornerHeight="5" cornerWidth="5"/>
          <link businessObjects="eu.cloudscaleproject.env.toolchain.ToolchainUtils.analyserConf.eu.cloudscaleproject.env.toolchain.ToolchainUtils.path_pms"/>
          <children visible="true">
            <properties key="node_name" value="node_name"/>
            <graphicsAlgorithm xsi:type="al:Text" foreground="/0/@colors.4" lineWidth="1" filled="false" transparency="0.0" width="241" height="20" horizontalAlignment="ALIGNMENT_CENTER" value="Monitors"/>
          </children>
        </children>
        <children xsi:type="pi:ContainerShape" visible="true" active="true">
          <properties key="node" value="node"/>
          <properties key="requirement" value="requirement"/>
          <graphicsAlgorithm xsi:type="al:RoundedRectangle" background="/0/@colors.3" foreground="/0/@colors.2" lineWidth="1" transparency="0.0" width="241" height="20" x="5" y="119" cornerHeight="5" cornerWidth="5"/>
          <link businessObjects="eu.cloudscaleproject.env.toolchain.ToolchainUtils.analyserConf.eu.cloudscaleproject.env.toolchain.ToolchainUtils.path_slo"/>
          <children visible="true">
            <properties key="node_name" value="node_name"/>
            <graphicsAlgorithm xsi:type="al:Text" foreground="/0/@colors.4" lineWidth="1" filled="false" transparency="0.0" width="241" height="20" horizontalAlignment="ALIGNMENT_CENTER" value="Service Level Objectives"/>
          </children>
        </children>
        <children xsi:type="pi:ContainerShape" visible="true" active="true">
          <properties key="node" value="node"/>
          <properties key="requirement" value="requirement"/>
          <graphicsAlgorithm xsi:type="al:RoundedRectangle" background="/0/@colors.3" foreground="/0/@colors.2" lineWidth="1" transparency="0.0" width="241" height="20" x="5" y="53" cornerHeight="5" cornerWidth="5"/>
          <link businessObjects="eu.cloudscaleproject.env.toolchain.ToolchainUtils.analyserConf.eu.cloudscaleproject.env.toolchain.ToolchainUtils.path_experiments"/>
          <children visible="true">
            <properties key="node_name" value="node_name"/>
            <graphicsAlgorithm xsi:type="al:Text" foreground="/0/@colors.4" lineWidth="1" filled="false" transparency="0.0" width="241" height="20" horizontalAlignment="ALIGNMENT_CENTER" value="Experiment"/>
          </children>
        </children>
      </children>
      <children xsi:type="pi:ContainerShape" visible="true" active="true">
        <properties key="node" value="node"/>
        <properties key="section" value="section"/>
        <graphicsAlgorithm xsi:type="al:RoundedRectangle" background="/0/@colors.3" foreground="/0/@colors.2" lineWidth="1" transparency="0.0" width="251" height="66" x="40" y="480" cornerHeight="15" cornerWidth="15"/>
        <link businessObjects="eu.cloudscaleproject.env.toolchain.ToolchainUtils.analyserRes"/>
        <anchors xsi:type="pi:ChopboxAnchor" incomingConnections="/0/@connections.5"/>
        <children visible="true">
          <properties key="node_name" value="node_name"/>
          <graphicsAlgorithm xsi:type="al:Text" foreground="/0/@colors.2" lineWidth="1" filled="false" transparency="0.0" width="246" height="20" x="5" y="5" value="Results"/>
        </children>
      </children>
      <children xsi:type="pi:ContainerShape" visible="true" active="true">
        <properties key="node" value="node"/>
        <properties key="command" value="command"/>
        <graphicsAlgorithm xsi:type="al:RoundedRectangle" background="/0/@colors.3" foreground="/0/@colors.2" lineWidth="1" transparency="0.0" width="21" height="20" x="279" y="5" cornerHeight="5" cornerWidth="5"/>
        <link businessObjects="_fcGHoDc_EeSoZdJQhqDUDw"/>
        <children visible="true">
          <properties key="node_name" value="node_name"/>
          <graphicsAlgorithm xsi:type="al:Text" foreground="/0/@colors.4" lineWidth="1" filled="false" transparency="0.0" width="21" height="20" horizontalAlignment="ALIGNMENT_CENTER" value="p"/>
        </children>
      </children>
      <children xsi:type="pi:ContainerShape" visible="true" active="true">
        <properties key="node" value="node"/>
        <properties key="command" value="command"/>
        <graphicsAlgorithm xsi:type="al:RoundedRectangle" background="/0/@colors.3" foreground="/0/@colors.2" lineWidth="1" transparency="0.0" width="21" height="20" x="305" y="5" cornerHeight="5" cornerWidth="5"/>
        <link businessObjects="_z_PHEPo4EeSga-ySs5-xDw"/>
        <children visible="true">
          <properties key="node_name" value="node_name"/>
          <graphicsAlgorithm xsi:type="al:Text" foreground="/0/@colors.4" lineWidth="1" filled="false" transparency="0.0" width="21" height="20" horizontalAlignment="ALIGNMENT_CENTER" value="?"/>
        </children>
      </children>
    </children>
    <children xsi:type="pi:ContainerShape" visible="true" active="true">
      <properties key="node" value="node"/>
      <properties key="container" value="container"/>
      <graphicsAlgorithm xsi:type="al:RoundedRectangle" background="/0/@colors.1" foreground="/0/@colors.2" lineWidth="1" transparency="0.0" width="601" height="251" x="60" y="370" cornerHeight="15" cornerWidth="15"/>
      <link businessObjects="_Zcc1sC61EeSADewJHO2y3w"/>
      <children visible="true">
        <properties key="node_name" value="node_name"/>
        <graphicsAlgorithm xsi:type="al:Text" foreground="/0/@colors.2" lineWidth="1" filled="false" transparency="0.0" width="596" height="20" x="5" y="5" value="Spotter"/>
      </children>
      <children xsi:type="pi:ContainerShape" visible="true" active="true">
        <properties key="node" value="node"/>
        <properties key="section" value="section"/>
        <graphicsAlgorithm xsi:type="al:RoundedRectangle" background="/0/@colors.3" foreground="/0/@colors.2" lineWidth="1" transparency="0.0" width="251" height="51" x="20" y="40" cornerHeight="15" cornerWidth="15"/>
        <link businessObjects="eu.cloudscaleproject.env.toolchain.ToolchainUtils.spotterStaInput"/>
        <anchors xsi:type="pi:ChopboxAnchor" outgoingConnections="/0/@connections.8" incomingConnections="/0/@connections.12"/>
        <children visible="true">
          <properties key="node_name" value="node_name"/>
          <graphicsAlgorithm xsi:type="al:Text" foreground="/0/@colors.2" lineWidth="1" filled="false" transparency="0.0" width="246" height="20" x="5" y="5" value="Static spotter input"/>
        </children>
        <children xsi:type="pi:ContainerShape" visible="true" active="true">
          <properties key="node" value="node"/>
          <properties key="command" value="command"/>
          <graphicsAlgorithm xsi:type="al:RoundedRectangle" background="/0/@colors.3" foreground="/0/@colors.2" lineWidth="1" transparency="0.0" width="21" height="20" x="5" y="25" cornerHeight="5" cornerWidth="5"/>
          <link businessObjects="_cwXP4Po8EeSga-ySs5-xDw"/>
          <children visible="true">
            <properties key="node_name" value="node_name"/>
            <graphicsAlgorithm xsi:type="al:Text" foreground="/0/@colors.4" lineWidth="1" filled="false" transparency="0.0" width="21" height="20" horizontalAlignment="ALIGNMENT_CENTER" value="V"/>
          </children>
        </children>
      </children>
      <children xsi:type="pi:ContainerShape" visible="true" active="true">
        <properties key="node" value="node"/>
        <properties key="section" value="section"/>
        <graphicsAlgorithm xsi:type="al:RoundedRectangle" background="/0/@colors.3" foreground="/0/@colors.2" lineWidth="1" transparency="0.0" width="251" height="51" x="20" y="110" cornerHeight="15" cornerWidth="15"/>
        <link businessObjects="eu.cloudscaleproject.env.toolchain.ToolchainUtils.spotterStaConf"/>
        <anchors xsi:type="pi:ChopboxAnchor" outgoingConnections="/0/@connections.9" incomingConnections="/0/@connections.8"/>
        <children visible="true">
          <properties key="node_name" value="node_name"/>
          <graphicsAlgorithm xsi:type="al:Text" foreground="/0/@colors.2" lineWidth="1" filled="false" transparency="0.0" width="246" height="20" x="5" y="5" value="Static spotter"/>
        </children>
        <children xsi:type="pi:ContainerShape" visible="true" active="true">
          <properties key="node" value="node"/>
          <properties key="command" value="command"/>
          <graphicsAlgorithm xsi:type="al:RoundedRectangle" background="/0/@colors.3" foreground="/0/@colors.2" lineWidth="1" transparency="0.0" width="21" height="20" x="5" y="25" cornerHeight="5" cornerWidth="5"/>
          <link businessObjects="_gElZsPo8EeSga-ySs5-xDw"/>
          <children visible="true">
            <properties key="node_name" value="node_name"/>
            <graphicsAlgorithm xsi:type="al:Text" foreground="/0/@colors.4" lineWidth="1" filled="false" transparency="0.0" width="21" height="20" horizontalAlignment="ALIGNMENT_CENTER" value="V"/>
          </children>
        </children>
      </children>
      <children xsi:type="pi:ContainerShape" visible="true" active="true">
        <properties key="node" value="node"/>
        <properties key="section" value="section"/>
        <graphicsAlgorithm xsi:type="al:RoundedRectangle" background="/0/@colors.3" foreground="/0/@colors.2" lineWidth="1" transparency="0.0" width="251" height="51" x="20" y="180" cornerHeight="15" cornerWidth="15"/>
        <link businessObjects="eu.cloudscaleproject.env.toolchain.ToolchainUtils.spotterStaRes"/>
        <anchors xsi:type="pi:ChopboxAnchor" incomingConnections="/0/@connections.9"/>
        <children visible="true">
          <properties key="node_name" value="node_name"/>
          <graphicsAlgorithm xsi:type="al:Text" foreground="/0/@colors.2" lineWidth="1" filled="false" transparency="0.0" width="246" height="20" x="5" y="5" value="Results"/>
        </children>
      </children>
      <children xsi:type="pi:ContainerShape" visible="true" active="true">
        <properties key="node" value="node"/>
        <properties key="section" value="section"/>
        <graphicsAlgorithm xsi:type="al:RoundedRectangle" background="/0/@colors.3" foreground="/0/@colors.2" lineWidth="1" transparency="0.0" width="241" height="51" x="330" y="40" cornerHeight="15" cornerWidth="15"/>
        <link businessObjects="eu.cloudscaleproject.env.toolchain.ToolchainUtils.spotterDynInput"/>
        <anchors xsi:type="pi:ChopboxAnchor" outgoingConnections="/0/@connections.10"/>
        <children visible="true">
          <properties key="node_name" value="node_name"/>
          <graphicsAlgorithm xsi:type="al:Text" foreground="/0/@colors.2" lineWidth="1" filled="false" transparency="0.0" width="236" height="20" x="5" y="5" value="Dynamic spotter input"/>
        </children>
        <children xsi:type="pi:ContainerShape" visible="true" active="true">
          <properties key="node" value="node"/>
          <properties key="command" value="command"/>
          <graphicsAlgorithm xsi:type="al:RoundedRectangle" background="/0/@colors.3" foreground="/0/@colors.2" lineWidth="1" transparency="0.0" width="21" height="20" x="5" y="25" cornerHeight="5" cornerWidth="5"/>
          <link businessObjects="_tgQWoF3mEeScwqkqKbMIeg"/>
          <children visible="true">
            <properties key="node_name" value="node_name"/>
            <graphicsAlgorithm xsi:type="al:Text" foreground="/0/@colors.4" lineWidth="1" filled="false" transparency="0.0" width="21" height="20" horizontalAlignment="ALIGNMENT_CENTER" value="v"/>
          </children>
        </children>
      </children>
      <children xsi:type="pi:ContainerShape" visible="true" active="true">
        <properties key="node" value="node"/>
        <properties key="section" value="section"/>
        <graphicsAlgorithm xsi:type="al:RoundedRectangle" background="/0/@colors.3" foreground="/0/@colors.2" lineWidth="1" transparency="0.0" width="241" height="51" x="330" y="110" cornerHeight="15" cornerWidth="15"/>
        <link businessObjects="eu.cloudscaleproject.env.toolchain.ToolchainUtils.spotterDynConf"/>
        <anchors xsi:type="pi:ChopboxAnchor" outgoingConnections="/0/@connections.11" incomingConnections="/0/@connections.10"/>
        <children visible="true">
          <properties key="node_name" value="node_name"/>
          <graphicsAlgorithm xsi:type="al:Text" foreground="/0/@colors.2" lineWidth="1" filled="false" transparency="0.0" width="236" height="20" x="5" y="5" value="Dynamic spotter"/>
        </children>
        <children xsi:type="pi:ContainerShape" visible="true" active="true">
          <properties key="node" value="node"/>
          <properties key="command" value="command"/>
          <graphicsAlgorithm xsi:type="al:RoundedRectangle" background="/0/@colors.3" foreground="/0/@colors.2" lineWidth="1" transparency="0.0" width="21" height="20" x="5" y="25" cornerHeight="5" cornerWidth="5"/>
          <link businessObjects="_yxql4F3mEeScwqkqKbMIeg"/>
          <children visible="true">
            <properties key="node_name" value="node_name"/>
            <graphicsAlgorithm xsi:type="al:Text" foreground="/0/@colors.4" lineWidth="1" filled="false" transparency="0.0" width="21" height="20" horizontalAlignment="ALIGNMENT_CENTER" value="v"/>
          </children>
        </children>
      </children>
      <children xsi:type="pi:ContainerShape" visible="true" active="true">
        <properties key="node" value="node"/>
        <properties key="section" value="section"/>
        <graphicsAlgorithm xsi:type="al:RoundedRectangle" background="/0/@colors.3" foreground="/0/@colors.2" lineWidth="1" transparency="0.0" width="241" height="51" x="330" y="180" cornerHeight="15" cornerWidth="15"/>
        <link businessObjects="eu.cloudscaleproject.env.toolchain.ToolchainUtils.spotterDynRes"/>
        <anchors xsi:type="pi:ChopboxAnchor" incomingConnections="/0/@connections.11"/>
        <children visible="true">
          <properties key="node_name" value="node_name"/>
          <graphicsAlgorithm xsi:type="al:Text" foreground="/0/@colors.2" lineWidth="1" filled="false" transparency="0.0" width="236" height="20" x="5" y="5" value="Results"/>
        </children>
      </children>
      <children xsi:type="pi:ContainerShape" visible="true" active="true">
        <properties key="node" value="node"/>
        <properties key="command" value="command"/>
        <graphicsAlgorithm xsi:type="al:RoundedRectangle" background="/0/@colors.3" foreground="/0/@colors.2" lineWidth="1" transparency="0.0" width="21" height="20" x="575" y="5" cornerHeight="5" cornerWidth="5"/>
        <link businessObjects="_j1WngPo6EeSga-ySs5-xDw"/>
        <children visible="true">
          <properties key="node_name" value="node_name"/>
          <graphicsAlgorithm xsi:type="al:Text" foreground="/0/@colors.4" lineWidth="1" filled="false" transparency="0.0" width="21" height="20" horizontalAlignment="ALIGNMENT_CENTER" value="?"/>
        </children>
      </children>
      <children xsi:type="pi:ContainerShape" visible="true" active="true">
        <properties key="node" value="node"/>
        <properties key="command" value="command"/>
        <graphicsAlgorithm xsi:type="al:RoundedRectangle" background="/0/@colors.3" foreground="/0/@colors.2" lineWidth="1" transparency="0.0" width="21" height="20" x="549" y="5" cornerHeight="5" cornerWidth="5"/>
        <link businessObjects="_GgslMPo7EeSga-ySs5-xDw"/>
        <children visible="true">
          <properties key="node_name" value="node_name"/>
          <graphicsAlgorithm xsi:type="al:Text" foreground="/0/@colors.4" lineWidth="1" filled="false" transparency="0.0" width="21" height="20" horizontalAlignment="ALIGNMENT_CENTER" value="?"/>
        </children>
      </children>
    </children>
    <children xsi:type="pi:ContainerShape" visible="true" active="true">
      <properties key="node" value="node"/>
      <properties key="action" value="action"/>
      <graphicsAlgorithm xsi:type="al:Polygon" background="/0/@colors.3" foreground="/0/@colors.2" lineWidth="1" filled="true" transparency="0.0" width="110" height="46" x="669" y="110">
        <points/>
        <points x="83"/>
        <points x="110" y="23"/>
        <points x="83" y="46"/>
        <points y="46"/>
      </graphicsAlgorithm>
      <link businessObjects="action_transform_overview_analyser"/>
      <anchors xsi:type="pi:ChopboxAnchor" outgoingConnections="/0/@connections.3" incomingConnections="/0/@connections.2"/>
      <children visible="true">
        <properties key="node_name" value="node_name"/>
        <graphicsAlgorithm xsi:type="al:Text" foreground="/0/@colors.2" lineWidth="1" filled="false" transparency="0.0" width="105" height="46" x="5" value="Transform"/>
      </children>
    </children>
    <children xsi:type="pi:ContainerShape" visible="true" active="true">
      <properties key="node" value="node"/>
      <properties key="action" value="action"/>
      <graphicsAlgorithm xsi:type="al:Polygon" background="/0/@colors.3" foreground="/0/@colors.2" lineWidth="1" filled="true" transparency="0.0" width="109" height="43" x="670" y="40">
        <points/>
        <points x="82"/>
        <points x="109" y="21"/>
        <points x="82" y="43"/>
        <points y="43"/>
      </graphicsAlgorithm>
      <link businessObjects="action_import_ext_analyser"/>
      <anchors xsi:type="pi:ChopboxAnchor" outgoingConnections="/0/@connections.0" incomingConnections="/0/@connections.14"/>
      <children visible="true">
        <properties key="node_name" value="node_name"/>
        <graphicsAlgorithm xsi:type="al:Text" foreground="/0/@colors.2" lineWidth="1" filled="false" transparency="0.0" width="104" height="43" x="5" value="Import"/>
      </children>
    </children>
    <children xsi:type="pi:ContainerShape" visible="true" active="true">
      <properties key="node" value="node"/>
      <properties key="action" value="action"/>
      <graphicsAlgorithm xsi:type="al:Polygon" background="/0/@colors.3" foreground="/0/@colors.2" lineWidth="1" filled="true" transparency="0.0" width="91" height="39" x="390" y="83">
        <points/>
        <points x="69"/>
        <points x="91" y="19"/>
        <points x="69" y="39"/>
        <points y="39"/>
      </graphicsAlgorithm>
      <link businessObjects="action_import_ext_overview"/>
      <anchors xsi:type="pi:ChopboxAnchor" outgoingConnections="/0/@connections.1" incomingConnections="/0/@connections.15"/>
      <children visible="true">
        <properties key="node_name" value="node_name"/>
        <graphicsAlgorithm xsi:type="al:Text" foreground="/0/@colors.2" lineWidth="1" filled="false" transparency="0.0" width="86" height="39" x="5" value="Transform"/>
      </children>
    </children>
    <children xsi:type="pi:ContainerShape" visible="true" active="true">
      <properties key="node" value="node"/>
      <properties key="action" value="action"/>
      <graphicsAlgorithm xsi:type="al:Polygon" background="/0/@colors.3" foreground="/0/@colors.2" lineWidth="1" filled="true" transparency="0.0" width="81" height="35" x="240" y="288">
        <points/>
        <points x="61"/>
        <points x="81" y="17"/>
        <points x="61" y="35"/>
        <points y="35"/>
      </graphicsAlgorithm>
      <link businessObjects="action_import_ext_ss"/>
      <anchors xsi:type="pi:ChopboxAnchor" outgoingConnections="/0/@connections.12" incomingConnections="/0/@connections.13"/>
      <children visible="true">
        <properties key="node_name" value="node_name"/>
        <graphicsAlgorithm xsi:type="al:Text" foreground="/0/@colors.2" lineWidth="1" filled="false" transparency="0.0" width="76" height="35" x="5" value="Import"/>
      </children>
    </children>
    <children xsi:type="pi:ContainerShape" visible="true" active="true">
      <properties key="node" value="node"/>
      <properties key="action" value="action"/>
      <graphicsAlgorithm xsi:type="al:Polygon" background="/0/@colors.3" foreground="/0/@colors.2" lineWidth="1" filled="true" transparency="0.0" width="110" height="46" x="669" y="180">
        <points/>
        <points x="83"/>
        <points x="110" y="23"/>
        <points x="83" y="46"/>
        <points y="46"/>
      </graphicsAlgorithm>
      <link businessObjects="_kQmOgPo5EeSga-ySs5-xDw"/>
      <anchors xsi:type="pi:ChopboxAnchor" outgoingConnections="/0/@connections.16 /0/@connections.17"/>
      <children visible="true">
        <properties key="node_name" value="node_name"/>
        <graphicsAlgorithm xsi:type="al:Text" foreground="/0/@colors.2" lineWidth="1" filled="false" transparency="0.0" width="105" height="46" x="5" value="Import external"/>
      </children>
    </children>
    <connections xsi:type="pi:FreeFormConnection" visible="true" active="true" start="/0/@children.5/@anchors.0" end="/0/@children.2/@children.1/@anchors.0">
      <graphicsAlgorithm xsi:type="al:Polyline" lineWidth="2" lineStyle="DASH" filled="false" transparency="0.0"/>
      <link businessObjects="/1/@links.0"/>
      <connectionDecorators visible="true" locationRelative="true" location="1.0">
        <graphicsAlgorithm xsi:type="al:Polyline" lineWidth="2">
          <points x="-10" y="8"/>
          <points/>
          <points y="-2"/>
          <points x="-10" y="-8"/>
        </graphicsAlgorithm>
      </connectionDecorators>
      <bendpoints x="976" y="62"/>
    </connections>
    <connections xsi:type="pi:FreeFormConnection" visible="true" active="true" start="/0/@children.6/@anchors.0" end="/0/@children.1/@children.1/@anchors.0">
      <graphicsAlgorithm xsi:type="al:Polyline" lineWidth="2" lineStyle="DASH" filled="false" transparency="0.0"/>
      <link businessObjects="/1/@links.1"/>
      <connectionDecorators visible="true" locationRelative="true" location="1.0">
        <graphicsAlgorithm xsi:type="al:Polyline" lineWidth="2">
          <points x="-10" y="8"/>
          <points/>
          <points y="-2"/>
          <points x="-10" y="-8"/>
        </graphicsAlgorithm>
      </connectionDecorators>
      <bendpoints x="523" y="102"/>
    </connections>
    <connections xsi:type="pi:FreeFormConnection" visible="true" active="true" start="/0/@children.1/@children.1/@anchors.0" end="/0/@children.4/@anchors.0">
      <graphicsAlgorithm xsi:type="al:Polyline" lineWidth="2" filled="false" transparency="0.0"/>
      <link businessObjects="/1/@links.2"/>
      <connectionDecorators visible="true" locationRelative="true" location="1.0">
        <graphicsAlgorithm xsi:type="al:Polyline" lineWidth="2">
          <points x="-10" y="8"/>
          <points/>
          <points y="-2"/>
          <points x="-10" y="-8"/>
        </graphicsAlgorithm>
      </connectionDecorators>
      <bendpoints x="593" y="135"/>
    </connections>
    <connections xsi:type="pi:FreeFormConnection" visible="true" active="true" start="/0/@children.4/@anchors.0" end="/0/@children.2/@children.1/@anchors.0">
      <graphicsAlgorithm xsi:type="al:Polyline" lineWidth="2" lineStyle="DASH" filled="false" transparency="0.0"/>
      <link businessObjects="/1/@links.3"/>
      <connectionDecorators visible="true" locationRelative="true" location="1.0">
        <graphicsAlgorithm xsi:type="al:Polyline" lineWidth="2">
          <points x="-10" y="8"/>
          <points/>
          <points y="-2"/>
          <points x="-10" y="-8"/>
        </graphicsAlgorithm>
      </connectionDecorators>
      <bendpoints x="817" y="133"/>
    </connections>
    <connections xsi:type="pi:FreeFormConnection" visible="true" active="true" start="/0/@children.2/@children.1/@anchors.0" end="/0/@children.2/@children.2/@anchors.0">
      <graphicsAlgorithm xsi:type="al:Polyline" lineWidth="2" filled="false" transparency="0.0"/>
      <link businessObjects="/1/@links.4"/>
      <connectionDecorators visible="true" locationRelative="true" location="1.0">
        <graphicsAlgorithm xsi:type="al:Polyline" lineWidth="2">
          <points x="-10" y="8"/>
          <points/>
          <points y="-2"/>
          <points x="-10" y="-8"/>
        </graphicsAlgorithm>
      </connectionDecorators>
    </connections>
    <connections xsi:type="pi:FreeFormConnection" visible="true" active="true" start="/0/@children.2/@children.2/@anchors.0" end="/0/@children.2/@children.3/@anchors.0">
      <graphicsAlgorithm xsi:type="al:Polyline" lineWidth="2" filled="false" transparency="0.0"/>
      <link businessObjects="/1/@links.5"/>
      <connectionDecorators visible="true" locationRelative="true" location="1.0">
        <graphicsAlgorithm xsi:type="al:Polyline" lineWidth="2">
          <points x="-10" y="8"/>
          <points/>
          <points y="-2"/>
          <points x="-10" y="-8"/>
        </graphicsAlgorithm>
      </connectionDecorators>
    </connections>
    <connections xsi:type="pi:FreeFormConnection" visible="true" active="true" start="/0/@children.0/@children.2/@anchors.0" end="/0/@children.0/@children.3/@anchors.0">
      <graphicsAlgorithm xsi:type="al:Polyline" lineWidth="2" filled="false" transparency="0.0"/>
      <link businessObjects="/1/@links.6"/>
      <connectionDecorators visible="true" locationRelative="true" location="1.0">
        <graphicsAlgorithm xsi:type="al:Polyline" lineWidth="2">
          <points x="-10" y="8"/>
          <points/>
          <points y="-2"/>
          <points x="-10" y="-8"/>
        </graphicsAlgorithm>
      </connectionDecorators>
    </connections>
    <connections xsi:type="pi:FreeFormConnection" visible="true" active="true" start="/0/@children.0/@children.3/@anchors.0" end="/0/@children.0/@children.1/@anchors.0">
      <graphicsAlgorithm xsi:type="al:Polyline" lineWidth="2" filled="false" transparency="0.0"/>
      <link businessObjects="/1/@links.7"/>
      <connectionDecorators visible="true" locationRelative="true" location="1.0">
        <graphicsAlgorithm xsi:type="al:Polyline" lineWidth="2">
          <points x="-10" y="8"/>
          <points/>
          <points y="-2"/>
          <points x="-10" y="-8"/>
        </graphicsAlgorithm>
      </connectionDecorators>
    </connections>
    <connections xsi:type="pi:FreeFormConnection" visible="true" active="true" start="/0/@children.3/@children.1/@anchors.0" end="/0/@children.3/@children.2/@anchors.0">
      <graphicsAlgorithm xsi:type="al:Polyline" lineWidth="2" filled="false" transparency="0.0"/>
      <link businessObjects="/1/@links.8"/>
      <connectionDecorators visible="true" locationRelative="true" location="1.0">
        <graphicsAlgorithm xsi:type="al:Polyline" lineWidth="2">
          <points x="-10" y="8"/>
          <points/>
          <points y="-2"/>
          <points x="-10" y="-8"/>
        </graphicsAlgorithm>
      </connectionDecorators>
    </connections>
    <connections xsi:type="pi:FreeFormConnection" visible="true" active="true" start="/0/@children.3/@children.2/@anchors.0" end="/0/@children.3/@children.3/@anchors.0">
      <graphicsAlgorithm xsi:type="al:Polyline" lineWidth="2" filled="false" transparency="0.0"/>
      <link businessObjects="/1/@links.9"/>
      <connectionDecorators visible="true" locationRelative="true" location="1.0">
        <graphicsAlgorithm xsi:type="al:Polyline" lineWidth="2">
          <points x="-10" y="8"/>
          <points/>
          <points y="-2"/>
          <points x="-10" y="-8"/>
        </graphicsAlgorithm>
      </connectionDecorators>
    </connections>
    <connections xsi:type="pi:FreeFormConnection" visible="true" active="true" start="/0/@children.3/@children.4/@anchors.0" end="/0/@children.3/@children.5/@anchors.0">
      <graphicsAlgorithm xsi:type="al:Polyline" lineWidth="2" filled="false" transparency="0.0"/>
      <link businessObjects="/1/@links.10"/>
      <connectionDecorators visible="true" locationRelative="true" location="1.0">
        <graphicsAlgorithm xsi:type="al:Polyline" lineWidth="2">
          <points x="-10" y="8"/>
          <points/>
          <points y="-2"/>
          <points x="-10" y="-8"/>
        </graphicsAlgorithm>
      </connectionDecorators>
    </connections>
    <connections xsi:type="pi:FreeFormConnection" visible="true" active="true" start="/0/@children.3/@children.5/@anchors.0" end="/0/@children.3/@children.6/@anchors.0">
      <graphicsAlgorithm xsi:type="al:Polyline" lineWidth="2" filled="false" transparency="0.0"/>
      <link businessObjects="/1/@links.11"/>
      <connectionDecorators visible="true" locationRelative="true" location="1.0">
        <graphicsAlgorithm xsi:type="al:Polyline" lineWidth="2">
          <points x="-10" y="8"/>
          <points/>
          <points y="-2"/>
          <points x="-10" y="-8"/>
        </graphicsAlgorithm>
      </connectionDecorators>
    </connections>
    <connections xsi:type="pi:FreeFormConnection" visible="true" active="true" start="/0/@children.7/@anchors.0" end="/0/@children.3/@children.1/@anchors.0">
      <graphicsAlgorithm xsi:type="al:Polyline" lineWidth="2" lineStyle="DASH" filled="false" transparency="0.0"/>
      <link businessObjects="/1/@links.12"/>
      <connectionDecorators visible="true" locationRelative="true" location="1.0">
        <graphicsAlgorithm xsi:type="al:Polyline" lineWidth="2">
          <points x="-10" y="8"/>
          <points/>
          <points y="-2"/>
          <points x="-10" y="-8"/>
        </graphicsAlgorithm>
      </connectionDecorators>
      <bendpoints x="358" y="308"/>
      <bendpoints x="358" y="364"/>
      <bendpoints x="205" y="364"/>
    </connections>
    <connections xsi:type="pi:FreeFormConnection" visible="true" active="true" start="/0/@children.0/@children.1/@anchors.0" end="/0/@children.7/@anchors.0">
      <graphicsAlgorithm xsi:type="al:Polyline" lineWidth="2" filled="false" transparency="0.0"/>
      <link businessObjects="/1/@links.13"/>
      <connectionDecorators visible="true" locationRelative="true" location="1.0">
        <graphicsAlgorithm xsi:type="al:Polyline" lineWidth="2">
          <points x="-10" y="8"/>
          <points/>
          <points y="-2"/>
          <points x="-10" y="-8"/>
        </graphicsAlgorithm>
      </connectionDecorators>
      <bendpoints x="278" y="274"/>
      <bendpoints x="194" y="274"/>
      <bendpoints x="194" y="308"/>
    </connections>
    <connections xsi:type="pi:FreeFormConnection" visible="true" active="true" start="/0/@children.0/@children.1/@anchors.0" end="/0/@children.5/@anchors.0">
      <graphicsAlgorithm xsi:type="al:Polyline" lineWidth="2" lineStyle="DASH" filled="false" transparency="0.0"/>
      <link businessObjects="/1/@links.14"/>
      <connectionDecorators visible="true" locationRelative="true" location="1.0">
        <graphicsAlgorithm xsi:type="al:Polyline" lineWidth="2">
          <points x="-10" y="8"/>
          <points/>
          <points y="-2"/>
          <points x="-10" y="-8"/>
        </graphicsAlgorithm>
      </connectionDecorators>
      <bendpoints x="290" y="61"/>
    </connections>
    <connections xsi:type="pi:FreeFormConnection" visible="true" active="true" start="/0/@children.0/@children.1/@anchors.0" end="/0/@children.6/@anchors.0">
      <graphicsAlgorithm xsi:type="al:Polyline" lineWidth="2" lineStyle="DASH" filled="false" transparency="0.0"/>
      <link businessObjects="/1/@links.15"/>
      <connectionDecorators visible="true" locationRelative="true" location="1.0">
        <graphicsAlgorithm xsi:type="al:Polyline" lineWidth="2">
          <points x="-10" y="8"/>
          <points/>
          <points y="-2"/>
          <points x="-10" y="-8"/>
        </graphicsAlgorithm>
      </connectionDecorators>
      <bendpoints x="319" y="102"/>
    </connections>
    <connections xsi:type="pi:FreeFormConnection" visible="true" active="true" start="/0/@children.8/@anchors.0" end="/0/@children.2/@children.1/@anchors.0">
      <graphicsAlgorithm xsi:type="al:Polyline" lineWidth="2" lineStyle="DASH" filled="false" transparency="0.0"/>
      <link businessObjects="/1/@links.16"/>
      <connectionDecorators visible="true" locationRelative="true" location="1.0">
        <graphicsAlgorithm xsi:type="al:Polyline" lineWidth="2">
          <points x="-10" y="8"/>
          <points/>
          <points y="-2"/>
          <points x="-10" y="-8"/>
        </graphicsAlgorithm>
      </connectionDecorators>
    </connections>
    <connections xsi:type="pi:FreeFormConnection" visible="true" active="true" start="/0/@children.8/@anchors.0" end="/0/@children.2/@children.2/@anchors.0">
      <graphicsAlgorithm xsi:type="al:Polyline" lineWidth="2" lineStyle="DASH" filled="false" transparency="0.0"/>
      <link businessObjects="/1/@links.17"/>
      <connectionDecorators visible="true" locationRelative="true" location="1.0">
        <graphicsAlgorithm xsi:type="al:Polyline" lineWidth="2">
          <points x="-10" y="8"/>
          <points/>
          <points y="-2"/>
          <points x="-10" y="-8"/>
        </graphicsAlgorithm>
      </connectionDecorators>
      <bendpoints x="799" y="203"/>
      <bendpoints x="799" y="404"/>
    </connections>
    <colors red="227" green="238" blue="249"/>
    <colors red="255" green="255" blue="255"/>
    <colors/>
    <colors red="161" green="201" blue="213"/>
    <colors blue="255"/>
  </pi:Diagram>
  <method:Method>
    <links start="action_import_ext_analyser" end="eu.cloudscaleproject.env.toolchain.ToolchainUtils.analyserInput"/>
    <links start="action_import_ext_overview" end="eu.cloudscaleproject.env.toolchain.ToolchainUtils.overview"/>
    <links start="eu.cloudscaleproject.env.toolchain.ToolchainUtils.overview" end="action_transform_overview_analyser" required="true"/>
    <links start="action_transform_overview_analyser" end="eu.cloudscaleproject.env.toolchain.ToolchainUtils.analyserInput"/>
    <links start="eu.cloudscaleproject.env.toolchain.ToolchainUtils.analyserInput" end="eu.cloudscaleproject.env.toolchain.ToolchainUtils.analyserConf" required="true"/>
    <links start="eu.cloudscaleproject.env.toolchain.ToolchainUtils.analyserConf" end="eu.cloudscaleproject.env.toolchain.ToolchainUtils.analyserRes" required="true"/>
    <links start="eu.cloudscaleproject.env.toolchain.ToolchainUtils.extractorInput" end="eu.cloudscaleproject.env.toolchain.ToolchainUtils.extractorConf" required="true"/>
    <links start="eu.cloudscaleproject.env.toolchain.ToolchainUtils.extractorConf" end="eu.cloudscaleproject.env.toolchain.ToolchainUtils.extractorRes" required="true"/>
    <links start="eu.cloudscaleproject.env.toolchain.ToolchainUtils.spotterStaInput" end="eu.cloudscaleproject.env.toolchain.ToolchainUtils.spotterStaConf" required="true"/>
    <links start="eu.cloudscaleproject.env.toolchain.ToolchainUtils.spotterStaConf" end="eu.cloudscaleproject.env.toolchain.ToolchainUtils.spotterStaRes" required="true"/>
    <links start="eu.cloudscaleproject.env.toolchain.ToolchainUtils.spotterDynInput" end="eu.cloudscaleproject.env.toolchain.ToolchainUtils.spotterDynConf" required="true"/>
    <links start="eu.cloudscaleproject.env.toolchain.ToolchainUtils.spotterDynConf" end="eu.cloudscaleproject.env.toolchain.ToolchainUtils.spotterDynRes" required="true"/>
    <links start="action_import_ext_ss" end="eu.cloudscaleproject.env.toolchain.ToolchainUtils.spotterStaInput"/>
    <links start="eu.cloudscaleproject.env.toolchain.ToolchainUtils.extractorRes" end="action_import_ext_ss" required="true"/>
    <links start="eu.cloudscaleproject.env.toolchain.ToolchainUtils.extractorRes" end="action_import_ext_analyser"/>
    <links start="eu.cloudscaleproject.env.toolchain.ToolchainUtils.extractorRes" end="action_import_ext_overview"/>
    <links start="_kQmOgPo5EeSga-ySs5-xDw" end="eu.cloudscaleproject.env.toolchain.ToolchainUtils.analyserInput"/>
    <links start="_kQmOgPo5EeSga-ySs5-xDw" end="eu.cloudscaleproject.env.toolchain.ToolchainUtils.analyserConf"/>
    <nodes xsi:type="method:Container" id="_SqDU0C61EeSADewJHO2y3w" name="Extractor" tooltip="Extractor tool status container">
      <children xsi:type="method:Section" id="eu.cloudscaleproject.env.toolchain.ToolchainUtils.extractorRes" name="Results" colorForeground="000000" colorBackground="a1c9d5" commandId="eu.cloudscaleproject.env.toolchain.openAlternative" next="/1/@links.13 /1/@links.14 /1/@links.15" previous="/1/@links.7">
        <requirements id="eu.cloudscaleproject.env.toolchain.ToolchainUtils.extractorRes.eu.cloudscaleproject.env.toolchain.ToolchainUtils.path_repository" name="Repository" colorText="0000ff" colorForeground="000000" colorBackground="a1c9d5"/>
        <requirements id="eu.cloudscaleproject.env.toolchain.ToolchainUtils.extractorRes.eu.cloudscaleproject.env.toolchain.ToolchainUtils.path_system" name="System" colorText="0000ff" colorForeground="000000" colorBackground="a1c9d5" position="10"/>
      </children>
      <children xsi:type="method:Section" id="eu.cloudscaleproject.env.toolchain.ToolchainUtils.extractorInput" name="Input" colorForeground="000000" colorBackground="a1c9d5" commandId="eu.cloudscaleproject.env.toolchain.openAlternative" next="/1/@links.6">
        <commands id="_1QP7cDRVEeSMLJ8vnBP-0A" name="v" colorText="0000ff" colorForeground="000000" colorBackground="a1c9d5" commandId="eu.cloudscaleproject.env.toolchain.selectAlternativeMethod"/>
      </children>
      <children xsi:type="method:Section" id="eu.cloudscaleproject.env.toolchain.ToolchainUtils.extractorConf" name="Extractor" colorForeground="000000" colorBackground="a1c9d5" commandId="eu.cloudscaleproject.env.toolchain.openAlternative" next="/1/@links.7" previous="/1/@links.6">
        <commands id="_6bS5cDRUEeSMLJ8vnBP-0A" name="v" colorText="0000ff" colorForeground="000000" colorBackground="a1c9d5" commandId="eu.cloudscaleproject.env.toolchain.selectAlternativeMethod" position="10"/>
      </children>
      <commands id="_3sl3EDc_EeSoZdJQhqDUDw" name="?" tooltip="Show Extractor introduction page" colorText="0000ff" colorForeground="000000" colorBackground="a1c9d5" commandId="eu.cloudscaleproject.env.extractor.command.openeditor" position="10">
        <commandParam>openIntro</commandParam>
      </commands>
    </nodes>
    <nodes xsi:type="method:Container" id="_YVJPYC61EeSADewJHO2y3w" name="ScaleDL">
      <children xsi:type="method:Section" id="eu.cloudscaleproject.env.toolchain.ToolchainUtils.overview" name="Overview model" description="Overview model digram is used to model cloud environment architecture and its deployment." tooltip="Overview model of a cloud environemnt." colorForeground="000000" colorBackground="a1c9d5" commandId="eu.cloudscaleproject.env.toolchain.openAlternative" next="/1/@links.2" previous="/1/@links.1">
        <commands id="_KUsqwBMzEeWrnYazqRxW7w" name="v" colorText="0000ff" colorForeground="000000" colorBackground="a1c9d5" commandId="eu.cloudscaleproject.env.toolchain.selectAlternativeMethod"/>
      </children>
      <children xsi:type="method:Section" id="architectureTemplates" name="Architecture Templ." colorForeground="000000" colorBackground="a1c9d5"/>
      <children xsi:type="method:Section" id="eu.cloudscaleproject.env.toolchain.ToolchainUtils.usageevolution" name="Usage evolution" description="Usage evolution model" colorForeground="000000" colorBackground="a1c9d5" commandId="eu.cloudscaleproject.env.toolchain.openAlternative">
        <commands id="_iueiAHPSEeS3b6FdlQPPqA" name="v" description="Usage evolution alternative selection button " tooltip="Select Usage evolution alternative" colorText="0000ff" colorForeground="000000" colorBackground="a1c9d5" commandId="eu.cloudscaleproject.env.toolchain.selectAlternativeMethod"/>
      </children>
      <commands id="_OjbRUDabEeWxRdp7yxfGjg" colorText="0000ff" colorForeground="000000" colorBackground="a1c9d5"/>
    </nodes>
    <nodes xsi:type="method:Container" id="_Yqbi4C61EeSADewJHO2y3w" name="Analyser" tooltip="Analyser tool status container">
      <children xsi:type="method:Section" id="eu.cloudscaleproject.env.toolchain.ToolchainUtils.analyserInput" name="Analyser input" description="Input models, used by the Analyser tool." tooltip="Analyser input status section" colorForeground="000000" colorBackground="a1c9d5" commandId="eu.cloudscaleproject.env.toolchain.openAlternative" next="/1/@links.4" previous="/1/@links.0 /1/@links.3 /1/@links.16">
        <commands id="_7zWcEDRVEeSMLJ8vnBP-0A" name="v" tooltip="Select input alternative" colorText="0000ff" colorForeground="000000" colorBackground="a1c9d5" commandId="eu.cloudscaleproject.env.analyser.command.openinputdialog"/>
        <requirements id="eu.cloudscaleproject.env.toolchain.ToolchainUtils.analyserInput.eu.cloudscaleproject.env.toolchain.ToolchainUtils.path_repository" name="Repository" description="Repository model diagram is used to model cloud services and their components." tooltip="Represents the repository model status" colorText="0000ff" colorForeground="000000" colorBackground="a1c9d5" position="10"/>
        <requirements id="eu.cloudscaleproject.env.toolchain.ToolchainUtils.analyserInput.eu.cloudscaleproject.env.toolchain.ToolchainUtils.path_system" name="System" description="System model is used to design cloud environment architecture." tooltip="Represents the system model status" colorText="0000ff" colorForeground="000000" colorBackground="a1c9d5" position="20"/>
        <requirements id="eu.cloudscaleproject.env.toolchain.ToolchainUtils.analyserInput.eu.cloudscaleproject.env.toolchain.ToolchainUtils.path_allocation" name="Allocation" description="Allocation model provides binding between repository and resource environment model. " tooltip="Represents the allocation model status" colorText="0000ff" colorForeground="000000" colorBackground="a1c9d5" position="30"/>
        <requirements id="eu.cloudscaleproject.env.toolchain.ToolchainUtils.analyserInput.eu.cloudscaleproject.env.toolchain.ToolchainUtils.path_resourceenvironment" name="Resource environment" description="Describes hardware resources in cloud environemnt." tooltip="Represents the resource environment model status" colorText="0000ff" colorForeground="000000" colorBackground="a1c9d5" position="40"/>
        <requirements id="eu.cloudscaleproject.env.toolchain.ToolchainUtils.analyserInput.eu.cloudscaleproject.env.toolchain.ToolchainUtils.path_usage" name="Usage" colorText="0000ff" colorForeground="000000" colorBackground="a1c9d5" position="100"/>
        <requirements id="eu.cloudscaleproject.env.toolchain.ToolchainUtils.analyserInput.eu.cloudscaleproject.env.toolchain.ToolchainUtils.path_usageevolution" name="Usage Evolution" colorText="0000ff" colorForeground="000000" colorBackground="a1c9d5" position="200"/>
      </children>
      <children xsi:type="method:Section" id="eu.cloudscaleproject.env.toolchain.ToolchainUtils.analyserConf" name="Analyser" tooltip="Analyser configuration status section" colorForeground="000000" colorBackground="a1c9d5" commandId="eu.cloudscaleproject.env.toolchain.openAlternative" next="/1/@links.5" previous="/1/@links.4 /1/@links.17">
        <commands id="_7Eo-8DHDEeSAkNNGxeU_SA" name="v" tooltip="Select configuration alternative" colorText="0000ff" colorForeground="000000" colorBackground="a1c9d5" commandId="eu.cloudscaleproject.env.analyser.command.openconfdialog" position="10"/>
        <requirements id="eu.cloudscaleproject.env.toolchain.ToolchainUtils.analyserConf.eu.cloudscaleproject.env.toolchain.ToolchainUtils.path_pcmmeasuringpoint" name="Measuring points" tooltip="Represents the measuring points model status" colorText="0000ff" colorForeground="000000" colorBackground="a1c9d5" position="10"/>
        <requirements id="eu.cloudscaleproject.env.toolchain.ToolchainUtils.analyserConf.eu.cloudscaleproject.env.toolchain.ToolchainUtils.path_pms" name="Monitors" tooltip="Represents the monitors model status" colorText="0000ff" colorForeground="000000" colorBackground="a1c9d5" position="20"/>
        <requirements id="eu.cloudscaleproject.env.toolchain.ToolchainUtils.analyserConf.eu.cloudscaleproject.env.toolchain.ToolchainUtils.path_slo" name="Service Level Objectives" tooltip="Represents the SLO model status" colorText="0000ff" colorForeground="000000" colorBackground="a1c9d5" position="30"/>
        <requirements id="eu.cloudscaleproject.env.toolchain.ToolchainUtils.analyserConf.eu.cloudscaleproject.env.toolchain.ToolchainUtils.path_experiments" name="Experiment" description="Contains experiment configurations." tooltip="Represents the experiment model status" colorText="0000ff" colorForeground="000000" colorBackground="a1c9d5" commandId="" position="1"/>
      </children>
      <children xsi:type="method:Section" id="eu.cloudscaleproject.env.toolchain.ToolchainUtils.analyserRes" name="Results" tooltip="Analyser results status section" colorForeground="000000" colorBackground="a1c9d5" commandId="eu.cloudscaleproject.env.toolchain.openAlternative" previous="/1/@links.5"/>
      <commands id="_fcGHoDc_EeSoZdJQhqDUDw" name="p" tooltip="Show Analyser perspective" colorText="0000ff" colorForeground="000000" colorBackground="a1c9d5" commandId="eu.cludscale.environment.analyser.openPerspective" position="10">
        <commandParam>openIntro</commandParam>
      </commands>
      <commands id="_z_PHEPo4EeSga-ySs5-xDw" name="?" tooltip="Show Analyser introduction page" colorText="0000ff" colorForeground="000000" colorBackground="a1c9d5" commandId="eu.cloudscaleproject.env.analyser.command.openeditor" position="5">
        <commandParam>openIntro</commandParam>
      </commands>
    </nodes>
    <nodes xsi:type="method:Container" id="_Zcc1sC61EeSADewJHO2y3w" name="Spotter" tooltip="Static and Dynamic Spotter tool status container">
      <children xsi:type="method:Section" id="eu.cloudscaleproject.env.toolchain.ToolchainUtils.spotterStaInput" name="Static spotter input" colorForeground="000000" colorBackground="a1c9d5" commandId="eu.cloudscaleproject.env.toolchain.openAlternative" next="/1/@links.8" previous="/1/@links.12">
        <commands id="_cwXP4Po8EeSga-ySs5-xDw" name="V" tooltip="Select input alternative" colorText="0000ff" colorForeground="000000" colorBackground="a1c9d5" commandId="eu.cloudscaleproject.env.toolchain.selectAlternativeMethod"/>
      </children>
      <children xsi:type="method:Section" id="eu.cloudscaleproject.env.toolchain.ToolchainUtils.spotterStaConf" name="Static spotter" colorForeground="000000" colorBackground="a1c9d5" commandId="eu.cloudscaleproject.env.toolchain.openAlternative" next="/1/@links.9" previous="/1/@links.8">
        <commands id="_gElZsPo8EeSga-ySs5-xDw" name="V" tooltip="Select configuration alternative" colorText="0000ff" colorForeground="000000" colorBackground="a1c9d5" commandId="eu.cloudscaleproject.env.toolchain.selectAlternativeMethod"/>
      </children>
      <children xsi:type="method:Section" id="eu.cloudscaleproject.env.toolchain.ToolchainUtils.spotterStaRes" name="Results" colorForeground="000000" colorBackground="a1c9d5" commandId="eu.cloudscaleproject.env.toolchain.openAlternative" previous="/1/@links.9"/>
      <children xsi:type="method:Section" id="eu.cloudscaleproject.env.toolchain.ToolchainUtils.spotterDynInput" name="Dynamic spotter input" colorForeground="000000" colorBackground="a1c9d5" commandId="eu.cloudscaleproject.env.toolchain.openAlternative" next="/1/@links.10">
        <commands id="_tgQWoF3mEeScwqkqKbMIeg" name="v" tooltip="Select input alternative" colorText="0000ff" colorForeground="000000" colorBackground="a1c9d5" commandId="eu.cloudscaleproject.env.toolchain.selectAlternativeMethod"/>
      </children>
      <children xsi:type="method:Section" id="eu.cloudscaleproject.env.toolchain.ToolchainUtils.spotterDynConf" name="Dynamic spotter" colorForeground="000000" colorBackground="a1c9d5" commandId="eu.cloudscaleproject.env.toolchain.openAlternative" next="/1/@links.11" previous="/1/@links.10">
        <commands id="_yxql4F3mEeScwqkqKbMIeg" name="v" tooltip="Select configuration alternative" colorText="0000ff" colorForeground="000000" colorBackground="a1c9d5" commandId="eu.cloudscaleproject.env.toolchain.selectAlternativeMethod"/>
      </children>
      <children xsi:type="method:Section" id="eu.cloudscaleproject.env.toolchain.ToolchainUtils.spotterDynRes" name="Results" colorForeground="000000" colorBackground="a1c9d5" commandId="eu.cloudscaleproject.env.toolchain.openAlternative" previous="/1/@links.11"/>
      <commands id="_j1WngPo6EeSga-ySs5-xDw" name="?" tooltip="Show Dynamic Spotter introduction page" colorText="0000ff" colorForeground="000000" colorBackground="a1c9d5" commandId="eu.cloudscaleproject.env.dynamicspotter.command.openeditor">
        <commandParam>openIntro</commandParam>
      </commands>
      <commands id="_GgslMPo7EeSga-ySs5-xDw" name="?" tooltip="Show Static Spotter introduction page" colorText="0000ff" colorForeground="000000" colorBackground="a1c9d5" commandId="eu.cloudscaleproject.env.staticspotter.command.openeditor">
        <commandParam>openIntro</commandParam>
      </commands>
    </nodes>
    <nodes xsi:type="method:Action" id="action_transform_overview_analyser" name="Transform" description="Transform Overview model into PCM model." tooltip="Overview to PCM transformation" colorForeground="000000" colorBackground="a1c9d5" commandId="org.scaledl.overview2pcm.command.transformWizard" next="/1/@links.3" previous="/1/@links.2"/>
    <nodes xsi:type="method:Action" id="action_import_ext_analyser" name="Import" description="Import" colorForeground="000000" colorBackground="a1c9d5" commandId="eu.cloudscaleproject.env.analyser.command.openextractorimportwizard" next="/1/@links.0" previous="/1/@links.14"/>
    <nodes xsi:type="method:Action" id="action_import_ext_overview" name="Transform" description="Import repository and system model, extracted from source code, into overview model." tooltip="Import extracted model into overview" colorForeground="000000" colorBackground="a1c9d5" commandId="eu.cludscale.environment.overview.openImportWizard" next="/1/@links.1" previous="/1/@links.15"/>
    <nodes xsi:type="method:Action" id="action_import_ext_ss" name="Import" description="Import input for StaticSpotter" colorForeground="000000" colorBackground="a1c9d5" commandId="eu.cloudscaleproject.env.staticspotter.command.openinputwizard" next="/1/@links.12" previous="/1/@links.13"/>
    <nodes xsi:type="method:Action" id="_kQmOgPo5EeSga-ySs5-xDw" name="Import external" colorForeground="000000" colorBackground="a1c9d5" commandId="eu.cloudscaleproject.env.analyser.command.openAnalyserImportWizard" next="/1/@links.16 /1/@links.17"/>
  </method:Method>
</xmi:XMI>

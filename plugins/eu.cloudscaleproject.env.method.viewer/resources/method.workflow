<?xml version="1.0" encoding="ASCII"?>
<xmi:XMI xmi:version="2.0" xmlns:xmi="http://www.omg.org/XMI" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:al="http://eclipse.org/graphiti/mm/algorithms" xmlns:method="http://eu.cloudscale.env.method/MethodComponentModel/1.0" xmlns:pi="http://eclipse.org/graphiti/mm/pictograms">
  <pi:Diagram visible="true" gridUnit="10" diagramTypeId="Workflow diagram" name="method" snapToGrid="true" pictogramLinks="/0/@children.0/@link /0/@children.0/@children.1/@link /0/@children.1/@link /0/@children.2/@link /0/@children.3/@link /0/@children.0/@children.2/@link /0/@children.0/@children.3/@link /0/@children.1/@children.1/@link /0/@children.1/@children.2/@link /0/@children.1/@children.3/@link /0/@children.2/@children.1/@link /0/@children.2/@children.2/@link /0/@children.2/@children.3/@link /0/@children.3/@children.1/@link /0/@children.3/@children.2/@link /0/@children.3/@children.3/@link /0/@children.3/@children.4/@link /0/@children.3/@children.5/@link /0/@children.3/@children.6/@link /0/@children.2/@children.1/@children.1/@link /0/@children.2/@children.1/@children.2/@link /0/@children.4/@link /0/@children.5/@link /0/@children.6/@link /0/@children.7/@link /0/@children.8/@link /0/@connections.0/@link /0/@connections.1/@link /0/@connections.2/@link /0/@connections.3/@link /0/@connections.4/@link /0/@connections.5/@link /0/@connections.6/@link /0/@connections.7/@link /0/@connections.8/@link /0/@connections.9/@link /0/@connections.10/@link /0/@connections.11/@link /0/@connections.12/@link /0/@connections.13/@link /0/@connections.14/@link /0/@connections.15/@link /0/@connections.16/@link /0/@connections.17/@link /0/@children.2/@children.2/@children.1/@link /0/@children.0/@children.3/@children.1/@link /0/@children.0/@children.1/@children.1/@link /0/@children.0/@children.2/@children.1/@link /0/@children.2/@children.1/@children.3/@link /0/@children.2/@children.4/@link /0/@children.0/@children.4/@link /0/@children.0/@children.3/@children.2/@link /0/@children.0/@children.3/@children.3/@link /0/@children.3/@children.4/@children.1/@link /0/@children.3/@children.5/@children.1/@link /0/@connections.18/@link /0/@children.2/@children.2/@children.2/@link /0/@children.2/@children.2/@children.3/@link /0/@children.2/@children.2/@children.4/@link /0/@children.2/@children.1/@children.4/@link /0/@children.1/@children.3/@children.1/@link /0/@children.2/@children.5/@link /0/@children.9/@link /0/@connections.19/@link /0/@children.3/@children.7/@link /0/@children.3/@children.8/@link /0/@children.3/@children.1/@children.1/@link /0/@children.3/@children.2/@children.1/@link /0/@children.3/@children.3/@children.1/@link /0/@children.2/@children.1/@children.5/@link /0/@children.2/@children.1/@children.6/@link /0/@children.0/@children.1/@children.2/@link /0/@children.0/@children.1/@children.3/@link /0/@children.1/@children.1/@children.1/@link" version="0.11.0">
    <graphicsAlgorithm xsi:type="al:Rectangle" background="/0/@colors.1" foreground="/0/@colors.0" lineWidth="1" transparency="0.0" width="1000" height="1000"/>
    <children xsi:type="pi:ContainerShape" visible="true" active="true">
      <properties key="node" value="node"/>
      <properties key="container" value="container"/>
      <graphicsAlgorithm xsi:type="al:RoundedRectangle" background="/0/@colors.1" foreground="/0/@colors.2" lineWidth="1" transparency="0.0" width="311" height="241" x="60" y="40" cornerHeight="15" cornerWidth="15"/>
      <link businessObjects="_SqDU0C61EeSADewJHO2y3w"/>
      <children visible="true">
        <properties key="node_name" value="node_name"/>
        <graphicsAlgorithm xsi:type="al:Text" foreground="/0/@colors.2" lineWidth="1" filled="false" transparency="0.0" width="306" height="20" x="5" y="5" value="Extractor"/>
      </children>
      <children xsi:type="pi:ContainerShape" visible="true" active="true">
        <properties key="node" value="node"/>
        <properties key="section" value="section"/>
        <graphicsAlgorithm xsi:type="al:RoundedRectangle" background="/0/@colors.3" foreground="/0/@colors.2" lineWidth="1" transparency="0.0" width="121" height="101" x="160" y="120" cornerHeight="15" cornerWidth="15"/>
        <link businessObjects="eu.cloudscaleproject.env.toolchain.ToolchainUtils.extractorRes"/>
        <anchors xsi:type="pi:ChopboxAnchor" outgoingConnections="/0/@connections.15 /0/@connections.16 /0/@connections.17" incomingConnections="/0/@connections.7"/>
        <children visible="true">
          <properties key="node_name" value="node_name"/>
          <graphicsAlgorithm xsi:type="al:Text" foreground="/0/@colors.2" lineWidth="1" filled="false" transparency="0.0" width="116" height="20" x="5" y="5" value="Results"/>
        </children>
        <children xsi:type="pi:ContainerShape" visible="true" active="true">
          <properties key="node" value="node"/>
          <properties key="command" value="command"/>
          <graphicsAlgorithm xsi:type="al:RoundedRectangle" background="/0/@colors.3" foreground="/0/@colors.2" lineWidth="1" transparency="0.0" width="21" height="20" x="5" y="25" cornerHeight="5" cornerWidth="5"/>
          <link businessObjects="_t6RzcDRVEeSMLJ8vnBP-0A"/>
          <children visible="true">
            <properties key="node_name" value="node_name"/>
            <graphicsAlgorithm xsi:type="al:Text" foreground="/0/@colors.4" lineWidth="1" filled="false" transparency="0.0" width="21" height="20" horizontalAlignment="ALIGNMENT_CENTER" value="v"/>
          </children>
        </children>
        <children xsi:type="pi:ContainerShape" visible="true" active="true">
          <properties key="node" value="node"/>
          <properties key="requirement" value="requirement"/>
          <graphicsAlgorithm xsi:type="al:RoundedRectangle" background="/0/@colors.3" foreground="/0/@colors.2" lineWidth="1" transparency="0.0" width="111" height="20" x="5" y="53" cornerHeight="5" cornerWidth="5"/>
          <link businessObjects="eu.cloudscaleproject.env.toolchain.ToolchainUtils.extractorRes.eu.cloudscaleproject.env.toolchain.ToolchainUtils.path_repository"/>
          <children visible="true">
            <properties key="node_name" value="node_name"/>
            <graphicsAlgorithm xsi:type="al:Text" foreground="/0/@colors.4" lineWidth="1" filled="false" transparency="0.0" width="111" height="20" horizontalAlignment="ALIGNMENT_CENTER" value="Repository"/>
          </children>
        </children>
        <children xsi:type="pi:ContainerShape" visible="true" active="true">
          <properties key="node" value="node"/>
          <properties key="requirement" value="requirement"/>
          <graphicsAlgorithm xsi:type="al:RoundedRectangle" background="/0/@colors.3" foreground="/0/@colors.2" lineWidth="1" transparency="0.0" width="111" height="20" x="5" y="75" cornerHeight="5" cornerWidth="5"/>
          <link businessObjects="eu.cloudscaleproject.env.toolchain.ToolchainUtils.extractorRes.eu.cloudscaleproject.env.toolchain.ToolchainUtils.path_system"/>
          <children visible="true">
            <properties key="node_name" value="node_name"/>
            <graphicsAlgorithm xsi:type="al:Text" foreground="/0/@colors.4" lineWidth="1" filled="false" transparency="0.0" width="111" height="20" horizontalAlignment="ALIGNMENT_CENTER" value="System"/>
          </children>
        </children>
      </children>
      <children xsi:type="pi:ContainerShape" visible="true" active="true">
        <properties key="node" value="node"/>
        <properties key="section" value="section"/>
        <graphicsAlgorithm xsi:type="al:RoundedRectangle" background="/0/@colors.3" foreground="/0/@colors.2" lineWidth="1" transparency="0.0" width="121" height="61" x="20" y="40" cornerHeight="15" cornerWidth="15"/>
        <link businessObjects="eu.cloudscaleproject.env.toolchain.ToolchainUtils.extractorInput"/>
        <anchors xsi:type="pi:ChopboxAnchor" outgoingConnections="/0/@connections.6"/>
        <children visible="true">
          <properties key="node_name" value="node_name"/>
          <graphicsAlgorithm xsi:type="al:Text" foreground="/0/@colors.2" lineWidth="1" filled="false" transparency="0.0" width="116" height="20" x="5" y="5" value="Input"/>
        </children>
        <children xsi:type="pi:ContainerShape" visible="true" active="true">
          <properties key="node" value="node"/>
          <properties key="command" value="command"/>
          <graphicsAlgorithm xsi:type="al:RoundedRectangle" background="/0/@colors.3" foreground="/0/@colors.2" lineWidth="1" transparency="0.0" width="20" height="20" x="5" y="25" cornerHeight="5" cornerWidth="5"/>
          <link businessObjects="_1QP7cDRVEeSMLJ8vnBP-0A"/>
          <children visible="true">
            <properties key="node_name" value="node_name"/>
            <graphicsAlgorithm xsi:type="al:Text" foreground="/0/@colors.4" lineWidth="1" filled="false" transparency="0.0" width="20" height="20" horizontalAlignment="ALIGNMENT_CENTER" value="v"/>
          </children>
        </children>
      </children>
      <children xsi:type="pi:ContainerShape" visible="true" active="true">
        <properties key="node" value="node"/>
        <properties key="section" value="section"/>
        <graphicsAlgorithm xsi:type="al:RoundedRectangle" background="/0/@colors.3" foreground="/0/@colors.2" lineWidth="1" transparency="0.0" width="121" height="61" x="20" y="140" cornerHeight="15" cornerWidth="15"/>
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
        <children xsi:type="pi:ContainerShape" visible="true" active="true">
          <properties key="node" value="node"/>
          <properties key="command" value="command"/>
          <graphicsAlgorithm xsi:type="al:RoundedRectangle" background="/0/@colors.3" foreground="/0/@colors.2" lineWidth="1" transparency="0.0" width="35" height="20" x="28" y="25" cornerHeight="5" cornerWidth="5"/>
          <link businessObjects="_TLb8YDdBEeSoZdJQhqDUDw"/>
          <children visible="true">
            <properties key="node_name" value="node_name"/>
            <graphicsAlgorithm xsi:type="al:Text" foreground="/0/@colors.4" lineWidth="1" filled="false" transparency="0.0" width="35" height="20" horizontalAlignment="ALIGNMENT_CENTER" value="Run"/>
          </children>
        </children>
        <children xsi:type="pi:ContainerShape" visible="true" active="true">
          <properties key="node" value="node"/>
          <properties key="command" value="command"/>
          <graphicsAlgorithm xsi:type="al:RoundedRectangle" background="/0/@colors.3" foreground="/0/@colors.2" lineWidth="1" transparency="0.0" width="21" height="20" x="65" y="25" cornerHeight="5" cornerWidth="5"/>
          <link businessObjects="_UElx8DdBEeSoZdJQhqDUDw"/>
          <children visible="true">
            <properties key="node_name" value="node_name"/>
            <graphicsAlgorithm xsi:type="al:Text" foreground="/0/@colors.4" lineWidth="1" filled="false" transparency="0.0" width="21" height="20" horizontalAlignment="ALIGNMENT_CENTER" value="p"/>
          </children>
        </children>
      </children>
      <children xsi:type="pi:ContainerShape" visible="true" active="true">
        <properties key="node" value="node"/>
        <properties key="command" value="command"/>
        <graphicsAlgorithm xsi:type="al:RoundedRectangle" background="/0/@colors.3" foreground="/0/@colors.2" lineWidth="1" transparency="0.0" width="20" height="20" x="286" y="5" cornerHeight="5" cornerWidth="5"/>
        <link businessObjects="_3sl3EDc_EeSoZdJQhqDUDw"/>
        <children visible="true">
          <properties key="node_name" value="node_name"/>
          <graphicsAlgorithm xsi:type="al:Text" foreground="/0/@colors.4" lineWidth="1" filled="false" transparency="0.0" width="20" height="20" horizontalAlignment="ALIGNMENT_CENTER" value="?"/>
        </children>
      </children>
    </children>
    <children xsi:type="pi:ContainerShape" visible="true" active="true">
      <properties key="node" value="node"/>
      <properties key="container" value="container"/>
      <graphicsAlgorithm xsi:type="al:RoundedRectangle" background="/0/@colors.1" foreground="/0/@colors.2" lineWidth="1" transparency="0.0" width="181" height="231" x="440" y="120" cornerHeight="15" cornerWidth="15"/>
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
          <graphicsAlgorithm xsi:type="al:Text" foreground="/0/@colors.2" lineWidth="1" filled="false" transparency="0.0" width="136" height="20" x="5" y="5" value="Architecture templates"/>
        </children>
      </children>
      <children xsi:type="pi:ContainerShape" visible="true" active="true">
        <properties key="node" value="node"/>
        <properties key="section" value="section"/>
        <graphicsAlgorithm xsi:type="al:RoundedRectangle" background="/0/@colors.3" foreground="/0/@colors.2" lineWidth="1" transparency="0.0" width="141" height="56" x="20" y="165" cornerHeight="15" cornerWidth="15"/>
        <link businessObjects="eu.cloudscaleproject.env.toolchain.ToolchainUtils.usageevolution"/>
        <anchors xsi:type="pi:ChopboxAnchor" outgoingConnections="/0/@connections.18"/>
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
    </children>
    <children xsi:type="pi:ContainerShape" visible="true" active="true">
      <properties key="node" value="node"/>
      <properties key="container" value="container"/>
      <graphicsAlgorithm xsi:type="al:RoundedRectangle" background="/0/@colors.1" foreground="/0/@colors.2" lineWidth="1" transparency="0.0" width="331" height="548" x="810" y="73" cornerHeight="15" cornerWidth="15"/>
      <link businessObjects="_Yqbi4C61EeSADewJHO2y3w"/>
      <children visible="true">
        <properties key="node_name" value="node_name"/>
        <graphicsAlgorithm xsi:type="al:Text" foreground="/0/@colors.2" lineWidth="1" filled="false" transparency="0.0" width="326" height="20" x="5" y="5" value="Analyser"/>
      </children>
      <children xsi:type="pi:ContainerShape" visible="true" active="true">
        <properties key="node" value="node"/>
        <properties key="section" value="section"/>
        <graphicsAlgorithm xsi:type="al:RoundedRectangle" background="/0/@colors.3" foreground="/0/@colors.2" lineWidth="1" transparency="0.0" width="251" height="181" x="30" y="47" cornerHeight="15" cornerWidth="15"/>
        <link businessObjects="eu.cloudscaleproject.env.toolchain.ToolchainUtils.analyserInput"/>
        <anchors xsi:type="pi:ChopboxAnchor" outgoingConnections="/0/@connections.4" incomingConnections="/0/@connections.0 /0/@connections.3 /0/@connections.12 /0/@connections.19"/>
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
      </children>
      <children xsi:type="pi:ContainerShape" visible="true" active="true">
        <properties key="node" value="node"/>
        <properties key="section" value="section"/>
        <graphicsAlgorithm xsi:type="al:RoundedRectangle" background="/0/@colors.3" foreground="/0/@colors.2" lineWidth="1" transparency="0.0" width="251" height="141" x="30" y="267" cornerHeight="15" cornerWidth="15"/>
        <link businessObjects="eu.cloudscaleproject.env.toolchain.ToolchainUtils.analyserConf"/>
        <anchors xsi:type="pi:ChopboxAnchor" outgoingConnections="/0/@connections.5" incomingConnections="/0/@connections.4 /0/@connections.18"/>
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
          <graphicsAlgorithm xsi:type="al:RoundedRectangle" background="/0/@colors.3" foreground="/0/@colors.2" lineWidth="1" transparency="0.0" width="241" height="20" x="5" y="53" cornerHeight="5" cornerWidth="5"/>
          <link businessObjects="eu.cloudscaleproject.env.toolchain.ToolchainUtils.analyserConf.eu.cloudscaleproject.env.toolchain.ToolchainUtils.path_pcmmeasuringpoint"/>
          <children visible="true">
            <properties key="node_name" value="node_name"/>
            <graphicsAlgorithm xsi:type="al:Text" foreground="/0/@colors.4" lineWidth="1" filled="false" transparency="0.0" width="241" height="20" horizontalAlignment="ALIGNMENT_CENTER" value="Measuring points"/>
          </children>
        </children>
        <children xsi:type="pi:ContainerShape" visible="true" active="true">
          <properties key="node" value="node"/>
          <properties key="requirement" value="requirement"/>
          <graphicsAlgorithm xsi:type="al:RoundedRectangle" background="/0/@colors.3" foreground="/0/@colors.2" lineWidth="1" transparency="0.0" width="241" height="20" x="5" y="75" cornerHeight="5" cornerWidth="5"/>
          <link businessObjects="eu.cloudscaleproject.env.toolchain.ToolchainUtils.analyserConf.eu.cloudscaleproject.env.toolchain.ToolchainUtils.path_pms"/>
          <children visible="true">
            <properties key="node_name" value="node_name"/>
            <graphicsAlgorithm xsi:type="al:Text" foreground="/0/@colors.4" lineWidth="1" filled="false" transparency="0.0" width="241" height="20" horizontalAlignment="ALIGNMENT_CENTER" value="Monitors"/>
          </children>
        </children>
        <children xsi:type="pi:ContainerShape" visible="true" active="true">
          <properties key="node" value="node"/>
          <properties key="requirement" value="requirement"/>
          <graphicsAlgorithm xsi:type="al:RoundedRectangle" background="/0/@colors.3" foreground="/0/@colors.2" lineWidth="1" transparency="0.0" width="241" height="20" x="5" y="97" cornerHeight="5" cornerWidth="5"/>
          <link businessObjects="eu.cloudscaleproject.env.toolchain.ToolchainUtils.analyserConf.eu.cloudscaleproject.env.toolchain.ToolchainUtils.path_slo"/>
          <children visible="true">
            <properties key="node_name" value="node_name"/>
            <graphicsAlgorithm xsi:type="al:Text" foreground="/0/@colors.4" lineWidth="1" filled="false" transparency="0.0" width="241" height="20" horizontalAlignment="ALIGNMENT_CENTER" value="Service Level Objectives"/>
          </children>
        </children>
      </children>
      <children xsi:type="pi:ContainerShape" visible="true" active="true">
        <properties key="node" value="node"/>
        <properties key="section" value="section"/>
        <graphicsAlgorithm xsi:type="al:RoundedRectangle" background="/0/@colors.3" foreground="/0/@colors.2" lineWidth="1" transparency="0.0" width="251" height="66" x="30" y="447" cornerHeight="15" cornerWidth="15"/>
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
        <graphicsAlgorithm xsi:type="al:RoundedRectangle" background="/0/@colors.3" foreground="/0/@colors.2" lineWidth="1" transparency="0.0" width="29" height="20" x="270" y="5" cornerHeight="5" cornerWidth="5"/>
        <link businessObjects="_fcGHoDc_EeSoZdJQhqDUDw"/>
        <children visible="true">
          <properties key="node_name" value="node_name"/>
          <graphicsAlgorithm xsi:type="al:Text" foreground="/0/@colors.4" lineWidth="1" filled="false" transparency="0.0" width="29" height="20" horizontalAlignment="ALIGNMENT_CENTER" value="p"/>
        </children>
      </children>
      <children xsi:type="pi:ContainerShape" visible="true" active="true">
        <properties key="node" value="node"/>
        <properties key="command" value="command"/>
        <graphicsAlgorithm xsi:type="al:RoundedRectangle" background="/0/@colors.3" foreground="/0/@colors.2" lineWidth="1" transparency="0.0" width="26" height="20" x="300" y="5" cornerHeight="5" cornerWidth="5"/>
        <link businessObjects="_z_PHEPo4EeSga-ySs5-xDw"/>
        <children visible="true">
          <properties key="node_name" value="node_name"/>
          <graphicsAlgorithm xsi:type="al:Text" foreground="/0/@colors.4" lineWidth="1" filled="false" transparency="0.0" width="26" height="20" horizontalAlignment="ALIGNMENT_CENTER" value="?"/>
        </children>
      </children>
    </children>
    <children xsi:type="pi:ContainerShape" visible="true" active="true">
      <properties key="node" value="node"/>
      <properties key="container" value="container"/>
      <graphicsAlgorithm xsi:type="al:RoundedRectangle" background="/0/@colors.1" foreground="/0/@colors.2" lineWidth="1" transparency="0.0" width="601" height="261" x="60" y="360" cornerHeight="15" cornerWidth="15"/>
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
        <anchors xsi:type="pi:ChopboxAnchor" outgoingConnections="/0/@connections.8" incomingConnections="/0/@connections.14"/>
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
        <children xsi:type="pi:ContainerShape" visible="true" active="true">
          <properties key="node" value="node"/>
          <properties key="command" value="command"/>
          <graphicsAlgorithm xsi:type="al:RoundedRectangle" background="/0/@colors.3" foreground="/0/@colors.2" lineWidth="1" transparency="0.0" width="11" height="11" x="10" y="30" cornerHeight="5" cornerWidth="5"/>
          <link businessObjects="_3flHgP79EeSFGv_dENwmLQ"/>
          <children visible="true">
            <properties key="node_name" value="node_name"/>
            <graphicsAlgorithm xsi:type="al:Text" foreground="/0/@colors.4" lineWidth="1" filled="false" transparency="0.0" width="11" height="11" horizontalAlignment="ALIGNMENT_CENTER" value="R"/>
          </children>
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
        <anchors xsi:type="pi:ChopboxAnchor" outgoingConnections="/0/@connections.13" incomingConnections="/0/@connections.11"/>
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
      <graphicsAlgorithm xsi:type="al:Polygon" background="/0/@colors.3" foreground="/0/@colors.2" lineWidth="1" filled="true" transparency="0.0" width="116" height="41" x="649" y="239">
        <points/>
        <points x="87"/>
        <points x="116" y="20"/>
        <points x="87" y="41"/>
        <points y="41"/>
      </graphicsAlgorithm>
      <link businessObjects="action_useMeasurements"/>
      <anchors xsi:type="pi:ChopboxAnchor" outgoingConnections="/0/@connections.12" incomingConnections="/0/@connections.13"/>
      <children visible="true">
        <properties key="node_name" value="node_name"/>
        <graphicsAlgorithm xsi:type="al:Text" foreground="/0/@colors.2" lineWidth="1" filled="false" transparency="0.0" width="111" height="41" x="5" value="Use measurements"/>
      </children>
    </children>
    <children xsi:type="pi:ContainerShape" visible="true" active="true">
      <properties key="node" value="node"/>
      <properties key="action" value="action"/>
      <graphicsAlgorithm xsi:type="al:Polygon" background="/0/@colors.3" foreground="/0/@colors.2" lineWidth="1" filled="true" transparency="0.0" width="114" height="52" x="650" y="160">
        <points/>
        <points x="86"/>
        <points x="114" y="26"/>
        <points x="86" y="52"/>
        <points y="52"/>
      </graphicsAlgorithm>
      <link businessObjects="action_transform_overview_analyser"/>
      <anchors xsi:type="pi:ChopboxAnchor" outgoingConnections="/0/@connections.3" incomingConnections="/0/@connections.2"/>
      <children visible="true">
        <properties key="node_name" value="node_name"/>
        <graphicsAlgorithm xsi:type="al:Text" foreground="/0/@colors.2" lineWidth="1" filled="false" transparency="0.0" width="109" height="52" x="5" value="Transformation"/>
      </children>
    </children>
    <children xsi:type="pi:ContainerShape" visible="true" active="true">
      <properties key="node" value="node"/>
      <properties key="action" value="action"/>
      <graphicsAlgorithm xsi:type="al:Polygon" background="/0/@colors.3" foreground="/0/@colors.2" lineWidth="1" filled="true" transparency="0.0" width="101" height="33" x="410" y="40">
        <points/>
        <points x="76"/>
        <points x="101" y="16"/>
        <points x="76" y="33"/>
        <points y="33"/>
      </graphicsAlgorithm>
      <link businessObjects="action_import_ext_analyser"/>
      <anchors xsi:type="pi:ChopboxAnchor" outgoingConnections="/0/@connections.0" incomingConnections="/0/@connections.16"/>
      <children visible="true">
        <properties key="node_name" value="node_name"/>
        <graphicsAlgorithm xsi:type="al:Text" foreground="/0/@colors.2" lineWidth="1" filled="false" transparency="0.0" width="96" height="33" x="5" value="Import"/>
      </children>
    </children>
    <children xsi:type="pi:ContainerShape" visible="true" active="true">
      <properties key="node" value="node"/>
      <properties key="action" value="action"/>
      <graphicsAlgorithm xsi:type="al:Polygon" background="/0/@colors.3" foreground="/0/@colors.2" lineWidth="1" filled="true" transparency="0.0" width="61" height="32" x="410" y="80">
        <points/>
        <points x="46"/>
        <points x="61" y="16"/>
        <points x="46" y="32"/>
        <points y="32"/>
      </graphicsAlgorithm>
      <link businessObjects="action_import_ext_overview"/>
      <anchors xsi:type="pi:ChopboxAnchor" outgoingConnections="/0/@connections.1" incomingConnections="/0/@connections.17"/>
      <children visible="true">
        <properties key="node_name" value="node_name"/>
        <graphicsAlgorithm xsi:type="al:Text" foreground="/0/@colors.2" lineWidth="1" filled="false" transparency="0.0" width="56" height="32" x="5" value="Import"/>
      </children>
    </children>
    <children xsi:type="pi:ContainerShape" visible="true" active="true">
      <properties key="node" value="node"/>
      <properties key="action" value="action"/>
      <graphicsAlgorithm xsi:type="al:Polygon" background="/0/@colors.3" foreground="/0/@colors.2" lineWidth="1" filled="true" transparency="0.0" width="81" height="35" x="220" y="290">
        <points/>
        <points x="61"/>
        <points x="81" y="17"/>
        <points x="61" y="35"/>
        <points y="35"/>
      </graphicsAlgorithm>
      <link businessObjects="action_import_ext_ss"/>
      <anchors xsi:type="pi:ChopboxAnchor" outgoingConnections="/0/@connections.14" incomingConnections="/0/@connections.15"/>
      <children visible="true">
        <properties key="node_name" value="node_name"/>
        <graphicsAlgorithm xsi:type="al:Text" foreground="/0/@colors.2" lineWidth="1" filled="false" transparency="0.0" width="76" height="35" x="5" value="Import"/>
      </children>
    </children>
    <children xsi:type="pi:ContainerShape" visible="true" active="true">
      <properties key="node" value="node"/>
      <properties key="action" value="action"/>
      <graphicsAlgorithm xsi:type="al:Polygon" background="/0/@colors.3" foreground="/0/@colors.2" lineWidth="1" filled="true" transparency="0.0" width="116" height="52" x="649" y="100">
        <points/>
        <points x="87"/>
        <points x="116" y="26"/>
        <points x="87" y="52"/>
        <points y="52"/>
      </graphicsAlgorithm>
      <link businessObjects="_kQmOgPo5EeSga-ySs5-xDw"/>
      <anchors xsi:type="pi:ChopboxAnchor" outgoingConnections="/0/@connections.19"/>
      <children visible="true">
        <properties key="node_name" value="node_name"/>
        <graphicsAlgorithm xsi:type="al:Text" foreground="/0/@colors.2" lineWidth="1" filled="false" transparency="0.0" width="111" height="52" x="5" value="Import existing"/>
      </children>
    </children>
    <connections xsi:type="pi:FreeFormConnection" visible="true" active="true" start="/0/@children.6/@anchors.0" end="/0/@children.2/@children.1/@anchors.0">
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
      <bendpoints x="964" y="57"/>
    </connections>
    <connections xsi:type="pi:FreeFormConnection" visible="true" active="true" start="/0/@children.7/@anchors.0" end="/0/@children.1/@children.1/@anchors.0">
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
      <bendpoints x="530" y="95"/>
    </connections>
    <connections xsi:type="pi:FreeFormConnection" visible="true" active="true" start="/0/@children.1/@children.1/@anchors.0" end="/0/@children.5/@anchors.0">
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
      <bendpoints x="620" y="186"/>
    </connections>
    <connections xsi:type="pi:FreeFormConnection" visible="true" active="true" start="/0/@children.5/@anchors.0" end="/0/@children.2/@children.1/@anchors.0">
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
      <bendpoints x="801" y="186"/>
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
    <connections xsi:type="pi:FreeFormConnection" visible="true" active="true" start="/0/@children.4/@anchors.0" end="/0/@children.2/@children.1/@anchors.0">
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
      <bendpoints x="807" y="258"/>
    </connections>
    <connections xsi:type="pi:FreeFormConnection" visible="true" active="true" start="/0/@children.3/@children.6/@anchors.0" end="/0/@children.4/@anchors.0">
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
      <bendpoints x="706" y="564"/>
    </connections>
    <connections xsi:type="pi:FreeFormConnection" visible="true" active="true" start="/0/@children.8/@anchors.0" end="/0/@children.3/@children.1/@anchors.0">
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
      <bendpoints x="358" y="308"/>
      <bendpoints x="358" y="425"/>
    </connections>
    <connections xsi:type="pi:FreeFormConnection" visible="true" active="true" start="/0/@children.0/@children.1/@anchors.0" end="/0/@children.8/@anchors.0">
      <graphicsAlgorithm xsi:type="al:Polyline" lineWidth="2" filled="false" transparency="0.0"/>
      <link businessObjects="/1/@links.15"/>
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
    <connections xsi:type="pi:FreeFormConnection" visible="true" active="true" start="/0/@children.0/@children.1/@anchors.0" end="/0/@children.6/@anchors.0">
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
      <bendpoints x="382" y="210"/>
      <bendpoints x="382" y="57"/>
    </connections>
    <connections xsi:type="pi:FreeFormConnection" visible="true" active="true" start="/0/@children.0/@children.1/@anchors.0" end="/0/@children.7/@anchors.0">
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
      <bendpoints x="280" y="96"/>
    </connections>
    <connections xsi:type="pi:FreeFormConnection" visible="true" active="true" start="/0/@children.1/@children.3/@anchors.0" end="/0/@children.2/@children.2/@anchors.0">
      <graphicsAlgorithm xsi:type="al:Polyline" lineWidth="2" lineStyle="DASH" filled="false" transparency="0.0"/>
      <link businessObjects="/1/@links.18"/>
      <connectionDecorators visible="true" locationRelative="true" location="1.0">
        <graphicsAlgorithm xsi:type="al:Polyline" lineWidth="2">
          <points x="-10" y="8"/>
          <points/>
          <points y="-2"/>
          <points x="-10" y="-8"/>
        </graphicsAlgorithm>
      </connectionDecorators>
      <bendpoints x="728" y="312"/>
      <bendpoints x="728" y="411"/>
    </connections>
    <connections xsi:type="pi:FreeFormConnection" visible="true" active="true" start="/0/@children.9/@anchors.0" end="/0/@children.2/@children.1/@anchors.0">
      <graphicsAlgorithm xsi:type="al:Polyline" lineWidth="2" lineStyle="DASH" filled="false" transparency="0.0"/>
      <link businessObjects="/1/@links.19"/>
      <connectionDecorators visible="true" locationRelative="true" location="1.0">
        <graphicsAlgorithm xsi:type="al:Polyline" lineWidth="2">
          <points x="-10" y="8"/>
          <points/>
          <points y="-2"/>
          <points x="-10" y="-8"/>
        </graphicsAlgorithm>
      </connectionDecorators>
      <bendpoints x="802" y="126"/>
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
    <links start="action_useMeasurements" end="eu.cloudscaleproject.env.toolchain.ToolchainUtils.analyserInput"/>
    <links start="eu.cloudscaleproject.env.toolchain.ToolchainUtils.spotterDynRes" end="action_useMeasurements" required="true"/>
    <links start="action_import_ext_ss" end="eu.cloudscaleproject.env.toolchain.ToolchainUtils.spotterStaInput"/>
    <links start="eu.cloudscaleproject.env.toolchain.ToolchainUtils.extractorRes" end="action_import_ext_ss" required="true"/>
    <links start="eu.cloudscaleproject.env.toolchain.ToolchainUtils.extractorRes" end="action_import_ext_analyser"/>
    <links start="eu.cloudscaleproject.env.toolchain.ToolchainUtils.extractorRes" end="action_import_ext_overview"/>
    <links start="eu.cloudscaleproject.env.toolchain.ToolchainUtils.usageevolution" end="eu.cloudscaleproject.env.toolchain.ToolchainUtils.analyserConf"/>
    <links start="_kQmOgPo5EeSga-ySs5-xDw" end="eu.cloudscaleproject.env.toolchain.ToolchainUtils.analyserInput"/>
    <nodes xsi:type="method:Container" id="_SqDU0C61EeSADewJHO2y3w" name="Extractor" tooltip="Extractor tool status container">
      <children xsi:type="method:Section" id="eu.cloudscaleproject.env.toolchain.ToolchainUtils.extractorRes" name="Results" colorForeground="000000" colorBackground="a1c9d5" commandId="eu.cloudscaleproject.env.extractor.command.openeditor" next="/1/@links.15 /1/@links.16 /1/@links.17" previous="/1/@links.7">
        <commandParam>openResults</commandParam>
        <commands id="_t6RzcDRVEeSMLJ8vnBP-0A" name="v" colorText="0000ff" colorForeground="000000" colorBackground="a1c9d5" commandId="eu.cloudscaleproject.env.extractor.command.openeditor">
          <commandParam>openResults</commandParam>
        </commands>
        <requirements id="eu.cloudscaleproject.env.toolchain.ToolchainUtils.extractorRes.eu.cloudscaleproject.env.toolchain.ToolchainUtils.path_repository" name="Repository" colorText="0000ff" colorForeground="000000" colorBackground="a1c9d5"/>
        <requirements id="eu.cloudscaleproject.env.toolchain.ToolchainUtils.extractorRes.eu.cloudscaleproject.env.toolchain.ToolchainUtils.path_system" name="System" colorText="0000ff" colorForeground="000000" colorBackground="a1c9d5" position="10"/>
      </children>
      <children xsi:type="method:Section" id="eu.cloudscaleproject.env.toolchain.ToolchainUtils.extractorInput" name="Input" colorForeground="000000" colorBackground="a1c9d5" commandId="eu.cloudscaleproject.env.extractor.command.openeditor" next="/1/@links.6">
        <commandParam>openInput</commandParam>
        <commands id="_1QP7cDRVEeSMLJ8vnBP-0A" name="v" colorText="0000ff" colorForeground="000000" colorBackground="a1c9d5" commandId="eu.cloudscaleproject.env.extractor.command.openeditor">
          <commandParam>openInput</commandParam>
        </commands>
      </children>
      <children xsi:type="method:Section" id="eu.cloudscaleproject.env.toolchain.ToolchainUtils.extractorConf" name="Extractor" colorForeground="000000" colorBackground="a1c9d5" commandId="eu.cloudscaleproject.env.extractor.command.openeditor" next="/1/@links.7" previous="/1/@links.6">
        <commandParam>openRun</commandParam>
        <commands id="_6bS5cDRUEeSMLJ8vnBP-0A" name="v" colorText="0000ff" colorForeground="000000" colorBackground="a1c9d5" commandId="eu.cloudscaleproject.env.extractor.command.openeditor" position="10">
          <commandParam>openRun</commandParam>
        </commands>
        <commands id="_TLb8YDdBEeSoZdJQhqDUDw" name="Run" colorText="0000ff" colorForeground="000000" colorBackground="a1c9d5" position="20"/>
        <commands id="_UElx8DdBEeSoZdJQhqDUDw" name="p" colorText="0000ff" colorForeground="000000" colorBackground="a1c9d5" position="30"/>
      </children>
      <commands id="_3sl3EDc_EeSoZdJQhqDUDw" name="?" tooltip="Show Extractor introduction page" colorText="0000ff" colorForeground="000000" colorBackground="a1c9d5" commandId="eu.cloudscaleproject.env.extractor.command.openeditor" position="10">
        <commandParam>openIntro</commandParam>
      </commands>
    </nodes>
    <nodes xsi:type="method:Container" id="_YVJPYC61EeSADewJHO2y3w" name="ScaleDL">
      <children xsi:type="method:Section" id="eu.cloudscaleproject.env.toolchain.ToolchainUtils.overview" name="Overview model" description="Overview model digram is used to model cloud environment architecture and its deployment." tooltip="Overview model of a cloud environemnt." colorForeground="000000" colorBackground="a1c9d5" commandId="eu.cloudscaleproject.env.overview.command.openValiadtionStatus" next="/1/@links.2" previous="/1/@links.1">
        <commands id="_KUsqwBMzEeWrnYazqRxW7w" name="v" colorText="0000ff" colorForeground="000000" colorBackground="a1c9d5" commandId="eu.cloudscaleproject.env.overview.command.openStatusSelectionDialog"/>
      </children>
      <children xsi:type="method:Section" id="architectureTemplates" name="Architecture templates" colorForeground="000000" colorBackground="a1c9d5"/>
      <children xsi:type="method:Section" id="eu.cloudscaleproject.env.toolchain.ToolchainUtils.usageevolution" name="Usage evolution" description="Usage evolution model" colorForeground="000000" colorBackground="a1c9d5" commandId="eu.cloudscaleproject.env.usageevolution.command.openeditor" next="/1/@links.18">
        <commands id="_iueiAHPSEeS3b6FdlQPPqA" name="v" description="Usage evolution alternative selection button " tooltip="Select Usage evolution alternative" colorText="0000ff" colorForeground="000000" colorBackground="a1c9d5" commandId="eu.cloudscaleproject.env.overview.command.openStatusSelectionDialog"/>
      </children>
    </nodes>
    <nodes xsi:type="method:Container" id="_Yqbi4C61EeSADewJHO2y3w" name="Analyser" tooltip="Analyser tool status container">
      <children xsi:type="method:Section" id="eu.cloudscaleproject.env.toolchain.ToolchainUtils.analyserInput" name="Analyser input" description="Input models, used by the Analyser tool." tooltip="Analyser input status section" colorForeground="000000" colorBackground="a1c9d5" commandId="eu.cloudscaleproject.env.analyser.command.openeditor" next="/1/@links.4" previous="/1/@links.0 /1/@links.3 /1/@links.12 /1/@links.19">
        <commandParam>openInput</commandParam>
        <commands id="_7zWcEDRVEeSMLJ8vnBP-0A" name="v" tooltip="Select input alternative" colorText="0000ff" colorForeground="000000" colorBackground="a1c9d5" commandId="eu.cloudscaleproject.env.analyser.command.openinputdialog"/>
        <requirements id="eu.cloudscaleproject.env.toolchain.ToolchainUtils.analyserInput.eu.cloudscaleproject.env.toolchain.ToolchainUtils.path_repository" name="Repository" description="Repository model diagram is used to model cloud services and their components." tooltip="Represents the repository model status" colorText="0000ff" colorForeground="000000" colorBackground="a1c9d5" position="10"/>
        <requirements id="eu.cloudscaleproject.env.toolchain.ToolchainUtils.analyserInput.eu.cloudscaleproject.env.toolchain.ToolchainUtils.path_system" name="System" description="System model is used to design cloud environment architecture." tooltip="Represents the system model status" colorText="0000ff" colorForeground="000000" colorBackground="a1c9d5" position="20"/>
        <requirements id="eu.cloudscaleproject.env.toolchain.ToolchainUtils.analyserInput.eu.cloudscaleproject.env.toolchain.ToolchainUtils.path_allocation" name="Allocation" description="Allocation model provides binding between repository and resource environment model. " tooltip="Represents the allocation model status" colorText="0000ff" colorForeground="000000" colorBackground="a1c9d5" position="30"/>
        <requirements id="eu.cloudscaleproject.env.toolchain.ToolchainUtils.analyserInput.eu.cloudscaleproject.env.toolchain.ToolchainUtils.path_resourceenvironment" name="Resource environment" description="Describes hardware resources in cloud environemnt." tooltip="Represents the resource environment model status" colorText="0000ff" colorForeground="000000" colorBackground="a1c9d5" position="40"/>
        <requirements id="eu.cloudscaleproject.env.toolchain.ToolchainUtils.analyserInput.eu.cloudscaleproject.env.toolchain.ToolchainUtils.path_usage" name="Usage" colorText="0000ff" colorForeground="000000" colorBackground="a1c9d5" position="100"/>
      </children>
      <children xsi:type="method:Section" id="eu.cloudscaleproject.env.toolchain.ToolchainUtils.analyserConf" name="Analyser" tooltip="Analyser configuration status section" colorForeground="000000" colorBackground="a1c9d5" commandId="eu.cloudscaleproject.env.analyser.command.openeditor" next="/1/@links.5" previous="/1/@links.4 /1/@links.18">
        <commandParam>openRun</commandParam>
        <commands id="_7Eo-8DHDEeSAkNNGxeU_SA" name="v" tooltip="Select configuration alternative" colorText="0000ff" colorForeground="000000" colorBackground="a1c9d5" commandId="eu.cloudscaleproject.env.analyser.command.openconfdialog" position="10"/>
        <requirements id="eu.cloudscaleproject.env.toolchain.ToolchainUtils.analyserConf.eu.cloudscaleproject.env.toolchain.ToolchainUtils.path_pcmmeasuringpoint" name="Measuring points" tooltip="Represents the measuring points model status" colorText="0000ff" colorForeground="000000" colorBackground="a1c9d5"/>
        <requirements id="eu.cloudscaleproject.env.toolchain.ToolchainUtils.analyserConf.eu.cloudscaleproject.env.toolchain.ToolchainUtils.path_pms" name="Monitors" tooltip="Represents the monitors model status" colorText="0000ff" colorForeground="000000" colorBackground="a1c9d5"/>
        <requirements id="eu.cloudscaleproject.env.toolchain.ToolchainUtils.analyserConf.eu.cloudscaleproject.env.toolchain.ToolchainUtils.path_slo" name="Service Level Objectives" tooltip="Represents the SLO model status" colorText="0000ff" colorForeground="000000" colorBackground="a1c9d5"/>
      </children>
      <children xsi:type="method:Section" id="eu.cloudscaleproject.env.toolchain.ToolchainUtils.analyserRes" name="Results" tooltip="Analyser results status section" colorForeground="000000" colorBackground="a1c9d5" commandId="eu.cloudscaleproject.env.analyser.command.openeditor" previous="/1/@links.5">
        <commandParam>openResults</commandParam>
      </children>
      <commands id="_fcGHoDc_EeSoZdJQhqDUDw" name="p" tooltip="Show Analyser perspective" colorText="0000ff" colorForeground="000000" colorBackground="a1c9d5" commandId="eu.cludscale.environment.analyser.openPerspective" position="10">
        <commandParam>openIntro</commandParam>
      </commands>
      <commands id="_z_PHEPo4EeSga-ySs5-xDw" name="?" tooltip="Show Analyser introduction page" colorText="0000ff" colorForeground="000000" colorBackground="a1c9d5" commandId="eu.cloudscaleproject.env.analyser.command.openeditor" position="5">
        <commandParam>openIntro</commandParam>
      </commands>
    </nodes>
    <nodes xsi:type="method:Container" id="_Zcc1sC61EeSADewJHO2y3w" name="Spotter" tooltip="Static and Dynamic Spotter tool status container">
      <children xsi:type="method:Section" id="eu.cloudscaleproject.env.toolchain.ToolchainUtils.spotterStaInput" name="Static spotter input" colorForeground="000000" colorBackground="a1c9d5" commandId="eu.cloudscaleproject.env.staticspotter.command.openeditor" next="/1/@links.8" previous="/1/@links.14">
        <commandParam>openInput</commandParam>
        <commands id="_cwXP4Po8EeSga-ySs5-xDw" name="V" tooltip="Select input alternative" colorText="0000ff" colorForeground="000000" colorBackground="a1c9d5" commandId="eu.cloudscaleproject.env.staticspotter.command.openeditor">
          <commandParam>openInput</commandParam>
        </commands>
      </children>
      <children xsi:type="method:Section" id="eu.cloudscaleproject.env.toolchain.ToolchainUtils.spotterStaConf" name="Static spotter" colorForeground="000000" colorBackground="a1c9d5" commandId="eu.cloudscaleproject.env.staticspotter.command.openeditor" next="/1/@links.9" previous="/1/@links.8">
        <commandParam>openConf</commandParam>
        <commands id="_gElZsPo8EeSga-ySs5-xDw" name="V" tooltip="Select configuration alternative" colorText="0000ff" colorForeground="000000" colorBackground="a1c9d5" commandId="eu.cloudscaleproject.env.staticspotter.command.openeditor">
          <commandParam>openConf</commandParam>
        </commands>
      </children>
      <children xsi:type="method:Section" id="eu.cloudscaleproject.env.toolchain.ToolchainUtils.spotterStaRes" name="Results" colorForeground="000000" colorBackground="a1c9d5" commandId="eu.cloudscaleproject.env.staticspotter.command.openeditor" previous="/1/@links.9">
        <commandParam>openResults</commandParam>
        <commands id="_3flHgP79EeSFGv_dENwmLQ" name="R" colorText="0000ff" colorForeground="000000" colorBackground="a1c9d5" commandId="eu.cloudscaleproject.env.staticspotter.command.openeditor">
          <commandParam>openResults</commandParam>
        </commands>
      </children>
      <children xsi:type="method:Section" id="eu.cloudscaleproject.env.toolchain.ToolchainUtils.spotterDynInput" name="Dynamic spotter input" colorForeground="000000" colorBackground="a1c9d5" commandId="eu.cloudscaleproject.env.dynamicspotter.command.openeditor" next="/1/@links.10">
        <commandParam>openInput</commandParam>
        <commands id="_tgQWoF3mEeScwqkqKbMIeg" name="v" tooltip="Select input alternative" colorText="0000ff" colorForeground="000000" colorBackground="a1c9d5" commandId="eu.cloudscaleproject.env.spotter.command.openinputdialog"/>
      </children>
      <children xsi:type="method:Section" id="eu.cloudscaleproject.env.toolchain.ToolchainUtils.spotterDynConf" name="Dynamic spotter" colorForeground="000000" colorBackground="a1c9d5" commandId="eu.cloudscaleproject.env.dynamicspotter.command.openeditor" next="/1/@links.11" previous="/1/@links.10">
        <commandParam>openRun</commandParam>
        <commands id="_yxql4F3mEeScwqkqKbMIeg" name="v" tooltip="Select configuration alternative" colorText="0000ff" colorForeground="000000" colorBackground="a1c9d5" commandId="eu.cloudscaleproject.env.spotter.command.openconfdialog"/>
      </children>
      <children xsi:type="method:Section" id="eu.cloudscaleproject.env.toolchain.ToolchainUtils.spotterDynRes" name="Results" colorForeground="000000" colorBackground="a1c9d5" commandId="eu.cloudscaleproject.env.dynamicspotter.command.openeditor" next="/1/@links.13" previous="/1/@links.11">
        <commandParam>openResults</commandParam>
      </children>
      <commands id="_j1WngPo6EeSga-ySs5-xDw" name="?" tooltip="Show Dynamic Spotter introduction page" colorText="0000ff" colorForeground="000000" colorBackground="a1c9d5" commandId="eu.cloudscaleproject.env.dynamicspotter.command.openeditor">
        <commandParam>openIntro</commandParam>
      </commands>
      <commands id="_GgslMPo7EeSga-ySs5-xDw" name="?" tooltip="Show Static Spotter introduction page" colorText="0000ff" colorForeground="000000" colorBackground="a1c9d5" commandId="eu.cloudscaleproject.env.staticspotter.command.openeditor">
        <commandParam>openIntro</commandParam>
      </commands>
    </nodes>
    <nodes xsi:type="method:Action" id="action_useMeasurements" name="Use measurements" colorForeground="000000" colorBackground="a1c9d5" next="/1/@links.12" previous="/1/@links.13"/>
    <nodes xsi:type="method:Action" id="action_transform_overview_analyser" name="Transformation" description="Transform Overview model into PCM model." tooltip="Overview to PCM transformation" colorForeground="000000" colorBackground="a1c9d5" commandId="org.scaledl.overview2pcm.command.transformWizard" next="/1/@links.3" previous="/1/@links.2"/>
    <nodes xsi:type="method:Action" id="action_import_ext_analyser" name="Import" description="Import Extractor result" colorForeground="000000" colorBackground="a1c9d5" commandId="eu.cloudscaleproject.env.analyser.command.openextractorimportwizard" next="/1/@links.0" previous="/1/@links.16"/>
    <nodes xsi:type="method:Action" id="action_import_ext_overview" name="Import" description="Import repository and system model, extracted from source code, into overview model." tooltip="Import extracted model into overview" colorForeground="000000" colorBackground="a1c9d5" commandId="eu.cludscale.environment.overview.openImportWizard" next="/1/@links.1" previous="/1/@links.17"/>
    <nodes xsi:type="method:Action" id="action_import_ext_ss" name="Import" description="Import input for StaticSpotter" colorForeground="000000" colorBackground="a1c9d5" commandId="eu.cloudscaleproject.env.staticspotter.command.openinputwizard" next="/1/@links.14" previous="/1/@links.15"/>
    <nodes xsi:type="method:Action" id="_kQmOgPo5EeSga-ySs5-xDw" name="Import existing" colorForeground="000000" colorBackground="a1c9d5" commandId="eu.cloudscaleproject.env.analyser.command.openpcmimportdialog" next="/1/@links.19"/>
  </method:Method>
</xmi:XMI>

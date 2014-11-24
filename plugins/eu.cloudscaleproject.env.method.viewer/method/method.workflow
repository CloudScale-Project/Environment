<?xml version="1.0" encoding="ASCII"?>
<xmi:XMI xmi:version="2.0" xmlns:xmi="http://www.omg.org/XMI" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:al="http://eclipse.org/graphiti/mm/algorithms" xmlns:method="http://eu.cloudscale.env.method/MethodComponentModel/1.0" xmlns:pi="http://eclipse.org/graphiti/mm/pictograms">
  <pi:Diagram visible="true" gridUnit="10" diagramTypeId="Workflow diagram" name="method" snapToGrid="true" pictogramLinks="/0/@children.0/@link /0/@children.0/@children.1/@link /0/@children.0/@children.1/@children.1/@link /0/@children.1/@link /0/@children.2/@link /0/@children.3/@link /0/@children.0/@children.2/@link /0/@children.0/@children.3/@link /0/@children.1/@children.1/@link /0/@children.1/@children.2/@link /0/@children.1/@children.3/@link /0/@children.2/@children.1/@link /0/@children.2/@children.2/@link /0/@children.2/@children.3/@link /0/@children.0/@children.1/@children.2/@link /0/@children.3/@children.1/@link /0/@children.3/@children.2/@link /0/@children.3/@children.3/@link /0/@children.3/@children.4/@link /0/@children.3/@children.5/@link /0/@children.3/@children.6/@link /0/@children.2/@children.1/@children.1/@link /0/@children.2/@children.1/@children.2/@link /0/@children.2/@children.1/@children.3/@link /0/@children.2/@children.1/@children.4/@link /0/@children.4/@link /0/@children.5/@link /0/@children.6/@link /0/@children.7/@link /0/@children.8/@link /0/@connections.0/@link /0/@connections.1/@link /0/@connections.2/@link /0/@connections.3/@link /0/@connections.4/@link /0/@connections.5/@link /0/@connections.6/@link /0/@connections.7/@link /0/@connections.8/@link /0/@connections.9/@link /0/@connections.10/@link /0/@connections.11/@link /0/@connections.12/@link /0/@connections.13/@link /0/@connections.14/@link /0/@connections.15/@link /0/@connections.16/@link /0/@connections.17/@link /0/@children.2/@children.2/@children.1/@link /0/@children.2/@children.2/@children.2/@link /0/@children.0/@children.3/@children.1/@link /0/@children.0/@children.1/@children.3/@link /0/@children.0/@children.2/@children.1/@link /0/@children.2/@children.1/@children.5/@link /0/@children.2/@children.3/@children.1/@link /0/@children.2/@children.4/@link /0/@children.0/@children.4/@link /0/@children.2/@children.2/@children.3/@link /0/@children.0/@children.3/@children.2/@link /0/@children.0/@children.3/@children.3/@link /0/@children.3/@children.4/@children.1/@link /0/@children.3/@children.5/@children.1/@link /0/@children.3/@children.5/@children.2/@link /0/@children.3/@children.6/@children.1/@link /0/@connections.18/@link /0/@children.2/@children.2/@children.4/@link /0/@children.2/@children.2/@children.5/@link /0/@children.2/@children.2/@children.6/@link /0/@children.2/@children.1/@children.6/@link /0/@children.1/@children.3/@children.1/@link" version="0.11.0">
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
        <link businessObjects="ext_res"/>
        <anchors xsi:type="pi:ChopboxAnchor" outgoingConnections="/0/@connections.15 /0/@connections.16 /0/@connections.17" incomingConnections="/0/@connections.7"/>
        <children visible="true">
          <properties key="node_name" value="node_name"/>
          <graphicsAlgorithm xsi:type="al:Text" foreground="/0/@colors.2" lineWidth="1" filled="false" transparency="0.0" width="116" height="20" x="5" y="5" value="Results"/>
        </children>
        <children xsi:type="pi:ContainerShape" visible="true" active="true">
          <properties key="node" value="node"/>
          <properties key="requirement" value="requirement"/>
          <graphicsAlgorithm xsi:type="al:RoundedRectangle" background="/0/@colors.3" foreground="/0/@colors.2" lineWidth="1" transparency="0.0" width="111" height="20" x="5" y="53" cornerHeight="5" cornerWidth="5"/>
          <link businessObjects="ext_res_repository"/>
          <children visible="true">
            <properties key="node_name" value="node_name"/>
            <graphicsAlgorithm xsi:type="al:Text" foreground="/0/@colors.4" lineWidth="1" filled="false" transparency="0.0" width="111" height="20" horizontalAlignment="ALIGNMENT_CENTER" value="Repository"/>
          </children>
        </children>
        <children xsi:type="pi:ContainerShape" visible="true" active="true">
          <properties key="node" value="node"/>
          <properties key="requirement" value="requirement"/>
          <graphicsAlgorithm xsi:type="al:RoundedRectangle" background="/0/@colors.3" foreground="/0/@colors.2" lineWidth="1" transparency="0.0" width="111" height="20" x="5" y="75" cornerHeight="5" cornerWidth="5"/>
          <link businessObjects="ext_res_system"/>
          <children visible="true">
            <properties key="node_name" value="node_name"/>
            <graphicsAlgorithm xsi:type="al:Text" foreground="/0/@colors.4" lineWidth="1" filled="false" transparency="0.0" width="111" height="20" horizontalAlignment="ALIGNMENT_CENTER" value="System"/>
          </children>
        </children>
        <children xsi:type="pi:ContainerShape" visible="true" active="true">
          <properties key="node" value="node"/>
          <properties key="command" value="command"/>
          <graphicsAlgorithm xsi:type="al:RoundedRectangle" background="/0/@colors.3" foreground="/0/@colors.2" lineWidth="1" transparency="0.0" width="20" height="20" x="5" y="25" cornerHeight="5" cornerWidth="5"/>
          <link businessObjects="_t6RzcDRVEeSMLJ8vnBP-0A"/>
          <children visible="true">
            <properties key="node_name" value="node_name"/>
            <graphicsAlgorithm xsi:type="al:Text" foreground="/0/@colors.4" lineWidth="1" filled="false" transparency="0.0" width="20" height="20" horizontalAlignment="ALIGNMENT_CENTER" value="v"/>
          </children>
        </children>
      </children>
      <children xsi:type="pi:ContainerShape" visible="true" active="true">
        <properties key="node" value="node"/>
        <properties key="section" value="section"/>
        <graphicsAlgorithm xsi:type="al:RoundedRectangle" background="/0/@colors.3" foreground="/0/@colors.2" lineWidth="1" transparency="0.0" width="121" height="61" x="20" y="40" cornerHeight="15" cornerWidth="15"/>
        <link businessObjects="ext_input"/>
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
        <link businessObjects="ext_tool"/>
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
        <link businessObjects="overview"/>
        <anchors xsi:type="pi:ChopboxAnchor" outgoingConnections="/0/@connections.2" incomingConnections="/0/@connections.1"/>
        <children visible="true">
          <properties key="node_name" value="node_name"/>
          <graphicsAlgorithm xsi:type="al:Text" foreground="/0/@colors.2" lineWidth="1" filled="false" transparency="0.0" width="136" height="20" x="5" y="5" value="Overview model"/>
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
        <link businessObjects="usageEvolution"/>
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
        <graphicsAlgorithm xsi:type="al:RoundedRectangle" background="/0/@colors.3" foreground="/0/@colors.2" lineWidth="1" transparency="0.0" width="251" height="181" x="30" y="37" cornerHeight="15" cornerWidth="15"/>
        <link businessObjects="analyser_input"/>
        <anchors xsi:type="pi:ChopboxAnchor" outgoingConnections="/0/@connections.4" incomingConnections="/0/@connections.0 /0/@connections.3 /0/@connections.12"/>
        <children visible="true">
          <properties key="node_name" value="node_name"/>
          <graphicsAlgorithm xsi:type="al:Text" foreground="/0/@colors.2" lineWidth="1" filled="false" transparency="0.0" width="246" height="20" x="5" y="5" value="PCM input"/>
        </children>
        <children xsi:type="pi:ContainerShape" visible="true" active="true">
          <properties key="node" value="node"/>
          <properties key="requirement" value="requirement"/>
          <graphicsAlgorithm xsi:type="al:RoundedRectangle" background="/0/@colors.3" foreground="/0/@colors.2" lineWidth="1" transparency="0.0" width="241" height="20" x="5" y="53" cornerHeight="5" cornerWidth="5"/>
          <link businessObjects="analyser_input_repository"/>
          <children visible="true">
            <properties key="node_name" value="node_name"/>
            <graphicsAlgorithm xsi:type="al:Text" foreground="/0/@colors.4" lineWidth="1" filled="false" transparency="0.0" width="241" height="20" horizontalAlignment="ALIGNMENT_CENTER" value="Repository"/>
          </children>
        </children>
        <children xsi:type="pi:ContainerShape" visible="true" active="true">
          <properties key="node" value="node"/>
          <properties key="requirement" value="requirement"/>
          <graphicsAlgorithm xsi:type="al:RoundedRectangle" background="/0/@colors.3" foreground="/0/@colors.2" lineWidth="1" transparency="0.0" width="241" height="20" x="5" y="75" cornerHeight="5" cornerWidth="5"/>
          <link businessObjects="analyser_input_system"/>
          <children visible="true">
            <properties key="node_name" value="node_name"/>
            <graphicsAlgorithm xsi:type="al:Text" foreground="/0/@colors.4" lineWidth="1" filled="false" transparency="0.0" width="241" height="20" horizontalAlignment="ALIGNMENT_CENTER" value="System"/>
          </children>
        </children>
        <children xsi:type="pi:ContainerShape" visible="true" active="true">
          <properties key="node" value="node"/>
          <properties key="requirement" value="requirement"/>
          <graphicsAlgorithm xsi:type="al:RoundedRectangle" background="/0/@colors.3" foreground="/0/@colors.2" lineWidth="1" transparency="0.0" width="241" height="20" x="5" y="97" cornerHeight="5" cornerWidth="5"/>
          <link businessObjects="analyser_input_allocation"/>
          <children visible="true">
            <properties key="node_name" value="node_name"/>
            <graphicsAlgorithm xsi:type="al:Text" foreground="/0/@colors.4" lineWidth="1" filled="false" transparency="0.0" width="241" height="20" horizontalAlignment="ALIGNMENT_CENTER" value="Allocation"/>
          </children>
        </children>
        <children xsi:type="pi:ContainerShape" visible="true" active="true">
          <properties key="node" value="node"/>
          <properties key="requirement" value="requirement"/>
          <graphicsAlgorithm xsi:type="al:RoundedRectangle" background="/0/@colors.3" foreground="/0/@colors.2" lineWidth="1" transparency="0.0" width="241" height="20" x="5" y="119" cornerHeight="5" cornerWidth="5"/>
          <link businessObjects="analyser_input_resource"/>
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
          <link businessObjects="analyser_input_usage"/>
          <children visible="true">
            <properties key="node_name" value="node_name"/>
            <graphicsAlgorithm xsi:type="al:Text" foreground="/0/@colors.4" lineWidth="1" filled="false" transparency="0.0" width="241" height="20" horizontalAlignment="ALIGNMENT_CENTER" value="Usage"/>
          </children>
        </children>
      </children>
      <children xsi:type="pi:ContainerShape" visible="true" active="true">
        <properties key="node" value="node"/>
        <properties key="section" value="section"/>
        <graphicsAlgorithm xsi:type="al:RoundedRectangle" background="/0/@colors.3" foreground="/0/@colors.2" lineWidth="1" transparency="0.0" width="251" height="141" x="30" y="257" cornerHeight="15" cornerWidth="15"/>
        <link businessObjects="analyser_tool"/>
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
          <properties key="command" value="command"/>
          <graphicsAlgorithm xsi:type="al:RoundedRectangle" background="/0/@colors.3" foreground="/0/@colors.2" lineWidth="1" transparency="0.0" width="35" height="20" x="28" y="25" cornerHeight="5" cornerWidth="5"/>
          <link businessObjects="_YEzigDHEEeSAkNNGxeU_SA"/>
          <children visible="true">
            <properties key="node_name" value="node_name"/>
            <graphicsAlgorithm xsi:type="al:Text" foreground="/0/@colors.4" lineWidth="1" filled="false" transparency="0.0" width="35" height="20" horizontalAlignment="ALIGNMENT_CENTER" value="Run"/>
          </children>
        </children>
        <children xsi:type="pi:ContainerShape" visible="true" active="true">
          <properties key="node" value="node"/>
          <properties key="command" value="command"/>
          <graphicsAlgorithm xsi:type="al:RoundedRectangle" background="/0/@colors.3" foreground="/0/@colors.2" lineWidth="1" transparency="0.0" width="21" height="20" x="65" y="25" cornerHeight="5" cornerWidth="5"/>
          <link businessObjects="_2WHW4DdAEeSoZdJQhqDUDw"/>
          <children visible="true">
            <properties key="node_name" value="node_name"/>
            <graphicsAlgorithm xsi:type="al:Text" foreground="/0/@colors.4" lineWidth="1" filled="false" transparency="0.0" width="21" height="20" horizontalAlignment="ALIGNMENT_CENTER" value="p"/>
          </children>
        </children>
        <children xsi:type="pi:ContainerShape" visible="true" active="true">
          <properties key="node" value="node"/>
          <properties key="requirement" value="requirement"/>
          <graphicsAlgorithm xsi:type="al:RoundedRectangle" background="/0/@colors.3" foreground="/0/@colors.2" lineWidth="1" transparency="0.0" width="241" height="20" x="5" y="53" cornerHeight="5" cornerWidth="5"/>
          <link businessObjects="analyser_conf_measuringpoints"/>
          <children visible="true">
            <properties key="node_name" value="node_name"/>
            <graphicsAlgorithm xsi:type="al:Text" foreground="/0/@colors.4" lineWidth="1" filled="false" transparency="0.0" width="241" height="20" horizontalAlignment="ALIGNMENT_CENTER" value="Measuring points"/>
          </children>
        </children>
        <children xsi:type="pi:ContainerShape" visible="true" active="true">
          <properties key="node" value="node"/>
          <properties key="requirement" value="requirement"/>
          <graphicsAlgorithm xsi:type="al:RoundedRectangle" background="/0/@colors.3" foreground="/0/@colors.2" lineWidth="1" transparency="0.0" width="241" height="20" x="5" y="75" cornerHeight="5" cornerWidth="5"/>
          <link businessObjects="analyser_conf_pms"/>
          <children visible="true">
            <properties key="node_name" value="node_name"/>
            <graphicsAlgorithm xsi:type="al:Text" foreground="/0/@colors.4" lineWidth="1" filled="false" transparency="0.0" width="241" height="20" horizontalAlignment="ALIGNMENT_CENTER" value="Measurement spec."/>
          </children>
        </children>
        <children xsi:type="pi:ContainerShape" visible="true" active="true">
          <properties key="node" value="node"/>
          <properties key="requirement" value="requirement"/>
          <graphicsAlgorithm xsi:type="al:RoundedRectangle" background="/0/@colors.3" foreground="/0/@colors.2" lineWidth="1" transparency="0.0" width="241" height="20" x="5" y="97" cornerHeight="5" cornerWidth="5"/>
          <link businessObjects="analyser_conf_slo"/>
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
        <link businessObjects="analyser_res"/>
        <anchors xsi:type="pi:ChopboxAnchor" incomingConnections="/0/@connections.5"/>
        <children visible="true">
          <properties key="node_name" value="node_name"/>
          <graphicsAlgorithm xsi:type="al:Text" foreground="/0/@colors.2" lineWidth="1" filled="false" transparency="0.0" width="246" height="20" x="5" y="5" value="Results"/>
        </children>
        <children xsi:type="pi:ContainerShape" visible="true" active="true">
          <properties key="node" value="node"/>
          <properties key="command" value="command"/>
          <graphicsAlgorithm xsi:type="al:RoundedRectangle" background="/0/@colors.3" foreground="/0/@colors.2" lineWidth="1" transparency="0.0" width="21" height="20" x="5" y="25" cornerHeight="5" cornerWidth="5"/>
          <link businessObjects="_PdUsUDRWEeSMLJ8vnBP-0A"/>
          <children visible="true">
            <properties key="node_name" value="node_name"/>
            <graphicsAlgorithm xsi:type="al:Text" foreground="/0/@colors.4" lineWidth="1" filled="false" transparency="0.0" width="21" height="20" horizontalAlignment="ALIGNMENT_CENTER" value="+"/>
          </children>
        </children>
      </children>
      <children xsi:type="pi:ContainerShape" visible="true" active="true">
        <properties key="node" value="node"/>
        <properties key="command" value="command"/>
        <graphicsAlgorithm xsi:type="al:RoundedRectangle" background="/0/@colors.3" foreground="/0/@colors.2" lineWidth="1" transparency="0.0" width="21" height="20" x="305" y="5" cornerHeight="5" cornerWidth="5"/>
        <link businessObjects="_fcGHoDc_EeSoZdJQhqDUDw"/>
        <children visible="true">
          <properties key="node_name" value="node_name"/>
          <graphicsAlgorithm xsi:type="al:Text" foreground="/0/@colors.4" lineWidth="1" filled="false" transparency="0.0" width="21" height="20" horizontalAlignment="ALIGNMENT_CENTER" value="?"/>
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
        <link businessObjects="static_spotter_input"/>
        <anchors xsi:type="pi:ChopboxAnchor" outgoingConnections="/0/@connections.8" incomingConnections="/0/@connections.14"/>
        <children visible="true">
          <properties key="node_name" value="node_name"/>
          <graphicsAlgorithm xsi:type="al:Text" foreground="/0/@colors.2" lineWidth="1" filled="false" transparency="0.0" width="246" height="20" x="5" y="5" value="Static spotter input"/>
        </children>
      </children>
      <children xsi:type="pi:ContainerShape" visible="true" active="true">
        <properties key="node" value="node"/>
        <properties key="section" value="section"/>
        <graphicsAlgorithm xsi:type="al:RoundedRectangle" background="/0/@colors.3" foreground="/0/@colors.2" lineWidth="1" transparency="0.0" width="251" height="51" x="20" y="110" cornerHeight="15" cornerWidth="15"/>
        <link businessObjects="static_spotter_tool"/>
        <anchors xsi:type="pi:ChopboxAnchor" outgoingConnections="/0/@connections.9" incomingConnections="/0/@connections.8"/>
        <children visible="true">
          <properties key="node_name" value="node_name"/>
          <graphicsAlgorithm xsi:type="al:Text" foreground="/0/@colors.2" lineWidth="1" filled="false" transparency="0.0" width="246" height="20" x="5" y="5" value="Static spotter"/>
        </children>
      </children>
      <children xsi:type="pi:ContainerShape" visible="true" active="true">
        <properties key="node" value="node"/>
        <properties key="section" value="section"/>
        <graphicsAlgorithm xsi:type="al:RoundedRectangle" background="/0/@colors.3" foreground="/0/@colors.2" lineWidth="1" transparency="0.0" width="251" height="51" x="20" y="180" cornerHeight="15" cornerWidth="15"/>
        <link businessObjects="static_spotter_res"/>
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
        <link businessObjects="dynamic_spotter_input"/>
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
        <link businessObjects="dynamic_spotter_tool"/>
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
        <children xsi:type="pi:ContainerShape" visible="true" active="true">
          <properties key="node" value="node"/>
          <properties key="command" value="command"/>
          <graphicsAlgorithm xsi:type="al:RoundedRectangle" background="/0/@colors.3" foreground="/0/@colors.2" lineWidth="1" transparency="0.0" width="35" height="20" x="28" y="25" cornerHeight="5" cornerWidth="5"/>
          <link businessObjects="_0xIjQF3mEeScwqkqKbMIeg"/>
          <children visible="true">
            <properties key="node_name" value="node_name"/>
            <graphicsAlgorithm xsi:type="al:Text" foreground="/0/@colors.4" lineWidth="1" filled="false" transparency="0.0" width="35" height="20" horizontalAlignment="ALIGNMENT_CENTER" value="Run"/>
          </children>
        </children>
      </children>
      <children xsi:type="pi:ContainerShape" visible="true" active="true">
        <properties key="node" value="node"/>
        <properties key="section" value="section"/>
        <graphicsAlgorithm xsi:type="al:RoundedRectangle" background="/0/@colors.3" foreground="/0/@colors.2" lineWidth="1" transparency="0.0" width="241" height="51" x="330" y="180" cornerHeight="15" cornerWidth="15"/>
        <link businessObjects="dynamic_spotter_res"/>
        <anchors xsi:type="pi:ChopboxAnchor" outgoingConnections="/0/@connections.13" incomingConnections="/0/@connections.11"/>
        <children visible="true">
          <properties key="node_name" value="node_name"/>
          <graphicsAlgorithm xsi:type="al:Text" foreground="/0/@colors.2" lineWidth="1" filled="false" transparency="0.0" width="236" height="20" x="5" y="5" value="Results"/>
        </children>
        <children xsi:type="pi:ContainerShape" visible="true" active="true">
          <properties key="node" value="node"/>
          <properties key="command" value="command"/>
          <graphicsAlgorithm xsi:type="al:RoundedRectangle" background="/0/@colors.3" foreground="/0/@colors.2" lineWidth="1" transparency="0.0" width="21" height="20" x="5" y="25" cornerHeight="5" cornerWidth="5"/>
          <link businessObjects="_3Gnz8F3mEeScwqkqKbMIeg"/>
          <children visible="true">
            <properties key="node_name" value="node_name"/>
            <graphicsAlgorithm xsi:type="al:Text" foreground="/0/@colors.4" lineWidth="1" filled="false" transparency="0.0" width="21" height="20" horizontalAlignment="ALIGNMENT_CENTER" value="v"/>
          </children>
        </children>
      </children>
    </children>
    <children xsi:type="pi:ContainerShape" visible="true" active="true">
      <properties key="node" value="node"/>
      <properties key="action" value="action"/>
      <graphicsAlgorithm xsi:type="al:Polygon" background="/0/@colors.3" foreground="/0/@colors.2" lineWidth="1" filled="true" transparency="0.0" width="116" height="41" x="648" y="230">
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
      <graphicsAlgorithm xsi:type="al:Polygon" background="/0/@colors.3" foreground="/0/@colors.2" lineWidth="1" filled="true" transparency="0.0" width="113" height="41" x="650" y="90">
        <points/>
        <points x="85"/>
        <points x="113" y="20"/>
        <points x="85" y="41"/>
        <points y="41"/>
      </graphicsAlgorithm>
      <link businessObjects="action_transform_overview_analyser"/>
      <anchors xsi:type="pi:ChopboxAnchor" outgoingConnections="/0/@connections.3" incomingConnections="/0/@connections.2"/>
      <children visible="true">
        <properties key="node_name" value="node_name"/>
        <graphicsAlgorithm xsi:type="al:Text" foreground="/0/@colors.2" lineWidth="1" filled="false" transparency="0.0" width="108" height="41" x="5" value="Transformation"/>
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
      <bendpoints x="706" y="185"/>
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
      <bendpoints x="791" y="111"/>
      <bendpoints x="791" y="201"/>
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
      <bendpoints x="758" y="251"/>
      <bendpoints x="799" y="251"/>
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
      <bendpoints x="705" y="564"/>
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
      <bendpoints x="382" y="209"/>
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
      <bendpoints x="728" y="399"/>
    </connections>
    <colors red="227" green="238" blue="249"/>
    <colors red="255" green="255" blue="255"/>
    <colors/>
    <colors red="161" green="201" blue="213"/>
    <colors blue="255"/>
  </pi:Diagram>
  <method:Method>
    <links start="action_import_ext_analyser" end="analyser_input"/>
    <links start="action_import_ext_overview" end="overview"/>
    <links start="overview" end="action_transform_overview_analyser" required="true"/>
    <links start="action_transform_overview_analyser" end="analyser_input"/>
    <links start="analyser_input" end="analyser_tool" required="true"/>
    <links start="analyser_tool" end="analyser_res" required="true"/>
    <links start="ext_input" end="ext_tool" required="true"/>
    <links start="ext_tool" end="ext_res" required="true"/>
    <links start="static_spotter_input" end="static_spotter_tool" required="true"/>
    <links start="static_spotter_tool" end="static_spotter_res" required="true"/>
    <links start="dynamic_spotter_input" end="dynamic_spotter_tool" required="true"/>
    <links start="dynamic_spotter_tool" end="dynamic_spotter_res" required="true"/>
    <links start="action_useMeasurements" end="analyser_input"/>
    <links start="dynamic_spotter_res" end="action_useMeasurements" required="true"/>
    <links start="action_import_ext_ss" end="static_spotter_input"/>
    <links start="ext_res" end="action_import_ext_ss" required="true"/>
    <links start="ext_res" end="action_import_ext_analyser"/>
    <links start="ext_res" end="action_import_ext_overview"/>
    <links start="usageEvolution" end="analyser_tool"/>
    <nodes xsi:type="method:Container" id="_SqDU0C61EeSADewJHO2y3w" name="Extractor">
      <children xsi:type="method:Section" id="ext_res" name="Results" colorForeground="000000" colorBackground="a1c9d5" commandId="eu.cloudscaleproject.env.extractor.command.openeditor" next="/1/@links.15 /1/@links.16 /1/@links.17" previous="/1/@links.7">
        <commandParam>openResults</commandParam>
        <commands id="_t6RzcDRVEeSMLJ8vnBP-0A" name="v" colorText="0000ff" colorForeground="000000" colorBackground="a1c9d5" commandId="eu.cloudscaleproject.env.extractor.command.openeditor">
          <commandParam>openResults</commandParam>
        </commands>
        <requirements id="ext_res_repository" name="Repository" colorText="0000ff" colorForeground="000000" colorBackground="a1c9d5"/>
        <requirements id="ext_res_system" name="System" colorText="0000ff" colorForeground="000000" colorBackground="a1c9d5"/>
      </children>
      <children xsi:type="method:Section" id="ext_input" name="Input" colorForeground="000000" colorBackground="a1c9d5" commandId="eu.cloudscaleproject.env.extractor.command.openeditor" next="/1/@links.6">
        <commandParam>openInput</commandParam>
        <commands id="_1QP7cDRVEeSMLJ8vnBP-0A" name="v" colorText="0000ff" colorForeground="000000" colorBackground="a1c9d5" commandId="eu.cloudscaleproject.env.extractor.command.openeditor">
          <commandParam>openInput</commandParam>
        </commands>
      </children>
      <children xsi:type="method:Section" id="ext_tool" name="Extractor" colorForeground="000000" colorBackground="a1c9d5" commandId="eu.cloudscaleproject.env.extractor.command.openeditor" next="/1/@links.7" previous="/1/@links.6">
        <commandParam>openRun</commandParam>
        <commands id="_6bS5cDRUEeSMLJ8vnBP-0A" name="v" colorText="0000ff" colorForeground="000000" colorBackground="a1c9d5" commandId="eu.cloudscaleproject.env.extractor.command.openeditor" position="10">
          <commandParam>openRun</commandParam>
        </commands>
        <commands id="_TLb8YDdBEeSoZdJQhqDUDw" name="Run" colorText="0000ff" colorForeground="000000" colorBackground="a1c9d5" position="20"/>
        <commands id="_UElx8DdBEeSoZdJQhqDUDw" name="p" colorText="0000ff" colorForeground="000000" colorBackground="a1c9d5" position="30"/>
      </children>
      <commands id="_3sl3EDc_EeSoZdJQhqDUDw" name="?" colorText="0000ff" colorForeground="000000" colorBackground="a1c9d5" commandId="eu.cloudscaleproject.env.extractor.command.openeditor" position="10">
        <commandParam>openIntro</commandParam>
      </commands>
    </nodes>
    <nodes xsi:type="method:Container" id="_YVJPYC61EeSADewJHO2y3w" name="ScaleDL">
      <children xsi:type="method:Section" id="overview" name="Overview model" description="Overview model digram is used to model cloud environment architecture and its deployment." tooltip="Overview model of a cloud environemnt." colorForeground="000000" colorBackground="a1c9d5" commandId="org.scaledl.overview.diagram.open" next="/1/@links.2" previous="/1/@links.1"/>
      <children xsi:type="method:Section" id="architectureTemplates" name="Architecture templates" colorForeground="000000" colorBackground="a1c9d5"/>
      <children xsi:type="method:Section" id="usageEvolution" name="Usage evolution" description="Usage evolution model" colorForeground="000000" colorBackground="a1c9d5" commandId="eu.cloudscaleproject.env.usageevolution.command.openeditor" next="/1/@links.18">
        <commandParam>usageEvolution</commandParam>
        <commands id="_iueiAHPSEeS3b6FdlQPPqA" name="v" description="Usage evolution alternative selection button " tooltip="Select Usage evolution alternative" colorText="0000ff" colorForeground="000000" colorBackground="a1c9d5" commandId="eu.cloudscaleproject.env.usageevolution.command.openSelectionDialog"/>
      </children>
    </nodes>
    <nodes xsi:type="method:Container" id="_Yqbi4C61EeSADewJHO2y3w" name="Analyser">
      <children xsi:type="method:Section" id="analyser_input" name="PCM input" description="Input models, used by the Analyser tool." tooltip="Input for the Analyser tool." colorForeground="000000" colorBackground="a1c9d5" commandId="eu.cloudscaleproject.env.analyser.command.openeditor" next="/1/@links.4" previous="/1/@links.0 /1/@links.3 /1/@links.12">
        <commandParam>openInput</commandParam>
        <commands id="_7zWcEDRVEeSMLJ8vnBP-0A" name="v" tooltip="Select input alternative" colorText="0000ff" colorForeground="000000" colorBackground="a1c9d5" commandId="eu.cloudscaleproject.env.analyser.command.openinputdialog"/>
        <requirements id="analyser_input_repository" name="Repository" description="Repository model diagram is used to model cloud services and their components." tooltip="Represents the repository model status" colorText="0000ff" colorForeground="000000" colorBackground="a1c9d5" position="10"/>
        <requirements id="analyser_input_system" name="System" description="System model is used to design cloud environment architecture." tooltip="Represents the system model status" colorText="0000ff" colorForeground="000000" colorBackground="a1c9d5" position="20"/>
        <requirements id="analyser_input_allocation" name="Allocation" description="Allocation model provides binding between repository and resource environment model. " tooltip="Represents the allocation model status" colorText="0000ff" colorForeground="000000" colorBackground="a1c9d5" position="30"/>
        <requirements id="analyser_input_resource" name="Resource environment" description="Describes hardware resources in cloud environemnt." tooltip="Represents the resource environment model status" colorText="0000ff" colorForeground="000000" colorBackground="a1c9d5" position="40"/>
        <requirements id="analyser_input_usage" name="Usage" colorText="0000ff" colorForeground="000000" colorBackground="a1c9d5" position="100"/>
      </children>
      <children xsi:type="method:Section" id="analyser_tool" name="Analyser" colorForeground="000000" colorBackground="a1c9d5" commandId="eu.cloudscaleproject.env.analyser.command.openeditor" next="/1/@links.5" previous="/1/@links.4 /1/@links.18">
        <commandParam>openRun</commandParam>
        <commands id="_7Eo-8DHDEeSAkNNGxeU_SA" name="v" tooltip="Select configuration alternative" colorText="0000ff" colorForeground="000000" colorBackground="a1c9d5" commandId="eu.cloudscaleproject.env.analyser.command.openconfdialog" position="10"/>
        <commands id="_YEzigDHEEeSAkNNGxeU_SA" name="Run" colorText="0000ff" colorForeground="000000" colorBackground="a1c9d5" commandId="" position="20"/>
        <commands id="_2WHW4DdAEeSoZdJQhqDUDw" name="p" colorText="0000ff" colorForeground="000000" colorBackground="a1c9d5" commandId="eu.cludscale.environment.analyser.openPerspective" position="30"/>
        <requirements id="analyser_conf_measuringpoints" name="Measuring points" colorText="0000ff" colorForeground="000000" colorBackground="a1c9d5"/>
        <requirements id="analyser_conf_pms" name="Measurement spec." colorText="0000ff" colorForeground="000000" colorBackground="a1c9d5"/>
        <requirements id="analyser_conf_slo" name="Service Level Objectives" colorText="0000ff" colorForeground="000000" colorBackground="a1c9d5"/>
      </children>
      <children xsi:type="method:Section" id="analyser_res" name="Results" colorForeground="000000" colorBackground="a1c9d5" commandId="eu.cloudscaleproject.env.analyser.command.openeditor" previous="/1/@links.5">
        <commandParam>openResults</commandParam>
        <commands id="_PdUsUDRWEeSMLJ8vnBP-0A" name="+" colorText="0000ff" colorForeground="000000" colorBackground="a1c9d5" commandId="eu.cloudscaleproject.env.analyser.command.openeditor">
          <commandParam>openResults</commandParam>
        </commands>
      </children>
      <commands id="_fcGHoDc_EeSoZdJQhqDUDw" name="?" colorText="0000ff" colorForeground="000000" colorBackground="a1c9d5" commandId="eu.cloudscaleproject.env.analyser.command.openeditor" position="10">
        <commandParam>openIntro</commandParam>
      </commands>
    </nodes>
    <nodes xsi:type="method:Container" id="_Zcc1sC61EeSADewJHO2y3w" name="Spotter">
      <children xsi:type="method:Section" id="static_spotter_input" name="Static spotter input" colorForeground="000000" colorBackground="a1c9d5" next="/1/@links.8" previous="/1/@links.14"/>
      <children xsi:type="method:Section" id="static_spotter_tool" name="Static spotter" colorForeground="000000" colorBackground="a1c9d5" next="/1/@links.9" previous="/1/@links.8"/>
      <children xsi:type="method:Section" id="static_spotter_res" name="Results" colorForeground="000000" colorBackground="a1c9d5" previous="/1/@links.9"/>
      <children xsi:type="method:Section" id="dynamic_spotter_input" name="Dynamic spotter input" colorForeground="000000" colorBackground="a1c9d5" commandId="eu.cloudscaleproject.env.dynamicspotter.command.openeditor" next="/1/@links.10">
        <commandParam>openInput</commandParam>
        <commands id="_tgQWoF3mEeScwqkqKbMIeg" name="v" colorText="0000ff" colorForeground="000000" colorBackground="a1c9d5" commandId="eu.cloudscaleproject.env.spotter.command.openinputdialog"/>
      </children>
      <children xsi:type="method:Section" id="dynamic_spotter_tool" name="Dynamic spotter" colorForeground="000000" colorBackground="a1c9d5" commandId="eu.cloudscaleproject.env.dynamicspotter.command.openeditor" next="/1/@links.11" previous="/1/@links.10">
        <commandParam>openRun</commandParam>
        <commands id="_yxql4F3mEeScwqkqKbMIeg" name="v" colorText="0000ff" colorForeground="000000" colorBackground="a1c9d5" commandId="eu.cloudscaleproject.env.spotter.command.openconfdialog"/>
        <commands id="_0xIjQF3mEeScwqkqKbMIeg" name="Run" colorText="0000ff" colorForeground="000000" colorBackground="a1c9d5" commandId="eu.cloudscaleproject.env.spotter.command.run"/>
      </children>
      <children xsi:type="method:Section" id="dynamic_spotter_res" name="Results" colorForeground="000000" colorBackground="a1c9d5" commandId="eu.cloudscaleproject.env.dynamicspotter.command.openeditor" next="/1/@links.13" previous="/1/@links.11">
        <commandParam>openResults</commandParam>
        <commands id="_3Gnz8F3mEeScwqkqKbMIeg" name="v" colorText="0000ff" colorForeground="000000" colorBackground="a1c9d5" commandId="eu.cloudscaleproject.env.spotter.result.select"/>
      </children>
    </nodes>
    <nodes xsi:type="method:Action" id="action_useMeasurements" name="Use measurements" colorForeground="000000" colorBackground="a1c9d5" next="/1/@links.12" previous="/1/@links.13"/>
    <nodes xsi:type="method:Action" id="action_transform_overview_analyser" name="Transformation" description="Transform Overview model into PCM model." tooltip="Overview to PCM transformation" colorForeground="000000" colorBackground="a1c9d5" commandId="eu.cloudscaleproject.env.csm2pcm.command.runtransformation" next="/1/@links.3" previous="/1/@links.2"/>
    <nodes xsi:type="method:Action" id="action_import_ext_analyser" name="Import" colorForeground="000000" colorBackground="a1c9d5" next="/1/@links.0" previous="/1/@links.16"/>
    <nodes xsi:type="method:Action" id="action_import_ext_overview" name="Import" description="Import repository and system model, extracted from source code, into overview model." tooltip="Import extracted model into overview" colorForeground="000000" colorBackground="a1c9d5" commandId="eu.cludscale.environment.extractor.openImportWizard" next="/1/@links.1" previous="/1/@links.17"/>
    <nodes xsi:type="method:Action" id="action_import_ext_ss" name="Import" colorForeground="000000" colorBackground="a1c9d5" next="/1/@links.14" previous="/1/@links.15"/>
  </method:Method>
</xmi:XMI>

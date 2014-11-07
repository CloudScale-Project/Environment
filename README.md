<img src="http://www.cloudscale-project.eu/static/img/logo-CloudScale.png" width=200> 

CloudScale
=========

The CloudScale Environment is a desktop application that integrates the different tools of the CloudScale project, 
namely the Dynamic and Static Spotters, the Analyzer and the Extractor, while driving the user through the flow of the CloudScale Model.
This desktop application can be installed and used in any personal computer running Java 6+, including Windows, MacOS and Linux.

Build and run CloudScale Environment
-----------------------------------------

1. Clone repository
	$ cd Cloudscale
	$ git clone https://github.com/CloudScale-Project/Environment.git
2. Build Cloudscale Environemnt
	$ mvn compile
	$ mvn package
3. Run Linux,MacOS,Windows distribution.

Start developing CloudScale Environment
-----------------------------------------

1. Download and install [Eclipse Luna for RCP and RAP][1]
2. Download and install Eclipse plugin dependencies for CloudScale developement.
	- Go to Eclipse->Help->Install New Software
	- Add CloudScale Environment update site: "http://cloudscale.xlab.si/cse/updatesites/environment/nightly/".
	- Install CloudScale Environment Developement feature.
3. Clone repository
	$ git clone https://github.com/CloudScale-Project/Environment.git
4. Import CloudScale Environment plugins, under "plugins/" directory, into Eclipse.

Plugin descriptions
-----------------------------------------

#eu.cloudscaleproject.env.analyser#

Analyser plugin that wraps all external dependencies for the Analyser tool and provides functionality to model, configure and simulate services deployed on elastic infrastructures.
It mostly contains classes which provide GUI components for the Dashboard editor and 'validators', that monitor current input/run/result configurations.

#eu.cloudscaleproject.env.common#

Common plugin required by all other plugins. It contains generally used dialogs and custom GUI components, project explorer common operations, functionality to retrieve project files, interfaces for notification and tool status mechanism and common context used by dependency injection. It also contains helper classes for GUI resource management, base class used by extension points to support dependency injection, color manipulator and converter and others.

#eu.cloudscaleproject.env.csm2pcm#

Contains QVTO scripts and support classes that gives support for transformation from Overview model to PCM model. PCM model is needed as an input to the Analyser.

#eu.cloudscaleproject.env.extractor#

#eu.cloudscaleproject.env.help#

Plugin includes HTML help pages.

#eu.cloudscaleproject.env.master#

Plugin used as a base project plugin for building the RCP application. It contains the '.product' file.

#eu.cloudscaleproject.env.method.common#

Common plugin for the Workflow diagram. Workflow diagram shows notifications and enabled options, based on the current state of the project. It contains Method meta-model and Graphiti diagram patterns, used for persisting, viewing and editing Workflow diagram.

#eu.cloudscaleproject.env.method.editor#

Plugin that is used as a Eclipse extension to create and edit Workflow diagrams, based on Method meta-model.
This plugin is not meant to be used inside CloudScale product application.
Please read description of the "eu.cloudscaleproject.env.method.common" plugin.

#eu.cloudscaleproject.env.method.viewer#

Plugin that provides only view capabilities and dynamic decorations for the Workflow diagram.
Please read description of the "eu.cloudscaleproject.env.method.common" plugin.

#eu.cloudscaleproject.env.product#

Plugin serves as a main CloudScale application configuration. It contains Eclipse E4 application model, toolbar and menu items, project nature, new project wizard and custom application brandings.

#eu.cloudscaleproject.env.spotter#

Dynamic Spotter plugin that wraps all external dependencies for the Dynamic Spotter tool and provides functionality to analyse and discover performance problems. Dynamic spotter approach is measurement-based, in contrast to the Analyser or Static spotter tool approach.
It mostly contains classes which provide GUI components for the Dashboard editor and various validators, that monitor current input/run/result configurations.

#eu.cloudscaleproject.env.staticspotter#

Static Spotter plugin that wraps all external dependencies for the Static Spotter tool.

#eu.cloudscaleproject.env.toolchain#

Toolchain plugin serves as the base plugin for the Dashboard editor. Dashboard editor is the common editor for all the tools that consist CloudScale Environment. It is extended, using the extension point functionality, by the aforementioned Analyser, Dynamic Spotter, Static Spotter and Extractor plugins, to provide GUI components for editing configurations and displaying the results.

#org.scaledl.overview#

This plugin contains EMF auto-generated classes, produced from Overview meta-model. It also introduces two new extension points to support different cloud environment specifications and model-to-model transformations.

#org.scaledl.overview.diagram#

Plugin provides diagram representation of the Overview model. It uses Graphiti framework for displaying and editing EMF based Overview model, together with custom service editors.

#org.scaledl.overview.edit#

This plugin contains EMF auto-generated "item provider" classes, used mainly by the "org.scaledl.overview.editor" plugin.

#org.scaledl.overview.editor#

Provides basic EMF editor for the Overview model.

#org.scaledl.overview.generic#

Overview generic plugin contains different cloud infrastructure specifications for the Overview model. Currently AWS, OpenStack, SAP Hana and Generic cloud infrastructures are supported.

#org.scaledl.overview.properties#

Overview properties plugin provides extension for the Overview diagram properties panel. It contains custom table and cell editors that facilitates Overview diagram editing.


[1]: https://www.eclipse.org/downloads/packages/eclipse-rcp-and-rap-developers/lunasr1
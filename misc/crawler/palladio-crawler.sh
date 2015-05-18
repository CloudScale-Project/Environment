#!/bin/bash

# Extract all update-sites, features and plugins available on Palladio website
URL='https://sdqweb.ipd.kit.edu/eclipse'
URL_REGEX=${URL//\//\\\/}

wget -q -O - "$@" $URL | 
	grep "<a href=" | sed "s/<a href/\\n<a href/g" |
	sed 's/\"/\"><\/a>\n/2' | 
	grep "href=\"\w" | 
	sed "s/<a href=\"\(.*\)\".*/$URL_REGEX\/\1nightly/g" > update-sites-nightly.txt

sed "s/\(.*\)/\1\/features/g" update-sites-nightly.txt | xargs wget -q -O - "$@" | grep ".jar" | sed 's/.*href=\"\(.*.jar\)\".*/\1/g' | sed s/_.*.jar//g | sort | uniq > features.txt
sed "s/\(.*\)/\1\/plugins/g" update-sites-nightly.txt | xargs wget -q -O - "$@" | grep ".jar" | sed 's/.*href=\"\(.*.jar\)\".*/\1/g' | sed s/_.*.jar//g | sort | uniq > plugins.txt

cat update-sites-nightly.txt | sed -e 's/\(.*\)/<repository><id>???<\/id><layout>p2<\/layout><url>\1<\/url><\/repository>/g' >> update-sites-nightly-repository.txt

cat features.txt | grep org.palladiosimulator.*.feature | grep -v .source. | sed -e 's/\(.*\)/<includes id="\1" version="0.0.0"\/>/g' >> palladio-includes.txt
cat features.txt | grep org.palladiosimulator.*.feature | grep    .source. | sed -e 's/\(.*\)/<includes id="\1" version="0.0.0"\/>/g' >> palladio-includes.txt
cat features.txt | grep de.uka.ipd.sdq.*.feature | grep    .source. | sed -e 's/\(.*\)/<includes id="\1" version="0.0.0"\/>/g' >> palladio-includes.txt
cat features.txt | grep de.uka.ipd.sdq.*.feature | grep    .source. | sed -e 's/\(.*\)/<includes id="\1" version="0.0.0"\/>/g' >> palladio-includes.txt

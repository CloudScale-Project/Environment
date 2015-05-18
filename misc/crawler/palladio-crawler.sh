#!/bin/bash

# Extract all update-sites, features and plugins available on Palladio website
#URL='https://sdqweb.ipd.kit.edu/eclipse'
URL='https://sdqweb.ipd.kit.edu/eclipse/palladio/addons'

URL_REGEX=${URL//\//\\\/}

wget -q -O - "$@" $URL | 
	grep "<a href=" | sed "s/<a href/\\n<a href/g" |
	sed 's/\"/\"><\/a>\n/2' | 
	grep "href=\"\w" | 
	sed "s/<a href=\"\(.*\)\".*/$URL_REGEX\/\1nightly/g" > update-sites-nightly.txt


sed "s/\(.*\)/\1\/features/g" update-sites-nightly.txt | xargs wget -q -O - "$@" | grep ".jar" | sed 's/.*href=\"\(.*.jar\)\".*/\1/g' | sed s/_.*.jar//g | sort | uniq > features.txt
sed "s/\(.*\)/\1\/plugins/g" update-sites-nightly.txt | xargs wget -q -O - "$@" | grep ".jar" | sed 's/.*href=\"\(.*.jar\)\".*/\1/g' | sed s/_.*.jar//g | sort | uniq > plugins.txt

cat update-sites-nightly.txt | sed -e 's/\(.*\)/<repository><id>???<\/id><layout>p2<\/layout><url>\1<\/url><\/repository>/g' > update-sites-nightly-repository.txt
sed -i 's/\(.*\)???\(.*\)\(eclipse\/\)\(.*\)\(\/nightly\)/\1\4\2\3\4\5/g' update-sites-nightly-repository.txt

echo -e "PalladioSimulator\n--------------------\n" > palladio-includes.txt
cat features.txt | grep org.palladiosimulator.*.feature | grep -v .source. | sed -e 's/\(.*\)/<includes id="\1" version="0.0.0"\/>/g' >> palladio-includes.txt

echo -e "\n\nPalladioSimulator (sources)\n------------------\n" >> palladio-includes.txt
cat features.txt | grep org.palladiosimulator.*.feature | grep    .source. | sed -e 's/\(.*\)/<includes id="\1" version="0.0.0"\/>/g' >> palladio-includes.txt

echo -e "\n\nDE \n------------------\n" >> palladio-includes.txt
cat features.txt | grep ^de.*.feature | grep    .source. | sed -e 's/\(.*\)/<includes id="\1" version="0.0.0"\/>/g' >> palladio-includes.txt

echo -e "\n\nDE (sources)\n------------------\n" >> palladio-includes.txt
cat features.txt | grep ^de.*.feature | grep    .source. | sed -e 's/\(.*\)/<includes id="\1" version="0.0.0"\/>/g' >> palladio-includes.txt

URL='https://sdqweb.ipd.kit.edu/eclipse/palladiosimulator/nightly/aggregate'

wget -q -O - "$URL/features" | grep ".jar" | sed 's/.*href=\"\(.*.jar\)\".*/\1/g' | sed s/_.*.jar//g | sort | uniq > features.txt
wget -q -O - "$URL/plugins" | grep ".jar" | sed 's/.*href=\"\(.*.jar\)\".*/\1/g' | sed s/_.*.jar//g | sort | uniq > plugins.txt

echo -e "PalladioSimulator\n--------------------\n" > palladio-includes.txt
cat features.txt | grep -v -e \.source | sed -e 's/\(.*\)/<includes id="\1" version="0.0.0"\/>/g' >> palladio-includes.txt

echo -e "\n\nPalladioSimulator (sources)\n------------------\n" >> palladio-includes.txt
cat features.txt | grep -e  \.source | sed -e 's/\(.*\)/<includes id="\1" version="0.0.0"\/>/g' >> palladio-includes.txt


echo -e "\n\nPalladioSimulator Plugins \n------------------\n" >> palladio-includes.txt
cat plugins.txt | grep -v -e \.source | sed -e 's/\(.*\)/<plugin id="\1" version="0.0.0"\/>/g' >> palladio-includes.txt

echo -e "\n\nPalladioSimulator Plugins (source)\n------------------\n" >> palladio-includes.txt
cat plugins.txt | grep -e \.source | sed -e 's/\(.*\)/<plugin id="\1" version="0.0.0" download-size="0" install-size="0" unpack="false"\/>/g' >> palladio-includes.txt


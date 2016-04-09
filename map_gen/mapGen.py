import os
import sys
from xml.etree.ElementTree import Element, SubElement, Comment, tostring, ElementTree
from xml.etree import ElementTree
from xml.dom import minidom
import random
import time

"""
    Return a pretty-printed XML string for the Element.
"""
def prettify(data):
    rough_string = ElementTree.tostring(data, 'utf-8')
    reparsed = minidom.parseString(rough_string)
    return reparsed.toprettyxml(indent="    ")

# Returns the terrain type corresponding to the txt file
def getTerrain(content, mapWidth, mapHeight):
    terrain = {}
    for k in range(0, 10):
        for i in range(0, mapHeight):
            # print content[i]
            for j in range(0, mapWidth):
                print str(str(i) + ", " + str(j) + ", " + str(k))
                char = content[i][j]
                if(char == "e"):
                    terrain[j, i, k] = "EARTH"
                elif(char == "w"):
                    terrain[j, i, k] = "WATER"
                elif(char == "s"):
                    terrain[j, i, k] = "SKY"
                else:
                    print("WTF why didnt that work?")
        for z in range(0, mapHeight + 1):
            content.pop(0)

    return terrain


# Generate the XML file
def generate(mapSizeX, mapSizeY, terrainArray, outputFileName):
    root = Element("map")
    root.set('width', str(mapSizeX))
    root.set('height', str(mapSizeY))

    for z in range(0, 10):
        for x in range(0, mapSizeX):
            for y in range(0, mapSizeY):
                element = SubElement(root, "tile")

                element.set("x", str(x - mapSizeX/2))
                element.set("y", str(y - mapSizeY/2))
                element.set("z", str(z))
                # Set the terrain
                terrain = SubElement(element, "terrain")
                terrainType = terrainArray[x, y, z]
                terrain.set("type", terrainType)

    # Write to the file
    outputFile = open(outputFileName + ".xml", "w")
    outputFile.write(prettify(root))


if __name__ == "__main__":
    #read the map file
    with open("map.txt") as f:
        content = f.readlines()

    # Extract the width and height from the first two lines and remove them from the list
    mapWidth = int(content[0])
    mapHeight = int(content[1])
    content.pop(0)
    content.pop(0)

    # Get the terrain from the file
    terrain = getTerrain(content, mapWidth, mapHeight);

    generate(mapWidth, mapHeight, terrain, "default_map")

    # items = getItems(content, mapWidth, mapHeight);
    # TODO: Generate items and area effects in the same way (non random in a text file)

    # Generate the xml file
    # generate(mapWidth, mapHeight, terrain, items, 20, 40, "default_map")
    print "Map successfully created!"

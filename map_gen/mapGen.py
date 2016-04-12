import os
import sys
from xml.etree.ElementTree import Element, SubElement, Comment, tostring, ElementTree
from xml.etree import ElementTree
from xml.dom import minidom
import random
import time
from shutil import copyfile

# Module Globals:


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
                # print str(str(i) + ", " + str(j) + ", " + str(k))
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
def generate(mapSizeX, mapSizeY, terrainArray, outputFileName, available_aoes):
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

                # possibly set an AOE here.
                # not limiting the amnt of aoes that can be spawned currentl
                # Only place AOE's on Z level of 0 for now
                if spawnAOE() and z == 0:
                    print 'Generated an AOE at pt ' + str(x) + ", " + str(y) + ", " + str(z)
                    available_aoes -= 1
                    generateRandomAOE(element, mapSizeX, mapSizeY, 10)

    # Write to the file
    outputFile = open(outputFileName + ".xml", "w")
    outputFile.write(prettify(root))


def spawnAOE():
    # generates a random number between 0 and 1
    chance = random.random()

    # 10% chance to spawn AOE
    if chance >= .10 and chance <= .20:
        return True
    else:
        return False


def generateRandomHealOrDamageAmount(max_val):
    return random.randint(1, max_val)


def generateRandomTrapTime():
    # be trapped for a maximum of 12 seconds.
    # (completely random amount)
    return random.randint(1, 12)


def generateRandom3DPoint(x_max, y_max, z_max):
    return (random.randint(0, x_max), random.randint(0, y_max), random.randint(0, z_max))


def generateRandomAOE(parentXMLElement, mapSizeX, mapSizeY, mapSizeZ):
    AOE_TYPES = ['HEAL', 'DAMAGE', 'LEVEL', 'DEATH', 'TRAP', 'TELEPORT']
    end_range = len(AOE_TYPES) - 1
    aoe = AOE_TYPES[random.randint(0, end_range)]

    # Create the XML object
    aoe_xml = SubElement(parentXMLElement, "area-effect")
    aoe_xml.set("type", aoe)

    # appropiately set values in it
    if aoe == 'HEAL' or aoe == 'DAMAGE':
        health_amnt = generateRandomHealOrDamageAmount(20)
        aoe_xml.set("value", str(health_amnt))
    elif aoe == 'TRAP':
        trap_time = generateRandomTrapTime()
        aoe_xml.set("value", str(trap_time))
    elif aoe == 'LEVEL' or aoe == 'DEATH':
        # These 2 dont need values, but need to set since we will
        # be attempting to read in the Javaz
        aoe_xml.set("value", str(0))
    elif aoe == 'TELEPORT':
        tele_point = generateRandom3DPoint(mapSizeX, mapSizeY, mapSizeZ)
        value = str(tele_point[0])+','+str(tele_point[1])+','+str(tele_point[2])
        print 'TELEPORT VAL: ' + value
        aoe_xml.set("value", value)
    else:
        print 'u fukt up bro'


if __name__ == "__main__":
    # read the map file
    with open("map.txt") as f:
        content = f.readlines()

    # Extract the width and height from the first two lines and remove them from the list
    mapWidth = int(content[0])
    mapHeight = int(content[1])
    content.pop(0)
    content.pop(0)

    # make a dictionary of available items
    ITEM_IDS = []
    # only wanna spawn a maximum of 30 aoe's.
    available_aoes = 30

    # Get the terrain from the file
    terrain = getTerrain(content, mapWidth, mapHeight)

    generate(mapWidth, mapHeight, terrain, "default_map", available_aoes)

    # items = getItems(content, mapWidth, mapHeight);
    # TODO: Generate items and area effects in the same way (non random in a text file)

    # Generate the xml file
    # generate(mapWidth, mapHeight, terrain, items, 20, 40, "default_map")

    print "Map successfully created!"
    # copy the file to '../res/map/default_map.xml'
    dest = '../res/map/default_map.xml'
    copyfile('default_map.xml', dest)
    print 'Map XML copied to: ' + dest

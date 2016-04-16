from xml.etree.ElementTree import Element, SubElement, Comment, tostring, ElementTree
from xml.etree import ElementTree
from xml.dom import minidom

def add_item(parentElement, x, y, z):
    # massive if block to conditionally add item to a tile.
    if (x == 1 and y == -1 and z == 0):
        # Create the XML object
        item_xml = SubElement(parentElement, "item")
        item_xml.set("id", "1000")


from xml.etree.ElementTree import Element, SubElement, Comment, tostring, ElementTree
from xml.etree import ElementTree
from xml.dom import minidom


'''
Takes in a 3D point.
Conditionally check the point and add whatever items
you want to that point!!!
'''
def add_item(parentElement, x, y, z):
    # massive if block to conditionally add items to a specific tiles.
    if (x == -5 and y == -2 and z == 1):
        add_yoga_pants(parentElement)

    if (x == 1 and y == -1 and z == 0):
        add_moccasin(parentElement)

    if (x == 2 and y == -2 and z == 1):
        add_air_stff(parentElement)

    if (x == 3 and y == -3 and z == 0):
        add_blue_phat(parentElement)

    if (x == 4 and y == -4 and z == 0):
        add_brass_knuckles(parentElement)

    if (x == 5 and y == -5 and z == 0):
        add_bronze_sword(parentElement)

    if (x == 6 and y == -6 and z == 0):
        add_chainmail(parentElement)

    if (x == 7 and y == -7 and z == 0):
        add_crossbow(parentElement)

    if (x == -5 and y == -10 and z == 3):
        add_dragon_dagger(parentElement)

    if (x == 12 and y == -12 and z == 9):
        add_dragon_2h(parentElement)

    if (x == -4 and y == -2 and z == 1):
        add_fire_staff(parentElement)

    if (x == -4 and y == 0 and z == 0):
        add_frat_tank(parentElement)

    if (x == -7 and y == -1 and z == 0):
        add_iron_helmet(parentElement)

    if (x == -6 and y == 3 and z == 1):
        add_jackson_gloves(parentElement)

    if (x == -7 and y == 3 and z == 1):
        add_ninja_stars(parentElement)

    if (x == -8 and y == 4 and z == 1):
        add_peter_pan_leggings(parentElement)

    if (x == -9 and y == 6 and z == 1):
        add_throwing_knives(parentElement)

    if (x == -6 and y == 6 and z == 1):
        add_two_handed_axe(parentElement)

    if (x == 0 and y == 8 and z == 0):
        add_water_staff(parentElement)

    if (x == 1 and y == 6 and z == 1):
        add_wooden_gauntlets(parentElement)

    if (x == -6 and y == 3 and z == 1):
        add_yoga_pants(parentElement)
    if (x == 12 and y == -3 and z == 0):
        add_health_pot(parentElement)
    if (x == -9 and y == -8 and z == 0):
        add_mana_pot(parentElement)
    if (x == 2 and y == -10 and z == 2):
        add_health_pot(parentElement)
    #### Boulders lol.
    if (x == -8 and y == 9 and z == 0):
        add_boulder(parentElement)
    if (x == -6 and y == 9 and z == 0):
        add_fire_staff(parentElement)
    if (x == -9 and y == 10 and z == 0):
        add_boulder(parentElement)
    if (x == -10 and y == 11 and z == 0):
        add_boulder(parentElement)
    if (x == -11 and y == 12 and z == 0):
        add_boulder(parentElement)
    if (x == -10 and y == 12 and z == 0):
        add_boulder(parentElement)
    if (x == -9 and y == 12 and z == 0):
        add_boulder(parentElement)
    if (x == -8 and y == 12 and z == 0):
        add_boulder(parentElement)
    if (x == -7 and y == 11 and z == 0):
        add_boulder(parentElement)
    if (x == -6 and y == 10 and z == 0):
        add_boulder(parentElement)
    if (x == -5 and y == 9 and z == 0):
        add_boulder(parentElement)
    if (x == -4 and y == 8 and z == 0):
        add_boulder(parentElement)
    if (x == -3 and y == 7 and z == 0):
        add_boulder(parentElement)
    if (x == -7 and y == 8 and z == 0):
        add_boulder(parentElement)
    if (x == -6 and y == 7 and z == 0):
        add_boulder(parentElement)
    if (x == -5 and y == 7 and z == 0):
        add_boulder(parentElement)
    if (x == -4 and y == 7 and z == 0):
        add_boulder(parentElement)
    if (x == -8 and y == -11 and z == 6):
        add_kat_key(parentElement)
    if (x == -1 and y == 2 and z == 0):
        add_door(parentElement)
    if (x == -12 and y == -4 and z == 0):
            add_cacoon(parentElement)
    if (x == 8 and y == -10 and z == 0) or (x == 6 and y == -2 and z == 1):
        add_bug_zapper(parentElement)
'''
All functions to add certain items.
If you wanna add a new item, just follow the other exampls
And add it to a tile based off the passed in point
in the add_item func
'''
def add_moccasin(parentElement):
    # Create the XML object for moccasins
    item_xml = SubElement(parentElement, "item")
    item_xml.set("id", "1000")

def add_air_stff(parentElement):
    # Create the XML object for airstaff
    item_xml = SubElement(parentElement, "item")
    item_xml.set("id", "8000")

def add_blue_phat(parentElement):
    # Create the XML object for blue phat
    item_xml = SubElement(parentElement, "item")
    item_xml.set("id", "3000")

def add_brass_knuckles(parentElement):
    # Create the XML object for brass knuckles
    item_xml = SubElement(parentElement, "item")
    item_xml.set("id", "4001")

def add_bronze_sword(parentElement):
    # Create the XML object for bronze short sword
    item_xml = SubElement(parentElement, "item")
    item_xml.set("id", "6000")

def add_chainmail(parentElement):
    # Create the XML object for chainmail
    item_xml = SubElement(parentElement, "item")
    item_xml.set("id", "2002")

def add_crossbow(parentElement):
    # Create the XML object for crossbow
    item_xml = SubElement(parentElement, "item")
    item_xml.set("id", "9002")

def add_dragon_dagger(parentElement):
    # Create the XML object for dragon dagger
    item_xml = SubElement(parentElement, "item")
    item_xml.set("id", "6002")

def add_dragon_2h(parentElement):
    # Create the XML object for dragon 2h
    item_xml = SubElement(parentElement, "item")
    item_xml.set("id", "7002")

def add_fire_staff(parentElement):
    # Create the XML object for fire staff
    item_xml = SubElement(parentElement, "item")
    item_xml.set("id", "8001")

def add_frat_tank(parentElement):
    # Create the XML object for frat tank
    item_xml = SubElement(parentElement, "item")
    item_xml.set("id", "2000")

def add_iron_helmet(parentElement):
    # Create the XML object for iron helmet
    item_xml = SubElement(parentElement, "item")
    item_xml.set("id", "3001")


def add_jackson_gloves(parentElement):
    # Create the XML object for jackson gloves
    item_xml = SubElement(parentElement, "item")
    item_xml.set("id", "4002")

def add_ninja_stars(parentElement):
    # Create the XML object for ninja stars
    item_xml = SubElement(parentElement, "item")
    item_xml.set("id", "9001")


def add_peter_pan_leggings(parentElement):
    # Create the XML object for peter pan leggings
    item_xml = SubElement(parentElement, "item")
    item_xml.set("id", "5003")


def add_throwing_knives(parentElement):
    # Create the XML object for throwin knives
    item_xml = SubElement(parentElement, "item")
    item_xml.set("id", "9000")


def add_two_handed_axe(parentElement):
    # Create the XML object for two handed axe
    item_xml = SubElement(parentElement, "item")
    item_xml.set("id", "7000")

def add_water_staff(parentElement):
    # Create the XML object for water staff
    item_xml = SubElement(parentElement, "item")
    item_xml.set("id", "8002")

def add_wooden_gauntlets(parentElement):
    # Create the XML object for wooden gauntlets
    item_xml = SubElement(parentElement, "item")
    item_xml.set("id", "4000")

def add_yoga_pants(parentElement):
    # Create the XML object for yoga pants
    item_xml = SubElement(parentElement, "item")
    item_xml.set("id", "5000")


def add_health_pot(parentElement):
    # Create the XML object for health pot
    item_xml = SubElement(parentElement, "item")
    item_xml.set("id", "10000")

def add_mana_pot(parentElement):
    # Create the XML object for mana pot
    item_xml = SubElement(parentElement, "item")
    item_xml.set("id", "10001")

### NEWWWWWWWWWW SHIT

def add_boulder(parentElement):
    # Create the XML object for boulder
    item_xml = SubElement(parentElement, "item")
    item_xml.set("id", "11000")

def add_dave(parentElement):
    # Create the XML object for dave
    item_xml = SubElement(parentElement, "item")
    item_xml.set("id", "11001")

def add_kat_key(parentElement):
    # Create the XML object for dave
    item_xml = SubElement(parentElement, "item")
    item_xml.set("id", "13000")

def add_door(parentElement):
    # Create the XML object for dave
    item_xml = SubElement(parentElement, "item")
    item_xml.set("id", "12001")



def add_cacoon(parentElement):
    # Create the XML object for dave
    item_xml = SubElement(parentElement, "item")
    item_xml.set("id", "14001")

def add_bug_zapper(parentElement):
    # Create the XML object for dave
    item_xml = SubElement(parentElement, "item")
    item_xml.set("id", "14000")
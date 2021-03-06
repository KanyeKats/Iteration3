package Models.Menu;

import Controllers.*;
import Core.State;
import Core.StateManager;
import Models.Entities.Entity;
import Models.Entities.NPC.AI.Personality;
import Models.Entities.NPC.Mount;
import Models.Entities.NPC.NPC;
import Models.Entities.Occupation.Smasher;
import Models.Entities.Occupation.Sneak;
import Models.Entities.Occupation.Summoner;
import Models.Entities.Skills.ActiveSkills.ActiveSkill;
import Models.Entities.Skills.ActiveSkills.ActiveSkillList;
import Models.Entities.Skills.PassiveSkills.PassiveSkill;
import Models.Entities.Skills.PassiveSkills.PassiveSkillList;
import Models.Entities.Stats.Stat;
import Models.Map.Map;
import Models.Map.Terrain;
import Utilities.Action;
import Utilities.Constants;
import Utilities.KeyBindings;
import Utilities.Savable.GameLoader;
import Utilities.Savable.GameSaver;
import Views.AvatarCreationMenuView;
import Views.GameView;
import Views.*;
import javafx.geometry.Point3D;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.util.*;

/**
 * Created by Bradley on 4/4/2016.
 */
public class Menu{

    private ArrayList<MenuOption> menuOptions;
    private int selectedIndex;

    private Menu(ArrayList<MenuOption> menuOptions){
        this.menuOptions = menuOptions;
        selectedIndex = 0;
    }

    public ArrayList<MenuOption> getMenuOptions(){
        return menuOptions;
    }

    public int getSelectedIndex(){
        return selectedIndex;
    }

    public ArrayList<Action> getSelectedActions(){
        return menuOptions.get(selectedIndex).getActions();
    }

    public void nextOption(){
        selectedIndex++;
        selectedIndex = (selectedIndex < menuOptions.size()) ? selectedIndex : 0; // This one's for you Josh :)
    }

    public void previousOption(){
        selectedIndex--;
        selectedIndex = (selectedIndex >=0 ) ? selectedIndex : menuOptions.size() - 1;
    }

    // Factory methods.
    public static Menu createStartMenu(StateManager stateManager){
        ArrayList<MenuOption> options = new ArrayList<>();

        options.add(new MenuOption() {
            @Override
            public String getTitle() {
                return "Create Game";
            }

            @Override
            public ArrayList<Action> getActions() {
                ArrayList<Action> actions = new ArrayList<>();
                actions.add(new Action() {
                    @Override
                    public void execute() {
                        System.out.println("CREATE GAME");
                        Menu avatarCreationMenu = Menu.createAvatarCreationMenu(stateManager);
                        MenuViewController controller = new MenuViewController(stateManager, avatarCreationMenu);
                        AvatarCreationMenuView view = new AvatarCreationMenuView(Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT, avatarCreationMenu);
                        stateManager.setActiveState(new State(controller, view));
                    }
                });
                return actions;
            }

            @Override
            public Object getAttachment() {
                return null;
            }
        });
        options.add(new MenuOption() {
            @Override
            public String getTitle() {
                return "Load Game";
            }

            @Override
            public ArrayList<Action> getActions() {
                ArrayList<Action> actions = new ArrayList<>();
                actions.add(new Action() {
                    @Override
                    public void execute() {
                        System.out.println("LOAD GAME");


                        Map map = GameLoader.loadMap("./res/map/saved.xml");
                        GameView gameView = new GameView(Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT, map.getAvatar(), map);
                        GameViewController gameViewController = new GameViewController(stateManager, map.getAvatar(), map, gameView.getAreaViewPort());
                        gameViewController.loadKeys("./res/map/saved.xml");
                        stateManager.setActiveState(new State(gameViewController, gameView));
                    }
                });
                return actions;
            }

            @Override
            public Object getAttachment() {
                return null;
            }
        });
        options.add(new MenuOption() {
            @Override
            public String getTitle() {
                return "Exit Game";
            }

            @Override
            public ArrayList<Action> getActions() {
                ArrayList<Action> actions = new ArrayList<>();
                actions.add(new Action() {
                    @Override
                    public void execute() {
                        System.exit(0);
                    }
                });
                return actions;
            }

            @Override
            public Object getAttachment() {
                return null;
            }
        });
        return new Menu(options);
    }
    public static Menu createAvatarCreationMenu(StateManager stateManager){
        ArrayList<MenuOption> options = new ArrayList<>();

        options.add(new MenuOption() {
            @Override
            public String getTitle() {
                return "Smasher";
            }

            @Override
            public ArrayList<Action> getActions() {
                ArrayList<Action> actions = new ArrayList<>();
                actions.add(new Action() {
                    @Override
                    public void execute() {

                        System.out.println("Smasher");

                        Map map = GameLoader.loadMap("./res/map/default_map.xml");
                        Terrain []passableTerrains =  {Terrain.EARTH, Terrain.WATER};
                        Entity avatar = new Entity(new Smasher(), GameLoader.DEFAULT_STARTING_POINT, map, false, passableTerrains); // TOD0: Improve avatar initial placement.
                        avatar.setAsAvatar();
                        map.setAvatar(avatar);
                        map.insertEntity(avatar, GameLoader.DEFAULT_STARTING_POINT);

                        Terrain []passableTerrainsNPC =  {Terrain.EARTH, Terrain.WATER};
                        NPC pet = new NPC(new Smasher(), new Point3D(2, -1, 0), map, passableTerrainsNPC, Personality.PET, false);
                        map.insertEntity(pet, new Point3D(2, -1, 0));

                        NPC shopKeeper = new NPC(new Smasher(), new Point3D(-7, -2, 0), map, passableTerrainsNPC, Personality.SHOP_KEEPER, false);
                        map.insertEntity(shopKeeper, new Point3D(-7, -2, 0));

                        NPC enemy = new NPC(new Sneak(), new Point3D(0, -4, 0), map, passableTerrainsNPC, Personality.HOSTILE, false);
                        map.insertEntity(enemy, new Point3D(0, -4, 0));

                        Mount hand = new Mount(new Point3D( 12,-1 , 0),map,passableTerrainsNPC, false);
                        map.insertEntity(hand,new Point3D(12,-1,0));
                        GameView gameView = new GameView(Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT, avatar, map);
                        GameViewController gameViewController = new GameViewController(stateManager, avatar, map, gameView.getAreaViewPort());
                        stateManager.setActiveState(new State(gameViewController, gameView));
                    }
                });
                return actions;
            }

            @Override
            public Object getAttachment() {
                return null;
            }
        });
        options.add(new MenuOption() {
            @Override
            public String getTitle() {
                return "Summoner";
            }

            @Override
            public ArrayList<Action> getActions() {
                ArrayList<Action> actions = new ArrayList<>();
                actions.add(new Action() {
                    @Override
                    public void execute() {
                        System.out.println("Summoner");

                        Map map = GameLoader.loadMap("./res/map/default_map.xml");
                        Terrain []passableTerrainsAvatar =  {Terrain.EARTH, Terrain.WATER,Terrain.SKY};
                        Entity avatar = new Entity(new Summoner(), GameLoader.DEFAULT_STARTING_POINT, map,true, passableTerrainsAvatar); // TOD0: Improve avatar initial placement.
                        avatar.setAsAvatar();
                        map.setAvatar(avatar);
                        map.insertEntity(avatar, GameLoader.DEFAULT_STARTING_POINT);
                        Terrain []passableTerrainsNPC =  {Terrain.EARTH, Terrain.WATER};
                        // TODO: Remove after testing.
                        //why does the entity need a point for its constructor and the insert entity map takes in the point anyway?
                        NPC pet = new NPC(new Smasher(), new Point3D(2, -1, 0), map, passableTerrainsNPC, Personality.PET, false);
                        map.insertEntity(pet, new Point3D(2, -1, 0));

                        NPC shopKeeper = new NPC(new Smasher(), new Point3D(-7, -2, 0), map, passableTerrainsNPC, Personality.SHOP_KEEPER, false);
                        map.insertEntity(shopKeeper, new Point3D(-7, -2, 0));

                        NPC enemy = new NPC(new Sneak(), new Point3D(0, -4, 0), map, passableTerrainsNPC, Personality.HOSTILE, false);
                        map.insertEntity(enemy, new Point3D(0, -4, 0));

                        Mount hand = new Mount(new Point3D( 12,-1 , 0),map,passableTerrainsNPC, false);
                        map.insertEntity(hand,new Point3D(12,-1,0));


                        GameView gameView = new GameView(Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT, avatar, map);
                        GameViewController gameViewController = new GameViewController(stateManager, avatar, map, gameView.getAreaViewPort());
                        stateManager.setActiveState(new State(gameViewController, gameView));
                    }
                });
                return actions;
            }

            @Override
            public Object getAttachment() {
                return null;
            }
        });
        options.add(new MenuOption() {
            @Override
            public String getTitle() {
                return "Sneak";
            }

            @Override
            public ArrayList<Action> getActions() {
                ArrayList<Action> actions = new ArrayList<>();
                actions.add(new Action() {
                    @Override
                    public void execute() {
                        System.out.println("Sneak");

                        Map map = GameLoader.loadMap("./res/map/default_map.xml");
                        Terrain []passableTerrains =  {Terrain.EARTH, Terrain.WATER};
                        Entity avatar = new Entity(new Sneak(), GameLoader.DEFAULT_STARTING_POINT, map,false, passableTerrains); // TOD0: Improve avatar initial placement.
                        avatar.setAsAvatar();
                        map.setAvatar(avatar);
                        map.insertEntity(avatar, GameLoader.DEFAULT_STARTING_POINT);
                        // why does the entity need a point for its constructor and the insert entity map takes in the point anyway?
                        NPC pet = new NPC(new Smasher(), new Point3D(2, -1, 0), map, passableTerrains, Personality.PET, false);
                        map.insertEntity(pet, new Point3D(2, -1, 0));

                        NPC shopKeeper = new NPC(new Smasher(), new Point3D(-7, -2, 0), map, passableTerrains, Personality.SHOP_KEEPER, false);
                        map.insertEntity(shopKeeper, new Point3D(-7, -2, 0));

                        NPC enemy = new NPC(new Sneak(), new Point3D(0, -4, 0), map, passableTerrains, Personality.HOSTILE, false);
                        map.insertEntity(enemy, new Point3D(0, -4, 0));
//
                        Mount hand = new Mount(new Point3D(-1, 1, 0),map,passableTerrains, false);
                        map.insertEntity(hand,new Point3D(-1,1,0));


                        GameView gameView = new GameView(Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT, avatar, map);
                        GameViewController gameViewController = new GameViewController(stateManager, avatar, map, gameView.getAreaViewPort());
                        stateManager.setActiveState(new State(gameViewController, gameView));
                    }
                });
                return actions;
            }

            @Override
            public Object getAttachment() {
                return null;
            }
        });
        return new Menu(options);
    }

    public static Menu createSkillViewPortMenu(StateManager stateManager, Entity avatar){
        PassiveSkillList passiveSkillList = avatar.getPassiveSkillList();
        ActiveSkillList activeSkillList = avatar.getActiveSkillList();
        ArrayList<MenuOption> options = new ArrayList<>();

        for(int i = 0; i < passiveSkillList.size(); i++){
            PassiveSkill passiveSkill = passiveSkillList.get(i);

            options.add(new MenuOption() {
                @Override
                public String getTitle() {
                    return passiveSkill.toString() + ":  " + passiveSkill.getLevel();
                }

                @Override
                public ArrayList<Action> getActions() {
                    ArrayList<Action> actions = new ArrayList<>();
                    actions.add(new Action() {
                        @Override
                        public void execute() {
                            if(avatar.getStats().getStat(Stat.SKILL_POINTS) > 0){
                                passiveSkill.incrementLevel();
                                avatar.getStats().modifyStat(Stat.SKILL_POINTS, -1);
                                System.out.println(passiveSkill.toString() + " upgraded!");
                                System.out.println("You have " + avatar.getStats().getStat(Stat.SKILL_POINTS) + " remaining!");
                            }
                            else
                                System.out.println("You don't have any skill points!");
                        }
                    });
                    return actions;
                }

                @Override
                public Object getAttachment() {
                    return null;
                }
            });
        }

        for(int i = 0; i < activeSkillList.size(); i++){
            ActiveSkill activeSkill = activeSkillList.get(i);

            options.add(new MenuOption() {
                @Override
                public String getTitle() {
                    return activeSkill.toString() + ":  " + activeSkill.getLevel();
                }

                @Override
                public ArrayList<Action> getActions() {
                    ArrayList<Action> actions = new ArrayList<>();
                    actions.add(new Action() {
                        @Override
                        public void execute() {
                            if(avatar.getStats().getStat(Stat.SKILL_POINTS) > 0){
                                activeSkill.incrementLevel();
                                avatar.getStats().modifyStat(Stat.SKILL_POINTS, -1);
                                System.out.println(activeSkill.toString() + " upgraded!");
                                System.out.println("You have " + avatar.getStats().getStat(Stat.SKILL_POINTS) + " remaining!");
                            }
                            else
                                System.out.println("You don't have any skill points!");
                        }
                    });
                    return actions;
                }

                @Override
                public Object getAttachment() {
                    return null;
                }
            });
        }

        return new Menu(options);
    }

    //This method creates a pause menu model
    public static Menu createPauseMenu(StateManager stateManager, GameViewController gameViewController, Entity avatar) {
        ArrayList<MenuOption> options = new ArrayList<>();

        options.add(new MenuOption() {
            @Override
            public String getTitle() {
                return "Resume";
            }

            @Override
            public ArrayList<Action> getActions() {
                ArrayList<Action> actions = new ArrayList<>();
                actions.add(new Action() {
                    @Override
                    public void execute() {
                        System.out.println("Resume");
                        stateManager.goToPreviousState();
                    }
                });
                return actions;
            }

            @Override
            public Object getAttachment() {
                return null;
            }
        });
        options.add(new MenuOption() {
            @Override
            public String getTitle() {
                return "Save Game";
            }

            @Override
            public ArrayList<Action> getActions() {
                ArrayList<Action> actions = new ArrayList<>();
                actions.add(new Action() {
                    @Override
                    public void execute() {
                        System.out.println("Save Game");
                        if(avatar.isMounted()) {
                            Mount mount = avatar.getMount();
                            avatar.unMountVehicle();
                            GameSaver.saveMap(avatar.getMap(), gameViewController);
                            mount.mount(avatar);
                        }
                        else
                            GameSaver.saveMap(avatar.getMap(), gameViewController);

                        // TODO: Implement this.
                    }
                });
                return actions;
            }

            @Override
            public Object getAttachment() {
                return null;
            }
        });
        options.add(new MenuOption() {
            @Override
            public String getTitle() {
                return "Change Keys";
            }

            @Override
            public ArrayList<Action> getActions() {
                ArrayList<Action> actions = new ArrayList<>();
                actions.add(new Action() {
                    @Override
                    public void execute() {
                        System.out.println("Change keys");
                        Models.Menu.Menu reconfigureKeysMenu = Models.Menu.Menu.createReconfigureKeysMenu(stateManager, gameViewController);
                        MenuViewController reconfigureKeysMenuController = new MenuViewController(stateManager, reconfigureKeysMenu);
                        ReconfigureKeysView reconfigureKeysView = new ReconfigureKeysView(Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT, reconfigureKeysMenu);
                        stateManager.setActiveState(new State(reconfigureKeysMenuController, reconfigureKeysView));
                    }
                });
                return actions;
            }

            @Override
            public Object getAttachment() {
                return null;
            }
        });
        options.add(new MenuOption() {
            @Override
            public String getTitle() {
                return "Exit Game";
            }

            @Override
            public ArrayList<Action> getActions() {
                ArrayList<Action> actions = new ArrayList<>();
                actions.add(new Action() {
                    @Override
                    public void execute() {
                        System.exit(0);
                    }
                });
                return actions;
            }

            @Override
            public Object getAttachment() {
                return null;
            }
        });
        return new Menu(options);
    }

    public static Menu createGameOverMenu(StateManager stateManager) {
        ArrayList<MenuOption> options = new ArrayList<>();

        options.add(new MenuOption() {
            @Override
            public String getTitle() {
                return "New Game?";
            }

            @Override
            public ArrayList<Action> getActions() {
                ArrayList<Action> actions = new ArrayList<>();
                actions.add(new Action() {
                    @Override
                    public void execute() {
                        System.out.println("New Game?");
                        stateManager.startOver();
                    }
                });
                return actions;
            }

            @Override
            public Object getAttachment() {
                return null;
            }
        });
        options.add(new MenuOption() {
            @Override
            public String getTitle() {
                return "Exit Game";
            }

            @Override
            public ArrayList<Action> getActions() {
                ArrayList<Action> actions = new ArrayList<>();
                actions.add(new Action() {
                    @Override
                    public void execute() {
                        System.exit(0);
                    }
                });
                return actions;
            }

            @Override
            public Object getAttachment() {
                return null;
            }
        });
        return new Menu(options);
    }

    public static Menu createReconfigureKeysMenu(StateManager stateManager, GameViewController gameViewController){
        KeyBindings keyBindings = gameViewController.getKeyBindings();
        ArrayList<MenuOption> options = new ArrayList<>();

        for(int i : keyBindings.getKeys()){
            options.add(new MenuOption() {
                @Override
                public String getTitle() {
                    return keyBindings.getKeyAction(i).toString() + ":  " + KeyEvent.getKeyText(i);
                }

                @Override
                public ArrayList<Action> getActions() {
                    ArrayList<Action> actions = new ArrayList<>();
                    actions.add(new Action() {
                        @Override
                        public void execute() {
                            System.out.println(keyBindings.getKeyAction(i).toString());

                            JFrame jf = new JFrame();
                            jf.setVisible(true);
                            KeyListener listen = new KeyListener(){
                                public void keyTyped(KeyEvent e){}
                                public void keyReleased(KeyEvent e){}

                                //This whole method is nasty...but it works
                                public void keyPressed(KeyEvent e){
                                    gameViewController.remapKey(i, e.getKeyCode());
                                    System.out.println("New key: " + KeyEvent.getKeyText(e.getKeyCode()));
                                    jf.dispose();
                                    stateManager.goToPreviousState();
                                    java.util.Timer timer = new java.util.Timer();
                                    timer.schedule(new TimerTask() {
                                        @Override
                                        public void run() {
                                            Models.Menu.Menu reconfigureKeysMenu = Models.Menu.Menu.createReconfigureKeysMenu(stateManager, gameViewController);
                                            MenuViewController reconfigureKeysMenuController = new MenuViewController(stateManager, reconfigureKeysMenu);
                                            ReconfigureKeysView reconfigureKeysView = new ReconfigureKeysView(Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT, reconfigureKeysMenu);
                                            stateManager.setActiveState(new State(reconfigureKeysMenuController, reconfigureKeysView));
                                        }
                                    }, 2000 / Constants.FRAME_RATE);
                                }
                            };
                            jf.addKeyListener(listen);
                        }
                    });
                    return actions;
                }

                @Override
                public Object getAttachment() {
                    return null;
                }
            });
        }

        return new Menu(options);
    }

    public static Menu createNPCMenu(StateManager stateManager, Entity avatar, Entity npc) {
        ArrayList<MenuOption> options = new ArrayList<>();

        options.add(new MenuOption() {
            @Override
            public String getTitle() {
                return "Talk";
            }

            @Override
            public ArrayList<Action> getActions() {
                ArrayList<Action> actions = new ArrayList<>();
                actions.add(new Action() {
                    @Override
                    public void execute() {
                        System.out.println("Talk");
                        String dialog = ((NPC) npc).getDialog();
                        System.out.println(dialog);

                        Models.Menu.Menu npcTalkMenu = Models.Menu.Menu.createNPCTalkMenu(stateManager);
                        MenuViewController npcTalkViewController = new MenuViewController(stateManager, npcTalkMenu);
                        BufferedImage lastViewContent = stateManager.getCurrentViewContent();
                        NPCTalkView npcTalkView = new NPCTalkView(Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT, npcTalkMenu, dialog, lastViewContent);
                        stateManager.setActiveState(new State(npcTalkViewController, npcTalkView));
                    }
                });
                return actions;
            }

            @Override
            public Object getAttachment() {
                return null;
            }
        });
        options.add(new MenuOption() {
            @Override
            public String getTitle() {
                return "Trade";
            }

            @Override
            public ArrayList<Action> getActions() {
                ArrayList<Action> actions = new ArrayList<>();
                actions.add(new Action() {
                    @Override
                    public void execute() {
                        if(((NPC)npc).willTrade()){
                            NPCShopView npcShopView = new NPCShopView(Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT, avatar.getInventory(), npc.getInventory(), avatar.getStats().getStat(Stat.BARGAIN));
                            NPCShopViewController npcShopViewController = new NPCShopViewController(stateManager, npcShopView);
                            stateManager.setActiveState(new State(npcShopViewController, npcShopView));
                        }else{
                            System.out.println("He doesn't wanna trade with ya!");
                            // TODO: Make a toast that says this.
                        }
                    }
                });
                return actions;
            }

            @Override
            public Object getAttachment() {
                return null;
            }
        });
        options.add(new MenuOption() {
            @Override
            public String getTitle() {
                return "Use Item";
            }

            @Override
            public ArrayList<Action> getActions() {
                ArrayList<Action> actions = new ArrayList<>();
                actions.add(new Action() {
                    @Override
                    public void execute() {
                        System.out.println("Use item");
                        InventoryView inventoryView = new InventoryView(Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT, avatar.getInventory());
                        UseItemOnNPCController useItemOnNPCController = new UseItemOnNPCController(stateManager, inventoryView, avatar, npc);
                        stateManager.setActiveState(new State(useItemOnNPCController, inventoryView));
                    }
                });
                return actions;
            }

            @Override
            public Object getAttachment() {
                return null;
            }
        });
        options.add(new MenuOption() {
            @Override
            public String getTitle() {
                return "Leave";
            }

            @Override
            public ArrayList<Action> getActions() {
                ArrayList<Action> actions = new ArrayList<>();
                actions.add(new Action() {
                    @Override
                    public void execute() {
                        System.out.println("Leave");
                        stateManager.goToPreviousState();
                    }
                });
                return actions;
            }

            @Override
            public Object getAttachment() {
                return null;
            }
        });
        return new Menu(options);
    }

    public static Menu createNPCTalkMenu(StateManager stateManager){
        ArrayList<MenuOption> options = new ArrayList<>();
        options.add(new MenuOption() {
            @Override
            public String getTitle() {
                return "Back";
            }

            @Override
            public ArrayList<Action> getActions() {
                ArrayList<Action> actions = new ArrayList<>();
                actions.add(new Action() {
                    @Override
                    public void execute() {
                        stateManager.goToPreviousState();
                    }
                });
                return actions;
            }

            @Override
            public Object getAttachment() {
                return null;
            }
        });
        return new Menu(options);
    }

    // TODO: Create other factory methods.
}

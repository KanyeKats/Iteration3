package Models.Menu;

import Controllers.GameViewController;
import Controllers.MenuViewController;
import Controllers.ViewController;
import Core.State;
import Core.StateManager;
import Models.Entities.Entity;
import Models.Entities.NPC.AI.Personality;
import Models.Entities.NPC.NPC;
import Models.Entities.Occupation.Smasher;
import Models.Entities.Occupation.Sneak;
import Models.Entities.Occupation.Summoner;
import Models.Entities.Skills.ActiveSkills.ActiveSkill;
import Models.Entities.Skills.ActiveSkills.ActiveSkillList;
import Models.Entities.Skills.InfluenceEffect.LinearEffect;
import Models.Entities.Skills.InfluenceEffect.RadialEffect;
import Models.Entities.Skills.PassiveSkills.PassiveSkill;
import Models.Entities.Skills.PassiveSkills.PassiveSkillList;
import Models.Entities.Stats.Stat;
import Models.Map.Direction;
import Models.Map.Map;
import Models.Map.Terrain;
import Utilities.Action;
import Utilities.Constants;
import Utilities.KeyBindings;
import Utilities.Savable.GameLoader;
import Utilities.Savable.GameSaver;
import Views.AvatarCreationMenuView;
import Views.GameView;
import Views.ReconfigureKeysView;
import javafx.geometry.Point3D;
import javafx.scene.input.KeyCode;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

/**
 * Created by Bradley on 4/4/2016.
 */
public class Menu extends java.util.Observable{

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

    public void refresh(){
        setChanged();
        notifyObservers();
    }

    public void nextOption(){
        selectedIndex++;
        selectedIndex = (selectedIndex < menuOptions.size()) ? selectedIndex : 0; // This one's for you Josh :)
        refresh();
    }

    public void previousOption(){
        selectedIndex--;
        selectedIndex = (selectedIndex >=0 ) ? selectedIndex : menuOptions.size() - 1;
        refresh();
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
                        Entity avatar = new Entity(new Smasher(), GameLoader.DEFAULT_STARTING_POINT, map, passableTerrains); // TOD0: Improve avatar initial placement.
                        map.insertEntity(avatar, GameLoader.DEFAULT_STARTING_POINT);
                        GameViewController gameViewController = new GameViewController(stateManager, avatar, map);
                        GameView gameView = new GameView(Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT, avatar, map);
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
                        Terrain []passableTerrains =  {Terrain.EARTH, Terrain.WATER};
                        Entity avatar = new Entity(new Summoner(), GameLoader.DEFAULT_STARTING_POINT, map, passableTerrains); // TOD0: Improve avatar initial placement.
                        map.insertEntity(avatar, GameLoader.DEFAULT_STARTING_POINT);

                        // TODO: Remove after testing.
                        NPC shopkeeper = new NPC(new Smasher(), new Point3D(2, -1, 0), map, passableTerrains, Personality.HOSTILE);
                        map.insertEntity(shopkeeper, new Point3D(2, -1, 0));

                        GameViewController gameViewController = new GameViewController(stateManager, avatar, map);
                        GameView gameView = new GameView(Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT, avatar, map);
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
                        Entity avatar = new Entity(new Sneak(), GameLoader.DEFAULT_STARTING_POINT, map, passableTerrains); // TOD0: Improve avatar initial placement.
                        map.insertEntity(avatar, GameLoader.DEFAULT_STARTING_POINT);
                        GameViewController gameViewController = new GameViewController(stateManager, avatar, map);
                        GameView gameView = new GameView(Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT, avatar, map);
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
                    return passiveSkill.toString() + " - " + passiveSkill.getLevel();
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
                    return activeSkill.toString() + " - " + activeSkill.getLevel();
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
    public static Menu createPauseMenu(StateManager stateManager, GameViewController gameViewController) {
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
                                public void keyPressed(KeyEvent e){
                                    gameViewController.remapKey(i, e.getKeyCode());
                                    System.out.println("New key: " + KeyEvent.getKeyText(e.getKeyCode()));
                                    jf.dispose();
                                    stateManager.goToPreviousState();
                                    Models.Menu.Menu reconfigureKeysMenu = Models.Menu.Menu.createReconfigureKeysMenu(stateManager, gameViewController);
                                    MenuViewController reconfigureKeysMenuController = new MenuViewController(stateManager, reconfigureKeysMenu);
                                    ReconfigureKeysView reconfigureKeysView = new ReconfigureKeysView(Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT, reconfigureKeysMenu);
                                    stateManager.setActiveState(new State(reconfigureKeysMenuController, reconfigureKeysView));
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

    // TODO: Create other factory methods.
}

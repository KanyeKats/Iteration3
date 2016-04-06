package Models.Menu;

import Controllers.MenuViewController;
import Core.State;
import Core.StateManager;
import Utilities.Action;
import Utilities.Constants;
import Views.AvatarCreationMenuView;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;

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

    public void nextOption(){
        selectedIndex++;
        selectedIndex = (selectedIndex < menuOptions.size()) ? selectedIndex : 0; // This one's for you Josh :)
        setChanged();
        notifyObservers();
    }

    public void previousOption(){
        selectedIndex--;
        selectedIndex = (selectedIndex >=0 ) ? selectedIndex : menuOptions.size() - 1;
        setChanged();
        notifyObservers();
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

                    @Override
                    public void finish() {}
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

                    @Override
                    public void finish() {}
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

                    @Override
                    public void finish() {}
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
                    }

                    @Override
                    public void finish() {}
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
                        // TODO: Implement this.
                    }

                    @Override
                    public void finish() {}
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
                    }

                    @Override
                    public void finish() {}
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

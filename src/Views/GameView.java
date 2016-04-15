package Views;

import Models.Entities.Entity;
import Models.Map.Map;

import java.awt.*;

/**
 * Created by Bradley on 4/7/16.
 */
public class GameView extends View {

    private AreaViewport areaViewport;
    private StatusViewPort statusViewport;
    private ToastView toastView;
//    private SkillsViewport skillsViewport;

    public GameView(int width, int height, Entity entity, Map map) {
        super(width, height);

        this.areaViewport = new AreaViewport(width, height, map, entity);
        this.statusViewport = new StatusViewPort(width, height, entity);
        this.toastView = new ToastView(width, height);

//        this.skillsViewport = new SkillsViewport(entity.getSkillList());

        areaViewport.addObserver(this);
        statusViewport.addObserver(this);
        toastView.addObserver(this);
//        skillsViewport.addObserver(this);

        repaint();
    }

    @Override
    protected void repaint() {

        // Extract the graphics object from this views content;
        Graphics g = viewContent.getGraphics();

        // Set content changed for the viewports
        areaViewport.setContentChanged(true);
        statusViewport.setContentChanged(true);
        toastView.setContentChanged(true);

        // Render the viewports onto this view.
        areaViewport.render(g);
        statusViewport.render(g);
        toastView.render(g);

//        skillsViewport.render(g);

        // Cleanup
        g.dispose();
    }
}

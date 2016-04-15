package Models.Entities.NPC.AI;

/**
 * Created by Aidan on 4/7/2016.
 */
public enum Personality {
    SHOP_KEEPER(0, 1, 0, 1, 0, "Welcome to my shop! I'd love to trade with you."),
    FRIENDLY(0, 1, 0.2, 0.5, 0.2, "Welcome friend!"),
    HOSTILE(1, 0, 0, 0.3, 0.0, "There ain't enough room in this town for the both of us."),
    PET(0, 1, 1, 1, 0, "I used to be an adventurer like you. Then I took an arrow to the knee.");

    private double attackOnSightProbability;
    private double tradeProbability;
    private double followProbability;
    private double collectItemProbability;
    private double activateAreaEffectProbability;
    private String dialog;

    Personality(double attackOnSightProbability, double tradeProbability, double followProbability, double collectItemProbability, double activateAreaEffectProbability, String dialog){
        this.attackOnSightProbability = attackOnSightProbability;
        this.tradeProbability = tradeProbability;
        this.followProbability = followProbability;
        this.collectItemProbability = collectItemProbability;
        this.activateAreaEffectProbability = activateAreaEffectProbability;
        this.dialog = dialog;
    }

    public boolean willAttack(){
        return attackOnSightProbability >= Math.random();
    }
    public boolean willTrade(){
        return tradeProbability >= Math.random();
    }
    public boolean willFollow(){ return followProbability >= Math.random();}
    public boolean willCollectItem() { return collectItemProbability >= Math.random(); }
    public boolean willActivateAreaEffect() { return activateAreaEffectProbability >= Math.random(); }
    public String getDialog() { return dialog; }
}

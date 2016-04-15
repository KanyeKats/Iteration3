package Models.Entities.NPC.AI;

/**
 * Created by Aidan on 4/7/2016.
 */
public enum Personality {
    SHOP_KEEPER(0, 1, 0, 1, 0),
    FRIENDLY(0, 1, 0.2, 0.5, 0.2),
    HOSTILE(1, 0, 0, 0.3, 0.9),
    PET(0, 1, 1, 1, 1);

    private double attackOnSightProbability;
    private double tradeProbability;
    private double followProbability;
    private double collectItemProbability;
    private double activateAreaEffectProbability;

    Personality(double attackOnSightProbability, double tradeProbability, double followProbability, double collectItemProbability, double activateAreaEffectProbability){
        this.attackOnSightProbability = attackOnSightProbability;
        this.tradeProbability = tradeProbability;
        this.followProbability = followProbability;
        this.collectItemProbability = collectItemProbability;
        this.activateAreaEffectProbability = activateAreaEffectProbability;
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
}

public class Item {
    private String name;
    private String description;
    private int weight;
    private int uses;
    private int damage;
    private int healingAmount;

    public Item(String name, String description, int weight, int uses, int damage, int healingAmount) {
        this.name = name;
        this.description = description;
        this.weight = weight;
        this.uses = uses;
        this.damage = damage;
        this.healingAmount = healingAmount;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getWeight() {
        return weight;
    }

    public int getUses() {
        return uses;
    }

    public void setUses(int uses) {
        this.uses = uses;
    }
    
    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public int getHealingAmount() {
        return healingAmount;
    }

    public void setHealingAmount(int healingAmount) {
        this.healingAmount = healingAmount;
    }

    public void useItem() {
        if (uses > 0) {
            uses--;
        }
    }
}

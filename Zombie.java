public class Zombie {
    private String name;
    private String description;
    private int health;
    private int damage;

    public Zombie(String name, String description, int health, int damage) {
        this.name = name;
        this.description = description;
        this.health = health;
        this.damage = damage;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
        if (this.health <= 0) {
            System.out.println(this.name + " has been defeated.");
        }
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public void attack(Player player) {
        System.out.println(this.name + " attacks " + player.getName() + " for " + damage + " damage.");
        player.takeDamage(damage);
    }

    public void takeDamage(int damage) {
        System.out.println(this.name + " took " + damage + " damage. Health is now: " + health);
        setHealth(health - damage);

    }
}

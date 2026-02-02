import edu.princeton.cs.algs4.StdRandom;

import java.awt.*;
import java.util.Map;

public class Particle {
    public ParticleFlavor flavor;
    public int lifespan;

    public static final int PLANT_LIFESPAN = 150;
    public static final int FLOWER_LIFESPAN = 75;
    public static final int FIRE_LIFESPAN = 10;
    public static final Map<ParticleFlavor, Integer> LIFESPANS =
            Map.of(ParticleFlavor.FLOWER, FLOWER_LIFESPAN,
                   ParticleFlavor.PLANT, PLANT_LIFESPAN,
                   ParticleFlavor.FIRE, FIRE_LIFESPAN);

    public Particle(ParticleFlavor flavor) {
        this.flavor = flavor;
        if (LIFESPANS.containsKey(flavor)) {
            this.lifespan = LIFESPANS.get(flavor);
        }
        else {
            this.lifespan = -1;
        }

    }

    public void decrementLifespan() {
        if (this.lifespan > 0) {
            this.lifespan --;
        }
        if (this.lifespan == 0) {
            this.flavor = ParticleFlavor.EMPTY;
            this.lifespan = -1;
        }
    }

    public Color color() {
        if(this.flavor == ParticleFlavor.PLANT){
            return new Color(0, 255, 0);
        }
        if (this.flavor == ParticleFlavor.FLOWER) {
            return new Color(255, 141, 161);
        }
        if (this.flavor == ParticleFlavor.FIRE) {
            return new Color(255, 0, 0);
        }
        if (this.flavor == ParticleFlavor.EMPTY) {
            return Color.BLACK;
        }
        if (this.flavor == ParticleFlavor.SAND) {
            return Color.YELLOW;
        }
        if (this.flavor == ParticleFlavor.BARRIER) {
            return Color.GRAY;
        }
        if (this.flavor == ParticleFlavor.WATER) {
            return Color.BLUE;
        }
        if (this.flavor == ParticleFlavor.FOUNTAIN) {
            return Color.CYAN;
        }
    return Color.GRAY;
    }

    public void moveInto(Particle other) {
        other.flavor = this.flavor;
        other.lifespan = this.lifespan;
        this.flavor = ParticleFlavor.EMPTY;
        this.lifespan = -1;
    }

    public void fall(Map<Direction, Particle> neighbors) {
        Particle p = neighbors.get(Direction.DOWN);
        if (p.flavor == ParticleFlavor.EMPTY) {
            this.moveInto(p);
        }
    }

    public void flow(Map<Direction, Particle> neighbors) {
        int a = StdRandom.uniformInt(3);
        if (a == 0) {
            return;
        }
        if (a == 1){
            if (neighbors.get(Direction.LEFT).flavor == ParticleFlavor.EMPTY) {
                this.moveInto(neighbors.get(Direction.LEFT));
            }
        }
        if (a == 2){
            if (neighbors.get(Direction.RIGHT).flavor == ParticleFlavor.EMPTY) {
                this.moveInto(neighbors.get(Direction.RIGHT));
            }
        }
    }

    public void grow(Map<Direction, Particle> neighbors) {
        int a = StdRandom.uniformInt(10);
        if (neighbors.get(Direction.UP).flavor == ParticleFlavor.EMPTY) {
            if (a == 0) {
                (neighbors.get(Direction.UP)).flavor = this.flavor;
                (neighbors.get(Direction.UP)).lifespan = (LIFESPANS.get(this.flavor));
            }
        }
        if (neighbors.get(Direction.LEFT).flavor == ParticleFlavor.EMPTY) {
            if (a == 0) {
                (neighbors.get(Direction.LEFT)).flavor = this.flavor;
                (neighbors.get(Direction.LEFT)).lifespan = (LIFESPANS.get(this.flavor));
            }
        }
        if (neighbors.get(Direction.RIGHT).flavor == ParticleFlavor.EMPTY) {
            if (a == 0) {
                (neighbors.get(Direction.RIGHT)).flavor = this.flavor;
                (neighbors.get(Direction.RIGHT)).lifespan = (LIFESPANS.get(this.flavor));
            }
        }
        else {
                return;
            }

    }

    public void burn(Map<Direction, Particle> neighbors) {
        if (neighbors.get(Direction.RIGHT).flavor == ParticleFlavor.PLANT || neighbors.get(Direction.RIGHT).flavor == ParticleFlavor.FLOWER) {
            int a = StdRandom.uniformInt(5);
            if (a == 0 || a == 1) {
                neighbors.get(Direction.RIGHT).flavor = ParticleFlavor.FIRE;
                neighbors.get(Direction.RIGHT).lifespan = FIRE_LIFESPAN;
            }
        }
        if (neighbors.get(Direction.LEFT).flavor == ParticleFlavor.PLANT || neighbors.get(Direction.LEFT).flavor == ParticleFlavor.FLOWER) {
            int a = StdRandom.uniformInt(5);
            if (a == 0 || a == 1) {
                neighbors.get(Direction.LEFT).flavor = ParticleFlavor.FIRE;
                neighbors.get(Direction.LEFT).lifespan = FIRE_LIFESPAN;
            }
        }
        if (neighbors.get(Direction.DOWN).flavor == ParticleFlavor.PLANT || neighbors.get(Direction.DOWN).flavor == ParticleFlavor.FLOWER) {
            int a = StdRandom.uniformInt(5);
            if (a == 0 || a == 1) {
                neighbors.get(Direction.DOWN).flavor = ParticleFlavor.FIRE;
                neighbors.get(Direction.DOWN).lifespan = FIRE_LIFESPAN;
            }
        }
        if (neighbors.get(Direction.UP).flavor == ParticleFlavor.PLANT || neighbors.get(Direction.UP).flavor == ParticleFlavor.FLOWER) {
            int a = StdRandom.uniformInt(5);
            if (a == 0 || a == 1) {
                neighbors.get(Direction.UP).flavor = ParticleFlavor.FIRE;
                neighbors.get(Direction.UP).lifespan = FIRE_LIFESPAN;
            }
        }


    }

    public void action(Map<Direction, Particle> neighbors) {
        if (this.flavor == ParticleFlavor.EMPTY) {
            return;
        }
        if (this.flavor != ParticleFlavor.BARRIER) {
            this.fall(neighbors);
        }
        if (this.flavor == ParticleFlavor.WATER) {
            this.flow(neighbors);
        }
        if (this.flavor == ParticleFlavor.FIRE) {
            this.burn(neighbors);
        }

    }
}
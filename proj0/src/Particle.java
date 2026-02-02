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
        if (flavor == ParticleFlavor.PLANT) {
            this.lifespan = 150;
        }
        if (flavor == ParticleFlavor.FLOWER) {
            this.lifespan = 75;
        }
        if (flavor == ParticleFlavor.FIRE) {
            this.lifespan = 10;
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
        if (flavor == ParticleFlavor.FLOWER) {
            double ratio = (double) Math.max(0, Math.min(lifespan, FLOWER_LIFESPAN)) / FLOWER_LIFESPAN;
            int r = 120 + (int) Math.round((255 - 120) * ratio);
            int g = 70 + (int) Math.round((141 - 70) * ratio);
            int b = 80 + (int) Math.round((161 - 80) * ratio);
            return new Color(r, g, b);
        }
        if (flavor == ParticleFlavor.PLANT) {
            double ratio = (double) Math.max(0, Math.min(lifespan, PLANT_LIFESPAN)) / PLANT_LIFESPAN;
            int g = 120 + (int) Math.round((255 - 120) * ratio);
            return new Color(0, g, 0);
        }
        if (flavor == ParticleFlavor.FIRE) {
            double ratio = (double) Math.max(0, Math.min(lifespan, FIRE_LIFESPAN)) / FIRE_LIFESPAN;
            int r = (int) Math.round(255 * ratio);
            return new Color(r, 0, 0);
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
        if (a == 0) {
            if (neighbors.get(Direction.UP).flavor == ParticleFlavor.EMPTY) {
                (neighbors.get(Direction.UP)).flavor = this.flavor;
                (neighbors.get(Direction.UP)).lifespan = (LIFESPANS.get(this.flavor));
            }
        }
        if (a == 1) {
            if (neighbors.get(Direction.LEFT).flavor == ParticleFlavor.EMPTY) {
                (neighbors.get(Direction.LEFT)).flavor = this.flavor;
                (neighbors.get(Direction.LEFT)).lifespan = (LIFESPANS.get(this.flavor));
            }
        }
        if (a == 2) {
            if (neighbors.get(Direction.RIGHT).flavor == ParticleFlavor.EMPTY) {
                (neighbors.get(Direction.RIGHT)).flavor = this.flavor;
                (neighbors.get(Direction.RIGHT)).lifespan = (LIFESPANS.get(this.flavor));
            } else {
                return;
            }
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
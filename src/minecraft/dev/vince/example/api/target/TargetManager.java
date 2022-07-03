package dev.vince.example.api.target;

import java.util.ArrayList;

public final class TargetManager {
    private final ArrayList<Target> targets = new ArrayList<>();

    public void addTarget(Target target) {
        targets.add(target);
    }

    public ArrayList<Target> getTargets() {
        return targets;
    }

    public void removeTarget(Target target) {
        targets.remove(target);
    }

    public void clearTargets() {
        targets.clear();
    }
}

package bg.tu_varna.sit.f24621660.dnd.entities.stats.contracts.providers;

import bg.tu_varna.sit.f24621660.dnd.entities.stats.contracts.blueprints.Depletable;

public interface Resource extends Attribute, Depletable {
    int getMaxValue();
}

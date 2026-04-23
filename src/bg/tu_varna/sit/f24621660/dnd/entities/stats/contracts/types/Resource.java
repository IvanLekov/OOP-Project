package bg.tu_varna.sit.f24621660.dnd.entities.stats.contracts.types;

import bg.tu_varna.sit.f24621660.dnd.entities.stats.contracts.traits.Depletable;

public interface Resource extends Attribute, Depletable {
    int getMaxValue();
}

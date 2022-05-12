package nl.cfp.application.rest;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
class BuildingRepository {

    private final List<Building> buildings = new ArrayList<>();
    private int id = 0;

    List<Building> findAll() {
        return buildings;
    }

    Building save(Building newBuilding) {
        if(newBuilding.getId() == null) {
            newBuilding.setId(id++);
        } else {
            buildings.removeIf(b->b.getId().equals(newBuilding.getId()));
        }
        buildings.add(newBuilding);
        return newBuilding;
    }

    Optional<Building> findById(Integer id) {

        return buildings.stream().filter(b->b.getId().equals(id)).findFirst();
    }

    void deleteById(Integer id) {

        buildings.removeIf(b->b.getId().equals(id));
    }
}

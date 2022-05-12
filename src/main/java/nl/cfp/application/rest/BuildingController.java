package nl.cfp.application.rest;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
class BuildingController {

    private final BuildingRepository repository;

    BuildingController(BuildingRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/buildings")
    List<Building> all() {
        return repository.findAll();
    }

    @PostMapping("/buildings")
    Building newBuilding(@RequestBody Building newBuilding) {
        return repository.save(newBuilding);
    }

    @GetMapping("/buildings/{id}")
    Building one(@PathVariable int id) {

        return repository.findById(id)
                .orElseThrow(() -> new BuildingNotFoundException(id));
    }

    @PutMapping("/buildings/{id}")
    Building replaceBuilding(@RequestBody Building newBuilding, @PathVariable Integer id) {

        return repository.findById(id)
                .map(building -> {
                    building.setName(newBuilding.getName());
                    building.setSize(newBuilding.getSize());
                    return repository.save(building);
                })
                .orElseGet(() -> {
                    newBuilding.setId(id);
                    return repository.save(newBuilding);
                });
    }

    @DeleteMapping("/buildings/{id}")
    void deleteEmployee(@PathVariable Integer id) {
        repository.deleteById(id);
    }
}

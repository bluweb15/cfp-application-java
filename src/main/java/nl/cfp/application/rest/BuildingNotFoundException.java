package nl.cfp.application.rest;

class BuildingNotFoundException extends RuntimeException {

    BuildingNotFoundException(int id) {
        super("Building not found with id " + id);
    }
}

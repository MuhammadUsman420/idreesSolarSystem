package com.solar.service;

import com.solar.dto.LocationDto;
import com.solar.model.Location;
import com.solar.repository.LocationRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LocationService {

    private LocationRepository locationRepository;

    public LocationService(LocationRepository locationRepository) {
        this.locationRepository = locationRepository;
    }

    public List<Location> getAllLocation(){
        List<Location> location =  locationRepository.findAll();
        return location;
    }

    public LocationDto getLocationById(Long id) throws Exception {
        Optional<Location> location =  locationRepository.findById(id);
        if(location.isPresent()){
            return toDto(location.get());
        }else {
            throw new Exception();
        }
    }

    public void deleteLocationById(Long id) throws Exception {
        try {
            locationRepository.deleteById(id);
        }catch (Exception e){
            throw new Exception();
        }
    }

    public List<Location> addLocation(List<Location> location) {
        List<Location> _location = locationRepository.saveAll(location);
        return _location;
    }

    public Location updateLocation(Long id, Location location) {
        try {
            Location updateLocation = getAllLocation().stream().filter(el -> el.getId().equals(id)).findAny().get();
            if (updateLocation != null) {
                updateLocation.setLatitude(location.getLatitude());
                updateLocation.setElement(location.getElement());
                updateLocation.setLongitude(location.getLongitude());
            }
            return locationRepository.save(updateLocation);
        }catch (Exception e){
            throw new RuntimeException("Cannot Update Solar Form "+e);
        }
    }

    public Location dto(LocationDto locationDto){
        return Location.builder()
                .id(locationDto.getId())

                .longitude(locationDto.getLongitude())
                .latitude(locationDto.getLatitude())
                .element(locationDto.getElement())

                .build();
    }

    public LocationDto toDto(Location location){
        return  LocationDto.builder()
                .id(location.getId())

                .element(location.getElement())
                .latitude(location.getLatitude())
                .longitude(location.getLongitude())

                .build();
    }
}


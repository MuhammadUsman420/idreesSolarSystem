package com.solar.service;


import com.solar.dto.EmailDetailsDto;
import com.solar.dto.SolarFormDto;
import com.solar.model.Location;
import com.solar.model.SolarForm;
import com.solar.repository.SolarFormRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class SolarFormService {

    private SolarFormRepository solarFormRepository;
    private LocationService locationService;
    private EmailService emailService;
    private EntityManager entityManager;

    @Value("${image.bucket.path}")
    private String imageBucketPath;

    @Value("${image.api.url}")
    private String imageApiUrl;

    public SolarFormService(SolarFormRepository solarFormRepository, LocationService locationService
            , EmailService emailService , EntityManager entityManager) {
        this.solarFormRepository = solarFormRepository;
        this.locationService = locationService;
        this.emailService = emailService;
        this.entityManager = entityManager;
    }

    public List<SolarForm> getAllSolarForm(){
        return solarFormRepository.findAll();
    }

    public Page<SolarForm> getAllSolarFormWithPagination(Integer pageNumber, Integer pageSize) {
        Page<SolarForm> solarFormPage = solarFormRepository.findAll(PageRequest.of(pageNumber, pageSize, Sort.by(Sort.Direction.DESC, "id")));
        return solarFormPage;
    }

    public SolarFormDto getSolarFormById(Long id) throws Exception {
        Optional<SolarForm> solarForm =  solarFormRepository.findById(id);
        if(solarForm.isPresent()){
            return toDto(solarForm.get());
        }else {
            throw new Exception();
        }
    }

    public void deleteSolarFormById(Long id) throws Exception {
        try {
            solarFormRepository.deleteById(id);
        }catch (Exception e){
            throw new Exception();
        }
    }

    public SolarFormDto addSolarForm(SolarFormDto solarFormDto) {
        List<Location> locations = null;
        if (solarFormDto.getLocations()!=null){
            locations = locationService.addLocation(solarFormDto.getLocations());
        }
        SolarFormDto _solarFormDto = toDto(solarFormRepository.save(dto(solarFormDto)));

        emailService.sendSimpleMail(new EmailDetailsDto("alihassan48484@gmail.com","Solar Form",solarFormDto));
        emailService.sendSimpleMail(new EmailDetailsDto(solarFormDto.getEmail(),"Your Solar Application Is Submitted",solarFormDto));

        for (Location location : locations){
            location.setSolar(dto(_solarFormDto));
            locationService.updateLocation(location.getId(), location);
        }
        return _solarFormDto;
    }


    public SolarFormDto updateSolarForm(Long id, SolarFormDto solarFormDto) {
        try {
            SolarForm updateSolarFormDto = getAllSolarForm().stream().filter(el -> el.getId().equals(id)).findAny().get();
            if (updateSolarFormDto != null) {

                updateSolarFormDto.setFirstName(solarFormDto.getFirstName());
                updateSolarFormDto.setLastName(solarFormDto.getLastName());
                updateSolarFormDto.setCompany(solarFormDto.getCompany());
                updateSolarFormDto.setAddress(solarFormDto.getAddress());
                updateSolarFormDto.setCountry(solarFormDto.getCountry());

                updateSolarFormDto.setPhoneNumber(solarFormDto.getPhoneNumber());
                updateSolarFormDto.setConsumption(solarFormDto.getConsumption());
                updateSolarFormDto.setArea(solarFormDto.getArea());
                updateSolarFormDto.setLocations(solarFormDto.getLocations());

                updateSolarFormDto.setRoofType(solarFormDto.getRoofType());
                updateSolarFormDto.setRoofInclination(solarFormDto.getRoofInclination());
                updateSolarFormDto.setRoofing(solarFormDto.getRoofing());
                updateSolarFormDto.setBuildingHeight(solarFormDto.getBuildingHeight());

                updateSolarFormDto.setArea(solarFormDto.getArea());
                updateSolarFormDto.setLeaseRooftop(solarFormDto.getLeaseRooftop());
                updateSolarFormDto.setRentRooftop(solarFormDto.getRentRooftop());
                updateSolarFormDto.setBuyRooftop(solarFormDto.getBuyRooftop());


            }

            return toDto(solarFormRepository.save(updateSolarFormDto));
        }catch (Exception e){
            throw new RuntimeException("Cannot Update Solar Form "+e);
        }
    }

    public SolarForm dto(SolarFormDto solarFormDto){
        return SolarForm.builder()
                .id(solarFormDto.getId())

                .firstName(solarFormDto.getFirstName())
                .lastName(solarFormDto.getLastName())
                .company(solarFormDto.getCompany())
                .address(solarFormDto.getAddress())
                .country(solarFormDto.getCountry())

                .email(solarFormDto.getEmail())
                .phoneNumber(solarFormDto.getPhoneNumber())
                .consumption(solarFormDto.getConsumption())
                .notes(solarFormDto.getNotes())
                .privacyCheck(solarFormDto.getPrivacyCheck())

                .roofType(solarFormDto.getRoofType())
                .roofInclination(solarFormDto.getRoofInclination())
                .roofing(solarFormDto.getRoofing())
                .buildingHeight(solarFormDto.getBuildingHeight())

                .area(solarFormDto.getArea())
                .leaseRooftop(solarFormDto.getLeaseRooftop())
                .rentRooftop(solarFormDto.getRentRooftop())
                .buyRooftop(solarFormDto.getBuyRooftop())
                .locations(solarFormDto.getLocations())
                .attachment(solarFormDto.getAttachment())

                .build();
    }

    public SolarFormDto toDto(SolarForm solarForm){
        return  SolarFormDto.builder()
                .id(solarForm.getId())

                .firstName(solarForm.getFirstName())
                .lastName(solarForm.getLastName())
                .company(solarForm.getCompany())
                .address(solarForm.getAddress())
                .country(solarForm.getCountry())

                .email(solarForm.getEmail())
                .phoneNumber(solarForm.getPhoneNumber())
                .consumption(solarForm.getConsumption())
                .notes(solarForm.getNotes())
                .privacyCheck(solarForm.getPrivacyCheck())

                .roofType(solarForm.getRoofType())
                .roofing(solarForm.getRoofing())
                .roofInclination(solarForm.getRoofInclination())
                .buildingHeight(solarForm.getBuildingHeight())
                .area(solarForm.getArea())

                .leaseRooftop(solarForm.getLeaseRooftop())
                .rentRooftop(solarForm.getRentRooftop())
                .buyRooftop(solarForm.getBuyRooftop())
                .locations(solarForm.getLocations())
                .attachment(solarForm.getAttachment())

                .build();
    }

    public String uploadImageAndGetApiPath(MultipartFile image){
        String filename = generateRandomImageName(image);
        final Path filePAth = Paths.get(imageBucketPath);
        Path imagePath = filePAth.resolve(filename);
        try {
            Files.copy(image.getInputStream(),imagePath);
            return imageApiUrl+filename;
        } catch (Exception e) {
            throw new RuntimeException("Could not store the file. Error: " + e.getMessage());
        }

    }

    private String generateRandomImageName(MultipartFile file){
        String randomId = UUID.randomUUID().toString();
        String filename = file.getOriginalFilename();
        String generatedfilename = randomId.concat(filename.substring(filename.lastIndexOf(".")));
        return generatedfilename;
    }

    public ResponseEntity<InputStreamResource> getImage(String filename) {
        try {

//            String currentDirectory = System.getProperty("user.dir");
//            currentDirectory = currentDirectory.replace("\\", "/");
            final Path file = Paths.get(imageBucketPath).resolve(filename);
            Resource resource = new UrlResource(file.toUri());
            InputStream in = resource.getInputStream();
            if (resource.exists() || resource.isReadable()) {
                return ResponseEntity.ok()
                        .contentType(MediaType.valueOf(Files.probeContentType(file)))
                        .body(new InputStreamResource(in));

            } else {
                throw new RuntimeException("Could not read the file!");
            }
        } catch (Exception e) {
            throw new RuntimeException("Error: " + e.getMessage());
        }
    }

    public List<SolarForm> getSolarFormFiltered(String firstName, String lastName, String email) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<SolarForm> criteriaQuery = criteriaBuilder.createQuery(SolarForm.class);
        Root<SolarForm> solarFormRoot = criteriaQuery.from(SolarForm.class);
        List<Predicate> predicates = new ArrayList<>();
        if (firstName!=null){
            predicates.add(criteriaBuilder.like(solarFormRoot.get("firstName"),firstName+"%"));
        }
        if (lastName!=null){
            predicates.add(criteriaBuilder.like(solarFormRoot.get("lastName"),lastName+"%"));
        }
        if (email!=null){
            predicates.add(criteriaBuilder.like(solarFormRoot.get("email"),email+"%"));
        }
        criteriaQuery.where(predicates.toArray(new Predicate[predicates.size()]));
        TypedQuery<SolarForm> query = entityManager.createQuery(criteriaQuery);
        return query.getResultList();
    }

    public long count() {
        return solarFormRepository.count();
    }
}
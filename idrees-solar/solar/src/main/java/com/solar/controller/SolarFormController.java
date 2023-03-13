package com.solar.controller;

import com.solar.dto.SolarFormDto;
import com.solar.model.SolarForm;
import com.solar.service.SolarFormService;
import org.springframework.core.io.InputStreamResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/solar-form")
public class SolarFormController {

    private SolarFormService solarFormService;

    public SolarFormController(SolarFormService solarFormService) {
        this.solarFormService = solarFormService;
    }

    @GetMapping("")
    public ResponseEntity<Page<SolarForm>> getSolarForm(@RequestParam(value = "pageNumber",defaultValue = "0",required = false) Integer pageNumber,
                                                        @RequestParam(value = "pageSize",defaultValue = "10",required = false) Integer pageSize){
        Page<SolarForm> solarFormPage= solarFormService.getAllSolarFormWithPagination(pageNumber,pageSize);
        return ResponseEntity.ok(solarFormPage);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SolarFormDto> getSolarFormById(@PathVariable Long id) throws Exception {
        SolarFormDto solarFormDto = solarFormService.getSolarFormById(id);
        return  ResponseEntity.ok(solarFormDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSolarForm(@PathVariable Long id) throws Exception {
        solarFormService.deleteSolarFormById(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<SolarFormDto> updateSolarFormById(@PathVariable Long id , @RequestBody SolarFormDto solarFormDto) throws Exception {
        try{
            return ResponseEntity.ok(solarFormService.updateSolarForm(id, solarFormDto));
        }catch (Exception e){
            System.out.println(e);
            throw new Exception("Cannot update No Solar Exist having id "+id);
        }
    }

    @PostMapping()
    public ResponseEntity<SolarFormDto> addSolarForm(@RequestBody SolarFormDto solarFormDto) {
        try{
            return ResponseEntity.ok(solarFormService.addSolarForm(solarFormDto));
        }catch (Exception e){
            System.out.println(e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping("/image")
    public String addImage(@RequestParam("image") MultipartFile image) {
        try{
            String fileName = solarFormService.uploadImageAndGetApiPath(image);
            return fileName;
        }catch (Exception e){
            System.out.println(e);
            return "file not uploaded";
        }
    }

    @GetMapping("/search")
    public Page<SolarForm> getSolarFormFiltered(@RequestParam(required = false) String firstName,
                                                @RequestParam(required = false) String lastName,
                                                @RequestParam(required = false) String email,
                                                @RequestParam(defaultValue = "0",required = false) Integer pageNumber,
                                                @RequestParam(defaultValue = "10",required = false) Integer pageSize) {

        Integer start = pageNumber;
        Integer end = pageSize;
        if (pageNumber>0){
            start = (pageNumber*pageSize);
            end = start + pageSize;
        }

        List<SolarForm> solarForms = solarFormService.getSolarFormFiltered(firstName,lastName,email);
        PageImpl page;
        if ((solarForms.size()-start) < (end-start)){
            page = new PageImpl(solarForms.subList(start,solarForms.size()));
        }else {
            page = new PageImpl(solarForms.subList(start,end));
        }
        return page;
    }

    @GetMapping("/count")
    public long count() {
        return solarFormService.count();
    }

}
